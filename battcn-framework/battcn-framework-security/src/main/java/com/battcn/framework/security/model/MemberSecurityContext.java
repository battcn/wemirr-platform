package com.battcn.framework.security.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Levin
 * @since 2017-05-25
 */
@Data
public class MemberSecurityContext {

    @NotBlank(message = "账号不能为空")
    @Length(min = 6, max = 20, message = "账号长度必须在{min} - {max} 之间")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须在{min} - {max} 之间")
    private String password;

}
