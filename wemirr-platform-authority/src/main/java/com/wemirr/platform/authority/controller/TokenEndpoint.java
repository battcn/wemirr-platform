package com.wemirr.platform.authority.controller;

import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.platform.authority.domain.dto.ChangePasswordDTO;
import com.wemirr.platform.authority.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Iterator;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class TokenEndpoint {

    private final TenantEnvironment tenantEnvironment;
    private final UserService userService;

    @GetMapping("/oauth2/check_token")
    public Authentication checkToken() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/oauth2/userinfo")
    public Object userinfo(HttpServletRequest request, Principal principal) {
        final Iterator<String> iterator = request.getHeaderNames().asIterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            log.info("KEY - {} - VAL - {}", next, request.getHeader(next));
        }
        return principal;
    }

    @ResponseBody
    @PutMapping("/change_password")
    @Operation(summary = "修改密码")
    public void changePassword(@Validated @RequestBody ChangePasswordDTO dto) {
        if (!StringUtils.equals(dto.getPassword(), dto.getConfirmPassword())) {
            throw CheckedException.badRequest("新密码与确认密码不一致");
        }
        final Long userId = tenantEnvironment.userId();
        this.userService.changePassword(userId, dto.getOriginalPassword(), dto.getPassword());
    }


}
