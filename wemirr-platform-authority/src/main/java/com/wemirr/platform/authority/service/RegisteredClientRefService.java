package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.authority.domain.entity.baseinfo.RegisteredClientRef;
import com.wemirr.platform.authority.domain.req.RegisteredClientRefReq;

/**
 * @author levin
 */
public interface RegisteredClientRefService extends SuperService<RegisteredClientRef> {

    /**
     * 注册安全终端
     *
     * @param req req
     */
    void registeredClient(RegisteredClientRefReq req);

    /**
     * 根据ID删除
     *
     * @param id id
     */
    void deleteById(String id);
}
