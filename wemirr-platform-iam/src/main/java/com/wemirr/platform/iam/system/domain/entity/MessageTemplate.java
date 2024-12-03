package com.wemirr.platform.iam.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 消息模板
 *
 * @author Levin
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_message_template")
@EqualsAndHashCode(callSuper = true)
@Schema(name = "MessageTemplate", description = "消息模板")
public class MessageTemplate extends SuperEntity<Long> {

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
