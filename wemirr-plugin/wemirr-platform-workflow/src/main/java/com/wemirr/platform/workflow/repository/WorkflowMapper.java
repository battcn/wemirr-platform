package com.wemirr.platform.workflow.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.platform.workflow.domain.dto.req.DefinitionPageReq;
import com.wemirr.platform.workflow.domain.dto.req.InstancePageReq;
import com.wemirr.platform.workflow.domain.dto.req.TaskPageReq;
import com.wemirr.platform.workflow.domain.dto.resp.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作warm-flow 表相关的sql语句都放在这里
 *
 * @author battcn
 * @since 2025/5/24
 **/
@Repository
public interface WorkflowMapper {

    /**
     * 分页查询待办列表
     *
     * @param page page
     * @param req req
     * @return 查询结果
     */
    IPage<TodoTaskPageResp> selectTodoTaskPageList(@Param("page") Page<Object> page, @Param("req") TaskPageReq req);

    /**
     * 查询当前登陆人发起的流程实例
     *
     * @param page page
     * @param req req
     * @return 查询结果
     */
    IPage<InstancePageResp> selectInstancePageList(@Param("page") Page<Object> page, @Param("req") InstancePageReq req);

    /**
     * 根据流程实例查询任务信息(包含历史的 跟 进行中的)
     *
     * @param instanceId instanceId
     * @return 查询结果
     */
    List<FlowTaskApproveListResp> selectTaskByInstanceId(@Param("instanceId") Long instanceId);

    /**
     * 分页查询已办任务列表
     *
     * @param page page
     * @param req req
     * @return 查询结果
     */
    IPage<DoneTaskPageResp> selectDoneTaskPageList(@Param("page") Page<Object> page, @Param("req") TaskPageReq req);

    /**
     * 分页查询流程定义相关信息
     * @param page page
     * @param req req
     * @return 查询结果
     */
    IPage<FlowDefinitionPageResp> selectDefinitionPageList(@Param("page") Page<Object> page, @Param("req") DefinitionPageReq req);


}
