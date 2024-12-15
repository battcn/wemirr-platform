package com.wemirr.platform.suite.file.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiao1
 * @date 2024-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "FileStorageSettingPageReq")
public class FileStoragePageReq extends PageRequest {
    private String FileType;

    private String originalFilename;

    private String createdName;


}
