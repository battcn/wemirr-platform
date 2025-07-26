package com.wemirr.platform.warmflow.domain.resp.warmflow;

import com.wemirr.platform.warmflow.domain.vo.FlowCategoryVo;
import lombok.Data;

import java.io.Serial;
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
