package com.wemirr.platform.tools.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDatasource;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDatasourceReq;
import com.wemirr.platform.tools.service.DynamicReleaseDatasourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/dynamic_release_datasource")
@RequiredArgsConstructor
@Tag(name = "数据源管理", description = "数据源管理")
@Validated
public class DynamicReleaseDatasourceController {

    private final DynamicReleaseDatasourceService dynamicReleaseDatasourceService;

    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping
    public Result<Page<DynamicReleaseDatasource>> page(PageRequest pageRequest, String database) {
        final Page<DynamicReleaseDatasource> page = dynamicReleaseDatasourceService.page(pageRequest.buildPage(),
                Wraps.<DynamicReleaseDatasource>lbQ().eq(DynamicReleaseDatasource::getDatabase, database));
        return Result.success(page);
    }

    @Operation(summary = "Ping数据库")
    @GetMapping("/{id}/ping")
    public Result<ResponseEntity<Void>> ping(@PathVariable Long id) {
        this.dynamicReleaseDatasourceService.ping(id);
        return Result.success();
    }

    @Deprecated
    @Operation(summary = "获取所有表")
    @GetMapping("/{id}/tables")
    public Result<ResponseEntity<Void>> tables(@PathVariable Long id) {
        List<String> tables = this.dynamicReleaseDatasourceService.tables(id);
        return Result.success();
    }

    @Operation(summary = "添加数据源")
    @PostMapping
    public Result<ResponseEntity<Void>> add(@Validated @RequestBody DynamicReleaseDatasourceReq req) {
        dynamicReleaseDatasourceService.save(BeanUtil.toBean(req, DynamicReleaseDatasource.class));
        return Result.success();
    }

    @Operation(summary = "编辑数据源")
    @PutMapping("/{id}")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody DynamicReleaseDatasourceReq req) {
        final DynamicReleaseDatasource request = BeanUtil.toBean(req, DynamicReleaseDatasource.class);
        request.setId(id);
        dynamicReleaseDatasourceService.updateById(request);
        return Result.success();
    }

    @Operation(summary = "删除数据源")
    @DeleteMapping("/{id}")
    public Result<ResponseEntity<Void>> remove(@PathVariable Long id) {
        dynamicReleaseDatasourceService.removeById(id);
        return Result.success();
    }
}
