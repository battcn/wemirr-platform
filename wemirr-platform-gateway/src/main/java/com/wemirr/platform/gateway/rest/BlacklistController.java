package com.wemirr.platform.gateway.rest;

import com.alibaba.fastjson.JSONObject;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.gateway.config.BlacklistHelper;
import com.wemirr.platform.gateway.rest.domain.BlacklistRule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/rules/blacklist")
public class BlacklistController {

    private final BlacklistHelper blacklistHelper;

    @GetMapping
    public Result<JSONObject> query() {
        JSONObject data = new JSONObject();
        final List<BlacklistRule> limitRules = blacklistHelper.query();
        data.put("total", limitRules.size());
        data.put("records", limitRules);
        data.put("current", 1);
        data.put("size", 10);
        data.put("pages", 1);
        return Result.success(data);
    }

    @PostMapping
    public Result<Void> saveOrUpdate(@RequestBody BlacklistRule rule) {
        blacklistHelper.saveOrUpdate(rule);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {

        return Result.success();
    }

}
