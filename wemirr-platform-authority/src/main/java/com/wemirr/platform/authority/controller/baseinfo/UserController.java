package com.wemirr.platform.authority.controller.baseinfo;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.annotation.SysLog;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.UserSaveDTO;
import com.wemirr.platform.authority.domain.dto.UserUpdateDTO;
import com.wemirr.platform.authority.domain.entity.User;
import com.wemirr.platform.authority.domain.vo.UserResp;
import com.wemirr.platform.authority.service.UserService;
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

import java.util.Optional;

import static com.wemirr.platform.authority.domain.converts.UserConverts.USER_DTO_2_PO_CONVERTS;


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

    @GetMapping
    @Parameters({
            @Parameter(description = "账号", name = "username", in = ParameterIn.QUERY),
            @Parameter(description = "名称", name = "nickName", in = ParameterIn.QUERY),
            @Parameter(description = "邮箱", name = "email", in = ParameterIn.QUERY),
            @Parameter(description = "性别", name = "sex", in = ParameterIn.QUERY),
            @Parameter(description = "手机号", name = "mobile", in = ParameterIn.QUERY)
    })
    @Operation(summary = "用户列表 - [Levin] - [DONE]")
    public Result<IPage<UserResp>> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                                         @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                                         String username, String nickName, Integer sex, String email, String mobile) {
        final IPage<UserResp> page = this.userService.findPage(new Page<>(current, size),
                Wraps.<User>lbQ().eq(User::getSex, sex).like(User::getNickName, nickName)
                        .like(User::getUsername, username).like(User::getEmail, email)
                        .like(User::getMobile, mobile));
        return Result.success(page);
    }


    @PostMapping
    @SysLog(value = "添加用户")
    @Operation(summary = "添加用户")
    public Result<ResponseEntity<Void>> save(@Validated @RequestBody UserSaveDTO dto) {
        final User user = BeanUtil.toBean(dto, User.class);
        this.userService.save(user);
        return Result.success();
    }


    @PutMapping("{id}")
    @SysLog(value = "编辑用户")
    @Operation(summary = "编辑用户")
    public Result<ResponseEntity<Void>> edit(@PathVariable Long id, @Validated @RequestBody UserUpdateDTO dto) {
        final User user = USER_DTO_2_PO_CONVERTS.convert(dto);
        user.setId(id);
        this.userService.updateById(user);
        return Result.success();
    }


    @DeleteMapping("{id}")
    @SysLog(value = "删除用户")
    @Operation(summary = "删除用户")
    public Result<ResponseEntity<Void>> del(@PathVariable Long id) {
        final User user = Optional.ofNullable(this.userService.getById(id)).orElseThrow(() -> CheckedException.notFound("用户不存在"));
        if (user.getReadonly()) {
            throw CheckedException.badRequest("内置用户不允许删除");
        }
        this.userService.removeById(id);
        return Result.success();
    }
}
