package com.wemirr.framework.mongodb.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.google.common.collect.Lists;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levin
 */
public final class MongoWraps {

    private final List<Criteria> criteriaList;

    private MongoWraps() {
        this.criteriaList = new ArrayList<>();
    }

    public static MongoWraps lb() {
        return new MongoWraps();
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

    public MongoWraps andOr(boolean status, List<String> keys, Object val) {
        if (!status) {
            return this;
        }
        List<Criteria> orList = Lists.newArrayList();
        for (String key : keys) {
            orList.add(Criteria.where(key).is(val));
        }
        Criteria criteria = new Criteria();
        criteria.orOperator(orList);
        criteriaList.add(criteria);
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
}
