package com.wemirr.platform.suite.file.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.file.domain.dto.req.StorageConfigEditReq;
import com.wemirr.platform.suite.file.domain.dto.req.StorageConfigSaveReq;
import com.wemirr.platform.suite.file.domain.entity.ResourceStorageConfig;


/**
 * @author xiao1
 * @date 2024-12
 */
public interface ResourceStorageConfigService extends SuperService<ResourceStorageConfig> {

    /**
     * 新增存储配置
     */
    void addStorageConfig(StorageConfigSaveReq storageConfigSaveReq);

    /**
     * 修改配置
     */
    void editStorageConfig(StorageConfigEditReq configForm);

    /**
     * 删除配置
     */
    void deleteStorageConfig(Long id);
    /**
     * 获取配置
     */
    ResourceStorageConfig getStorageConfig();
}
