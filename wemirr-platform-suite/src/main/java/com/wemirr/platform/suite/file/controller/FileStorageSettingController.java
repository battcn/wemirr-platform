package com.wemirr.platform.suite.file.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.rep.FileStorageSettingPageResp;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.wemirr.platform.suite.file.domain.dto.req.FileStorageSettingSaveReq;
import com.wemirr.platform.suite.file.domain.entity.FileStorageSetting;
import com.wemirr.platform.suite.file.service.FileStorageSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiao1
 * @date 2024-12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file-storage/setting")
@RequiredArgsConstructor
@Tag(name = "存储配置管理", description = "存储配置管理")
public class FileStorageSettingController {

    private final FileStorageSettingService fileStorageSettingService;
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public IPage<FileStorageSettingPageResp> pageList(FileStorageSettingPageReq req) {
        return fileStorageSettingService.pageList(req);
    }
    /**
     * 删除配置
     */
    @PostMapping
    @AccessLog(description = "添加存储配置")
    @Operation(summary = "新增存储配置", description = "新增存储配置")
    public void add(@RequestBody FileStorageSettingSaveReq req) {
        fileStorageSettingService.addStorageConfig(req);
    }

    @PutMapping("/{id}/modify")
    @AccessLog(description = "编辑存储配置")
    @Operation(summary = "编辑存储配置", description = "编辑存储配置")
    public void edit(@PathVariable Long id, @Validated @RequestBody FileStorageSettingSaveReq req) {
        fileStorageSettingService.modify(id,req);
    }


    @DeleteMapping("/{id}")
    @AccessLog(description = "删除存储配置")
    @Operation(summary = "删除存储配置", description = "删除存储配置")
    public void delete(@PathVariable Long id) {
        fileStorageSettingService.deleteStorageConfig(id);
    }



}
