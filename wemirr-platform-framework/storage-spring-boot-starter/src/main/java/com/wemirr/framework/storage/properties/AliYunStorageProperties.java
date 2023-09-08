package com.wemirr.framework.storage.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.wemirr.framework.storage.StorageOperation.OSS_CONFIG_PREFIX_ALIYUN;


/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = OSS_CONFIG_PREFIX_ALIYUN)
public class AliYunStorageProperties extends BaseStorageProperties {


    /**
     * endpoint
     */
    private String endpoint = "http://cloud.aliyuncs.com";


}
