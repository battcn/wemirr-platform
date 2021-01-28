package com.wemirr.platform.gateway.rest;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.gateway.rest.domain.LimitRule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/rules/limits")
public class LimitController {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String DEFAULT_RULE_LIMIT = "rule:limit";

    @GetMapping
    public Result<Set<Object>> query() {
        final Set<Object> keys = stringRedisTemplate.opsForHash().keys(DEFAULT_RULE_LIMIT);
        return Result.success(keys);
    }

    @PostMapping
    public Result<String> create(@RequestBody LimitRule rule) {
        String uuid = IdUtil.fastSimpleUUID();
        rule.setId(uuid);
        stringRedisTemplate.opsForHash().put(DEFAULT_RULE_LIMIT, uuid, JSON.toJSONString(rule));
        return Result.success();
    }

}
