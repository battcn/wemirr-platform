package com.wemirr.platform.authority.domain.common.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Levin
 */
@Data
public class SysDictResp {

    @Schema(description = "ID")
    private Long key;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "名称")
    private String title;

    @Schema(description = "名称")
    private String label;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "排序")
    private Integer sequence;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "只读")
    private Boolean readonly;

    @Schema(description = "描述")
    private String description;

    public Long getKey() {
        return id;
    }

    public String getTitle() {
        return label;
    }
}
