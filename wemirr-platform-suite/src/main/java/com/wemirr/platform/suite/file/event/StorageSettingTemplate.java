package com.wemirr.platform.suite.file.event;

import com.alibaba.fastjson2.JSON;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StorageSettingTemplate {

    private final StringRedisTemplate redisTemplate;

    public void publish(FileStorageSetting setting, int eventType) {
        log.info("redis publish - {},type -> {}", setting, eventType);
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        Long tenantId = setting.getTenantId();
//        String configKey = String.format(STORAGE_CONFIG_KEY_PREFIX, tenantId);
        String relayPlatform = tenantId + "-" + setting.getId();
        // 构建并发布存储配置更新事件
        StorageSettingEvent event = StorageSettingEvent.builder()
                .tenantId(tenantId)
                .updateType(1)
                .platform(relayPlatform)
//                .configKey(configKey)
                .accessKey(setting.getAccessKey())
                .secretKey(setting.getSecretKey())
                .bucketName(setting.getBucketName())
                .region(setting.getRegion())
                .domain(setting.getDomain())
                .basePath(setting.getBasePath())
                .endPoint(setting.getEndPoint())
                .build();
        if (setting.getStatus()) {
//            redisTemplate.opsForHash().put(STORAGE_SETTING_DEFAULT_SETTING, event.getTenantId(), relayPlatform);
        }
        redisTemplate.convertAndSend(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC, JSON.toJSONString(event));
    }
}
