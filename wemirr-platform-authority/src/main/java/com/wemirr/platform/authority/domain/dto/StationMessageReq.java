package com.wemirr.platform.authority.domain.dto;

import com.wemirr.platform.authority.domain.enums.ReceiverType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author Levin
 */
@Data
public class StationMessageReq {


    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "类型不能为空")
    private String type;

    @NotBlank(message = "消息内容不能为空")
    private String content;

    @NotNull(message = "接收类型不能为空")
    private ReceiverType receiverType;
    /**
     * 接收的Id
     */
    private List<Long> receiver;


}
