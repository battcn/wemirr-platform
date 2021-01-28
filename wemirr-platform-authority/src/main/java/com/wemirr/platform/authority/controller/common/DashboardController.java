package com.wemirr.platform.authority.controller.common;


import com.wemirr.framework.commons.entity.Result;
import com.wemirr.platform.authority.domain.vo.DashboardResp;
import com.wemirr.platform.authority.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 地区管理
 * </p>
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dashboards")
@Tag(name = "控制面板", description = "控制面板")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @Operation(description = "控制面板 - [DONE] - [Levin]")
    public Result<DashboardResp> query() {
        return Result.success(dashboardService.dashboard());
    }


}
