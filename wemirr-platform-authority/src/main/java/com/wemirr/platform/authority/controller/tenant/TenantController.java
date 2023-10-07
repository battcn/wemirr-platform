package com.wemirr.platform.authority.controller.tenant;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.redis.plus.anontation.RedisParam;
import com.wemirr.platform.authority.domain.tenant.entity.Tenant;
import com.wemirr.platform.authority.domain.tenant.entity.TenantConfig;
import com.wemirr.platform.authority.domain.tenant.req.TenantConfigReq;
import com.wemirr.platform.authority.domain.tenant.req.TenantPageReq;
import com.wemirr.platform.authority.domain.tenant.req.TenantSaveReq;
import com.wemirr.platform.authority.domain.tenant.resp.TenantDatasourceResp;
import com.wemirr.platform.authority.domain.tenant.resp.TenantPageResp;
import com.wemirr.platform.authority.service.TenantDatasourceService;
import com.wemirr.platform.authority.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final TenantService tenantService;
    private final TenantDatasourceService dynamicDatasourceService;

    @PostMapping("/page")
    @Operation(summary = "租户列表 - [Levin] - [DONE]")
    @PreAuthorize("hasAuthority('tenant:page')")
    public IPage<TenantPageResp> query(TenantPageReq req) {
        return tenantService.page(req.buildPage(), Wraps.<Tenant>lbQ()
                .like(Tenant::getName, req.getName()).eq(Tenant::getCode, req.getCode())
                .eq(Tenant::getProvinceId, req.getProvinceId())
                .eq(Tenant::getCityId, req.getCityId())
                .eq(Tenant::getDistrictId, req.getDistrictId())
                .eq(Tenant::getIndustry, req.getIndustry()).eq(Tenant::getStatus, req.getStatus())
                .eq(Tenant::getType, req.getType())).convert(x -> BeanUtil.toBean(x, TenantPageResp.class));
    }

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/databases/active")
    public List<TenantDatasourceResp> queryActive() {
        return this.dynamicDatasourceService.selectTenantDynamicDatasource();
    }


    @PostMapping
    @SysLog(description = "添加租户")
    @Operation(summary = "添加租户")
    @PreAuthorize("hasAuthority('tenant:add')")
    public void add(@Validated @RequestBody TenantSaveReq dto) {
        tenantService.saveOrUpdateTenant(BeanUtil.toBean(dto, Tenant.class));
    }

    @PutMapping("/{id}")
    @SysLog(description = "编辑租户")
    @Operation(summary = "编辑租户")
    @PreAuthorize("hasAuthority('tenant:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody TenantSaveReq dto) {
        tenantService.saveOrUpdateTenant(BeanUtilPlus.toBean(id, dto, Tenant.class));
    }

    @PutMapping("/{id}/config")
    @SysLog(description = "配置租户")
    @Operation(summary = "配置租户")
    @PreAuthorize("hasAuthority('tenant:config')")
    public void config(@PathVariable Long id, @Validated @RequestBody TenantConfigReq dto) {
        tenantService.tenantConfig(TenantConfig.builder().tenantId(id).dynamicDatasourceId(dto.getDynamicDatasourceId()).build());
    }

    @PutMapping("/{id}/init_sql_script")
    @SysLog(description = "加载初始数据")
    @Operation(summary = "加载初始数据")
    @RedisLock(prefix = "tenants:init_sql_script")
    public void initSqlScript(@RedisParam(name = "id") @PathVariable Long id) {
        tenantService.initSqlScript(id);
    }

    @DeleteMapping("/{id}")
    @SysLog(description = "删除租户")
    @Operation(summary = "删除租户")
    @PreAuthorize("hasAuthority('tenant:remove')")
    public void del(@PathVariable Long id) {
        tenantService.removeById(id);
    }

}
