package com.wemirr.framework.db.dynamic;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 共享连接池下的 Schema 切换包装器：
 * - getConnection 时切换到目标 schema
 * - close(归还连接) 前恢复原 schema，避免串租户
 */
public final class SchemaSwitchingDataSource implements DataSource {

    private final DataSource delegate;
    private final String schemaName;
    private final String poolKey;

    public SchemaSwitchingDataSource(DataSource delegate, String schemaName, String poolKey) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
        this.schemaName = Objects.requireNonNull(schemaName, "schemaName");
        this.poolKey = Objects.requireNonNull(poolKey, "poolKey");
    }

    public String getPoolKey() {
        return poolKey;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = delegate.getConnection();
        return wrapConnection(conn);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection conn = delegate.getConnection(username, password);
        return wrapConnection(conn);
    }

    private Connection wrapConnection(Connection conn) throws SQLException {
        final String originalSchema = safeGetSchema(conn);
        try {
            conn.setSchema(schemaName);
        } catch (SQLException e) {
            conn.close();
            throw e;
        }

        return (Connection) Proxy.newProxyInstance(
                conn.getClass().getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    if ("close".equals(method.getName()) && method.getParameterCount() == 0) {
                        try {
                            String resetSchema = (originalSchema == null || originalSchema.isBlank()) ? "public" : originalSchema;
                            conn.setSchema(resetSchema);
                        } finally {
                            conn.close();
                        }
                        return null;
                    }
                    if ("isClosed".equals(method.getName()) && method.getParameterCount() == 0) {
                        return conn.isClosed();
                    }
                    return method.invoke(conn, args);
                }
        );
    }

    private String safeGetSchema(Connection conn) {
        try {
            return conn.getSchema();
        } catch (SQLFeatureNotSupportedException ignored) {
            return null;
        } catch (SQLException ignored) {
            return null;
        }
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return delegate.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        delegate.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        delegate.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return delegate.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return delegate.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return delegate.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return delegate.isWrapperFor(iface);
    }
}
