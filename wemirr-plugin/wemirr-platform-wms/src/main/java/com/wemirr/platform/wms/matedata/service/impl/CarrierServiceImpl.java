package com.wemirr.platform.wms.matedata.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.wms.inbound.domain.enums.WmsSequence;
import com.wemirr.platform.wms.matedata.domain.entity.Carrier;
import com.wemirr.platform.wms.matedata.domain.req.CarrierSaveReq;
import com.wemirr.platform.wms.matedata.mapper.CarrierMapper;
import com.wemirr.platform.wms.matedata.service.CarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 承运商 服务实现类
 * </p>
 *
 * @author ddCat
 * @since 2024-07-14
 */
@Service
@RequiredArgsConstructor
public class CarrierServiceImpl extends SuperServiceImpl<CarrierMapper, Carrier> implements CarrierService {

    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext context;

    @Override
    public void create(CarrierSaveReq req) {
        Long count = this.baseMapper.selectCount(Carrier::getName, req.getName());
        if (count != null && count > 0) {
            throw CheckedException.badRequest("承运商名称已存在");
        }
        var bean = BeanUtil.toBean(req, Carrier.class);
        String code = sequenceHelper.generate(WmsSequence.CARRIER_NO, context.tenantId());
        bean.setCode(code);
        this.baseMapper.insert(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void modify(Long id, CarrierSaveReq req) {
        Long count = this.baseMapper.selectCount(Wraps.<Carrier>lbQ().ne(Carrier::getId, id).eq(Carrier::getName, req.getName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("承运商名称已存在");
        }
        Carrier bean = BeanUtilPlus.toBean(id, req, Carrier.class);
        this.baseMapper.updateById(bean);
    }
}
