package com.wemirr.framework.storage.cloud.minio;

import com.wemirr.framework.storage.MinioStorageOperation;
import com.wemirr.framework.storage.properties.MinioStorageProperties;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static com.wemirr.framework.storage.StorageOperation.MINIO_STORAGE_OPERATION;
import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_MINIO;


/**
 * @author Levin
 */
@Slf4j
@EnableConfigurationProperties(MinioStorageProperties.class)
@ConditionalOnProperty(prefix = OSS_CONFIG_PREFIX_MINIO, name = "enabled", havingValue = "true")
public class MinioOssAutoConfiguration {

    @SneakyThrows
    @Bean
    public MinioClient minioClient(MinioStorageProperties properties) {
        MinioClient minioClient = MinioClient.builder().endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey()).region(properties.getRegion()).build();
        minioClient.setTimeout(properties.getConnectTimeout().toMillis(), properties.getWriteTimeout().toMillis(),
                properties.getReadTimeout().toMillis());
        try {
            log.debug("Checking if bucket {} exists", properties.getBucket());
            boolean b = minioClient.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucket()).build());
            if (!b) {
                throw new RuntimeException(properties.getBucket() + "Bucket does not exists");
            }
        } catch (Exception e) {
            log.error("Error while checking bucket", e);
            throw e;
        }
        return minioClient;
    }

    @Bean(MINIO_STORAGE_OPERATION)
    public MinioStorageOperation minioStorageOperation(MinioClient minioClient, MinioStorageProperties properties) {
        return new MinioStorageOperation(minioClient, properties);
    }

}
