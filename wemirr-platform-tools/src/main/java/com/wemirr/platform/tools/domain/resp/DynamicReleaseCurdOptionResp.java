package com.wemirr.platform.tools.domain.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wemirr.framework.commons.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicReleaseCurdOptionResp {

    private Options options;
    private PageOptions pageOptions;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DynamicReleaseColumnResp> columns;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SelectionRow selectionRow;

    private RowHandle rowHandle;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PageOptions {
        @Builder.Default
        private PageOptionsExport export = PageOptionsExport.builder().build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PageOptionsExport {
        @Builder.Default
        private Boolean local = false;
        @Builder.Default
        private String type = "excel";
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Options {
        @Builder.Default
        private String rowKey = "_id";
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RowHandle {
        @Builder.Default
        private Integer width = 150;
        private List<RowHandleCustom> custom;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class RowHandleCustom {
        @Builder.Default
        private Boolean thin = true;
        private String text;
        @Builder.Default
        private String type = "warning";
        @Builder.Default
        private String size = "small";
        private String emit;
        private String icon;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SelectionRow {
        @Builder.Default
        private String align = "center";
        @Builder.Default
        private Integer width = 45;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnResp {

        private String title;
        private String key;
        private String type;
        private Boolean showOverflowTooltip;
        private DynamicReleaseColumnFormResp form;
        private DynamicReleaseColumnSearchResp search;
        private DynamicReleaseColumnDictResp dict;
        private Boolean disabled;
        private Integer width;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnFormResp {
        private Boolean disabled;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private DynamicReleaseColumnFormRuleResp rules;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private DynamicReleaseColumnComponentResp component;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnComponentResp {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer span;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnSearchResp {
        private Boolean disabled;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnDictResp {
        private String value;
        private String label;
        private String url;
        private List<DynamicReleaseColumnDictData> data;
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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DynamicReleaseColumnFormRuleResp {

        private Boolean required;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String type;

        public String getMessage() {
            return required != null && required ? StringUtils.defaultIfBlank(message, "内容不能为空") : null;
        }
    }


}
