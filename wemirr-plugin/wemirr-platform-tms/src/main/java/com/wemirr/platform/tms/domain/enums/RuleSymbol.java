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
public enum RuleSymbol {

    /**
     * 规则
     */
    EQUAL(1, "=", "=", "完全相等", 4, 1),
    MORE_THAN(2, ">", ">", "大于", 4, 1),
    MORE_AND_EQ(3, "≧", "≧", "大于等于", 4, 1),
    LESS_THAN(4, "<", "<", "小于", 4, 1),
    LESS_AND_EQ(5, "≦", "≦", "小于等于", 4, 1),
    NOT_EQUAL(6, "≠", "≠", "不等于", 4, 1),
    MT_LT(7, "<,<", "<,<", "在...之间", 4, 1),
    MTEQ_LT(8, "≦,<", "≦,<", "在...之间,包括左边", 4, 1),
    MT_LTEQ(9, "<,≦", "<,≦", "在...之间,包括右边", 4, 1),
    MTEQ_LTEQ(10, "≦,≦", "≦,≦", "在...之间,包括两边", 4, 1),
    CONTAIN(11, "包含", "包含", "字符包含", 4, 1),
    NOT_CONTAIN(12, "不包含", "不包含", "字符不包含", 4, 1),
    BEGIN(13, "以...开头", "START", "开头模糊匹配", 4, 1),
    END(14, "以...结尾", "END", "结尾模糊匹配", 4, 1),
    NOT_BEGIN(15, "不以...开头", "!START", "开头模糊匹配", 4, 1),
    NOT_END(16, "不以...结尾", "!END", "结尾模糊匹配", 4, 1),
    PLUS(17, "+", "+", "加法", 2, 1),
    MINUS(18, "-", "-", "减法", 2, 1),
    MULTIPLY(19, "×", "*", "乘法", 2, 1),
    DIVIDE(20, "÷", "/", "除法", 2, 1),
    POWER(21, "乘方", "^", "乘方", 2, 0),
    SQUARE(22, "²", "²", "平方", 2, 0),
    CUBE(23, "³", "³", "立方", 2, 0),
    ROOT(24, "方根", "√", "根方", 2, 0),
    PAREN(25, "()", "()", "圆括号", 2, 0),
    PAREN_LEFT(26, "(", "(", "左圆括号", 2, 1),
    PAREN_RIGHT(27, ")", ")", "右圆括号", 2, 1),
    M_PAREN(28, "[]", "[]", "方括号", 2, 0),
    M_PAREN_LEFT(29, "[", "[", "左方括号", 2, 0),
    M_PAREN_RIGHT(30, "]", "]", "右方括号", 2, 0),
    ROUND_UP(31, "进位", "⌉", "向上取整、进位", 2, 0),
    ROUND_DOWN(32, "截尾", "⌋", "向下取整、抹零", 2, 0),
    PERCENTAGE(33, "％", "％", "百分号", 2, 0),
    PERMILLAGE(34, "‰", "‰", "千分号", 2, 0),
    IS_IN(35, "等于其一", "等于其一", "满足其中一个选项", 4, 1),
    NOT_IN(36, "不等于其一", "不等于其一", "不满足其中任一选项", 4, 1),

    ZERO(50, "零", "0", "零", 1, 1),
    ONE(51, "一", "1", "一", 1, 1),
    TWO(52, "二", "2", "二", 1, 1),
    THREE(53, "三", "3", "三", 1, 1),
    FOUR(54, "四", "4", "四", 1, 1),
    FIVE(55, "五", "5", "五", 1, 1),
    SIX(56, "六", "6", "六", 1, 1),
    SEVEN(57, "七", "7", "七", 1, 1),
    EIGHT(58, "八", "8", "八", 1, 1),
    NINE(59, "九", "9", "九", 1, 1),
    TEN(60, "小数点", ".", "小数点", 1, 1),
    ;

    private final Integer id;
    private final String name;
    private final String symbol;
    private final String remark;
    private final Integer type;
    private final Integer isShow;

    public final static int NUMBER = 1;
    public final static int OPERATOR = 2;
    public final static int ORDER_FIELD = 3;
    public final static int ORDER = 3;

    public final static int SHOW = 1;
    public final static int HIDE = 0;

    public static List<RuleSymbolResp> getSymbolListByType(int type, int isShow) {
        List<RuleSymbolResp> list = new ArrayList<>();
        for (RuleSymbol symbolEnum : RuleSymbol.values()) {
            if (symbolEnum.getType() == type && symbolEnum.getIsShow() == isShow) {
                RuleSymbolResp dto = RuleSymbolResp.builder()
                        .id(symbolEnum.getId())
                        .name(symbolEnum.getName())
                        .symbol(symbolEnum.getSymbol())
                        .remark(symbolEnum.getRemark())
                        .type(symbolEnum.getType())
                        .isShow(symbolEnum.getIsShow())
                        .build();
                list.add(dto);
            }
        }
        return list;
    }

    public static List<RuleSymbolResp> getSymbolList() {
        List<RuleSymbolResp> list = new ArrayList<>();
        for (RuleSymbol symbolEnum : RuleSymbol.values()) {
            RuleSymbolResp dto = RuleSymbolResp.builder()
                    .id(symbolEnum.getId())
                    .name(symbolEnum.getName())
                    .symbol(symbolEnum.getSymbol())
                    .type(symbolEnum.getType())
                    .isShow(symbolEnum.getIsShow())
                    .build();
            list.add(dto);
        }
        return list;
    }

    public static String getSymbolNameById(Integer id) {
        for (RuleSymbol symbolEnum : RuleSymbol.values()) {
            if (symbolEnum.getId().equals(id)) {
                return symbolEnum.getName();
            }
        }
        return null;
    }

    public static RuleSymbolResp getSymbol(RuleSymbol ruleSymbol) {
        for (RuleSymbol symbolEnum : RuleSymbol.values()) {
            if (symbolEnum.equals(ruleSymbol)) {
                return RuleSymbolResp.builder()
                        .id(symbolEnum.getId())
                        .name(symbolEnum.getName())
                        .symbol(symbolEnum.getSymbol())
                        .remark(symbolEnum.getRemark())
                        .type(symbolEnum.getType())
                        .isShow(symbolEnum.getIsShow())
                        .build();
            }
        }
        return null;
    }

}
