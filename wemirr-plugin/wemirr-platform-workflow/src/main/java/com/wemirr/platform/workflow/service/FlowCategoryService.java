
package com.wemirr.platform.workflow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.workflow.domain.dto.req.FlowCategoryPageReq;
import com.wemirr.platform.workflow.domain.dto.req.FlowCategorySaveReq;
import com.wemirr.platform.workflow.domain.dto.resp.FlowCategoryPageResp;
import com.wemirr.platform.workflow.domain.entity.FlowCategory;
//import com.wemirr.framework.commons.entity.enums.ActiveStatus;
//import com.wemirr.framework.db.mybatisplus.ext.SuperService;

/**
 * <p>
 * 业务接口
 * 流程分类
 * </p>
 *
 * @author battcn
 * @since 2025-08-15
 */
public interface FlowCategoryService extends SuperService<FlowCategory> {

    /**
     * 按权限查询流程分类的分页信息
     *
     * @param req req
     * @return FlowCategory
     */
    IPage<FlowCategoryPageResp> pageList(FlowCategoryPageReq req);

    /**
     * 添加流程分类
     *
     * @param req req
     */
    void create(FlowCategorySaveReq req);

    /**
     * 修改流程分类数据
     *
     * @param id  id
     * @param req req
     */
    void modify(String id, FlowCategorySaveReq req);

//    /**
//     * 修改流程分类状态
//     * @param id id
//     * @param activeStatus 状态
//     */
//    void modifyStatus(String id, ActiveStatus activeStatus);
}