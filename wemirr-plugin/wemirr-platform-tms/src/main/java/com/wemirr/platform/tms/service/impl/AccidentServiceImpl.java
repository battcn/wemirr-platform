package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.sequence.RedisSequenceHelper;
import com.wemirr.platform.tms.domain.entity.Accident;
import com.wemirr.platform.tms.domain.enums.TmsSequence;
import com.wemirr.platform.tms.domain.req.AccidentPageReq;
import com.wemirr.platform.tms.domain.req.AccidentSaveReq;
import com.wemirr.platform.tms.domain.resp.AccidentPageResp;
import com.wemirr.platform.tms.mapper.AccidentMapper;
import com.wemirr.platform.tms.service.AccidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class AccidentServiceImpl extends SuperServiceImpl<AccidentMapper, Accident> implements AccidentService {

    private final RedisSequenceHelper sequenceHelper;
    private final AuthenticationContext context;

    @Override
    public IPage<AccidentPageResp> pageList(AccidentPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<Accident>lbQ().like(Accident::getAccidentNo, req.getAccidentNo())
                        .eq(Accident::getDriverName, req.getDriverName())
                        .like(Accident::getPlateNo, req.getPlateNo()))
                .convert(x -> BeanUtil.toBean(x, AccidentPageResp.class));
    }

    @Override
    public void create(AccidentSaveReq req) {
        final Accident bean = BeanUtil.toBean(req, Accident.class);
        bean.setAccidentNo(sequenceHelper.generate(TmsSequence.ACCIDENT_NO, context.tenantId()));
        this.baseMapper.insert(bean);
    }

    @Override
    public void edit(Long id, AccidentSaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("车辆事故信息列表不存在"));
        final Accident bean = BeanUtil.toBean(req, Accident.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }
}
