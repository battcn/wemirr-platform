package com.wemirr.platform.iam;

import cn.hutool.core.lang.Assert;
import com.wemirr.framework.boot.remote.dict.DictLoadService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author YanCh
 * create on: 2025-06-06 15:50
 **/
@SpringBootTest(classes = IamApplication.class)
public class RemoteAloneRedisTest {
    @Resource
    private DictLoadService dictLoadService;


    @Test
    public void dictLoadTest() {
        Map<Object, Object> map = dictLoadService.findByIds("AREA_LEVEL");
        Assert.notNull(map, "字典加载失败");
    }

}
