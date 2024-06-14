package com.wemirr.platform.authority.domain.baseinfo.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class StationPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "组织ID")
    private Long orgId;

    @Schema(description = "状态")
    @TableField("`status`")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;

}
