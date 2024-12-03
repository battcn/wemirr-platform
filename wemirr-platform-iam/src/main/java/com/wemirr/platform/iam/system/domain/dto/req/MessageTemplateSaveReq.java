package com.wemirr.platform.iam.system.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author levin
 */
@Data
public class MessageTemplateSaveReq {

    @Schema(description = "模板编码")
    private String code;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "消息标题（如邮件标题）")
    private String subject;

    @Schema(description = "消息内容模板，支持占位符")
    private String content;
}
