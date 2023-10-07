package com.wemirr.platform.authority.domain.common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * @author Levin
 */
@Data
@Schema(name = "AreaReq")
public class AreaReq {

    @NotNull(message = "国标码不能为空")
    @Schema(description = "ID")
    private Long id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    @Schema(description = "名称")
    protected String name;

    @Schema(description = "层级")
    private Integer level;

    /**
     * 父ID
     */
    @NotNull(message = "上级国标码不能为空")
    @Schema(description = "父级ID")
    protected Long parentId;

    /**
     * 经度
     */
    @Schema(description = "经度")
    private BigDecimal longitude;
    /**
     * 纬度
     */
    @Schema(description = "纬度")
    private BigDecimal latitude;
    @Schema(description = "排序")
    private Integer sequence;
    @Length(max = 255,message = "数据来源不能超过 {max}")
    @Schema(description = "数据来源")
    private String source;


}
