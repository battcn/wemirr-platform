package com.wemirr.platform.authority.controller.message;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@Tag(name = "短信消息")
@RequiredArgsConstructor
@RequestMapping("/sms_messages")
public class SmsMessageController {


}
