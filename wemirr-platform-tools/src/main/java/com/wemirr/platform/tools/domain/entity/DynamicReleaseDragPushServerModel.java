package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("gen_dynamic_release_drag_push_server_model")
public class DynamicReleaseDragPushServerModel {

    @TableId
    private Long pushId;

    private Long dragId;


    /**
     * 创建时间（依托数据库功能）
     */
    @TableField(value = "created_time")
    @Parameter(description = "创建时间")
    protected LocalDateTime createdTime;

}
