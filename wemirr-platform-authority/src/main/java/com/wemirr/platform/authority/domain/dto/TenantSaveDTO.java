package com.wemirr.platform.authority.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Levin
 */
@Data
public class TenantSaveDTO {

    @NotBlank(message = "租户编码不能为空")
    @Length(min = 2,max = 6,message = "租户编码长度 {min} - {max} 之间")
    private String code;
    @NotBlank(message = "租户名称不能为空")
    @Length(min = 2,max = 30,message = "租户名称长度 {min} - {max} 之间")
    private String name;
    @NotNull(message = "租户类型不能为空")
    private Integer type;
    @NotNull(message = "认证状态不能为空")
    private Integer status;
    @Length(min = 2,max = 8,message = "租户简称长度 {min} - {max} 之间")
    private String alias;
    @NotNull(message = "使用状态不能为空")
    private Boolean locked;
    @Length(min = 2,max = 256,message = "租户LOGO长度 {min} - {max} 之间")
    private String logo;
    @Length(min = 2,max = 30,message = "租户邮箱长度 {min} - {max} 之间")
    private String email;
    @Length(min = 2,max = 30,message = "联系人长度 {min} - {max} 之间")
    private String contactPerson;
    @Length(min = 2,max = 30,message = "联系方式长度 {min} - {max} 之间")
    private String contactPhone;
    private String industry;

    private Long provinceId;
    private String provinceName;
    private Long cityId;
    private String cityName;
    private Long districtId;
    private String districtName;

    @Length(min = 2,max = 100,message = "地址长度 {min} - {max} 之间")
    private String address;
    @Length(min = 2,max = 100,message = "统一信用代码长度为 {min} - {max} 之间")
    private String creditCode;
    @Length(min = 2,max = 30,message = "法人长度为 {min} - {max} 之间")
    private String legalPersonName;
    @Length(min = 2,max = 100,message = "租户站点长度为 {min} - {max} 之间")
    private String webSite;
    @Length(min = 2,max = 256,message = "租户秒长度为 {min} - {max} 之间")
    private String description;
}
