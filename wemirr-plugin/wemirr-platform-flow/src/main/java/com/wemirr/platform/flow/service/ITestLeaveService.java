//package com.wemirr.platform.warmflow.service;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.wemirr.framework.db.mybatisplus.ext.SuperService;
//import com.wemirr.platform.warmflow.domain.bo.TestLeaveBo;
//import com.wemirr.platform.warmflow.domain.entity.TestLeave;
//import com.wemirr.platform.warmflow.domain.req.TestLeavePageReq;
//import com.wemirr.platform.warmflow.domain.req.warmflow.TestLeaveSaveReq;
//import com.wemirr.platform.warmflow.domain.resp.warmflow.TestLeaveResp;
//import com.wemirr.platform.warmflow.domain.vo.TestLeaveVo;
//
//import java.util.List;
//
///**
// * 请假Service接口
// *
// * @author battcn
// * @date 2023-07-21
// */
//public interface ITestLeaveService extends SuperService<TestLeave> {
//
//    /**
//     * 查询请假
//     */
//    TestLeave queryById(Long id);
//
//    /**
//     * 查询请假列表
//     */
//    IPage<TestLeave> queryPageList(TestLeavePageReq req);
//
//    /**
//     * 查询请假列表
//     */
//    List<TestLeaveResp> queryList(TestLeavePageReq req);
//
//    /**
//     * 新增请假
//     */
//    void insert(TestLeaveSaveReq req);
//
//    /**
//     * 修改请假
//     */
//    void update(TestLeaveSaveReq req);
//
//    /**
//     * 校验并批量删除请假信息
//     */
//    Boolean deleteWithValidByIds(List<Long> ids);
//
//
//}
