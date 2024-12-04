package com.wemirr.platform.iam.system.domain.dto.req;

import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Levin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "MessageNotifyPageReq")
public class MessageNotifyPageReq extends PageRequest {

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "消息类型")
    private String type;

    @Schema(description = "用户ID")
    private Long userId;

}
