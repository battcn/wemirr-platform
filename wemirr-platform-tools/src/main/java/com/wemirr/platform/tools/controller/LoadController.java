package com.wemirr.platform.tools.controller;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/loads")
@RequiredArgsConstructor
@Validated
@Tag(name = "数据加载", description = "数据加载")
public class LoadController {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Mode {
        private String crudOptions;
    }

    @IgnoreAuthorize
    @GetMapping("/{mode}")
    @Operation(summary = "查询")
    public Result<Mode> find(@PathVariable String mode) {
        Mode standard = new Mode("{\"columns\":[{\"title\":\"id\",\"key\":\"id\",\"form\":{\"disabled\":true},\"disabled\":true},{\"title\":\"登录账号\",\"key\":\"principal\",\"search\":{\"disabled\":false}},{\"title\":\"名称\",\"key\":\"name\",\"search\":{\"disabled\":false}},{\"title\":\"ip\",\"width\":120,\"key\":\"ip\"},{\"title\":\"登录地点\",\"width\":150,\"key\":\"location\"}]}");
        Mode test = new Mode("{\"columns\":[{\"title\":\"id\",\"key\":\"id\",\"form\":{\"disabled\":true},\"disabled\":true},{\"title\":\"登录账号\",\"key\":\"principal\",\"search\":{\"disabled\":false}},{\"title\":\"名称\",\"key\":\"name\",\"search\":{\"disabled\":false}},{\"title\":\"ip\",\"width\":120,\"key\":\"ip\"}]}");
        Mode result = StringUtils.equals(mode, "standard") ? standard : test;
        return Result.success(result);
    }


}
