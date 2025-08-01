package com.wemirr.platform.flow.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.flow.domain.entity.FlowCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流程分类Mapper接口
 *
 * @author battcn
 * @date 2023-06-27
 */
@Repository
public interface FlwCategoryMapper extends SuperMapper<FlowCategory> {

    /**
     * 统计指定流程分类ID的分类数量
     *
     * @param categoryId 流程分类ID
     * @return 该流程分类ID的分类数量
     */
//    @DataPermission({
//        @DataColumn(key = "deptName", value = "createDept")
//    })
    long countCategoryById(Long categoryId);

    /**
     * 根据父流程分类ID查询其所有子流程分类的列表
     *
     * @param parentId 父流程分类ID
     * @return 包含子流程分类的列表
     */
    default List<FlowCategory> selectListByParentId(Long parentId) {
        return this.selectList(new LambdaQueryWrapper<FlowCategory>()
                .select(FlowCategory::getId)
                .apply("find_in_set({0}, " + "ancestors" + ") <> 0", parentId));
    }

    /**
     * 根据父流程分类ID查询包括父ID及其所有子流程分类ID的列表
     *
     * @param parentId 父流程分类ID
     * @return 包含父ID和子流程分类ID的列表
     */
    default List<Long> selectCategoryIdsByParentId(Long parentId) {
        return Stream.concat(
                this.selectListByParentId(parentId).stream()
                        .map(FlowCategory::getId),
                Stream.of(parentId)
        ).collect(Collectors.toList());
    }

}
