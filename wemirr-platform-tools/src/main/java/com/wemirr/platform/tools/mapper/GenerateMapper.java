package com.wemirr.platform.tools.mapper;

import com.wemirr.framework.db.mybatis.SuperMapper;
import com.wemirr.platform.tools.domain.entity.GenerateEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface GenerateMapper extends SuperMapper<GenerateEntity> {

    @Select("show tables")
    List<String> loadTables();
}
