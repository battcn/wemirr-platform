package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.*;

/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("gen_dynamic_release_drag_push_server")
public class DynamicReleaseDragPushServer extends SuperEntity<Long> {

    @TableField("`method`")
    private String method;
    @TableField("`url`")
    private String url;
    private Boolean locked;
    @TableField("`secret`")
    private String secret;
    @TableField("`opt`")
    private String opt;
}
