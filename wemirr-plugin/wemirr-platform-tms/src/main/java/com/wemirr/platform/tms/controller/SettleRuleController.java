package com.wemirr.platform.tms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.platform.tms.domain.req.BmsRulePageReq;
import com.wemirr.platform.tms.domain.req.BmsRuleSaveReq;
import com.wemirr.platform.tms.domain.resp.BmsRulePageResp;
import com.wemirr.platform.tms.domain.resp.RuleOptionResp;
import com.wemirr.platform.tms.domain.resp.RuleSymbolResp;
import com.wemirr.platform.tms.service.SettleRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/settle/rule")
@Tag(name = "结算规则模块",description = "结算规则模块")
public class SettleRuleController {

    private final SettleRuleService ruleService;

    @PostMapping("/page")
    @Operation(summary = "结算规则列表 - [Levin] - [DONE]")
    public IPage<BmsRulePageResp> pageList(@RequestBody BmsRulePageReq req) {
        return this.ruleService.pageList(req);
    }

    @GetMapping("/{id}")
    @Operation(summary = "结算规则信息 - [Levin] - [DONE]")
    public BmsRulePageResp info(@PathVariable Long id) {
        return this.ruleService.info(id);
    }

    @PostMapping("/create")
    @Operation(summary = "创建结算规则 - [Levin] - [DONE]")
    public BmsRulePageResp create(@RequestBody BmsRuleSaveReq req) {
        return this.ruleService.createRule(req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "取消结算规则 - [Levin] - [DONE]")
    public BmsRulePageResp cancel(@PathVariable Long id) {
        return this.ruleService.cancelRule(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "编辑结算规则 - [Levin] - [DONE]")
    public BmsRulePageResp update(@PathVariable Long id, @RequestBody BmsRuleSaveReq req) {
        return this.ruleService.updateRule(id, req);
    }

    @GetMapping("/symbol")
    @Operation(summary = "查询表达式可选字符 - [Levin] - [DONE]")
    public List<RuleSymbolResp> getSymbols() {
        return this.ruleService.getSymbols();
    }

    @GetMapping("/option")
    @Operation(summary = "查询规则可选字段 - [Levin] - [DONE]")
    public List<RuleOptionResp> getOptions() {
        return this.ruleService.getOptions();
    }

}
