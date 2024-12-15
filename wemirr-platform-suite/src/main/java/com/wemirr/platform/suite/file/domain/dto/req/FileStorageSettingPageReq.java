package com.wemirr.platform.suite.file.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.suite.file.domain.entity.FileStorage;
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
public class FileStorageSettingPageReq extends PageRequest {

    private String platformV;

    private Boolean enableStorage;

}
