package com.wemirr.platform.tools.mapper;

import com.wemirr.framework.boot.SuperMapper;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDragPushServerModel;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerMapperResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface DynamicReleaseDragPushServerModelMapper extends SuperMapper<DynamicReleaseDragPushServerModel> {

    List<DynamicReleaseDragPushServerMapperResp> queryDragPushServerByModel(String model);

}
