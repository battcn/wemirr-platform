package com.wemirr.platform.ai.core.helper;

import com.wemirr.framework.ai.core.enums.ModelType;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.ai.core.exception.ModelNotFoundException;
import com.wemirr.platform.ai.domain.entity.ModelEntity;
import com.wemirr.platform.ai.service.ModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 模型配置获取器
 *
 * @author Levin
 * @since 2025-12-27
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ModelConfigRetriever {

    private final ModelService modelService;

    /**
     * 根据ID获取模型配置，不存在时抛出异常
     */
    public ModelEntity getRequiredModel(Long modelId) {
        return Optional.ofNullable(modelService.getById(modelId))
                .orElseThrow(() -> {
                    log.error("模型配置不存在: modelId={}", modelId);
                    return new ModelNotFoundException(modelId);
                });
    }

    /**
     * 根据ID和类型获取模型配置
     */
    public ModelEntity getRequiredModel(Long modelId, ModelType expectedType) {
        ModelEntity model = getRequiredModel(modelId);

        if (model.getType() != expectedType) {
            log.error("模型类型不匹配: modelId={}, expected={}, actual={}",
                    modelId, expectedType, model.getType());
            throw new IllegalArgumentException(
                    String.format("模型类型不匹配，期望: %s, 实际: %s", expectedType, model.getType())
            );
        }

        return model;
    }

    /**
     * 根据ID和类型获取模型配置（使用查询条件）
     */
    public ModelEntity getModelByIdAndType(Long modelId, ModelType modelType) {
        return Optional.ofNullable(
                modelService.getOne(
                        Wraps.<ModelEntity>lbQ()
                                .eq(ModelEntity::getId, modelId)
                                .eq(ModelEntity::getType, modelType)
                )
        ).orElseThrow(() -> {
            log.error("模型配置不存在或类型不匹配: modelId={}, type={}", modelId, modelType);
            return new ModelNotFoundException(modelId);
        });
    }

    /**
     * 根据ID获取模型配置（可选）
     */
    public Optional<ModelEntity> getModel(Long modelId) {
        if (modelId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(modelService.getById(modelId));
    }
}
