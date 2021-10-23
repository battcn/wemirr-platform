package com.wemirr.framework.db.mybatis.auth;

/**
 * @author Levin
 */
public class DataScopeEnvironment {

    private static final ThreadLocal<DataScope> DATA_SCOPE_THREAD_LOCAL = new ThreadLocal<>();

    public static DataScope get() {
        return DATA_SCOPE_THREAD_LOCAL.get();
    }

    public static void set(DataScope scope) {
        DATA_SCOPE_THREAD_LOCAL.set(scope);
    }

    public static void remove() {
        DATA_SCOPE_THREAD_LOCAL.remove();
    }


}
