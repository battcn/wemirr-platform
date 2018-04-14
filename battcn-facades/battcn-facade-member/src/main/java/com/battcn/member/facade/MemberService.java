package com.battcn.member.facade;

import com.battcn.framework.mybatis.service.BaseService;
import com.battcn.member.pojo.po.Member;

/**
 * @author Levin
 * @since 2018/3/23 0023
 */
public interface MemberService extends BaseService<Member> {

    /**
     * 根据 账号名 查询会员信息
     *
     * @param accountName 账号
     * @return 会员信息
     */
    Member findByName(String accountName);
}
