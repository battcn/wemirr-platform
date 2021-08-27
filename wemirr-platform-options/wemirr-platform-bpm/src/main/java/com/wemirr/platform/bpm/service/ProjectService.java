package com.wemirr.platform.bpm.service;

import com.wemirr.platform.bpm.camunda.process.ProjectProcessConstant;
import com.wemirr.platform.bpm.domain.vo.ProjectParticipateVO;
import com.wemirr.platform.bpm.domain.vo.UploadExtraInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final TaskService taskService;
    private final RuntimeService runtimeService;

    private Long insertProcessInstance(Long projectId) {
        // 查询数据库
        String projectRecord = selectProjectById(projectId);
        return 3L;
    }

    private String selectProjectById(Long projectId) {
        return "";
    }

    public ProcessInstance startProcessInstanceByKey(Long projectId, Long userId) {
        // todo 参数验证
        //将记录保存到数据库
        Long savedRecordId = insertProcessInstance(projectId);
        Map<String, Object> variables = new HashMap<>();
        variables.put(ProjectProcessConstant.VAR_NAME_SCHOOL, "上海交通大学");
        variables.put(ProjectProcessConstant.VAR_NAME_STUDENT, String.valueOf(userId));
        variables.put(ProjectProcessConstant.FORM_RECORD_ID, savedRecordId);
        //启动流程的新实例
        return runtimeService.startProcessInstanceByKey(ProjectProcessConstant.PROCESS_ID, variables);
    }

    public List<ProjectParticipateVO> getAllProjectParticipateRequest(Integer reviewLevel, String schoolName) {
        log.info("The school name is {}", schoolName);
        //get the taskList
        List<Task> tasks;
        if (reviewLevel.equals(1)) {
            tasks = taskService.createTaskQuery().taskName(ProjectProcessConstant.TASK_NAME_FIRST_LEVEL_REVIEW).
                    taskCandidateGroup(schoolName).list();
        } else {
            tasks = taskService.createTaskQuery().taskName(ProjectProcessConstant.TASK_NAME_SECOND_LEVEL_REVIEW).
                    taskCandidateGroup(schoolName).list();
        }
        return tasks.stream().map(task -> {
            String taskId = task.getId();
            Map<String, Object> variables = taskService.getVariables(taskId);
            Long studentId = Long.valueOf((String) variables.get(ProjectProcessConstant.VAR_NAME_STUDENT));
            Long recordId = (Long) variables.get(ProjectProcessConstant.FORM_RECORD_ID);
            return ProjectParticipateVO.builder().studentId(studentId).projectParticipateId(recordId).taskId(taskId).build();
        }).collect(Collectors.toList());
    }

    /**
     * 批准项目参与请求
     *
     * @param taskId        任务ID
     * @param needExtraInfo 需要额外信息吗
     * @param passed        是否通过
     * @param schoolName    学校名称
     * @return 批准成功/失败
     */
    public boolean approveProjectParticipateRequest(String taskId, boolean needExtraInfo, boolean passed, String schoolName) {
        Task task = taskService.createTaskQuery().taskCandidateGroup(schoolName).taskId(taskId).singleResult();
        if (task == null) {
            log.error("The task not found, task id is {}", taskId);
            return false;
        } else {
            //业务逻辑在此进入下一步
            log.info("The taskId is {}", taskId);
            Map<String, Object> variables = new HashMap<>();
            variables.put(ProjectProcessConstant.FORM_EXTRA_INFO_1, needExtraInfo);
            variables.put(ProjectProcessConstant.FORM_APPROVED_1, passed);
            taskService.complete(task.getId(), variables);
            return true;
        }
    }

    public List<UploadExtraInfo> getUploadExtraTask(Long userId) {
        List<Task> uploadExtraInfoTask = taskService.createTaskQuery().taskAssignee(String.valueOf(userId)).
                taskName(ProjectProcessConstant.TASK_NAME_UPLOAD_EXTRA_INFO).list();
        return uploadExtraInfoTask.stream().map(task -> {
            // 额外信息的上传url由变量决定
            return UploadExtraInfo.builder().taskId(task.getId()).theUploadUrlOfExtraInfo("www.google.com").build();
        }).collect(Collectors.toList());
    }

    public boolean uploadExtraInfo(Long projectId, Long userId, String extraInfo, String taskId) {
        // 必须验证taskId指向的任务是否属于当前用户。
        Task task = taskService.createTaskQuery().taskAssignee(String.valueOf(userId)).
                taskName(ProjectProcessConstant.TASK_NAME_UPLOAD_EXTRA_INFO).taskId(taskId).singleResult();
        if (task == null) {
            log.error("The task not found.");
            log.error("the assignee is {}, taskName is {}, taskId is {}.", userId, ProjectProcessConstant.TASK_NAME_UPLOAD_EXTRA_INFO, taskId);
            return false;
        } else {
            //上传额外的信息到数据库。
            //这里的业务逻辑
            //进入下一步
            taskService.complete(task.getId());
            return true;
        }
    }
}
