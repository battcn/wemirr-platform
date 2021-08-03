package com.wemirr.framework.storage.domain;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 响应结果
 *
 * @author Levin
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StorageResponse extends BaseResponse implements java.io.Serializable {

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


    /**
     * 对应存储的扩展字段
     */
    private Map<String, Object> extend;

    public static StorageResponse error(String message) {
        StorageResponse response = new StorageResponse();
        response.setSuccessful(false);
        response.setMessage(message);
        return response;
    }

    @Builder
    public StorageResponse(boolean successful, String message, String originName, String targetName,
                           long size,
                           String md5, String fullUrl, Map<String, Object> extend) {
        super(successful, message);
        this.size = size;
        this.originName = originName;
        this.targetName = targetName;
        this.md5 = md5;
        this.fullUrl = fullUrl;
        this.extend = extend;
    }
}
