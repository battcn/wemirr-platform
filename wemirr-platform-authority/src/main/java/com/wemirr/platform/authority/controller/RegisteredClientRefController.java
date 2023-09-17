package com.wemirr.platform.authority.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.commons.annotation.log.SysLog;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.baseinfo.RegisteredClientRef;
import com.wemirr.platform.authority.domain.req.RegisteredClientRefReq;
import com.wemirr.platform.authority.domain.resp.RegisteredClientRefResp;
import com.wemirr.platform.authority.service.RegisteredClientRefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.wemirr.platform.authority.domain.converts.RegisteredClientRefConverts.REGISTERED_CLIENT_REF_2_RESP_CONVERTS;

/**
 * 应用管理
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/registered_client_refs")
@Tag(name = "终端管理", description = "终端管理")
public class RegisteredClientRefController {


    private final RegisteredClientRefService registeredClientRefService;

    @GetMapping
    @Parameters({
            @Parameter(description = "clientId", name = "clientId", in = ParameterIn.QUERY),
            @Parameter(description = "clientName", name = "clientName", in = ParameterIn.QUERY),
    })
    @Operation(summary = "应用列表 - [Levin] - [DONE]")
    public IPage<RegisteredClientRefResp> query(@Parameter(description = "当前页") @RequestParam(required = false, defaultValue = "1") Integer current,
                                                @Parameter(description = "条数") @RequestParam(required = false, defaultValue = "20") Integer size,
                                                String clientId, String clientName) {
        return this.registeredClientRefService.page(new Page<>(current, size),
                Wraps.<RegisteredClientRef>lbQ().like(RegisteredClientRef::getClientId, clientId)
                        .like(RegisteredClientRef::getClientName, clientName)).convert(REGISTERED_CLIENT_REF_2_RESP_CONVERTS::convert);
    }

    @PostMapping
    @SysLog(value = "保存应用")
    @Operation(summary = "保存应用")
    public void save(@Validated @RequestBody RegisteredClientRefReq dto) {
        this.registeredClientRefService.registeredClient(dto);
    }

    @DeleteMapping("/{id}")
    @SysLog(value = "删除应用")
    @Operation(summary = "删除应用")
    public void del(@PathVariable String id) {
        this.registeredClientRefService.deleteById(id);
    }


}
