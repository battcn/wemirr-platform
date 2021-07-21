package com.wemirr.platform.authority.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 岗位
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
@Schema(name= "StationSaveDTO", description = "岗位")
public class StationSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @Length(max = 255, message = "岗位名称长度不能超过{max}")
    @NotBlank(message = "岗位名称不能为空")
    private String name;

    @Schema(description = "岗位编码")
    private String code;


    @Schema(description = "岗位类型")
    @NotNull(message = "岗位类型不能为空")
    private Integer type;

    @Schema(description = "排序")
    private Integer sequence;

    /**
     * 组织ID
     * #c_core_org
     */
    @Schema(description = "组织ID")
    @NotNull(message = "组织不能为空")
    private Long orgId;
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
