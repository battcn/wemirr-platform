package com.wemirr.framework.storage.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.BufferedReader;
import java.io.File;

/**
 * 响应结果
 *
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DownloadResponse extends BaseResponse implements java.io.Serializable {

    private static final long serialVersionUID = -498222912510624959L;

    private BufferedReader bufferedReader;
    /**
     * 上下文类型
     */
    private String contentType;
    /**
     * 内容编码
     */
    private String contentEncoding;
    /**
     * 文件长度
     */
    private long contentLength;
    /**
     * 文件-本地
     */
    private File file;
    /**
     * 本地文件地址
     */
    private String localFilePath;

    public static DownloadResponse success(BufferedReader bufferedReader) {
        DownloadResponse response = new DownloadResponse();
        response.setSuccessful(true);
        response.setBufferedReader(bufferedReader);
        return response;
    }

    public static DownloadResponse error(String message) {
        DownloadResponse response = new DownloadResponse();
        response.setSuccessful(false);
        response.setBufferedReader(null);
        response.setMessage(message);
        return response;
    }
}
