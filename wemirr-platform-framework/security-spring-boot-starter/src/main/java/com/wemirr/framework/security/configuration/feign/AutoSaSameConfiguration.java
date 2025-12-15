package com.wemirr.framework.security.configuration.feign;

import cn.dev33.satoken.same.SaSameUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.util.Collection;
import java.util.Map;

/**
 * 服务之间调用添加same-token 请求头
 *
 * @author YanCh
 * create on: 2025-06-06 16:04
 **/
@Slf4j
public class AutoSaSameConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        final Map<String, Collection<String>> headers = template.headers();
        if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
            log.debug("当前上下文中已包含token,跳过自动获取流程...");
            return;
        }
        // 获取Same Token
        template.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
    }
}
