package com.wemirr.platform.iam;

import cn.dev33.satoken.secure.BCrypt;
import com.wemirr.framework.security.utils.PasswordEncoderHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class PasswordEncoderHelperTest {

    @Test
    public void passwordEncoderTest() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String rawPassword = "123456";
        String oldPassword = "{bcrypt}$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K";
        log.info("encode - {}", passwordEncoder.encode("123456"));
        log.info("encode - {}", passwordEncoder.encode("123456"));
        log.info("matches - {}", passwordEncoder.matches("123456", oldPassword));

        String xx = BCrypt.hashpw("123456");
        System.out.println(BCrypt.checkpw("123456", xx));
        System.out.println(BCrypt.checkpw("123456", "$2a$10$R2AdNVf402GnqcJejdjY..wOHP5hFt5x0vz5qXdTVG.udcdFmqu.K"));


        System.out.println(PasswordEncoderHelper.encode(rawPassword));
        System.out.println(PasswordEncoderHelper.matches(rawPassword, oldPassword));
        System.out.println(PasswordEncoderHelper.matches(rawPassword, "{noop}123456"));
        System.out.println(PasswordEncoderHelper.matches(rawPassword, "{noop}1234567"));
    }
}
