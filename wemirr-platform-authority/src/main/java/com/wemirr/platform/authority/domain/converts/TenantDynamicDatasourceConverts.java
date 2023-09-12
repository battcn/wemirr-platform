package com.wemirr.platform.authority.domain.converts;

import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.framework.db.configuration.dynamic.event.body.TenantDynamicDatasource;
import com.wemirr.platform.authority.domain.vo.TenantDynamicDatasourceVO;
import org.springframework.beans.BeanUtils;

/**
 * @author levin
 */
public class TenantDynamicDatasourceConverts {

    public static final TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts TENANT_DYNAMIC_DATASOURCE_VO_2_TENANT_DYNAMIC_DATASOURCE_CONVERTS = new TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts();

    public static class TenantDynamicDatasourceVo2TenantDynamicDatasourceConverts implements BaseConverts<TenantDynamicDatasourceVO, TenantDynamicDatasource> {
        @Override
        public TenantDynamicDatasource convert(TenantDynamicDatasourceVO source) {
            if (source == null) {
                return null;
            }
            TenantDynamicDatasource target = new TenantDynamicDatasource();
            BeanUtils.copyProperties(source, target);
            return target;
        }
    }


}
