package com.wemirr.platform.authority.domain.req;

import com.wemirr.platform.authority.domain.enums.ReceiverType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author Levin
 */
@Data
public class SiteMessageReq {


    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "类型不能为空")
    private String level;

    @NotBlank(message = "消息内容不能为空")
    private String content;

    private String description;

    @NotNull(message = "接收类型不能为空")
    private ReceiverType type;
    /**
     * 接收的Id
     */
    @NotEmpty(message = "接收者不能为空")
    private List<Long> receiver;


}
