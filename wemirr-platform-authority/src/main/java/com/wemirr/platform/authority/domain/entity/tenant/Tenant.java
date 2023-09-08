package com.wemirr.platform.authority.domain.entity.tenant;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NEVER;

/**
 * <p>
 * 租户信息
 * </p>
 *
 * @author Levin
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_tenant")
public class Tenant extends SuperEntity<Long> {

    private String code;
    private String name;
    private Integer type;
    private Integer status;
    private String alias;
    private Boolean locked;

    private String logo;
    private String email;
    private String contactPerson;
    private String contactPhone;
    private String industry;

    @TableField(updateStrategy = NEVER)
    private Long provinceId;
    @TableField(updateStrategy = NEVER)
    private String provinceName;
    @TableField(updateStrategy = NEVER)
    private Long cityId;
    @TableField(updateStrategy = NEVER)
    private String cityName;
    @TableField(updateStrategy = NEVER)
    private Long districtId;
    @TableField(updateStrategy = NEVER)
    private String districtName;

    private String address;

    private String creditCode;
    private String legalPersonName;
    private String webSite;
    private String description;


}
