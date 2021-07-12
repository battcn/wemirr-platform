package com.wemirr.platform.authority.domain.dto;

import com.wemirr.platform.authority.domain.enums.ReceiverType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class StationMessagePublishReq {


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
    private List<Long> receiver;


}
