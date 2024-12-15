package com.wemirr.platform.suite.file.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.rep.FileStoragePageResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageDelReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStoragePageReq;
import com.wemirr.platform.suite.file.domain.entity.FileStorage;
import com.wemirr.platform.suite.file.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xiao1
 * @date 2024-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file-storage")
@RequiredArgsConstructor
@Tag(name = "资源文件管理", description = "资源文件管理")
public class FileStorageController {

    private final FileStorageService fileStorageService;


    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件")
    @AccessLog(description = "上传文件")
    public FileStorage upload(MultipartFile file) throws IOException {
       return fileStorageService.upload(file);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<FileStoragePageResp> pageList(FileStoragePageReq req) {
        return fileStorageService.pageList(req);
    }


    @PostMapping("/del")
    @Operation(summary = "删除文件", description = "删除文件")
    @AccessLog(description = "删除文件")
    public void deleteFile(@RequestBody FileStorageDelReq fileStorageDelReq) throws JsonProcessingException {
        fileStorageService.deleteFile(fileStorageDelReq);
    }

    @PutMapping("/rename/{id}/{originName}")
    @Operation(summary = "文件重命名", description = "文件重命名")
    @AccessLog(description = "文件重命名")
    public void rename(@PathVariable Long id, @PathVariable String originName) {
        fileStorageService.rename(id, originName);
    }


    @PostMapping("/upload-image")
    @Operation(summary = "上传图片", description = "上传图片")
    public FileStorage uploadImage(MultipartFile file) throws IOException {
        return fileStorageService.uploadImage(file);
    }




}
