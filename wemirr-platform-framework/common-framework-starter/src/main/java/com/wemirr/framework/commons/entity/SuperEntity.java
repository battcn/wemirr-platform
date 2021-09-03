package com.wemirr.framework.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 增强实体类
 *
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class SuperEntity<T> extends Entity<T> {

    public static final String UPDATE_TIME = "lastModifiedTime";
    public static final String UPDATE_USER = "lastModifiedBy";
    public static final String UPDATE_USER_NAME = "lastModifiedName";

    private static final long serialVersionUID = 5169873634279173683L;

    @Parameter(description = "最后修改时间")
    @TableField(value = UPDATE_TIME)
    protected LocalDateTime lastModifiedTime;

    @Parameter(description = "最后修改人ID")
    @TableField(value = UPDATE_USER, fill = FieldFill.INSERT_UPDATE)
    protected T lastModifiedBy;


    @Parameter(description = "最后修改人名称")
    @TableField(value = UPDATE_USER_NAME, fill = FieldFill.INSERT_UPDATE)
    protected String lastModifiedName;

}
