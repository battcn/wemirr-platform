## 导入核心包

本系统基于 `Spring官方`提供的最新SDK `spring-security-oauth2-authorization-server:1.1.2` 集成封装,简化开发者使用，提供的扩展配置请开发者自行看注释 

**即便不用全部的架构体系，只用`oauth2-spring-boot-starter` 也可以轻松自定义 自己内部的oauth2 系统架构**

``` xml
<dependency>
    <groupId>com.wemirr.framework</groupId>
    <artifactId>oauth2-spring-boot-starter</artifactId>
</dependency> 
```

### 服务端配置使用

添加注解 **`@EnableOAuth2Server`** 标记当前项目为**鉴权服务端** 

``` xml
extend:
  oauth2:
    server:
      type: redis
      registered-client: jdbc
      consent: jdbc
      token-type: jwt
  ignore:
    # 配置权限过滤的地址
    resource-urls:
      - /captcha
      - /instances/**

```


#### 自定义登录类型

**默认支持OAuth2.1 标准的登录模式**

> 登录类型扩展

**实现 `IntegrationAuthenticator` 接口即可自定义N种基于 `grant_type:custom` 方式的自定义登录**，该项目默认提供了基于用户名密码和图文验证码方式，微信/短信可以参考图文依样画葫芦

```` 
@Slf4j
@Component
public class XXXXXXAuthenticator implements IntegrationAuthenticator {

    @Override
   public void prepare(IntegrationAuthentication integrationAuthentication) {
        // 你的验证逻辑处理（比如参数合法性、验证码是否合法 等等）
   }

    @Override
    public UserInfoDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        // 你的查询逻辑,最终返回 UserInfoDetails 就可以
    }
}
````





### 客户端配置使用

添加注解 **`@EnableOAuth2Client`** 标记当前项目为**资源客户端**

> 配置

``` xml
extend:
  oauth2:
    client:
      opaque-token:
        type: redis
```