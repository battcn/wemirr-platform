package com.wemirr.framework.db.mybatisplus.intercept.data;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author Levin
 */
public class DataPermissionContextHolder {


    /**
     * 不建议使用 List,防止方法的嵌套调用
     */
    private static final ThreadLocal<DataPermission> DATA_PERMISSIONS = TransmittableThreadLocal.withInitial(DataPermission::new);

    /**
     * 获得当前的 DataPermission
     *
     * @return DataPermission
     */
    public static DataPermission get() {
        return DATA_PERMISSIONS.get();
    }

    /**
     * 入栈 DataPermission
     *
     * @param dataPermission DataPermission
     */
    public static void set(DataPermission dataPermission) {
        DATA_PERMISSIONS.set(dataPermission);
    }

    /**
     * 出栈 DataPermission
     */
    public static void remove() {
        DATA_PERMISSIONS.remove();
    }


    /**
     * 清空上下文
     */
    public static void clear() {
        DATA_PERMISSIONS.remove();
    }


}
