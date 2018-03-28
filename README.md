## 开始维护

[http://demo.battcn.com](http://demo.battcn.com "演示地址")

# 必读

鉴于 `dubbo-boot` 存在不少问题，故暂时以功能实现为主,暂时未做代码分包处理,本人会在新版`dubbo-boot`发布的第一时间修改项目中不规范的地方 

鉴于 `dubbo` 还未支持 `spring-boot2` 所以`battcn-framework-elsasticsearch` 暂时未进行开发

# 新增功能

- 添加 **`JWT` 认证（`battcn-bookstore-consumer`）**
- 基于`Redis`封装,唯一序列号生成与分布式`CacheLock`
- 新增章节模板
- 生成书籍文章
- 添加了书籍爬虫(亲测抓取3000本书)
- 自行维护代理IP池（自动清洗,自动抓取）


# 开发内容

目前会编写一个 书城  涵盖 `爬虫、代理、管理系统、阅读、下载` 本项目会分为 `dubbo` 与 `springcloud` 版本，初期以 `dubbo`为主


鉴于 `dubbo` 已经开始维护了 , 本项目也即将开启维护之旅

由于 `dubbo-spring-boot-starter（现在还是开发版）`，所以暂未升级 `spring-boot2.x`,依旧采用的`1.5.10.RELEASE` 版本


# 模块 battcn-framework

为了简化开发,提高开发效率,代码整洁,特意封装了几个组件

- **`battcn-framework-commons`：** 基础工具类,不依赖`Servlet` 包
- **`battcn-framework-core`：** 封装了*`跨域`、`RequestUtils`、`全局通用异常`、*
- **`battcn-framework-mybatis`：** 封装了*`分页插件`、`通用Mapper`、`通用Service`、*
- **`battcn-framework-redis`：** 封装了*`分布式限流`、`分布式锁`、`唯一序列`、*
- **`battcn-framework-security`：** 封装了*`JWT`、`自定义权限注解`、*
- **`battcn-framework-webmagic`：** 封装了*`webmagic` 爬虫功能*


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
