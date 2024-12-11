package com.wemirr.platform.iam.base.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.iam.base.domain.dto.resp.SiteSettingDetailResp;
import com.wemirr.platform.iam.base.service.PlatService;
import com.wemirr.platform.iam.tenant.domain.entity.Tenant;
import com.wemirr.platform.iam.tenant.domain.entity.TenantSetting;
import com.wemirr.platform.iam.tenant.repository.TenantMapper;
import com.wemirr.platform.iam.tenant.repository.TenantSettingMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * @author levin
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlatServiceImpl implements PlatService {

    private final TenantMapper tenantMapper;
    private final TenantSettingMapper tenantSettingMapper;

    @Override
    public SiteSettingDetailResp siteSetting(HttpServletRequest request) {
        String httpUrl = request.getHeader("origin");
        if (StrUtil.isBlank(httpUrl)) {
            httpUrl = request.getHeader("referer");
        }
        log.debug("http header origin => {}", httpUrl);
        // 该部分配置后续从数据库读取
        SiteSettingDetailResp detail = new SiteSettingDetailResp();
        if (StrUtil.isBlank(httpUrl)) {
            detail.setTitle("WP SAAS云平台");
            return detail;
        }
        String siteUrl = URLUtil.url(httpUrl).getHost();
        TenantSetting setting = this.tenantSettingMapper.selectOne(Wraps.<TenantSetting>lbQ()
                .eq(TenantSetting::getSiteUrl, siteUrl).last(" limit 1"));
        if (setting == null) {
            detail.setTitle("WP 租户平台");
            return detail;
        }
        Tenant tenant = this.tenantMapper.selectById(setting.getTenantId());
        if (tenant != null) {
            detail.setTenantCode(tenant.getCode());
        }
        detail.setTitle(setting.getSiteTitle());
        detail.setSubTitle(setting.getSiteSubTitle());
        detail.setLogo(setting.getSiteLogo());
        JSONObject ext = new JSONObject();
        ext.put("showCodeLogin", false);
        ext.put("showForgetPassword", true);
        ext.put("showQrcodeLogin", false);
        ext.put("showRegister", false);
        ext.put("showRememberMe", false);
        ext.put("showThirdPartyLogin", false);
        detail.setExt(ext);
        return detail;
    }
}
