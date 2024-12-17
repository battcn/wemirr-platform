package com.wemirr.platform.wms.inbound.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.wms.inbound.domain.entity.InventoryReceipt;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptSaveReq;
import com.wemirr.platform.wms.inbound.domain.req.InventoryReceiptSubmitReq;
import com.wemirr.platform.wms.inbound.domain.resp.InventoryReceiptDetailResp;

/**
 * <p>
 * 入库单 服务类
 * </p>
 *
 * @author ddCat
 * @since 2024-06-27
 */
public interface InventoryReceiptService extends SuperService<InventoryReceipt> {

    /**
     * 保存入库单
     *
     * @param id  入库单id
     * @param req 入库单
     */
    void saveOrUpdateInventoryReceipt(Long id, InventoryReceiptSaveReq req);

    /**
     * 移除入库单
     *
     * @param id 入库单id
     */
    void removeInventoryReceipt(Long id);

    /**
     * 提交确认入库单
     *
     * @param id  入库单id
     * @param req req
     */
    void submit(Long id, InventoryReceiptSubmitReq req);

    /**
     * 查询入库单详情
     *
     * @param id 入库单id
     * @return 入库单详情
     */
    InventoryReceiptDetailResp detail(Long id);
}
