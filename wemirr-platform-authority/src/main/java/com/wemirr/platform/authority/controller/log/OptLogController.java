package com.wemirr.platform.authority.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.configuration.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.authority.domain.entity.log.OptLog;
import com.wemirr.platform.authority.service.OptLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @Operation(description = "查询日志 - [DONE] - [Levin]")
    public Result<Page<OptLog>> query(PageRequest request, String location, String description) {
        final Page<OptLog> page = this.optLogService.page(request.buildPage(), Wraps.<OptLog>lbQ()
                .like(OptLog::getLocation, location)
                .like(OptLog::getDescription, description).orderByDesc(OptLog::getStartTime));
        return Result.success(page);
    }

    @DeleteMapping("/{day}")
    @Parameters({
            @Parameter(name = "day", description = "天数", in = ParameterIn.PATH),
    })
    @Operation(description = "查询日志 - [DONE] - [Levin]")
    public void batchDelete(@PathVariable Integer day) {
        this.optLogService.remove(Wraps.<OptLog>lbQ().le(OptLog::getStartTime, LocalDateTime.now().plusDays(-day)));
    }


}
