package com.wemirr.platform.suite.online.dialect;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class EpicDesignerField {

    @Schema(description = "字段ID")
    private String id;

    @Schema(description = "字段标题")
    private String label;

    @Schema(description = "字段类型")
    private String type;

    @Schema(description = "字段名")
    private String field;

    @Schema(description = "是否可以输入")
    private Boolean input;

    private ComponentProps componentProps;


    @Data
    @NoArgsConstructor
    public static class ComponentProps {
        @Schema(description = "提示语")
        private String placeholder;

        @Schema(description = "字典")
        private List<Option> options;

    }
    @Data
    public static class Option {
        @Schema(description = "字典标题")
        private String label;
        @Schema(description = "字典值")
        private String value;
    }
}
