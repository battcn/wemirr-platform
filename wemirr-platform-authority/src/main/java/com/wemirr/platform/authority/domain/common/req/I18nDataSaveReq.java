package com.wemirr.platform.authority.domain.common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(description = "I18N国际化入参")
public class I18nDataSaveReq {


    @Schema(description = "编码")
    private String code;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "各国语言")
    private List<LocaleLanguage> languages;

    @Data
    public static class LocaleLanguage {

        @Schema(description = "语言")
        private String locale;

        @Schema(description = "消息")
        private String message;

    }


}
