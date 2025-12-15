package com.wemirr.framework.log.diff.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 差异日志操作配置，封装 @DiffLog 注解解析后的配置信息
 *
 * @author muzhantong
 */
@Data
@Builder
public class DiffLogOps {

    /**
     * 操作成功时的日志模板
     */
    private String successLogTemplate;

    /**
     * 操作失败时的日志模板
     */
    private String failLogTemplate;

    /**
     * 业务分组，用于日志归类
     */
    private String group;

    /**
     * 业务唯一标识，支持 SpEL 表达式
     */
    private String businessKey;

    /**
     * 业务标签，描述具体操作类型
     */
    private String tag;

    /**
     * 记录日志的条件表达式，为 false 时不记录
     */
    private String condition;

    /**
     * 判断操作是否成功的条件表达式
     */
    private String successCondition;

}
