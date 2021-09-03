## 环境安装

一般安装 `latest` 版本即可，也可以自行指定版本 `docker search` 或者自己上 `docker hub` 看版本

如果 `docker` 运行开发环境，建议先创建一个网络 `docker network create wemirr` 后面容器都走统一网络

**提供 `docker-compose.yml` 安装（文件在`附件/docker/docker-compose.yml`）**

## 必要组件

- 创建网络: **`docker run --net wemirr --name xxx`**

**最小中间件依赖，`数据库`、`缓存`,`Naocs`** 当然你也可以切换其它注册中心或者配置中心,这样需要开发者自己去修改了

``` shell
安装 Redis （>=3.0）
docker pull redis:latest
docker run -itd --name redis -p 6379:6379 redis

安装 Mysql （>= 5.7）
docker pull mysql:latest
docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql

安装 Nacos （>=2.0）
docker pull nacos/nacos-server
docker  run --name nacos -d-p 8848:8848 --restart=always -e MODE=standalone
```

## 可选组件

> 消息总线（动态数据源租户）

*多租户 => 动态数据源之间同步用到了消息总线* 本项目默认使用 `RabbitMQ` 做消息总线和消息队列，如与公司中间件不符可自行修改 

```shell
docker pull docker.io/macintoshplus/rabbitmq-management
docker run -d  -p 5671:5671 -p 5672:5672  -p 15672:15672 -p 15671:15671  -p 25672:25672  rabbitmq_image_id
```

> 流量控制

默认使用 `Spring Cloud Alibaba` 做流量保护，也可自行切换成 `Hystrix`

```shell

docker pull bladex/sentinel-dashboard
docker run -i -t -d -p 8858:8858 -p 8719:8719  bladex/sentinel-dashboard
```

> 定时任务

- 方案一：运行 `wemirr-platform-options/wemirr-platform-job-admin`
- 方案二：安装 XXL-JOB-ADMIN (如果数据库也是docker 运行需要配置统一网络 例如 `docker network create wemirr` )

```shell
docker pull xuxueli/xxl-job-admin:2.3.0
docker run --net wemirr -e PARAMS="--spring.datasource.username=root --spring.datasource.password=123456 --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver --spring.datasource.url=jdbc:mysql://127.0.0.1:3306/wemirr-platform?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true" -p 9999:8080 -v /Users/battcn/Development/xxl-job/data/applogs:/data/applogs --name xxl-job-admin  -d xuxueli/xxl-job-admin:2.3.0
```

> 链路追踪 

安装 skywalking-es7（**8.7.0 有问题,别安装哦**）

```shell
# 如果已执行请忽略
docker network create wemirr

docker pull elasticsearch:7.9.3
docker pull apache/skywalking-oap-server:8.5.0-es7
docker pull apache/skywalking-ui:8.5.0

docker run --name elasticsearch --net wemirr -p 9200:9200 -p 9300:9300 -d -e "discovery.type=single-node" elasticsearch:7.9.3
docker run --name oap --net wemirr --restart always -p 1234:1234 -p 12800:12800 -p 11800:11800 -d -e SW_STORAGE=elasticsearch7 -e SW_STORAGE_ES_CLUSTER_NODES=elasticsearch:9200 apache/skywalking-oap-server:8.5.0-es7
docker run --name oap-ui --net wemirr --restart always -p 10086:8080 -d -e TZ=Asia/Shanghai -e SW_OAP_ADDRESS=oap:12800 apache/skywalking-ui:8.5.0
```

- IDEA 配置

``` properties
VmOption -javaagent:/Users/battcn/Desktop/apache-skywalking-apm-bin/agent/skywalking-agent.jar
Environment variables SW_AGENT_NAME=wemirr-platform-gateway
Environment variables SW_AGENT_NAME=wemirr-platform-authority
```

- 启动命令

``` shell
nohup java -javaagent:/opt/wemirr-platform/skywalking/agent/skywalking-agent.jar -Dskywalking.agent.service_name=wemirr-platform-gateway -Dskywalking.collector.backend_service=127.0.0.1:11800 -jar wemirr-platform-gateway.jar -d > logs/start_gateway.log &
nohup java -javaagent:/opt/wemirr-platform/skywalking/agent/skywalking-agent.jar -Dskywalking.agent.service_name=wemirr-platform-authority -Dskywalking.collector.backend_service=127.0.0.1:11800 -jar wemirr-platform-authority.jar -d --spring.profiles.active=demo > logs/start_authority.log &
```

> 低码平台

**如果需要体验低码平台一键发布需要安装 `MongoDB` 除此之外其它中间件是必须的**



