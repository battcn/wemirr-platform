package com.wemirr.framework.storage;

import cn.hutool.core.util.StrUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.wemirr.framework.storage.domain.DownloadResponse;
import com.wemirr.framework.storage.domain.StorageItem;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.framework.storage.exception.StorageException;
import com.wemirr.framework.storage.properties.BaseStorageProperties;
import com.wemirr.framework.storage.properties.TencentStorageProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.List;


/**
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class TencentStorageOperation implements StorageOperation {

    private final COSClient client;
    private final TencentStorageProperties properties;


    @Override
    public DownloadResponse download(String fileName) {
        return download(properties.getBucket(), fileName);
    }

    @Override
    public DownloadResponse download(String bucketName, String fileName) {
        final String path = StringUtils.defaultIfBlank(this.properties.getTmpDir(), this.getClass().getResource("/").getPath());
        final File file = new File(path + File.separator + fileName);
        log.debug("[文件目录] - [{}]", file.getPath());
        download(bucketName, fileName, file);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new StorageException(BaseStorageProperties.StorageType.TENCENT, "文件上传失败," + e.getLocalizedMessage());
        }
        return DownloadResponse.builder().bufferedReader(bufferedReader)
                .file(file).localFilePath(file.getPath()).build();

    }

    @Override
    public void download(String bucketName, String fileName, File file) {
        final String bucket = StrUtil.blankToDefault(bucketName, properties.getBucket());
        this.client.getObject(new GetObjectRequest(bucket, fileName), file);
    }

    @Override
    public void download(String fileName, File file) {
        download(properties.getBucket(), fileName, file);
    }

    @Override
    public List<StorageItem> list() {
        return null;
    }

    @Override
    public void rename(String oldName, String newName) {

    }


    @Override
    public void rename(String bucketName, String oldName, String newName) {

    }


    @Override
    public StorageResponse upload(String fileName, byte[] content) {
        return upload(properties.getBucket(), fileName, content);
    }


    @Override
    public StorageResponse upload(String bucketName, String fileName, InputStream content) {
        //腾讯云必需要以"/"开头
        if (!fileName.startsWith(File.separator)) {
            fileName = File.separator + fileName;
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        try {
            objectMetadata.setContentLength(content.available());
            PutObjectRequest request = new PutObjectRequest(properties.getBucket(), fileName, content, objectMetadata);
            PutObjectResult result = client.putObject(request);
            if (StringUtils.isEmpty(result.getETag())) {
                throw new StorageException(BaseStorageProperties.StorageType.TENCENT, "文件上传失败,ETag为空");
            }
            return StorageResponse.builder().originName(fileName).targetName(fileName)
                    .size(objectMetadata.getContentLength()).fullUrl(properties.getMappingPath() + fileName).build();
        } catch (IOException e) {
            log.error("[文件上传异常]", e);
            throw new StorageException(BaseStorageProperties.StorageType.TENCENT, "文件上传失败," + e.getLocalizedMessage());
        }
    }


    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        //腾讯云必需要以"/"开头
        if (!fileName.startsWith(File.separator)) {
            fileName = File.separator + fileName;
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度为 500
        objectMetadata.setContentLength(content.length);
        PutObjectRequest request = new PutObjectRequest(properties.getBucket(), fileName,
                new ByteArrayInputStream(content), objectMetadata);
        PutObjectResult result = client.putObject(request);
        if (StringUtils.isEmpty(result.getETag())) {
            throw new StorageException(BaseStorageProperties.StorageType.TENCENT, "文件上传失败,ETag为空");
        }
        return StorageResponse.builder().originName(fileName).targetName(fileName)
                .size(objectMetadata.getContentLength()).fullUrl(properties.getMappingPath() + fileName).build();
    }

    @Override
    public StorageResponse upload(StorageRequest request) {
        return null;
    }

    @Override
    public void remove(String fileName) {

    }

    @Override
    public void remove(String bucketName, String fileName) {

    }

    @Override
    public void remove(String bucketName, Path path) {

    }
}
