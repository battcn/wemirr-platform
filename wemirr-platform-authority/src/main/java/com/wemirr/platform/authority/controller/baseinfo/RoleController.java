package com.wemirr.platform.authority.controller.baseinfo;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.mybatis.auth.DataScopeType;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleOrg;
import com.wemirr.platform.authority.domain.entity.baseinfo.RoleRes;
import com.wemirr.platform.authority.domain.req.RoleReq;
import com.wemirr.platform.authority.domain.req.RoleResSaveReq;
import com.wemirr.platform.authority.domain.req.RoleUserReq;
import com.wemirr.platform.authority.domain.resp.RoleDetailResp;
import com.wemirr.platform.authority.domain.resp.RolePermissionResp;
import com.wemirr.platform.authority.domain.resp.RoleResResp;
import com.wemirr.platform.authority.domain.resp.UserRoleResp;
import com.wemirr.platform.authority.service.RoleOrgService;
import com.wemirr.platform.authority.service.RoleResService;
import com.wemirr.platform.authority.service.RoleService;
import com.wemirr.platform.authority.service.UserRoleService;
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

/**
 * 角色管理
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "角色管理", description = "角色管理")
public class RoleController {

    private final TenantEnvironment tenantEnvironment;
    private final RoleService roleService;
    private final RoleResService roleResService;
    private final RoleOrgService roleOrgService;
    private final UserRoleService userRoleService;


    @GetMapping("/query_all")
    @Operation(summary = "角色列表 - [Levin] - [DONE]")
    public List<Role> query() {
        return this.roleService.list();
    }

    @GetMapping
    @Parameters({
            @Parameter(description = "名称", name = "name", in = ParameterIn.QUERY),
    })
    @Operation(summary = "角色列表 - [Levin] - [DONE]")
    @PreAuthorize("hasAuthority('sys:roles:page')")
    public IPage<Role> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                             @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                             String name, Boolean locked, DataScopeType scopeType) {
        return this.roleService.page(new Page<>(current, size), Wraps.<Role>lbQ()
                .eq(Role::getLocked, locked).like(Role::getName, name).eq(Role::getScopeType, scopeType));
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "角色详情")
    public RoleDetailResp details(@PathVariable Long id) {
        Role role = roleService.getById(id);
        RoleDetailResp detail = BeanUtilPlus.toBean(role, RoleDetailResp.class);
        final RoleResResp authority = this.roleResService.findAuthorityIdByRoleId(id);
        detail.setAuthority(authority);
        return detail;
    }

    @PostMapping
    @SysLog(value = "添加角色")
    @Operation(summary = "添加角色")
    @PreAuthorize("hasAuthority('sys:roles:add')")
    public void add(@Validated @RequestBody RoleReq data) {
        roleService.saveRole(tenantEnvironment.userId(), data);
    }

    @PutMapping("/{id}")
    @SysLog(value = "编辑角色")
    @Operation(summary = "编辑角色")
    @PreAuthorize("hasAuthority('sys:roles:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody RoleReq data) {
        roleService.updateRole(id, tenantEnvironment.userId(), data);
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除角色")
    @Operation(summary = "删除角色")
    @PreAuthorize("hasAuthority('sys:roles:remove')")
    public void del(@PathVariable Long id) {
        this.roleService.removeByRoleId(id);
    }

    @Operation(summary = "角色关联的用户")
    @GetMapping("/{roleId}/users")
    public UserRoleResp userByRoleId(@PathVariable Long roleId) {
        return userRoleService.findUserByRoleId(roleId);
    }


    @GetMapping("/{role_id}/resources/permissions")
    @Operation(summary = "资源权限", description = "只能看到自身权限")
    public RolePermissionResp permission(@PathVariable("role_id") Long roleId) {
        return this.roleService.findRolePermissionById(roleId);
    }


    @Operation(summary = "角色关联的资源")
    @GetMapping("/{roleId}/role_res")
    public List<RoleRes> resByRoleId(@PathVariable Long roleId) {
        return roleResService.list(Wraps.<RoleRes>lbQ().eq(RoleRes::getRoleId, roleId));
    }


    @Operation(summary = "角色关联的组织")
    @GetMapping("/{roleId}/role_rog")
    public List<RoleOrg> orgByRoleId(@PathVariable Long roleId) {
        return roleOrgService.list(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
    }


    @Operation(summary = "角色分配操作资源")
    @PostMapping("/{roleId}/authority")
    @PreAuthorize("hasAuthority('sys:roles:distribution:res')")
    public void distributionAuthority(@PathVariable Long roleId, @RequestBody RoleResSaveReq dto) {
        this.roleResService.saveRoleAuthority(dto);

    }

    @Operation(summary = "角色分配用户")
    @PostMapping("/{roleId}/users")
    @PreAuthorize("hasAuthority('sys:roles:distribution:user')")
    public void distributionUser(@PathVariable Long roleId, @RequestBody RoleUserReq dto) {
        this.roleService.saveUserRole(roleId, dto.getUserIdList());
    }


}
