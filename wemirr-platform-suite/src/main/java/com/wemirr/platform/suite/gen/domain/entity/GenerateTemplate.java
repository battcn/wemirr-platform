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
 * 生成模板实体类
 *
 * @author xiao1
 * @since 2024-12
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("c_generate_template")
public class GenerateTemplate extends SuperEntity<Long> {


    @NotBlank(message = "模板名称不能为空")
    private String name;

    /**
     * 文件路径模板
     * 用于文件生成路径
     * wp/src/main/java/${packagePath}/${moduleName}/controller/${ClassName}Controller.java
     */
    @Schema(description = "模板路径")
    private String generatePath;

    /**
     * 模板描述
     */
    @Schema(description = "模板描述")
    private String description;

    /**
     * 模板代码
     */
    @Schema(description = "模板代码")
    private String code;


    @Schema(description = "租户ID")
    private Long tenantId;

}
