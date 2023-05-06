package com.wemirr.platform.authority.domain.dto;

import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author Levin
 * @since 2019-11-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(name= "UserUpdateAvatarDTO", description = "用户")
public class UserUpdateImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 头像
     */
    @Schema(description = "头像")
    @Length(max = 255, message = "头像长度不能超过255")
    private String image;

}
