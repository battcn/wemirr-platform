## 使用方式

> 添加依赖

``` xml
<dependency>
    <groupId>com.wemirr.framework</groupId>
    <artifactId>easyexcel-spring-boot-starter</artifactId>
</dependency>
 
```

### 用法一（ExcelWriteFile）

**根据实际业务需要,自己设置写入的文件和Sheet 内容,方便动态赋值**

``` java
@PostMapping("/export")
public ExcelWriteFile exportList(@RequestBody UserPageReq req) {
    // 可以根据业务需要自己构建导出对象
    return ExcelWriteFile.builder().fileName("文件名称").data(list).build();
}
```


### 用法二

**注解导出，省时省力，缺点就是不能根据条件动态去设置文件和Sheet**

``` java
@PostMapping("/export")
@ResponseExcel(fileName = "用户列表")
public List<User> exportList() {
    // 直接返回需要导出的集合即可
    return list;
}
```