package com.wemirr.platform.authority.feign.domain.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Levin
 */
@Data
public class UserInfoResp {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "昵称")
    private String nickName;


    @Schema(description = "头像")
    private String avatar;


    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;


    @Schema(description = "生日")
    private LocalDate birthday;

}
