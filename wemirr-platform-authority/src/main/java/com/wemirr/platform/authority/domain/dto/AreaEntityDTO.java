package com.wemirr.platform.authority.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Levin
 */
@Data
public class AreaEntityDTO {

    @NotNull(message = "国标码不能为空")
    private Long id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    protected String name;

    private Integer level;

    /**
     * 父ID
     */
    @NotNull(message = "上级国标码不能为空")
    protected Long parentId;

    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    private Integer sequence;
    @Length(max = 255,message = "数据长度不能超过 {max}")
    private String source;


}
