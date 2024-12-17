package com.wemirr.platform.tms.domain.req;

import cn.hutool.core.bean.BeanUtil;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.platform.tms.domain.entity.TmsOrderAddress;
import com.wemirr.platform.tms.domain.entity.TmsOrderSku;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * @author Levin
 */
@Data
@Schema(name = "OrderSaveReq")
public class OrderSaveReq {

    @NotBlank(message = "客户单号不能为空")
    @Schema(description = "客户单号")
    private String customNo;

    @Schema(description = "运输单号")
    private String waybillNo;

    @NotNull(message = "项目不能为空")
    @Schema(description = "项目ID")
    private Long projectId;

    @Schema(description = "项目名称")
    private String projectName;

    @NotNull(message = "运输方式不能为空")
    @Schema(description = "运输方式")
    private Integer transportType;

    @Schema(description = "货物信息")
    private String cargoDesc;

    @Schema(description = "单位")
    private String qtyUnit;

    @Schema(description = "总数量")
    private Integer quantity;

    @Schema(description = "订单明细数量")
    private Integer detailQuantity;

    @Schema(description = "毛重")
    private BigDecimal grossWeight;

    @Schema(description = "体积")
    private BigDecimal volume;

    @Schema(description = "声明价值")
    private BigDecimal declareValue;

    @Schema(description = "预计发货时间")
    private Instant etd;

    @Schema(description = "预计到达时间")
    private Instant eta;

    @Schema(description = "订单备注")
    private String remark;

    @Schema(description = "运输线路id")
    private Integer transportLineId;

    @Schema(description = "是否有电子回单")
    private Boolean hasEpod;

    @Schema(description = "数据来源")
    private Integer sourceType;

    @Schema(description = "发货人信息")
    private OrderContactSaveReq senderInfo;

    @Schema(description = "收货人信息")
    private OrderContactSaveReq receiverInfo;

    @Schema(description = "货物信息")
    private List<OrderSkuSaveReq> skus;

    @Schema(description = "文件信息")
    private List<OrderFileSaveReq> files;


    public TmsOrderAddress buildOrderAddress(Long orderId, Integer type, OrderContactSaveReq address) {
        if (address == null) {
            throw CheckedException.notFound("地址信息不能为");
        }
        final TmsOrderAddress orderAddress = BeanUtil.toBean(address, TmsOrderAddress.class);
        orderAddress.setOrderId(orderId);
        orderAddress.setType(type);
        orderAddress.setDeleted(false);
        return orderAddress;
    }


    public List<TmsOrderSku> buildOrderSkus(Long orderId) {
        if (skus == null) {
            return null;
        }
        return skus.stream().map(sku -> {
            final TmsOrderSku bean = BeanUtil.toBean(sku, TmsOrderSku.class);
            bean.setOrderId(orderId);
            bean.setDeleted(false);
            return bean;
        }).toList();
    }

}