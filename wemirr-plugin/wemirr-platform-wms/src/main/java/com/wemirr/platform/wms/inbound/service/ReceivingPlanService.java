package com.wemirr.platform.wms.inbound.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.inbound.domain.entity.ReceivingPlan;
import com.wemirr.platform.wms.inbound.domain.entity.ReceivingPlanItem;
import com.wemirr.platform.wms.inbound.domain.req.ReceivingPlanItemReceivingReq;
import com.wemirr.platform.wms.inbound.domain.req.ReceivingPlanSaveReq;
import com.wemirr.platform.wms.inbound.domain.resp.ReceivingPlanDetailResp;
import com.wemirr.platform.wms.inbound.domain.resp.ReceivingPlanItemPageResp;

import java.util.List;

/**
 * <p>
 * 收货计划 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-24
 */
public interface ReceivingPlanService extends SuperService<ReceivingPlan> {

    /**
     * 创建收货计划
     *
     * @param req 收货计划内容
     */
    void create(ReceivingPlanSaveReq req);

    /**
     * 更新收货计划
     *
     * @param id  收货计划id
     * @param req 收货计划内容
     */
    void modify(Long id, ReceivingPlanSaveReq req);

    /**
     * 删除收货计划
     *
     * @param id 收货计划id
     */
    void removeReceivingPlan(Long id);

    ReceivingPlanDetailResp detail(Long id);

    /**
     * 提交收货计划
     *
     * @param id 计划id
     */
    void submit(Long id);

    /**
     * 关闭收货计划
     *
     * @param id 计划id
     */
    void close(Long id);

    /**
     * 撤回收货计划
     *
     * @param id 计划id
     */
    void cancel(Long id);

    /**
     * 收货
     *
     * @param id    计划id
     * @param items 收货明细
     */
    void receiving(Long id, List<ReceivingPlanItemReceivingReq> items);

    /**
     * 查询收货计划明细行列表
     *
     * @param page   分页参数
     * @param planId 查询条件
     * @return 明细行分页列表
     */
    IPage<ReceivingPlanItemPageResp> itemPage(Page<ReceivingPlanItem> page, Long planId);

    /**
     * 全量收货
     *
     * @param id id
     */
    void allReceiving(Long id);

    /**
     * 绑定月台
     *
     * @param id     收货计划id
     * @param dockId 月台id
     */
    void bindingDock(Long id, Long dockId);

    /**
     * 绑定容器
     *
     * @param id          收货计划id
     * @param containerId 容器id
     */
    void bindingContainer(Long id, Long containerId);
}
