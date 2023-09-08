package com.wemirr.framework.storage.exception;

import com.wemirr.framework.storage.properties.BaseStorageProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Levin
 */

public class StorageException extends RuntimeException {


    @Getter
    @Setter
    private BaseStorageProperties.StorageType storageType;


    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(BaseStorageProperties.StorageType storageType, String message) {
        super(message);
        this.setStorageType(storageType);
    }

    public StorageException(BaseStorageProperties.StorageType storageType, String message, Throwable cause) {
        super(message, cause);
        this.setStorageType(storageType);
    }


}
