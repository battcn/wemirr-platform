package com.battcn.framework.mybatis.pojo.enums.handler;

import com.battcn.framework.mybatis.pojo.enums.Gender;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Levin
 * @version 1.0
 */
public class GenderTypeHandler implements TypeHandler<Gender> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Gender parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.value());
    }

    @Override
    public Gender getResult(ResultSet rs, String columnName) throws SQLException {
        return Gender.of(Integer.valueOf(rs.getString(columnName)));
    }

    @Override
    public Gender getResult(ResultSet rs, int columnIndex) throws SQLException {
        return Gender.of(rs.getInt(columnIndex));
    }

    @Override
    public Gender getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Gender.of(cs.getInt(columnIndex));
    }

}
