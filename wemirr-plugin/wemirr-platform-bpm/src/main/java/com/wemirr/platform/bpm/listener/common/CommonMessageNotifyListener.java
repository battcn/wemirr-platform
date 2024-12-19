package com.wemirr.platform.bpm.listener.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.wemirr.framework.robot.emums.NotifyType;
import com.wemirr.framework.robot.message.push.RobotMessageHandler;
import com.wemirr.platform.iam.feign.UserFeign;
import com.wemirr.platform.iam.feign.domain.resp.UserInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Component
public class CommonMessageNotifyListener implements ExecutionListener, TaskListener {

    private Expression notifyType;
    private Expression notifyContent;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info("notify listener execution - {} - {} - {}", execution.getId(), notifyType.getExpressionText(), notifyContent.getExpressionText());
        messageNotify();

    }

    @Override
    public void notify(DelegateTask task) {
        Map<String, Object> variables = task.getVariables();
        String assignee = task.getAssignee();
        log.info("notify listener task - {} - {} - {}\n variables - {}", task.getId(), notifyType.getExpressionText(), notifyContent.getExpressionText(), variables);
        if (StrUtil.isBlank(assignee)) {
            return;
        }
        variables.put("taskName", task.getName());
        UserFeign feign = SpringUtil.getBean(UserFeign.class);
        try {
            Map<Object, UserInfoResp> map = feign.findByIds(StrUtil.split(assignee, ",").stream().map(Long::valueOf).collect(Collectors.toSet()));
            if (map == null) {
                return;
            }
            for (Map.Entry<Object, UserInfoResp> entry : map.entrySet()) {
                variables.put("approveName", map.get(entry.getKey()).getNickName());
                messageNotify(variables);
            }
        } catch (Exception ex) {
            log.error("用户获取异常", ex);
        }
    }

    private void messageNotify() {
        messageNotify(null);
    }

    private void messageNotify(Map<String, Object> variables) {
        Map<String, RobotMessageHandler> beans = SpringUtil.getBeansOfType(RobotMessageHandler.class);
        if (beans == null) {
            return;
        }
        for (RobotMessageHandler messageHandler : beans.values()) {
            if (messageHandler.notifyType() != NotifyType.of(notifyType.getExpressionText())) {
                continue;
            }
            messageHandler.notify(notifyContent.getExpressionText(), variables, false);
        }
    }
}
