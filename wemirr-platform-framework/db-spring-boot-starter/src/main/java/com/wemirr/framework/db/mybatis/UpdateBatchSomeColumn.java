package com.wemirr.framework.db.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.function.Predicate;

/**
 * 批量更新方法实现，条件为主键，选择性更新
 *
 * @author Levin
 */
@Slf4j
public class UpdateBatchSomeColumn extends AbstractMethod {

    private static final String UPDATE_BATCH_SOME_COLUMN_BY_ID = "updateBatchSomeColumnById";

    @Setter
    @Accessors(chain = true)
    private Predicate<TableFieldInfo> predicate;

    public UpdateBatchSomeColumn(Predicate<TableFieldInfo> predicate) {
        super(UPDATE_BATCH_SOME_COLUMN_BY_ID);
        this.predicate = predicate;
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "<script>\n<foreach collection=\"list\" item=\"item\" separator=\";\">\nupdate %s %s where %s=#{%s} %s\n</foreach>\n</script>";
        String additional = tableInfo.isWithVersion() ? tableInfo.getVersionFieldInfo().getVersionOli("item", "item.") : tableInfo.getLogicDeleteSql(true, true);
        String setSql = sqlSet(tableInfo.isWithLogicDelete(), false, tableInfo, false, "item", "item.");
        String sqlResult = String.format(sql, tableInfo.getTableName(), setSql, tableInfo.getKeyColumn(), "item." + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult, modelClass);
        // 第三个参数必须和Mapper的自定义方法名一致
        return addUpdateMappedStatement(mapperClass, modelClass, methodName, sqlSource);

    }
}