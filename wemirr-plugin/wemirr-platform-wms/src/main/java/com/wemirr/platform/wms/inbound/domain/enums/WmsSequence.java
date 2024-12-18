package com.wemirr.platform.wms.inbound.domain.enums;

import com.wemirr.framework.redis.plus.sequence.Sequence;
import lombok.RequiredArgsConstructor;

/**
 * @author Levin
 */
@RequiredArgsConstructor
public enum WmsSequence implements Sequence {

    /**
     * 规费编号
     */
    DEMAND_NO("tenant:wms:demand", "XQ"),


    BRAND_NO("tenant:wms:brand", "BR"),

    WAREHOUSE_NO("tenant:wms:warehouse", "WH"),

    /**
     * 供应商编号
     */
    SUPPLIER_NO("tenant:wms:supplier", "EWS"),

    /**
     * 承运商
     */
    CARRIER_NO("tenant:wms:carrier", "EWC"),

    /**
     * 物料分类
     */
    MATERIAL_CATEGORY_NO("tenant:wms:material_category", "MC"),

    /**
     * 仓库站点
     */
    WAREHOUSE_STATION_NO("tenant:wms:warehouse_station", "WS"),

    APPOINTMENT_NO("tenant:wms:appointment", "WA"),

    PRODUCT_NO("tenant:wms:product", "WP"),
    /**
     * 收货计划单号
     */
    RECEIVING_PLAN("tenant:wms:receiving_plan", "RP"),
    /**
     * 入库单号
     */
    INVENTORY_RECEIPT("tenant:wms:inventory_receipt", "IR"),
    /**
     * 容器日志
     */
    CONTAINER_TASK_NO("tenant:wms:container_task", "CT"),


    ;

    public final String key;
    public final String prefix;

    @Override
    public String key() {
        return key;
    }

    @Override
    public String prefix() {
        return prefix;
    }
}
