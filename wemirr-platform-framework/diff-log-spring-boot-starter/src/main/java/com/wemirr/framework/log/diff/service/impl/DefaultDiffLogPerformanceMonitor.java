package com.wemirr.framework.log.diff.service.impl;

import com.wemirr.framework.log.diff.service.IDiffLogPerformanceMonitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author Levin
 */
@Slf4j
public class DefaultDiffLogPerformanceMonitor implements IDiffLogPerformanceMonitor {

    @Override
    public void print(StopWatch stopWatch) {
        log.debug("diff log performance {}", stopWatch.prettyPrint());
    }
}
