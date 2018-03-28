package com.battcn.framework.redis.sequence;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>序列生成器</p>
 *
 * @author Levin
 * @since 2018/03/20
 */

public class SequenceGenerator {

    private static final String DATE_FORMAT = "YYYYMMdd";
    private RedisTemplate<String, Serializable> lockRedisTemplate;
    private RedisSequenceProperties redisSequenceProperties;

    /**
     * 根据前置key和日期生成序列号。
     * <br>规则为：prekey + dateTime + 序列, 如：PM2017030900001
     *
     * @param preKey 前缀
     * @param date   日期
     * @author Levin
     */
    public String generateDateSequence(String preKey, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        String today = simpleDateFormat.format(date);
        return generateSequence(preKey + today);
    }

    public String generateNowSequence(String preKey) {
        return generateDateSequence(preKey, new Date());
    }

    private String generateSequence(String key) {
        Long value = lockRedisTemplate.opsForValue().increment(key, 1L);
        return key + String.format("%05d", value);
    }

    public String generateSequence(SequenceType sequenceType) {
        final String profile = redisSequenceProperties.getProfile();
        if (StringUtils.isEmpty(profile)) {
            return generateNowSequence(sequenceType.getCode());
        }
        return generateNowSequence(sequenceType.getCode() + profile);
    }


    public void setLockRedisTemplate(RedisTemplate<String, Serializable> lockRedisTemplate) {
        this.lockRedisTemplate = lockRedisTemplate;
    }

    public void setRedisSequenceProperties(RedisSequenceProperties redisSequenceProperties) {
        this.redisSequenceProperties = redisSequenceProperties;
    }
}
