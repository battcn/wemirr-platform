package com.wemirr.platform.iam.base.domain.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class MessageNotifyPageResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "消息类型")
    private String type;

    @Schema(description = "消息模板ID")
    private Long templateId;

    @Schema(description = "消息变量")
    private String variables;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "接收用户ID")
    private Long userId;

    @Schema(description = "订阅人")
    private String nickname;

    @Schema(description = "创建人")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;

}
