package com.wemirr.framework.mongodb.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.exception.CheckedException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Levin
 */
public final class MongoWraps {

    private final List<Criteria> criteriaList;
    private PageRequest request;

    private MongoWraps() {
        this.criteriaList = new ArrayList<>();
    }

    public static MongoWraps lb() {
        return new MongoWraps();
    }

    public static Update toUpdate(Object object) {
        final Field[] fields = ReflectUtil.getFields(object.getClass());
        Update update = new Update();
        for (Field field : fields) {
            final String name = field.getName();
            final MongoId annotation = field.getAnnotation(MongoId.class);
            if (annotation != null) {
                continue;
            }
            final Object value = ReflectUtil.getFieldValue(object, field);
            if (ObjectUtils.isEmpty(value)) {
                continue;
            }
            update.set(name, value);
        }
        return update;
    }

    public MongoWraps eq(boolean condition, String key, Object value) {
        if (condition) {
            criteriaList.add(Criteria.where(key).is(value));
        }
        return this;
    }

    public MongoWraps eq(String key, Object value) {
        if (ObjUtil.isNotEmpty(value)) {
            criteriaList.add(Criteria.where(key).is(value));
        }
        return this;
    }

    public MongoWraps gt(String key, Object value) {
        if (ObjUtil.isNotEmpty(value)) {
            criteriaList.add(Criteria.where(key).gt(value));
        }
        return this;
    }

    public MongoWraps gte(String key, Object value) {
        if (ObjUtil.isNotEmpty(value)) {
            criteriaList.add(Criteria.where(key).gte(value));
        }
        return this;
    }

    public MongoWraps lt(String key, Object value) {
        if (ObjUtil.isNotEmpty(value)) {
            criteriaList.add(Criteria.where(key).lt(value));
        }
        return this;
    }

    public MongoWraps lte(String key, Object value) {
        if (ObjUtil.isNotEmpty(value)) {
            criteriaList.add(Criteria.where(key).lte(value));
        }
        return this;
    }

    public MongoWraps in(String key, Collection<?> values) {
        if (ObjUtil.isNotEmpty(values)) {
            criteriaList.add(Criteria.where(key).in(values));
        }
        return this;
    }

    public MongoWraps andOr(boolean condition, List<String> keys, Object val) {
        if (!condition) {
            return this;
        }
        List<Criteria> orList = Lists.newArrayList();
        for (String key : keys) {
            if (StrUtil.isBlank(key)) {
                continue;
            }
            orList.add(Criteria.where(key).is(val));
        }
        Criteria criteria = new Criteria();
        criteria.orOperator(orList);
        criteriaList.add(criteria);
        return this;
    }

    public MongoWraps andOperator(boolean condition, Criteria criteria) {
        if (!condition || criteria == null) {
            return this;
        }
        criteriaList.add(criteria);
        return this;
    }

    public MongoWraps between(String gteKey, String ltKey, Object getTime, Object ltTime) {
        if (ObjUtil.isAllNotEmpty(gteKey, ltKey, getTime, ltTime)) {
            criteriaList.add(Criteria.where(gteKey).gte(getTime).and(ltKey).lt(ltTime));
        }
        return this;
    }

    public MongoWraps between(String key, Object startTime, Object endTime) {
        if (startTime != null && endTime != null) {
            criteriaList.add(Criteria.where(key).gte(startTime).lt(endTime));
        } else if (startTime != null) {
            criteriaList.add(Criteria.where(key).gte(startTime));
        } else if (endTime != null) {
            criteriaList.add(Criteria.where(key).lt(endTime));
        }
        return this;
    }

    public MongoWraps addCriteria(boolean condition, Criteria criteria) {
        if (!condition || criteria == null) {
            return this;
        }
        criteriaList.add(criteria);
        return this;
    }

    public MongoWraps page(boolean condition, PageRequest request) {
        this.request = request;
        return this;
    }

    public MongoWraps page(PageRequest request) {
        this.request = request;
        return this;
    }

    public <T> MongoPageResult<T> execute(MongoTemplate mongoTemplate,
                                          @NotNull Class<T> entityClass,
                                          String collectionName) {
        long count;
        Query query = this.build();
        List<T> v2List = Lists.newArrayList();
        if (request == null) {
            v2List = mongoTemplate.find(query, entityClass, collectionName);
            count = v2List.size();
        } else {
            count = mongoTemplate.count(query, entityClass, collectionName);
            if (count > 0) {
                query.with(request);
                v2List = mongoTemplate.find(query, entityClass, collectionName);
            }
        }
        return new MongoPageResult<>(v2List, count);
    }

    public Query build() {
        Query query = new Query();
        if (CollUtil.isEmpty(criteriaList)) {
            return query;
        }
        for (Criteria criteria : criteriaList) {
            query.addCriteria(criteria);
        }
        return query;
    }

    public List<Criteria> toCriteriaList() {
        if (CollUtil.isEmpty(criteriaList)) {
            throw CheckedException.notFound("criteria list is empty");
        }
        return criteriaList;
    }

    public Criteria buildCriteria() {
        if (CollUtil.isEmpty(criteriaList)) {
            throw CheckedException.notFound("criteria list is empty");
        }
        return new Criteria().andOperator(criteriaList);
    }

    @Data
    @AllArgsConstructor
    public static class MongoPageResult<T> {

        /**
         * 响应结果
         */
        private List<T> records;

        /**
         * 总数
         */
        private long total;

    }
}

