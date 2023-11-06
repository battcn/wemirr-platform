package com.wemirr.platform.demo.domain.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wemirr.framework.i18n.annotation.I18nField;
import com.wemirr.platform.demo.domain.enums.I18nEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class I18nDemoResp {

    @I18nField(code = "i18n.test", target = "staticName")
    private String name;

    private String staticName;


    @I18nField(code = "i18n.demo.type", target = "typeLabel")
    private String type;

    private String typeLabel;

    @I18nField(target = "i18nEnumLabel")
    private I18nEnum i18nEnum;

    private String i18nEnumLabel;

    @I18nField
    private String dbCode;

}
