package com.wemirr.platform.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.common.entity.I18nData;
import com.wemirr.platform.authority.domain.common.req.I18nDataSaveReq;
import com.wemirr.platform.authority.domain.common.req.I18nPageReq;
import com.wemirr.platform.authority.domain.common.resp.I18nDataPageResp;

/**
 * @author Levin
 */
public interface I18nDataService extends SuperService<I18nData> {

    /**
     * 分页查询
     *
     * @param req req
     * @return 查询结果
     */
    IPage<I18nDataPageResp> pageList(I18nPageReq req);

    /**
     * 添加 i18n 数据
     *
     * @param req req
     */
    void add(I18nDataSaveReq req);

    /**
     * 编辑I18N数据
     *
     * @param id  id
     * @param req req
     */
    void edit(Long id, I18nDataSaveReq req);
}
