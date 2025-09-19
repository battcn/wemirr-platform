package com.wemirr.platform.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.workflow.domain.dto.req.TaskPageReq;
import com.wemirr.platform.workflow.domain.dto.req.WorkflowTaskReq;
import com.wemirr.platform.workflow.domain.dto.resp.DoneTaskPageResp;
import com.wemirr.platform.workflow.domain.dto.resp.ProcessInstanceFormPreviewResp;
import com.wemirr.platform.workflow.domain.dto.resp.TodoTaskPageResp;
import org.dromara.warm.flow.core.entity.Task;

import java.util.List;

/**
 * 处理work flow task
 *
 * @author battcn
 * @since 2025/5/21
 **/
public interface TaskExtService {

    /**
     * 根据流程实例id 查询任务列表
     *
     * @param instanceId instanceId
     * @return 任务清单
     */
    List<Task> getTaskByInstantId(Long instanceId);

    /**
     * 转办任务  默认删除当前办理用户权限，转办后，当前办理不可办理
     *
     * @param id  id
     * @param req req
     */
    void transfer(Long id, WorkflowTaskReq req);

    /**
     * 加签
     *
     * @param id id
     * @param req req
     */
    void addSignature(Long id, WorkflowTaskReq req);

    /**
     * 减签
     *
     * @param id id
     * @param req req
     */
    void removeSignature(Long id, WorkflowTaskReq req);

    /**
     * 查询当前登陆人需要处理的任务列表
     * @param req req
     * @return 查询结果
     */
    IPage<TodoTaskPageResp> meTodoPageList(TaskPageReq req);


    /**
     * 已完成任务列表
     * @param req req
     * @return 查询结果
     */
    IPage<DoneTaskPageResp> meDonePageList(TaskPageReq req);

    /**
     * 待办任务列表
     *
     * @param req req
     * @return 待办任务列表
     */
    IPage<TodoTaskPageResp> todoPageList(TaskPageReq req);

    /**
     * 审批通过
     * @param id id
     * @param req req
     */
    void pass(Long id, WorkflowTaskReq req);

    /**
     * 审批拒绝
     * @param id id
     * @param req req
     */
    void reject(Long id, WorkflowTaskReq req);

    /**
     * 任务退回
     * @param id id
     * @param req req
     */
    void taskReturn(Long id, WorkflowTaskReq req);


    /**
     * 任务终止
     * @param id id
     * @param req req
     */
    void termination(Long id, WorkflowTaskReq req);

    /**
     * 流程表单预览
     * @param id ID
     * @return 预览结果
     */
    ProcessInstanceFormPreviewResp formPreview(String id);
}
