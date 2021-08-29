package com.wemirr.platform.stp.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.wemirr.platform.stp.utils.SoMap;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Levin
 */
@Data
@RequiredArgsConstructor
public class UserInfoTokenServices {

    private final RestTemplate lbRestTemplate;
    private final SaTokenExtProperties properties;

    public SaResult getUserInfo() {
        final String authorization = SaHolder.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        // 调用Server端接口，查询开放的资源
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, authorization);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = lbRestTemplate.exchange(properties.getTokenInfoUri(), HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
        SoMap so = SoMap.getSoMap().setJsonString(response.getBody());
        System.out.println("返回结果: " + so);
        final SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        System.out.println("tokenInfo" + tokenInfo);
        // code不等于200  代表请求失败
        if (so.getInt("code") != 200) {
            return SaResult.error(so.getString("msg"));
        }
        // 返回相关参数 (data=获取到的资源 )
        SoMap data = so.getMap("data");
        return SaResult.data(data);
    }
}
