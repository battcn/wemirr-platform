package com.wemirr.platform.tools.service;

import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.platform.tools.domain.entity.FileEntity;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Levin
 */
public interface FileService extends SuperService<FileEntity> {


    /**
     * 文件上传
     *
     * @param storage storage
     * @param request request
     * @return 上传结果
     */
    StorageResponse upload(StorageRequest storage, HttpServletRequest request);

}
