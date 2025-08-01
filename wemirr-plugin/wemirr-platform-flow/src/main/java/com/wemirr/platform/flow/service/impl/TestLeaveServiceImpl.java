//package com.wemirr.platform.warmflow.service.impl;
//
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.core.util.StrUtil;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.wemirr.framework.commons.BeanUtilPlus;
//import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
//import com.wemirr.platform.warmflow.domain.entity.TestLeave;
//import com.wemirr.platform.warmflow.domain.enums.ApprovalStatus;
//import com.wemirr.platform.warmflow.domain.event.ProcessDeleteEvent;
//import com.wemirr.platform.warmflow.domain.event.ProcessEvent;
//import com.wemirr.platform.warmflow.domain.event.ProcessTaskEvent;
//import com.wemirr.platform.warmflow.domain.req.TestLeavePageReq;
//import com.wemirr.platform.warmflow.domain.req.warmflow.TestLeaveSaveReq;
//import com.wemirr.platform.warmflow.domain.resp.warmflow.TestLeaveResp;
//import com.wemirr.platform.warmflow.domain.vo.TestLeaveVo;
//import com.wemirr.platform.warmflow.repository.TestLeaveMapper;
//import com.wemirr.platform.warmflow.service.ITestLeaveService;
//import com.wemirr.platform.warmflow.service.WorkflowService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 请假Service业务层处理
// *
// * @author battcn
// * @date 2023-07-21
// */
////@ConditionalOnEnable
//@RequiredArgsConstructor
//@Service
//@Slf4j
//public class TestLeaveServiceImpl extends SuperServiceImpl<TestLeaveMapper, TestLeave> implements ITestLeaveService {
//
//    private final WorkflowService workflowService;
//
//    /**
//     * spel条件表达：判断小于2
//     *
//     * @param leaveDays 待判断的变量（可不传自行返回true或false）
//     * @return boolean
//     */
//    public boolean eval(Integer leaveDays) {
//        if (leaveDays <= 2) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 查询请假
//     */
//    @Override
//    public TestLeave queryById(Long id) {
//        return baseMapper.selectById(id);
//    }
//
//    /**
//     * 查询请假列表
//     */
//    @Override
//    public IPage<TestLeave> queryPageList(TestLeavePageReq req) {
//        LambdaQueryWrapper<TestLeave> lqw = buildQueryWrapper(req);
//        return baseMapper.selectPage(req.buildPage(), lqw);
//    }
//
//    /**
//     * 查询请假列表
//     */
//    @Override
//    public List<TestLeaveResp> queryList(TestLeavePageReq bo) {
//        LambdaQueryWrapper<TestLeave> lqw = buildQueryWrapper(bo);
//        List<TestLeave> testLeaves = baseMapper.selectList(lqw);
//        return BeanUtilPlus.toBeans(testLeaves, TestLeaveResp.class);
//    }
//
//    private LambdaQueryWrapper<TestLeave> buildQueryWrapper(TestLeavePageReq req) {
//        LambdaQueryWrapper<TestLeave> lqw = Wrappers.lambdaQuery();
//        lqw.eq(StrUtil.isNotBlank(req.getLeaveType()), TestLeave::getLeaveType, req.getLeaveType());
//        lqw.ge(req.getStartLeaveDays() != null, TestLeave::getLeaveDays, req.getStartLeaveDays());
//        lqw.le(req.getEndLeaveDays() != null, TestLeave::getLeaveDays, req.getEndLeaveDays());
//        lqw.orderByDesc(TestLeave::getCreatedTime);
//        return lqw;
//    }
//
//    /**
//     * 新增请假
//     */
//    @Override
//    public void insert(TestLeaveSaveReq req) {
//        long day = DateUtil.betweenDay(req.getStartDate(), req.getEndDate(), true);
//        // 截止日期也算一天
//        req.setLeaveDays((int) day + 1);
//        TestLeave add = BeanUtilPlus.toBean(req, TestLeave.class);
//        if (StrUtil.isBlank(add.getStatus())) {
//            add.setStatus(ApprovalStatus.DRAFT.getStatus());
//        }
//    }
//
//    /**
//     * 修改请假
//     */
//    @Override
//    public void update(TestLeaveSaveReq bo) {
//        TestLeave update = BeanUtilPlus.toBean(bo, TestLeave.class);
//        baseMapper.updateById(update);
//    }
//
//    /**
//     * 批量删除请假
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean deleteWithValidByIds(List<Long> ids) {
//        workflowService.deleteInstance(ids);
//        return baseMapper.deleteByIds(ids) > 0;
//    }
//
//    /**
//     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
//     * 正常使用只需#processEvent.flowCode=='leave1'
//     * 示例为了方便则使用startsWith匹配了全部示例key
//     *
//     * @param processEvent 参数
//     */
//    @EventListener(condition = "#processEvent.flowCode.startsWith('leave')")
//    public void processHandler(ProcessEvent processEvent) {
//        log.info("当前任务执行了{}", processEvent.toString());
//        TestLeave testLeave = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
//        testLeave.setStatus(processEvent.getStatus());
//        // 用于例如审批附件 审批意见等 存储到业务表内 自行根据业务实现存储流程
//        Map<String, Object> params = processEvent.getParams();
//        if (MapUtil.isNotEmpty(params)) {
//            // 历史任务扩展(通常为附件)
//            String hisTaskExt = Convert.toStr(params.get("hisTaskExt"));
//            // 办理人
//            String handler = Convert.toStr(params.get("handler"));
//            // 办理意见
//            String message = Convert.toStr(params.get("message"));
//        }
//        if (processEvent.getSubmit()) {
//            testLeave.setStatus(ApprovalStatus.WAITING.getStatus());
//        }
//        baseMapper.updateById(testLeave);
//    }
//
//    /**
//     * 执行任务创建监听
//     * 示例：也可通过  @EventListener(condition = "#processTaskEvent.flowCode=='leave1'")进行判断
//     * 在方法中判断流程节点key
//     * if ("xxx".equals(processTaskEvent.getNodeCode())) {
//     * //执行业务逻辑
//     * }
//     *
//     * @param processTaskEvent 参数
//     */
//    @EventListener(condition = "#processTaskEvent.flowCode.startsWith('leave')")
//    public void processTaskHandler(ProcessTaskEvent processTaskEvent) {
//        log.info("当前任务创建了{}", processTaskEvent.toString());
//    }
//
//    /**
//     * 监听删除流程事件
//     * 正常使用只需#processDeleteEvent.flowCode=='leave1'
//     * 示例为了方便则使用startsWith匹配了全部示例key
//     *
//     * @param processDeleteEvent 参数
//     */
//    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('leave')")
//    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
//        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
//        TestLeave testLeave = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
//        if (ObjectUtil.isNull(testLeave)) {
//            return;
//        }
//        baseMapper.deleteById(testLeave.getId());
//    }
//
//}
