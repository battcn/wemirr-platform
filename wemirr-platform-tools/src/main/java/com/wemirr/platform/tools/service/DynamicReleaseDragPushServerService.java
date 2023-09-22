package com.wemirr.platform.tools.service;

import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServer;
import com.wemirr.platform.tools.domain.req.BatchKey;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerModelResp;

/**
 * @author Levin
 */
public interface DynamicReleaseDragPushServerService extends SuperService<DynamicReleaseDragPushServer> {


    /**
     * 根据 pushId 查询动态发布模型
     *
     * @param pushId pushId
     * @return 查询结果
     */
    DynamicReleaseDragPushServerModelResp queryModelByPushId(Long pushId);

    /**
     * 绑定
     *
     * @param id  id
     * @param ids 绑定内容
     */
    void binding(Long id, BatchKey<Long> ids);
}
