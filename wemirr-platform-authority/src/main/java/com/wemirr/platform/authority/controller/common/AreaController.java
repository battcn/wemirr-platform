package com.wemirr.platform.authority.controller.common;


import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.AreaEntityDTO;
import com.wemirr.platform.authority.domain.entity.common.AreaEntity;
import com.wemirr.platform.authority.domain.vo.AreaNodeResp;
import com.wemirr.platform.authority.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wemirr.platform.authority.domain.converts.AreaConverts.AREA_DTO_2_PO_CONVERTS;
import static com.wemirr.platform.authority.domain.converts.AreaConverts.AREA_ENTITY_2_NODE_RESP_CONVERTS;

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

    /**
     * 查询系统所有的组织树
     */
    @GetMapping("/trees")
    @Operation(summary = "查询地址树", description = "查询地址树")
    public Result<List<Tree<Long>>> tree() {
        List<AreaEntity> list = this.areaService.list(Wraps.<AreaEntity>lbQ().orderByAsc(AreaEntity::getSequence));
        final List<TreeNode<Long>> nodes = list.stream().map(area -> {
            TreeNode<Long> treeNode = new TreeNode<>(area.getId(), area.getParentId(), area.getName(), area.getSequence());
            Map<String, Object> extra = Maps.newLinkedHashMap();
            extra.put("value", area.getId());
            extra.put("label", area.getName());
            extra.put("level", area.getLevel());
            extra.put("longitude", area.getLongitude());
            extra.put("latitude", area.getLatitude());
            treeNode.setExtra(extra);
            return treeNode;
        }).collect(Collectors.toList());
        return Result.success(TreeUtil.build(nodes, 0L));
    }

    @GetMapping("/{parent_id}/children")
    @Operation(summary = "查询子节点 - [DONE] - [Levin]", description = "查询子节点 - [DONE] - [Levin]")
    public Result<List<AreaNodeResp>> list(@PathVariable(name = "parent_id") Integer parentId) {
        final List<AreaEntity> list = this.areaService.listArea(parentId);
        return Result.success(AREA_ENTITY_2_NODE_RESP_CONVERTS.converts(list));
    }

    @PostMapping
    @Parameter(name = "id", description = "国标码", in = ParameterIn.PATH)
    @Operation(summary = "保存地址 - [DONE] - [Levin]", description = "保存地址 - [DONE] - [Levin]")
    public void save(@Validated @RequestBody AreaEntityDTO dto) {
        this.areaService.saveOrUpdateArea(AREA_DTO_2_PO_CONVERTS.convert(dto));
        
    }

    @DeleteMapping
    @Operation(summary = "批量删除 - [DONE] - [Levin]", description = "批量删除 - [DONE] - [Levin]")
    public void batchDel(@RequestBody List<Long> ids) {
        this.areaService.removeByIds(ids);
        
    }

    @DeleteMapping("/{id}")
    @Parameter(name = "id", description = "国标码", in = ParameterIn.PATH)
    @Operation(summary = "删除地址 - [DONE] - [Levin]", description = "删除地址 - [DONE] - [Levin]")
    public void del(@PathVariable Integer id) {
        this.areaService.removeById(id);
        
    }
}
