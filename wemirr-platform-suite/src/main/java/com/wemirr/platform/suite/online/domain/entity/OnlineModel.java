package com.wemirr.platform.suite.online.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_online_model")
public class OnlineModel extends SuperEntity<Long> {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "定义KEY")
    private String definitionKey;

    @Schema(description = "状态")
    private Boolean status;

    @Version
    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "备注")
    private String description;

    @Schema(defaultValue = "脚本")
    private String formScript;

    @Schema(description = "表单配置")
    private String formSchemas;

    @Schema(description = "表单crud配置")
    private String formCrudConfig;

}
