package com.wemirr.platform.authority.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.Station;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.req.StationPageReq;
import com.wemirr.platform.authority.repository.baseinfo.StationMapper;
import com.wemirr.platform.authority.service.OrgService;
import com.wemirr.platform.authority.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 业务实现类
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StationServiceImpl extends SuperServiceImpl<StationMapper, Station> implements StationService {

    private final OrgService orgService;

    @Override
    public IPage<Station> pageList(StationPageReq req) {
        final LbqWrapper<Station> wrapper = Wraps.<Station>lbQ().like(Station::getName, req.getName())
                .eq(Station::getStatus, req.getStatus())
                .in(Station::getOrgId, orgService.getFullTreeIdPath(req.getOrgId()))
                .eq(Station::getDeleted, false)
                .orderByAsc(Station::getSequence);
        return baseMapper.findStationPage(req.buildPage(), wrapper);
    }


}
