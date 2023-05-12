package com.wemirr.framework.security.feign;

import cn.hutool.core.net.NetUtil;
import com.wemirr.framework.commons.entity.enums.CommonError;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.security.client.annotation.InnerService;
import com.wemirr.framework.security.feign.properties.InnerServiceProperties;
import com.wemirr.framework.security.utils.RequestUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 服务间接口不鉴权处理逻辑
 *
 * @author Levin
 */
@Slf4j
@Aspect
@AllArgsConstructor
@EnableConfigurationProperties(InnerServiceProperties.class)
public class SecurityInnerServiceAspect implements Ordered {

    private final HttpServletRequest request;
    private final InnerServiceProperties innerServiceProperties;

    @Around("@annotation(inner)")
    public Object around(ProceedingJoinPoint point, InnerService inner) throws Throwable {
        String ipAddress = RequestUtils.getIpAddress(request);
        if (ipAddress.contains(",")) {
            String[] ips = ipAddress.split(",");
            for (String ip : ips) {
                boolean innerIP = NetUtil.isInnerIP(ip);
                if (innerIP) {
                    return point.proceed();
                }
            }
        } else {
            boolean innerIP = NetUtil.isInnerIP(ipAddress);
            if (innerIP) {
                return point.proceed();
            }
        }
        List<String> whiteLists = innerServiceProperties.getWhiteLists();
        String signatureName = point.getSignature().getName();
        if (!whiteLists.contains(ipAddress)) {
            log.warn("访问受限，非白名单，[IP] - [{}] - [方法] - [{}]", ipAddress, signatureName);
            throw CheckedException.badRequest(CommonError.ACCESS_DENIED);
        }
        String header = request.getHeader(innerServiceProperties.getHeader());
        String headerValue = innerServiceProperties.getHeaderValue();
        if (inner.value() && !StringUtils.equals(headerValue, header)) {
            log.warn("访问受限，非白名单，[IP] - [{}] - [方法] - [{}]", ipAddress, signatureName);
            throw CheckedException.badRequest(CommonError.ACCESS_DENIED);
        }
        return point.proceed();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
