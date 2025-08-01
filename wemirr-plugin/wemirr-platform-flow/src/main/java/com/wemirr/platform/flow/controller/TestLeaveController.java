//package com.wemirr.platform.warmflow.controller;
//
//import cn.dev33.satoken.annotation.SaCheckPermission;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.wemirr.framework.commons.entity.SuperEntity;
//import com.wemirr.framework.excel.annotation.ResponseExcel;
//import com.wemirr.platform.warmflow.domain.entity.TestLeave;
//import com.wemirr.platform.warmflow.domain.req.TestLeavePageReq;
//import com.wemirr.platform.warmflow.domain.req.warmflow.TestLeaveSaveReq;
//import com.wemirr.platform.warmflow.domain.resp.warmflow.TestLeaveResp;
//import com.wemirr.platform.warmflow.service.ITestLeaveService;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * 请假
// *
// * @author may
// * @date 2023-07-21
// */
//@Validated
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/workflow/leave")
//public class TestLeaveController {
//
//    private static ITestLeaveService testLeaveService;
//
//    /**
//     * 查询请假列表
//     */
//    @SaCheckPermission("workflow:leave:list")
//    @GetMapping("/list")
//    public IPage<TestLeave> list(TestLeavePageReq req) {
//        return testLeaveService.queryPageList(req);
//    }
//
//    /**
//     * 导出请假列表
//     *
//     * @return Excel导出实体
//     */
//    @PostMapping("/export")
//    @ResponseExcel(fileName = "请假")
//    public List<TestLeaveResp> export(TestLeavePageReq req, HttpServletResponse response) {
//        return testLeaveService.queryList(req);
//    }
//
//    /**
//     * 获取请假详细信息
//     *
//     * @param id 主键
//     */
//    @GetMapping("/{id}")
//    public TestLeave getInfo(@NotNull(message = "主键不能为空")
//                             @PathVariable Long id) {
//        return testLeaveService.queryById(id);
//    }
//
//    /**
//     * 新增请假
//     */
//    @SaCheckPermission("workflow:leave:add")
////    @Log(title = "请假", businessType = BusinessType.INSERT)
//    @PostMapping()
//    public void add(@Validated(SuperEntity.Save.class) @RequestBody TestLeaveSaveReq req) {
//        testLeaveService.insert(req);
//    }
//
//    /**
//     * 修改请假
//     */
////    @SaCheckPermission("workflow:leave:edit")
////    @Log(title = "请假", businessType = BusinessType.UPDATE)
////    @RepeatSubmit()
//    @PutMapping()
//    public void edit(@Validated(SuperEntity.Update.class) @RequestBody TestLeaveSaveReq req) {
//        testLeaveService.update(req);
//    }
//
//    /**
//     * 删除请假
//     *
//     * @param ids 主键串
//     */
//    @SaCheckPermission("workflow:leave:remove")
//    @Log(title = "请假", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public R<Void> remove(@NotEmpty(message = "主键不能为空")
//                          @PathVariable Long[] ids) {
//        return toAjax(testLeaveService.deleteWithValidByIds(List.of(ids)));
//    }
//}
