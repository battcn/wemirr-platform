package com.wemirr.framework.robot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 异常捕获切面处理类
 * <p>
 *
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = RobotProperties.PREFIX)
public class RobotProperties {

    public static final String PREFIX = "extend.robot";
    public static final String WECHAT_PREFIX = PREFIX + ".we-chat";
    public static final String DING_TALK_PREFIX = PREFIX + "ding-talk";
    public static final String FEI_SHU_PREFIX = PREFIX + "fei-shu";
    /**
     * 是否启用
     */
    private Boolean enabled = false;

    /**
     * 企业微信
     */
    private WeChat weChat;

    /**
     * 钉钉
     */
    private DingTalk dingTalk;

    /**
     * 飞书
     */
    private FeiShu feiShu;

    @Data
    public static class WeChat {

        /**
         * 是否启用
         */
        private Boolean enabled = false;

        private String key;

    }

    @Data
    public static class DingTalk {

        /**
         * 是否启用
         */
        private Boolean enabled = false;

        private String accessToken;

        private String secret;

    }

    @Data
    public static class FeiShu {
        /**
         * 是否启用
         */
        private Boolean enabled = false;

        private String key;

        private String secret;
    }
}
