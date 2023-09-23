package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
@TableName("common_opt_log")
public class OptLog {


    @TableId
    private Long id;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "地区信息")
    private String location;

    @Schema(description = "日志链路追踪id日志标志")
    private String trace;

    @Schema(description = "日志类型#LogType{OPT:操作类型;EX:异常类型}")
    private String type;

    @Schema(description = "操作描述")
    private String description;

    @Schema(description = "类路径")
    private String classPath;

    @Schema(description = "请求类型")
    private String actionMethod;

    @Schema(description = "请求地址")
    private String requestUri;

    /**
     * 请求类型
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    private String httpMethod;

    @Schema(description = "请求参数")
    private String params;

    @Schema(description = "返回值")
    private String result;

    @Schema(description = "异常详情信息")
    private String exDesc;

    @Schema(description = "异常描述")
    private String exDetail;

    @Schema(description = "开始时间")
    private Instant startTime;

    @Schema(description = "完成时间")
    private Instant finishTime;

    @Schema(description = "消耗时间")
    private Long consumingTime;

    @Schema(description = "浏览器")
    private String browser;

    @Schema(description = "引擎")
    private String engine;

    @Schema(description = "系统")
    private String os;

    @Schema(description = "平台")
    private String platform;

    @Schema(description = "浏览器版本")
    private String browserVersion;

    @Schema(description = "引擎版本")
    private String engineVersion;

    @Schema(description = "创建人")
    private Long createdBy;
    /**
     * 操作人
     */
    @Schema(description = "创建人名称")
    private String createdName;


}
