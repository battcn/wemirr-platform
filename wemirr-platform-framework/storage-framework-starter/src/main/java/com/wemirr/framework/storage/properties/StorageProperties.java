package com.wemirr.framework.storage.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Levin
 */
@Data
@Deprecated
public class StorageProperties {

    private Boolean enabled;
    private String secretId;
    private String secretKey;
    private String region;
    private String bucket;
    private String url;
    private String tmpDir;
    private ACL acl = ACL.DEFAULT;
    /**
     * 30分钟的有效期
     */
    private long expiration = 60 * 60 * 1000;


    @AllArgsConstructor
    public enum ACL {
        /**
         * 私有
         */
        PRIVATE("private"),

        /**
         * 公读
         */
        PUBLIC_READ("public-read"),
        /**
         * 公读写
         */
        PUBLIC_READ_WRITE("public-read-write"),

        /**
         * 默认
         */
        DEFAULT("default");

        /**
         * 存储的权限
         */
        private final String cannedAclHeader;

        public static ACL of(String cannedAclHeader) {
            for (ACL acl : values()) {
                if (acl.cannedAclHeader.equals(cannedAclHeader)) {
                    return acl;
                }
            }
            return null;
        }

    }

}
