package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tms.domain.entity.Peccancy;
import com.wemirr.platform.tms.domain.req.PeccancyPageReq;
import com.wemirr.platform.tms.domain.req.PeccancySaveReq;
import com.wemirr.platform.tms.domain.resp.PeccancyPageResp;
import com.wemirr.platform.tms.mapper.PeccancyMapper;
import com.wemirr.platform.tms.service.PeccancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Levin
 */
@Service
@RequiredArgsConstructor
public class PeccancyServiceImpl extends SuperServiceImpl<PeccancyMapper, Peccancy> implements PeccancyService {


    @Override
    public IPage<PeccancyPageResp> pageList(PeccancyPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<Peccancy>lbQ().like(Peccancy::getPeccancyNo, req.getPeccancyNo())
                        .eq(Peccancy::getPeccancyItem, req.getPeccancyItem())
                        .like(Peccancy::getPlateNo, req.getPlateNo()))
                .convert(x -> BeanUtil.toBean(x, PeccancyPageResp.class));
    }

    @Override
    public void create(PeccancySaveReq req) {
        final Peccancy bean = BeanUtil.toBean(req, Peccancy.class);
        this.baseMapper.insert(bean);
    }

    @Override
    public void edit(Long id, PeccancySaveReq req) {
        Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("车辆事故信息列表不存在"));
        final Peccancy bean = BeanUtil.toBean(req, Peccancy.class);
        bean.setId(id);
        this.baseMapper.updateById(bean);
    }

}
