package com.battcn.member.provider.service;

import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.member.facade.MemberService;
import com.battcn.member.pojo.po.Member;

/**
 * 点赞
 *
 * @author Levin
 * @since 2018/3/23 0023
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout = 5000
)
public class MemberServiceImpl extends BaseServiceImpl<Member> implements MemberService {

    @Override
    public Member findByName(String username) {
        Member record = new Member();
        record.setUsername(username);
        return this.selectOne(record);
    }
}
