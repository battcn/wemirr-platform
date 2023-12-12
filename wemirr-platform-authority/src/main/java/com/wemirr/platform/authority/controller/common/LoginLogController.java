package com.wemirr.platform.authority.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.datascope.util.DataPermissionUtils;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.common.entity.LoginLog;
import com.wemirr.platform.authority.domain.common.req.LoginLogPageReq;
import com.wemirr.platform.authority.service.LoginLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/login_logs")
@Tag(name = "登录日志", description = "登录日志")
@RequiredArgsConstructor
public class LoginLogController {

    private final LoginLogService loginLogService;

    @GetMapping
    @Operation(summary = "查询日志 - [DONE] - [Levin]", description = "查询日志 - [DONE] - [Levin]")
    @PreAuthorize("hasAuthority('log:login:page')")
    public Page<LoginLog> page(LoginLogPageReq req) {
        return DataPermissionUtils.executeDefaultDataPermissionRule(() ->
                loginLogService.page(req.buildPage(), Wraps.<LoginLog>lbQ().like(LoginLog::getName, req.getName())
                        .like(LoginLog::getPrincipal, req.getPrincipal())
                        .eq(LoginLog::getPlatform, req.getPlatform())));
    }


}
