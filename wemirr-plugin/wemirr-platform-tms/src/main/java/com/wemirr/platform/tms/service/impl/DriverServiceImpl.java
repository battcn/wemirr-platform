package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tms.domain.entity.Driver;
import com.wemirr.platform.tms.domain.entity.Truck;
import com.wemirr.platform.tms.domain.req.DriverApprovalReq;
import com.wemirr.platform.tms.domain.req.DriverPageReq;
import com.wemirr.platform.tms.domain.req.DriverSaveReq;
import com.wemirr.platform.tms.domain.resp.DriverPageResp;
import com.wemirr.platform.tms.mapper.DriverMapper;
import com.wemirr.platform.tms.mapper.TruckMapper;
import com.wemirr.platform.tms.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class DriverServiceImpl extends SuperServiceImpl<DriverMapper, Driver> implements DriverService {

    private final AuthenticationContext context;
    private final TruckMapper truckMapper;

    @Override
    public IPage<DriverPageResp> pageList(DriverPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<Driver>lbQ().eq(Driver::getEnabled, req.getEnabled())
                .like(Driver::getIdCardNo, req.getIdCardNo())
                .eq(Driver::getLicenseCode, req.getLicenseCode())
                .eq(Driver::getLicenseType, req.getLicenseType())).convert(x -> BeanUtil.toBean(x, DriverPageResp.class));
    }

    void validDefaultTruck(Long defaultTruckId) {
        if (defaultTruckId == null) {
            return;
        }
        final Truck truck = truckMapper.selectById(defaultTruckId);
        if (truck == null) {
            throw CheckedException.badRequest("默认车辆不存在");
        }
    }

    @Override
    public void create(DriverSaveReq req) {
        validDefaultTruck(req.getDefaultTruckId());
        this.baseMapper.insert(BeanUtil.toBean(req, Driver.class));
    }

    @Override
    public void edit(Long id, DriverSaveReq req) {
        validDefaultTruck(req.getDefaultTruckId());
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("司机信息不存在"));
        final Driver bean = BeanUtil.toBean(req, Driver.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void approval(Long id, DriverApprovalReq req) {
        final Driver driver = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("司机信息不存在"));
        this.baseMapper.updateById(Driver.builder().id(driver.getId())
                .approvalStatus(req.getApprovalStatus()).approvalDesc(req.getApprovalDesc())
                .approvalId(context.userId()).approvalName(context.nickName()).build());
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void enabled(Long id, Boolean enabled) {
        final Driver driver = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("司机信息不存在"));
        if (driver.getEnabled() != null && driver.getEnabled().equals(enabled)) {
            return;
        }
        this.baseMapper.updateById(Driver.builder().id(driver.getId()).enabled(enabled).build());
    }
}
