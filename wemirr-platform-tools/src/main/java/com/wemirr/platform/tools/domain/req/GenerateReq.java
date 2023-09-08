package com.wemirr.platform.tools.domain.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateReq {

    /**
     * 开发人员
     */
    @NotBlank(message = "作者不能为空")
    private String author;
    /**
     * 开启 swagger2 模式
     */
    private boolean swagger2 = false;
    /**
     * 表名
     */
    @NotBlank(message = "表名不能为空")
    private String tableName;
    @NotBlank(message = "包名不能为空")
    private String parentPackage;
    @NotBlank(message = "模块名不能为空")
    private String moduleName;
    private String tablePrefix;
    private String apiUrlPrefix;
    /**
     * 逻辑删除属性名称
     */
    private String logicDeleteField;

    private String rootDir;
}
