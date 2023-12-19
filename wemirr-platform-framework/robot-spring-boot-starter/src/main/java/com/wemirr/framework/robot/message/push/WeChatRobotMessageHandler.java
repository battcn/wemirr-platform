
package com.wemirr.framework.robot.message.push;

import cn.hutool.core.util.StrUtil;
import com.wemirr.framework.robot.RobotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

/**
 * <a href="https://work.weixin.qq.com/api/doc/90000/90136/91770">企业微信消息机器人文档</a>
 *
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class WeChatRobotMessageHandler implements RobotMessageHandler {


    public static final String OPEN_API_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s";

    private final RobotProperties robotProperties;

    @Override
    public String notify(String message) {
        Map<String, Object> body = Map.ofEntries(
                Map.entry("msgtype", "text"),
                Map.entry("text", Map.of(
                        "mentioned_list", Collections.singleton("@all"),
                        // 企业微信文本内容，最长不超过2048个字节，必须是utf8编码
                        "content", StrUtil.subWithLength(message, 0, 2048).getBytes(StandardCharsets.UTF_8)
                ))
        );
        return this.request(body);
    }

    @Override
    public String getUrl() {
        return String.format(OPEN_API_URL, robotProperties.getWeChat().getKey());
    }
}
