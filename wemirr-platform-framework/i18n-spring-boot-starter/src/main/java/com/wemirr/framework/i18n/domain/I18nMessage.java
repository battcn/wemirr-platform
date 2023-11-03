package com.wemirr.framework.i18n.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 对标于 message bundle 的文件消息的抽象
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "国际化信息")
public class I18nMessage {

    @Schema(title = "国际化标识")
    private String code;

    @Schema(title = "文本值，可以使用 { } 加角标，作为占位符")
    private String message;

    @Schema(title = "语言")
    private String language;

    public String buildKey() {
        return language + ":" + code;
    }

}