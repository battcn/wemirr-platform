package com.wemirr.platform.wms.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.platform.wms.inbound.domain.enums.WeightMode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 工作台表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_workbench")
@Schema(name = "Workbench", description = "仓库")
@EqualsAndHashCode(callSuper = true)
public class Workbench extends SuperEntity<Long> {

    @Schema(description = "编号")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "类别")
    private String category;

    @Schema(description = "复核模式")
    private String reviewMode;

    @Schema(description = "任务类型")
    private String taskType;

    @Schema(description = "称重模式")
    private WeightMode weightMode;

    @Schema(description = "MAC地址")
    private String macAddress;

}
