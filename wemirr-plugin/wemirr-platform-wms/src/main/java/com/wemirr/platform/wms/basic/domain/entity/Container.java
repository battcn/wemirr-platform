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
 * 容器表
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_container")
@Schema(name = "Container", description = "容器")
@EqualsAndHashCode(callSuper = true)
public class Container extends SuperEntity<Long> {

    @Schema(description = "容器编码")
    private String code;

    @Schema(description = "容器名称")
    private String name;

    @Schema(description = "容器规格ID")
    private Long specId;

    @Schema(description = "容器用途（入库、拣货、分拣、集货、存储）")
    private String remark;

}
