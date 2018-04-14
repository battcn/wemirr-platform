package com.battcn.bookstore.consumer.controller.pay;

import com.battcn.framework.redis.annotation.CacheLimit;
import com.battcn.framework.redis.annotation.CacheLock;
import com.battcn.framework.redis.annotation.CacheParam;
import com.battcn.framework.redis.limit.LimitType;
import com.battcn.framework.redis.sequence.SequenceGenerator;
import com.battcn.framework.redis.sequence.SequenceType;
import com.battcn.framework.security.annotation.IgnoreAuthenticate;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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
    private final RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    public RedisController(SequenceGenerator sequenceGenerator, RedisTemplate<String, Serializable> redisCacheTemplate) {
        this.sequenceGenerator = sequenceGenerator;
        this.redisCacheTemplate = redisCacheTemplate;
    }

    @IgnoreAuthenticate
    @CacheLock(prefix = "book:num", expire = 100)
    @PutMapping
    public String lock(@CacheParam @RequestParam int num) {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

    @IgnoreAuthenticate
    @CacheLimit(name = "ip", limitType = LimitType.CUSTOMER, count = 10, period = 100, delimiter = "_")
    @PostMapping
    public String limit(@CacheParam @RequestParam int num) {
        return this.sequenceGenerator.generateSequence(SequenceType.BO);
    }

    @IgnoreAuthenticate
    @GetMapping
    public Set<ZSetOperations.TypedTuple<Serializable>> cache(String bookNo, String title) {
        String key = StringUtils.join("books:", bookNo);
        redisCacheTemplate.opsForHash().put(key, "title", title);
        redisCacheTemplate.opsForZSet().incrementScore("score", key, 1);
        return redisCacheTemplate.opsForZSet().reverseRangeWithScores("score", 0, -1);
    }


}
