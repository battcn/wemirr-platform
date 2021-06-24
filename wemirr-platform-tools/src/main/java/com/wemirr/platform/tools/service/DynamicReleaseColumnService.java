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


    /**
     * 编辑动态发布字段
     *
     * @param gridId 表格ID
     * @param req    修改内容
     */
    void edit(Long gridId, DynamicReleaseGridColumnReq req);

    /**
     * 根据表格ID查询
     *
     * @param gridId gridId
     * @return 查询结果
     */
    List<DynamicReleaseGridColumnResp> listByGridId(Long gridId);
}
