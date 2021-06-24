package com.wemirr.platform.tools.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.domain.req.DynamicReleaseDragReq;

/**
 * @author Levin
 */
public interface DynamicReleaseDragService extends SuperService<DynamicReleaseDrag> {

    /**
     * 添加
     *
     * @param req req
     */
    void add(DynamicReleaseDragReq req);

    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, DynamicReleaseDragReq req);

}
