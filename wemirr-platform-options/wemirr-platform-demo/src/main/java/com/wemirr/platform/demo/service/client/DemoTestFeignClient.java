package com.wemirr.platform.demo.service.client;

import com.wemirr.framework.commons.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Levin
 */
@FeignClient(name = "wemirr-platform-authority", decode404 = true)
public interface DemoTestFeignClient {

    @GetMapping("/opt_logs")
    Result<?> query();

}
