package com.wemirr.platform.flow.domain.resp.warmflow;

import com.wemirr.platform.flow.domain.vo.FlowCategoryVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class FlowCategoryResp {

    /**
     * 流程分类ID
     */
    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 父类别名称
     */
    private String parentName;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 流程分类名称
     */
    private String categoryName;

    /**
     * 显示顺序
     */
    private Long orderNum;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 子菜单
     */
    private List<FlowCategoryVo> children = new ArrayList<>();
}
