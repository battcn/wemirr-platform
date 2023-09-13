package com.wemirr.platform.authority.domain.converts;

import com.wemirr.framework.db.page.BasePageConverts;
import com.wemirr.platform.authority.domain.entity.baseinfo.User;
import com.wemirr.platform.authority.domain.req.UserUpdateReq;
import com.wemirr.platform.authority.domain.resp.UserResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author Levin
 */
@Slf4j
public class UserConverts {


    public static final User2VoConverts USER_2_VO_CONVERTS = new User2VoConverts();
    public static final UserDto2PoConverts USER_DTO_2_PO_CONVERTS = new UserDto2PoConverts();

    public static class User2VoConverts implements BasePageConverts<User, UserResp> {

        @Override
        public UserResp convert(User source) {
            if (source == null) {
                return null;
            }
            UserResp target = new UserResp();
            BeanUtils.copyProperties(source, target);
            return target;
        }
    }

    public static class UserDto2PoConverts implements BasePageConverts<UserUpdateReq, User> {

        @Override
        public User convert(UserUpdateReq source, Long id) {
            if (source == null) {
                return null;
            }
            User target = new User();
            BeanUtils.copyProperties(source, target);
            target.setId(id);
            return target;
        }
    }
}
