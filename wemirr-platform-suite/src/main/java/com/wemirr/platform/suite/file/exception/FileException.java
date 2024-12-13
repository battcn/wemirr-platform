package com.wemirr.platform.suite.file.exception;

import com.wemirr.framework.storage.properties.BaseStorageProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiao1
 * @date 2024-12
 */
public class FileException extends RuntimeException {


    @Getter
    @Setter
    private String platform;

    public FileException(String message, String platform) {
        super(message);
        this.platform=platform;
    }
}
