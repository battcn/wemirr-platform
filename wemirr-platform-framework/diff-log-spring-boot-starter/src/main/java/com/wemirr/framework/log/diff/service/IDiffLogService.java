package com.wemirr.framework.log.diff.service;


import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.req.DiffLogInfoQueryReq;

import java.util.List;

/**
 * 日志记录
 *
 * @author Levin
 */
public interface IDiffLogService {
    /**
     * 日志信息回调
     *
     * @param logInfo 日志信息
     */
    void handler(DiffLogInfo logInfo);

    /**
     * 返回最多100条记录
     *
     * @param req req
     * @return 操作日志列表
     */
    List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req);
}
