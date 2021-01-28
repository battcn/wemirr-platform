package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("gen_dynamic_release_datasource")
public class DynamicReleaseDatasource extends SuperEntity<Long> {

    @TableField("`database`")
    private String database;
    @TableField("`username`")
    private String username;
    @TableField("`password`")
    private String password;
    @TableField("`host`")
    private String host;
    @TableField("`port`")
    private Integer port;
    @TableField("`description`")
    private String description;
    @TableField("`locked`")
    private Boolean locked;






}
