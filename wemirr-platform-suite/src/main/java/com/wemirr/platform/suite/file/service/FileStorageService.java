package com.wemirr.platform.suite.file.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.file.domain.dto.rep.FileStoragePageResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageDelReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStoragePageReq;
import com.wemirr.platform.suite.file.domain.entity.FileStorage;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiao1
 * @date 2024-12
 */
public interface FileStorageService extends SuperService<FileStorage> {

    /**
     * 文件上传
     * @param file
     * @return
     * @throws JsonProcessingException
     */
    FileStorage upload(MultipartFile file) throws JsonProcessingException;

    FileStorage uploadImage(MultipartFile file) throws JsonProcessingException;
    /**
     * 文件信息保存
     */
    void saveFile(FileStorage fileInfo);
    /**
     * 文件删除
     */
    void deleteFile(FileStorageDelReq fileStorageDelReq) throws JsonProcessingException;

    void rename(Long id, String originName);

    IPage<FileStoragePageResp> pageList(FileStoragePageReq req);

}
