package com.wemirr.platform.tools.controller.resource;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.OS;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.RegionUtils;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.framework.storage.StorageOperation;
import com.wemirr.framework.storage.domain.StorageRequest;
import com.wemirr.framework.storage.domain.StorageResponse;
import com.wemirr.platform.tools.domain.entity.FileEntity;
import com.wemirr.platform.tools.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.apache.http.protocol.HTTP.USER_AGENT;

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
    public StorageResponse upload(@RequestParam MultipartFile file, HttpServletRequest request, String bucket,
                                  @RequestParam(defaultValue = "true") boolean random) {
        if (file == null) {
            throw CheckedException.badRequest("文件内容不能为空");
        }
        final StorageResponse response = storageOperation.upload(StorageRequest.builder().bucket(bucket)
                .inputStream(file.getInputStream()).contentType(file.getContentType()).randomName(random)
                .originName(file.getOriginalFilename()).rule(StorageRequest.PrefixRule.now_date_mouth_day).build());
        saveFileUploadRecord(request, file.getContentType(), response);
        return response;
    }

    private void saveFileUploadRecord(HttpServletRequest request, String contentType, StorageResponse response) {
        String ua = request.getHeader(USER_AGENT);
        String ip = ServletUtil.getClientIP(request);
        String location = RegionUtils.getRegion(ip);
        final UserAgent userAgent = UserAgentUtil.parse(ua);
        final OS os = userAgent.getOs();
        this.fileService.save(FileEntity.builder()
                .contentType(contentType)
                .size(response.getSize())
                .originName(response.getOriginName())
                .targetName(response.getTargetName())
                .mappingPath(response.getMappingPath())
                .fullUrl(response.getFullUrl())
                .bucket(response.getBucket())
                .location(location).ip(ip)
                .engine(userAgent.getEngineVersion())
                .engineVersion(userAgent.getEngine().getName())
                .os(os.getName())
                .build());
    }

    @DeleteMapping("{id}")
    @SysLog(value = "删除文件")
    @Operation(summary = "删除文件")
    public void del(@PathVariable Long id) {
        final FileEntity file = Optional.ofNullable(this.fileService.getById(id)).orElseThrow(() -> CheckedException.notFound("文件不存在"));
        storageOperation.remove(file.getBucket(), file.getTargetName());
        fileService.removeById(id);
    }


}
