## 多租户 - 字段隔离

**字段隔离：在需要对租户数据隔离的SQL添加租户字段进行数据过滤**

``` yaml
# 表示字段隔离
extend.mybatis-plus.multi-tenant.type=column
# 表示需要进行租户数据隔离的表名(逗号分隔)
extend.mybatis-plus.multi-tenant.include-tables=t_user,t_file
```


## 多租户 - 数据源切换

**数据源隔离：没个租户独立数据源，使用 `Spring Cloud Bus` 动态切换分配数据源，项目初始化也会加载所有数据源**

``` yaml
# 表示数据源隔离
extend.mybatis-plus.multi-tenant.type=datasource
# 非 authority 模块都配置成 feign 即可
extend.mybatis-plus.multi-tenant.strategy=feign
```

> 默认数据源配置

``` properties
spring.datasource.dynamic.primary=master
spring.datasource.dynamic.strict=true
spring.datasource.dynamic.datasource.master.pool-name=HikariDataSourcePool
spring.datasource.dynamic.datasource.master.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.dynamic.datasource.master.url=jdbc:mysql://localhost:3306/wemirr-platform?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.dynamic.datasource.master.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.dynamic.datasource.master.username=root
spring.datasource.dynamic.datasource.master.password=123456
spring.datasource.dynamic.hikari.max-pool-size=15
spring.datasource.dynamic.hikari.max-lifetime=1800000
spring.datasource.dynamic.hikari.connection-timeout=30000
spring.datasource.dynamic.hikari.min-idle=5
spring.datasource.dynamic.hikari.is-auto-commit=true
```

### 使用方式

**在 Mapper/Service/Controller 处添加 `@DynamicDS` 注解，即可根据规则动态切换数据源了**

### 注意事项

**如果多租户类型不是`动态数据源方案`，请代码注释掉 `@DynamicDS` 注解或注释掉里面的 `@DS("#custom.tenant_code")` 代码即可**


### 自定义规则

如果你觉得默认方案不符合业务场景要求，可以自己修改 `TenantDynamicDataSourceProcess` 与 `TenantDynamicDataSourceEventBusAutoConfiguration` 或者联系作者进行兼容性处理（*非通用场景不考虑*）
