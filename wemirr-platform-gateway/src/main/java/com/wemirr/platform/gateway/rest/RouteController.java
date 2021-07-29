package com.wemirr.platform.gateway.rest;


import com.alibaba.fastjson.JSONObject;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.gateway.config.rule.RouteRuleHelper;
import com.wemirr.platform.gateway.rest.domain.RouteRule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * http://localhost:9000/actuator/gateway/routes
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/rules/routes")
public class RouteController {

    private final RouteRuleHelper routeRuleHelper;


    @GetMapping
    public Result<JSONObject> query() {
        JSONObject data = new JSONObject();
        final List<RouteRule> limitRules = routeRuleHelper.query();
        data.put("total", limitRules.size());
        data.put("records", limitRules);
        data.put("current", 1);
        data.put("size", 20);
        data.put("pages", 1);
        return Result.success(data);
    }

    @SneakyThrows
    @PostMapping
    public Result<Void> add(@Validated @RequestBody RouteRule rule) {
        routeRuleHelper.saveOrUpdate(rule);
        return Result.success();
    }

    @PatchMapping("/{id}/{status}")
    public Result<Void> status(@PathVariable String id, @PathVariable Boolean status) {
        this.routeRuleHelper.routeHandler(id, status);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        this.routeRuleHelper.delete(id);
        return Result.success();
    }

}
