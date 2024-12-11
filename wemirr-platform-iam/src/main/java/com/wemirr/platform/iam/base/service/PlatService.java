package com.wemirr.platform.iam.base.service;

import com.wemirr.platform.iam.base.domain.dto.resp.SiteSettingDetailResp;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Levin
 */
public interface PlatService {


    /**
     * 站点设置信息
     *
     * @param request http request
     * @return 站点详情
     */
    SiteSettingDetailResp siteSetting(HttpServletRequest request);

}
