package com.battcn.framework.redis.lock;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * <p>RedisLockProperties</p>
 *
 * @author Levin
 * @since 2018/03/20
 */
@ConfigurationProperties(prefix = "spring.redis.battcn.lock")
public class RedisLockProperties {

    /**
     * 默认Redis的DB
     */
    private int  db = 0;
    /**
     * <p>Redis运行环境(比如同一台Redis,其目的是为了多环境公用一台redis但生成的Key又不会冲突</p>
     * <p>test：生成的Key就是 BOdev201801032001</p>
     * <p>prod：生成的Key就是 BOprod201801032001</p>
     */
    private String profile;

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
