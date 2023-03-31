package com.wemirr.framework.storage.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;

/**
 * 响应结果
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadResponse implements java.io.Serializable {

    private static final long serialVersionUID = -498222912510624959L;

    @Deprecated
    private BufferedReader bufferedReader;

    private InputStream inputStream;
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
}
