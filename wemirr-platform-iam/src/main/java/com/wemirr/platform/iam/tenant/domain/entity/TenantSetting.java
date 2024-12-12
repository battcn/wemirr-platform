package com.wemirr.platform.iam.tenant.domain.entity;

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
 * 租户设置信息
 * </p>
 *
 * @author Levin
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_tenant_setting")
public class TenantSetting extends SuperEntity<Long> {

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "站点地址")
    private String siteUrl;

    @Schema(description = "站点标题")
    private String siteTitle;

    @Schema(description = "站点子标题")
    private String siteSubTitle;

    @Schema(description = "站点LOGO")
    private String siteLogo;

    @Schema(description = "DB-ID")
    private Long dbId;


}
