package com.wemirr.platform.suite.file.event;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import com.wemirr.platform.suite.file.repository.FileStorageSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StorageSettingTemplate {

    private final AuthenticationContext context;
    private final FileStorageSettingMapper fileStorageSettingMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(FileStorageSetting setting, int eventType) {
        log.info("redis publish - {},type -> {}", setting, eventType);
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        Long tenantId = setting.getTenantId();
        String platform = setting.getPlatform();
        // 构建并发布存储配置更新事件
        StorageSettingEvent event = new StorageSettingEvent();
        event.setPlatform(platform);
        event.setTenantId(tenantId);
        event.setUpdateType(eventType);
        event.setBucketName(setting.getBucketName());
        event.setAccessKey(setting.getAccessKey());
        event.setSecretKey(setting.getSecretKey());
        event.setRegion(setting.getRegion());
        event.setBasePath(setting.getBasePath());
        event.setDomain(setting.getDomain());
        event.setEndPoint(setting.getEndPoint());
        if (setting.getStatus()) {
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, tenantId.toString(), JSONObject.toJSONString(setting));
        }
        redisTemplate.convertAndSend(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC, event);
        SpringUtil.publishEvent(event);
    }

    public FileStorageSetting getDefaultStorageSetting() {
        String json = (String) redisTemplate.opsForHash().get(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString());
        if (StrUtil.isBlank(json)) {
            FileStorageSetting setting = this.fileStorageSettingMapper.selectOne(Wraps.<FileStorageSetting>lbQ().eq(FileStorageSetting::getStatus, true)
                    .eq(FileStorageSetting::getTenantId, context.tenantId()));
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString(), JSONObject.toJSONString(setting));
            return setting;
        }
        return JSON.parseObject(json, FileStorageSetting.class);
    }
}
