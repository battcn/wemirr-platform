package com.wemirr.platform.suite.file.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiao1
 * @since 2024-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "FileStorageSettingPageReq")
public class FileStorageSettingPageReq extends PageRequest {


    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态")
    private Boolean status;

}
