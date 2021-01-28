package com.wemirr.platform.tools.mapper;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface DynamicReleaseColumnMapper extends SuperMapper<DynamicReleaseColumn> {


    List<String> findFieldByTableName(@Param("tableName") String tableName);

}
