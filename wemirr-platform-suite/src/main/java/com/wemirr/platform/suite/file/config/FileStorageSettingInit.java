package com.wemirr.platform.suite.file.config;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import com.wemirr.platform.suite.file.service.FileStorageSettingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.wemirr.platform.suite.file.domain.constants.StorageConstants.STORAGE_SETTING_DEFAULT_SETTING;


/**
 * @author xiao1
 * @since 2024-12
 * 存储平台策略初始化
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class FileStorageSettingInit {


    private final FileStorageService fileStorageService;

    private final FileStorageSettingService imageStorageConfigService;

    private final StringRedisTemplate redisTemplate;


    @PostConstruct
    public void init() {
        log.info("- 动态初始化存储策略开始 -");
        InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
        final LbqWrapper<FileStorageSetting> wrapper = Wraps.<FileStorageSetting>lbQ().eq(FileStorageSetting::getStatus, true);
        List<FileStorageSetting> dbConfigList = imageStorageConfigService.list(wrapper);
        CopyOnWriteArrayList<FileStorage> fileStorageList = fileStorageService.getFileStorageList();
        List<FileStorageProperties.AmazonS3Config> amazonS3ConfigList = new ArrayList<>();
        for (FileStorageSetting setting : dbConfigList) {
            FileStorageProperties.AmazonS3Config s3Config = new FileStorageProperties.AmazonS3Config();
            String platform = setting.getPlatform();
            s3Config.setPlatform(platform);
            s3Config.setAccessKey(setting.getAccessKey());
            s3Config.setSecretKey(setting.getSecretKey());
            s3Config.setRegion(setting.getRegion());
            s3Config.setEndPoint(setting.getEndPoint());
            s3Config.setBucketName(setting.getBucketName());
            s3Config.setDomain(setting.getDomain());
            s3Config.setBasePath(setting.getBasePath());
            amazonS3ConfigList.add(s3Config);
            redisTemplate.opsForHash().put(STORAGE_SETTING_DEFAULT_SETTING, setting.getTenantId().toString(), JSON.toJSONString(setting));
        }
        fileStorageList.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(amazonS3ConfigList, null));
        log.info("- 动态初始化存储策略结束 -");
        log.info("- 加载【租户ID，开启的存储的平台】到redis完成 -");
    }
}
