package com.wemirr.framework.boot.remote.dict;

import cn.hutool.core.lang.Pair;
import com.wemirr.framework.commons.remote.LoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class DictLoadService implements LoadService<Object> {

    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public Map<Object, Object> findByIds(Set<Object> ids) {
        log.info("字典服务......");
        throw new RuntimeException("暂未实现");
    }

    @Override
    public Map<Object, Object> findByIds(String tag) {
        return redisTemplate.opsForHash().entries(tag);
    }

    @Override
    public void refreshCache(Map<String, List<Pair<String, String>>> data) {
        if (data == null) {
            return;
        }
        for (Map.Entry<String, List<Pair<String, String>>> entry : data.entrySet()) {
            List<Pair<String, String>> value = entry.getValue();
            if (value == null) {
                continue;
            }
            Map<String, String> map = value.stream().collect(toMap(Pair::getKey, Pair::getValue));
            redisTemplate.opsForHash().putAll(entry.getKey(), map);
        }
    }
}
