package com.wemirr.framework.mongodb.warp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MongoDB 聚合查询构建器
 * <p>提供链式调用构建聚合管道</p>
 *
 * <pre>{@code
 * // 统计分组
 * List<Map> result = MongoAggregation.newPipeline()
 *     .match(Criteria.where("status").is(1))
 *     .group("category")
 *         .count("total")
 *         .sum("amount", "totalAmount")
 *         .avg("price", "avgPrice")
 *         .done()
 *     .sort(Sort.Direction.DESC, "total")
 *     .limit(10)
 *     .execute(mongoTemplate, "orders", Map.class);
 *
 * // 关联查询
 * MongoAggregation.newPipeline()
 *     .match(Criteria.where("userId").is(userId))
 *     .lookup("user", "userId", "_id", "userInfo")
 *     .unwind("userInfo")
 *     .project("orderNo", "amount", "userInfo.username")
 *     .execute(mongoTemplate, "orders", OrderVO.class);
 * }</pre>
 *
 * @author Levin
 */
@Slf4j
public class MongoAggregation {

    private final List<AggregationOperation> operations;
    private GroupOperationBuilder currentGroup;

    private MongoAggregation() {
        this.operations = new ArrayList<>();
    }

    public static MongoAggregation newPipeline() {
        return new MongoAggregation();
    }

    // ==================== Match ====================

    /**
     * 匹配条件
     */
    public MongoAggregation match(Criteria criteria) {
        operations.add(Aggregation.match(criteria));
        return this;
    }

    /**
     * 使用MongoWraps构建匹配条件
     */
    public MongoAggregation match(MongoWraps wraps) {
        operations.add(Aggregation.match(wraps.buildCriteria()));
        return this;
    }

    // ==================== Group ====================

    /**
     * 分组（返回GroupOperationBuilder继续配置聚合函数）
     */
    public GroupOperationBuilder group(String... fields) {
        this.currentGroup = new GroupOperationBuilder(this, fields);
        return currentGroup;
    }

    // ==================== Sort ====================

    /**
     * 排序
     */
    public MongoAggregation sort(Sort.Direction direction, String... fields) {
        operations.add(Aggregation.sort(direction, fields));
        return this;
    }

    /**
     * 多字段排序
     */
    public MongoAggregation sort(Sort sort) {
        operations.add(Aggregation.sort(sort));
        return this;
    }

    // ==================== Limit & Skip ====================

    /**
     * 限制数量
     */
    public MongoAggregation limit(long limit) {
        operations.add(Aggregation.limit(limit));
        return this;
    }

    /**
     * 跳过数量
     */
    public MongoAggregation skip(long skip) {
        operations.add(Aggregation.skip(skip));
        return this;
    }

    // ==================== Project ====================

    /**
     * 字段投影
     */
    public MongoAggregation project(String... fields) {
        operations.add(Aggregation.project(fields));
        return this;
    }

    /**
     * 排除字段
     */
    public MongoAggregation projectExclude(String... fields) {
        ProjectionOperation projection = Aggregation.project();
        for (String field : fields) {
            projection = projection.andExclude(field);
        }
        operations.add(projection);
        return this;
    }

    // ==================== Lookup ====================

    /**
     * 关联查询
     *
     * @param from         关联集合
     * @param localField   本地字段
     * @param foreignField 外部字段
     * @param as           结果别名
     */
    public MongoAggregation lookup(String from, String localField, String foreignField, String as) {
        operations.add(Aggregation.lookup(from, localField, foreignField, as));
        return this;
    }

    // ==================== Unwind ====================

    /**
     * 展开数组
     */
    public MongoAggregation unwind(String field) {
        operations.add(Aggregation.unwind(field));
        return this;
    }

    /**
     * 展开数组（保留空数组）
     */
    public MongoAggregation unwindPreserveNull(String field) {
        operations.add(Aggregation.unwind(field, true));
        return this;
    }

    // ==================== Count ====================

    /**
     * 总数统计
     */
    public MongoAggregation count(String outputField) {
        operations.add(Aggregation.count().as(outputField));
        return this;
    }

    // ==================== AddFields ====================

    /**
     * 添加自定义操作
     */
    public MongoAggregation addOperation(AggregationOperation operation) {
        operations.add(operation);
        return this;
    }

    // ==================== Execute ====================

    /**
     * 执行聚合查询
     */
    public <T> List<T> execute(MongoTemplate mongoTemplate, String collectionName, Class<T> outputType) {
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<T> results = mongoTemplate.aggregate(aggregation, collectionName, outputType);
        return results.getMappedResults();
    }

    /**
     * 执行聚合查询（使用实体类名作为集合名）
     */
    public <T> List<T> execute(MongoTemplate mongoTemplate, Class<?> inputType, Class<T> outputType) {
        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<T> results = mongoTemplate.aggregate(aggregation, inputType, outputType);
        return results.getMappedResults();
    }

    /**
     * 执行聚合查询并返回单个结果
     */
    public <T> T executeOne(MongoTemplate mongoTemplate, String collectionName, Class<T> outputType) {
        List<T> results = execute(mongoTemplate, collectionName, outputType);
        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * 执行聚合查询返回Map结果
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> executeAsMap(MongoTemplate mongoTemplate, String collectionName) {
        return execute(mongoTemplate, collectionName, (Class<Map<String, Object>>) (Class<?>) Map.class);
    }

    /**
     * 构建聚合对象
     */
    public Aggregation build() {
        return Aggregation.newAggregation(operations);
    }

    // ==================== Group Operation Builder ====================

    /**
     * 分组操作构建器
     */
    public static class GroupOperationBuilder {
        private final MongoAggregation parent;
        private GroupOperation groupOperation;

        GroupOperationBuilder(MongoAggregation parent, String... fields) {
            this.parent = parent;
            this.groupOperation = Aggregation.group(fields);
        }

        /**
         * 计数
         */
        public GroupOperationBuilder count(String alias) {
            groupOperation = groupOperation.count().as(alias);
            return this;
        }

        /**
         * 求和
         */
        public GroupOperationBuilder sum(String field, String alias) {
            groupOperation = groupOperation.sum(field).as(alias);
            return this;
        }

        /**
         * 求平均值
         */
        public GroupOperationBuilder avg(String field, String alias) {
            groupOperation = groupOperation.avg(field).as(alias);
            return this;
        }

        /**
         * 最大值
         */
        public GroupOperationBuilder max(String field, String alias) {
            groupOperation = groupOperation.max(field).as(alias);
            return this;
        }

        /**
         * 最小值
         */
        public GroupOperationBuilder min(String field, String alias) {
            groupOperation = groupOperation.min(field).as(alias);
            return this;
        }

        /**
         * 第一个值
         */
        public GroupOperationBuilder first(String field, String alias) {
            groupOperation = groupOperation.first(field).as(alias);
            return this;
        }

        /**
         * 最后一个值
         */
        public GroupOperationBuilder last(String field, String alias) {
            groupOperation = groupOperation.last(field).as(alias);
            return this;
        }

        /**
         * 收集到数组
         */
        public GroupOperationBuilder push(String field, String alias) {
            groupOperation = groupOperation.push(field).as(alias);
            return this;
        }

        /**
         * 收集到Set（去重）
         */
        public GroupOperationBuilder addToSet(String field, String alias) {
            groupOperation = groupOperation.addToSet(field).as(alias);
            return this;
        }

        /**
         * 完成分组配置，返回主构建器
         */
        public MongoAggregation done() {
            parent.operations.add(groupOperation);
            return parent;
        }
    }
}
