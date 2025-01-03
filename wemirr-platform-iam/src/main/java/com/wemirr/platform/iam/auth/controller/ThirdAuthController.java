package com.wemirr.platform.iam.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.wemirr.platform.iam.auth.service.ThirdAuthTemplate;
import com.wemirr.platform.iam.system.domain.dto.resp.ThirdAuthResp;
import com.wemirr.platform.iam.system.domain.enums.ThirdAuthType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author levin
 */
@SaIgnore
@RestController
@RequestMapping("/third-auth")
@RequiredArgsConstructor
@Tag(name = "三方授权", description = "第三方授权管理")
public class ThirdAuthController {

    private final ThirdAuthTemplate thirdAuthTemplate;

    @GetMapping("/{type}")
    public ThirdAuthResp authorize(@PathVariable String type) {
        return thirdAuthTemplate.authorize(ThirdAuthType.of(type));
    }

    @SneakyThrows
    @GetMapping("/{type}/callback")
    public AuthUser callback(HttpServletResponse response, @PathVariable String type, AuthCallback callback) {
        AuthUser user = thirdAuthTemplate.callback(ThirdAuthType.of(type), callback);
        // TODO 如何更加灵活,还在思考中
        // TODO 租户/租户绑定域名后,这地方是否需要动态配置,返回的 POJO 需要改进,应该只返回基本信息以及基本租户信息才行
        // response.sendRedirect("http://localhost:5666/auth/login?accountId=" + user.getUuid() + "&platform=" + type + "&tenantCode=0000");
        response.sendRedirect("https://cloud.battcn.com?accountId=" + user.getUuid() + "&platform=" + type + "&tenantCode=0000");
        return user;
    }
}
