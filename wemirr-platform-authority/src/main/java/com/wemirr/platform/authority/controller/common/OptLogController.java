package com.wemirr.platform.authority.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.OptLog;
import com.wemirr.platform.authority.service.OptLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * 操作日志
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/opt_logs")
@Tag(name = "操作日志", description = "操作日志")
@TenantDS
@RequiredArgsConstructor
public class OptLogController {


    private final OptLogService optLogService;

    @GetMapping
    @Parameters({
            @Parameter(name = "location", description = "地区", in = ParameterIn.QUERY),
            @Parameter(name = "description", description = "描述信息", in = ParameterIn.QUERY)
    })
    @Operation(summary = "查询日志 - [DONE] - [Levin]", description = "查询日志 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('log:opt:page')")
    public Page<OptLog> query(PageRequest request, String location, String description) {
        return this.optLogService.page(request.buildPage(), Wraps.<OptLog>lbQ()
                .like(OptLog::getLocation, location)
                .like(OptLog::getDescription, description).orderByDesc(OptLog::getStartTime));
    }

    @DeleteMapping("/{day}")
    @Parameters({
            @Parameter(name = "day", description = "天数", in = ParameterIn.PATH),
    })
    @Operation(summary = "查询日志 - [DONE] - [Levin]", description = "查询日志 - [DONE] - [Levin]")
    public void batchDelete(@PathVariable Integer day) {
        this.optLogService.remove(Wraps.<OptLog>lbQ().le(OptLog::getStartTime, Instant.now().plus(-day, ChronoUnit.DAYS)));
    }


}
