
package com.wemirr.platform.bpm.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.bpm.domain.entity.ProcessDeployHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 流程模型历史(ProcessDeployHistory)持久层
 *
 * @author Levin
 */
@Repository
public interface ProcessDeployHistoryMapper extends SuperMapper<ProcessDeployHistory> {

    /**
     * 通过历史模型id物理删除
     *
     * @param modelId ${@link Long} 历史模型id
     * @return int ${@link Integer} 成功状态:0-失败,1-成功
     * @author Levin
     */
    int physicalDeleteByModelId(@Param("modelId") Long modelId);
}
