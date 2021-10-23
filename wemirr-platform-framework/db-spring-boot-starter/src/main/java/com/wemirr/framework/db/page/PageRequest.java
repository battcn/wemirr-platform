package com.wemirr.framework.db.page;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wemirr.framework.commons.StringUtils;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页参数
 *
 * @author Levin
 * @since 2020-07-08
 */
@Slf4j
@Data
public class PageRequest {

    @Parameter(description = "当前页码", example = "1")
    private long current = 1;

    @Parameter(description = "页面大小", example = "20")
    private long size = 20;

    @Parameter(description = "排序字段")
    private String column;

    @Parameter(description = "排序规则")
    private Boolean asc = true;


    @JsonIgnore
    public <T> Page<T> buildPage() {
        PageRequest params = this;
        if (StringUtils.isBlank(params.getColumn())) {
            return new Page<>(params.getCurrent(), params.getSize());
        }
        Page<T> page = new Page<>(params.getCurrent(), params.getSize());
        List<OrderItem> orders = new ArrayList<>();
        // 简单的 驼峰 转 下划线
        String column = StrUtil.toUnderlineCase(params.getColumn());
        orders.add(params.getAsc() ? OrderItem.asc(column) : OrderItem.desc(column));
        page.setOrders(orders);
        return page;
    }
}
