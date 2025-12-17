package com.wemirr.framework.mongodb.annotation;

import java.lang.annotation.*;

/**
 * 动态MongoDB数据源注解
 * <p>标记需要进行动态数据源切换的方法或类</p>
 *
 * <pre>{@code
 * @Service
 * public class UserService {
 *
 *     @Autowired
 *     private MongoTemplate mongoTemplate;
 *
 *     // 自动根据租户切换数据源
 *     @DynamicMongo
 *     public List<User> findAll() {
 *         return mongoTemplate.findAll(User.class);
 *     }
 *
 *     // 指定数据源
 *     @DynamicMongo(value = "tenant_001")
 *     public User findById(String id) {
 *         return mongoTemplate.findById(id, User.class);
 *     }
 * }
 * }</pre>
 *
 * @author Levin
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicMongo {

    /**
     * 数据源标识（为空时自动从当前租户获取）
     */
    String value() default "";

    /**
     * 是否强制使用指定数据源（忽略租户上下文）
     */
    boolean force() default false;
}
