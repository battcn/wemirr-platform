package com.wemirr.platform.suite.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.req.StorageConfigEditReq;
import com.wemirr.platform.suite.file.domain.dto.req.StorageConfigSaveReq;
import com.wemirr.platform.suite.file.domain.entity.ResourceStorageConfig;
import com.wemirr.platform.suite.file.event.RedisStorageConfigEvent;
import com.wemirr.platform.suite.file.event.RedisStorageConfigEventPublish;
import com.wemirr.platform.suite.file.exception.FileException;
import com.wemirr.platform.suite.file.repository.ResourceStorageConfigMapper;
import com.wemirr.platform.suite.file.service.ResourceStorageConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.wemirr.platform.suite.file.domain.constants.StorageConstants.STORAGE_CONFIG_KEY_PREFIX;


/**
 * @author xiao1
 * @date 2024-12
 */
@Service
@AllArgsConstructor
public class ResourceStorageConfigServiceImpl extends SuperServiceImpl<ResourceStorageConfigMapper, ResourceStorageConfig> implements ResourceStorageConfigService {

    private final AuthenticationContext authenticationContext;

    private final RedisStorageConfigEventPublish redisStorageConfigEventPublish;

    private final ResourceStorageConfigMapper resourceStorageConfigMapper;

    /**
     * 添加存储配置
     *
     * @param storageConfigSaveReq 存储配置保存对象，包含要添加的存储配置信息
     * @throws FileException 如果平台名称已存在，则抛出此异常
     */
    @Override
    public void addStorageConfig(StorageConfigSaveReq storageConfigSaveReq) {
        // 获取当前租户ID
        Long tenantId = authenticationContext.tenantId();
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        String relayPlatform = tenantId + "-" + storageConfigSaveReq.getPlatformV();
        // 检查平台名称是否已存在
        Long count = this.count(Wraps.<ResourceStorageConfig>lbQ().eq(ResourceStorageConfig::getPlatformV, storageConfigSaveReq.getPlatformV()));
        if (count > 0) {
            throw new FileException("该平台名称已存在", storageConfigSaveReq.getPlatformV());
        }
        // 复制属性并设置租户ID与平台名称
        ResourceStorageConfig resourceStorageConfig = BeanUtil.copyProperties(storageConfigSaveReq, ResourceStorageConfig.class);
        resourceStorageConfig.setTenantId(tenantId);
        resourceStorageConfig.setPlatform(relayPlatform);
        resourceStorageConfig.setPlatformV(storageConfigSaveReq.getPlatformV());

        try {
            // 更新存储配置状态
            if (resourceStorageConfig.getEnableStorage()){
                resourceStorageConfigMapper.updateConfigStatus(tenantId, relayPlatform);
            }
            // 保存存储配置
            save(resourceStorageConfig);
        } catch (Exception e) {
            throw new RuntimeException("保存存储配置时发生异常", e);
        }
        // 构建并发布存储配置更新事件
        RedisStorageConfigEvent event = RedisStorageConfigEvent.builder()
                .tenantId(tenantId)
                .updateType(1)
                .enableStorage(resourceStorageConfig.getEnableStorage())
                .platform(relayPlatform)
                .configKey(String.format(STORAGE_CONFIG_KEY_PREFIX, tenantId))
                .accessKey(resourceStorageConfig.getAccessKey())
                .secretKey(resourceStorageConfig.getSecretKey())
                .bucketName(resourceStorageConfig.getBucketName())
                .region(resourceStorageConfig.getRegion())
                .domain(resourceStorageConfig.getDomain())
                .basePath(resourceStorageConfig.getBasePath())
                .endPoint(resourceStorageConfig.getEndPoint())
                .build();
        redisStorageConfigEventPublish.publish(event);
    }

    @Override
    public void editStorageConfig(StorageConfigEditReq form) {
        // 获取当前租户ID
        Long tenantId = authenticationContext.tenantId();
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        String relayPlatform = tenantId + "-" + form.getPlatformV();
        // 检查平台名称是否已存在
        Long count = this.count(Wraps.<ResourceStorageConfig>lbQ()
                .eq(ResourceStorageConfig::getPlatformV, form.getPlatformV())
                .ne(ResourceStorageConfig::getId, form.getId()));
        if (count > 0) {
            throw new FileException("该平台名称已存在", form.getPlatformV());
        }
        ResourceStorageConfig oldConfig = this.getById(form.getId());
        // 复制属性并设置租户ID与平台名称
        ResourceStorageConfig resourceStorageConfig = BeanUtil.copyProperties(form, ResourceStorageConfig.class);
        resourceStorageConfig.setTenantId(tenantId);
        resourceStorageConfig.setPlatform(relayPlatform);
        try {
            // 更新存储配置状态
            if (form.getEnableStorage()){
                resourceStorageConfigMapper.updateConfigStatus(tenantId, relayPlatform);
            }
            // 保存存储配置
            this.updateById(resourceStorageConfig);
        } catch (Exception e) {
            throw new RuntimeException("保存存储配置时发生异常", e);
        }
        // 构建并发布存储配置更新事件
        RedisStorageConfigEvent event = RedisStorageConfigEvent.builder()
                .tenantId(tenantId)
                .updateType(2)
                .enableStorage(resourceStorageConfig.getEnableStorage())
                .platform(relayPlatform)
                .oldPlatform(oldConfig.getPlatform())
                .configKey(String.format(STORAGE_CONFIG_KEY_PREFIX, tenantId))
                .accessKey(resourceStorageConfig.getAccessKey())
                .secretKey(resourceStorageConfig.getSecretKey())
                .bucketName(resourceStorageConfig.getBucketName())
                .region(resourceStorageConfig.getRegion())
                .domain(resourceStorageConfig.getDomain())
                .basePath(resourceStorageConfig.getBasePath())
                .endPoint(resourceStorageConfig.getEndPoint())
                .build();
        redisStorageConfigEventPublish.publish(event);
    }

    @Override
    public void deleteStorageConfig(Long id) {
        ResourceStorageConfig resourceStorageConfig = resourceStorageConfigMapper.selectById(id);
        if (resourceStorageConfig.getEnableStorage()){
            throw new FileException("该平台名称已启用，无法删除", resourceStorageConfig.getPlatform());
        }else {
            this.removeById(id);
        }
    }

    @Override
    public ResourceStorageConfig getStorageConfig() {
        return null;
    }
}
