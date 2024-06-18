package com.wemirr.platform.authority.service.impl;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.req.DiffLogInfoQueryReq;
import com.wemirr.framework.log.diff.service.IDiffLogService;
import com.wemirr.platform.authority.domain.common.entity.OptLog;
import com.wemirr.platform.authority.repository.common.OptLogMapper;
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

    private final OptLogMapper optLogMapper;

    @Override
    public void handler(DiffLogInfo logInfo) {
        OptLog optLog = new OptLog();
        this.optLogMapper.insert(optLog);
        log.info("[diff log] {}", JSON.toJSONString(logInfo));
    }

    @Override
    public List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req) {
        return null;
    }

}
