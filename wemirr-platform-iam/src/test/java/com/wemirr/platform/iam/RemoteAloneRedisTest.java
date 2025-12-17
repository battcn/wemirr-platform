package com.wemirr.platform.iam;

import cn.hutool.core.lang.Assert;
import com.wemirr.framework.boot.remote.dict.DictLoadService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * 远程Redis字典加载测试
 *
 * @author YanCh
 */
@Slf4j
@SpringBootTest(classes = IamApplication.class)
public class RemoteAloneRedisTest {

    @Resource
    private DictLoadService dictLoadService;

    @Test
    void shouldLoadDictByCode() {
        Map<Object, Object> map = dictLoadService.findByIds("AREA_LEVEL");
        Assert.notNull(map, "字典加载失败");
        log.info("字典加载结果: {}", map);
    }
}
