package com.wemirr.platform.authority.domain.baseinfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.security.DataResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 实体类
 * 角色组织关系
 * </p>
 *
 * @author Levin
 * @since 2020-10-20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_data_permission_resource")
@EqualsAndHashCode(callSuper = true)
public class DataPermissionResource extends Entity<Long> {


    @Schema(description = "用户ID")
    @TableField("owner_id")
    private Long ownerId;


    @Schema(description = "资源类型")
    private DataResourceType ownerType;


    @Schema(description = "数据ID")
    @TableField("data_id")
    private Long dataId;

    @Schema(description = "数据类型")
    private DataResourceType dataType;
}
