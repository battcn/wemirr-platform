package com.wemirr.platform.suite.file.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xiao1
 * @date 2024-12
 * @description 文件存储配置
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_file_storage_setting")
public class FileStorageSetting extends SuperEntity<Long> {

    /**
     * 平台名称 【teantId-平台名称】 唯一值，用于x-file区分平台
     */
    private String platform;
    /**
     * 平台名称
     */
    private String platformV;
    /**
     * 是否启用存储 [true:启用;false:禁用]
     * 同个租户下只能开启一个配置
     */
    private Boolean enableStorage;
    /**
     * 访问key
     */
    private String accessKey;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 区域
     */
    private String region;
    /**
     * 访问域名
     */
    private String domain;
    /**
     * 桶名称
     */
    private String bucketName;
    /**
     * 基础路径
     */
    private String basePath;
    /**
     * 连接地址
     */
    private String endPoint;

    private Long tenantId;

    //        /**
//         *       - platform: amazon-s3-1 # 存储平台标识
//         *         enable-storage: true  # 启用存储
//         *         access-key: Pk9KwzuidZZ6IyOleiza
//         *         secret-key: OfwecI2Nf0Q7m4T97gp1TpjuDnhp0j6JgiQJ7c31
//         *         region: null # 与 end-point 参数至少填一个
//         *         end-point: http://192.168.241.130:9000 # 与 region 参数至少填一个
//         *         bucket-name: test
//         *         domain: http://192.168.241.130:9000/test/ # 访问域名，注意“/”结尾，例如：https://abc.hn-bkt.clouddn.com/
//         *         base-path: test/ # 基础路径
//         */

}
