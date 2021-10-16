package com.wemirr.framework.redis.plus.sequence;


import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class RedisSequenceHelper {

    private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter YYYYMM = DateTimeFormatter.ofPattern("yyyyMM");

    private final StringRedisTemplate redisTemplate;

    public String generate(Sequence sequence, KeyRule keyRule, int size) {
        String localDate;
        if (keyRule == null) {
            localDate = null;
        } else if (keyRule == KeyRule.YYYY_MM) {
            localDate = LocalDate.now().format(YYYYMM);
        } else {
            localDate = LocalDate.now().format(YYYYMMDD);
        }
        Long increment = redisTemplate.opsForValue().increment(StrUtil.join(sequence.key(), localDate));
        return StrUtil.join(sequence.prefix(), localDate, StrUtil.padAfter(increment + "", size, '0'));
    }

    public String generate6Number(String key) {
        String localDate = LocalDate.now().format(YYYYMMDD);
        Long increment = redisTemplate.opsForValue().increment(key + localDate);
        return StrUtil.padAfter(increment + "", 6, '0');
    }

    public String generateNumber(String key, int size) {
        Long increment = redisTemplate.opsForValue().increment(key);
        return StrUtil.padAfter(increment + "", size, '0');
    }


    /**
     * @author Levin
     */
    @Getter
    @NoArgsConstructor
    @JsonFormat
    public enum KeyRule {
        /**
         * 年月日
         */
        YYYY_MM_DD,
        /**
         * 年月
         */
        YYYY_MM,

    }


}
