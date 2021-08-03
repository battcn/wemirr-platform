package com.wemirr.framework.storage.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * 请求参数
 *
 * @author Levin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageRequest implements java.io.Serializable {

    private static final long serialVersionUID = -3607203589216325639L;


    private String bucket;

    private String fileName;

    private boolean randomName = true;
    private PrefixRule rule;
    private String prefix;

    /**
     * content 与 inputStream 二选一
     */
    private byte[] content;
    /**
     * inputStream 与 content 二选一
     */
    private InputStream inputStream;

    private String contentType;


    public enum PrefixRule {
        /**
         * 无规则
         */
        none,
        /**
         * 当前日期+月份
         */
        now_date_mouth,

        /**
         * 当前年月日
         */
        now_date_mouth_day;
    }

}
