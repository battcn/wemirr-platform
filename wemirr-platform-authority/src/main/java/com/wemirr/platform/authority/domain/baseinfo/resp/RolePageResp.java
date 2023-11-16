package com.wemirr.platform.authority.domain.baseinfo.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wemirr.framework.commons.security.DataScopeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class RolePageResp {


    private Long id;

    private Long tenantId;

    private String name;

    private String code;

    @TableField("`super`")
    private Boolean superRole;

    private String description;

    private Boolean readonly;

    private Boolean locked;
    /**
     * 数据权限类型
     * #DataScopeType
     */
    private DataScopeType scopeType;

    @Schema(description = "创建时间")
    private Instant createdTime;

}
