package com.wemirr.platform.authority.domain.baseinfo.resp;

import com.wemirr.platform.authority.domain.baseinfo.enums.ResourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;

/**
 * @author Levin
 */
@Data
public class ResourcePageResp {

    private Long id;

    @Schema(description = "权限编码")
    private String permission;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String label;

    private Boolean readonly;

    /**
     * 菜单ID
     * #c_auth_menu
     */
    private Long parentId;

    /**
     * '资源类型（1=按钮，0=菜单）'
     */
    private ResourceType type;

    private Integer sequence;

    private String style;

    private String icon;

    private String path;

    private String component;

    private String model;

    private String treePath;
    /**
     * 是否公开菜单
     * 就是无需分配就可以访问的。所有人可见
     */
    private Boolean global;
    private Boolean status;
    @Schema(description = "创建时间")
    private Instant createdTime;

}
