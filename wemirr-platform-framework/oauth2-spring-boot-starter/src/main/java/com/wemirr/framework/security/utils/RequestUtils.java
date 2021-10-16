package com.wemirr.framework.security.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Levin
 */
@Slf4j
public class RequestUtils {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, localhost0,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request 请求参数
     * @return IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return getIpAddress(request);
    }


    public static String getUri(HttpServletRequest request) throws IllegalStateException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        int start = uri.indexOf(contextPath);
        return uri.substring(start + contextPath.length());
    }

    public static String getBodyParamsStr(InputStream stream, Charset charset) throws IOException {
        return IOUtils.toString(stream, charset.name());
    }

    public static String getBodyParamsStr(HttpServletRequest request) throws IOException {
        return getBodyParamsStr(request.getInputStream(), StandardCharsets.UTF_8);
    }

    public static JSONObject getBodyParams(String bodyParamsStr) {
        if (StringUtils.isBlank(bodyParamsStr)) {
            return null;
        }
        return JSONObject.parseObject(bodyParamsStr);
    }

    public static JSONObject getUriParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            List<String> valueList = new ArrayList<>(Arrays.asList(values));
            jsonObject.put(name, valueList.size() < 2 ? valueList.get(0) : valueList);
        }
        return jsonObject;
    }

    public static Map<String, String> getHeadParams(HttpServletRequest request) {
        Map<String, String> result = Maps.newHashMap();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }
        return result;
    }

    public static JSONObject getHeadParams2Json(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            jsonObject.put(key, value);
        }
        return jsonObject;
    }

    public static String getLocalIpAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("[网络错误]", e);
        }
        return "127.0.0.1";
    }

}
