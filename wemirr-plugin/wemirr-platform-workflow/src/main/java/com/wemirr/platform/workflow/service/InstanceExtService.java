

package com.wemirr.platform.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.workflow.domain.dto.req.InstancePageReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowTaskApproveListResp;
import com.wemirr.platform.workflow.domain.dto.resp.InstanceExtDetailResp;
import com.wemirr.platform.workflow.domain.dto.resp.InstancePageResp;
import com.wemirr.platform.workflow.domain.entity.InstanceExt;

import java.util.List;


/**
 * 流程实例扩展信息
 *
 * @author battcn
 * @since 2025/5/28
 **/
public interface InstanceExtService extends SuperService<InstanceExt> {
    /**
     * 查询当前登陆人发起的流程实例
     *
     * @param req req
     * @return 查询结果
     */
    IPage<InstancePageResp> mePageList(InstancePageReq req);


    /**
     * todo 需要将审批人的id转为name  需要考虑用户跟角色
     * 根据流程实例查询任务信息(包含历史的 跟 进行中的)
     *
     * @param id id
     * @return 查询结果
     */
    List<FlowTaskApproveListResp> allTask(Long id);

    /**
     * 流程实例列表
     * @param req req
     * @return 查询结果
     */
    IPage<InstancePageResp> pageList(InstancePageReq req);

    /**
     * 流程详情
     * @param id id
     * @return 查询结果
     */
    InstanceExtDetailResp extInfo(Long id);

}
