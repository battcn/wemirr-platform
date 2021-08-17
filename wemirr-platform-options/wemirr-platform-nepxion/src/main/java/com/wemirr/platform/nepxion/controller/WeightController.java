package com.wemirr.platform.nepxion.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/weights")
@RequiredArgsConstructor
@Validated
public class WeightController {

    @Value("${spring.cloud.nacos.discovery.metadata.version:1.0}")
    private String version;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    @SneakyThrows
    @GetMapping
    public String find1(HttpServletRequest request) {
        log.info("当前版本 - {} - 流量 {}", version, atomicInteger.getAndIncrement());
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            log.info("header - key - {} - value - {} ", key, value);
        }
        return version;
    }


}
