package com.wemirr.framework.storage;

import com.wemirr.framework.storage.domain.DownloadResponse;
import com.wemirr.framework.storage.domain.StorageItem;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 文件存储
 *
 * @author Levin
 */
public interface StorageOperation {

    /*  OSS 云存储的    */
    /**
     * 配置文件前缀
     */
    String OSS_CONFIG_PREFIX_ALIYUN = "extend.oss.cloud.aliyun";
    String OSS_CONFIG_PREFIX_MINIO = "extend.oss.cloud.minio";
    String OSS_CONFIG_PREFIX_QINIU = "extend.oss.cloud.qiniu";
    String OSS_CONFIG_PREFIX_TENCENT = "extend.oss.cloud.tencent";


    String MINIO_STORAGE_OPERATION = "minioStorageOperation";
    String ALI_YUN_STORAGE_OPERATION = "aliYunStorageOperation";
    String QI_NIU_STORAGE_OPERATION = "qiNiuStorageOperation";
    String TENCENT_STORAGE_OPERATION = "tencentStorageOperation";

    AtomicLong FILE_UPLOAD_SUCCESS = new AtomicLong();
    AtomicLong FILE_UPLOAD_FAIL = new AtomicLong();
    AtomicLong FILE_GET_COUNTS = new AtomicLong();
    AtomicLong FILE_DELETE_COUNTS = new AtomicLong();

    /**
     * 文件下载（流式下载）
     *
     * @param fileName 文件名
     * @return BufferedReader BufferedReader
     */
    DownloadResponse download(String fileName);

    /**
     * 文件下载（流式下载）
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名
     * @return BufferedReader BufferedReader
     */
    DownloadResponse download(String bucketName, String fileName);

    /**
     * 文件下载（文件下载到本地）
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名
     * @param file       保存的本地文件路径
     */
    void download(String bucketName, String fileName, File file);

    /**
     * 文件下载（文件下载到本地）
     *
     * @param fileName 文件名
     * @param file     保存的本地文件路径
     */
    void download(String fileName, File file);

    /**
     * 文件列表
     *
     * @return 文件内容
     */
    List<StorageItem> list();

    /**
     * 重命名
     *
     * @param oldName 原始名称
     * @param newName 新名称
     */
    void rename(String oldName, String newName);

    /**
     * 重命名
     *
     * @param bucketName 存储桶名
     * @param oldName    原始名称
     * @param newName    新名称
     */
    void rename(String bucketName, String oldName, String newName);

    /**
     * 上传文件到指定的 bucket
     *
     * @param fileName 文件名字
     * @param content  文件内容
     * @return StorageResponse
     */
    StorageResponse upload(String fileName, byte[] content);

    /**
     * 上传文件到指定的 bucket
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名字
     * @param content    文件内容
     * @return StorageResponse
     */
    StorageResponse upload(String bucketName, String fileName, InputStream content);

    /**
     * 上传文件到指定的 bucket
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名字
     * @param content    文件内容
     * @return StorageResponse
     */
    StorageResponse upload(String bucketName, String fileName, byte[] content);


    /**
     * 简化上传复杂度
     *
     * @param request request
     * @return 上传结果
     */
    StorageResponse upload(StorageRequest request);

    /**
     * 删除文件
     *
     * @param fileName 文件名
     */
    void remove(String fileName);

    /**
     * 删除文件
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名
     */
    void remove(String bucketName, String fileName);

    /**
     * 删除文件
     *
     * @param bucketName 存储桶名
     * @param path       文件路径
     */
    void remove(String bucketName, Path path);


    /**
     * 获取目标名字
     *
     * @param request request
     * @return 目标名称
     */
    default String getTargetName(StorageRequest request) {
        if (request == null) {
            throw new RuntimeException("request 不能为空");
        }
        if (StringUtils.isBlank(request.getOriginName())) {
            throw new RuntimeException("originName 不能为空");
        }
        final StorageRequest.PrefixRule rule = request.getRule();
        if (rule == null) {
            return FileUtils.targetName(request.isRandomName(), request.getPrefix(), request.getOriginName());
        }
        String prefix;
        switch (rule) {
            case now_date_mouth:
                prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
                break;
            case now_date_mouth_day:
                prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                break;
            case none:
                prefix = request.getPrefix();
                break;
            default:
                return request.getPrefix();
        }
        return FileUtils.targetName(request.isRandomName(), prefix, request.getOriginName());
    }


}
