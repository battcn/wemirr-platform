package com.wemirr.platform.flow.domain.dto.bo;

import com.wemirr.framework.commons.entity.SuperEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


/**
 * 任务操作业务对象，用于描述任务委派、转办、加签等操作的必要参数
 * 包含了用户ID、任务ID、任务相关的消息、以及加签/减签的用户ID
 *
 * @author battcn
 */
@Data
public class TaskOperationBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 委派/转办人的用户ID（必填，准对委派/转办人操作）
     */
    @NotNull(message = "委派/转办人id不能为空", groups = {SuperEntity.Save.class})
    private String userId;

    /**
     * 加签/减签人的用户ID列表（必填，针对加签/减签操作）
     */
    @NotNull(message = "加签/减签id不能为空", groups = {SuperEntity.Update.class})
    private List<String> userIds;

    /**
     * 任务ID（必填）
     */
    @NotNull(message = "任务id不能为空")
    private Long taskId;

    /**
     * 意见或备注信息（可选）
     */
    private String message;

}
