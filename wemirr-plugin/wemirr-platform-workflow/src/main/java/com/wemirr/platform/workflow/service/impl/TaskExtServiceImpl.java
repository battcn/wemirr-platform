package com.wemirr.platform.workflow.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.platform.workflow.domain.constant.VariableConstant;
import com.wemirr.platform.workflow.domain.dto.req.TaskPageReq;
import com.wemirr.platform.workflow.domain.dto.req.WorkflowTaskReq;
import com.wemirr.platform.workflow.domain.dto.resp.DoneTaskPageResp;
import com.wemirr.platform.workflow.domain.dto.resp.ProcessInstanceFormPreviewResp;
import com.wemirr.platform.workflow.domain.dto.resp.TodoTaskPageResp;
import com.wemirr.platform.workflow.domain.entity.InstanceExt;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalAction;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalType;
import com.wemirr.platform.workflow.handler.CustomPermissionHandler;
import com.wemirr.platform.workflow.repository.InstanceExtMapper;
import com.wemirr.platform.workflow.repository.WorkflowMapper;
import com.wemirr.platform.workflow.service.TaskExtService;
import lombok.RequiredArgsConstructor;
import org.dromara.warm.flow.core.dto.FlowParams;
import org.dromara.warm.flow.core.entity.Task;
import org.dromara.warm.flow.core.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 工作流任务相关
 *
 * @author battcn
 * @since 2025/5/21
 **/
@Service
@RequiredArgsConstructor
public class TaskExtServiceImpl implements TaskExtService {

    private final TaskService taskService;
    private final WorkflowMapper workflowMapper;
    private final CustomPermissionHandler customPermissionHandler;
    private final AuthenticationContext context;
    private final InstanceExtMapper instanceExtMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pass(Long id, WorkflowTaskReq req) {
        Map<String, Object> variable = ObjUtil.defaultIfNull(req.getVariable(), Maps.newHashMap());
        variable.put(VariableConstant.VAR_APPROVE_USER, context.nickName());
        FlowParams flowParams = FlowParams.build()
                .skipType(ApprovalType.PASS.getValue())
                .message(req.getMessage())
                .ignoreCooperate(customPermissionHandler.isIgnoreAuth())
                .ignore(customPermissionHandler.isIgnoreAuth())
                .hisTaskExt(ApprovalAction.PASS.getValue())
                .variable(variable);
        taskService.skip(id, flowParams);
        // 抄送往
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reject(Long id, WorkflowTaskReq req) {
        Map<String, Object> variable = ObjUtil.defaultIfNull(req.getVariable(), Maps.newHashMap());
        variable.put(VariableConstant.VAR_APPROVE_USER, context.nickName());
        FlowParams flowParams = FlowParams.build()
//                .skipType(ApprovalType.REJECT.getValue())
                .message(req.getMessage())
//                .hisTaskExt(ApprovalAction.REJECT.getValue())
                .variable(variable);
        taskService.skip(id, flowParams);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void taskReturn(Long id, WorkflowTaskReq req) {
        FlowParams flowParams = FlowParams.build();
        // 作为审批意见保存到历史记录表
        flowParams.message(req.getMessage());
//        flowParams.hisTaskExt(ApprovalAction.REJECT.getValue());
        taskService.rejectLast(id, flowParams);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void termination(Long id, WorkflowTaskReq req) {
        Map<String, Object> variable = ObjUtil.defaultIfNull(req.getVariable(), Maps.newHashMap());
        variable.put(VariableConstant.VAR_APPROVE_USER, context.nickName());
        taskService.termination(id, FlowParams.build().message(req.getMessage())
//                .hisTaskExt(ApprovalAction.TERMINATION.getValue())
                .variable(variable));
    }

    @Override
    public ProcessInstanceFormPreviewResp formPreview(String id) {
        final InstanceExt ext = Optional.ofNullable(instanceExtMapper.selectOne(InstanceExt::getInstanceId, id))
                .orElseThrow(() -> CheckedException.notFound("流程不存在"));
        //通过实例ID获取表单数据
        return ProcessInstanceFormPreviewResp.builder().formDesign(ProcessInstanceFormPreviewResp.FormDesign.builder()
                        .schemas(JacksonUtils.readValue(ext.getFormSchemas(), new TypeReference<>() {
                        })).script(ext.getFormScript()).build())
                .formData(JacksonUtils.readValue(ext.getFormData(), new TypeReference<>() {})).build();
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void terminationByInstanceId(Long instanceId, WorkflowInstanceTerminationReq req) {
//        Instance instance = taskService.terminationByInsId(instanceId, FlowParams
//                .build()
//                .ignore(req.getIgnore())
//                .hisTaskExt(req.getTaskExt())
//                .variable(Map.of("approvalAction", req.getTaskExt()))
//                .message(req.getMessage()));
//    }


    @Override
    public List<Task> getTaskByInstantId(Long instanceId) {
        return taskService.getByInsId(instanceId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(Long taskId, WorkflowTaskReq req) {
        List<String> list = handlerChangeObject(req);
        taskService.transfer(taskId, FlowParams.build().message(req.getMessage()).variable(req.getVariable()).addHandlers(list));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSignature(Long taskId, WorkflowTaskReq req) {
        taskService.addSignature(taskId, FlowParams.build().addHandlers(handlerChangeObject(req)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSignature(Long taskId, WorkflowTaskReq req) {
        taskService.reductionSignature(taskId, FlowParams.build().reductionHandlers(handlerChangeObject(req)));
    }

    @Override
    public IPage<TodoTaskPageResp> meTodoPageList(TaskPageReq req) {
        req.setPermissionList(customPermissionHandler.permissions());
        return workflowMapper.selectTodoTaskPageList(req.buildPage(), req);
    }

    @Override
    public IPage<DoneTaskPageResp> meDonePageList(TaskPageReq req) {
        req.setPermissionList(customPermissionHandler.permissions());
        return workflowMapper.selectDoneTaskPageList(req.buildPage(), req);
    }

    @Override
    public IPage<TodoTaskPageResp> todoPageList(TaskPageReq taskPageReq) {
        return workflowMapper.selectTodoTaskPageList(taskPageReq.buildPage(), taskPageReq);
    }


    /**
     * 加签 减签 转办都需要根据 HandlerObject 里面解析出来对象, 所以抽取公共方法
     *
     * @param req req
     */
    private List<String> handlerChangeObject(WorkflowTaskReq req) {
        if (StrUtil.isBlank(req.getHandlerObject())) {
            throw CheckedException.badRequest("需要修改的对象不能为空！");
        }
        return StrUtil.split(req.getHandlerObject(), ",");
    }

}
