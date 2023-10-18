package com.wemirr.framework.boot.log.feign;

import com.wemirr.framework.boot.log.AccessLogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.wemirr.framework.boot.log.feign.AccessLogFeign.FEIGN_CLIENT_NAME;

/**
 * 访问日志的 Feign
 *
 * @author Levin
 */
@FeignClient(name = FEIGN_CLIENT_NAME, dismiss404 = true)
public interface AccessLogFeign {

    String FEIGN_CLIENT_NAME = "wemirr-platform-authority";

    /**
     * 日志监听
     *
     * @param info 日志信息
     */
    @PostMapping("/opt_logs/listener")
    void listener(@RequestBody AccessLogInfo info);

}
