
package com.wemirr.framework.robot.message.push;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 发送消息接口
 *
 * @author Levin
 */
public interface RobotMessageHandler {

    /**
     * 消息通知
     *
     * @param message 消息内容
     */
    String notify(String message);


    String getUrl();


    default String request(Map<String, Object> body) {
        RestTemplate template = SpringUtil.getBean(RestTemplate.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return template.postForEntity(this.getUrl(), new HttpEntity<>(body, headers), String.class).getBody();
    }


}
