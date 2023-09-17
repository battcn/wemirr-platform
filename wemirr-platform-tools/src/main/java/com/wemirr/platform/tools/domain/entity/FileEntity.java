package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Levin
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_file")
public class FileEntity extends SuperEntity<Long> {

    private Long tenantId;

    private String contentType;

    @TableField("`location`")
    private String location;

    @TableField("`ip`")
    private String ip;

    @TableField("`engine`")
    private String engine;

    private String engineVersion;

    private String os;

    @TableField("`bucket`")
    private String bucket;

    private String originName;

    private String targetName;

    @TableField("`size`")
    private Long size;

    /**
     * 文件的完整路径
     */
    private String fullUrl;

    private String mappingPath;

    /**
     * 对应存储的扩展字段
     */
    private String extend;


}
