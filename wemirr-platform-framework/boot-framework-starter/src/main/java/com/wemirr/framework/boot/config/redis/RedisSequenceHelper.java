package com.wemirr.framework.boot.config.redis;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wemirr.framework.database.mybatis.DictionaryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Levin
 */
@Slf4j
@Component
public class RedisSequenceHelper {

    private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter YYYYMM = DateTimeFormatter.ofPattern("yyyyMM");

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String generate(DictionaryEnum<String> sequence, KeyRule keyRule, int size) {
        String localDate;
        if (keyRule == null) {
            localDate = null;
        } else if (keyRule == KeyRule.YYYY_MM) {
            localDate = LocalDate.now().format(YYYYMM);
        } else {
            localDate = LocalDate.now().format(YYYYMMDD);
        }
        Long increment = stringRedisTemplate.opsForValue().increment(StringUtils.join(sequence.getDesc(), localDate));
        return StringUtils.join(sequence.getValue(), localDate, StringUtils.leftPad(increment + "", size, '0'));
    }

    public String generate6Number(String key) {
        String localDate = LocalDate.now().format(YYYYMMDD);
        Long increment = stringRedisTemplate.opsForValue().increment(key + localDate);
        return StringUtils.leftPad(increment + "", 6, '0');
    }

    public String generateNumber(String key, int size) {
        Long increment = stringRedisTemplate.opsForValue().increment(key);
        return StringUtils.leftPad(increment + "", size, '0');
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
