package com.wemirr.platform.authority.domain.converts;

import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.framework.db.dynamic.event.body.TenantDynamicDatasource;
import com.wemirr.platform.authority.domain.resp.TenantDatasourceResp;
import org.springframework.beans.BeanUtils;

/**
 * @author levin
 */
public class TenantDatasourceConverts {

    public static final TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS = new TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts();

    public static class TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts implements BaseConverts<TenantDatasourceResp, TenantDynamicDatasource> {
        @Override
        public TenantDynamicDatasource convert(TenantDatasourceResp source) {
            if (source == null) {
                return null;
            }
            TenantDynamicDatasource target = new TenantDynamicDatasource();
            BeanUtils.copyProperties(source, target);
            return target;
        }
    }


}
