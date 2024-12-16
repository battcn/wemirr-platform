package com.wemirr.platform.suite.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.rep.FileStorageSettingPageResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingSaveReq;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import com.wemirr.platform.suite.file.event.RedisStorageConfigEvent;
import com.wemirr.platform.suite.file.event.RedisStorageConfigEventPublish;

import com.wemirr.platform.suite.file.repository.FileStorageSettingMapper;
import com.wemirr.platform.suite.file.service.FileStorageSettingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.wemirr.platform.suite.file.domain.constants.StorageConstants.STORAGE_CONFIG_KEY_PREFIX;


/**
 * @author xiao1
 * @date 2024-12
 */
@Service
@AllArgsConstructor
public class FileStorageSettingServiceImpl extends SuperServiceImpl<FileStorageSettingMapper, FileStorageSetting> implements FileStorageSettingService {

    private final AuthenticationContext authenticationContext;

    private final RedisStorageConfigEventPublish redisStorageConfigEventPublish;

    private final FileStorageSettingMapper fileStorageSettingMapper;

    /**
     * 添加存储配置
     *
     * @param fileStorageSettingSaveReq 存储配置保存对象，包含要添加的存储配置信息
     */
    @Override
    public void addStorageConfig(FileStorageSettingSaveReq fileStorageSettingSaveReq) {
        // 获取当前租户ID
        Long tenantId = authenticationContext.tenantId();
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        String relayPlatform = tenantId + "-" + fileStorageSettingSaveReq.getPlatformV();
        // 检查平台名称是否已存在
        Long count = this.count(Wraps.<FileStorageSetting>lbQ().eq(FileStorageSetting::getPlatformV, fileStorageSettingSaveReq.getPlatformV()));
        if (count > 0) {
            throw  CheckedException.badRequest("该平台名称已存在");
        }
        // 复制属性并设置租户ID与平台名称
        FileStorageSetting fileStorageSetting = BeanUtil.copyProperties(fileStorageSettingSaveReq, FileStorageSetting.class);
        fileStorageSetting.setTenantId(tenantId);
        fileStorageSetting.setPlatform(relayPlatform);
        fileStorageSetting.setPlatformV(fileStorageSettingSaveReq.getPlatformV());

        try {
            // 更新存储配置状态
            if (fileStorageSetting.getEnableStorage()){
                fileStorageSettingMapper.updateConfigStatus(tenantId, relayPlatform);
            }
            // 保存存储配置
            save(fileStorageSetting);
        } catch (Exception e) {
            throw new RuntimeException("保存存储配置时发生异常", e);
        }
        // 构建并发布存储配置更新事件
        RedisStorageConfigEvent event = RedisStorageConfigEvent.builder()
                .tenantId(tenantId)
                .updateType(1)
                .enableStorage(fileStorageSetting.getEnableStorage())
                .platform(relayPlatform)
                .configKey(String.format(STORAGE_CONFIG_KEY_PREFIX, tenantId))
                .accessKey(fileStorageSetting.getAccessKey())
                .secretKey(fileStorageSetting.getSecretKey())
                .bucketName(fileStorageSetting.getBucketName())
                .region(fileStorageSetting.getRegion())
                .domain(fileStorageSetting.getDomain())
                .basePath(fileStorageSetting.getBasePath())
                .endPoint(fileStorageSetting.getEndPoint())
                .build();
        redisStorageConfigEventPublish.publish(event);
    }



    @Override
    public void deleteStorageConfig(Long id) {
        FileStorageSetting fileStorageSetting = fileStorageSettingMapper.selectById(id);
        RedisStorageConfigEvent event = RedisStorageConfigEvent.builder()
                .tenantId(fileStorageSetting.getTenantId())
                .updateType(3)
                .enableStorage(fileStorageSetting.getEnableStorage())
                .platform(fileStorageSetting.getPlatform())
                .configKey(String.format(STORAGE_CONFIG_KEY_PREFIX, fileStorageSetting.getTenantId()))
                .accessKey(fileStorageSetting.getAccessKey())
                .secretKey(fileStorageSetting.getSecretKey())
                .bucketName(fileStorageSetting.getBucketName())
                .region(fileStorageSetting.getRegion())
                .domain(fileStorageSetting.getDomain())
                .basePath(fileStorageSetting.getBasePath())
                .endPoint(fileStorageSetting.getEndPoint())
                .build();
        redisStorageConfigEventPublish.publish(event);
        if (fileStorageSetting.getEnableStorage()){
            throw  CheckedException.badRequest("该平台名称已启用，无法删除");
        }else {
            this.removeById(id);
        }
    }

    @Override
    public FileStorageSetting getStorageConfig() {
        return null;
    }

    @Override
    public void modify(Long id, FileStorageSettingSaveReq req) {
        // 获取当前租户ID
        Long tenantId = authenticationContext.tenantId();
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        String relayPlatform = tenantId + "-" + req.getPlatformV();
        // 检查平台名称是否已存在
        Long count = this.count(Wraps.<FileStorageSetting>lbQ()
                .eq(FileStorageSetting::getPlatformV, req.getPlatformV())
                .ne(FileStorageSetting::getId,id));
        if (count > 0) {
            throw  CheckedException.badRequest("该平台名称已存在");
        }
        FileStorageSetting oldConfig = this.getById(id);
        FileStorageSetting bean = BeanUtilPlus.toBean(id,req, FileStorageSetting.class);
        bean.setTenantId(tenantId);
        bean.setPlatform(relayPlatform);
        try {
            // 更新存储配置状态
            if (req.getEnableStorage()){
                fileStorageSettingMapper.updateConfigStatus(tenantId, relayPlatform);
            }
            // 保存存储配置
            this.updateById(bean);
        } catch (Exception e) {
            throw new RuntimeException("保存存储配置时发生异常", e);
        }
        // 构建并发布存储配置更新事件
        RedisStorageConfigEvent event = RedisStorageConfigEvent.builder()
                .tenantId(tenantId)
                .updateType(2)
                .enableStorage(bean.getEnableStorage())
                .platform(relayPlatform)
                .oldPlatform(oldConfig.getPlatform())
                .configKey(String.format(STORAGE_CONFIG_KEY_PREFIX, tenantId))
                .accessKey(bean.getAccessKey())
                .secretKey(bean.getSecretKey())
                .bucketName(bean.getBucketName())
                .region(bean.getRegion())
                .domain(bean.getDomain())
                .basePath(bean.getBasePath())
                .endPoint(bean.getEndPoint())
                .build();
        redisStorageConfigEventPublish.publish(event);
    }

    @Override
    public IPage<FileStorageSettingPageResp> pageList(FileStorageSettingPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<FileStorageSetting>lbQ()
                        .eq(FileStorageSetting::getPlatformV, req.getPlatformV())
                        .eq(FileStorageSetting::getEnableStorage, req.getEnableStorage()))
                .convert(x -> BeanUtil.toBean(x, FileStorageSettingPageResp.class));
    }
}
