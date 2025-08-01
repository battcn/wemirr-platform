//package com.wemirr.platform.warmflow.repository;
//
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Constants;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.wemirr.platform.warmflow.domain.bo.FlowTaskBo;
//import com.wemirr.platform.warmflow.domain.vo.FlowTaskVo;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//
///**
// * 任务信息Mapper接口
// *
// * @author may
// * @date 2024-03-02
// */
//public interface FlwTaskMapper {
//
//    /**
//     * 获取待办信息
//     *
//     * @param page         分页
//     * @param queryWrapper 条件
//     * @return 结果
//     */
//    Page<FlowTaskVo> getListRunTask(@Param("page") Page<FlowTaskVo> page, @Param(Constants.WRAPPER) Wrapper<FlowTaskBo> queryWrapper);
//
//    /**
//     * 获取待办信息
//     *
//     * @param queryWrapper 条件
//     * @return 结果
//     */
//    List<FlowTaskVo> getListRunTask(@Param(Constants.WRAPPER) Wrapper<FlowTaskBo> queryWrapper);
//
//    /**
//     * 获取已办
//     *
//     * @param page         分页
//     * @param queryWrapper 条件
//     * @return 结果
//     */
//    Page<FlowHisTaskVo> getListFinishTask(@Param("page") Page<FlowTaskVo> page, @Param(Constants.WRAPPER) Wrapper<FlowTaskBo> queryWrapper);
//
//    /**
//     * 查询当前用户的抄送
//     *
//     * @param page         分页
//     * @param queryWrapper 条件
//     * @return 结果
//     */
//    Page<FlowTaskVo> getTaskCopyByPage(@Param("page") Page<FlowTaskVo> page, @Param(Constants.WRAPPER) QueryWrapper<FlowTaskBo> queryWrapper);
//}
