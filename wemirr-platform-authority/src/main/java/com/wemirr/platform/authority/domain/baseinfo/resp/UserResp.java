package com.wemirr.platform.authority.domain.baseinfo.resp;

import com.wemirr.platform.authority.domain.baseinfo.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
public class UserResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "身份证")
    private String idCard;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "性别")
    private Sex sex;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "是否只读")
    private Boolean readonly;

    @Schema(description = "状态")
    private Boolean status;

    @Schema(description = "民族")
    private String nation;

    @Schema(description = "学历")
    private String education;

    @Schema(description = "生日")
    private LocalDate birthday;

    @Schema(description = "机构ID")
    private Long orgId;

    @Schema(description = "岗位ID")
    private Long stationId;

    @Schema(description = "职位状态")
    private String positionStatus;

    @Schema(description = "创建时间")
    protected Instant createdTime;


}
