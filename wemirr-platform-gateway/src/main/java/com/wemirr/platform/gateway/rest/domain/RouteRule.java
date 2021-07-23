package com.wemirr.platform.gateway.rest.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Levin
 */
@Data
public class RouteRule {

    @NotBlank(message = "ID不能为空")
    private String id;
    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "URI不能为空")
    private String uri;
    private Boolean status;
    private List<Predicate> predicates;
    private List<Filter> filters;
    private String description;
    private LocalDateTime createdTime;
    private Integer order;

    @Data
    public static class Filter {
        @NotBlank(message = "名称不能为空")
        private String name;
        private List<FilterArg> args;
        @Data
        public static class FilterArg{
            private String key;
            private String value;
        }
    }

    @Data
    public static class Predicate {
        @NotBlank(message = "名称不能为空")
        private String name;
        private List<String> args;
    }
}
