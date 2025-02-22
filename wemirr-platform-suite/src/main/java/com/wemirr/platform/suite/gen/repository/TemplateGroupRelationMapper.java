package com.wemirr.platform.suite.gen.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.gen.domain.entity.TemplateGroupRelation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @date 2024-12
 */
@Repository
public interface TemplateGroupRelationMapper extends SuperMapper<TemplateGroupRelation> {

    @Delete("delete from c_generate_template_group_relation where group_id = #{id}")
    void deleteByGroupId(Long id);

    @Delete("delete from c_generate_template_group_relation where template_id = #{id}")
    void deleteTemplateId(Long id);
}
