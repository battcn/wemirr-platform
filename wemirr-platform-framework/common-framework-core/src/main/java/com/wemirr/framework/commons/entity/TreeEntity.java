package com.wemirr.framework.commons.entity;


import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树形实体
 *
 * @author Levin
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public class TreeEntity<E, T extends Serializable> extends SuperEntity<T> {

    private static final long serialVersionUID = 1788431089674465975L;
    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过255")
    @TableField(value = "label", condition = SqlCondition.LIKE)
    protected String label;

    /**
     * 父ID
     */
    @Schema(description = "父ID")
    @TableField(value = "parent_id")
    protected T parentId;

    @TableField(value = "`tel`")
    private String tel;

    /**
     * 排序
     */
    @Schema(description = "排序号")
    @TableField(value = "`sequence`")
    protected Integer sequence;


    @Schema(description = "子节点")
    @TableField(exist = false)
    protected List<E> children = Lists.newArrayList();

    @TableField(exist = false)
    protected Integer size = 0;

    /**
     * 初始化子类
     */
    public void initChildren() {
        if (getChildren() == null) {
            this.setChildren(new ArrayList<>());
        }
    }
}
