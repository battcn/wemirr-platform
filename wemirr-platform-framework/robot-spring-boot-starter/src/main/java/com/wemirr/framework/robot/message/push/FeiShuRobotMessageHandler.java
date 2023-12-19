
package com.wemirr.framework.robot.message.push;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.wemirr.framework.robot.RobotProperties;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * 飞书机器人
 * 文档 <a href="https://open.feishu.cn/document/ukTMukTMukTM/ucTM5YjL3ETO24yNxkjN">在线文档</a>
 *
 * @author Levin
 */
@RequiredArgsConstructor
public class FeiShuRobotMessageHandler implements RobotMessageHandler {


    private static final String OPEN_API_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/%s";

    private final RobotProperties robotProperties;

    @Override
    public String notify(String message) {
        final String secret = robotProperties.getFeiShu().getSecret();
        final long currentTimeMillis = System.currentTimeMillis() / 1000L;
        final String stringToSign = currentTimeMillis + "\n" + secret;
        String sign = SecureUtil.hmacSha256(stringToSign).digestBase64(StrUtil.EMPTY, true);
        final Map<String, Object> body = Map.ofEntries(
                Map.entry("timestamp", currentTimeMillis),
                Map.entry("sign", sign),
                Map.entry("msg_type", "text"),
                Map.entry("content", Map.of("text", message))
        );
        return this.request(body);
    }

    @Override
    public String getUrl() {
        return String.format(OPEN_API_URL, robotProperties.getFeiShu().getKey());
    }
}
