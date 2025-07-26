package com.wemirr.platform.warmflow.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.commons.utils.StreamUtils;
import com.wemirr.framework.commons.utils.TreeBuildUtils;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.warmflow.domain.entity.FlowCategory;
import com.wemirr.platform.warmflow.domain.req.warmflow.category.FlowCategoryReq;
import com.wemirr.platform.warmflow.domain.req.warmflow.category.FlowCategorySaveReq;
import com.wemirr.platform.warmflow.domain.resp.warmflow.FlowCategoryResp;
import com.wemirr.platform.warmflow.domain.vo.FlowCategoryVo;
import com.wemirr.platform.warmflow.repository.FlwCategoryMapper;
import com.wemirr.platform.warmflow.service.IFlwCategoryService;
import lombok.RequiredArgsConstructor;
import org.dromara.warm.flow.core.service.DefService;
import org.dromara.warm.flow.orm.entity.FlowDefinition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程分类Service业务层处理
 *
 * @author may
 */
//@ConditionalOnEnable

@Service
@RequiredArgsConstructor
public class FlwCategoryServiceImpl extends SuperServiceImpl<FlwCategoryMapper, FlowCategory> implements IFlwCategoryService {

    private final DefService defService;
    private final AuthenticationContext context;

    /**
     * 查询流程分类
     *
     * @param categoryId 主键
     * @return 流程分类
     */
    @Override
    public FlowCategoryResp queryById(Long categoryId) {
        FlowCategory category = baseMapper.selectById(categoryId);
        FlowCategoryResp bean = BeanUtilPlus.toBean(category, FlowCategoryResp.class);
        if (ObjectUtil.isNull(category)) {
            return null;
        }
        FlowCategory parentCategory = baseMapper.selectOne(new LambdaQueryWrapper<FlowCategory>()
                .select(FlowCategory::getCategoryName).eq(FlowCategory::getId, category.getParentId()));
        if (ObjectUtil.isNotNull(parentCategory)) {
            bean.setParentName(parentCategory.getCategoryName());
        }
        return bean;
    }

//    /**
//     * 根据流程分类ID查询流程分类名称
//     *
//     * @param categoryId 流程分类ID
//     * @return 流程分类名称
//     */
//    @Cacheable(cacheNames = FlowConstant.FLOW_CATEGORY_NAME, key = "#categoryId")
//    @Override
//    public String selectCategoryNameById(Long categoryId) {
//        if (ObjectUtil.isNull(categoryId)) {
//            return null;
//        }
//        FlowCategory category = baseMapper.selectOne(new LambdaQueryWrapper<FlowCategory>()
//            .select(FlowCategory::getCategoryName).eq(FlowCategory::getCategoryId, categoryId));
//        if (!ObjectUtil.isNull(category)) {
//            return category.getCategoryName();
//        }
//        return  null;
//    }
//

    /**
     * 查询符合条件的流程分类列表
     *
     * @param req 查询条件
     * @return 流程分类列表
     */
    @Override
    public List<FlowCategoryResp> queryList(FlowCategoryReq req) {
        LambdaQueryWrapper<FlowCategory> lqw = buildQueryWrapper(req);
        return BeanUtilPlus.toBeans(baseMapper.selectList(lqw), FlowCategoryResp.class);
    }


    /**
     * 查询流程分类树结构信息
     *
     * @param category 流程分类信息
     * @return 流程分类树信息集合
     */
    @Override
    public List<Tree<String>> selectCategoryTreeList(FlowCategoryReq category) {
        LambdaQueryWrapper<FlowCategory> lqw = buildQueryWrapper(category);
        List<FlowCategory> categorys = baseMapper.selectList(lqw);
        if (CollUtil.isEmpty(categorys)) {
            return CollUtil.newArrayList();
        }
        // 获取当前列表中每一个节点的parentId，然后在列表中查找是否有id与其parentId对应，若无对应，则表明此时节点列表中，该节点在当前列表中属于顶级节点
        List<Tree<String>> treeList = CollUtil.newArrayList();
        for (FlowCategory d : categorys) {
            String parentId = d.getParentId().toString();
            FlowCategory categoryVo = StreamUtils.findFirst(categorys, it -> it.getId().toString().equals(parentId));
            if (ObjectUtil.isNull(categoryVo)) {
                List<Tree<String>> trees = TreeBuildUtils.build(categorys, parentId, (dept, tree) ->
                    tree.setId(dept.getId().toString())
                        .setParentId(dept.getParentId().toString())
                        .setName(dept.getCategoryName())
                        .setWeight(dept.getOrderNum()));
                Tree<String> tree = StreamUtils.findFirst(trees, it -> it.getId().equals(d.getId().toString()));
                treeList.add(tree);
            }
        }
        return treeList;
    }

    /**
     * 校验流程分类是否有数据权限
     *
     * @param categoryId 流程分类ID
     */
    @Override
    public void checkCategoryDataScope(Long categoryId) {
        if (ObjectUtil.isNull(categoryId)) {
            return;
        }
        //TODO: 判断分类维护权限
//        if (context.isSuperAdmin()) {
//            return;
//        }
        //TODO: 判断分类数据权限
//        if (baseMapper.countCategoryById(categoryId) == 0) {
//            throw new CheckedException("没有权限访问流程分类数据！");
//        }
    }

    /**
     * 校验流程分类名称是否唯一
     *
     * @param category 流程分类信息
     * @return 结果
     */
    @Override
    public boolean checkCategoryNameUnique(FlowCategorySaveReq category) {
        Long exist = baseMapper.selectCount(new LambdaQueryWrapper<FlowCategory>()
            .eq(FlowCategory::getCategoryName, category.getCategoryName())
            .eq(FlowCategory::getParentId, category.getParentId())
            .ne(ObjectUtil.isNotNull(category.getId()), FlowCategory::getId, category.getId()));
        return exist < 1;
    }

    /**
     * 查询流程分类是否存在流程定义
     *
     * @param categoryId 流程分类ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkCategoryExistDefinition(Long categoryId) {
        FlowDefinition definition = new FlowDefinition();
        definition.setCategory(categoryId.toString());
        return defService.exists(definition);
    }

    /**
     * 是否存在流程分类子节点
     *
     * @param categoryId 流程分类ID
     * @return 结果
     */
    @Override
    public boolean hasChildByCategoryId(Long categoryId) {
        return baseMapper.exists(new LambdaQueryWrapper<FlowCategory>()
            .eq(FlowCategory::getParentId, categoryId));
    }

    private LambdaQueryWrapper<FlowCategory> buildQueryWrapper(FlowCategoryReq req) {
        LambdaQueryWrapper<FlowCategory> lqw = Wrappers.lambdaQuery();
//        lqw.eq(FlowCategory::getDeleted, SystemConstants.NORMAL);  逻辑删除状态
        lqw.eq(ObjectUtil.isNotNull(req.getId()), FlowCategory::getId, req.getId());
        lqw.eq(ObjectUtil.isNotNull(req.getParentId()), FlowCategory::getParentId, req.getParentId());
        lqw.like(StrUtil.isNotBlank(req.getCategoryName()), FlowCategory::getCategoryName, req.getCategoryName());
        lqw.orderByAsc(FlowCategory::getAncestors);
        lqw.orderByAsc(FlowCategory::getParentId);
        lqw.orderByAsc(FlowCategory::getOrderNum);
        lqw.orderByAsc(FlowCategory::getId);
        return lqw;
    }
//
    /**
     * 新增流程分类
     *
     * @param req 流程分类
     * @return 是否新增成功
     */
    @Override
    public int insert(FlowCategorySaveReq req) {
        FlowCategory info = baseMapper.selectById(req.getParentId());
        FlowCategory category = BeanUtilPlus.toBean(req, FlowCategory.class);
        category.setAncestors(info.getAncestors() + StrUtil.COMMA + category.getParentId());
        return baseMapper.insert(category);
    }

    /**
     * 修改流程分类
     *
     * @param req 流程分类
     * @return 是否修改成功
     */
//    @CacheEvict(cacheNames = FlowConstant.FLOW_CATEGORY_NAME, key = "#bo.categoryId")
    @Override
    public int updateByBo(FlowCategorySaveReq req) {
        FlowCategory category = BeanUtilPlus.toBean(req, FlowCategory.class);
        FlowCategory oldCategory = baseMapper.selectById(category.getId());
        if (ObjectUtil.isNull(oldCategory)) {
            throw new CheckedException("流程分类不存在，无法修改");
        }
        if (!oldCategory.getParentId().equals(category.getParentId())) {
            // 如果是新父流程分类 则校验是否具有新父流程分类权限 避免越权
            this.checkCategoryDataScope(category.getParentId());
            FlowCategory newParentCategory = baseMapper.selectById(category.getParentId());
            if (ObjectUtil.isNotNull(newParentCategory)) {
                String newAncestors = newParentCategory.getAncestors() + StrUtil.COMMA + newParentCategory.getId();
                String oldAncestors = oldCategory.getAncestors();
                category.setAncestors(newAncestors);
                updateCategoryChildren(category.getId(), newAncestors, oldAncestors);
            }
        } else {
            category.setAncestors(oldCategory.getAncestors());
        }
        return baseMapper.updateById(category);
    }

    /**
     * 修改子元素关系
     *
     * @param categoryId   被修改的流程分类ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    private void updateCategoryChildren(Long categoryId, String newAncestors, String oldAncestors) {
        List<FlowCategory> children = baseMapper.selectList(new LambdaQueryWrapper<FlowCategory>().in(FlowCategory::getAncestors)
            .apply("find_in_set({0}, " + "ancestors" + ") <> 0", categoryId));
        List<FlowCategory> list = new ArrayList<>();
        for (FlowCategory child : children) {
            FlowCategory category = new FlowCategory();
            category.setId(child.getId());
            category.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
            list.add(category);
        }
        if (CollUtil.isNotEmpty(list)) {
            baseMapper.updateBatchSomeColumnById(list);
        }
    }

    /**
     * 删除流程分类信息
     *
     * @param categoryId 主键
     * @return 是否删除成功
     */
//    @CacheEvict(cacheNames = FlowConstant.FLOW_CATEGORY_NAME, key = "#categoryId")
    @Override
    public int deleteWithValidById(Long categoryId) {
        return baseMapper.deleteById(categoryId);
    }
}
