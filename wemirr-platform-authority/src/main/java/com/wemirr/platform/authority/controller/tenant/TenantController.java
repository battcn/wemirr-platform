package com.wemirr.platform.authority.controller.tenant;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.redis.plus.anontation.RedisLock;
import com.wemirr.framework.redis.plus.anontation.RedisParam;
import com.wemirr.platform.authority.domain.entity.tenant.Tenant;
import com.wemirr.platform.authority.domain.entity.tenant.TenantConfig;
import com.wemirr.platform.authority.domain.req.TenantConfigReq;
import com.wemirr.platform.authority.domain.req.TenantPageReq;
import com.wemirr.platform.authority.domain.req.TenantSaveReq;
import com.wemirr.platform.authority.domain.resp.TenantDatasourceResp;
import com.wemirr.platform.authority.service.TenantDatasourceService;
import com.wemirr.platform.authority.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    @Operation(summary = "租户列表 - [Levin] - [DONE]")
    public IPage<Tenant> query(TenantPageReq params) {
        return tenantService.page(params.buildPage(), Wraps.<Tenant>lbQ()
                .like(Tenant::getName, params.getName()).eq(Tenant::getCode, params.getCode())
                .eq(Tenant::getProvinceId, params.getProvinceId())
                .eq(Tenant::getCityId, params.getCityId())
                .eq(Tenant::getDistrictId, params.getDistrictId())
                .eq(Tenant::getIndustry, params.getIndustry()).eq(Tenant::getStatus, params.getStatus())
                .eq(Tenant::getType, params.getType()));
    }

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/databases/active")
    public List<TenantDatasourceResp> queryActive() {
        return this.dynamicDatasourceService.selectTenantDynamicDatasource();
    }


    @PostMapping
    @SysLog(value = "添加租户")
    @Operation(summary = "添加租户")
    public void add(@Validated @RequestBody TenantSaveReq dto) {
        tenantService.saveOrUpdateTenant(BeanUtil.toBean(dto, Tenant.class));
    }

    @PutMapping("/{id}")
    @SysLog(value = "编辑租户")
    @Operation(summary = "编辑租户")
    public void edit(@PathVariable Long id, @Validated @RequestBody TenantSaveReq dto) {
        tenantService.saveOrUpdateTenant(BeanUtilPlus.toBean(id, dto, Tenant.class));
    }

    @PutMapping("/{id}/config")
    @SysLog(value = "配置租户")
    @Operation(summary = "配置租户")
    public void config(@PathVariable Long id, @Validated @RequestBody TenantConfigReq dto) {
        tenantService.tenantConfig(TenantConfig.builder().tenantId(id).dynamicDatasourceId(dto.getDynamicDatasourceId()).build());
    }

    @PutMapping("/{id}/init_sql_script")
    @SysLog(value = "加载初始数据")
    @Operation(summary = "加载初始数据")
    @RedisLock(prefix = "tenants:init_sql_script")
    public void initSqlScript(@RedisParam(name = "id") @PathVariable Long id) {
        tenantService.initSqlScript(id);
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除租户")
    @Operation(summary = "删除租户")
    public void del(@PathVariable Long id) {
        tenantService.removeById(id);
    }

}
