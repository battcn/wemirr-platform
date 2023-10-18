package com.wemirr.platform.authority.feign;

import com.wemirr.framework.commons.remote.LoadService;
import com.wemirr.platform.authority.feign.domain.resp.UserInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

/**
 * @author Levin
 */
@FeignClient(name = FeignConstants.FEIGN_NAME, dismiss404 = true)
public interface UserFeign extends LoadService<Long, UserInfoResp> {

    @Override
    @PostMapping("/users/batch_ids")
    Map<Long, UserInfoResp> findByIds(@RequestBody Set<Long> ids);

}
