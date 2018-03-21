package com.battcn.framework.redis.sequence;

import org.springframework.util.StringUtils;

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
    private SequenceRedisHelper redisHelper;
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
        Integer value = redisHelper.incr(key);
        return key + String.format("%05d", value);
    }

    public String generateSequence(SequenceType sequenceType) {
        final String profile = redisSequenceProperties.getProfile();
        if (StringUtils.isEmpty(profile)) {
            return generateNowSequence(sequenceType.getCode());
        }
        return generateNowSequence(sequenceType.getCode() + profile);
    }


    public void setRedisHelper(SequenceRedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    public void setRedisSequenceProperties(RedisSequenceProperties redisSequenceProperties) {
        this.redisSequenceProperties = redisSequenceProperties;
    }
}
