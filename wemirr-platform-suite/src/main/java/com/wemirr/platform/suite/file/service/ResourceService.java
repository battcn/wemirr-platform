package com.wemirr.platform.suite.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.file.domain.dto.req.ResourceDelReq;
import com.wemirr.platform.suite.file.domain.entity.ResourceInfoRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiao1
 * @date 2024-12
 */
public interface ResourceService extends SuperService<ResourceInfoRecord> {

    /**
     * 文件上传
     * @param file
     * @return
     * @throws JsonProcessingException
     */
    ResourceInfoRecord upload(MultipartFile file) throws JsonProcessingException;

    ResourceInfoRecord uploadImage(MultipartFile file) throws JsonProcessingException;
    /**
     * 文件信息保存
     */
    void saveFile(ResourceInfoRecord fileInfo);
    /**
     * 文件删除
     */
    void deleteFile(ResourceDelReq resourceDelReq) throws JsonProcessingException;

    ResourceInfoRecord rename(Long id, String originName);
    /**
     * 文件存储平台迁移
     */

    /**
     * 文件下载【前端直接通过文件下载】
     */

    /**
     * 文件列表
     */

    /**
     * 文件预览
     */
}
