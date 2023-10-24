package com.wemirr.platform.authority.domain.baseinfo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
@Schema(name = "StationSaveReq", description = "岗位")
public class StationSaveReq {

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
    @Length(max = 255, message = "描述长度不能超过 {max}")
    private String description;

}
