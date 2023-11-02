package com.wemirr.platform.demo.domain.resp;

import com.wemirr.framework.i18n.annotation.I18nField;
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
public class I18nDemoResp {


    @I18nField(code = "i18n.demo.type", target = "typeLabel")
    private String type;

    private String typeLabel;

}
