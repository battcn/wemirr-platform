package com.wemirr.platform.tools.controller.resource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.security.client.annotation.IgnoreAuthorize;
import com.wemirr.framework.storage.StorageOperation;
import com.wemirr.framework.storage.domain.DownloadResponse;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.platform.tools.domain.entity.FileEntity;
import com.wemirr.platform.tools.domain.req.BatchKey;
import com.wemirr.platform.tools.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "文件管理", description = "文件管理")
public class FileController {

    private final FileService fileService;
    private final TenantEnvironment tenantEnvironment;
    private final StorageOperation storageOperation;


    @GetMapping("/token")
    @Parameter(description = "文件名", name = "originName", in = ParameterIn.QUERY)
    @Operation(summary = "上传Token获取 - [Levin] - [DONE]")
    public String getToken(String key, @RequestParam(defaultValue = "true") boolean random) {
        return storageOperation.token(key, random);
    }

    @GetMapping
    @Parameters({
            @Parameter(description = "原始文件名", name = "originName", in = ParameterIn.QUERY),
            @Parameter(description = "文件类型", name = "fileType", in = ParameterIn.QUERY),
    })
    @Operation(summary = "文件列表 - [Levin] - [DONE]")
    public IPage<FileEntity> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                                   @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                                   String originName, String fileType) {
        return fileService.page(new Page<>(current, size), Wraps.<FileEntity>lbQ()
                .eq(FileEntity::getContentType, fileType).like(FileEntity::getOriginName, originName));
    }

    @SneakyThrows
    @Parameters({
            @Parameter(name = "bucket", description = "存储桶（不建议赋值）", in = ParameterIn.QUERY),
            @Parameter(name = "random", description = "随机文件名称", in = ParameterIn.QUERY),
    })
    @PostMapping("/upload")
    @Operation(summary = "文件上传 - [Levin] - [DONE]")
    public StorageResponse upload(@RequestParam MultipartFile file, @RequestParam(required = false) String bucket, HttpServletRequest request,
                                  @RequestParam(defaultValue = "true") boolean random) {
        if (file == null) {
            throw CheckedException.badRequest("文件内容不能为空");
        }
        final StorageRequest storage = StorageRequest.builder().bucket(bucket).inputStream(file.getInputStream())
                .contentType(file.getContentType()).randomName(random)
                .originName(file.getOriginalFilename())
                .tenantId(tenantEnvironment.tenantId()).userId(tenantEnvironment.userId())
                .rule(StorageRequest.PrefixRule.tenant_now_date_mouth_day).build();
        return fileService.upload(storage, request);
    }


    @IgnoreAuthorize
    @Parameters({@Parameter(name = "id", description = "文件ID", in = ParameterIn.PATH),})
    @GetMapping("/{id}/download")
    @Operation(summary = "文件下载 - [Levin] - [DONE]")
    public void download(@PathVariable String id, HttpServletResponse response) {
        final FileEntity file = this.fileService.getById(id);
        if (file == null) {
            return;
        }
        final DownloadResponse download = storageOperation.download(file.getTargetName());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getOriginName());
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(IoUtil.readBytes(download.getInputStream()));
        } catch (Exception e) {
            log.error("文件预览失败", e);
        }
    }


    @IgnoreAuthorize
    @Parameters({@Parameter(name = "id", description = "文件KEY", in = ParameterIn.PATH),})
    @GetMapping("/{id}/preview")
    @Operation(summary = "文件预览 - [Levin] - [DONE]")
    public ResponseEntity<Resource> preview(@PathVariable String id) {
        final FileEntity file = this.fileService.getById(id);
        if (file == null) {
            return ResponseEntity.ok(null);
        }
        final DownloadResponse download = storageOperation.download(file.getTargetName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, file.getContentType());
        return new ResponseEntity<>(new InputStreamResource(download.getInputStream()), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除文件")
    @Operation(summary = "删除文件")
    public void del(@PathVariable Long id) {
        final FileEntity file = Optional.ofNullable(this.fileService.getById(id)).orElseThrow(() -> CheckedException.notFound("文件不存在"));
        storageOperation.remove(file.getBucket(), file.getTargetName());
        fileService.removeById(id);
    }

    @PostMapping("/ids_query")
    @Operation(summary = "通过ID查询文件信息 - [Aaron] - [DONE]")
    public List<FileEntity> batchQueryByIds(@RequestBody BatchKey<String> param) {
        if (Objects.isNull(param) || CollUtil.isEmpty(param.getIds())) {
            return Lists.newArrayList();
        }
        return fileService.list(Wraps.<FileEntity>lbQ().in(FileEntity::getId, param.getIds()));
    }


}
