package com.wemirr.framework.commons.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 在考虑是否应该移动到 database 模块
 * 基础实体
 *
 * @author Levin
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Entity<T> implements Serializable {

    public static final String FIELD_ID = "id";
    public static final String CREATE_TIME = "createdTime";
    public static final String CREATE_TIME_COLUMN = "created_time";
    public static final String CREATE_USER = "createdBy";
    public static final String CREATE_USER_NAME = "createdName";
    public static final String CREATE_USER_NAME_COLUMN = "created_name";

    public static final String CREATE_USER_COLUMN = "created_by";

    private static final long serialVersionUID = -4603650115461757622L;

    @Schema(description = "ID")
    @TableId(value = FIELD_ID, type = IdType.AUTO)
    @NotNull(message = "id不能为空", groups = Update.class)
    protected T id;

    /**
     * 创建人ID
     */
    @TableField(value = CREATE_USER_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建人ID")
    protected T createdBy;

    /**
     * 创建人名称
     */
    @TableField(value = CREATE_USER_NAME_COLUMN, fill = FieldFill.INSERT)
    @Schema(description = "创建人名称")
    protected String createdName;

    /**
     * 创建时间（依托数据库功能）
     */
    @TableField(value = CREATE_TIME_COLUMN)
    @Schema(description = "创建时间")
    protected LocalDateTime createdTime;



    /**
     * 保存和缺省验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新和缺省验证组
     */
    public interface Update extends Default {

    }

}
