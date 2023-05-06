package com.wemirr.platform.authority.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-07-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(name = "OrgSaveDTO", description = "组织")
public class OrgSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过255")
    private String label;
    /**
     * 简称
     */
    @Schema(description = "简称")
    @Length(max = 255, message = "简称长度不能超过255")
    private String abbreviation;

    @Schema(description = "部门电话")
    private String tel;
    /**
     * 父ID
     */
    @Schema(description = "父ID")
    private Long parentId;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sequence;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean status;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 255, message = "描述长度不能超过255")
    private String description;

}
