package com.wemirr.platform.gateway;

import com.alibaba.fastjson.JSON;
import com.wemirr.platform.gateway.rest.domain.LimitRule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class GatewayApplicationTests {

    @Test
    void contextLoads() {
        LimitRule rule = new LimitRule();
        rule.setBlacklist(true);
        rule.setCreatedTime(LocalDateTime.now());
        rule.setDescription("测试gateway限流");
        rule.setPath("/gateway/*");
        rule.setTotal(1L);
        System.out.println(JSON.toJSONString(rule));

    }

}
