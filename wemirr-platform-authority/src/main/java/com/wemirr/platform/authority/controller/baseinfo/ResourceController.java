package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.wemirr.framework.boot.utils.BeanPlusUtil;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.datasource.TenantEnvironment;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.framework.database.mybatis.conditions.query.LbqWrapper;
import com.wemirr.platform.authority.domain.dto.ResourceQueryDTO;
import com.wemirr.platform.authority.domain.dto.ResourceSaveDTO;
import com.wemirr.platform.authority.domain.entity.Resource;
import com.wemirr.platform.authority.domain.enums.ResourceType;
import com.wemirr.platform.authority.domain.vo.VueRouter;
import com.wemirr.platform.authority.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单资源
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
@Tag(name = "菜单资源", description = "菜单资源")
public class ResourceController {

    private final ResourceService resourceService;
    private final TenantEnvironment tenantEnvironment;

    @GetMapping("/router")
    @Operation(summary = "菜单路由", description = "只能看到自身权限")
    public Result<List<Tree<Long>>> router() {
        List<VueRouter> routers = resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(tenantEnvironment.userId()).build());
        List<TreeNode<Long>> list = routers.stream()
                .filter(router -> router.getType() != null && router.getType() == 1 || router.getType() == 5).map(route -> {
                    TreeNode<Long> node = new TreeNode<>();
                    node.setId(route.getId());
                    node.setParentId(route.getParentId());
                    node.setName(route.getName());
                    Map<String, Object> extra = Maps.newHashMap();
                    extra.put("title", route.getLabel());
                    extra.put("label", route.getLabel());
                    extra.put("path", route.getPath());
                    extra.put("name", route.getLabel());
                    extra.put("component", route.getComponent());
                    extra.put("icon", route.getIcon());
                    extra.put("permission", route.getPermission());
                    extra.put("sequence", route.getSequence());
                    extra.put("type", route.getType());
                    extra.put("model", route.getModel());
                    extra.put("locked", route.getLocked());
                    extra.put("global", route.getGlobal());
                    Map<String, Object> meta = Maps.newHashMap();
                    meta.put("icon", route.getIcon());
                    meta.put("title", route.getLabel());
                    extra.put("meta", meta);
                    node.setExtra(extra);
                    node.setWeight(route.getSequence());
                    return node;
                }).collect(Collectors.toList());
        return Result.success(TreeUtil.build(list, 0L));
    }


    @GetMapping
    @Parameters({
            @Parameter(description = "父ID", name = "parentId", in = ParameterIn.QUERY),
            @Parameter(description = "资源类型", name = "type", in = ParameterIn.QUERY),
            @Parameter(description = "名称", name = "name", in = ParameterIn.QUERY),
    })
    @Operation(summary = "资源列表 - [Levin] - [DONE]")
    public Result<IPage<Resource>> query(Long parentId, Integer type) {
        final LbqWrapper<Resource> wrapper = Wraps.<Resource>lbQ().eq(Resource::getParentId, parentId).eq(Resource::getType, ResourceType.BUTTON);
        IPage<Resource> page = resourceService.page(new Page<>(1, 9999), wrapper);
        return Result.success(page);
    }

    @PostMapping
    @SysLog(value = "添加资源")
    @Operation(summary = "添加资源")
    public Result<ResponseEntity<Void>> save(@Validated @RequestBody ResourceSaveDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        resourceService.addResource(resource);
        return Result.success();
    }


    @DeleteMapping("/{id}")
    @SysLog(value = "删除资源")
    @Operation(summary = "删除资源")
    public Result<ResponseEntity<Void>> del(@PathVariable Long id) {
        this.resourceService.delResource(id);
        return Result.success();
    }

    @PutMapping("/{id}")
    @SysLog(value = "修改资源")
    @Operation(summary = "修改资源")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody ResourceSaveDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        resource.setId(id);
        resourceService.editResourceById(resource);
        return Result.success();
    }


}
