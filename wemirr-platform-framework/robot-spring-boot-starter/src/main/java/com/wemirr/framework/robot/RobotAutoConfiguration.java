
package com.wemirr.framework.robot;

import com.wemirr.framework.robot.handler.DefaultIErrorMessageHandler;
import com.wemirr.framework.robot.handler.IErrorMessageHandler;
import com.wemirr.framework.robot.message.exception.DefaultRobotExceptionMessage;
import com.wemirr.framework.robot.message.exception.IRobotExceptionMessage;
import com.wemirr.framework.robot.message.exception.RobotExceptionNotifyAspect;
import com.wemirr.framework.robot.message.push.DingTalkRobotMessageHandler;
import com.wemirr.framework.robot.message.push.FeiShuRobotMessageHandler;
import com.wemirr.framework.robot.message.push.RobotMessageHandler;
import com.wemirr.framework.robot.message.push.WeChatRobotMessageHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 机器人消息推送依赖注入
 * <p>
 *
 * @author Levin
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({RobotProperties.class})
public class RobotAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean
    public IErrorMessageHandler errorMessageHandler() {
        return new DefaultIErrorMessageHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultRobotExceptionMessage robotSendException(List<RobotMessageHandler> robotMessageTemplateList, IErrorMessageHandler IErrorMessageHandler) {
        return new DefaultRobotExceptionMessage(robotMessageTemplateList, IErrorMessageHandler);
    }

    @Bean
    @ConditionalOnProperty(prefix = RobotProperties.PREFIX, name = "enabled", havingValue = "true")
    public RobotExceptionNotifyAspect exceptionAspect(IRobotExceptionMessage sendException) {
        return new RobotExceptionNotifyAspect(sendException);
    }

    /**
     * 钉钉
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = RobotProperties.DING_TALK_PREFIX, name = "enabled", havingValue = "true")
    public RobotMessageHandler dingTalkRobotMessageHandler(RobotProperties robotProperties) {
        return new DingTalkRobotMessageHandler(robotProperties);
    }

    /**
     * 企业微信
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = RobotProperties.WECHAT_PREFIX, name = "enabled", havingValue = "true")
    public RobotMessageHandler weChatRobotMessageHandler(RobotProperties robotProperties) {
        return new WeChatRobotMessageHandler(robotProperties);
    }

    /**
     * 飞书
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = RobotProperties.FEI_SHU_PREFIX, name = "enabled", havingValue = "true")
    public RobotMessageHandler feiShuRobotMessageHandler(RobotProperties robotProperties) {
        return new FeiShuRobotMessageHandler(robotProperties);
    }
}