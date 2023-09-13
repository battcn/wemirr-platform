package com.wemirr.platform.tools.domain.req.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Levin
 */
@Data
public class FileSaveReq {

    @NotBlank(message = "文件名称不能为空")
    private String fileName;

    @NotBlank(message = "映射地址不能为空")
    private String mappingPath;
    /**
     * 描述
     */
    @Schema(description = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    private String description;

}
