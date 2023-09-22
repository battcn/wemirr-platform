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
 * Integer[] 的类型转换器实现类，对应数据库的 varchar 类型
 *
 * @author Levin
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(value = {Integer[].class})
public class IntegerArrayTypeHandler extends BaseTypeHandler<Integer[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Integer[] parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, ArrayUtil.join(parameter, StrUtil.COMMA));
    }

    @Override
    @SneakyThrows
    public Integer[] getNullableResult(ResultSet rs, String columnName) {
        String reString = rs.getString(columnName);
        return Convert.toIntArray(reString);
    }

    @Override
    @SneakyThrows
    public Integer[] getNullableResult(ResultSet rs, int columnIndex) {
        String reString = rs.getString(columnIndex);
        return Convert.toIntArray(reString);
    }

    @Override
    @SneakyThrows
    public Integer[] getNullableResult(CallableStatement cs, int columnIndex) {
        String reString = cs.getString(columnIndex);
        return Convert.toIntArray(reString);
    }
}