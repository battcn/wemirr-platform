package com.wemirr.platform.tools.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicReleaseGridColumnResp {

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
        private Boolean disabled;
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
