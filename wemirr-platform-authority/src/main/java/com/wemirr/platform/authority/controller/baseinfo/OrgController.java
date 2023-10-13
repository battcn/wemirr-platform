package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.Org;
import com.wemirr.platform.authority.domain.baseinfo.req.OrgSaveReq;
import com.wemirr.platform.authority.service.OrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 组织架构
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/org")
@RequiredArgsConstructor
@Tag(name = "组织架构", description = "组织架构")
public class OrgController {

    private final OrgService orgService;

    /**
     * 查询系统所有的组织树
     */
    @GetMapping("/trees")
    @Operation(summary = "查询系统所有的组织树", description = "查询系统所有的组织树")
    public List<Tree<Long>> tree(String name, Boolean status) {
        List<Org> list = this.orgService.list(Wraps.<Org>lbQ().like(Org::getLabel, name).eq(Org::getStatus, status).orderByAsc(Org::getSequence));
        final List<TreeNode<Long>> nodes = list.stream().map(org -> {
            TreeNode<Long> treeNode = new TreeNode<>(org.getId(), org.getParentId(), org.getLabel(), org.getSequence());
            Map<String, Object> extra = Maps.newLinkedHashMap();
            extra.put("label", org.getLabel());
            extra.put("alias", org.getAlias());
            extra.put("status", org.getStatus());
            extra.put("sequence", org.getSequence());
            extra.put("tel", org.getTel());
            extra.put("description", org.getDescription());
            treeNode.setExtra(extra);
            return treeNode;
        }).collect(Collectors.toList());
        return TreeUtil.build(nodes, 0L);
    }

    @PostMapping
    @AccessLog(description = "保存组织架构")
    @Operation(summary = "保存编辑组织架构")
    @PreAuthorize("hasAuthority('sys:org:add')")
    public void save(@Validated @RequestBody OrgSaveReq dto) {
        orgService.addOrg(BeanUtil.toBean(dto, Org.class));
    }

    @PutMapping("/{id}")
    @AccessLog(description = "编辑组织架构")
    @Operation(summary = "编辑编辑组织架构")
    @PreAuthorize("hasAuthority('sys:org:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody OrgSaveReq dto) {
        orgService.updateById(BeanUtilPlus.toBean(id, dto, Org.class));
    }

    @DeleteMapping("/{id}")
    @AccessLog(description = "删除组织架构")
    @Operation(summary = "删除组织架构")
    @PreAuthorize("hasAuthority('sys:org:remove')")
    public void del(@PathVariable Long id) {
        orgService.remove(id);
    }


}
