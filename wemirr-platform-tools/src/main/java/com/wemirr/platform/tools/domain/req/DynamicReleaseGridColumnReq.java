package com.wemirr.platform.tools.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Levin
 */
@Data
public class DynamicReleaseGridColumnReq {

    @NotBlank(message = "字段不能为空")
    private String key;
    @NotBlank(message = "标题不能为空")
    private String title;
    private Boolean disabled;
    @NotBlank(message = "类型不能为空")
    private String type;
    private DynamicReleaseGridColumnForm form;
    private DynamicReleaseGridColumnSearch search;
    private DynamicReleaseGridColumnDict dict;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnForm {
        private Boolean disabled;
        private DynamicReleaseGridColumnFormRule rules;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnSearch {
        private boolean disabled;
        private String expression;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnDict {
        private String code;
        private String url;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGridColumnFormRule {
        private Boolean required;
        private String type;
    }
}
