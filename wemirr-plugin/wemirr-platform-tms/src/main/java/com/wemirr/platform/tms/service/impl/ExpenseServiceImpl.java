package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.tms.domain.entity.Expense;
import com.wemirr.platform.tms.domain.enums.TmsSequence;
import com.wemirr.platform.tms.domain.req.ExpensePageReq;
import com.wemirr.platform.tms.domain.req.ExpenseSaveReq;
import com.wemirr.platform.tms.domain.resp.ExpensePageResp;
import com.wemirr.platform.tms.mapper.ExpenseMapper;
import com.wemirr.platform.tms.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl extends SuperServiceImpl<ExpenseMapper, Expense> implements ExpenseService {

    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext authenticationContext;


    @Override
    public IPage<ExpensePageResp> pageList(ExpensePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<Expense>lbQ().like(Expense::getExpenseNo, req.getExpenseNo())
                        .eq(Expense::getExpenseItem, req.getExpenseItem())
                        .like(Expense::getPlateNo, req.getPlateNo()))
                .convert(x -> BeanUtil.toBean(x, ExpensePageResp.class));
    }

    @Override
    public void create(ExpenseSaveReq req) {
        final Expense bean = BeanUtil.toBean(req, Expense.class);
        bean.setExpenseNo(sequenceHelper.generate(TmsSequence.EXPENSE_NO, authenticationContext.tenantId()));
        this.baseMapper.insert(bean);
    }

    @Override
    public void edit(Long id, ExpenseSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("规费信息列表不存在"));
        final Expense bean = BeanUtil.toBean(req, Expense.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }


}
