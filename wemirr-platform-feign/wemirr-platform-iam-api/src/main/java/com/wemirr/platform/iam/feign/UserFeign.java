package com.wemirr.platform.iam.feign;

import com.wemirr.framework.commons.FeignConstants;
import com.wemirr.framework.commons.remote.LoadService;
import com.wemirr.platform.iam.feign.domain.resp.UserInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

/**
 * @author Levin
 */
@FeignClient(name = FeignConstants.AUTH_FEIGN_NAME, dismiss404 = true)
public interface UserFeign extends LoadService<UserInfoResp> {

    /**
     * 根据 ID 批量查询
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return 查询结果
     */
    @Override
    @PostMapping("/users/batch_ids")
    Map<Object, UserInfoResp> findByIds(@RequestBody Set<Object> ids);


    /**
     * 根据 ID 批量查询
     *
     * @param id 唯一键（可能不是主键ID)
     * @return 查询结果
     */
    @PostMapping("/users/{id}")
    UserInfoResp findById(@PathVariable Long id);

}
