package com.wemirr.platform.suite.gen.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplateGroupPageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplateGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiao1
 * @since 2024-12
 */
@Repository
public interface GenerateTemplateGroupMapper extends SuperMapper<GenerateTemplateGroup> {

    /**
     * 分页查询模板组列表（包含模板ID集合）
     *
     * @param page 分页参数
     * @param req  查询条件
     * @return 分页结果
     */
    IPage<GenerateTemplateGroupPageResp> selectPageList(@Param("page") Page<GenerateTemplateGroup> page, @Param("req") GenerateTemplateGroupPageReq req);
}
