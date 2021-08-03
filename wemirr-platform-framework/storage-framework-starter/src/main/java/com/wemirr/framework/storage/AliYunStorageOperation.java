package com.wemirr.framework.storage;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.wemirr.framework.storage.domain.DownloadResponse;
import com.wemirr.framework.storage.domain.StorageItem;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.framework.storage.exception.StorageException;
import com.wemirr.framework.storage.properties.AliYunStorageProperties;
import com.wemirr.framework.storage.properties.BaseStorageProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class AliYunStorageOperation implements StorageOperation {


    private final OSSClient ossClient;
    private final AliYunStorageProperties properties;


    @Override
    public DownloadResponse download(String fileName) {
        return download(properties.getBucket(), fileName);
    }


    @Override
    public DownloadResponse download(String bucketName, String fileName) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);
        // 读取文件内容。
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
            }
            return DownloadResponse.success(reader);
        } catch (Exception e) {
            log.error("[文件下载异常]", e);
            return DownloadResponse.error(e.getLocalizedMessage());
        }
    }


    @Override
    public void download(String fileName, File file) {
        download(properties.getBucket(), fileName, file);
    }


    @Override
    public void download(String bucketName, String fileName, File file) {
        ossClient.getObject(new GetObjectRequest(bucketName, fileName), file);
    }


    @Override
    public List<StorageItem> list() {
        throw new StorageException(BaseStorageProperties.StorageType.ALIYUN, "方法未实现");
    }


    @Override
    public void rename(String oldName, String newName) {
        rename(properties.getBucket(), oldName, newName);
    }


    @Override
    public void rename(String bucketName, String oldName, String newName) {
        boolean keyExists = true;
        try {
            ossClient.getObjectMetadata(bucketName, oldName);
        } catch (Exception e) {
            keyExists = false;
        }
        if (keyExists) {
            ossClient.copyObject(bucketName, oldName, bucketName, newName);
        }
    }


    @Override
    public StorageResponse upload(String fileName, byte[] content) {
        return upload(properties.getBucket(), fileName, content);
    }


    @Override
    public StorageResponse upload(String bucketName, String fileName, InputStream content) {
        try {
            byte[] bytes = new byte[content.available()];
            return upload(properties.getBucket(), fileName, bytes);
        } catch (IOException ex) {
            log.error("[异常信息]", ex);
            return StorageResponse.error(ex.getLocalizedMessage());
        }
    }

    /**
     * 上传文件到指定的 bucket
     *
     * @param bucketName 存储桶名称
     * @param fileName   文件名字
     * @param content    文件内容
     */

    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        ByteArrayInputStream bis = new ByteArrayInputStream(content);
        try {
            PutObjectResult objectResult = ossClient.putObject(bucketName, fileName, bis);
            ResponseMessage response = objectResult.getResponse();
            if (!response.isSuccessful()) {
                return StorageResponse.error(response.getErrorResponseAsString());
            }
            FILE_UPLOAD_SUCCESS.incrementAndGet();
            return StorageResponse.builder().originName(fileName).targetName(fileName)
                    .size(response.getContentLength()).fullUrl(response.getUri()).build();
        } catch (Exception ex) {
            ossClient.putObject(bucketName, fileName, bis);
            FILE_UPLOAD_FAIL.incrementAndGet();
            log.error("[异常信息]", ex);
            return StorageResponse.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public StorageResponse upload(StorageRequest request) {
        return null;
    }


    @Override
    public void remove(String fileName) {
        remove(properties.getBucket(), fileName);
    }


    @Override
    public void remove(String bucketName, String fileName) {
        ossClient.deleteObject(bucketName, fileName);
        FILE_DELETE_COUNTS.incrementAndGet();
    }

    @Override
    public void remove(String bucketName, Path path) {
        remove(bucketName, path.toString());
    }
}
