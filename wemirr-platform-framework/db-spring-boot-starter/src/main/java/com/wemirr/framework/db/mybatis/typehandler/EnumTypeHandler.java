package com.wemirr.framework.db.mybatis.typehandler;

import com.wemirr.framework.db.mybatis.DictionaryEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param <E>
 * @author Levin
 */
public class EnumTypeHandler<E extends Enum<?> & DictionaryEnum<Integer>> extends BaseTypeHandler<DictionaryEnum<Integer>> {

    private final Class<E> clazz;

    public EnumTypeHandler(Class<E> enumType) {
        if (enumType == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DictionaryEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, (Integer) parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return DictionaryEnum.of(clazz, rs.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return DictionaryEnum.of(clazz, rs.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return DictionaryEnum.of(clazz, cs.getInt(columnIndex));
    }


}