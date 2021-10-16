package com.wemirr.framework.storage.cloud.qiniu.connection;

import com.qiniu.cdn.CdnManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.wemirr.framework.storage.cloud.qiniu.QiNiuScope;

/**
 * @author Levin
 */
public interface QiNiuConnectionFactory {

    /**
     * 创建 Auth
     *
     * @return Auth
     */
    Auth getAuth();

    /**
     * 获取 BucketManager
     *
     * @return BucketManager
     */
    BucketManager getBucketManager();

    /**
     * 获取 UploadManager
     *
     * @return UploadManager
     */
    UploadManager getUploadManager();

    /**
     * 获取 CdnManager
     *
     * @return CdnManager
     */
    CdnManager getCdnManager();

    /**
     * 获取域名
     *
     * @param bucket bucket
     * @return Domain
     */
    String getDomain(String bucket);

    /**
     * 获取文件上传 token
     *
     * @param bucket bucket
     * @param key    key
     * @return token
     */
    String getUploadToken(String bucket, String key);

    /**
     * 获取文件上传 token
     *
     * @param bucket bucket
     * @param scope  范围
     * @param key    key
     * @return token
     */
    String getUploadToken(String bucket, String key, QiNiuScope scope);

}