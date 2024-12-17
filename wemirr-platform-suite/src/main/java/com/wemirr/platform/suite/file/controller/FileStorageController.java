package com.wemirr.platform.suite.file.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.suite.file.domain.dto.rep.FileStoragePageResp;
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

/**
 * @author xiao1
 * @since 2024-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file-storage")
@RequiredArgsConstructor
@Tag(name = "文件存储", description = "文件存储")
public class FileStorageController {

    private final FileStorageService fileStorageService;


    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件")
    @AccessLog(module = "文件存储", description = "上传文件")
    public FileStorage upload(@RequestParam("file") MultipartFile file) {
        return fileStorageService.upload(file);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<FileStoragePageResp> pageList(FileStoragePageReq req) {
        return fileStorageService.pageList(req);
    }


    @PostMapping("/{id}")
    @Operation(summary = "删除文件", description = "删除文件")
    @AccessLog(module = "文件存储", description = "删除文件")
    public void delete(@PathVariable Long id) {
        fileStorageService.delete(id);
    }

    @PutMapping("/rename/{id}/{originName}")
    @Operation(summary = "文件重命名", description = "文件重命名")
    @AccessLog(module = "文件存储", description = "文件重命名")
    public void rename(@PathVariable Long id, @PathVariable String originName) {
        fileStorageService.rename(id, originName);
    }


    @PostMapping("/upload-image")
    @Operation(summary = "上传图片", description = "上传图片")
    public FileStorage uploadImage(@RequestParam("file") MultipartFile file) {
        return fileStorageService.uploadImage(file);
    }


}
