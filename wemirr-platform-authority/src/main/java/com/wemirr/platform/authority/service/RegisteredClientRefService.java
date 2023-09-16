package com.wemirr.platform.authority.service;

import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.platform.authority.domain.entity.baseinfo.RegisteredClientRef;
import com.wemirr.platform.authority.domain.req.RegisteredClientRefReq;

/**
 * @author levin
 */
public interface RegisteredClientRefService extends SuperService<RegisteredClientRef> {

    void registeredClient(RegisteredClientRefReq req);

}
