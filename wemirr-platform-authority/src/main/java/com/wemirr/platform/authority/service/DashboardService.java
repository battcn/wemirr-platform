package com.wemirr.platform.authority.service;

import com.wemirr.platform.authority.domain.vo.DashboardResp;

/**
 * @author Levin
 */
public interface DashboardService {

    /**
     * 控制面板
     *
     * @return 结果集
     */
    DashboardResp dashboard();

}
