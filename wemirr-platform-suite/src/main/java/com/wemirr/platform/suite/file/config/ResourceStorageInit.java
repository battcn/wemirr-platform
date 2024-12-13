package com.wemirr.platform.suite.file.config;

import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.framework.db.mybatisplus.wrap.query.LbqWrapper;
import com.wemirr.platform.suite.file.domain.entity.ResourceStorageConfig;
import com.wemirr.platform.suite.file.service.ResourceStorageConfigService;
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

import static com.wemirr.platform.suite.file.domain.constants.StorageConstants.STORAGE_CONFIG_KEY_PREFIX;


/**
 * @author xiao1
 * @date 2024-12
 * 存储平台策略初始化
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class ResourceStorageInit {


    private final FileStorageService fileStorageService;

    private final ResourceStorageConfigService imageStorageConfigService;

    private final StringRedisTemplate  redisTemplate;


    @PostConstruct
    public void init() {
        log.info("- 动态初始化存储策略开始 -");
        InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
        final LbqWrapper<ResourceStorageConfig> wrapper = Wraps.<ResourceStorageConfig>lbQ()
                .eq(ResourceStorageConfig::getEnableStorage, Boolean.TRUE);
        List<ResourceStorageConfig> dbConfigList = imageStorageConfigService.list(wrapper);
        CopyOnWriteArrayList<FileStorage> fileStorageList = fileStorageService.getFileStorageList();
        List<FileStorageProperties.AmazonS3Config> amazonS3ConfigList = new ArrayList<>();
        for (ResourceStorageConfig resourceStorageConfig : dbConfigList) {
                FileStorageProperties.AmazonS3Config amazonS3Config = new FileStorageProperties.AmazonS3Config();
                amazonS3Config.setPlatform(resourceStorageConfig.getPlatform());
                amazonS3Config.setAccessKey(resourceStorageConfig.getAccessKey());
                amazonS3Config.setSecretKey(resourceStorageConfig.getSecretKey());
                amazonS3Config.setRegion(resourceStorageConfig.getRegion());
                amazonS3Config.setEndPoint(resourceStorageConfig.getEndPoint());
                amazonS3Config.setBucketName(resourceStorageConfig.getBucketName());
                amazonS3Config.setDomain(resourceStorageConfig.getDomain());
                amazonS3Config.setBasePath(resourceStorageConfig.getBasePath());
                amazonS3ConfigList.add(amazonS3Config);
                redisTemplate.opsForValue().set(geyKey(resourceStorageConfig.getTenantId()), resourceStorageConfig.getPlatform());
        }
        fileStorageList.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(amazonS3ConfigList,null));
        log.info("- 动态初始化存储策略结束 -");
        log.info("- 加载【租户ID，开启的存储的平台】到redis完成 -");

    }

    private String geyKey(Long key) {
        return String.format(STORAGE_CONFIG_KEY_PREFIX, key);
    }


}
