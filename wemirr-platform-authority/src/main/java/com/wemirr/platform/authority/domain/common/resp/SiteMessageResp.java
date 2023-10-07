package com.wemirr.platform.authority.domain.common.resp;

import com.wemirr.platform.authority.domain.baseinfo.enums.ReceiverType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class SiteMessageResp {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "级别")
    private String level;
    @Schema(description = "接收类型")
    private ReceiverType type;
    @Schema(description = "接收人")
    private List<Long> receiver;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "创建人")
    private String createdName;
    @Schema(description = "创建时间")
    private Instant createdTime;

}
