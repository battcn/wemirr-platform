package com.wemirr.platform.tms.domain.enums;

import com.wemirr.platform.tms.domain.resp.RuleOptionResp;
import com.wemirr.platform.tms.domain.resp.RuleSymbolResp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * @author Levin
 */
@AllArgsConstructor
@Getter
public enum RuleOption {

    /**
     * 运输方式
     */
    ORDER_TRANSPORT_TYPE(1, "运输方式", "tms_order", "transport_type", 1, 1, true, false),
    ORDER_QUANTITY(2, "总数量", "tms_order", "quantity", 3, 0, true, true),
    ORDER_GROSS_WEIGHT(3, "总毛重", "tms_order", "gross_weight", 3, 0, true, true),
    ORDER_VOLUME(4, "总体积", "tms_order", "volume", 3, 0, true, true),

    SENDER_COMPANY(5, "发货公司", "tms_order_sender", "company", 4, 0, true, false),
    SENDER_NAME(6, "发货联系人", "tms_order_sender", "name", 4, 0, true, false),
    SENDER_PROVINCE(7, "发货省份", "tms_order_sender", "province", 4, 0, true, false),
    SENDER_CITY(8, "发货城市", "tms_order_sender", "city", 4, 0, true, false),
    SENDER_DISTRICT(9, "发货区县", "tms_order_sender", "district", 4, 0, true, false),

    CONSIGNEE_COMPANY(10, "收货公司", "tms_order_consignee", "company", 4, 0, true, false),
    CONSIGNEE_NAME(11, "收货联系人", "tms_order_consignee", "name", 4, 0, true, false),
    CONSIGNEE_PROVINCE(12, "收货省份", "tms_order_consignee", "province", 4, 0, true, false),
    CONSIGNEE_CITY(13, "收货城市", "tms_order_consignee", "city", 4, 0, true, false),
    CONSIGNEE_DISTRICT(14, "收货区县", "tms_order_consignee", "district", 4, 0, true, false),


    ;

    private final Integer id;
    private final String name;
    private final String subName;
    private final String colName;
    private final Integer type;
    private final Integer selectType;
    private final boolean isCondition;
    private final boolean isSolution;

    public final static int TYPE_ENUMERATION = 1;
    public final static int TYPE_TF = 2;
    public final static int TYPE_INPUT_NUM = 3;
    public final static int TYPE_INPUT_STR = 4;

    public final static int TRANSPORT_TYPE = 1;

    public static List<RuleOptionResp> getOptionList(boolean isCondition, boolean isSolution) {
        List<RuleOptionResp> list = new ArrayList<>();
        for (RuleOption optionEnum : RuleOption.values()) {
            if (optionEnum.isCondition() == isCondition && optionEnum.isSolution() == isSolution) {
                RuleOptionResp dto = RuleOptionResp.builder()
                        .id(optionEnum.getId())
                        .name(optionEnum.getName())
                        .tableName(optionEnum.getSubName())
                        .colName(optionEnum.getColName())
                        .type(optionEnum.getType())
                        .selectType(optionEnum.getSelectType())
                        .isCondition(optionEnum.isCondition())
                        .isSolution(optionEnum.isSolution())
                        .build();
                list.add(dto);
            }
        }
        return list;
    }

    public static String getOptionNameById(Integer id) {
        for (RuleOption optionEnum : RuleOption.values()) {
            if (optionEnum.getId().equals(id)) {
                return optionEnum.getName();
            }
        }
        return null;
    }

    public static RuleOption getOptionById(Integer id) {
        for (RuleOption optionEnum : RuleOption.values()) {
            if (optionEnum.getId().equals(id)) {
                return optionEnum;
            }
        }
        return null;
    }

    public static RuleOption getOptionByName(String name) {
        for (RuleOption optionEnum : RuleOption.values()) {
            if (optionEnum.getName().equals(name)) {
                return optionEnum;
            }
        }
        return null;
    }

    public static RuleOption getOptionByColName(String colName) {
        for (RuleOption optionEnum : RuleOption.values()) {
            if (optionEnum.getColName().equals(colName)) {
                return optionEnum;
            }
        }
        return null;
    }

    public static List<String> getOptionNameList() {
        List<String> optionNames = new ArrayList<>();
        for (RuleOption optionEnum : RuleOption.values()) {
            optionNames.add(optionEnum.getName());
        }
        return optionNames;
    }

    public static List<String> getOptionCharList() {
        Set<String> optionNameSets = new HashSet<>();
        for (RuleOption optionEnum : RuleOption.values()) {
            String name = optionEnum.getName();
            String[] s = name.split("");
            optionNameSets.addAll(Arrays.asList(s));
        }
        List<String> optionNames = new ArrayList<>(optionNameSets);
        return optionNames;
    }

    public static String getColNameById(Integer id) {
        for (RuleOption optionEnum : RuleOption.values()) {
            if (optionEnum.getId().equals(id)) {
                return optionEnum.getColName();
            }
        }
        return null;
    }

    public static String getColNameByOptionName(String optionName) {
        for (RuleOption optionEnum : RuleOption.values()) {
            if (optionEnum.getName().equals(optionName)) {
                return optionEnum.getColName();
            }
        }
        return null;
    }

    public static List<RuleSymbolResp> getSymbolListByType() {
        List<RuleSymbolResp> list = new ArrayList<>();
        for (RuleOption ruleOption : RuleOption.values()) {
            if (ruleOption.isSolution()) {
                RuleSymbolResp dto = RuleSymbolResp.builder()
                        .id(ruleOption.getId())
                        .name(ruleOption.getName())
                        .symbol(ruleOption.getName())
                        .type(3)
                        .isShow(1)
                        .build();
                list.add(dto);
            }
        }
        return list;
    }

}
