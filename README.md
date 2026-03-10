# Wemirr Platform

<div align="center">

[![个人信息](https://img.shields.io/badge/Author-唐亚峰-blue.svg)](http://blog.battcn.com/)
[![项目交流群](https://img.shields.io/badge/Chat-项目交流群-yellow.svg)](https://qm.qq.com/cgi-bin/qm/qr?k=7HSbjhK2b23CyodXUX2tR_ldigjrAlPT&jump_from=webapi)
![JDK Version](https://img.shields.io/badge/JDK-21+-red.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

![Total visits](https://visitor-badge.laobi.icu/badge?page_id=wemirr-platform&title=total%20visits)
[![Total lines](https://tokei.rs/b1/github/battcn/wemirr-platform?category=lines)](https://tokei.rs/b1/github/battcn/wemirr-platform?category=lines)
[![Stars](https://img.shields.io/github/stars/battcn/wemirr-platform.svg?label=Stars&style=flat&logo=GitHub)](https://github.com/battcn/wemirr-platform/stargazers)
[![Gitee Stars](https://gitee.com/battcn/wemirr-platform/badge/star.svg?theme=gvp)](https://gitee.com/battcn/wemirr-platform/stargazers)

[![Spring Boot](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-dependencies.svg?label=Spring%20Boot&logo=Spring)](https://search.maven.org/artifact/org.springframework.boot/spring-boot-dependencies)
[![Spring Cloud](https://img.shields.io/maven-central/v/org.springframework.cloud/spring-cloud-dependencies.svg?label=Spring%20Cloud&logo=Spring)](https://search.maven.org/artifact/org.springframework.cloud/spring-cloud-dependencies)
[![Spring Cloud Alibaba](https://img.shields.io/maven-central/v/com.alibaba.cloud/spring-cloud-alibaba-dependencies.svg?label=Spring%20Cloud%20Alibaba&logo=Spring)](https://search.maven.org/artifact/com.alibaba.cloud/spring-cloud-alibaba-dependencies)

</div>

> **🚀 WP的使命**：打破假开源，做全网最优秀、最简单、最漂亮的开源 SAAS 多租户云平台架构。从开源中来，到开源中去，让大家有更多时间 `摸鱼`、`学习`、`陪伴家人`、`锻炼身体`

**说实话，AI时代，脚手架基本死了，之所以还在维护，也只不过是方便大家快速应用和落地，AI时代，文档已经没意义了，大家直接让AI读自己不懂的地方就行了。**

---

## 🙏 致谢

**欢迎更多朋友来添砖加瓦，在大 AI 时代来临之前留下浓墨一笔！**

| 来源  | 贡献者   | 贡献内容         |
|-----|-------|--------------|
| QQ群 | ddCat | WMS 核心功能     |
| 微信群 | 千里风   | 提供 PR 环境服务器  |


## 📋 版本管理

> 项目代码**全部开源**，非阉割版，有 Spring Cloud 工作经验的无需付费协助一样可以玩转项目

| 版本 | JDK | 技术栈                                          | 状态 | 说明 |
|------|-----|----------------------------------------------|------|------|
| **v4-dev** | 21 | SCA2025 / SC2025 / Sa-Token / warm-flow / AI | 🔥 推荐 | 新用户首选，后期重心 |
| v3-dev | 17 | SCA2023 / SC2024 / Sa-Token / Camunda        | ✅ 维护 | 稳定版本 |
| v2-dev | 17 | SCA2022 / SC2023 / OAuth2.1 / Security       | 🔧 仅修复 | 仅提供BUG修复 |
| ~~v1-dev~~ | 8-11 | -                                            | ❌ 废弃 | 不再维护 |

> ⚠️ **注意**：v2 → v3 是破坏性升级，不推荐已稳定运行的系统升级

### 📚 文档与支持

| 资源 | 链接                                                                                              |
|------|-------------------------------------------------------------------------------------------------|
| 📖 开发文档 | [docs.battcn.com](https://docs.battcn.com)                                                      |
| 🎯 演示地址 | [cloud.battcn.com](https://cloud.battcn.com/)、[h5.battcn.com](https://h5.battcn.com/)(H5 移动APP) |
| 💬 技术交流群 | QQ群：789517089                                                                                   |
| 📱 微信 | battcn2022                                                                                      |

**演示账号**：
- 平台管理员：租户码 `0000`，账号 `admin`，密码 `123456`
- 租户管理员：租户码 `8888`，账号 `admin`，密码 `123456`

---

## 🏗️ 技术架构

<div align="center">

`Vue3` `Spring Cloud 2024` `Spring Cloud Alibaba 2023` `Nacos` `Sentinel` `MyBatis-Plus` `Sa-Token` `Redis` `MySQL`

</div>

### ✨ 核心特性

| 特性 | 描述 |
|------|------|
| 🔗 **链路追踪** | 支持 SkyWalking / Zipkin / Pinpoint 等多种方案 |
| 🎨 **优雅布局** | 简洁美观，多套主题任意组合搭配 |
| 🔐 **权限控制** | SAAS / 多租户 / RBAC 权限控制开箱即用 |
| 📡 **消息推送** | 基于 WebSocket + Redis 的分布式消息 |
| 🚪 **动态网关** | 支持 Redis / Nacos 配置，动态路由管理 |
| 📦 **插拔组件** | 按需引入 `wemirr-platform-plugin` 模块 |
| ⚡ **高性能** | 2M 网络下接口响应普遍 10-150ms |
| 📝 **标准文档** | 集成 SpringDoc (Swagger V3) |
| ⏰ **分布式任务** | 集成 snail-job 分布式调度 |
| 🤖 **AI 集成** | 基于 Langchain4j 集成 RAG / Tools / MCP（开发中） |

### 🚀 快速开发

Vue 开发只需几行代码即可完成单表 CRUD：

```vue
<template>
  <fs-crud ref="crudRef" v-bind="crudBinding"/>
</template>
```

---

## 🔗 相关链接

| 名称 | 链接 |
|------|------|
| 配套前端 | [gitee.com/battcn/wemirr-platform-ui](https://gitee.com/battcn/wemirr-platform-ui) |
| OpenAPI3 | [springdoc.org](https://springdoc.org/) |

---

## 🛠️ 快速开始

### 1. 编译安装

```bash
# 克隆项目
git clone https://gitee.com/battcn/wemirr-platform.git
cd wemirr-platform

# 安装依赖（必须按顺序执行）
mvn install -f wemirr-platform-dependencies/pom.xml -DskipTests
mvn install -f wemirr-platform-framework/pom.xml -DskipTests
```

### 2. 环境准备

#### 方式一：Docker Compose（推荐）

```bash
# 创建网络
docker network create wemirr

# 一键启动所有服务
cd 附件/docker
docker-compose up -d
```

#### 方式二：手动安装

<details>
<summary>📦 <b>必要环境</b>（点击展开）</summary>

```bash
# 创建 Docker 网络
docker network create wemirr

# Redis
docker run -d --name redis \
  --net wemirr \
  -p 6379:6379 \
  --restart always \
  redis:7-alpine

# MySQL 8.0
docker run -d --name mysql \
  --net wemirr \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=123456 \
  -e TZ=Asia/Shanghai \
  -v mysql_data:/var/lib/mysql \
  --restart always \
  mysql:8.0 \
  --character-set-server=utf8mb4 \
  --collation-server=utf8mb4_unicode_ci

# Nacos 2.x
docker run -d --name nacos \
  --net wemirr \
  -p 8848:8848 \
  -p 9848:9848 \
  -p 9849:9849 \
  -e MODE=standalone \
  -e NACOS_AUTH_ENABLE=false \
  --restart always \
  nacos/nacos-server:v2.3.0

# RabbitMQ（可选，消息总线需要）
docker run -d --name rabbitmq \
  --net wemirr \
  -p 5672:5672 \
  -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=admin \
  -e RABBITMQ_DEFAULT_PASS=admin \
  --restart always \
  rabbitmq:3-management
```

</details>

<details>
<summary>🔧 <b>可选环境</b>（点击展开）</summary>

```bash
# Sentinel Dashboard（流量控制）
docker run -d --name sentinel \
  --net wemirr \
  -p 8858:8858 \
  -p 8719:8719 \
  --restart always \
  bladex/sentinel-dashboard

# SkyWalking（链路追踪）
# 1. Elasticsearch
docker run -d --name elasticsearch \
  --net wemirr \
  -p 9200:9200 \
  -p 9300:9300 \
  -e "discovery.type=single-node" \
  -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
  --restart always \
  elasticsearch:7.17.10

# 2. SkyWalking OAP Server
docker run -d --name skywalking-oap \
  --net wemirr \
  -p 11800:11800 \
  -p 12800:12800 \
  -e SW_STORAGE=elasticsearch \
  -e SW_STORAGE_ES_CLUSTER_NODES=elasticsearch:9200 \
  -e TZ=Asia/Shanghai \
  --restart always \
  apache/skywalking-oap-server:9.5.0

# 3. SkyWalking UI
docker run -d --name skywalking-ui \
  --net wemirr \
  -p 10086:8080 \
  -e SW_OAP_ADDRESS=http://skywalking-oap:12800 \
  -e TZ=Asia/Shanghai \
  --restart always \
  apache/skywalking-ui:9.5.0
```

</details>

### 3. SkyWalking 集成配置

<details>
<summary>🔍 <b>IDEA 开发环境配置</b>（点击展开）</summary>

**VM Options:**
```
-javaagent:/path/to/skywalking-agent/skywalking-agent.jar
-Dskywalking.agent.service_name=wemirr-platform-gateway
-Dskywalking.collector.backend_service=127.0.0.1:11800
```

**生产环境启动命令:**
```bash
# Gateway 服务
nohup java \
  -javaagent:/opt/skywalking/agent/skywalking-agent.jar \
  -Dskywalking.agent.service_name=wemirr-platform-gateway \
  -Dskywalking.collector.backend_service=127.0.0.1:11800 \
  -jar wemirr-platform-gateway.jar \
  > logs/gateway.log 2>&1 &

# IAM 服务
nohup java \
  -javaagent:/opt/skywalking/agent/skywalking-agent.jar \
  -Dskywalking.agent.service_name=wemirr-platform-iam \
  -Dskywalking.collector.backend_service=127.0.0.1:11800 \
  -jar wemirr-platform-iam.jar \
  --spring.profiles.active=prod \
  > logs/iam.log 2>&1 &
```

</details>

---

## 💼 商业服务

> 开源永远是第一位，业务模块可付费购买以降低企业成本

- ✅ 模块定制化开发
- ✅ 远程技术支持
- ✅ 私有化部署协助
- ✅ BUG 反馈和运行报错**无偿技术支持**（包括远程协助）

**联系方式**：微信 `battcn2022`
