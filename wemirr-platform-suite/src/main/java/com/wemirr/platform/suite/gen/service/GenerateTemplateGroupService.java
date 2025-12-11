package com.wemirr.platform.suite.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupPageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTemplateGroupSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTemplateGroupPageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplateGroup;

/**
 * 模板组管理服务接口
 * 提供对生成模板组的增删改查及分页查询功能。
 *
 * @author xiao1
 * @since 2024-12
 */
public interface GenerateTemplateGroupService extends SuperService<GenerateTemplateGroup> {

    /**
     * 分页查询模板组列表
     *
     * @param req 查询条件封装对象 {@link GenerateTemplateGroupPageReq}
     * @return 返回分页结果 {@link IPage< GenerateTemplateGroupPageResp >}
     */
    IPage<GenerateTemplateGroupPageResp> pageList(GenerateTemplateGroupPageReq req);

    /**
     * 创建新的模板组
     *
     * @param req 模板组保存请求数据传输对象 {@link GenerateTemplateGroupSaveReq}
     */
    void create(GenerateTemplateGroupSaveReq req);

    /**
     * 修改指定ID的模板组信息
     *
     * @param id 要修改的模板组ID
     * @param req 模板组更新请求数据传输对象 {@link GenerateTemplateGroupSaveReq}
     */
    void modify(Long id, GenerateTemplateGroupSaveReq req);

    /**
     * 删除指定ID的模板组
     *
     * @param id 需要删除的模板组ID
     */
    void removeGroup(Long id);
}