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

    /**
     * 如果为空则取应用配置的
     */
    private String bucket;

    /**
     * 原始文件名称
     */
    private String originName;

    /**
     * 如果为 true 则会随机生成文件名
     */
    @Builder.Default
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
         * 无规则 默认提取 prefix 否则自动生成前缀
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
