package com.wemirr.platform.workflow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.workflow.domain.constant.VariableConstant;
import com.wemirr.platform.workflow.domain.dto.req.InstancePageReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowTaskApproveListResp;
import com.wemirr.platform.workflow.domain.dto.resp.InstanceExtDetailResp;
import com.wemirr.platform.workflow.domain.dto.resp.InstancePageResp;
import com.wemirr.platform.workflow.domain.dto.resp.ProcessInstanceFormPreviewResp;
import com.wemirr.platform.workflow.domain.entity.InstanceExt;
import com.wemirr.platform.workflow.repository.InstanceExtMapper;
import com.wemirr.platform.workflow.repository.WorkflowMapper;
import com.wemirr.platform.workflow.service.InstanceExtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.*;

/**
 * 流程实例扩展服务
 * <p>
 * 提供流程实例的扩展查询功能，包括实例详情、任务历史等
 *
 * @author Levin
 * @since 2025-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InstanceExtServiceImpl extends SuperServiceImpl<InstanceExtMapper, InstanceExt> implements InstanceExtService {

    private final WorkflowMapper workflowMapper;
    private final AuthenticationContext context;
    private final InstanceExtMapper instanceExtMapper;

    @Override
    public IPage<InstancePageResp> mePageList(InstancePageReq req) {
        req.setUserId(String.valueOf(context.userId()));
        return workflowMapper.selectInstancePageList(req.buildPage(), req);
    }


    @Override
    public List<FlowTaskApproveListResp> allTask(Long instanceId) {
        var list = workflowMapper.selectTaskByInstanceId(instanceId);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        handlerVariable(list);
        return list.stream()
                .sorted(comparing(FlowTaskApproveListResp::getApprovalTime, nullsFirst(reverseOrder())))
                .toList();
    }

    @Override
    public IPage<InstancePageResp> pageList(InstancePageReq req) {
        return workflowMapper.selectInstancePageList(req.buildPage(), req);
    }

    @Override
    public InstanceExtDetailResp extInfo(Long id) {
        var instance = Optional.ofNullable(instanceExtMapper.selectOne(InstanceExt::getInstanceId, id)).orElseThrow(() -> CheckedException.notFound("流程实例不存在"));
        var taskList = allTask(id);
        var detail = BeanUtilPlus.toBean(instance, InstanceExtDetailResp.class);
        //通过实例ID获取表单数据
        var formPreview = ProcessInstanceFormPreviewResp.builder().formDesign(ProcessInstanceFormPreviewResp.FormDesign.builder()
                        .schemas(JacksonUtils.readValue(instance.getFormSchemas(), new TypeReference<>() {})).script(instance.getFormScript()).build())
                .formData(JacksonUtils.readValue(instance.getFormData(), new TypeReference<>() {})).build();
        detail.setTaskList(taskList);
        detail.setFormPreview(formPreview);
        return detail;
    }

    private void handlerVariable(List<FlowTaskApproveListResp> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        for (FlowTaskApproveListResp task : list) {
            if (StrUtil.isBlank(task.getVariable())) {
                continue;
            }
            JsonNode variable = JacksonUtils.toJsonNode(task.getVariable());
            String approverName = variable.get(VariableConstant.VAR_APPROVE_USER).asText();
            if (StrUtil.isEmpty(approverName)) {
                approverName = variable.get("user").asText();
//                    task.setTransferApproverName(jsonObject.getString(VariableConstant.VAR_TRANSFER_APPROVE_USER));
            }
            task.setApproverName(approverName);
        }
    }
}
