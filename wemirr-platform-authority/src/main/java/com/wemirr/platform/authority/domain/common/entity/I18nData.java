package com.wemirr.platform.authority.domain.common.entity;

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
@EqualsAndHashCode(callSuper = true)
@TableName("common_i18n_data")
@Schema(name = "I18nData", description = "字典项")
public class I18nData extends SuperEntity<Long> {

    @Schema(description = "编码")
    private String code;
    @Schema(description = "语言")
    private String language;
    @Schema(description = "消息")
    private String message;
    @Schema(description = "备注")
    private String remark;


}
