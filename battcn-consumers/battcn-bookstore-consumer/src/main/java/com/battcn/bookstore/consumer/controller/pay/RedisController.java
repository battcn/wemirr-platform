package com.battcn.bookstore.consumer.controller.pay;

import com.battcn.framework.redis.annotation.CacheLimit;
import com.battcn.framework.redis.annotation.CacheLock;
import com.battcn.framework.redis.annotation.CacheParam;
import com.battcn.framework.redis.cache.CacheService;
import com.battcn.framework.redis.limit.LimitType;
import com.battcn.framework.redis.sequence.SequenceGenerator;
import com.battcn.framework.redis.sequence.SequenceType;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 缓存测试
 *
 * @author Levin
 * @since 2018/3/21 0021
 */
@RequestMapping("/app/redis")
@RestController
@Api(value = "缓存管理", description = "缓存管理", tags = "3.0")
public class RedisController {

    private final SequenceGenerator sequenceGenerator;
    private final CacheService cacheService;

    @Autowired
    public RedisController(SequenceGenerator sequenceGenerator, CacheService cacheService) {
        this.sequenceGenerator = sequenceGenerator;
        this.cacheService = cacheService;
    }

    @CacheLock(prefix = "book:num", expire = 100)
    @PutMapping
    public String lock(@CacheParam @RequestParam int num) {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

    @CacheLimit(name = "ip", limitType = LimitType.CUSTOMER, count = 10, period = 100,delimiter = "_")
    @PostMapping
    public String limit(@CacheParam @RequestParam int num) {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

    @GetMapping
    public String cache(String key, String value) {
        cacheService.add(key, value);
        return (String) cacheService.get(key);
    }


}
