## Mybatis 枚举映射

> 用法一（Mybatis-Plus 内置API）

创建一个枚举实现 `com.wemirr.framework.db.mybatisplus.core.DictionaryEnum<T>` 接口 

```
@TableField(value = "sex")
private Sex sex;
```

> 用法二（Mybatis 自定义XML SQL ）

定义通用 `EnumTypeHandler` 只要是实现了 `DictionaryEnum<T>` 接口的枚举都可以用如下写法做枚举映射

``` xml
<resultMap id="xxxx" type="com.xxx.xxx">
    <id column="id" property="id"/>
    <result column="sex" property="sex" javaType="com.wemirr.platform.authority.domain.enums.Sex"
        typeHandler="com.wemirr.framework.db.mybatisplus.handler.type.EnumTypeHandler"/>
</resultMap>
```