package com.wemirr.platform.wms.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.wms.inbound.domain.enums.ContainerStatus;
import com.wemirr.platform.wms.inbound.domain.enums.ContainerTaskType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * <p>
 * 容器操作日志
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_container_log")
@Schema(name = "ContainerLog", description = "容器操作日志")
@EqualsAndHashCode(callSuper = true)
public class ContainerLog extends SuperEntity<Long> {

    @Schema(description = "容器id")
    private Long containerId;

    @Schema(description = "占用任务类型")
    private ContainerTaskType occupationTaskType;

    @Schema(description = "任务号")
    private String taskNo;

    @Schema(description = "单据id")
    private Long docId;

    @Schema(description = "单据号")
    private String docNum;

    @Schema(description = "单据行id")
    private Long docItemId;

    @Schema(description = "单据行号")
    private String docItemNum;

    @Schema(description = "状态(占用、释放、异常)")
    private ContainerStatus status;

    @Schema(description = "结束时间")
    private Instant endTime;

    @Schema(description = "错误日志")
    private String errorMsg;

}
