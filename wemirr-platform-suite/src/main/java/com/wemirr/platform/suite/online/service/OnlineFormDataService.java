package com.wemirr.platform.suite.online.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.suite.online.domain.entity.OnlineFormData;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDataSaveReq;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;

/**
 * @author levin
 */
public interface OnlineFormDataService extends SuperService<OnlineFormData> {


    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<JSONObject> pageList(OnlineFormDesignerPageReq req);


    /**
     * 新增数据
     *
     * @param req req
     */
    void created(OnlineFormDataSaveReq req);


    /**
     * 修改
     *
     * @param id  id
     * @param req req
     */
    void modify(Long id, OnlineFormDataSaveReq req);

}
