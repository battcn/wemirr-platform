package com.battcn.bookstore.consumer.pojo.converter;

import com.battcn.bookstore.consumer.pojo.dto.MemberDetailDto;
import com.battcn.framework.commons.entity.BaseConverter;
import com.battcn.member.pojo.po.Member;

/**
 * @author Levin
 */
public class MemberConverts {

    public static final MemberDetailDtoConverter MEMBER_DETAIL_DTO_CONVERTER = new MemberDetailDtoConverter();


    public static class MemberDetailDtoConverter extends BaseConverter<Member, MemberDetailDto> {
        @Override
        public MemberDetailDto convert(Member entity) {
            if (entity == null) {
                return null;
            }
            MemberDetailDto result = new MemberDetailDto();
            result.setExp(entity.getExp());
            result.setLevel(entity.getLevel());
            result.setMemberNo(entity.getMemberNo());
            result.setNickName(entity.getNickName());
            result.setUsername(entity.getUsername());
            result.setPoint(entity.getPoint());
            return result;
        }
    }
}
