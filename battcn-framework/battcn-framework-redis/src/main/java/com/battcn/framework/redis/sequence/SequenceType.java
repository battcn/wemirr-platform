package com.battcn.framework.redis.sequence;

/**
 * <p>序列类型</p>
 *
 * @author Levin
 * @since 2018/03/20
 */

public enum SequenceType {
    /**
     * 会员编号
     */
    MR("MR", "会员编号"),
    /**
     * 预约单号
     */
    BO("BO", "书籍编号"),;
    String code;
    String desc;

    SequenceType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
