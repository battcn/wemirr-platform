package com.wemirr.platform.authority.domain.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wemirr.framework.db.mybatisplus.handler.type.JsonTypeHandler;
import com.wemirr.framework.log.diff.domain.DiffLogInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("common_diff_log")
public class DiffLogEntity extends DiffLogInfo {

    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    private Long id;

    @TableField(typeHandler = JsonTypeHandler.class)
    @Schema(description = "日志的代码信息")
    protected Map<Object, Object> variables;
}
