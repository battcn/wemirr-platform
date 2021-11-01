//package com.wemirr.platform.tools.service.handler;
//
//import com.xxl.job.core.context.XxlJobHelper;
//import com.xxl.job.core.handler.annotation.XxlJob;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * DOC： https://www.xuxueli.com/xxl-job/
// * * 开发步骤：
// * *      1、任务开发：在Spring Bean实例中，开发Job方法；
// * *      2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
// * *      3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
// * *      4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
// *
// * @author Levin
// */
//@Slf4j
//@Component
//public class DemoJobHandler {
//
//    /**
//     * 1、简单任务示例（Bean模式）
//     */
//    @XxlJob("demoJobHandler")
//    public void demoJobHandler() throws Exception {
//        XxlJobHelper.log("================= 内置日志开始 =================");
//        for (int i = 0; i < 5; i++) {
//            XxlJobHelper.log("我是tools程序输出的日志哦 beat at:" + i);
//            TimeUnit.SECONDS.sleep(2);
//        }
//        XxlJobHelper.log("================= 内置日志结束 =================");
//        // ReturnT.SUCCESS 也可以
//    }
//
//    /**
//     * 5、生命周期任务示例：任务初始化与销毁时，支持自定义相关逻辑；
//     */
//    @XxlJob(value = "demoJobHandler2", init = "init", destroy = "destroy")
//    public void demoJobHandler2() {
//        XxlJobHelper.log("XXL-JOB, Hello World.");
//    }
//
//    public void init() {
//        log.info("init");
//    }
//
//    public void destroy() {
//        log.info("destory");
//    }
//
//
//}
