package com.wemirr.platform.tools.domain.converts;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.wemirr.framework.commons.StringUtils;
import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridColumnReq;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseGridColumnResp;

/**
 * @author Levin
 */
public class DynamicReleaseGridColumnConverts {

    public static final DynamicReleaseGridColumnReq2Po DYNAMIC_RELEASE_GRID_COLUMN_REQ_2_PO = new DynamicReleaseGridColumnReq2Po();


    public static final DynamicReleaseGridColumnPo2Resp DYNAMIC_RELEASE_GRID_COLUMN_PO_2_RESP = new DynamicReleaseGridColumnPo2Resp();

    public static class DynamicReleaseGridColumnPo2Resp implements BaseConverts<DynamicReleaseColumn, DynamicReleaseGridColumnResp> {
        @Override
        public DynamicReleaseGridColumnResp convert(DynamicReleaseColumn source) {
            if (source == null) {
                return null;
            }
            DynamicReleaseGridColumnResp resp = DynamicReleaseGridColumnResp.builder()
                    .key(source.getKey()).title(source.getTitle()).type(source.getType())
                    .disabled(source.getDisabled()).build();

            if (StringUtils.isNotBlank(source.getForm())) {
                resp.setForm(JSONUtil.toBean(source.getForm(), DynamicReleaseGridColumnResp.DynamicReleaseGridColumnForm.class));
            }
            if (StringUtils.isNotBlank(source.getSearch())) {
                resp.setSearch(JSONUtil.toBean(source.getSearch(), DynamicReleaseGridColumnResp.DynamicReleaseGridColumnSearch.class));
            }
            if (StringUtils.isNotBlank(source.getDict())) {
                resp.setDict(JSONUtil.toBean(source.getDict(), DynamicReleaseGridColumnResp.DynamicReleaseGridColumnDict.class));
            }
            return resp;
        }
    }


    public static class DynamicReleaseGridColumnReq2Po implements BaseConverts<DynamicReleaseGridColumnReq, DynamicReleaseColumn> {

        @Override
        public DynamicReleaseColumn convert(DynamicReleaseGridColumnReq source) {
            if (source == null) {
                return null;
            }
            final DynamicReleaseColumn releaseColumn = DynamicReleaseColumn.builder()
                    .key(source.getKey()).title(source.getTitle()).disabled(source.getDisabled())
                    .type(source.getType())
                    .build();
            if (source.getForm() != null) {
                releaseColumn.setForm(JSON.toJSONString(source.getForm()));
            }
            if (source.getSearch() != null) {
                releaseColumn.setSearch(JSON.toJSONString(source.getSearch()));
            }
            if (source.getDict() != null) {
                releaseColumn.setDict(JSON.toJSONString(source.getDict()));
            }
            return releaseColumn;
        }
    }

}
