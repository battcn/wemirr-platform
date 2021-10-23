package com.wemirr.platform.authority.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatis.SuperService;
import com.wemirr.framework.db.page.PageRequest;
import com.wemirr.platform.authority.domain.dto.StationPageDTO;
import com.wemirr.platform.authority.domain.entity.baseinfo.Station;

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
     * @param params params
     * @param data data
     * @return Station
     */
    IPage<Station> findStationPage(PageRequest params, StationPageDTO data);
}
