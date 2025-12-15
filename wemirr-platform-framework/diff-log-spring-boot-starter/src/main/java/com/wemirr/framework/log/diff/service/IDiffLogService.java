package com.wemirr.framework.log.diff.service;


import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import com.wemirr.framework.log.diff.domain.req.DiffLogInfoQueryReq;

import java.util.List;

/**
 * 差异日志服务接口，用于处理和查询操作日志
 *
 * @author Levin
 */
public interface IDiffLogService {
    /**
     * 处理日志信息，由业务方实现具体的存储逻辑
     *
     * @param logInfo 日志信息
     */
    void handler(DiffLogInfo logInfo);

    /**
     * 查询操作日志
     *
     * @param req 查询条件
     * @return 操作日志列表
     */
    List<DiffLogInfo> queryLog(DiffLogInfoQueryReq req);
}
