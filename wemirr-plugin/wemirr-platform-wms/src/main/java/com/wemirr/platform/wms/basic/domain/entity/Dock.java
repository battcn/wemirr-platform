package com.wemirr.platform.wms.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 月台管理表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_dock")
@Schema(name = "Dock", description = "月台")
@EqualsAndHashCode(callSuper = true)
public class Dock extends SuperEntity<Long> {

    @Schema(description = "月台编号")
    private String code;

    @Schema(description = "月台名称")
    private String name;

    @Schema(description = "月台规格")
    private String spec;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态")
    private String status;
}
