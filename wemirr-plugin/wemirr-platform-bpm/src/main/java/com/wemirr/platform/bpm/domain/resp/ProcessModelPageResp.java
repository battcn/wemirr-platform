
package com.wemirr.platform.bpm.domain.resp;

import com.wemirr.platform.bpm.feign.domain.enums.ProcessModelStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * 流程模型管理分页查询Response
 *
 * @author Levin
 */
@Data
@Schema(name = "ProcessModelPageResp")
public class ProcessModelPageResp {

    @Schema(description = "模型ID")
    private String id;

    @Schema(description = "模型名称")
    private String diagramName;

    @Schema(description = "类别编码")
    private String categoryCode;

    @Schema(description = "类别名称")
    private String categoryName;

    @Schema(description = "模型状态:0-未部署,1-已经部署,2-新版本待部署")
    private ProcessModelStatus status;

    @Schema(description = "是否pool模型,0-不是,1-是")
    private Integer havePool;

    @Schema(description = "流程定义key,多个逗号隔开")
    private String definitionKey;

    @Schema(description = "部署名称")
    private String deployName;

    @Schema(description = "部署时间", example = "2020-12-21 12:22")
    protected Instant deployTime;

    @Schema(description = "部署ID")
    private String deployId;

    @Schema(description = "当前模型版本")
    private Integer version;

    @Schema(description = "创建人姓名")
    private String createdName;

    @Schema(description = "创建时间")
    private Instant createdTime;
}
