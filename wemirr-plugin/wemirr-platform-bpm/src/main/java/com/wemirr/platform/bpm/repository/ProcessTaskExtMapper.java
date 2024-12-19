package com.wemirr.platform.bpm.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.bpm.domain.entity.ProcessTaskExt;
import com.wemirr.platform.bpm.domain.req.ProcessTaskPageReq;
import com.wemirr.platform.bpm.domain.resp.ProcessTaskExtResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Levin
 */
@Repository
public interface ProcessTaskExtMapper extends SuperMapper<ProcessTaskExt> {

    /**
     * 查看任务列表
     *
     * @param page 分页对象
     * @param req  req
     * @return 分页结果
     */
    Page<ProcessTaskExtResp> pageList(@Param("page") Page<ProcessTaskExt> page, @Param("req") ProcessTaskPageReq req);
}
