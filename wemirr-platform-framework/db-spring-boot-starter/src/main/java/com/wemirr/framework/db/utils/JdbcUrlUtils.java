package com.wemirr.framework.db.utils;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Levin
 */
public class JdbcUrlUtils {

    // 正则：匹配 jdbc:mysql://host:port/ 之后，? 之前的部分
    private static final Pattern MYSQL_URL_PATTERN = Pattern.compile("(jdbc:.*://.*?/)([^?]*)(.*)");

    public static String extractSchema(String jdbcUrl) {
        if (StrUtil.isBlank(jdbcUrl)) {
            return null;
        }
        Matcher matcher = MYSQL_URL_PATTERN.matcher(jdbcUrl);
        if (!matcher.find()) {
            return null;
        }
        String schema = matcher.group(2);
        if (StrUtil.isBlank(schema)) {
            return null;
        }
        int slashIndex = schema.indexOf('/');
        if (slashIndex > -1) {
            schema = schema.substring(0, slashIndex);
        }
        return schema;
    }

    /**
     * 将 JDBC URL 中的数据库名替换为新的 Schema
     * * 输入: jdbc:mysql://lo:3306/v4-pit?useSSL=false
     * 目标: v4_dev_8888
     * 输出: jdbc:mysql://lo:3306/v4_dev_8888?useSSL=false
     */
    public static String replaceSchema(String jdbcUrl, String schemaName) {
        // schemaName 允许为空字符串：表示移除库名，用于构造 bootstrap url (jdbc:mysql://host:port/?params)
        if (StrUtil.isBlank(jdbcUrl) || schemaName == null) {
            return jdbcUrl;
        }
        Matcher matcher = MYSQL_URL_PATTERN.matcher(jdbcUrl);
        if (matcher.find()) {
            return matcher.group(1) + schemaName + matcher.group(3);
        }
        // 如果没匹配上（比如 PostgreSQL 格式不同），根据实际情况兜底，这里简单追加
        return jdbcUrl.endsWith("/") ? jdbcUrl + schemaName : jdbcUrl + "/" + schemaName;
    }
}