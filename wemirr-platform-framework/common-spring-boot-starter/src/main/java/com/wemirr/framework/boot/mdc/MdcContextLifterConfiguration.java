package com.wemirr.framework.boot.mdc;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.reactivestreams.Subscription;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;
import reactor.util.context.Context;

/**
 * @author Levin
 */
@Configuration
public class MdcContextLifterConfiguration {

    private static final String MDC_CONTEXT_REACTOR_KEY = MdcContextLifterConfiguration.class.getName();
    /**
     * // 必须与 Log pattern 和 Filter 中的 key 一致
     */
    private static final String TRACE_ID_KEY = "n-d-trace-id";

    @PostConstruct
    public void contextOperatorHook() {
        // 注册全局 Hook，拦截所有 Reactor 操作
        Hooks.onEachOperator(MDC_CONTEXT_REACTOR_KEY, Operators.lift((scannable, coreSubscriber) -> new MdcContextLifter<>(coreSubscriber)));
    }

    @PreDestroy
    public void cleanupHook() {
        Hooks.resetOnEachOperator(MDC_CONTEXT_REACTOR_KEY);
    }

    /**
     * 核心 Lifter 类：负责在 onNext, onError 等信号触发时同步 MDC
     */
    static class MdcContextLifter<T> implements CoreSubscriber<T> {

        private final CoreSubscriber<T> coreSubscriber;

        public MdcContextLifter(CoreSubscriber<T> coreSubscriber) {
            this.coreSubscriber = coreSubscriber;
        }

        @Override
        public void onSubscribe(Subscription s) {
            copyToMdc(coreSubscriber.currentContext());
            try {
                coreSubscriber.onSubscribe(s);
            } finally {
                clearMdc();
            }
        }

        @Override
        public void onNext(T t) {
            copyToMdc(coreSubscriber.currentContext());
            try {
                coreSubscriber.onNext(t);
            } finally {
                clearMdc();
            }
        }

        @Override
        public void onError(Throwable t) {
            copyToMdc(coreSubscriber.currentContext());
            try {
                coreSubscriber.onError(t);
            } finally {
                clearMdc();
            }
        }

        @Override
        public void onComplete() {
            copyToMdc(coreSubscriber.currentContext());
            try {
                coreSubscriber.onComplete();
            } finally {
                clearMdc();
            }
        }

        @Override
        public Context currentContext() {
            return coreSubscriber.currentContext();
        }

        /**
         * 将 Reactor Context 中的 TraceId 放入 MDC
         */
        private void copyToMdc(Context context) {
            if (context != null && !context.isEmpty() && context.hasKey(TRACE_ID_KEY)) {
                MDC.put(TRACE_ID_KEY, context.get(TRACE_ID_KEY));
            }
        }

        /**
         * 清理 MDC，防止线程池污染
         */
        private void clearMdc() {
            MDC.remove(TRACE_ID_KEY);
            // 或者 MDC.clear(); 但要注意是否会误删其他框架的 MDC
        }
    }
}