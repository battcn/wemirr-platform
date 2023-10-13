package com.wemirr.platform.tools.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServerModel;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerMapperResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface DynamicReleaseDragPushServerModelMapper extends SuperMapper<DynamicReleaseDragPushServerModel> {

    /**
     * 根据 mobile 查询
     *
     * @param model model
     * @return 查询结果
     */
    List<DynamicReleaseDragPushServerMapperResp> queryDragPushServerByModel(String model);

}
