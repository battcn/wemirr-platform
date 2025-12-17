package com.wemirr.framework.mongodb.warp;

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
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * MongoDB 查询条件构建器
 * <p>提供链式调用构建MongoDB查询条件</p>
 *
 * <pre>{@code
 * // 基本查询
 * MongoWraps.lb()
 *     .eq("status", 1)
 *     .like("name", "张")
 *     .between("createTime", startTime, endTime)
 *     .orderByDesc("createTime")
 *     .page(PageRequest.of(0, 10))
 *     .execute(mongoTemplate, User.class, "user");
 *
 * // 复杂查询
 * MongoWraps.lb()
 *     .eq("type", "ORDER")
 *     .in("status", List.of(1, 2, 3))
 *     .exists("payTime", true)
 *     .regex("orderNo", "^ORD")
 *     .select("id", "orderNo", "amount")
 *     .orderByAsc("createTime")
 *     .build();
 * }</pre>
 *
 * @author Levin
 */
public final class MongoWraps {

    private final List<Criteria> criteriaList;
    private final List<Sort.Order> sortOrders;
    private final List<String> includeFields;
    private final List<String> excludeFields;
    private PageRequest request;

    private MongoWraps() {
        this.criteriaList = new ArrayList<>();
        this.sortOrders = new ArrayList<>();
        this.includeFields = new ArrayList<>();
        this.excludeFields = new ArrayList<>();
    }

    public static MongoWraps lb() {
        return new MongoWraps();
    }

    /**
     * 将对象转换为Update对象（忽略null值和@MongoId字段）
     */
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

    /**
     * 创建Update对象（包含null值更新）
     */
    public static Update toUpdateWithNull(Object object, String... includeNullFields) {
        final Field[] fields = ReflectUtil.getFields(object.getClass());
        Update update = new Update();
        List<String> nullFieldList = List.of(includeNullFields);
        for (Field field : fields) {
            final String name = field.getName();
            final MongoId annotation = field.getAnnotation(MongoId.class);
            if (annotation != null) {
                continue;
            }
            final Object value = ReflectUtil.getFieldValue(object, field);
            if (ObjectUtils.isEmpty(value)) {
                if (nullFieldList.contains(name)) {
                    update.set(name, null);
                }
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

    // ==================== 模糊查询 ====================

    /**
     * 模糊查询（包含）
     */
    public MongoWraps like(String key, String value) {
        if (StrUtil.isNotBlank(value)) {
            Pattern pattern = Pattern.compile(".*" + escapeRegex(value) + ".*", Pattern.CASE_INSENSITIVE);
            criteriaList.add(Criteria.where(key).regex(pattern));
        }
        return this;
    }

    /**
     * 模糊查询（条件控制）
     */
    public MongoWraps like(boolean condition, String key, String value) {
        if (condition && StrUtil.isNotBlank(value)) {
            return like(key, value);
        }
        return this;
    }

    /**
     * 左模糊查询（以...结尾）
     */
    public MongoWraps likeLeft(String key, String value) {
        if (StrUtil.isNotBlank(value)) {
            Pattern pattern = Pattern.compile(".*" + escapeRegex(value) + "$", Pattern.CASE_INSENSITIVE);
            criteriaList.add(Criteria.where(key).regex(pattern));
        }
        return this;
    }

    /**
     * 右模糊查询（以...开头）
     */
    public MongoWraps likeRight(String key, String value) {
        if (StrUtil.isNotBlank(value)) {
            Pattern pattern = Pattern.compile("^" + escapeRegex(value) + ".*", Pattern.CASE_INSENSITIVE);
            criteriaList.add(Criteria.where(key).regex(pattern));
        }
        return this;
    }

    /**
     * 正则表达式查询
     */
    public MongoWraps regex(String key, String regex) {
        if (StrUtil.isNotBlank(regex)) {
            criteriaList.add(Criteria.where(key).regex(regex));
        }
        return this;
    }

    /**
     * 正则表达式查询（带选项）
     */
    public MongoWraps regex(String key, Pattern pattern) {
        if (pattern != null) {
            criteriaList.add(Criteria.where(key).regex(pattern));
        }
        return this;
    }

    // ==================== 不等于/空值判断 ====================

    /**
     * 不等于
     */
    public MongoWraps ne(String key, Object value) {
        if (ObjUtil.isNotEmpty(value)) {
            criteriaList.add(Criteria.where(key).ne(value));
        }
        return this;
    }

    /**
     * 不等于（条件控制）
     */
    public MongoWraps ne(boolean condition, String key, Object value) {
        if (condition) {
            criteriaList.add(Criteria.where(key).ne(value));
        }
        return this;
    }

    /**
     * 字段存在判断
     */
    public MongoWraps exists(String key, boolean exists) {
        criteriaList.add(Criteria.where(key).exists(exists));
        return this;
    }

    /**
     * 字段为null
     */
    public MongoWraps isNull(String key) {
        criteriaList.add(Criteria.where(key).is(null));
        return this;
    }

    /**
     * 字段不为null
     */
    public MongoWraps isNotNull(String key) {
        criteriaList.add(Criteria.where(key).ne(null));
        return this;
    }

    /**
     * 不在集合中
     */
    public MongoWraps notIn(String key, Collection<?> values) {
        if (ObjUtil.isNotEmpty(values)) {
            criteriaList.add(Criteria.where(key).nin(values));
        }
        return this;
    }

    // ==================== 排序 ====================

    /**
     * 升序排序
     */
    public MongoWraps orderByAsc(String... fields) {
        for (String field : fields) {
            sortOrders.add(Sort.Order.asc(field));
        }
        return this;
    }

    /**
     * 降序排序
     */
    public MongoWraps orderByDesc(String... fields) {
        for (String field : fields) {
            sortOrders.add(Sort.Order.desc(field));
        }
        return this;
    }

    /**
     * 自定义排序
     */
    public MongoWraps orderBy(Sort.Order... orders) {
        sortOrders.addAll(List.of(orders));
        return this;
    }

    // ==================== 字段投影 ====================

    /**
     * 只查询指定字段
     */
    public MongoWraps select(String... fields) {
        includeFields.addAll(List.of(fields));
        return this;
    }

    /**
     * 排除指定字段
     */
    public MongoWraps exclude(String... fields) {
        excludeFields.addAll(List.of(fields));
        return this;
    }

    // ==================== 执行查询 ====================

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

    /**
     * 执行查询（使用实体类名作为集合名）
     */
    public <T> MongoPageResult<T> execute(MongoTemplate mongoTemplate, @NotNull Class<T> entityClass) {
        long count;
        Query query = this.build();
        List<T> list = Lists.newArrayList();
        if (request == null) {
            list = mongoTemplate.find(query, entityClass);
            count = list.size();
        } else {
            count = mongoTemplate.count(query, entityClass);
            if (count > 0) {
                query.with(request);
                list = mongoTemplate.find(query, entityClass);
            }
        }
        return new MongoPageResult<>(list, count);
    }

    /**
     * 查询列表（不分页）
     */
    public <T> List<T> list(MongoTemplate mongoTemplate, Class<T> entityClass) {
        return mongoTemplate.find(this.build(), entityClass);
    }

    /**
     * 查询列表（指定集合）
     */
    public <T> List<T> list(MongoTemplate mongoTemplate, Class<T> entityClass, String collectionName) {
        return mongoTemplate.find(this.build(), entityClass, collectionName);
    }

    /**
     * 查询单条记录
     */
    public <T> T one(MongoTemplate mongoTemplate, Class<T> entityClass) {
        return mongoTemplate.findOne(this.build(), entityClass);
    }

    /**
     * 查询单条记录（指定集合）
     */
    public <T> T one(MongoTemplate mongoTemplate, Class<T> entityClass, String collectionName) {
        return mongoTemplate.findOne(this.build(), entityClass, collectionName);
    }

    /**
     * 查询数量
     */
    public long count(MongoTemplate mongoTemplate, Class<?> entityClass) {
        return mongoTemplate.count(this.build(), entityClass);
    }

    /**
     * 查询数量（指定集合）
     */
    public long count(MongoTemplate mongoTemplate, Class<?> entityClass, String collectionName) {
        return mongoTemplate.count(this.build(), entityClass, collectionName);
    }

    /**
     * 判断是否存在
     */
    public boolean exists(MongoTemplate mongoTemplate, Class<?> entityClass) {
        return mongoTemplate.exists(this.build(), entityClass);
    }

    public Query build() {
        Query query = new Query();
        // 添加查询条件
        if (CollUtil.isNotEmpty(criteriaList)) {
            for (Criteria criteria : criteriaList) {
                query.addCriteria(criteria);
            }
        }
        // 添加排序
        if (CollUtil.isNotEmpty(sortOrders)) {
            query.with(Sort.by(sortOrders));
        }
        // 添加字段投影
        if (CollUtil.isNotEmpty(includeFields)) {
            for (String field : includeFields) {
                query.fields().include(field);
            }
        }
        if (CollUtil.isNotEmpty(excludeFields)) {
            for (String field : excludeFields) {
                query.fields().exclude(field);
            }
        }
        return query;
    }

    /**
     * 转义正则表达式特殊字符
     */
    private String escapeRegex(String value) {
        return value.replaceAll("([\\\\*+\\[\\](){}|.^$?])", "\\\\$1");
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

