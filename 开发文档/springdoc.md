## Spring Doc

**SpringDoc 基于 swagger，并做了更多的对 Spring 系列各框架的兼容，用法与 Swagger3 基本相同，并多了一些自己的配置，相较于Swagger3来说，好用，支持更好**

> `swagger-spring-boot-starter` 基于 `SpringDoc` 做了增强,相关属性(`springdoc.plus.xxxx`)

``` properties
# 应用名称,建议同 ${spring.application.name}
springdoc.swagger-ui.oauth.app-name=authority
springdoc.swagger-ui.oauth.client-id=client
springdoc.swagger-ui.oauth.client-secret=client
springdoc.swagger-ui.oauth.use-pkce-with-authorization-code-grant=true
springdoc.plus.info.title=Swagger API
springdoc.plus.info.description=聚合文档
springdoc.plus.info.terms-of-service=https://blog.battcn.com/
springdoc.plus.info.license.name=apache
springdoc.plus.info.license.url=apache
springdoc.plus.info.contact.name=唐亚峰
springdoc.plus.info.contact.url=https://cloud.battcn.com/
springdoc.plus.info.contact.email=1837307557@qq.com
# 授权类型（标准 OAuth2.0 登录）
springdoc.plus.security-schemes.oauth2.type=oauth2
springdoc.plus.security-schemes.oauth2.name=oauth2
springdoc.plus.security-schemes.oauth2.in=header
springdoc.plus.security-schemes.oauth2.bearer-format=Bearer
springdoc.plus.security-schemes.oauth2.flows.password.scopes.server=server
# 登录地址,返回的数据格式必须要有 access_token 
springdoc.plus.security-schemes.oauth2.flows.password.token-url=http://localhost:5002/oauth/token?tenant_code=0000
```

|  Swagger2      |  OpenAPI3  | 注解位置  |
|  ----  | ----  | ----  |
| `@Api`  | @Tag(name = "接口类描述") | Controller 类上  |
| `@ApiOperation`  | @Operation(summary ="接口方法描述") | Controller 方法上  |
| `@ApiImplicitParam`  | @Parameter(description="参数描述") | Controller 方法上 @Parameters 里  |
| `@ApiImplicitParams`  | @Parameters |  Controller 方法上 |
| `@ApiParam`  | @Parameter(description="参数描述") | Controller 方法的参数上  |
| `@ApiIgnore`  | @Parameter(hidden = true) 或 @Operation(hidden = true) 或 @Hidden | ----  |
| `@ApiModel`  | @Schema	 | DTO类上  |
| `@ApiModelProperty`  | @Schema	 | DTO属性上  |


