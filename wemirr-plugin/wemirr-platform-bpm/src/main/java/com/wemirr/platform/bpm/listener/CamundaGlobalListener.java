package com.wemirr.platform.bpm.listener;

import com.wemirr.platform.bpm.listener.base.*;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.pvm.process.TransitionImpl;
import org.camunda.bpm.engine.impl.task.TaskDefinition;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 基础监听器，用于实现camunda扩展表的业务持久化
 *
 * @author Levin
 */
@Component
public class CamundaGlobalListener extends AbstractBpmnParseListener {

    public final ExecutionListener START_PROCESS_INSTANCE_LISTENER = new ProcessStartBaseListener();
    public final ExecutionListener END_PROCESS_INSTANCE_LISTENER = new ProcessEndBaseListener();
    public final static TaskListener CREATE_TASK_LISTENER = new TaskCreateListener();
    public final static TaskListener COMPLETE_TASK_LISTENER = new TaskCompleteListener();
    public final static TaskListener DELETED_TASK_LISTENER = new TaskDeletedListener();

    protected void addEndEventListener(ScopeImpl activity) {
        activity.addListener(ExecutionListener.EVENTNAME_END, new CamundaGlobalListenerDelegate("addEndEventListener", ExecutionListener.EVENTNAME_END));
        activity.addListener(ExecutionListener.EVENTNAME_START, new CamundaGlobalListenerDelegate("addEndEventListener", ExecutionListener.EVENTNAME_START));
        activity.addListener(ExecutionListener.EVENTNAME_TAKE, new CamundaGlobalListenerDelegate("addEndEventListener", ExecutionListener.EVENTNAME_TAKE));


    }

    protected void addStartEventListener(ScopeImpl activity) {
        activity.addListener(ExecutionListener.EVENTNAME_START, new CamundaGlobalListenerDelegate("addStartEventListener", ExecutionListener.EVENTNAME_START));
        activity.addListener(ExecutionListener.EVENTNAME_END, new CamundaGlobalListenerDelegate("addStartEventListener", ExecutionListener.EVENTNAME_END));
        activity.addListener(ExecutionListener.EVENTNAME_TAKE, new CamundaGlobalListenerDelegate("addStartEventListener", ExecutionListener.EVENTNAME_TAKE));
    }

    protected void addTakeEventListener(TransitionImpl transition) {
        transition.addListener(ExecutionListener.EVENTNAME_START, new CamundaGlobalListenerDelegate("addTakeEventListener", ExecutionListener.EVENTNAME_START));
        transition.addListener(ExecutionListener.EVENTNAME_END, new CamundaGlobalListenerDelegate("addTakeEventListener", ExecutionListener.EVENTNAME_END));
        transition.addListener(ExecutionListener.EVENTNAME_TAKE, new CamundaGlobalListenerDelegate("addTakeEventListener", ExecutionListener.EVENTNAME_TAKE));

    }

    protected void addTaskAssignmentListeners(TaskDefinition taskDefinition) {
        taskDefinition.addTaskListener(TaskListener.EVENTNAME_COMPLETE, new CamundaGlobalListenerDelegate("addTaskAssignmentListeners", TaskListener.EVENTNAME_COMPLETE));
        taskDefinition.addTaskListener(TaskListener.EVENTNAME_DELETE, new CamundaGlobalListenerDelegate("addTaskAssignmentListeners", TaskListener.EVENTNAME_DELETE));
    }

    /**
     * 添加 任务创建监听器 EventType：create
     *
     * @param taskDefinition 任务定义
     */
    protected void addTaskCreateListeners(TaskDefinition taskDefinition) {
        taskDefinition.addTaskListener(TaskListener.EVENTNAME_CREATE, CREATE_TASK_LISTENER);
    }

    /**
     * 添加 任务完成通用监听器 EventType： complete
     *
     * @param taskDefinition 任务定义
     */
    protected void addTaskCompleteListeners(TaskDefinition taskDefinition) {
        taskDefinition.addTaskListener(TaskListener.EVENTNAME_COMPLETE, COMPLETE_TASK_LISTENER);
    }

    protected void addTaskUpdateListeners(TaskDefinition taskDefinition) {
//        taskDefinition.addTaskListener(TaskListener.EVENTNAME_UPDATE, TASK_LISTENER);
    }

    protected void addTaskDeleteListeners(TaskDefinition taskDefinition) {
        taskDefinition.addTaskListener(TaskListener.EVENTNAME_DELETE, DELETED_TASK_LISTENER);
    }

    @Override
    public void parseProcess(Element processElement, ProcessDefinitionEntity processDefinition) {
        processDefinition.addListener(ExecutionListener.EVENTNAME_END, new CamundaGlobalListenerDelegate("parseProcess", ExecutionListener.EVENTNAME_END));
        processDefinition.addListener(ExecutionListener.EVENTNAME_END, END_PROCESS_INSTANCE_LISTENER);
        processDefinition.addListener(ExecutionListener.EVENTNAME_TAKE, new CamundaGlobalListenerDelegate("parseProcess", ExecutionListener.EVENTNAME_TAKE));
        processDefinition.addListener(ExecutionListener.EVENTNAME_START, new CamundaGlobalListenerDelegate("parseProcess", ExecutionListener.EVENTNAME_START));


    }


    /**
     * 流程实例启动事件
     *
     * @param startEventElement
     * @param scope
     * @param startEventActivity
     */
    @Override
    public void parseStartEvent(Element startEventElement, ScopeImpl scope, ActivityImpl startEventActivity) {
        //开始事件监听
        startEventActivity.addListener(ExecutionListener.EVENTNAME_START, START_PROCESS_INSTANCE_LISTENER);
    }

    @Override
    public void parseExclusiveGateway(Element exclusiveGwElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseInclusiveGateway(Element inclusiveGwElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseParallelGateway(Element parallelGwElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseScriptTask(Element scriptTaskElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseServiceTask(Element serviceTaskElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseBusinessRuleTask(Element businessRuleTaskElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseTask(Element taskElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
        activity.addListener(TaskListener.EVENTNAME_COMPLETE, new CamundaGlobalListenerDelegate("parseTask", TaskListener.EVENTNAME_COMPLETE));
        activity.addListener(TaskListener.EVENTNAME_DELETE, new CamundaGlobalListenerDelegate("parseTask", TaskListener.EVENTNAME_DELETE));
        activity.addListener(TaskListener.EVENTNAME_CREATE, new CamundaGlobalListenerDelegate("parseTask", TaskListener.EVENTNAME_CREATE));
    }

    @Override
    public void parseManualTask(Element manualTaskElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
        addStartEventListener(activity);
        addEndEventListener(activity);
        UserTaskActivityBehavior activityBehavior = (UserTaskActivityBehavior) activity.getActivityBehavior();
        TaskDefinition taskDefinition = activityBehavior.getTaskDefinition();
        addTaskCreateListeners(taskDefinition);
        addTaskAssignmentListeners(taskDefinition);
        addTaskCompleteListeners(taskDefinition);
        addTaskUpdateListeners(taskDefinition);
        addTaskDeleteListeners(taskDefinition);
    }

    @Override
    public void parseEndEvent(Element endEventElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseBoundaryTimerEventDefinition(Element timerEventDefinition, boolean interrupting,
                                                  ActivityImpl timerActivity) {
        // start and end event listener are set by parseBoundaryEvent()
    }

    @Override
    public void parseBoundaryErrorEventDefinition(Element errorEventDefinition, boolean interrupting,
                                                  ActivityImpl activity, ActivityImpl nestedErrorEventActivity) {
        // start and end event listener are set by parseBoundaryEvent()
    }

    @Override
    public void parseSubProcess(Element subProcessElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseCallActivity(Element callActivityElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseSequenceFlow(Element sequenceFlowElement, ScopeImpl scopeElement, TransitionImpl transition) {
//        addTakeEventListener(transition);
    }

    @Override
    public void parseSendTask(Element sendTaskElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseMultiInstanceLoopCharacteristics(Element activityElement,
                                                      Element multiInstanceLoopCharacteristicsElement, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseRootElement(Element rootElement, List<ProcessDefinitionEntity> processDefinitions) {
    }

    @Override
    public void parseIntermediateTimerEventDefinition(Element timerEventDefinition, ActivityImpl timerActivity) {
        // start and end event listener are set by parseIntermediateCatchEvent()
    }

    @Override
    public void parseReceiveTask(Element receiveTaskElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseIntermediateSignalCatchEventDefinition(Element signalEventDefinition,
                                                            ActivityImpl signalActivity) {
        // start and end event listener are set by parseIntermediateCatchEvent()
    }

    @Override
    public void parseBoundarySignalEventDefinition(Element signalEventDefinition, boolean interrupting,
                                                   ActivityImpl signalActivity) {
        // start and end event listener are set by parseBoundaryEvent()
    }

    @Override
    public void parseEventBasedGateway(Element eventBasedGwElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseTransaction(Element transactionElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseCompensateEventDefinition(Element compensateEventDefinition, ActivityImpl compensationActivity) {

    }

    @Override
    public void parseIntermediateThrowEvent(Element intermediateEventElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseIntermediateCatchEvent(Element intermediateEventElement, ScopeImpl scope, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseBoundaryEvent(Element boundaryEventElement, ScopeImpl scopeElement, ActivityImpl activity) {
//        addStartEventListener(activity);
    }

    @Override
    public void parseIntermediateMessageCatchEventDefinition(Element messageEventDefinition,
                                                             ActivityImpl nestedActivity) {
    }

    @Override
    public void parseBoundaryMessageEventDefinition(Element element, boolean interrupting,
                                                    ActivityImpl messageActivity) {
    }

    @Override
    public void parseBoundaryEscalationEventDefinition(Element escalationEventDefinition, boolean interrupting,
                                                       ActivityImpl boundaryEventActivity) {
    }

    @Override
    public void parseBoundaryConditionalEventDefinition(Element element, boolean interrupting,
                                                        ActivityImpl conditionalActivity) {
    }

    @Override
    public void parseIntermediateConditionalEventDefinition(Element conditionalEventDefinition,
                                                            ActivityImpl conditionalActivity) {
    }

    @Override
    public void parseConditionalStartEventForEventSubprocess(Element element, ActivityImpl conditionalActivity,
                                                             boolean interrupting) {
    }
}