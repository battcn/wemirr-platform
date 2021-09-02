# 2.0.0-beta.3 (2020-10-07)

### ✨ Features

- 项目配置文件新增`openNProgress`用于控制是否开启顶部控制条
- 添加`table`组件及 demo

### 🎫 Chores

- 添加` github workflows`

# 2.0.0-beta.2 (2020-10-07)

### ✨ Features

- 新增图片预览组件

### 🔧 Continuous Integration

- 增加 githubAction 脚本

# v_saas_1.0 (2021-09-02)

基于 `VBen UI` 实现的多租户SAAS中台，开箱即用，动态数据源、字段隔离等多种租户形态

### 🎫 Chores

- 从 d_pass_v1.0 迁移部分代码
- 实现`RBAC` 权限
- 统一的认证授权中心服务(`authority`)
- 升级Swagger V2 => SpringDoc 
- 升级Spring Cloud 2020.3、Spring Cloud Alibaba 2021
- `Spring Cloud Gateway` 网关二次开发支持 `动态网关`、`黑名单`、`全局动态限流`
- 支持多种云存储
- 封装 `xxl-job、websocket、springdoc` 等 `starter` 包
- 添加 README.md 描述文件

### 🔧 Continuous Integration

- 增加 开发文档
- 增加 `docker-compose.yml` 一件安装开发环境
- 增加 `wemirr-platform.sql` 数据库脚本文件

### 🐛 Bug Fixes

- 修复动态数据源切换
