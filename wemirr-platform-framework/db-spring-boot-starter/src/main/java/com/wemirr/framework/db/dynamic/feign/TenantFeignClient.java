package com.wemirr.framework.db.dynamic.feign;

import com.wemirr.framework.db.dynamic.core.TenantDynamicDatasource;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Levin
 */
@FeignClient(name = "wemirr-platform-authority", dismiss404 = true, fallback = TenantFeignClient.TenantFeignClientFallback.class)
public interface TenantFeignClient {

    /**
     * 查询所有租户数据源
     *
     * @return 查询结果
     */
    @GetMapping(value = "/tenants/databases/active", headers = {"X-Auto-Token=true"})
    List<TenantDynamicDatasource> selectAll();

    @Component
    @RequiredArgsConstructor
    class TenantFeignClientFallback implements TenantFeignClient {

        @Override
        public List<TenantDynamicDatasource> selectAll() {
            return null;
        }
    }
}
