//package com.wemirr.platform.gateway.filter;
//
//
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.crypto.Mode;
//import cn.hutool.crypto.Padding;
//import cn.hutool.crypto.symmetric.AES;
//import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
//import cn.hutool.http.HttpUtil;
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.alibaba.nacos.common.utils.CollectionUtils;
//import com.google.common.net.HttpHeaders;
//import com.oit.gateway.filter.domain.OauthTokenBody;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.util.UriComponentsBuilder;
//import reactor.core.publisher.Mono;
//
//import jakarta.crypto.spec.IvParameterSpec;
//import jakarta.crypto.spec.SecretKeySpec;
//import java.net.URI;
//import java.util.List;
//import java.util.Map;
//
//import static java.nio.charset.StandardCharsets.UTF_8;
//
///**
// * 密码解密工具类
// *
// * @author Levin
// */
//@Slf4j
//@Configuration
//public class SecureDecoderFilterFactory extends AbstractGatewayFilterFactory<SecureDecoderFilterFactory.Config> {
//
//    private static final String KEY_ALGORITHM = "AES";
//
//    private static final String DEFAULT_FILTER_NAME = "SecureDecoderFilter";
//
//    @Override
//    public String name() {
//        return DEFAULT_FILTER_NAME;
//    }
//
//    public SecureDecoderFilterFactory() {
//        super(Config.class);
//    }
//
//
//    private static final String CONTENT = "content";
//    private static final String SWAGGER_UI_HTML = "doc.html";
//    private static AES aes;
//
//    private static AES getInstant(String pass) {
//        if (aes == null) {
//            aes = new AES(Mode.CBC, Padding.PKCS5Padding,
//                    new SecretKeySpec(pass.getBytes(UTF_8), KEY_ALGORITHM),
//                    new IvParameterSpec(pass.getBytes(UTF_8)));
//        }
//        return aes;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//
//            // 不是登录请求，直接向下执行
//            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), config.getUrl())) {
//                return chain.filter(exchange);
//            }
//            // 如果从 doc.html 来的不做加密处理
//            final List<String> referer = request.getHeaders().get("Referer");
//            if (CollectionUtils.isNotEmpty(referer)) {
//                final long count = referer.stream().filter(r -> r.contains(SWAGGER_UI_HTML)).count();
//                if (count > 0) {
//                    return chain.filter(exchange);
//                }
//            }
//            URI uri = exchange.getRequest().getURI();
//            String queryParam = uri.getRawQuery();
//            Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, UTF_8);
//            String content = paramMap.get(CONTENT);
//            String replaceQuery;
//            if (StringUtils.isBlank(content)) {
//                return wrap(exchange, HttpStatus.BAD_REQUEST.value(), "未识别的数据来源");
//            }
//            String decryptStr;
//            try {
//                // base64 解码
//                //final byte[] decode = Base64.decode(content);
//                //final byte[] bytes = HexUtil.decodeHex(new String(decode, StandardCharsets.UTF_8));
//                //final byte[] decrypt = getInstant(config.key).decrypt(bytes);
//                decryptStr = getInstant(config.key).decryptStr(content);
//                //decryptStr = new String(decrypt, StandardCharsets.UTF_8);
//                log.debug("[解密字符串] - [{}]", decryptStr);
//            } catch (Exception e) {
//                // 返回出去全局处理。
//                return wrap(exchange, HttpStatus.BAD_REQUEST.value(), "密码解密失败");
//            }
//            try {
//                final OauthTokenBody object = JSON.parseObject(decryptStr, OauthTokenBody.class);
//                final String string = JSON.toJSONString(object);
//                replaceQuery = HttpUtil.toParams(JSON.parseObject(string));
//            } catch (Exception e) {
//                return wrap(exchange, HttpStatus.BAD_REQUEST.value(), "解密后数据转换错误");
//            }
//            URI newUri = UriComponentsBuilder.fromUri(uri)
//                    .replaceQuery(replaceQuery)
//                    .build(true)
//                    .toUri();
//            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
//            return chain.filter(exchange.mutate().request(newRequest).build());
//        };
//    }
//
//    private Mono<Void> wrap(ServerWebExchange exchange, int messageId, String message) {
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.BAD_REQUEST);
//        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        JSONObject result = new JSONObject();
//        result.put("messageId", messageId);
//        result.put("message", message);
//        result.put("successful", false);
//        result.put("timestamp", System.currentTimeMillis());
//        log.warn("[错误信息] - [{}]", result.toJSONString());
//        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(result))));
//    }
//
//    @Data
//    public static class Config {
//        private String url;
//        private String filed;
//        private String key;
//        private SymmetricAlgorithm type;
//    }
//}
