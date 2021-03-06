package com.wemirr.platform.authority.configuration;

import com.google.common.collect.Lists;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 演示环境拦截器
 *
 * @author Levin
 */
@Slf4j
public class DemoProfileInterceptor implements HandlerInterceptor {

    private static final int MAX_ID = 10;
    private static final List<String> REJECT_POST = Lists.newArrayList("resources", "dictionaries");
    private static final List<String> REJECT_OPTION = Lists.newArrayList("users", "roles", "stations", "tenants", "databases", "applications", "change_password", "resources", "dictionaries");

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {
        if (request == null) {
            throw CheckedException.notFound("request is null");
        }
        final HttpMethod method = HttpMethod.valueOf(request.getMethod());
        final String uri = request.getRequestURI();
        if (method == HttpMethod.GET) {
            return true;
        }
        if (method == HttpMethod.POST) {
            for (String url : REJECT_POST) {
                if (StringUtils.contains(uri, url)) {
                    throw CheckedException.notFound("演示环境,禁止破坏性的数据新增");
                }
            }
        }
        for (String url : REJECT_OPTION) {
            if (StringUtils.contains(uri, url)) {
                throw CheckedException.notFound("禁止操作演示环境的核心数据,");
            }
        }
        final String strId = StringUtils.substringAfterLast(uri, "/");
        try {
            final int id = Integer.parseInt(strId);
            if (id < MAX_ID) {
                throw CheckedException.notFound("禁止操作演示环境的核心数据");
            }
        } catch (Exception e) {
            log.error("转换失败 - {}", e.getMessage());
        }
        log.debug("method - {},uri - {} - id - {}", method, uri, strId);
        return true;
    }

}
