package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xJh
 * @date 2025/11/4
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_chat_agent")
@Schema(description = "聊天智能体实体")
public class ChatAgent extends SuperEntity<Long> {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "智能体名称")
    private String name;

    //通过模型名称自然可以匹配创建到对应的模型策略
    @Schema(description = "绑定模型名称")
    private String modelId;

    @Schema(description = "智能体描述")
    private String description;

    @Schema(description = "智能体角色预设")
    private String aiSystemMessage;

    @Schema(description = "智能体头像")
    private String avatar;

    @Schema(description = "关联知识库ID")
    private Long kbId;

    // JSON格式存储工具名称列表，如：["平台菜单查询工具"]
    @Schema(description = "智能体工具配置")
    private String tools;
}
