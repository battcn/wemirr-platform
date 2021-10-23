package com.wemirr.platform.tools.mapper;

import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface DynamicReleaseColumnMapper extends SuperMapper<DynamicReleaseColumn> {


    /**
     * 根据表名查询字段名称
     *
     * @param tableName 表名
     * @return 查询结果
     */
    List<String> findFieldByTableName(@Param("tableName") String tableName);

}
