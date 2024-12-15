package com.wemirr.platform.suite.file.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiao1
 * @date 2024-12
 * 文件删除请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FileDelReq", description = "文件删除请求")
public class FileStorageDelReq {
    /**
     * 文件id
     */
    @Schema(description = "文件id")
    private Long id;
    /**
     * 文件路径
     */
    @Schema(description = "文件url")
    private String fileUrl;
    /**
     * 存储平台
     */
    @Schema(description = "存储平台")
    private String platform;
}
