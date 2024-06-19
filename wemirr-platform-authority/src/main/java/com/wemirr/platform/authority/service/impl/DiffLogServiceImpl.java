package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.req.DiffLogInfoQueryReq;
import com.wemirr.framework.log.diff.service.IDiffLogService;
import com.wemirr.platform.authority.domain.common.entity.DiffLogEntity;
import com.wemirr.platform.authority.repository.common.DiffLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DiffLogServiceImpl implements IDiffLogService {

    private final DiffLogMapper diffLogMapper;

    @Override
    public void handler(DiffLogInfo logInfo) {
        log.info("[diff log] {}", JSON.toJSONString(logInfo));
        DiffLogEntity entity = BeanUtil.toBean(logInfo, DiffLogEntity.class);
        this.diffLogMapper.insert(entity);
    }

    @Override
    public List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req) {
        return this.diffLogMapper.selectObjs(Wraps.<DiffLogEntity>lbQ()
                .eq(DiffLogEntity::getBusinessGroup, req.getBusinessGroup())
                .eq(DiffLogEntity::getBusinessTag, req.getBusinessTag())
                .eq(DiffLogEntity::getBusinessKey, req.getBusinessKey()));
    }

}
