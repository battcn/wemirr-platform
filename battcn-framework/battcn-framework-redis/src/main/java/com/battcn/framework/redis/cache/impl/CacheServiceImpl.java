package com.battcn.framework.redis.cache.impl;

import com.battcn.framework.redis.cache.CacheService;
import com.battcn.framework.redis.constant.RedisConstant;
import com.battcn.framework.redis.exception.CacheException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * 缓存组件-缓存接口
 *
 * @author 许泓林
 */
public class CacheServiceImpl implements CacheService {

    private final static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    private final RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    public CacheServiceImpl(@Qualifier(RedisConstant.CACHE_TEMPLATE_NAME) RedisTemplate<String, Serializable> redisCacheTemplate) {
        this.redisCacheTemplate = redisCacheTemplate;
    }

    @Override
    public Long incrBy(String key, long val) throws CacheException {
        try {
            return redisCacheTemplate.opsForValue().increment(key, val);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public Long incr(String key) throws CacheException {
        return this.incrBy(key, 1);

    }

    @Override
    public Long incr(String key, long val, int interval, TimeUnit unit) {
        Long result = null;
        try {
            result = this.incrBy(key, val);
            // val小于0表示回退，所以要忽略
            if (result == 1 && val > 0) {
                redisCacheTemplate.expire(key, interval, unit);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
        return result;
    }

    @Override
    public void expire(String key, int interval) throws CacheException {
        try {
            redisCacheTemplate.expire(key, interval, TimeUnit.SECONDS);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void expire(String key, int interval, TimeUnit unit) throws CacheException {
        try {
            redisCacheTemplate.expire(key, interval, unit);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void add(String key, Serializable value, int minutes) throws CacheException {
        try {
            redisCacheTemplate.opsForValue().set(key, value);
            if (minutes > 0) {
                redisCacheTemplate.expire(key, minutes, TimeUnit.MINUTES);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void add(String key, Serializable value, int interval, TimeUnit unit) throws CacheException {
        try {
            redisCacheTemplate.opsForValue().set(key, value);
            redisCacheTemplate.expire(key, interval, unit);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public Serializable getAndSet(String key, Serializable value, int interval, TimeUnit unit) throws CacheException {
        Serializable v;
        try {
            v = redisCacheTemplate.opsForValue().getAndSet(key, value);
            if (v == null) {
                redisCacheTemplate.expire(key, interval, unit);
            }
        } catch (Exception ex) {
            remove(key);
            logger.error(ex.getMessage(), ex);
            return null;
        }
        return v;
    }

    @Override
    public void add(String key, Serializable value) throws CacheException {
        try {
            redisCacheTemplate.opsForValue().set(key, value);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void addList(String key, Collection<Serializable> values) throws CacheException {
        try {
            if (values != null && values.size() > 0) {
                remove(key);
                redisCacheTemplate.opsForList().leftPushAll(key, values);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }

    }

    @Override
    public void addList(String key, Collection<Serializable> values, int minutes) throws CacheException {
        try {
            remove(key);
            redisCacheTemplate.opsForList().leftPushAll(key, values);
            redisCacheTemplate.expire(key, minutes, TimeUnit.MINUTES);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void addMap(String key, Map<Object, Object> valueMap) throws CacheException {
        try {
            if (valueMap != null && valueMap.keySet().size() > 0) {
                remove(key);
                redisCacheTemplate.opsForHash().putAll(key, valueMap);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void addMap(String key, Map<Object, Object> valueMap, int minutes) throws CacheException {
        try {
            remove(key);
            redisCacheTemplate.opsForHash().putAll(key, valueMap);
            redisCacheTemplate.expire(key, minutes, TimeUnit.MINUTES);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public Map<Object, Object> getMap(String key) throws CacheException {
        try {
            return redisCacheTemplate.opsForHash().entries(key);
        } catch (Exception ex) {
            remove(key);
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void addOneToList(String key, Serializable value) throws CacheException {
        try {
            redisCacheTemplate.opsForList().leftPush(key, value);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public Serializable get(String key) throws CacheException {
        try {
            if (null != key) {
                return redisCacheTemplate.opsForValue().get(key);
            }
            return null;
        } catch (Exception ex) {
            remove(key);
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public List<Serializable> getList(String key) throws CacheException {
        try {
            Long size = redisCacheTemplate.opsForList().size(key);
            return redisCacheTemplate.opsForList().range(key, 0, size);
        } catch (Exception ex) {
            remove(key);
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public Serializable getListFirstOne(String key) throws CacheException {
        try {
            List<Serializable> le = redisCacheTemplate.opsForList().range(key, 0, 1);
            if (le != null && le.size() > 0) {
                return le.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            remove(key);
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public long getCountLike(String keyPrefix) throws CacheException {
        try {
            if (StringUtils.isNotEmpty(keyPrefix)) {
                Set<String> matchedCacheKeys = redisCacheTemplate.keys(keyPrefix + "*");
                return matchedCacheKeys.size();
            } else {
                return 0;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void remove(String key) throws CacheException {
        try {
            if (null != key) {
                redisCacheTemplate.delete(key);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }

    }

    @Override
    public void removeLike(String keyPrefix) throws CacheException {
        try {
            if (StringUtils.isEmpty(keyPrefix)) {
                return;
            }
            Set<String> matchedCacheKeys = redisCacheTemplate.keys(keyPrefix + "*");
            for (String cacheKey : matchedCacheKeys) {
                this.remove(cacheKey);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public Long getListSize(String key) throws CacheException {
        try {
            return redisCacheTemplate.opsForList().size(key);
        } catch (Exception ex) {
            remove(key);
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public List<Serializable> popList(String key, int offset) throws CacheException {
        try {
            List<Serializable> le = redisCacheTemplate.opsForList().range(key, 0, offset - 1);
            for (Serializable item : le) {
                redisCacheTemplate.opsForList().remove(key, 0, item);
            }
            return le;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void setNX(final String key, final Serializable value, int interval, TimeUnit timeUnit) {
        try {
            redisCacheTemplate.execute((RedisCallback<Object>) connection -> {
                RedisSerializer<String> serializer = redisCacheTemplate.getStringSerializer();
                byte[] key1 = serializer.serialize(key);
                byte[] value1 = serializer.serialize(value.toString());
                return connection.setNX(key1, value1);
            });
            if (interval > 0) {
                redisCacheTemplate.expire(key, interval, timeUnit);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CacheException(e, e.getMessage());
        }
    }

    @Override
    public void hset(String key, String field, Serializable value, int interval, TimeUnit timeUnit) {
        try {
            redisCacheTemplate.opsForHash().put(key, field, value);
            if (interval > 0) {
                redisCacheTemplate.expire(key, interval, timeUnit);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void hset(String key, String field, Serializable value) {
        try {
            redisCacheTemplate.opsForHash().put(key, field, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public Object hget(String key, String field) {
        try {
            return redisCacheTemplate.opsForHash().get(key, field);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void addListRight(String key, Collection<Serializable> values) throws CacheException {
        try {
            if (values != null && values.size() > 0) {
                remove(key);
                redisCacheTemplate.opsForList().rightPushAll(key, values);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void addListRight(String key, Collection<Serializable> values, int minutes) throws CacheException {
        try {
            remove(key);
            redisCacheTemplate.opsForList().rightPushAll(key, values);
            redisCacheTemplate.expire(key, minutes, TimeUnit.MINUTES);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public void addOneToListRight(String key, Serializable value) throws CacheException {
        try {
            redisCacheTemplate.opsForList().rightPush(key, value);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

    @Override
    public Serializable getListRandomOne(String key) throws CacheException {
        try {
            long size = redisCacheTemplate.opsForList().size(key);
            if (size < 1) {
                return null;
            }
            Random random = new Random();
            int i = random.nextInt((int) size);
            List<Serializable> le = redisCacheTemplate.opsForList().range(key, i, i + 1);
            if (le != null && le.size() > 0) {
                return le.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            remove(key);
            logger.error(ex.getMessage(), ex);
            throw new CacheException(ex, ex.getMessage());
        }
    }

}
