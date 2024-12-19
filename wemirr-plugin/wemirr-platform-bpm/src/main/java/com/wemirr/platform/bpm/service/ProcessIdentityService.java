package com.wemirr.platform.bpm.service;

/**
 * Camunda流程 身份信息维护业务接口
 *
 * @author Levin
 */
public interface ProcessIdentityService {

    /**
     * 创建租户
     *
     * @param tenantId   tenantId
     * @param tenantName tenantName
     */
    void createTenant(Long tenantId, String tenantName);


    /**
     * 设置用户与权限
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     */
    void setAuthentication(Long userId, Long tenantId);

}
