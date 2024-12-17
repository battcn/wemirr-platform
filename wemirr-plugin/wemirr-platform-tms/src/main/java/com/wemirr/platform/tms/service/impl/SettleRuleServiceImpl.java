package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.platform.tms.domain.entity.BmsRule;
import com.wemirr.platform.tms.domain.entity.BmsRuleCondition;
import com.wemirr.platform.tms.domain.enums.RuleOption;
import com.wemirr.platform.tms.domain.enums.RuleSymbol;
import com.wemirr.platform.tms.domain.enums.TransportType;
import com.wemirr.platform.tms.domain.req.BmsRuleConditionSaveReq;
import com.wemirr.platform.tms.domain.req.BmsRulePageReq;
import com.wemirr.platform.tms.domain.req.BmsRuleSaveReq;
import com.wemirr.platform.tms.domain.resp.BmsRuleConditionResp;
import com.wemirr.platform.tms.domain.resp.BmsRulePageResp;
import com.wemirr.platform.tms.domain.resp.RuleOptionResp;
import com.wemirr.platform.tms.domain.resp.RuleSymbolResp;
import com.wemirr.platform.tms.mapper.BmsRuleConditionMapper;
import com.wemirr.platform.tms.mapper.BmsRuleMapper;
import com.wemirr.platform.tms.service.SettleRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结算规则
 *
 * @author wangyi
 */
@Service
@RequiredArgsConstructor
public class SettleRuleServiceImpl extends SuperServiceImpl<BmsRuleMapper, BmsRule> implements SettleRuleService {

    private final AuthenticationContext authenticationContext;
    private final BmsRuleConditionMapper conditionMapper;

    @Override
    public IPage<BmsRulePageResp> pageList(BmsRulePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(),Wrappers.<BmsRule>lambdaQuery()
                .eq(BmsRule::getProjectId, req.getProjectId())
                .eq(BmsRule::getFeeType,req.getFeeType())
                .eq(BmsRule::getPaymentType,req.getPaymentType())
                .eq(BmsRule::getRuleName,req.getRuleName())
                .eq(BmsRule::getLocked,false)
        ).convert(x -> BeanUtil.toBean(x, BmsRulePageResp.class));
    }

    @Override
    public BmsRulePageResp info(Long id) {
        BmsRule bmsRule = this.baseMapper.selectById(id);
        if (bmsRule == null) {
            return null;
        }

        BmsRulePageResp resp = BeanUtil.toBean(bmsRule,BmsRulePageResp.class);
        final List<BmsRuleConditionResp> conditions = this.conditionMapper.selectList(Wrappers.<BmsRuleCondition>lambdaQuery()
                .eq(BmsRuleCondition::getRuleId, id)
                .eq(BmsRuleCondition::getLocked, false)
        ).stream().map( item -> BeanUtil.toBean(item,BmsRuleConditionResp.class)).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(conditions)){
            resp.setConditions(conditions);
        }
        return resp;
    }

    @Override
    public BmsRulePageResp createRule(BmsRuleSaveReq req) {
        LocalDate nowDate = LocalDate.now();
        final BmsRule bmsRule = BeanUtil.toBean(req, BmsRule.class);
        bmsRule.setLocked(false);
        LocalDate startDate = bmsRule.getStartDate();
        if (startDate==null){
            bmsRule.setStartDate(LocalDate.of(2000,1,1));
        }
        LocalDate endDate = bmsRule.getEndDate();
        if(endDate==null){
            bmsRule.setEndDate(LocalDate.of(2100,1,1));
        }
        this.baseMapper.insert(bmsRule);

        List<BmsRuleConditionSaveReq> conditions = req.getConditions();
        List<Object> list = conditions.stream().map(item -> {
            final BmsRuleCondition condition = BeanUtil.toBean(item, BmsRuleCondition.class);
            condition.setRuleId(bmsRule.getId());
            condition.setLocked(false);
            return condition;
        }).collect(Collectors.toList());
        this.conditionMapper.insertBatchSomeColumn(list);

        BmsRulePageResp resp = new BmsRulePageResp();
        resp.setId(bmsRule.getId());
        return resp;
    }

    @Override
    public BmsRulePageResp cancelRule(Long id) {
        BmsRule bmsRule = this.baseMapper.selectById(id);
        if (bmsRule == null) {
            return null;
        }
        bmsRule.setLocked(true);
        this.baseMapper.updateById(bmsRule);

        BmsRulePageResp resp = new BmsRulePageResp();
        resp.setId(bmsRule.getId());
        return resp;
    }

    @Override
    public BmsRulePageResp updateRule(Long id, BmsRuleSaveReq req) {
        BmsRule oldRule = this.baseMapper.selectById(id);
        if (oldRule == null) {
            return null;
        }

        final BmsRule newRule = BeanUtil.toBean(req, BmsRule.class);
        newRule.setId(id);
        this.baseMapper.updateById(newRule);

        BmsRuleCondition oldCon = BmsRuleCondition.builder().locked(true).build();
        this.conditionMapper.update(oldCon, Wrappers.<BmsRuleCondition>lambdaUpdate()
                .eq(BmsRuleCondition::getRuleId,id)
                .eq(BmsRuleCondition::getLocked,false));
        List<BmsRuleConditionSaveReq> conditions = req.getConditions();
        List<Object> list = conditions.stream().map(item -> {
            final BmsRuleCondition condition = BeanUtil.toBean(item, BmsRuleCondition.class);
            condition.setRuleId(id);
            condition.setLocked(false);
            return condition;
        }).collect(Collectors.toList());
        this.conditionMapper.insertBatchSomeColumn(list);

        BmsRulePageResp resp = new BmsRulePageResp();
        resp.setId(newRule.getId());
        return resp;
    }

    @Override
    public List<RuleSymbolResp> getSymbols() {
        List<RuleSymbolResp> resp = new ArrayList<>();
        List<RuleSymbolResp> numbers = RuleSymbol.getSymbolListByType(RuleSymbol.NUMBER, RuleSymbol.SHOW);
        List<RuleSymbolResp> operators = RuleSymbol.getSymbolListByType(RuleSymbol.OPERATOR, RuleSymbol.SHOW);
        List<RuleSymbolResp> orderFields = RuleOption.getSymbolListByType();

        resp.addAll(numbers);
        resp.addAll(operators);
        resp.addAll(orderFields);
        return resp;
    }

    @Override
    public List<RuleOptionResp> getOptions() {

        List<RuleOptionResp> optionList = RuleOption.getOptionList(true, false);
        List<RuleOptionResp> list = optionList.stream().map(item -> {
            List<RuleSymbolResp> symbols = new ArrayList<>();
            List<RuleSymbolResp> options = new ArrayList<>();
            Integer type = item.getType();
            Integer selectType = item.getSelectType();
            switch (type) {
                case RuleOption.TYPE_ENUMERATION:
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.EQUAL));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.NOT_EQUAL));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.IS_IN));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.NOT_IN));
                    switch (selectType){
                        case RuleOption.TRANSPORT_TYPE:
                            options = TransportType.getSymbolList();
                            break;
                    }
                    break;
                case RuleOption.TYPE_TF:
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.EQUAL));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.NOT_EQUAL));
                    options.add(RuleSymbolResp.builder().id(0).name("否").build());
                    options.add(RuleSymbolResp.builder().id(1).name("是").build());
                    break;
                case RuleOption.TYPE_INPUT_NUM:
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.EQUAL));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.NOT_EQUAL));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.MORE_THAN));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.MORE_AND_EQ));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.LESS_THAN));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.LESS_AND_EQ));
                    break;
                case RuleOption.TYPE_INPUT_STR:
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.EQUAL));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.NOT_EQUAL));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.CONTAIN));
                    symbols.add(RuleSymbol.getSymbol(RuleSymbol.NOT_CONTAIN));
                    break;
            }
            return RuleOptionResp.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .tableName(item.getTableName())
                    .colName(item.getColName())
                    .type(type)
                    .selectType(selectType)
                    .isCondition(true)
                    .isSolution(false)
                    .symbols(symbols)
                    .options(options)
                    .build();
        }).collect(Collectors.toList());
        return list;
    }

}
