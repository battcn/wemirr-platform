package com.wemirr.platform.authority.domain.tenant.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@SuperBuilder
@TableName("t_dynamic_datasource")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DynamicDatasource extends SuperEntity<Long> {


    /**
     * 连接池名称
     */
    private String poolName;
    /**
     * 数据库类型(只支持Mysql)
     */
    private String dbType;


    private String driverClassName;

    /**
     * 单库单 schema 需要配置 database
     */
    @TableField(value = "`database`", updateStrategy = FieldStrategy.NEVER)
    private String database;

    @TableField("`username`")
    private String username;

    @TableField("`password`")
    private String password;

    @TableField("`host`")
    private String host;

    /**
     * 是否禁用
     */
    @TableField("`locked`")
    private Boolean locked;

    /**
     * 描述
     */
    private String description;


}
