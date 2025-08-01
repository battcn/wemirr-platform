package com.wemirr.platform.warmflow.domain.bo;

import com.wemirr.framework.commons.entity.SuperEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 流程分类业务对象 wf_category
 *
 * @author may
 * @date 2023-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FlowCategoryBo extends SuperEntity<Long> {

    /**
     * 流程分类ID
     */
    @NotNull(message = "流程分类ID不能为空", groups = { SuperEntity.Update.class })
    private Long categoryId;

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
