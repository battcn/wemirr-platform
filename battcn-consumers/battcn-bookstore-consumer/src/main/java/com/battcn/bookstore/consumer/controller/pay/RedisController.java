package com.battcn.bookstore.consumer.controller.pay;

import com.battcn.framework.redis.annotation.CacheLimit;
import com.battcn.framework.redis.annotation.CacheLock;
import com.battcn.framework.redis.annotation.CacheParam;
import com.battcn.framework.redis.cache.CacheService;
import com.battcn.framework.redis.limit.LimitType;
import com.battcn.framework.redis.sequence.SequenceGenerator;
import com.battcn.framework.redis.sequence.SequenceType;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @CacheLimit(name = "ip", limitType = LimitType.CUSTOMER, count = 10, period = 100, delimiter = "_")
    @PostMapping
    public String limit(@CacheParam @RequestParam int num) {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

    @GetMapping
    public Set<RedisZSetCommands.Tuple> cache(String bookNo, String title) {
        String key = StringUtils.join("books:", bookNo);
        cacheService.hset(key, "title", title);
        cacheService.zincrby("score", 1, key);
        final Set<RedisZSetCommands.Tuple> score = cacheService.zrevrangeWithScores("score", 0, -1);

        return cacheService.zrevrangeWithScores("score", 0, -1);
    }


}
