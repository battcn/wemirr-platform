package com.wemirr.platform.workflow.mq.listener;

import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.platform.workflow.domain.constant.VariableConstant;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalAction;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalStatus;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.warm.flow.core.dto.FlowParams;
import org.dromara.warm.flow.core.listener.Listener;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.dromara.warm.flow.core.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 监听的create 事件
 * 流程任务自动审批监听器
 * 通常监听的是创建任务的事件
 *
 * @author battcn
 * @since 2025/7/08
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class TaskAutoApprovalListener implements Listener {

    private static final String TASK_AUTO_APPROVAL_MESSAGE = "自动审批";
    private final AuthenticationContext context;
    private final TaskService taskService;

    @Override
    public void notify(ListenerVariable variable) {
        log.info("流程任务 => 自动审批监听开始");
        notifyHandler(variable);
        log.info("流程任务 => 自动审批监听器处理结束");
    }

    private void notifyHandler(ListenerVariable variable) {
        var instance = variable.getInstance();
        if (ApprovalStatus.REJECTED.getValue().equals(instance.getFlowStatus())) {
            log.warn("审核拒绝,停止自动审批");
            return;
        }
        if (variable.getTask() == null) {
            log.warn("变量范围为空,停止自动审批");
            return;
        }
        Map<String, Object> params = new HashMap<>(2);
        params.put(VariableConstant.VAR_APPROVE_USER, context.nickName());
        FlowParams flowParams = FlowParams.build()
                .skipType(ApprovalType.PASS.getValue())
                .message(TASK_AUTO_APPROVAL_MESSAGE)
                .hisTaskExt(ApprovalAction.PASS.getValue())
                .variable(params);
        taskService.skip(variable.getTask().getId(), flowParams);
    }


}
