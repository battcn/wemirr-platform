package com.wemirr.platform.authority.controller.baseinfo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataScopeService;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.enums.Sex;
import com.wemirr.platform.authority.domain.baseinfo.req.UserSaveReq;
import com.wemirr.platform.authority.domain.baseinfo.req.UserUpdateReq;
import com.wemirr.platform.authority.service.UserService;
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
    @Parameters({
            @Parameter(description = "账号", name = "username", in = ParameterIn.QUERY),
            @Parameter(description = "名称", name = "nickName", in = ParameterIn.QUERY),
            @Parameter(description = "邮箱", name = "email", in = ParameterIn.QUERY),
            @Parameter(description = "性别", name = "sex", in = ParameterIn.QUERY),
            @Parameter(description = "手机号", name = "mobile", in = ParameterIn.QUERY),
            @Parameter(description = "组织", name = "orgId", in = ParameterIn.QUERY)
    })
    @Operation(summary = "用户列表 - [Levin] - [DONE]")
    @PreAuthorize("hasAuthority('sys:users:page')")
    public IPage<User> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                             @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                             String username, String nickName, Integer sex, String email, Long orgId, String mobile) {
        return this.userService.page(new Page<>(current, size),
                Wraps.<User>lbQ().eq(User::getSex, Sex.of(sex)).eq(User::getOrgId, orgId)
                        .like(User::getNickName, nickName).like(User::getMobile, mobile)
                        .like(User::getUsername, username).like(User::getEmail, email));
    }


    @PostMapping
    @SysLog(description = "添加用户")
    @Operation(summary = "添加用户")
    @PreAuthorize("hasAuthority('sys:users:add')")
    public void save(@Validated @RequestBody UserSaveReq dto) {
        this.userService.addUser(dto);
    }


    @PutMapping("{id}")
    @SysLog(description = "编辑用户")
    @Operation(summary = "编辑用户")
    @PreAuthorize("hasAuthority('sys:users:edit')")
    public void edit(@PathVariable Long id, @Validated @RequestBody UserUpdateReq dto) {
        this.userService.updateById(USER_DTO_2_PO_CONVERTS.convert(dto, id));
    }


    @DeleteMapping("{id}")
    @SysLog(description = "删除用户")
    @Operation(summary = "删除用户")
    @PreAuthorize("hasAuthority('sys:users:remove')")
    public void del(@PathVariable Long id) {
        this.userService.deleteById(id);
    }


    @GetMapping("/{id}/data_permission")
    @Operation(summary = "获取数据权限")
    public void dataPermission(@PathVariable Long id) {
        this.dataScopeService.getDataScopeById(id);
    }


}
