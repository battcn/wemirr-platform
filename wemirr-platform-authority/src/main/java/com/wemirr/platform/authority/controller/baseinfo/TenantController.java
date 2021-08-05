package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.utils.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.TenantPageDTO;
import com.wemirr.platform.authority.domain.dto.TenantSaveDTO;
import com.wemirr.platform.authority.domain.entity.baseinfo.Tenant;
import com.wemirr.platform.authority.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wemirr.framework.commons.entity.Result.success;

/**
 * 租户管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tenants")
@Tag(name = "租户管理", description = "租户管理")
public class TenantController {

    private final TenantService stationService;

    @GetMapping
    @Operation(summary = "租户列表 - [Levin] - [DONE]")
    public Result<IPage<Tenant>> query(TenantPageDTO params) {
        return Result.success(stationService.page(params.buildPage(), Wraps.<Tenant>lbQ()
                .like(Tenant::getName, params.getName()).eq(Tenant::getCode, params.getCode())
                .eq(Tenant::getProvinceId, params.getProvinceId())
                .eq(Tenant::getCityId, params.getCityId())
                .eq(Tenant::getDistrictId, params.getDistrictId())
                .eq(Tenant::getIndustry, params.getIndustry()).eq(Tenant::getStatus, params.getStatus())
                .eq(Tenant::getType, params.getType())));
    }

    @PostMapping
    @SysLog(value = "添加租户")
    @Operation(summary = "添加租户")
    public Result<ResponseEntity<Void>> add(@Validated @RequestBody TenantSaveDTO dto) {
        stationService.saveOrUpdateTenant(BeanUtil.toBean(dto, Tenant.class));
        return success();
    }

    @PutMapping("/{id}")
    @SysLog(value = "编辑租户")
    @Operation(summary = "编辑租户")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody TenantSaveDTO dto) {
        stationService.saveOrUpdateTenant(BeanUtilPlus.toBean(id, dto, Tenant.class));
        return success();
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除租户")
    @Operation(summary = "删除租户")
    public Result<ResponseEntity<Void>> del(@PathVariable Long id) {
        stationService.removeById(id);
        return success();
    }

}
