package com.wemirr.framework.boot.log;


import lombok.*;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <p>
 * 实体类
 * 系统日志
 * </p>
 *
 * @author Levin
 * @since 2019-07-02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Accessors(chain = true)
public class OptLogDTO {

    

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 日志链路追踪id日志标志
     */
    private String trace;

    /**
     * 日志类型
     * #LogType{OPT:操作类型;EX:异常类型}
     */
    private String type;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 类路径
     */
    private String classPath;

    /**
     * 请求类型
     */
    private String actionMethod;

    /**
     * 请求地址
     */
    private String requestUri;

    /**
     * 请求类型
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    private String httpMethod;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回值
     */
    private String result;

    /**
     * 异常描述
     */
    private String exDesc;

    /**
     * 异常详情信息
     */
    private String exDetail;

    /**
     * 开始时间
     */
    private Instant startTime;

    /**
     * 完成时间
     */
    private Instant finishTime;

    /**
     * 消耗时间
     */
    private Long consumingTime;


    private Long createdBy;
    private String createdName;

    private String browser;
    private String browserVersion;
    private String engine;
    private String os;
    private String platform;
    private String version;
    private String engineVersion;
    /**
     * 数据源
     */
    private String dsKey;

}
