package com.wemirr.platform.authority.controller.log;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.authority.domain.entity.log.LoginLog;
import com.wemirr.platform.authority.service.LoginLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Parameters({
            @Parameter(name = "name", description = "名称", in = ParameterIn.QUERY),
            @Parameter(name = "principal", description = "账号", in = ParameterIn.QUERY)
    })
    @Operation(summary = "查询日志 - [DONE] - [Levin]", description = "查询日志 - [DONE] - [Levin]")
    public Result<Page<LoginLog>> query(PageRequest request, String name, String principal) {
        final Page<LoginLog> page = this.loginLogService.page(request.buildPage(), Wraps.<LoginLog>lbQ()
                .like(LoginLog::getName, name)
                .like(LoginLog::getPrincipal, principal).orderByDesc(LoginLog::getCreatedTime));
        return Result.success(page);
    }


}
