package com.wemirr.platform.wms.outbound.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.outbound.domain.entity.OutboundPlan;
import com.wemirr.platform.wms.outbound.domain.entity.OutboundPlanItem;
import com.wemirr.platform.wms.outbound.domain.req.OutboundPlanSaveReq;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanDetailResp;
import com.wemirr.platform.wms.outbound.domain.resp.OutboundPlanItemPageResp;

/**
 * <p>
 * 出库单 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-07-20
 */
public interface OutboundPlanService extends SuperService<OutboundPlan> {

    /**
     * 创建或更新出库单
     *
     * @param id  出库单id
     * @param req 出库单内容
     */
    void saveOrUpdateOutboundPlan(Long id, OutboundPlanSaveReq req);

    /**
     * 删除出库单
     *
     * @param id 出库单id
     */
    void removeOutboundPlan(Long id);

    /**
     * 提交出库单
     *
     * @param id 计划id
     */
    void submit(Long id);

    /**
     * 关闭出库单
     *
     * @param id 计划id
     */
    void close(Long id);

    /**
     * 撤回出库单
     *
     * @param id 计划id
     */
    void cancel(Long id);

    /**
     * 查询出库单明细行列表
     *
     * @param page   分页参数
     * @param planId 查询条件
     * @return 明细行分页列表
     */
    IPage<OutboundPlanItemPageResp> itemPage(Page<OutboundPlanItem> page, Long planId);

    /**
     * 查询出库单详情
     *
     * @param id 出库单id
     * @return 详情
     */
    OutboundPlanDetailResp detail(Long id);

    /**
     * 完成出库单
     * @param id
     */
    void confirm(Long id);
}
