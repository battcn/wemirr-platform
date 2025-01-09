package com.wemirr.platform.suite.online.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDataSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import com.wemirr.platform.suite.online.service.OnlineFormDataService;
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
@RequestMapping("/online-form-data")
@RequiredArgsConstructor
@Tag(name = "表单数据", description = "表单数据")
public class OnlineFormDataController {

    private final OnlineFormDataService onlineFormDataService;


    @Operation(summary = "分页查询", description = "分页查询")
    @PostMapping("/page")
    public IPage<JSONObject> pageList(@RequestBody OnlineFormDesignerPageReq req) {
        return onlineFormDataService.pageList(req);
    }

    @PostMapping("/create")
    @Operation(summary = "添加数据 - [Levin] - [DONE]")
    public void created(@Validated @RequestBody OnlineFormDataSaveReq req) {
        this.onlineFormDataService.created(req);
    }

    @PutMapping("/{id}/modify")
    @Operation(summary = "修改数据 - [Levin] - [DONE]")
    public void modify(@PathVariable Long id, @Validated @RequestBody OnlineFormDataSaveReq req) {
        this.onlineFormDataService.modify(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据 - [Levin] - [DONE]")
    public void delete(@PathVariable Long id) {
        this.onlineFormDataService.removeById(id);
    }
}
