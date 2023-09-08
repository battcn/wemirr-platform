package com.wemirr.platform.authority.domain.dto;

import com.wemirr.framework.db.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-07-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(name = "StationPageDTO", description = "岗位")
public class StationPageDTO extends PageRequest implements Serializable {

    

    /**
     * 名称
     */
    @Schema(description = "名称")
    @Length(max = 255, message = "名称长度不能超过255")
    private String name;
    /**
     * 组织ID
     * #c_core_org
     */
    @Schema(description = "组织ID")
    private Long orgId;
    @Schema(description = "类型")
    private Integer type;
    @Schema(description = "状态")
    private Boolean status;

}
