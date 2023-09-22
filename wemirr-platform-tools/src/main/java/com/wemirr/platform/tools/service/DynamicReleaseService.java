package com.wemirr.platform.tools.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.platform.tools.domain.entity.ExportExcelReq;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQueryDrag;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseLogTrackResp;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Levin
 */
public interface DynamicReleaseService<T> {

    /**
     * 获取 curdOptions
     *
     * @param model model
     * @return 查询结果
     */
    DynamicReleaseCurdOptionResp curdOptions(String model);

    /**
     * 保存
     *
     * @param model model
     * @param map   map
     */
    void save(String model, Map<String, Object> map);

    /**
     * 日志跟踪
     *
     * @param model model
     * @param id    id
     * @return 查询结果
     */
    List<DynamicReleaseLogTrackResp<T>> logTrack(String model, T id);

    /**
     * 推送信息
     *
     * @param model model
     * @param id    id
     */
    void pushTrack(String model, T id);

    /**
     * 根据ID删除
     *
     * @param model model
     * @param id    id
     */
    void deleteById(String model, T id);

    /**
     * 根据ID修改
     *
     * @param model model
     * @param id    id
     * @param map   map
     */
    void updateById(String model, T id, Map<String, Object> map);

    /**
     * 带条件分页查询
     *
     * @param model   model
     * @param request request
     * @param params  params
     * @return 查询结果
     */
    IPage<?> pages(String model, PageRequest request, Map<String, Object> params);

    /**
     * 带条件分页查询
     *
     * @param model model
     * @param drag  drag
     * @return 查询结果
     */
    IPage<?> pages(String model, DynamicReleaseQueryDrag drag);

    /**
     * 根据 model 获取表名
     *
     * @param model model
     * @return 结果
     */
    default String tableName(String model) {
        return model;
    }

    /**
     * 批量删除 keys
     *
     * @param model models
     * @param ids   ids
     */
    void batchDeleteByKeys(String model, List<T> ids);

    /**
     * 导出 excel
     *
     * @param model    model
     * @param req      req
     * @param response 响应结果
     */
    void exportExcel(String model, ExportExcelReq req, HttpServletResponse response);


}
