package com.wemirr.platform.suite.file.event;


import com.wemirr.framework.redis.plus.listener.AbstractMessageEventListener;
import com.wemirr.platform.suite.file.domain.constants.StorageConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@AllArgsConstructor
@Component
public class RedisStorageSettingListener implements AbstractMessageEventListener<StorageSettingEvent> {

    private final StringRedisTemplate redisTemplate;
    private final FileStorageService fileStorageService;


    /**
     * 处理Redis存储配置更新消息
     *
     * @param event Redis存储配置事件
     */
    public void handleMessage(StorageSettingEvent event) {
        log.info("- 监听存储配置更新消息 -");
        log.info("租户ID:{}, 更新类型:{}", event.getTenantId(), event.getUpdateType());
        switch (event.getUpdateType()) {
            case 1 -> handleNewConfiguration(event);
            case 2 -> handleUpdateConfiguration(event);
            case 3 -> handleDeleteConfiguration(event);
            default -> log.warn("未知的更新类型: {}", event.getUpdateType());
        }
        //当前的存储配置
        String platform = fileStorageService.getFileStorageList()
                .stream()
                .map(FileStorage::getPlatform)
                .collect(Collectors.joining(";"));
        log.info("- 更新后当前的存储配置平台： -{}",platform);
    }

    /**
     * 处理新的配置添加
     *
     * @param event Redis存储配置事件
     */
    private void handleNewConfiguration(StorageSettingEvent event) {
        CopyOnWriteArrayList<FileStorage> list = fileStorageService.getFileStorageList();
        log.info("添加新的平台配置");
        FileStorageProperties.AmazonS3Config amazonS3Config = createAmazonS3Config(event);
        list.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(Collections.singletonList(amazonS3Config), null));
    }

    /**
     * 处理配置更新
     *
     * @param event Redis存储配置事件
     */
    private void handleUpdateConfiguration(StorageSettingEvent event) {
        log.info("文件配置修改");
        if (event.getStatus()) {
            redisTemplate.opsForValue().set(event.getConfigKey(), event.getPlatform());
            //删除旧平台
            CopyOnWriteArrayList<FileStorage> list = fileStorageService.getFileStorageList();
            FileStorage fileStorage = fileStorageService.getFileStorage(event.getPlatform());
            list.remove(fileStorage);
            FileStorageProperties.AmazonS3Config amazonS3Config = createAmazonS3Config(event);
            list.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(Collections.singletonList(amazonS3Config), null));
        } else {
            String cur = redisTemplate.opsForValue().get(event.getConfigKey());
            if (cur != null && cur.equals(event.getPlatform())) {
                redisTemplate.delete(event.getConfigKey());
                log.info("关闭的是当前开启的配置，需要重新加载");
            } else {
                log.info("关闭的是非当前开启的其他配置，无需重新加载");
            }
        }
    }

    /**
     * 处理配置删除
     * 删除事件无需更新redis，因为开启的配置不应该被删除
     * @param event Redis存储配置事件
     */
    private void handleDeleteConfiguration(StorageSettingEvent event) {
        log.info("删除");
        CopyOnWriteArrayList<FileStorage> list = fileStorageService.getFileStorageList();
        FileStorage fileStorage = fileStorageService.getFileStorage(event.getPlatform());
        list.remove(fileStorage);
        //fileStorage.close(); // 释放资源
    }

    /**
     * 创建AmazonS3Config配置
     *
     * @param event Redis存储配置事件
     * @return 配置好的AmazonS3Config对象
     */
    private FileStorageProperties.AmazonS3Config createAmazonS3Config(StorageSettingEvent event) {
        FileStorageProperties.AmazonS3Config s3Config = new FileStorageProperties.AmazonS3Config();
        s3Config.setPlatform(event.getPlatform());
        s3Config.setAccessKey(event.getAccessKey());
        s3Config.setSecretKey(event.getSecretKey());
        s3Config.setRegion(event.getRegion());
        s3Config.setEndPoint(event.getEndPoint());
        s3Config.setBucketName(event.getBucketName());
        s3Config.setDomain(event.getDomain());
        s3Config.setBasePath(event.getBasePath());
        return s3Config;
    }


    @Override
    public Topic topic() {
        return new ChannelTopic(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC);
    }

    @Override
    public Type type() {
        return StorageSettingEvent.class;
    }


}
