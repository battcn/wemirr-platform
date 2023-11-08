package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.Entity;
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
@TableName("common_i18n_locale_message")
@Schema(name = "I18nLocaleMessage", description = "I18N语言数据")
public class I18nLocaleMessage extends Entity<Long> {

    @Schema(description = "i18n_data.id")
    private Long parentId;

    @Schema(description = "语言环境")
    private String locale;

    @Schema(description = "消息内容")
    private String message;

}
