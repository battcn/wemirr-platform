package com.wemirr.platform.suite.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.file.domain.dto.rep.FileStorageSettingPageResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingSaveReq;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;


/**
 * @author xiao1
 * @date 2024-12
 */
public interface FileStorageSettingService extends SuperService<FileStorageSetting> {

    /**
     * 新增存储配置
     */
    void addStorageConfig(FileStorageSettingSaveReq fileStorageSettingSaveReq);

    /**
     * 修改配置
     */
//    void editStorageConfig(StorageConfigEditReq configForm);

    /**
     * 删除配置
     */
    void deleteStorageConfig(Long id);
    /**
     * 获取配置
     */
    FileStorageSetting getStorageConfig();

    void modify(Long id, FileStorageSettingSaveReq req);

    IPage<FileStorageSettingPageResp> pageList(FileStorageSettingPageReq req);
}
