package com.wemirr.platform.authority.repository.baseinfo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wemirr.framework.db.dynamic.annotation.TenantDS;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.authority.domain.baseinfo.entity.Station;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 岗位
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
@TenantDS
@Repository
public interface StationMapper extends SuperMapper<Station> {

    /**
     * 分页查询岗位信息（含角色）
     *
     * @param page    page
     * @param wrapper wrapper
     * @return 查询结果
     */
    IPage<Station> findStationPage(IPage<?> page, @Param(Constants.WRAPPER) Wrapper<Station> wrapper);

}
