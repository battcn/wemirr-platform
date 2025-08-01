package com.wemirr.platform.flow.domain.dto.req.warmflow.category;

import com.wemirr.framework.commons.entity.SuperEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FlowCategorySaveReq {

    /**
     * 流程分类ID
     */
    @NotNull(message = "流程分类ID不能为空", groups = { SuperEntity.Update.class })
    private Long id;

    /**
     * 父流程分类id
     */
    @NotNull(message = "父流程分类id不能为空", groups = {SuperEntity.Save.class, SuperEntity.Update.class})
    private Long parentId;

    /**
     * 流程分类名称
     */
    @NotBlank(message = "流程分类名称不能为空", groups = {SuperEntity.Save.class, SuperEntity.Update.class})
    private String categoryName;

    /**
     * 显示顺序
     */
    private Long orderNum;
}
