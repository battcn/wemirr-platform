package com.wemirr.framework.storage.cloud.qiniu;

import com.wemirr.framework.storage.QiNiuStorageOperation;
import com.wemirr.framework.storage.cloud.qiniu.connection.QiNiuConnectionFactory;
import com.wemirr.framework.storage.cloud.qiniu.connection.QiNiuOssClientConnectionFactory;
import com.wemirr.framework.storage.properties.QiNiuStorageProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_QINIU;
import static com.wemirr.framework.storage.StorageOperation.QI_NIU_STORAGE_OPERATION;

/**
 * @author Levin
 */
@EnableConfigurationProperties({QiNiuStorageProperties.class})
@ConditionalOnProperty(prefix = OSS_CONFIG_PREFIX_QINIU, name = "enabled", havingValue = "true")
public class QiNiuOssAutoConfiguration {

    @Bean
    public QiNiuConnectionFactory qiNiuConnectionFactory(QiNiuStorageProperties properties) {
        return new QiNiuOssClientConnectionFactory(properties);
    }

    @Bean(QI_NIU_STORAGE_OPERATION)
    public QiNiuStorageOperation qiNiuStorageOperation(QiNiuStorageProperties properties, QiNiuConnectionFactory qiNiuConnectionFactory) {
        return new QiNiuStorageOperation(properties, qiNiuConnectionFactory);
    }
}