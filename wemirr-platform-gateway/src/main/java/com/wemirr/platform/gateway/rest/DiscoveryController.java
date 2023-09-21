package com.wemirr.platform.gateway.rest;


import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.commons.entity.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/discoveries")
public class DiscoveryController {

    private final DiscoveryClient discoveryClient;

    @GetMapping
    public List<String> serviceUrl() {
        return discoveryClient.getServices();
    }

    @GetMapping("/dict")
    public Result<List<JSONObject>> dict() {
        final List<String> services = discoveryClient.getServices();
        return Result.success(services.stream().map(serviceId -> {
            JSONObject object = new JSONObject();
            object.put("serviceId", serviceId);
            return object;
        }).collect(toList()));
    }


}
