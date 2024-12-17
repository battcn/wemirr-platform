package com.wemirr.platform.tms.domain.enums;

import com.wemirr.platform.tms.domain.resp.RuleSymbolResp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levin
 */
@AllArgsConstructor
@Getter
public enum TransportType {

    /**
     * 运输类型
     */
    LTL(1, "零担"),
    FTL(2, "整车"),
    TRAIN(3, "铁运"),
    AIRLINE(4, "空运"),
    EXPRESS(5, "快递"),
    ;

    private final Integer id;
    private final String name;

    public static String getNameById(Integer id) {
        for (TransportType qtyUnit : TransportType.values()) {
            if (qtyUnit.getId().equals(id)) {
                return qtyUnit.getName();
            }
        }
        return null;
    }

    public static Integer getIdByName(String name) {
        for (TransportType qtyUnit : TransportType.values()) {
            if (qtyUnit.getName().equals(name)) {
                return qtyUnit.getId();
            }
        }
        return null;
    }

    public static List<RuleSymbolResp> getSymbolList() {
        List<RuleSymbolResp> respList = new ArrayList<>();
        for (TransportType transportType : TransportType.values()) {
            RuleSymbolResp resp = RuleSymbolResp.builder()
                    .id(transportType.getId())
                    .name(transportType.getName())
                    .build();
            respList.add(resp);
        }
        return respList;
    }

}
