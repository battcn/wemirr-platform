package com.wemirr.platform.wms.basic.domain.req;

import com.wemirr.platform.wms.inbound.domain.enums.ContainerStatus;
import com.wemirr.platform.wms.inbound.domain.enums.ContainerTaskType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author ddCat, Levin
 */
@Data
@SuperBuilder
@Schema(name = "ContainerOccupyReq", description = "容器占用释放请求对象")
public class ContainerOccupyReleaseReq {

    @Schema(description = "容器ID")
    private Long containerId;

    @Schema(description = "状态(占用、释放、异常)")
    private ContainerStatus status;

    @Schema(description = "占用任务类型")
    private ContainerTaskType occupationTaskType;

    @Schema(description = "单据id")
    private Long docId;

    @Schema(description = "单据行id")
    private Long docItemId;

}
