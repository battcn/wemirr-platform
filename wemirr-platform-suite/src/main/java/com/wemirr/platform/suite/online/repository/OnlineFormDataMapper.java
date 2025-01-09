package com.wemirr.platform.suite.online.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import com.wemirr.platform.suite.online.domain.entity.OnlineFormData;
import com.wemirr.platform.suite.online.domain.req.OnlineFormDesignerPageReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author levin
 */
@Repository
public interface OnlineFormDataMapper extends SuperMapper<OnlineFormData> {

    IPage<OnlineFormData> pageList(@Param("page") Page<?> page, @Param("req") OnlineFormDesignerPageReq req);

}
