package com.wemirr.platform.tools.controller.dynamic;

import com.wemirr.platform.tools.domain.DynamicReleaseDragSetting;
import com.wemirr.platform.tools.service.DynamicReleaseGenerateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/dynamic_release_generates")
@RequiredArgsConstructor
@Tag(name = "代码生成", description = "代码生成")
@Validated
public class DynamicReleaseGenerateController {

    private final DynamicReleaseGenerateService dynamicReleaseGenerateService;


    @Operation(summary = "添加代码生成")
    @PostMapping
    public void add(@Validated @RequestBody DynamicReleaseDragSetting req) {
        this.dynamicReleaseGenerateService.add(req);
        
    }


}
