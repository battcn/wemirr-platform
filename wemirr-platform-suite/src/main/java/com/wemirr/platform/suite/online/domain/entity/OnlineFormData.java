package com.wemirr.platform.suite.online.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_online_form_data")
@EqualsAndHashCode(callSuper = true)
public class OnlineFormData extends SuperEntity<Long> {


    @Schema(defaultValue = "定义KEY")
    private String definitionKey;

    @Schema(defaultValue = "表单数据")
    private String formData;

    @Schema(defaultValue = "租户ID")
    private Long tenantId;


}
