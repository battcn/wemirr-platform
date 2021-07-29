package com.wemirr.platform.gateway.rest;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.gateway.config.rule.BlacklistHelper;
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
    public Result<List<BlacklistRule>> query() {
        return Result.success(blacklistHelper.query());
    }

    @PostMapping
    public Result<Void> saveOrUpdate(@RequestBody BlacklistRule rule) {
        blacklistHelper.saveOrUpdate(rule);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        blacklistHelper.delete(id);
        return Result.success();
    }

}
