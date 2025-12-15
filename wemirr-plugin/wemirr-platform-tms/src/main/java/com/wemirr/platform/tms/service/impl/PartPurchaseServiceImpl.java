package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.tms.domain.entity.PartPurchase;
import com.wemirr.platform.tms.domain.enums.TmsSequence;
import com.wemirr.platform.tms.domain.req.PartPurchasePageReq;
import com.wemirr.platform.tms.domain.req.PartPurchaseSaveReq;
import com.wemirr.platform.tms.domain.resp.PartPurchasePageResp;
import com.wemirr.platform.tms.mapper.PartPurchaseMapper;
import com.wemirr.platform.tms.service.PartPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class PartPurchaseServiceImpl extends SuperServiceImpl<PartPurchaseMapper, PartPurchase> implements PartPurchaseService {


    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext authenticationContext;

    @Override
    public IPage<PartPurchasePageResp> pageList(PartPurchasePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<PartPurchase>lbQ()
                        .like(PartPurchase::getPurchaseNo, req.getPurchaseNo())
                        .like(PartPurchase::getPartName, req.getPartName()))
                .convert(x -> BeanUtil.toBean(x, PartPurchasePageResp.class));
    }

    @Override
    public void create(PartPurchaseSaveReq req) {
        final PartPurchase bean = BeanUtil.toBean(req, PartPurchase.class);
        bean.setPurchaseNo(sequenceHelper.generate(TmsSequence.PURCHASE_NO, authenticationContext.tenantId()));
        this.baseMapper.insert(bean);
    }

    @Override
    public void edit(Long id, PartPurchaseSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("配件采购单信息不存在"));
        final PartPurchase bean = BeanUtil.toBean(req, PartPurchase.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }
}
