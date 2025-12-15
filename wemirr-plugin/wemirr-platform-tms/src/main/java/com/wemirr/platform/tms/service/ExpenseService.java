package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.Expense;
import com.wemirr.platform.tms.domain.req.ExpensePageReq;
import com.wemirr.platform.tms.domain.req.ExpenseSaveReq;
import com.wemirr.platform.tms.domain.resp.ExpensePageResp;

/**
 * @author Levin
 */
public interface ExpenseService extends SuperService<Expense> {

    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<ExpensePageResp> pageList(ExpensePageReq req);

    /**
     * 创建规费信息
     *
     * @param req req
     */
    void create(ExpenseSaveReq req);

    /**
     * 编辑规费信息
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, ExpenseSaveReq req);

}
