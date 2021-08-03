package com.wemirr.framework.storage;

import com.google.common.collect.Maps;
import com.wemirr.framework.storage.domain.DownloadResponse;
import com.wemirr.framework.storage.domain.StorageItem;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.framework.storage.exception.StorageException;
import com.wemirr.framework.storage.properties.BaseStorageProperties;
import com.wemirr.framework.storage.properties.MinioStorageProperties;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class MinioStorageOperation implements StorageOperation {


    private final MinioClient minioClient;
    private final MinioStorageProperties properties;


    @Override
    public DownloadResponse download(String fileName) {
        return download(properties.getBucket(), fileName);
    }

    @Override
    public DownloadResponse download(String bucketName, String fileName) {
        try {
            InputStream inputStream = minioClient.getObject(bucketName, fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return DownloadResponse.success(bufferedReader);
        } catch (Exception ex) {
            log.error("[文件下载异常]", ex);
            return DownloadResponse.error(ex.getLocalizedMessage());
        }
    }


    @Override
    public void download(String bucketName, String fileName, File file) {
        try {
            InputStream inputStream = minioClient.getObject(bucketName, fileName);
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            log.error("[文件下载异常]", e);
        }
    }


    @Override
    public void download(String fileName, File file) {
        download(properties.getBucket(), fileName, file);
    }


    /**
     * 对象转换
     *
     * @param iterable 迭代内容
     * @return 返回结果
     */
    private List<StorageItem> getStorageItems(Iterable<Result<Item>> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), true)
                .map(itemResult -> {
                    try {
                        StorageItem storageItem = new StorageItem();
                        Item item = itemResult.get();
                        storageItem.setName(item.objectName());
                        storageItem.setSize(item.objectSize());
                        Map<String, Object> extended = Maps.newHashMap();
                        extended.put("tag", item.etag());
                        extended.put("storageClass", item.storageClass());
                        extended.put("lastModified", item.lastModified());
                        storageItem.setExtended(extended);
                        return storageItem;
                    } catch (InvalidBucketNameException |
                            NoSuchAlgorithmException |
                            InsufficientDataException |
                            IOException |
                            InvalidKeyException |
                            NoResponseException |
                            XmlPullParserException |
                            ErrorResponseException |
                            InternalException e) {
                        throw new StorageException(BaseStorageProperties.StorageType.MINIO, "Error while parsing list of objects", e);
                    }
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<StorageItem> list() {
        try {
            Iterable<Result<Item>> iterable = minioClient.listObjects(properties.getBucket());
            return getStorageItems(iterable);
        } catch (XmlPullParserException e) {
            log.error("[文件列表异常]", e);
        }
        return null;
    }


    @Override
    public void rename(String oldName, String newName) {
        throw new StorageException(BaseStorageProperties.StorageType.MINIO, "方法未实现");
    }


    @Override
    public void rename(String bucketName, String oldName, String newName) {
        throw new StorageException(BaseStorageProperties.StorageType.MINIO, "方法未实现");
    }


    @Override
    public StorageResponse upload(String fileName, byte[] content) {
        InputStream stream = new ByteArrayInputStream(content);
        return upload(properties.getBucket(), fileName, stream);
    }


    @Override
    public StorageResponse upload(String bucketName, String fileName, InputStream content) {
        try {
            minioClient.putObject(bucketName, fileName, content, content.available(),
                    ContentType.APPLICATION_OCTET_STREAM.getMimeType());
            return StorageResponse.builder().originName(fileName).targetName(fileName)
                    .fullUrl(properties.getMappingPath() + fileName).build();
        } catch (Exception e) {
            log.error("[文件上传失败]", e);
            return StorageResponse.error(e.getLocalizedMessage());
        }
    }


    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        return upload(bucketName, fileName, new ByteArrayInputStream(content));
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
        try {
            minioClient.removeObject(bucketName, fileName);
        } catch (Exception e) {
            log.error("[文件删除失败]", e);
        }
    }


    @Override
    public void remove(String bucketName, Path path) {
        remove(bucketName, path.toString());
    }
}
