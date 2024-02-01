/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wemirr.platform.authority.domain.baseinfo.converts;

import com.wemirr.framework.db.mybatisplus.page.BasePageConverts;
import com.wemirr.platform.authority.domain.baseinfo.entity.User;
import com.wemirr.platform.authority.domain.baseinfo.req.UserUpdateReq;
import com.wemirr.platform.authority.domain.baseinfo.resp.UserResp;
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
