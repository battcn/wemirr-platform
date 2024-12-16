package com.wemirr.platform.suite.designer.controller;

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
@RequestMapping("/form-designer")
@RequiredArgsConstructor
@Tag(name = "表单设计", description = "表单设计")
public class FormDesignerController {

    // TODO 在线开发

    // 业务名	功能名	类名	包名	作者	移除表前缀	生成方式


}
