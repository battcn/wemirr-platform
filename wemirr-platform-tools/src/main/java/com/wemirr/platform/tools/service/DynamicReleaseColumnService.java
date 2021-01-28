package com.wemirr.platform.tools.service;

import com.wemirr.framework.boot.service.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridColumnReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseGridColumnResp;

import java.util.List;

/**
 * @author Levin
 */
public interface DynamicReleaseColumnService extends SuperService<DynamicReleaseColumn> {


    void edit(Long gridId, DynamicReleaseGridColumnReq req);

    List<DynamicReleaseGridColumnResp> listByGridId(Long gridId);
}
