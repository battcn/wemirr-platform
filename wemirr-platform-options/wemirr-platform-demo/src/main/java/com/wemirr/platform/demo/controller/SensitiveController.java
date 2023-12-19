package com.wemirr.platform.demo.controller;

import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.demo.domain.SensitiveTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 脱敏测试类
 * 脱敏的目的：传输给第三方的数据或者返回给前端的列表数据进行脱敏
 * 如果有编辑权限需要编辑数据怎么办：调用详情接口返回未脱敏的数据
 *
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/sensitives")
@RequiredArgsConstructor
public class SensitiveController {

    @GetMapping("/list")
    @IgnoreAuthorize
    public List<SensitiveTest> list() {
        final SensitiveTest s1 = SensitiveTest.builder().mobile("13000001111")
                .desc("随机打码").none("不打码").username("1837307555@qq.com").build();
        final SensitiveTest s2 = SensitiveTest.builder().mobile("13000002222")
                .desc("随机打码").none("不打码").username("1837307555@qq.com").build();
        return List.of(s1, s2);
    }


}
