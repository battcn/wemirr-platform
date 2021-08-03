package com.oit.framework.tencent;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TencentStorageTemplateTest {


    @Test
    public void test1() throws FileNotFoundException {

        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDV7YeKpp6rIcfZVm1JK1bqpiQs86orkrq";
        String secretKey = "vb2au2wZ4NPFoAK9h7LJ3WjldEj9II1U";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = "oit-tcn-test-1256301505";
        // 设置公有读写
        cosClient.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
        String localFilePath = "/Users/battcn/oneinfitech/workspace/oit-cloud-platform/灰度发布流程.md";


        // 方法1 本地文件上传
        File localFile = new File(localFilePath);
        String key = "test1.md";
//        PutObjectResult putObjectResult = cosClient.putObject(bucketName, key, localFile);
//        String etag = putObjectResult.getETag();  // 获取文件的 etag

//        // 方法2 从输入流上传(需提前告知输入流的长度, 否则可能导致 oom)
        FileInputStream fileInputStream = new FileInputStream(localFile);
        ObjectMetadata objectMetadata = new ObjectMetadata();
//        // 设置输入流长度为500
        objectMetadata.setContentLength(10);

//        // 设置 Content type, 默认是 application/octet-stream
//        // objectMetadata.setContentType("application/pdf");
        PutObjectResult putObjectResult = cosClient.putObject(bucketName, key, fileInputStream, objectMetadata);
        System.out.println(JSON.toJSONString(putObjectResult));
    }


}