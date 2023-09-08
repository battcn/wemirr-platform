package com.wemirr.framework.storage.domain;


import com.wemirr.framework.storage.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private Object tenantId;

    private Object userId;

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
        now_date_mouth_day,

        /**
         * 租户当前日期策略
         */
        tenant_now_date_mouth_day;
    }


    /**
     * 获取目标名字
     *
     * @return 目标名称
     */
    public String buildTargetName() {
        if (StringUtils.isBlank(this.getOriginName())) {
            throw new RuntimeException("originName 不能为空");
        }
        final PrefixRule rule = this.getRule();
        if (rule == null) {
            return FileUtils.targetName(this.isRandomName(), this.getPrefix(), this.getOriginName());
        }
        String prefix;
        switch (rule) {
            case now_date_mouth:
                prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
                break;
            case now_date_mouth_day:
                prefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                break;
            case tenant_now_date_mouth_day:
                if (tenantId == null || userId == null) {
                    throw new RuntimeException("tenantId or userId not null");
                }
                prefix = tenantId + "/" + userId + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                break;
            case none:
                prefix = this.getPrefix();
                break;
            default:
                return this.getPrefix();
        }
        return FileUtils.targetName(this.isRandomName(), prefix, this.getOriginName());
    }
}
