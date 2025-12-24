package com.wemirr.platform.workflow.mq.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.JacksonUtils;
import com.wemirr.platform.iam.feign.UserFeign;
import com.wemirr.platform.iam.feign.domain.resp.UserInfoResp;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalAction;
import com.wemirr.platform.workflow.feign.domain.enums.ApprovalStatus;
import com.wemirr.platform.workflow.feign.domain.enums.DefId2Tag;
import com.wemirr.platform.workflow.feign.domain.req.BusinessCommonMqReq;
import com.wemirr.platform.workflow.feign.domain.req.WorkflowCommonReq;
import com.wemirr.platform.workflow.handler.CustomPermissionHandler;
import com.wemirr.platform.workflow.mq.TransactionMqTemplate;
import lombok.RequiredArgsConstructor;
import org.dromara.warm.flow.core.FlowEngine;
import org.dromara.warm.flow.core.dto.DefJson;
import org.dromara.warm.flow.core.dto.NodeJson;
import org.dromara.warm.flow.core.entity.Definition;
import org.dromara.warm.flow.core.entity.Instance;
import org.dromara.warm.flow.core.entity.Task;
import org.dromara.warm.flow.core.listener.GlobalListener;
import org.dromara.warm.flow.core.listener.ListenerVariable;
import org.dromara.warm.flow.core.service.DefService;
import org.dromara.warm.flow.core.service.InsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局监听器: 整个系统只有一个，任务开始、分派、完成和创建时期执行
 *
 * @author battcn
 * @since 2025-09-18
 */
@Component
@RequiredArgsConstructor
public class CustomGlobalListener implements GlobalListener {

    private static final Logger log = LoggerFactory.getLogger(CustomGlobalListener.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String APPROVAL_ACTION = "approvalAction";
    private final CustomPermissionHandler customPermissionHandler;
    private final TransactionMqTemplate transactionMQTemplate;
    private final DefService defService;
    private final UserFeign userFeign;
    private final InsService insService;
    @Value("${workflow.mq.topic:workflow_topic}")
    private String workflowTopic;


    /**
     * 开始监听器，任务开始办理时执行
     *
     * @param listenerVariable 监听器变量
     */
    @Override
    public void start(ListenerVariable listenerVariable) {
        log.info("全局开始监听器开始执行......");

        log.info("全局开始监听器执行结束......");
    }

    /**
     * 分派监听器，动态修改代办任务信息
     *
     * @param listenerVariable 监听器变量
     */
    @Override
    public void assignment(ListenerVariable listenerVariable) {
        log.info("全局分派监听器开始执行......");

        // 处理任务审批人分配
        handleTaskPermission(listenerVariable);

        // 更新流程定义JSON信息
        updateDefinitionJson(listenerVariable);

        log.info("全局分派监听器执行结束......");
    }

    /**
     * 更新下一节点的待办人信息
     */
    private void updateNextNodeAssignees(DefJson defJson, List<Task> nextTasks) {
        if (CollUtil.isEmpty(nextTasks)) {
            return;
        }

        // 收集所有需要查询的用户ID
        Set<Object> userIds = nextTasks.stream()
                .filter(task -> CollUtil.isNotEmpty(task.getPermissionList()))
                .flatMap(task -> task.getPermissionList().stream())
                .collect(Collectors.toSet());

        if (CollUtil.isEmpty(userIds)) {
            return;
        }

        // 批量查询用户信息
        Map<Object, UserInfoResp> userMap = userFeign.findByIds(userIds);

        // 更新各节点的待办人信息
        nextTasks.forEach(task -> {
            defJson.getNodeList().stream()
                    .filter(nodeJson -> nodeJson.getNodeCode().equals(task.getNodeCode()))
                    .findFirst()
                    .ifPresent(nodeJson -> {
                        List<String> permissionList = task.getPermissionList();
                        if (CollUtil.isNotEmpty(permissionList)) {
                            String assigneeNames = getAssigneeNames(permissionList, userMap);
                            nodeJson.getExtMap().put("待办人", assigneeNames);
                        }
                    });
        });
    }

    /**
     * 根据用户ID列表获取用户名称列表
     */
    private String getAssigneeNames(List<String> permissionList, Map<Object, UserInfoResp> userMap) {
        return permissionList.stream()
                .map(userId -> {
                    UserInfoResp userInfo = userMap.get(userId);
                    return userInfo != null ? userInfo.getNickName() : userId;
                })
                .collect(Collectors.joining(","));
    }

    /**
     * 更新流程定义JSON信息
     */
    private void updateDefinitionJson(ListenerVariable listenerVariable) {
        String defJsonStr = listenerVariable.getInstance().getDefJson();
        if (StrUtil.isBlank(defJsonStr)) {
            return;
        }
        try {
            List<Task> nextTasks = listenerVariable.getNextTasks();
            DefJson defJson = FlowEngine.jsonConvert.strToBean(defJsonStr, DefJson.class);
            String currentNodeCode = listenerVariable.getNode().getNodeCode();
            String handlerName = customPermissionHandler.getHandlerName();
            String handleTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).format(DATE_TIME_FORMATTER);
            // 更新当前节点信息
            updateCurrentNodeInfo(defJson, currentNodeCode, handlerName, handleTime);
            updateNextNodeAssignees(defJson, nextTasks);
            // 保存更新后的JSON
            listenerVariable.getInstance().setDefJson(FlowEngine.jsonConvert.objToStr(defJson));
        } catch (Exception e) {
            log.error("更新流程定义JSON信息失败", e);
        }
    }

    /**
     * 更新当前节点信息（办理人和办理时间）
     */
    private void updateCurrentNodeInfo(DefJson defJson, String currentNodeCode,
                                       String handlerName, String handleTime) {
        defJson.getNodeList().stream()
                .filter(nodeJson -> nodeJson.getNodeCode().equals(currentNodeCode))
                .findFirst()
                .ifPresent(nodeJson -> {
                    // 办理人信息采用逗号分隔追加的方式
                    appendToExtMap(nodeJson, "办理人", handlerName);
                    // 办理时间信息采用逗号分隔追加的方式
                    appendToExtMap(nodeJson, "办理时间", handleTime);
                });
    }

    /**
     * 向extMap中追加值，如果已存在则用逗号分隔
     *
     * @param nodeJson 节点JSON对象
     * @param key      键
     * @param value    值
     */
    private void appendToExtMap(NodeJson nodeJson, String key, String value) {
        String currentValue = (String) nodeJson.getExtMap().get(key);
        nodeJson.getExtMap().put(key,
                StrUtil.isNotBlank(currentValue) ? currentValue + "," + value : value);
    }

    /**
     * 处理任务审批人分配
     */
    private void handleTaskPermission(ListenerVariable listenerVariable) {
        List<Task> nextTasks = listenerVariable.getNextTasks();
        if (CollUtil.isEmpty(nextTasks)) {
            return;
        }
        String createBy = listenerVariable.getInstance().getCreateBy();
        nextTasks.stream()
                .filter(task -> CollUtil.isEmpty(task.getPermissionList()))
                .forEach(task -> task.setPermissionList(Lists.newArrayList(createBy)));
    }

    /**
     * 完成监听器，当前任务完成后执行
     *
     * @param listenerVariable 监听器变量
     */
    @Override
    public void finish(ListenerVariable listenerVariable) {
        log.info("全局完成监听器开始执行......");
        Instance instance = listenerVariable.getInstance();
        if (null != instance) {
            Map<String, Object> variable = listenerVariable.getVariable();
            BusinessCommonMqReq businessCommonMqDto = BusinessCommonMqReq.builder()
                    .businessCode(instance.getBusinessId())
                    .workflowCommonReq(StrUtil.isBlank(instance.getExt()) ? null : JacksonUtils.toBean(instance.getExt(), WorkflowCommonReq.class))
                    .instanceStatus(ApprovalStatus.of(instance.getFlowStatus()))
                    .createBy(customPermissionHandler.getHandler())
                    .createName(customPermissionHandler.getHandlerName())
                    .tenantId(customPermissionHandler.getTenantId())
                    .instanceId(instance.getId()).build();
            if (variable.containsKey(APPROVAL_ACTION)) {
                businessCommonMqDto.setApprovalAction(ApprovalAction.getApprovalActionByCode(variable.get("approvalAction").toString()));
            }
            Long definitionId = instance.getDefinitionId();
            Definition definition = defService.getById(definitionId);
            if (null == definition || null == DefId2Tag.ofTag(definition.getFlowCode())) {
                return;
            }
            transactionMQTemplate.sendOrderlyMessage(workflowTopic, DefId2Tag.ofTag(definition.getFlowCode()), businessCommonMqDto.getBusinessCode(), JacksonUtils.toJson(businessCommonMqDto), Boolean.TRUE);
        }
        log.info("全局完成监听器执行结束......");
    }

    /**
     * 创建监听器，任务创建时执行
     *
     * @param listenerVariable 监听器变量
     */
    @Override
    public void create(ListenerVariable listenerVariable) {
        log.info("全局创建监听器开始执行......");

        log.info("全局创建监听器执行结束......");
    }

}
