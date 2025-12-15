package com.wemirr.platform.tms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tms.domain.entity.*;
import com.wemirr.platform.tms.domain.enums.RuleOption;
import com.wemirr.platform.tms.domain.enums.RuleSymbol;
import com.wemirr.platform.tms.domain.req.BmsDetailCalcReq;
import com.wemirr.platform.tms.domain.req.SettleDetailPageReq;
import com.wemirr.platform.tms.domain.req.SettleDetailSaveReq;
import com.wemirr.platform.tms.domain.resp.BmsRuleConditionResp;
import com.wemirr.platform.tms.domain.resp.BmsRulePageResp;
import com.wemirr.platform.tms.domain.resp.SettleDetailPageResp;
import com.wemirr.platform.tms.mapper.*;
import com.wemirr.platform.tms.service.SettleService;
import com.wemirr.platform.tms.util.CalculateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangyi
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SettleServiceImpl extends SuperServiceImpl<BmsSettleDetailMapper, BmsSettleDetail> implements SettleService {

    private final AuthenticationContext authenticationContext;
    private final OrderMapper orderMapper;
    private final BmsRuleMapper ruleMapper;
    private final BmsRuleConditionMapper ruleConditionMapper;
    private final TmsOrderAddressMapper orderSenderMapper;

    @Override
    public IPage<SettleDetailPageResp> pageList(SettleDetailPageReq req) {
        LambdaQueryWrapper<BmsSettleDetail> wrapper = Wrappers.<BmsSettleDetail>lambdaQuery()
                .eq(BmsSettleDetail::getOrderId, req.getOrderId())
                .eq(req.getPaymentType() != null && req.getPaymentType() >= 0, BmsSettleDetail::getPaymentType, req.getPaymentType())
                .eq(BmsSettleDetail::getLocked, false);
        return this.baseMapper.selectPage(req.buildPage(), wrapper).convert(x -> BeanUtil.toBean(x, SettleDetailPageResp.class));
    }

    @Override
    public SettleDetailPageResp createFee(SettleDetailSaveReq req) {
        Long orderId = req.getOrderId();
        TmsOrder order = this.orderMapper.selectById(orderId);
        if (order == null) {
            return null;
        }
        final BmsSettleDetail detail = BeanUtil.toBean(req, BmsSettleDetail.class);
        detail.setId(null);
        detail.setProjectId(order.getProjectId());
        detail.setOperatorId(authenticationContext.userId());
        detail.setLocked(false);
        this.baseMapper.insert(detail);

        SettleDetailPageResp resp = new SettleDetailPageResp();
        resp.setId(detail.getId());
        return resp;
    }

    @Override
    public SettleDetailPageResp cancelFee(Long id) {
        BmsSettleDetail detail = this.baseMapper.selectById(id);
        if (detail == null) {
            return null;
        }
        detail.setLocked(true);
        this.baseMapper.updateById(detail);

        SettleDetailPageResp resp = new SettleDetailPageResp();
        resp.setId(id);
        return resp;
    }

    @Override
    public SettleDetailPageResp updateFee(Long id, SettleDetailSaveReq req) {
        BmsSettleDetail oldDetail = this.baseMapper.selectById(id);
        if (oldDetail == null) {
            return null;
        }

        final BmsSettleDetail newDetail = BeanUtil.toBean(req, BmsSettleDetail.class);
        newDetail.setId(id);
        newDetail.setOrderId(null);
        newDetail.setOperatorId(null);
        newDetail.setPayerId(null);
        newDetail.setPayerName(null);
        newDetail.setPayeeId(null);
        newDetail.setPayeeName(null);
        newDetail.setPayeeDriverId(null);
        newDetail.setPayeeDriverName(null);
        this.baseMapper.updateById(newDetail);

        SettleDetailPageResp resp = new SettleDetailPageResp();
        resp.setId(id);
        return resp;
    }

    @Override
    public List<Long> calc(BmsDetailCalcReq req) {
        Long projectId = req.getProjectId();
        LocalDate orderStartDate = req.getStartDate();
        LocalDate orderEndDate = req.getEndDate();
        if (projectId == null || orderStartDate == null || orderEndDate == null) {
            return null;
        }
        Integer paymentType = req.getPaymentType();
        Set<Long> orderIds = new HashSet<>();

        //删除原有规则计算费用
        BmsSettleDetail oldDetail = BmsSettleDetail.builder().locked(true).build();
        this.baseMapper.update(oldDetail, Wrappers.<BmsSettleDetail>lambdaUpdate()
                .eq(BmsSettleDetail::getProjectId, projectId)
                .eq(BmsSettleDetail::getPaymentType, paymentType)
                .eq(BmsSettleDetail::getSourceId, 1)
                .eq(BmsSettleDetail::getLocked, false));

        List<Map<String, Object>> orderMaps = this.orderMapper.selectMaps(Wrappers.<TmsOrder>lambdaQuery()
                .eq(TmsOrder::getProjectId, req.getProjectId())
                .between(TmsOrder::getCreateTime, orderStartDate, orderEndDate.plusDays(1))
        ).parallelStream().peek(orderMap -> {
            Long orderId = (Long) orderMap.get("id");
            final List<Map<String, Object>> senders = this.orderSenderMapper.selectMaps(Wraps.<TmsOrderAddress>lbQ()
                    .eq(TmsOrderAddress::getOrderId, orderId)
                    .eq(TmsOrderAddress::getType, 0));
            orderMap.put("tms_order_sender", senders);

            final List<Map<String, Object>> consignees = this.orderSenderMapper.selectMaps(Wraps.<TmsOrderAddress>lbQ()
                    .eq(TmsOrderAddress::getOrderId, orderId)
                    .eq(TmsOrderAddress::getType, 1));
            orderMap.put("tms_order_consignee", consignees);
        }).collect(Collectors.toList());

        if (CollUtil.isNotEmpty(orderMaps)) {
            List<BmsRulePageResp> bmsRules = this.ruleMapper.selectList(Wrappers.<BmsRule>lambdaQuery()
                    .eq(BmsRule::getProjectId, projectId)
                    .eq(paymentType != null, BmsRule::getPaymentType, paymentType)
                    .eq(BmsRule::getLocked, false)
            ).stream().map(item -> {
                final BmsRulePageResp rule = BeanUtil.toBean(item, BmsRulePageResp.class);
                List<BmsRuleConditionResp> conditions = this.ruleConditionMapper.selectList(Wrappers.<BmsRuleCondition>lambdaQuery()
                        .eq(BmsRuleCondition::getRuleId, item.getId())
                        .eq(BmsRuleCondition::getLocked, false)
                ).stream().map(condition -> BeanUtil.toBean(condition, BmsRuleConditionResp.class)).collect(Collectors.toList());
                rule.setConditions(conditions);
                return rule;
            }).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(bmsRules)) {
                List<BmsSettleDetail> details = new ArrayList<>();
                for (Map<String, Object> orderMap : orderMaps) {
                    for (BmsRulePageResp rule : bmsRules) {
                        //判断有效期
                        LocalDateTime createTime = (LocalDateTime) orderMap.get("create_time");
                        LocalDate createDate = createTime.toLocalDate();
                        LocalDate startDate = rule.getStartDate();
                        LocalDate endDate = rule.getEndDate();
                        if (createDate.isBefore(startDate) || createDate.isAfter(endDate)) {
                            //创建时间不在规则有效期内，不计算
                            continue;
                        }
                        //条件规则
                        StringBuffer ruleWhere = new StringBuffer();
                        //判断规则
                        boolean needCalc = this.checkRuleOptions(orderMap, rule.getConditions(), ruleWhere);
                        if (!needCalc) {
                            //条件不满足，不计算
                            continue;
                        }
                        //计算费用
                        String expression = rule.getExpression();
                        BigDecimal amount = CalculateUtils.calculate(expression, orderMap);
                        //计算公式
                        StringBuilder msg = new StringBuilder();
                        msg.append(expression).append("\n");
                        BigDecimal amountDecimal = amount.setScale(2, RoundingMode.HALF_UP);
                        //判断下限、上限
                        BigDecimal min = rule.getMin();
                        if (min != null) {
                            amountDecimal = amountDecimal.max(min);
                        }
                        BigDecimal max = rule.getMax();
                        if (max != null) {
                            amountDecimal = amountDecimal.min(max);
                        }
                        if (amountDecimal.compareTo(BigDecimal.ZERO) > 0) {
                            Long orderId = (Long) orderMap.get("id");
                            BmsSettleDetail detail = BmsSettleDetail.builder()
                                    .orderId(orderId)
                                    .projectId(projectId)
                                    .paymentType(paymentType)
                                    .payerId(rule.getPayerId())
                                    .payerName(rule.getPayerName())
                                    .payeeId(rule.getPayeeId())
                                    .payeeName(rule.getPayeeName())
                                    .payeeDriverId(rule.getPayeeDriverId())
                                    .payeeDriverName(rule.getPayeeDriverName())
                                    .feeType(rule.getFeeType())
                                    .feeAmount(amountDecimal)
                                    .operatorId(authenticationContext.tenantId())
                                    .ruleId(rule.getId())
                                    .sourceId(1)
                                    .locked(false)
                                    .build();
                            details.add(detail);
                            orderIds.add(orderId);
                        }
                    }
                }
                this.baseMapper.insertBatchSomeColumn(details);
            }
        }
        return orderIds.stream().collect(Collectors.toList());
    }

    private boolean checkRuleOptions(Map<String, Object> order, List<BmsRuleConditionResp> conditions, StringBuffer msg) {
        for (BmsRuleConditionResp condition : conditions) {
            Integer optionId = condition.getOptionId();
            RuleOption option = RuleOption.getOptionById(optionId);
            if (option != null) {
                Integer type = option.getType();
                String detail = condition.getDetail();
                String colName = option.getColName();
                String subName = option.getSubName();

                Integer judgmentId = condition.getJudgmentId();
                String optionName = option.getName();
                String symbolName = RuleSymbol.getSymbolNameById(judgmentId);

                Object colVal = null;
                if (!StringUtils.hasLength(subName) || "tms_order".equals(subName)) {
                    colVal = order.get(colName);
                } else if ("tms_order_sender".equals(subName) || "tms_order_consignee".equals(subName)) {
                    List<Map<String, Object>> subMaps = (List<Map<String, Object>>) order.get(subName);
                    if (CollUtil.isNotEmpty(subMaps)) {
                        colVal = subMaps.get(0).get(colName);
                    }
                }
                String colValStr = colVal == null ? null : colVal.toString();
                if (colValStr == null) {
                    log.info("checkRuleOptions[colValStr is NULL!!] systemNo:{},colName:{}", order.get("systemNo"), colName);
                    return false;
                }
                msg.append(optionName != null ? optionName : "").append(symbolName != null ? symbolName : "");

                if (type == RuleOption.TYPE_ENUMERATION) {
                    if (RuleSymbol.EQUAL.getId().equals(judgmentId) && !detail.equals(colValStr)) {
                        //等于
                        msg.append(colValStr).append("|");
                        return false;
                    } else if (RuleSymbol.NOT_EQUAL.getId().equals(judgmentId) && detail.equals(colValStr)) {
                        //不等于
                        msg.append(colValStr).append("|");
                        return false;
                    } else if (RuleSymbol.IS_IN.getId().equals(judgmentId) && !detail.contains(colValStr)) {
                        //等于其一
                        msg.append(colValStr).append("|");
                        return false;
                    } else if (RuleSymbol.NOT_IN.getId().equals(judgmentId) && detail.contains(colValStr)) {
                        //不等于其一
                        msg.append(colValStr).append("|");
                        return false;
                    }
                } else if (type == RuleOption.TYPE_TF) {
                    if (!colValStr.equals(detail)) {
                        msg.append("1".equals(colValStr) ? "是" : "否").append("|");
                        return false;
                    }
                } else if (type == RuleOption.TYPE_INPUT_NUM) {
                    BigDecimal colBig = new BigDecimal(colValStr);
                    BigDecimal detailBig = detail != null ? new BigDecimal(detail) : BigDecimal.ZERO;

                    if (RuleSymbol.MORE_THAN.getId().equals(judgmentId) && colBig.compareTo(detailBig) <= 0) {
                        msg.append(colBig).append("|");
                        return false;
                    } else if (RuleSymbol.MORE_AND_EQ.getId().equals(judgmentId) && colBig.compareTo(detailBig) == -1) {
                        msg.append(colBig).append("|");
                        return false;
                    } else if (RuleSymbol.LESS_THAN.getId().equals(judgmentId) && colBig.compareTo(detailBig) >= 0) {
                        msg.append(colBig).append("|");
                        return false;
                    } else if (RuleSymbol.LESS_AND_EQ.getId().equals(judgmentId) && colBig.compareTo(detailBig) == 1) {
                        msg.append(colBig).append("|");
                        return false;
                    }
                } else if (type == RuleOption.TYPE_INPUT_STR) {
                    if (RuleSymbol.CONTAIN.getId().equals(judgmentId) && !colValStr.contains(detail)) {
                        //包含
                        msg.append(colValStr).append("|");
                        return false;
                    } else if (RuleSymbol.NOT_CONTAIN.getId().equals(judgmentId) && colValStr.contains(detail)) {
                        //不包含
                        msg.append(colValStr).append("|");
                        return false;
                    }
                }
                msg.append(colValStr).append("|");
            }
        }
        return true;
    }
}
