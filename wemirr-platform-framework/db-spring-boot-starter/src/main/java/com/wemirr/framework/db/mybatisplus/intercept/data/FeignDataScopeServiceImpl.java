package com.wemirr.framework.db.mybatisplus.intercept.data;

import com.wemirr.framework.db.properties.DatabaseProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Levin
 */
@Primary
@ConditionalOnProperty(prefix = DatabaseProperties.PREFIX + ".intercept.data-permission", name = "remote")
@FeignClient(name = "wemirr-platform-authority", dismiss404 = true)
public interface FeignDataScopeServiceImpl extends DataScopeService {


    /**
     * 获取用户的数据权限
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @GetMapping("/{user_id}/data_permission")
    DataPermission getDataScopeById(@PathVariable("user_id") Long userId);

}