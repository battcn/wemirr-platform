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
    public static final String DING_TALK_PREFIX = PREFIX + ".ding-talk";
    public static final String FEI_SHU_PREFIX = PREFIX + ".fei-shu";
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

        /**
         * 需要再企业微信开发者申请
         */
        private String key;

    }

    @Data
    public static class DingTalk {

        /**
         * 是否启用
         */
        private Boolean enabled = false;

        /**
         * 必填
         */
        private String accessToken;


        /**
         * 没有可以不填
         */
        private String secret;

    }

    @Data
    public static class FeiShu {

        /**
         * 是否启用
         */
        private Boolean enabled = false;

        /**
         * <a href="https://open.feishu.cn/open-apis/bot/v2/hook/后面的就是 KEY"></a>
         */
        private String key;

        private String secret;
    }
}