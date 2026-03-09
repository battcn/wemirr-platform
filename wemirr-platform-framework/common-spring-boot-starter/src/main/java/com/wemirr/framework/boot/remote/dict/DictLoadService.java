package com.wemirr.framework.boot.remote.dict;

import cn.hutool.core.lang.Pair;
import com.wemirr.framework.commons.remote.LoadService;
import com.wemirr.framework.i18n.I18nMessageProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

/**
 * @author Levin
 */
@Slf4j
@RequiredArgsConstructor
public class DictLoadService implements LoadService<Object> {

    private static final String PLAT_DICT_HASH_KEY = "dict:platform";
    private final RedisTemplate<String, Object> redisTemplate;
    private final I18nMessageProvider i18nMessageProvider;

    @Override
    public Map<Object, Object> findByIds(Set<Object> ids) {
        log.info("字典服务......");
        throw new RuntimeException("暂未实现");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Object, Object> findByIds(String tag) {
        Locale locale = LocaleContextHolder.getLocale();
        Object object = redisTemplate.opsForHash().get(PLAT_DICT_HASH_KEY, tag);
        if (object == null) {
            return null;
        }
        var entries = (Map<Object, Object>) object;
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            String code = tag + "." + entry.getKey();
            String message = i18nMessageProvider.getI18nMessage(code, locale);
            if (message == null) {
                log.warn("未检测到 IN18N 队列中存在数据,请检查 Redis 缓存或者 DB是否有配置 - {}", code);
                continue;
            }
            entries.put(entry.getKey(), message);
        }
        return entries;
    }

    @Override
    public void refreshCache(Map<String, List<Pair<String, String>>> data) {
        if (data == null) {
            return;
        }
        for (Map.Entry<String, List<Pair<String, String>>> entry : data.entrySet()) {
            String key = entry.getKey();
            List<Pair<String, String>> value = entry.getValue();
            if (value == null) {
                continue;
            }
            Map<String, String> map = value.stream().collect(toMap(Pair::getKey, Pair::getValue));
            redisTemplate.opsForHash().put(PLAT_DICT_HASH_KEY, key, map);
        }
    }
}
