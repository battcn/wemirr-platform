package com.wemirr.platform.tools.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Levin
 */
@Data
public class DynamicReleaseDragSetting implements java.io.Serializable {

    private Long dragId;
    private Boolean disabled;
    private List<DynamicReleaseGenerateField> fields;
    private Boolean formBtns;
    private String formModel;
    private String formRef;
    private String formRules;
    private Integer gutter;
    private String labelPosition;
    private String size;
    private Integer labelWidth;
    private Integer span;
    private DynamicReleaseGenerateFieldStyle style;

    @Data
    public static class DynamicReleaseGenerateField {

        private Boolean clearable;
        private Boolean disabled;
        private String placeholder;
        private Integer maxlength;
        private Boolean readonly;
        private Integer labelWidth;
        private Integer span;


        @JsonProperty("is-range")
        @JSONField(name = "is-range")
        private Boolean range;
        @JsonProperty("range-separator")
        @JSONField(name = "range-separator")
        private String rangeSeparator;

        @JsonProperty("start-placeholder")
        @JSONField(name = "start-placeholder")
        private String startPlaceholder;
        @JsonProperty("end-placeholder")
        @JSONField(name = "end-placeholder")
        private String endPlaceholder;

        private String format;
        @JsonProperty("value-format")
        @JSONField(name = "value-format")
        private String valueFormat;

        @JsonProperty("prefix-icon")
        @JSONField(name = "prefix-icon")
        private String prefixIcon;
        @JsonProperty("suffix-icon")
        @JSONField(name = "suffix-icon")
        private String suffixIcon;
        @JsonProperty("show-word-limit")
        @JSONField(name = "show-word-limit")
        private Boolean showWordLimit;
        @JsonProperty("__vModel__")
        @JSONField(name = "__vModel__")
        private String column;
        @JsonProperty("__config__")
        @JSONField(name = "__config__")
        private DynamicReleaseGenerateFieldConfig config;
        // __slot__
        @JsonProperty("__slot__")
        @JSONField(name = "__slot__")
        private DynamicReleaseGenerateFieldSlot slot;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicReleaseGenerateFieldConfig {
        private Integer span;
        private Boolean required;
        private Boolean changeTag;
        private Object defaultValue;
        private String document;
        private String label;
        private Integer formId;
        private String renderKey;
        private Boolean showLabel;

        /**
         * "colFormItem"
         */
        @Builder.Default
        private String layout = "colFormItem";
        /**
         * el-input 这类的标签
         */
        private String tag;
        /**
         * 对应 html 中 input type（password,text,textarea）
         */
        private String tagIcon;
        /**
         * default
         */
        private String optionType;

        private List<String> regList;


    }

    @Data
    public static class DynamicReleaseGenerateFieldSlot {
        private String prepend;
        private String append;
        private List<DynamicReleaseGenerateFieldSlotOption> options;
    }

    @Data
    public static class DynamicReleaseGenerateFieldSlotOption {
        private String label;
        private String value;
    }

    @Data
    public static class DynamicReleaseGenerateFieldStyle {
        private String width;
    }
}
