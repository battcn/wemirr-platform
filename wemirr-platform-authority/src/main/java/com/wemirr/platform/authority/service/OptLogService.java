package com.wemirr.platform.authority.service;

import com.wemirr.framework.boot.config.log.OptLogDTO;
import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.authority.domain.entity.log.OptLog;

/**
 * @author Levin
 */
public interface OptLogService extends SuperService<OptLog> {

    /**
     * 保存操作日志
     *
     * @param dto dto
     */
    void save(OptLogDTO dto);
}
