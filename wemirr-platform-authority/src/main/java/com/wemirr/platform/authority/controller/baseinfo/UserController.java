package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.MapHelper;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.db.mybatisplus.datascope.service.DataScopeService;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.req.UserPageReq;
import com.wemirr.platform.authority.domain.baseinfo.req.UserSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.req.UserUpdateReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.UserResp;
import com.wemirr.platform.authority.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.wemirr.platform.authority.domain.baseinfo.converts.UserConverts.USER_DTO_2_PO_CONVERTS;


/**
 * 用户管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "用户管理", description = "用户管理")
public class UserController {

    private final UserService userService;
    private final DataScopeService dataScopeService;

    @GetMapping
    @Operation(summary = "用户列表 - [Levin] - [DONE]")
    @PreAuthorize("hasAuthority('sys:user:page')")
    public IPage<UserResp> page(UserPageReq req) {
        return this.userService.pageList(req);
    }


    @PostMapping
    @AccessLog(description = "添加用户")
    @Operation(summary = "添加用户")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public void save(@Validated @RequestBody UserSaveReq dto) {
        this.userService.addUser(dto);
    }


    @PutMapping("{id}")
    @AccessLog(description = "编辑用户")
    @Operation(summary = "编辑用户")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody UserUpdateReq dto) {
        this.userService.updateById(USER_DTO_2_PO_CONVERTS.convert(dto, id));
    }


    @DeleteMapping("{id}")
    @AccessLog(description = "删除用户")
    @Operation(summary = "删除用户")
    @PreAuthorize("hasAuthority('sys:user:remove')")
    public void del(@PathVariable Long id) {
        this.userService.deleteById(id);
    }

    @PostMapping("/batch_ids")
    @Operation(summary = "ID批量查询")
    public Map<Long, UserResp> batchIds(@RequestBody Set<Long> ids) {
        final List<User> users = this.userService.listByIds(ids);
        return MapHelper.toHashMap(users, Entity::getId, x -> BeanUtil.toBean(x, UserResp.class));
    }


    @GetMapping("/{id}/data_permission")
    @Operation(summary = "获取数据权限")
    public void dataPermission(@PathVariable Long id) {
        this.dataScopeService.getDataScopeById(id);
    }

}
