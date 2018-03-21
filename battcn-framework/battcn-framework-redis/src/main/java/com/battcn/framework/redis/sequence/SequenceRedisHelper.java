package com.battcn.framework.redis.sequence;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * ClassName:StringRedisHelper <br/>
 *
 * @author Levin
 * @since 2018/03/20
 */
public class SequenceRedisHelper {


    private StringRedisTemplate sequenceRedisTemplate;


    public String get(String key) {
        return sequenceRedisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value) {
        sequenceRedisTemplate.opsForValue().set(key, value.toString());
    }

    public boolean setnx(String key, String value) {
        return sequenceRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * key 中储存的数字值增1（该操作具有原子性）
     *
     * @param key 自增的Key
     * @return 增加后对应的值
     * @author Levin
     */
    public Integer incr(String key) {
        Long result = sequenceRedisTemplate.opsForValue().increment(key, 1L);
        return result.intValue();
    }

    /**
     * key 中储存的数字值增加指定值（该操作具有原子性）
     *
     * @param key    自增Key
     * @param addVal 自增的数值
     * @return 增加后对应的值
     * @author Levin
     */
    public Integer incr(String key, int addVal) {
        Long result = sequenceRedisTemplate.opsForValue().increment(key, addVal);
        return result.intValue();
    }

    public boolean setBit(String key, int offset) {
        return sequenceRedisTemplate.opsForValue().setBit(key, offset, true);
    }

    public Long bitCount(String key) {
        return sequenceRedisTemplate.getConnectionFactory().getConnection().bitCount(key.getBytes());
    }

    public StringRedisTemplate getSequenceRedisTemplate() {
        return sequenceRedisTemplate;
    }

    public void setSequenceRedisTemplate(StringRedisTemplate sequenceRedisTemplate) {
        this.sequenceRedisTemplate = sequenceRedisTemplate;
    }
}

