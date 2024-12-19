package com.wemirr.platform.bpm.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskHistory;
import com.wemirr.platform.bpm.domain.req.ProcessTaskPageReq;
import com.wemirr.platform.bpm.domain.resp.ProcessTaskHistoryResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface ProcessTaskHistoryMapper extends SuperMapper<ProcessTaskHistory> {

    /**
     * 分页查询
     *
     * @param page page
     * @param req  req
     * @return 查询结果
     */
    Page<ProcessTaskHistoryResp> pageList(@Param("page") Page<ProcessTaskHistory> page, @Param("req") ProcessTaskPageReq req);
}
