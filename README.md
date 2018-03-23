## 开始维护

[http://demo.battcn.com](http://demo.battcn.com "演示地址")

# 新增功能

- 添加 **`JWT` 认证***
- 基于`Redis`封装,唯一序列号生成与分布式`CacheLock`
- 新增章节模板
- 生成书籍文章
- 添加了书籍爬虫(亲测抓取3000本书)
- 自行维护代理IP池（自动清洗,自动抓取）


# 开发内容

目前会编写一个 书城  涵盖 `爬虫、代理、管理系统、阅读、下载` 本项目会分为 `dubbo` 与 `springcloud` 版本，初期以 `dubbo`为主


鉴于 `dubbo` 已经开始维护了 , 本项目也即将开启维护之旅

由于 `dubbo-spring-boot-starter（现在还是开发版）`，所以暂未升级 `spring-boot2.x`,依旧采用的`1.5.10.RELEASE` 版本



## 技术栈

- **spring-boot**
- **dubbo**
- **mybatis**
- **shiro**
- **zookeeper**
- **swagger**
- **bootstarp table：** [bootstrap table 技术文档](http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation "技术文档")

----------

**后续引入 redis、rabbitmq 等技术栈**


# 启动

- **将 `doc/battcn3.0.sql` 导入到 `version >= mysql5.6`的数据库中,如果版本低于 `5.6` 请仔细看 sql 文件的注释部分**
- 将 `battcn-parent` 转换成 `Maven Project` 然后 执行 **`install`** 
- 启动 `battcn-provider/battcn-system-provider/SystemProvider.java`   
- 启动 `battcn-consumers/battcn-system-consumer/ManagementApplication.java`
- **以上步骤成功后,访问 http://localhost:8082/ 即可** 

# 存在缺陷

**`由于 dubbo` 对 yml 配置存在缺陷,所以该阶段只能启动一个 provider 做测试,根据官方 issues 该问题应该会在下一个版本得到解决**
