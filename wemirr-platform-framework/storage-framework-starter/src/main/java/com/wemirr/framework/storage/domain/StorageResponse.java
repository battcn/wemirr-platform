package com.wemirr.framework.storage.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 响应结果
 *
 * @author Levin
 */
@Data
@NoArgsConstructor
public class StorageResponse implements java.io.Serializable {

    private static final long serialVersionUID = -498222912510624959L;

    private String originName;
    private String targetName;
    private long size;
    /**
     * 文件存储的名字
     */
    private String md5;

    /**
     * 文件的完整路径
     */
    private String fullUrl;

    private String mappingPath;

    /**
     * 对应存储的扩展字段
     */
    private Map<String, Object> extend;

    @Builder
    public StorageResponse(String originName, String targetName, String mappingPath,
                           long size, String md5, String fullUrl, Map<String, Object> extend) {
        this.originName = originName;
        this.targetName = targetName;
        this.size = size;
        this.md5 = md5;
        this.extend = extend;
        this.mappingPath = mappingPath;
        if (StringUtils.isBlank(fullUrl)) {
            this.fullUrl = buildFullUrl(mappingPath, targetName);
        } else {
            this.fullUrl = fullUrl;
        }
    }

    public static String buildFullUrl(String mappingPath, String targetName) {
        if (mappingPath.endsWith("/") && targetName.startsWith("/")) {
            mappingPath = mappingPath.substring(0, mappingPath.length() - 1);
        }
        return StringUtils.join(mappingPath, targetName);
    }
}
