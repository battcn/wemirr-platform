package com.wemirr.platform.flow.service;

import cn.hutool.core.lang.tree.Tree;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.flow.domain.dto.req.warmflow.category.FlowCategoryReq;
import com.wemirr.platform.flow.domain.dto.req.warmflow.category.FlowCategorySaveReq;
import com.wemirr.platform.flow.domain.dto.resp.warmflow.FlowCategoryResp;
import com.wemirr.platform.flow.domain.entity.FlowCategory;

import java.util.List;

/**
 * 流程分类Service接口
 *
 * @author battcn
 */
public interface IFlwCategoryService extends SuperService<FlowCategory> {

    /**
     * 查询流程分类
     *
     * @param categoryId 主键
     * @return 流程分类
     */
    FlowCategoryResp queryById(Long categoryId);

    /**
     * 根据流程分类ID查询流程分类名称
     *
     * @param categoryId 流程分类ID
     * @return 流程分类名称
     */
//    String selectCategoryNameById(Long categoryId);

    /**
     * 查询符合条件的流程分类列表
     *
     * @param req 查询条件
     * @return 流程分类列表
     */
    List<FlowCategoryResp> queryList(FlowCategoryReq req);

    /**
     * 查询流程分类树结构信息
     *
     * @param category 流程分类信息
     * @return 流程分类树信息集合
     */
    List<Tree<String>> selectCategoryTreeList(FlowCategoryReq category);

    /**
     * 校验流程分类是否有数据权限
     *
     * @param categoryId 流程分类ID
     */
    void checkCategoryDataScope(Long categoryId);

    /**
     * 校验流程分类名称是否唯一
     *
     * @param category 流程分类信息
     * @return 结果
     */
    boolean checkCategoryNameUnique(FlowCategorySaveReq category);

    /**
     * 查询流程分类是否存在流程定义
     *
     * @param categoryId 流程分类ID
     * @return 结果 true 存在 false 不存在
     */
    boolean checkCategoryExistDefinition(Long categoryId);

    /**
     * 是否存在流程分类子节点
     *
     * @param categoryId 流程分类ID
     * @return 结果
     */
    boolean hasChildByCategoryId(Long categoryId);

    /**
     * 新增流程分类
     *
     * @param req 流程分类
     * @return 是否新增成功
     */
    int insert(FlowCategorySaveReq req);

    /**
     * 修改流程分类
     *
     * @param bo 流程分类
     * @return 是否修改成功
     */
    int updateByBo(FlowCategorySaveReq bo);

    /**
     * 删除流程分类信息
     *
     * @param categoryId 主键
     * @return 是否删除成功
     */
    int deleteWithValidById(Long categoryId);
}
