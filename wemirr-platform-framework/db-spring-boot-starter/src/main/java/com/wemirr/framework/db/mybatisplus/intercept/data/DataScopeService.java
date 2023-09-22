package com.wemirr.framework.db.mybatisplus.intercept.data;

/**
 * @author Levin
 */
public interface DataScopeService {

    /**
     * 根据用户编号获取数据权限
     *
     * @param userId 用户ID
     * @return 查询结果
     */
    DataPermission getDataScopeById(Long userId);
}
