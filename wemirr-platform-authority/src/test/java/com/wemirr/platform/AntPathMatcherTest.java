package com.wemirr.platform;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;

public class AntPathMatcherTest {

    @Test
    public void test1() {
        final AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/abc/*/aa", "/abc/xxx/aa"));
        System.out.println(StringUtils.containsAny("http://local", "http", "https"));
        // {bcrypt}$2a$10$drv58T9XIdIquX1.GB.TSuT.hmIlYXSmGkdng9Kt2yu1Zhf8LtZne
        // {bcrypt}$2a$10$RjxqgHEAdKysnrmB1dteru42i553UOdpJo0BLjy8N671vqGRP557S

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }

}
