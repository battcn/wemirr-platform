package com.wemirr.platform.tools.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDragReq;

/**
 * @author Levin
 */
public interface DynamicReleaseDragService extends SuperService<DynamicReleaseDrag> {

    void add(DynamicReleaseDragReq req);

    void edit(Long id, DynamicReleaseDragReq req);

}
