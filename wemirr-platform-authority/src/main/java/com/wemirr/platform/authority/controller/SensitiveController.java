package com.wemirr.platform.authority.controller;

import com.wemirr.framework.security.configuration.client.annotation.IgnoreAuthorize;
import com.wemirr.platform.authority.domain.entity.common.SensitiveTestEntity;
import com.wemirr.platform.authority.repository.ResourceMapper;
import com.wemirr.platform.authority.repository.TenantConfigMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensitives")
public class SensitiveController {

    private final ResourceMapper resourceMapper;
    private final TenantConfigMapper tenantConfigMapper;

    @GetMapping("/list")
    @IgnoreAuthorize
    public List<SensitiveTestEntity> list() {
        final SensitiveTestEntity s1 = SensitiveTestEntity.builder().mobile("13000001111")
                .desc("随机打码").none("不打码").username("1837307555@qq.com").build();
        final SensitiveTestEntity s2 = SensitiveTestEntity.builder().mobile("13000002222")
                .desc("随机打码").none("不打码").username("1837307555@qq.com").build();
//        this.resourceMapper.queryPermissionByUserId(1L);
        this.tenantConfigMapper.selectTenantDynamicDatasource(10086L);
        return List.of(s1, s2);
    }


}
