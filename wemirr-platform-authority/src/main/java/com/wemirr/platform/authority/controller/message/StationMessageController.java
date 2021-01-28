package com.wemirr.platform.authority.controller.message;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.database.datasource.TenantEnvironment;
import com.wemirr.framework.database.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.dto.StationMessageReq;
import com.wemirr.platform.authority.domain.entity.StationMessage;
import com.wemirr.platform.authority.service.StationMessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@Tag(name = "站内消息")
@RequiredArgsConstructor
public class StationMessageController {

    private final TenantEnvironment tenantEnvironment;
    private final StationMessageService stationMessageService;

    @GetMapping("/station_messages/my")
    public Result<Page<StationMessage>> myMessage(PageRequest request) {
        final Page<StationMessage> page = stationMessageService.page(request.buildPage(), Wraps.<StationMessage>lbQ()
                .eq(StationMessage::getReceiveId, tenantEnvironment.userId()));
        return Result.success(page);
    }


    @PostMapping("/station_messages")
    public Result<ResponseEntity<Void>> push(@Validated @RequestBody StationMessageReq req) {
        log.debug("[参数内容] - {}", JSON.toJSONString(req));
        this.stationMessageService.pushMessage(req);
        return Result.success();
    }


    @GetMapping("/users/{user_id}/station_messages")
    public Result<List<StationMessage>> load(@PathVariable("user_id") Long userId) {
        final List<StationMessage> messages = this.stationMessageService.list(Wraps.<StationMessage>lbQ()
                .eq(StationMessage::getMark, false)
                .eq(StationMessage::getReceiveId, userId));
        return Result.success(messages);
    }

    @PatchMapping("/station_messages/{message_id}/mark")
    public Result<ResponseEntity<Void>> mark(@PathVariable("message_id") Long messageId) {
        StationMessage message = new StationMessage();
        message.setId(messageId);
        message.setMark(true);
        this.stationMessageService.updateById(message);
        return Result.success();
    }


    @DeleteMapping("/station_messages/{message_id}")
    public Result<ResponseEntity<Void>> del(@PathVariable("message_id") Long messageId) {
        this.stationMessageService.removeById(messageId);
        return Result.success();
    }

}
