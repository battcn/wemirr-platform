package com.battcn.bookstore.consumer.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会员详细资料
 *
 * @author Levin
 * @since 2018/4/9 0009
 */
@ApiModel
@Data
public class MemberDetailDto {

    @ApiModelProperty("会员号")
    private String memberNo;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("会员昵称")
    private String nickName;
    @ApiModelProperty("会员等级")
    private Byte level;
    @ApiModelProperty("账户积分（充值的阅读币）")
    private Integer point;
    @ApiModelProperty("经验值（升级用）")
    private Integer exp;


}
