package com.wemirr.platform.tools.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.boot.entity.PageRequest;
import com.wemirr.platform.tools.domain.entity.ExportExcelReq;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQueryDrag;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseLogTrackResp;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
public interface DynamicReleaseService<T> {


    DynamicReleaseCurdOptionResp curdOptions(String model);

    void save(String model, Map<String, Object> map);

    List<DynamicReleaseLogTrackResp<T>> logTrack(String model, T id);

    void pushTrack(String model, T id);

    void deleteById(String model, T id);

    void updateById(String model, T id, Map<String, Object> map);

    IPage<?> pages(String model, PageRequest request, Map<String, Object> params);

    IPage<?> pages(String model, DynamicReleaseQueryDrag drag);

    default String tableName(String model) {
        return model;
    }

    void batchDeleteByKeys(String model, List<T> ids);

    void exportExcel(String model, ExportExcelReq req, HttpServletResponse response);


}
