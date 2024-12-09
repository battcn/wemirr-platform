package com.wemirr.framework.log.diff.service;

import org.springframework.util.StopWatch;

/**
 * 日志性能监视器
 *
 * @author Levin
 */
public interface IDiffLogPerformanceMonitor {

    String MONITOR_NAME = "diff-log-performance";
    String MONITOR_TASK_BEFORE_EXECUTE = "before-execute";
    String MONITOR_TASK_AFTER_EXECUTE = "after-execute";

    /**
     * 打印
     *
     * @param stopWatch StopWatch
     */
    void print(StopWatch stopWatch);
}
