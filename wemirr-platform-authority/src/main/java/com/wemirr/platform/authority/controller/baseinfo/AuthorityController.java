package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataPermission;
import com.wemirr.framework.db.mybatisplus.intercept.data.DataScopeService;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.authority.domain.entity.baseinfo.Role;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.resp.DictResp;
import com.wemirr.platform.authority.service.RoleService;
import com.wemirr.platform.authority.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 用户管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "用户角色", description = "用户角色")
public class AuthorityController {

    private final UserService userService;
    private final RoleService roleService;

    private static final int RECEIVER_TYPE_1 = 1;
    private static final int RECEIVER_TYPE_2 = 2;


    @GetMapping("/list/{receiver_type}/users_or_roles")
    public List<DictResp> list(@PathVariable("receiver_type") Integer receiverType) {
        List<DictResp> result = Lists.newArrayList();
        // 查询角色
        if (receiverType == RECEIVER_TYPE_1) {
            final List<User> users = this.userService.list(new DataPermission());
            if (CollectionUtil.isNotEmpty(users)) {
                result = users.stream().map(user -> DictResp.builder()
                        .label(user.getNickName()).value(user.getId()).build()).collect(toList());
            }
        } else if (receiverType == RECEIVER_TYPE_2) {
            final List<Role> roles = this.roleService.list(new DataPermission());
            if (CollectionUtil.isNotEmpty(roles)) {
                result = roles.stream().map(role -> DictResp.builder()
                        .label(role.getName()).value(role.getId()).build()).collect(toList());
            }
        }
        return result;
    }

    private final DataScopeService dataScopeService;

    @IgnoreAuthorize
    @GetMapping("/list")
    public DataPermission list() {
        return dataScopeService.getDataScopeById(7L);
    }


}
