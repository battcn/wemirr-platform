package com.wemirr.platform.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.ext.SuperService;
import com.wemirr.platform.authority.domain.entity.baseinfo.Station;
import com.wemirr.platform.authority.domain.req.StationPageReq;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
public interface StationService extends SuperService<Station> {
    /**
     * 按权限查询岗位的分页信息
     *
     * @param req   req
     * @return Station
     */
    IPage<Station> pageList(StationPageReq req);
}
