package com.wemirr.platform.suite.file.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.file.domain.dto.req.StorageConfigEditReq;
import com.wemirr.platform.suite.file.domain.dto.req.StorageConfigSaveReq;
import com.wemirr.platform.suite.file.domain.entity.ResourceStorageConfig;
import com.wemirr.platform.suite.file.service.ResourceStorageConfigService;
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
@RequestMapping("/storage/config")
@RequiredArgsConstructor
@Tag(name = "存储配置管理", description = "存储配置管理")
public class ResourceStorageConfigController {

    private final ResourceStorageConfigService fileStorageConfigService;

    /**
     * 新增配置
     */
    public void addStorageConfig() {}
    /**
     * 删除配置
     */
    @PostMapping
    @Operation(summary = "新增存储配置", description = "新增存储配置")
    public void add(@RequestBody StorageConfigSaveReq storageConfigSaveReq) {
        fileStorageConfigService.addStorageConfig(storageConfigSaveReq);
    }
    /**
     * 更新配置
     */
    @PutMapping
    @Operation(summary = "更新存储配置", description = "更新存储配置")
    public void edit(@RequestBody StorageConfigEditReq form) {
        fileStorageConfigService.editStorageConfig(form);
    }

    /**
     * 删除配置
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除存储配置", description = "删除存储配置")
    public void delete(@PathVariable Long id) {
        fileStorageConfigService.deleteStorageConfig(id);
    }

    /**
     * 查询配置
     */
    @GetMapping
    @Parameters({
            @Parameter(description = "配置平台", name = "platform", in = ParameterIn.QUERY),
            @Parameter(description = "状态", name = "enableStorage", in = ParameterIn.QUERY)
    })
    @Operation(summary = "配置列表")
    public IPage<ResourceStorageConfig> query(PageRequest pageRequest,
                                              String platformV, Boolean enableStorage) {
        return fileStorageConfigService.page(pageRequest.buildPage(), Wraps.<ResourceStorageConfig>lbQ()
                .like(ResourceStorageConfig::getPlatformV, platformV)
                .eq(ResourceStorageConfig::getEnableStorage, enableStorage));
    }

}
