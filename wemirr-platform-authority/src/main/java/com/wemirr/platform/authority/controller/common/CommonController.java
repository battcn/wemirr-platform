package com.wemirr.platform.authority.controller.common;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公共接口
 * </p>
 *
 * @author Levin
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/commons")
@Tag(name = "公共接口", description = "公共接口")
@RequiredArgsConstructor
public class CommonController {




}
