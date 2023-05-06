package com.wemirr.framework.db.configuration.dynamic.feign;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.configuration.dynamic.event.body.TenantDynamicDatasource;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Levin
 */
@FeignClient(name = "wemirr-platform-authority", dismiss404 =  true, fallback = TenantFeignClient.TenantFeignClientFallback.class)
public interface TenantFeignClient {

    /**
     * 查询所有租户数据源
     *
     * @return 查询结果
     */
    @GetMapping(value = "/tenants/databases/active", headers = {"X-Auto-Token=true"})
    Result<List<TenantDynamicDatasource>> selectAll();

    @Component
    @RequiredArgsConstructor
    class TenantFeignClientFallback implements TenantFeignClient {

        @Override
        public Result<List<TenantDynamicDatasource>> selectAll() {
            return Result.success();
        }
    }
}
