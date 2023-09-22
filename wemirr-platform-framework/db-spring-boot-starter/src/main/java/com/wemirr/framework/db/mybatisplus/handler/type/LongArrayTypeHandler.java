package com.wemirr.framework.db.mybatisplus.handler.type;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数组字符串互转
 *
 * @author Levin
 */
@MappedTypes(value = {Long[].class})
@MappedJdbcTypes(value = JdbcType.VARCHAR)
public class LongArrayTypeHandler extends BaseTypeHandler<Long[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long[] parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, ArrayUtil.join(parameter, StrUtil.COMMA));
    }

    @Override
    @SneakyThrows
    public Long[] getNullableResult(ResultSet rs, String columnName) {
        String reString = rs.getString(columnName);
        return Convert.toLongArray(reString);
    }

    @Override
    @SneakyThrows
    public Long[] getNullableResult(ResultSet rs, int columnIndex) {
        String reString = rs.getString(columnIndex);
        return Convert.toLongArray(reString);
    }

    @Override
    @SneakyThrows
    public Long[] getNullableResult(CallableStatement cs, int columnIndex) {
        String reString = cs.getString(columnIndex);
        return Convert.toLongArray(reString);
    }

}
