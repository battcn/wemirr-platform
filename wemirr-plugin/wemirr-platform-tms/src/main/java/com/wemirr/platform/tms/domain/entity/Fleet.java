package com.wemirr.platform.tms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@TableName(value = "tms_fleet")
@Schema(name = "Fleet")
public class Fleet extends SuperEntity<Long> {


    @Schema(description = "车队名称")
    private String fleetName;

    @Schema(description = "队长姓名")
    private String leaderRealName;

    @Schema(description = "队长手机号")
    private String leaderMobile;

    @Schema(description = "队长用户ID")
    private String leaderUserId;

    @Schema(description = "启用/禁用")
    private Boolean enabled;

    @Schema(description = "备注")
    private String remark;
}
