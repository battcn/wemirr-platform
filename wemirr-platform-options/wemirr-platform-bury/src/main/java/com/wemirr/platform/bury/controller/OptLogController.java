package com.wemirr.platform.bury.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.RegionUtils;
import com.wemirr.framework.boot.log.OptLogDTO;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.framework.security.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.bury.domain.entity.OptLog;
import com.wemirr.platform.bury.service.OptLogService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/opt_logs")
@RequiredArgsConstructor
@Validated
@Tag(name = "操作日志", description = "操作日志")
public class OptLogController {

    private final OptLogService optLogService;


    @GetMapping
    @Parameters({
            @Parameter(name = "location", description = "地区", in = ParameterIn.QUERY),
            @Parameter(name = "description", description = "描述信息", in = ParameterIn.QUERY)
    })
    public Result<Page<OptLog>> query(PageRequest request, String location, String description) {
        final Page<OptLog> page = this.optLogService.page(request.buildPage(), Wraps.<OptLog>lbQ()
                .like(OptLog::getLocation, location)
                .like(OptLog::getDescription, description)
                .orderByDesc(OptLog::getStartTime));
        return Result.success(page);
    }

    @PostMapping
    @IgnoreAuthorize
    public Result<ResponseEntity<Void>> save(@RequestBody OptLogDTO dto) {
        log.info("[日志信息] - {}", JSON.toJSONString(dto));
        final OptLog record = BeanUtil.toBean(dto, OptLog.class);
        record.setLocation(RegionUtils.getRegion(dto.getIp()));
        this.optLogService.save(record);
        return Result.success();
    }

}
