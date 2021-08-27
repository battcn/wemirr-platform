package com.wemirr.platform.bpm.controller;

import com.wemirr.platform.bpm.domain.vo.ProjectParticipateVO;
import com.wemirr.platform.bpm.domain.vo.UploadExtraInfo;
import com.wemirr.platform.bpm.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "项目管理", description = "项目管理")
@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @Operation(summary = "项目报名", description = "项目报名")
    @PostMapping(value = "/{projectId}/users{userId}")
    public ProcessInstance ParticipatingProject(@PathVariable Long projectId, @PathVariable Long userId) {
        return projectService.startProcessInstanceByKey(projectId, userId);
    }

    @Operation(summary = "审批申请列表", description = "获取需要审批的项目申请列表")
    @GetMapping(value = "/project/approve/list")
    public List<ProjectParticipateVO> getAllProjectParticipateRequest(Integer reviewLevel, String schoolName) {
        return projectService.getAllProjectParticipateRequest(reviewLevel, schoolName);
    }

    @Operation(summary = "审批项目申请", description = "审批项目申请")
    @PutMapping(value = "/project/participateRequests/{taskId}")
    public boolean approveProjectParticipateRequest(@PathVariable String taskId, boolean needExtraInfo, boolean passed, String schoolName) {
        return projectService.approveProjectParticipateRequest(taskId, needExtraInfo, passed, schoolName);
    }

    @Operation(summary = "材料上传记录", description = "获取学生需要上传额外材料的记录")
    @GetMapping(value = "/users/{userId}/extraInfo/list")
    public List<UploadExtraInfo> getUploadExtraTask(@PathVariable Long userId) {
        return projectService.getUploadExtraTask(userId);
    }

    @Operation(summary = "上传额外资料", description = "上传指定项目所需的额外资料")
    @PostMapping(value = "/{projectId}/users/{userId}/extraInfo")
    public boolean uploadExtraInfo(@PathVariable Long projectId, @PathVariable Long userId, String extraInfo, String taskId) {
        return projectService.uploadExtraInfo(projectId, userId, extraInfo, taskId);
    }
}