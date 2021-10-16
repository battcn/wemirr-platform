## 云存储方案

支持`阿里云`、`腾讯云`、`自建Minio`、`七牛云`、等多种云存储，提供丰富的API开箱即用，如不满足业务需要，可以自行扩展`storage-spring-boot-starter`

```properties
# 阿里云
extend.oss.cloud.aliyun.access-key=xxxx
extend.oss.cloud.aliyun.bucket=xxxx

# 腾讯云
extend.oss.cloud.tencent.access-key=xxxx
extend.oss.cloud.tencent.app-id=xxxx

# 自建Minio
extend.oss.cloud.minio.access-key=xxxx
extend.oss.cloud.minio.bucket=xxxx

# 七牛云
extend.oss.cloud.qiniu.enabled=true
extend.oss.cloud.qiniu.region=region0
extend.oss.cloud.qiniu.bucket=battcn
extend.oss.cloud.qiniu.mapping-path=http://qiniu.battcn.com
```

## 使用方式

注入 `StorageOperation` 即可使用

``` java
private final StorageOperation storageOperation;
```
