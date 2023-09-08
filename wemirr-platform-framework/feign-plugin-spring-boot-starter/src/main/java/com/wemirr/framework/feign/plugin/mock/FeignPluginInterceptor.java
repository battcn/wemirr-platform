package com.wemirr.framework.feign.plugin.mock;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.feign.plugin.FeignPluginProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class FeignPluginInterceptor implements RequestInterceptor {

    public final String X_MOCK_APPLICATION = "X-Mock-Application";
    private final FeignPluginProperties properties;

    @Override
    public void apply(RequestTemplate template) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null && properties.isEnabled()) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            final Enumeration<?> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                final String headerKey = (String) headerNames.nextElement();
                final String headerValue = request.getHeader(headerKey);
                if (headerKey.contains(HttpHeaders.CONTENT_LENGTH) || StrUtil.equalsIgnoreCase(headerKey, HttpHeaders.CONTENT_LENGTH)) {
                    continue;
                }
                template.header(headerKey, headerValue);
            }
        }
        if (properties.getMock() != null && properties.getMock().isEnabled()) {
            log.debug("mock interceptor .....");
            template.header(X_MOCK_APPLICATION, "true");
        }
    }
}
