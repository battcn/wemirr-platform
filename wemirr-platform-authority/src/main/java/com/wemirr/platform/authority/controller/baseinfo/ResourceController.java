package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.Resource;
import com.wemirr.platform.authority.domain.baseinfo.req.ResourceQueryReq;
import com.wemirr.platform.authority.domain.baseinfo.req.ResourceSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.ResourcePageResp;
import com.wemirr.platform.authority.domain.baseinfo.resp.VueRouter;
import com.wemirr.platform.authority.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.wemirr.platform.authority.domain.baseinfo.converts.MenuConverts.VUE_ROUTER_2_TREE_NODE_CONVERTS;
import static java.util.stream.Collectors.toList;

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
    private final AuthenticationContext authenticationContext;

    @GetMapping("/router")
    @Operation(summary = "菜单路由", description = "只能看到自身权限")
    public List<Tree<Long>> router(@RequestParam(required = false, defaultValue = "false") Boolean all) {
        List<VueRouter> routers = resourceService.findVisibleResource(ResourceQueryReq.builder().userId(authenticationContext.userId()).build());
        List<TreeNode<Long>> list = routers.stream()
                .filter(router -> all || (router.getType() != null && (router.getType() == 1 || router.getType() == 5)))
                .map(VUE_ROUTER_2_TREE_NODE_CONVERTS::convert).collect(toList());
        return TreeUtil.build(list, 0L);
    }

    @GetMapping
    @Parameters({
            @Parameter(description = "父ID", name = "parentId", in = ParameterIn.QUERY),
            @Parameter(description = "资源类型", name = "type", in = ParameterIn.QUERY),
            @Parameter(description = "名称", name = "name", in = ParameterIn.QUERY),
            @Parameter(description = "类型", name = "type", in = ParameterIn.QUERY),
    })
    @Operation(summary = "资源列表 - [Levin] - [DONE]")
    public IPage<ResourcePageResp> pageList(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                                            @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                                            Long parentId, Integer type) {
        return resourceService.page(new Page<>(current, size), Wraps.<Resource>lbQ().eq(Resource::getParentId, parentId)
                .eq(Resource::getType, type)).convert(x -> BeanUtil.toBean(x, ResourcePageResp.class));
    }

    @GetMapping("/permissions")
    @Operation(summary = "资源码", description = "只能看到自身资源码")
    public List<String> permissions() {
        List<VueRouter> routers = Optional.ofNullable(resourceService.findVisibleResource(ResourceQueryReq.builder()
                .userId(authenticationContext.userId()).build())).orElseGet(Lists::newArrayList);
        return routers.stream().map(VueRouter::getPermission).collect(toList());
    }

    @PostMapping
    @AccessLog(description = "添加资源")
    @Operation(summary = "添加资源")
    @PreAuthorize("hasAuthority('sys:resources:add')")
    public void save(@Validated @RequestBody ResourceSaveReq data) {
        Resource resource = BeanUtil.toBean(data, Resource.class);
        resourceService.addResource(resource);
    }


    @DeleteMapping("/{id}")
    @AccessLog(description = "删除资源")
    @Operation(summary = "删除资源")
    @PreAuthorize("hasAuthority('sys:resources:remove')")
    public void del(@PathVariable Long id) {
        this.resourceService.delResource(id);
    }

    @PutMapping("/{id}")
    @AccessLog(description = "修改资源")
    @Operation(summary = "修改资源")
    @PreAuthorize("hasAuthority('sys:resources:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody ResourceSaveReq data) {
        resourceService.editResourceById(BeanUtilPlus.toBean(id, data, Resource.class));
    }


}
