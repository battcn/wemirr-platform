package com.wemirr.platform.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.ai.core.enums.ModelType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * @author xJh
 * @since 2025/10/9
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_model")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "多模态模型配置实体")
public class ModelEntity extends SuperEntity<Long> {

    @Schema(description = "模型提供商")
    private String provider;

    @Schema(description = "模型类型")
    private ModelType type;

    @Schema(description = "模型名称")
    private String name;

    @Schema(description = "API密钥")
    private String apiKey;

    @Schema(description = "基础URL")
    private String baseUrl;

    @Schema(description = "模型配置属性")
    @TableField(typeHandler = JacksonTypeHandler.class)
    protected Map<String, Object> variables;

    @Schema(description = "租户ID")
    private Long tenantId;

    @TableField(exist = false)
    @Schema(description = "是否返回深度思考结果")
    private Boolean returnThinking = false;

    @TableField(exist = false)
    @Schema(description = "是否联网搜索")
    private Boolean enableWebSearch = false;
}
