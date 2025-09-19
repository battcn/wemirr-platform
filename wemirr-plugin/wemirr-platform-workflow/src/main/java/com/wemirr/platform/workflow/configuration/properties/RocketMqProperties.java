package com.wemirr.platform.workflow.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * mq 配置类
 * @author battcn
 * @since 2025/5/26
 **/
@Data
@Component
@ConfigurationProperties(prefix = "rocketmq.workflow")
public class RocketMqProperties {

    private String topic = "DEFAULT_TOPIC";

    private Boolean callBack = true;
    private String callBackTopic = "callBackTopic";
}
