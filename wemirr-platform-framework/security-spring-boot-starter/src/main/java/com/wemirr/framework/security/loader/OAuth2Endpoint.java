//package com.wemirr.framework.security.endpoint;
//
//import cn.dev33.satoken.context.SaHolder;
//import cn.dev33.satoken.oauth2.config.SaOAuth2ServerConfig;
//import cn.dev33.satoken.oauth2.consts.GrantType;
//import cn.dev33.satoken.oauth2.data.model.loader.SaClientModel;
//import cn.dev33.satoken.oauth2.processor.SaOAuth2ServerProcessor;
//import cn.dev33.satoken.oauth2.strategy.SaOAuth2Strategy;
//import cn.dev33.satoken.stp.StpUtil;
//import cn.dev33.satoken.util.SaFoxUtil;
//import cn.dev33.satoken.util.SaResult;
//import com.wemirr.framework.commons.annotation.IgnoreGlobalResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Sa-Token OAuth2 Server端 控制器
// *
// * @author battcn
// */
//@Slf4j
//@RestController
//@ConditionalOnExpression("${extend.security.enabled-oauth2: false}")
//public class OAuth2Endpoint {
//
//    /**
//     * OAuth2-Server 端：处理所有 OAuth2 相关请求
//     *
//     * @return Object
//     */
//    @IgnoreGlobalResponse(description = "oauth2.0")
//    @RequestMapping("/oauth2/*")
//    public Object request() {
//        log.debug("enabled oauth2 request url => {}", SaHolder.getRequest().getUrl());
//        return SaOAuth2ServerProcessor.instance.dister();
//    }
//
//    /**
//     * Sa-Token OAuth2 定制化配置
//     *
//     * @param oauth2Server oauth2Server
//     */
//    @Autowired
//    public void configOauth2Server(SaOAuth2ServerConfig oauth2Server) {
//        // 重写 AccessToken 创建策略，返回会话令牌
//        SaOAuth2Strategy.instance.createAccessToken = (clientId, loginId, scopes) -> {
//            log.debug("createAccessToken, clientId => {}, loginId => {}, scopes => {}", clientId, loginId, scopes);
//            return StpUtil.getOrCreateLoginSession(loginId);
//        };
//
//        // 添加 client 信息
//        // todo 后续用 JDBC 加载
//        oauth2Server.addClient(
//                new SaClientModel()
//                        .setClientId("client-id")    // client id
//                        .setClientSecret("client-secret")    // client 秘钥
//                        .addAllowRedirectUris("*")    // 所有允许授权的 url
//                        .addContractScopes("openid", "userid", "userinfo")    // 所有签约的权限
//                        .addAllowGrantTypes(     // 所有允许的授权模式
//                                GrantType.authorization_code, // 授权码式
//                                GrantType.implicit,  // 隐式式
//                                GrantType.refresh_token,  // 刷新令牌
//                                GrantType.password,  // 密码式
//                                GrantType.client_credentials  // 客户端模式
//                        )
//        );
//
//        // 可以添加更多 client 信息，只要保持 clientId 唯一就行了
//        // oauth2Server.addClient(...)
//
//        // 配置：未登录时返回的View
//        oauth2Server.notLoginView = () -> "当前会话在OAuth-Server端尚未登录，请先访问"
//                + "<a href='/oauth2/doLogin?name=sa&pwd=123456' target='_blank'> doLogin登录 </a>"
//                + "进行登录之后，刷新页面开始授权";
//
//
//        // 配置：登录处理函数
//        // todo 后续用 JDBC 加载
//        oauth2Server.doLoginHandle = (name, pwd) -> {
//            if ("sa".equals(name) && "123456".equals(pwd)) {
//                StpUtil.login(10001);
//                return SaResult.ok();
//            }
//            return SaResult.error("账号名或密码错误");
//        };
//
//        // 配置：确认授权时返回的 view
//        oauth2Server.confirmView = (clientId, scopes) -> {
//            String scopeStr = SaFoxUtil.convertListToString(scopes);
//            String yesCode =
//                    "fetch('/oauth2/doConfirm?client_id=" + clientId + "&scope=" + scopeStr + "', {method: 'POST'})" +
//                            ".then(res => res.json())" +
//                            ".then(res => location.reload())";
//            return "<p>应用 " + clientId + " 请求授权：" + scopeStr + "，是否同意？</p>"
//                    + "<p>" +
//                    "        <button onclick=\"" + yesCode + "\">同意</button>" +
//                    "        <button onclick='history.back()'>拒绝</button>" +
//                    "</p>";
//        };
//    }
//
//}