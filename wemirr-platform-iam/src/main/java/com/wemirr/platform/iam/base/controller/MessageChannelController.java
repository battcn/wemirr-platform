package com.wemirr.platform.iam.base.controller;

import com.wemirr.framework.commons.annotation.log.AccessLog;
import com.wemirr.platform.iam.base.domain.dto.req.MessageChannelSaveReq;
import com.wemirr.platform.iam.base.domain.dto.resp.MessageChannelDetailResp;
import com.wemirr.platform.iam.base.service.MessageChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@Tag(name = "消息通道")
@RequiredArgsConstructor
@RequestMapping("/message-channel")
public class MessageChannelController {

    private final MessageChannelService messageChannelService;

    @PostMapping("/setting")
    @AccessLog(description = "渠道设置")
    @Operation(summary = "渠道设置")
    public void setting(@Validated @RequestBody MessageChannelSaveReq req) {
        messageChannelService.setting(req);
    }

    @GetMapping("/detail")
    @Operation(summary = "渠道详情")
    public MessageChannelDetailResp detail(String type) {
        return messageChannelService.detail(type);
    }


}
