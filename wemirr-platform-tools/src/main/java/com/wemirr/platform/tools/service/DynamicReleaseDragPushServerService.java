package com.wemirr.platform.tools.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServer;
import com.wemirr.platform.tools.domain.req.BatchKey;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerModelResp;

/**
 * @author Levin
 */
public interface DynamicReleaseDragPushServerService extends SuperService<DynamicReleaseDragPushServer> {


    DynamicReleaseDragPushServerModelResp queryModelByPushId(Long pushId);

    void binding(Long id, BatchKey<Long> ids);
}
