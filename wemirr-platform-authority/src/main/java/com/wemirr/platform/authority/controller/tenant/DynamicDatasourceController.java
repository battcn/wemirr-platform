package com.wemirr.platform.authority.controller.tenant;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.req.DynamicDatasourceReq;
import com.wemirr.platform.authority.domain.tenant.entity.DynamicDatasource;
import com.wemirr.platform.authority.service.TenantDatasourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/databases")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
@Validated
public class DynamicDatasourceController {

    private final TenantDatasourceService tenantDatasourceService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Page<DynamicDatasource> page(PageRequest pageRequest, String database, String dbType, Boolean locked) {
        return tenantDatasourceService.page(pageRequest.buildPage(),
                Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getDatabase, database)
                        .eq(DynamicDatasource::getDbType, dbType)
                        .eq(DynamicDatasource::getLocked, locked));
    }

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/active")
    public List<DynamicDatasource> queryActive() {
        return this.tenantDatasourceService.list(Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getLocked, false));
    }

    @Operation(summary = "Ping数据库")
    @GetMapping("/{id}/ping")
    public void ping(@PathVariable Long id) {
        this.tenantDatasourceService.ping(id);

    }

    @Operation(summary = "添加数据源")
    @PostMapping
    public void add(@Validated @RequestBody DynamicDatasourceReq req) {
        tenantDatasourceService.saveOrUpdateDatabase(BeanUtil.toBean(req, DynamicDatasource.class));

    }

    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @Validated @RequestBody DynamicDatasourceReq req) {
        tenantDatasourceService.saveOrUpdateDatabase(BeanUtilPlus.toBean(id, req, DynamicDatasource.class));

    }

    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        tenantDatasourceService.removeDatabaseById(id);

    }
}
