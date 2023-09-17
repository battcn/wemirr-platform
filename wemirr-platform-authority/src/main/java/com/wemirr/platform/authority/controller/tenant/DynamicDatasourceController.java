package com.wemirr.platform.authority.controller.tenant;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.authority.domain.entity.tenant.DynamicDatasource;
import com.wemirr.platform.authority.domain.req.DynamicDatasourceReq;
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

    private final TenantDatasourceService dynamicDatasourceService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Page<DynamicDatasource> page(PageRequest pageRequest, String database, String dbType, Boolean locked) {
        return dynamicDatasourceService.page(pageRequest.buildPage(),
                Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getDatabase, database)
                        .eq(DynamicDatasource::getDbType, dbType)
                        .eq(DynamicDatasource::getLocked, locked));
    }

    @Operation(summary = "查询可用", description = "查询可用数据源")
    @GetMapping("/active")
    public List<DynamicDatasource> queryActive() {
        return this.dynamicDatasourceService.list(Wraps.<DynamicDatasource>lbQ().eq(DynamicDatasource::getLocked, false));
    }

    @Operation(summary = "Ping数据库")
    @GetMapping("/{id}/ping")
    public void ping(@PathVariable Long id) {
        this.dynamicDatasourceService.ping(id);

    }

    @Operation(summary = "添加数据源")
    @PostMapping
    public void add(@Validated @RequestBody DynamicDatasourceReq req) {
        dynamicDatasourceService.saveOrUpdateDatabase(BeanUtil.toBean(req, DynamicDatasource.class));

    }

    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @Validated @RequestBody DynamicDatasourceReq req) {
        dynamicDatasourceService.saveOrUpdateDatabase(BeanUtilPlus.toBean(id, req, DynamicDatasource.class));

    }

    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        dynamicDatasourceService.removeDatabaseById(id);

    }
}
