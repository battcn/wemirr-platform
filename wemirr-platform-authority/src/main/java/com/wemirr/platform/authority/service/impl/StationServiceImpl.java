package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.boot.service.impl.SuperServiceImpl;
import com.wemirr.framework.database.mybatis.auth.DataScope;
import com.wemirr.framework.database.mybatis.auth.DataScopeType;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.dto.StationPageDTO;
import com.wemirr.platform.authority.domain.entity.baseinfo.Station;
import com.wemirr.platform.authority.repository.StationMapper;
import com.wemirr.platform.authority.service.StationService;
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
public class StationServiceImpl extends SuperServiceImpl<StationMapper, Station> implements StationService {


    @Override
    public IPage<Station> findStationPage(PageRequest params, StationPageDTO data) {
        Station station = BeanUtil.toBean(data, Station.class);
        final LbqWrapper<Station> wrapper = Wraps.<Station>lbQ().like(Station::getName, station.getName())
                .like(Station::getDescription, station.getDescription())
                .eq(Station::getOrgId, station.getOrgId())
                .eq(Station::getStatus, station.getStatus()).orderByAsc(Station::getSequence);
        return baseMapper.findStationPage(params.buildPage(), wrapper, DataScope.builder().scopeType(DataScopeType.ALL).build());
    }
}
