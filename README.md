# wemirr-platform

![分配用户](./images/binding_user.png)

![分配权限](./images/binding_res.png)

![黑白名单](./images/blacklist.png)

![限流配置](./images/limit.png)

![菜单管理](./images/menu.png)

![监控管理](./images/monitor.png)

![操作日志](./images/opt_log.png)

![发布消息](./images/publish.png)

![用户列表](./images/users.png)

## 介绍

一个业余时间写的开源项目、欢迎使用和提建议、包括低码平台、常见 中台 、SAAS 、 多租户功能、最最少的代码实现功能

[演示地址](https://cloud.battcn.com/) 在线演示地址，尽量别删数据。没有做一键回滚数据

[文档地址](https://www.yuque.com/books/share/c5467c7b-ae67-4d3e-a6cd-541ce5a90bb7?#《wemirr-platform-doc》) 

[配套前端](https://gitee.com/battcn/wemirr-platform-ui) 配套的 UI 

[Nepxion-Discovery](https://github.com/Nepxion/Discovery) 蓝绿、灰度、流量保护

[d2-curd-plus](http://greper.gitee.io/d2-crud-plus) 前端的 CRUD 

[OpenAPi3](https://springdoc.org/) Swagger 标准版

#### 软件架构

Vue、Spring Cloud Alibaba 2.2.5.RELEASE、Spring Cloud Hoxton.SR11、Nacos、Sentinel、
Nepxion、Mybatis-Plus、多租户、灰度、Oauth2.0、Spring Security、Redis、Mysql、MongoDB、
ShardingJdbc、ShardingSphere


`wemirr-platform-bury` 是一个用 `shardingsphere` 做分表分库收集日志的，常见埋点日志手段
- **`记录日志文件,EFK/ELK采集日志`**
- **`日志量小的话可以写到库`**
- **`日志量大可以分表分库记录埋点日志，定期清理`**


#### 多端登录


#### 注意事项

**如果需要使用低码平台，需要安装 MongoDB 的支持**

**nepxion 项目是本人用于测试一些中间件的工程、完全可以忽略**

### 环境安装

一般安装 `latest` 版本即可，也可以自行指定版本 `docker search` 或者自己上 `docker hub` 看版本

**如果需要体验低码平台一键发布需要安装 `MongoDB` 除此之外其它中间件是必须的**


``` shell script
docker pull redis:latest
docker run -itd --name redis -p 6379:6379 redis

安装 Mysql 
docker pull mysql:latest
docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql


安装 Sentinel-Dashboard
docker pull bladex/sentinel-dashboard
docker run -i -t -d -p 8858:8858 -p 8719:8719  bladex/sentinel-dashboard

安装 Nacos
docker pull nacos/nacos-server
docker  run --name nacos -d-p 8848:8848 --restart=always -e MODE=standalone


安装 MongoDB
docker pull mongo:latest
docker run -itd --name mongo -p 27017:27017 mongo
```


### 关于埋点日志

``` java
@Bean
public SysLogListener sysLogListener(OptLogService optLogService) {
    return new SysLogListener(optLogService::save);
}

// 如果操作量大又想记录到数据库，请用该组件
@Bean
@ConditionalOnExpression
public SysLogListener sysLogListener(BuryPointClient buryPointClient) {
    return new SysLogListener(buryPointClient::buryPoint);
}
```

