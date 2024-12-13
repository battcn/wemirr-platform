package com.wemirr.platform.suite.file.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.req.ResourceDelReq;
import com.wemirr.platform.suite.file.domain.entity.ResourceInfoRecord;
import com.wemirr.platform.suite.file.service.ResourceService;
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
@RequestMapping("/resource")
@RequiredArgsConstructor
@Tag(name = "资源文件管理", description = "资源文件管理")
public class ResourceController {

    private final ResourceService resourceService;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件")
    public ResourceInfoRecord upload(MultipartFile file) throws IOException {
       return resourceService.upload(file);
    }

    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload-image")
    @Operation(summary = "上传图片", description = "上传图片")
    public ResourceInfoRecord uploadImage(MultipartFile file) throws IOException {
        return resourceService.uploadImage(file);
    }
    /**
     * 删除文件
     */
    @PostMapping("/del")
    @Operation(summary = "删除文件", description = "删除文件")
    public void deleteFile(@RequestBody ResourceDelReq resourceDelReq) throws JsonProcessingException {
        resourceService.deleteFile(resourceDelReq);
    }
    //文件重命名
    @PutMapping("/rename/{id}/{originName}")
    @Operation(summary = "文件重命名", description = "文件重命名")
    public void rename(@PathVariable Long id, @PathVariable String originName) {
         resourceService.rename(id, originName);
    }


    @GetMapping
    @Operation(summary = "文件列表")
    public IPage<ResourceInfoRecord> query(PageRequest pageRequest,
                                           String originalFilename, String fileType, String createdName) {
        return resourceService.page(pageRequest.buildPage(), Wraps.<ResourceInfoRecord>lbQ()
                .eq(ResourceInfoRecord::getFileType, fileType)
                .like(ResourceInfoRecord::getOriginalFilename, originalFilename)
                .like(ResourceInfoRecord::getCreatedName,createdName));
    }

}
