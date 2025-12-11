package com.wemirr.platform.suite.gen.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiao1
 * @since 2024-12
 */
@Repository
public interface GenerateTemplateMapper extends SuperMapper<GenerateTemplate> {

    /**
     * 根据模板组ID查询
     * @param templateGroupId 模板组ID
     * @return 查询结果
     */
    List<GenerateTemplate> getTemplateDetailByGroupId(Long templateGroupId);

}
