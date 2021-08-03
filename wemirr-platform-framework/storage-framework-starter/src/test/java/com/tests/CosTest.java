package com.tests;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class CosTest {

    private COSClient cosClient;
    String bucket = "oit-tcn-test-1256301505";

    @Before
    public void before() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDV7YeKpp6rIcfZVm1JK1bqpiQs86orkrq";
        String secretKey = "vb2au2wZ4NPFoAK9h7LJ3WjldEj9II1U";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
        System.out.println(JSON.toJSONString(cred));
    }

    @Test
    public void testUpload() {
        String fileName = "/Users/battcn/oneinfitech/workspace/oit-cloud-platform/oit-cloud-framework/readme.md";
        PutObjectRequest request = new PutObjectRequest(bucket, "20200821/md/readme.md", new File(fileName));
        final PutObjectResult result = cosClient.putObject(request);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void test1() {
        final COSObject object = cosClient.getObject(bucket, "20200821/md/readme.md");
        System.out.println(JSON.toJSONString(object));
    }

    @Test
    public void test2() {
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucket, "0006b3c2-4625-48de-80ab-51359f9c73d1.png", HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
        req.setExpiration(expirationDate);
        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(JSON.toJSONString(url));
        System.out.println(url.toString());
    }

}
