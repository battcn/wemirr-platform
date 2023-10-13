package com.wemirr.platform.tools.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.tools.domain.entity.GenerateEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface GenerateMapper extends SuperMapper<GenerateEntity> {

    /**
     * 显示所有表
     *
     * @return 表名
     */
    @Select("show tables")
    List<String> loadTables();
}
