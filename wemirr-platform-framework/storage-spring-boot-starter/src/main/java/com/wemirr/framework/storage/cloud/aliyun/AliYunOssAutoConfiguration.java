package com.wemirr.framework.storage.cloud.aliyun;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.wemirr.framework.storage.AliYunStorageOperation;
import com.wemirr.framework.storage.endpoint.OssEndpoint;
import com.wemirr.framework.storage.properties.AliYunStorageProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.wemirr.framework.storage.StorageOperation.ALI_YUN_STORAGE_OPERATION;
import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_ALIYUN;

/**
 * 阿里云 OSS
 *
 * @author Levin
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(AliYunStorageProperties.class)
@ConditionalOnProperty(prefix = OSS_CONFIG_PREFIX_ALIYUN, name = "enabled", havingValue = "true")
public class AliYunOssAutoConfiguration {


    @Bean
    public OSSClient ossClient(AliYunStorageProperties properties) {
        CredentialsProvider provider = new DefaultCredentialProvider(properties.getAccessKey(), properties.getSecretKey());
        return new OSSClient(properties.getEndpoint(), provider, new ClientConfiguration());
    }

    @Bean(ALI_YUN_STORAGE_OPERATION)
    public AliYunStorageOperation aliYunStorageOperation(OSSClient ossClient, AliYunStorageProperties properties) {
        return new AliYunStorageOperation(ossClient, properties);
    }


    @Bean
    public OssEndpoint aliYunOssEndpoint() {
        return new OssEndpoint();
    }
}