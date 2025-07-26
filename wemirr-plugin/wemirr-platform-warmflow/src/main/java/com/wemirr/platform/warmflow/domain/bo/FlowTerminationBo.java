package com.wemirr.platform.warmflow.domain.bo;

import com.wemirr.framework.commons.entity.SuperEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 终止任务请求对象
 *
 * @author may
 */
@Data
public class FlowTerminationBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @NotNull(message = "任务id为空", groups = SuperEntity.Save.class)
    private Long taskId;

    /**
     * 审批意见
     */
    private String comment;
}
