package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tms.domain.entity.Fleet;
import com.wemirr.platform.tms.domain.req.FleetPageReq;
import com.wemirr.platform.tms.domain.req.FleetSaveReq;
import com.wemirr.platform.tms.domain.resp.FleetPageResp;
import com.wemirr.platform.tms.mapper.FleetMapper;
import com.wemirr.platform.tms.service.FleetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class FleetServiceImpl extends SuperServiceImpl<FleetMapper, Fleet> implements FleetService {


    @Override
    public IPage<FleetPageResp> pageList(FleetPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<Fleet>lbQ().like(Fleet::getFleetName, req.getFleetName())
                        .like(Fleet::getLeaderMobile, req.getLeaderMobile())
                        .like(Fleet::getLeaderRealName, req.getLeaderRealName()))
                .convert(x -> BeanUtil.toBean(x, FleetPageResp.class));
    }

    @Override
    public void create(FleetSaveReq req) {
        this.baseMapper.insert(BeanUtil.toBean(req, Fleet.class));
    }

    @Override
    public void edit(Long id, FleetSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("司机信息不存在"));
        final Fleet bean = BeanUtil.toBean(req, Fleet.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }
}
