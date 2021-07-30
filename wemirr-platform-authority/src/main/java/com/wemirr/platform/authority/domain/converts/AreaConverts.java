package com.wemirr.platform.authority.domain.converts;

import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.platform.authority.domain.dto.AreaEntityDTO;
import com.wemirr.platform.authority.domain.entity.common.AreaEntity;
import org.springframework.beans.BeanUtils;

/**
 * @author Levin
 */
public class AreaConverts {

    public static final AreaConverts.AreaDto2PoConverts AREA_DTO_2_PO_CONVERTS = new AreaConverts.AreaDto2PoConverts();


    public static class AreaDto2PoConverts implements BaseConverts<AreaEntityDTO, AreaEntity> {
        @Override
        public AreaEntity convert(AreaEntityDTO source) {
            if (source == null) {
                return null;
            }
            AreaEntity target = new AreaEntity();
            BeanUtils.copyProperties(source, target);
            target.setId(source.getId());
            return target;
        }
    }

}
