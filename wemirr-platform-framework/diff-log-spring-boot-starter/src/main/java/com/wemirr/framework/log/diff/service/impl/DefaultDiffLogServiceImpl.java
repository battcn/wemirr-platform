package com.wemirr.framework.log.diff.service.impl;

import com.alibaba.fastjson2.JSON;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.req.DiffLogInfoQueryReq;
import com.wemirr.framework.log.diff.service.IDiffLogService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author muzhantong
 */
@Slf4j
public class DefaultDiffLogServiceImpl implements IDiffLogService {

    @Override
    public void handler(DiffLogInfo diffLogInfo) {
        log.info("[default diff log] {}", JSON.toJSONString(diffLogInfo));
    }

    @Override
    public List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req) {
        return null;
    }


}
