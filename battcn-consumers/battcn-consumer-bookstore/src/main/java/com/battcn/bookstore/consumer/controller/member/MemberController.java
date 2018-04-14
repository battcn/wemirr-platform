package com.battcn.bookstore.consumer.controller.member;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.bookstore.consumer.pojo.dto.MemberDetailDto;
import com.battcn.framework.redis.annotation.CacheLock;
import com.battcn.framework.security.Environments;
import com.battcn.member.facade.MemberService;
import com.battcn.member.pojo.po.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.battcn.bookstore.consumer.pojo.converter.MemberConverts.MEMBER_DETAIL_DTO_CONVERTER;

/**
 * 个人信息
 *
 * @author Levin
 * @since 2018/4/9 0009
 */
@RestController
@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "会员管理", description = "会员管理", tags = "4.0")
public class MemberController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20881", timeout = 10000)
    private MemberService memberService;

    @CacheLock
    @ApiOperation(value = "修改会员昵称（DONE）", notes = "修改会员昵称 \n 注：添加了分布式锁,默认5秒")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/nick_name")
    public void modifyDetail(@RequestParam("nick_name") String nickName) {
        Member member = new Member();
        member.setMemberNo(Environments.getAuthId());
        member.setNickName(nickName);
        this.memberService.updateSelectiveById(member);
    }

    @ApiOperation(value = "个人资料（DONE）", notes = "获取当前会员的个人资料")
    @GetMapping
    public MemberDetailDto memberDetail() {
        final Member member = memberService.selectById(Environments.getAuthId());
        return MEMBER_DETAIL_DTO_CONVERTER.convert(member);
    }


}
