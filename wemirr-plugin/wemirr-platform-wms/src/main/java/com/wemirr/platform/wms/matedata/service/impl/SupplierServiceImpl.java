package com.wemirr.platform.wms.matedata.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.wms.inbound.domain.enums.WmsSequence;
import com.wemirr.platform.wms.matedata.domain.entity.Supplier;
import com.wemirr.platform.wms.matedata.domain.req.SupplierSaveReq;
import com.wemirr.platform.wms.matedata.mapper.SupplierMapper;
import com.wemirr.platform.wms.matedata.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-07-01
 */
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl extends SuperServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext context;

    @Override
    public void create(SupplierSaveReq req) {
        Long count = this.baseMapper.selectCount(Supplier::getCreditCode, req.getCreditCode());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("供应商统一信用代码已存在");
        }
        var bean = BeanUtil.toBean(req, Supplier.class);
        String code = sequenceHelper.generate(WmsSequence.SUPPLIER_NO, context.tenantId());
        bean.setCode(code);
        this.baseMapper.insert(bean);
    }

    @Override
    public void modify(Long id, SupplierSaveReq req) {
        Supplier oldSupplier = this.baseMapper.selectById(id);
        if (ObjectUtils.isEmpty(oldSupplier)) {
            throw CheckedException.badRequest("数据不存在");
        }
        if (!oldSupplier.getCreditCode().equals(req.getCreditCode())) {
            // 统一信用代码变动，需要校验一下是否已存在
            if (exists(Wraps.<Supplier>lbQ().eq(Supplier::getCreditCode, req.getCreditCode()))) {
                throw CheckedException.badRequest("该供应商统一信用代码已存在");
            }
        }
        Supplier bean = BeanUtil.toBean(req, Supplier.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }
}
