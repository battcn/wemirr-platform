package com.wemirr.framework.log.diff.service.impl;

import com.wemirr.framework.commons.JacksonUtils;
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
        log.info("[default diff log] {}", JacksonUtils.toJson(diffLogInfo));
    }

    @Override
    public List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req) {
        return null;
    }


}
