package com.wemirr.framework.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 增强实体类
 *
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class SuperEntity<T> extends Entity<T> {

    public static final String UPDATE_TIME = "lastModifiedTime";
    public static final String UPDATE_USER = "lastModifiedBy";
    public static final String UPDATE_USER_NAME = "lastModifiedName";

    public static final String UPDATE_TIME_COLUMN = "last_modified_time";
    public static final String UPDATE_USER_COLUMN = "last_modified_by";
    public static final String UPDATE_USER_NAME_COLUMN = "last_modified_name";


    @Schema(description = "最后修改时间")
    @TableField(value = UPDATE_TIME_COLUMN)
    protected LocalDateTime lastModifiedTime;

    @Schema(description = "最后修改人ID")
    @TableField(value = UPDATE_USER_COLUMN, fill = FieldFill.INSERT_UPDATE)
    protected T lastModifiedBy;


    @Schema(description = "最后修改人名称")
    @TableField(value = UPDATE_USER_NAME_COLUMN, fill = FieldFill.INSERT_UPDATE)
    protected String lastModifiedName;

    @TableLogic
    @Schema(description = "逻辑删除")
    private Boolean deleted;

}
