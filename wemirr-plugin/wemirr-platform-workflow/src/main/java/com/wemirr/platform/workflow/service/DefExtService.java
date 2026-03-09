package com.wemirr.platform.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.workflow.domain.dto.req.DefinitionDeployReq;
import com.wemirr.platform.workflow.domain.dto.req.DefinitionPageReq;
import com.wemirr.platform.workflow.domain.dto.req.FormDesignSaveReq;
import com.wemirr.platform.workflow.domain.dto.req.InstanceStartReq;
import com.wemirr.platform.workflow.domain.dto.resp.DesignModelFormResp;
import com.wemirr.platform.workflow.domain.dto.resp.DesignModelGroupListResp;
import com.wemirr.platform.workflow.domain.dto.resp.FlowDefinitionPageResp;
import com.wemirr.platform.workflow.domain.dto.resp.WorkflowDefinitionResp;
import com.wemirr.platform.workflow.feign.domain.req.WorkflowStartReq;
import com.wemirr.platform.workflow.feign.domain.resp.InstanceStartResp;
import org.dromara.warm.flow.core.dto.DefJson;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 操作warm-flow
 *
 * @author battcn
 * @since 2025/5/21
 **/
public interface DefExtService {
    /**
     * 根据 JSON 文件 部署流程
     *
     * @param req req
     * @return 部署结果
     */
    WorkflowDefinitionResp deploy(DefinitionDeployReq req);

    /**
     * 发布流程
     *
     * @param id id
     */
    void publish(Long id);

    /**
     * 获取流程
     *
     * @param id id
     * @return 流程明细
     */
    DefJson detail(Long id);

    /**
     * 开启一个流程 基于流程定义
     *
     * @param req req
     * @return 查询结果
     */
    InstanceStartResp startFlow(WorkflowStartReq req);


    /**
     * 分页查询流程定义相关信息
     *
     * @param req req
     * @return 查询结果
     */
    IPage<FlowDefinitionPageResp> pageList(DefinitionPageReq req);


    /**
     * 取消发布
     * @param id id
     */
    void unPublish(Long id);


    /**
     * 删除定义
     * @param id id
     */
    void delete(Long id);


    /**
     * 流程复制
     * @param id id
     */
    void copyDef(Long id);

    /**
     * 激活流程
     * @param id id
     */
    void active(Long id);

    /**
     * 挂起流程
     * @param id id
     */
    void unActive(Long id);

    /**
     * 导出JSON
     * @param id id
     * @return 查询结果
     */
    Map<String, Object> exportJson(Long id);

    /**
     * 导入流程定义
     * @param inputStream 文件流
     */
    void importDef(InputStream inputStream);

    /**
     * 保存表单设计
     * @param id   id
     * @param req req
     */
    void addFormDesign(Long id, FormDesignSaveReq req);


    /**
     * 查询表单设计信息
     * @param id id
     * @return 查询结果
     */
    DesignModelFormResp findFormDesign(Long id);


    /**
     * 流程定义分组列表
     * @return 查询结果
     */
    List<DesignModelGroupListResp> groupList();

    InstanceStartResp startInstance(Long id, InstanceStartReq req);


}
