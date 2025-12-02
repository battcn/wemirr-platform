package com.wemirr.platform.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xiao1
 * @since 2024-12
 * 模板组别
 */

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("c_generate_template_group")
public class GenerateTemplateGroup extends SuperEntity<Long> {


    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    @NotBlank(message = "分组名称不能为空")
    private String name;


    /**
     * 分组描述
     */
    @Schema(description = "分组描述")
    @NotBlank(message = "分组描述不能为空")
    private String description;

    /**
     * 是否作为默认分组
     */
    @Schema(description = "是否作为默认分组")
    private Boolean isDefault;



    @Schema(description = "租户ID")
    private Long tenantId;


}
