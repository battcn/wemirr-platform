package com.wemirr.framework.database.configuration.encode;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.hikari.extend")
public class HikariDataSourceExtProperties {


    private Boolean encrypt;
    private String pubKey;
    private EncryptType encryptType;

    public enum EncryptType {
        /**
         * aes加密
         */
        AES
    }


}
