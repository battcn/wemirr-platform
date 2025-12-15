package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.PartPurchase;
import com.wemirr.platform.tms.domain.req.PartPurchasePageReq;
import com.wemirr.platform.tms.domain.req.PartPurchaseSaveReq;
import com.wemirr.platform.tms.domain.resp.PartPurchasePageResp;

/**
 * @author Levin
 */
public interface PartPurchaseService extends SuperService<PartPurchase> {

    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<PartPurchasePageResp> pageList(PartPurchasePageReq req);

    /**
     * 创建配件采购信息
     *
     * @param req req
     */
    void create(PartPurchaseSaveReq req);

    /**
     * 编辑配件采购信息
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, PartPurchaseSaveReq req);
}
