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
 * 库区表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_storage_area")
@Schema(name = "StorageArea", description = "库区")
@EqualsAndHashCode(callSuper = true)
public class StorageArea extends SuperEntity<Long> {

    @Schema(description = "库区编号")
    private String code;

    @Schema(description = "库区名称")
    private String name;

    @Schema(description = "库区类型")
    private String type;

    @Schema(description = "库区状态")
    private String status;

    @Schema(description = "仓库id")
    private Long warehouseId;

}
