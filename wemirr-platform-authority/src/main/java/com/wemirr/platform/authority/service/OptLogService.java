package com.wemirr.platform.authority.service;

import com.wemirr.framework.boot.log.OptLogDTO;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.common.entity.OptLog;

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
