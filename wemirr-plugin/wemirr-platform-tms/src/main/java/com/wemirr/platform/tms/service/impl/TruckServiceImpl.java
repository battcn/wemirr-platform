package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tms.domain.entity.Truck;
import com.wemirr.platform.tms.domain.req.TruckApprovalReq;
import com.wemirr.platform.tms.domain.req.TruckPageReq;
import com.wemirr.platform.tms.domain.req.TruckSaveReq;
import com.wemirr.platform.tms.domain.resp.TruckPageResp;
import com.wemirr.platform.tms.mapper.TruckMapper;
import com.wemirr.platform.tms.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class TruckServiceImpl extends SuperServiceImpl<TruckMapper, Truck> implements TruckService {

    private final AuthenticationContext authenticationContext;


    @Override
    public IPage<TruckPageResp> pageList(TruckPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<Truck>lbQ().eq(Truck::getEnabled, req.getEnabled())
                .like(Truck::getPlateNo, req.getPlateNo())
                .like(Truck::getRoadCertificateCode, req.getRoadCertificateCode())
                .eq(Truck::getEnergyType, req.getEnergyType())).convert(x -> BeanUtil.toBean(x, TruckPageResp.class));
    }

    @Override
    public void create(TruckSaveReq req) {
        this.baseMapper.insert(BeanUtil.toBean(req, Truck.class));
    }

    @Override
    public void edit(Long id, TruckSaveReq req) {
        final Truck driver = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("司机信息不存在"));
        final Truck bean = BeanUtil.toBean(req, Truck.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }

    @Override
    public void approval(Long id, TruckApprovalReq req) {
        final Truck driver = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("司机信息不存在"));
        this.baseMapper.updateById(Truck.builder().id(driver.getId())
                .approvalStatus(req.getApprovalStatus()).approvalDesc(req.getApprovalDesc())
                .approvalId(authenticationContext.userId()).approvalName(authenticationContext.nickName()).build());
    }

    @Override
    public void enabled(Long id, Boolean enabled) {
        final Truck driver = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("司机信息不存在"));
        if (driver.getEnabled() != null && driver.getEnabled().equals(enabled)) {
            return;
        }
        this.baseMapper.updateById(Truck.builder().id(driver.getId()).enabled(enabled).build());
    }
}
