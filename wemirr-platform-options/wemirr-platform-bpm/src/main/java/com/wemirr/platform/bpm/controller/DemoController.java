package com.wemirr.platform.bpm.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.wemirr.platform.bpm.domain.vo.ProjectParticipateVO;
import com.wemirr.platform.bpm.domain.vo.UploadExtraInfo;
import com.wemirr.platform.bpm.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RequestMapping("/demo")
@RestController
@RequiredArgsConstructor
@Tag(name = "项目管理(集成)", description = "项目管理-完整流程")
public class DemoController {

    private final ProjectService projectService;

    /**
     * 完成一个任务
     */
    @GetMapping("/run")
    public void run() {
        Long projectId = 1L, userId = 1L;
        projectService.startProcessInstanceByKey(projectId, userId);
        final List<ProjectParticipateVO> projectParticipates = projectService.getAllProjectParticipateRequest(1, "上海交通大学");
        if (CollectionUtil.isEmpty(projectParticipates)) {
            return;
        }
        for (ProjectParticipateVO participate : projectParticipates) {
            final String taskId = participate.getTaskId();
            projectService.approveProjectParticipateRequest(taskId, false, true, "上海交通大学");
            projectService.uploadExtraInfo(projectId, userId, "www.google.com", taskId);
            final List<UploadExtraInfo> infoList = projectService.getUploadExtraTask(userId);
            for (UploadExtraInfo extraInfo : infoList) {
                projectService.approveProjectParticipateRequest(extraInfo.getTaskId(), false, true, "上海交通大学");
            }
        }
        final List<ProjectParticipateVO> projectParticipates2 = projectService.getAllProjectParticipateRequest(2, "教务处");
        if (CollectionUtil.isEmpty(projectParticipates2)) {
            return;
        }
        for (ProjectParticipateVO participate : projectParticipates2) {
            final String taskId = participate.getTaskId();
            projectService.approveProjectParticipateRequest(taskId, false, true, "教务处");
            projectService.uploadExtraInfo(projectId, userId, "www.google.com", taskId);
            final List<UploadExtraInfo> infoList = projectService.getUploadExtraTask(userId);
            for (UploadExtraInfo extraInfo : infoList) {
                projectService.approveProjectParticipateRequest(extraInfo.getTaskId(), false, true, "教务处");
            }
        }
    }
}
