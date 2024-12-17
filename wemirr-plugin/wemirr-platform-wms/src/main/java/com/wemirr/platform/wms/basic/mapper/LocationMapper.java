package com.wemirr.platform.wms.basic.mapper;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.wms.basic.domain.entity.Location;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 储位表 Mapper 接口
 * </p>
 *
 * @author ddCat
 * @since 2024-06-17
 */
@Repository
public interface LocationMapper extends SuperMapper<Location> {

}
