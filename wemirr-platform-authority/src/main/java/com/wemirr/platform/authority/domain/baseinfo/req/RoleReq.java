package com.wemirr.platform.authority.domain.baseinfo.req;

import com.wemirr.framework.db.mybatisplus.intercept.data.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 实体类
 * 角色
 * </p>
 *
 * @author Levin
 * @since 2019-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(name= "RoleDTO", description = "角色")
public class RoleReq implements Serializable {

    

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称长度不能超过30")
    private String name;
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    @Length(max = 20, message = "角色编码长度不能超过20")
    private String code;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 100, message = "描述长度不能超过100")
    private String description;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean locked;

    @Schema(description = "内置角色")
    private Boolean readonly;
    /**
     * 数据权限类型
     * #DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}
     */
    @Schema(description = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    private DataScopeType scopeType;

    /**
     * 关联的组织id
     */
    @Schema(description = "关联的组织id")
    private List<Long> orgList;
}
