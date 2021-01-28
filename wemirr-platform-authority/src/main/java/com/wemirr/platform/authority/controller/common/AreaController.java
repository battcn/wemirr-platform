package com.wemirr.platform.authority.controller.common;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.AreaEntityDTO;
import com.wemirr.platform.authority.domain.entity.common.AreaEntity;
import com.wemirr.platform.authority.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wemirr.platform.authority.domain.converts.AreaConverts.AREA_DTO_2_PO_CONVERTS;

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
@RequestMapping("/areas")
@Tag(name = "地区管理", description = "地区管理")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping
    @Operation(description = "分页查询 - [DONE] - [Levin]")
    public Result<Page<AreaEntity>> query(PageRequest request, String name) {
        final Page<AreaEntity> page = this.areaService.page(request.buildPage(),
                Wraps.<AreaEntity>lbQ()
                        .eq(AreaEntity::getParentId, 0)
                        .like(AreaEntity::getName, name)
                        .orderByAsc(AreaEntity::getSequence));
        return Result.success(page);
    }

    @GetMapping("/{parent_id}/children")
    @Operation(description = "查询子节点 - [DONE] - [Levin]")
    public Result<List<AreaEntity>> list(@PathVariable(name = "parent_id") Integer parentId) {
        final List<AreaEntity> list = this.areaService.listArea(parentId);
        return Result.success(list);
    }


    @PutMapping("/{id}")
    @Parameter(name = "id", description = "国标码", in = ParameterIn.PATH)
    @Operation(description = "编辑地址 - [DONE] - [Levin]")
    public Result<ResponseEntity<Void>> edit(@PathVariable Integer id, @Validated @RequestBody AreaEntityDTO dto) {
        final AreaEntity bean = BeanUtil.toBean(dto, AreaEntity.class);
        bean.setId(id);
        this.areaService.updateById(bean);
        return Result.success();
    }

    @PostMapping
    @Parameter(name = "id", description = "国标码", in = ParameterIn.PATH)
    @Operation(description = "保存地址 - [DONE] - [Levin]")
    public Result<ResponseEntity<Void>> save(@Validated @RequestBody AreaEntityDTO dto) {
        this.areaService.save(AREA_DTO_2_PO_CONVERTS.convert(dto));
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Parameter(name = "id", description = "国标码", in = ParameterIn.PATH)
    @Operation(description = "删除地址 - [DONE] - [Levin]")
    public Result<ResponseEntity<Void>> del(@PathVariable Integer id) {
        this.areaService.removeById(id);
        return Result.success();
    }
}
