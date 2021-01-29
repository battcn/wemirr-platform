package com.wemirr.platform.gateway.rest;

import cn.hutool.core.util.IdUtil;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.gateway.config.LimitHelper;
import com.wemirr.platform.gateway.rest.domain.LimitRule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/gateway/rules/limits")
public class LimitController {

    private final LimitHelper limitHelper;

    @GetMapping
    public Result<List<LimitRule>> query() {
        return Result.success(limitHelper.query());
    }

    @PostMapping
    public Result<String> create(@RequestBody LimitRule rule) {
        String uuid = IdUtil.fastSimpleUUID();
        rule.setId(uuid);
        limitHelper.create(rule);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable String id) {
        limitHelper.delete(id);
        return Result.success();
    }

}
