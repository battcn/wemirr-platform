package com.wemirr.platform.wms.matedata.mapper;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.wms.matedata.domain.entity.Material;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物料表 Mapper 接口
 * </p>
 *
 * @author ddCat
 * @since 2024-06-18
 */
@Repository
public interface MaterialMapper extends SuperMapper<Material> {

}
