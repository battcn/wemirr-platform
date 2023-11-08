package com.wemirr.platform.authority.domain.common.resp;

import com.wemirr.platform.authority.domain.common.entity.I18nLocaleMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class I18nDataPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;

    @Schema(description = "语言区域")
    private List<I18nLocaleMessage> languages;

}
