package com.wemirr.platform.suite.gen.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTable;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @date 2024-12
 */
@Repository
public interface GenerateTableMapper extends SuperMapper<GenerateTable> {

    @Select("select count(*) from generate_table where template_group_id = #{id}")
    int countByGroupId(Long id);
}
