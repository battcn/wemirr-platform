package com.wemirr.platform.tools.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
public class ExportExcelReq {

    private List<DynamicReleaseColumnReq> columns;


    @Data
    public static class DynamicReleaseColumnReq {

        private String title;
        private String key;
        private String type;
        private Boolean showOverflowTooltip;
        private DynamicReleaseColumnFormReq form;
        private DynamicReleaseColumnDictReq dict;
        private Boolean show;
        private Boolean disabled;
        private Integer width;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnFormReq {
        private Boolean disabled;
        private DynamicReleaseCurdOptionResp.DynamicReleaseColumnFormRuleResp rules;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnDictReq {
        private String value;
        private String label;
        private String url;
        private List<DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictData> data;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnDictData {
        private String value;
        private String label;
    }

}
