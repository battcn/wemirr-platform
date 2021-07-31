package com.wemirr.platform.authority.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.baseinfo.OAuthClientDetails;
import com.wemirr.platform.authority.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
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
 * 应用管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
@Tag(name = "应用管理", description = "应用管理")
public class ApplicationController {


    private final ApplicationService applicationService;

    @GetMapping
    @Parameters({
            @Parameter(description = "应用ID", name = "clientId", in = ParameterIn.QUERY),
            @Parameter(description = "应用名称", name = "clientName", in = ParameterIn.QUERY),
    })
    @Operation(summary = "应用列表 - [Levin] - [DONE]")
    public Result<IPage<OAuthClientDetails>> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                                                   @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                                                   String clientId, String clientName) {
        final IPage<OAuthClientDetails> page = this.applicationService.page(new Page<>(current, size),
                Wraps.<OAuthClientDetails>lbQ().like(OAuthClientDetails::getClientId, clientId)
                        .like(OAuthClientDetails::getClientName, clientName));
        return Result.success(page);
    }

    @PostMapping
    @SysLog(value = "添加应用")
    @Operation(summary = "添加应用")
    public Result<ResponseEntity<Void>> save(@Validated @RequestBody OAuthClientDetails dto) {
        final int count = this.applicationService.count(Wraps.<OAuthClientDetails>lbQ().eq(OAuthClientDetails::getClientId, dto.getClientId()));
        if (count > 0) {
            throw CheckedException.badRequest("客户ID已存在");
        }
        this.applicationService.save(dto);
        return Result.success();
    }

    @PutMapping("/{id}")
    @SysLog(value = "修改应用")
    @Operation(summary = "修改应用")
    public Result<ResponseEntity<Void>> edit(@Validated @RequestBody OAuthClientDetails dto) {
        this.applicationService.updateById(dto);
        return Result.success();
    }

    @DeleteMapping("{id}")
    @SysLog(value = "删除应用")
    @Operation(summary = "删除应用")
    public Result<ResponseEntity<Void>> del(@PathVariable String id) {
        this.applicationService.removeById(id);
        return Result.success();
    }


}
