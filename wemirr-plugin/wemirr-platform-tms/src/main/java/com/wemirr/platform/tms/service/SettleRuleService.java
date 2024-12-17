package com.wemirr.platform.tms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tms.domain.entity.BmsRule;
import com.wemirr.platform.tms.domain.req.BmsRulePageReq;
import com.wemirr.platform.tms.domain.req.BmsRuleSaveReq;
import com.wemirr.platform.tms.domain.resp.BmsRulePageResp;
import com.wemirr.platform.tms.domain.resp.RuleOptionResp;
import com.wemirr.platform.tms.domain.resp.RuleSymbolResp;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 结算规则信息
 *
 * </p>
 *
 * @author Levin
 * @since 2023-09-27
 */
public interface SettleRuleService extends SuperService<BmsRule> {

    /**
     * 规则列表
     *
     * @param req req
     * @return 查询结果
     */
    IPage<BmsRulePageResp> pageList(BmsRulePageReq req);

    /**
     * 规则信息
     *
     * @param id id
     * @return id
     */
    BmsRulePageResp info(Long id);

    /**
     * 创建规则
     *
     * @param req req
     * @return id
     */
    BmsRulePageResp createRule(BmsRuleSaveReq req);

    /**
     * 取消规则
     *
     * @param id id
     * @return id
     */
    BmsRulePageResp cancelRule(Long id);

    /**
     * 修改规则
     *
     * @param id id
     * @param req req
     * @return id
     */
    BmsRulePageResp updateRule(Long id,BmsRuleSaveReq req);

    /**
     * 查询表达式可选字符
     *
     * @return 可选字符
     */
    List<RuleSymbolResp> getSymbols();

    /**
     * 查询规则可选字段
     *
     * @return 可选字段
     */
    List<RuleOptionResp> getOptions();

}
