package com.wemirr.platform.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wemirr.framework.commons.entity.SuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xiao1
 * @since 2024-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("c_generate_table_column")
public class GenerateTableColumn extends SuperEntity<Long> {

    /**
     * 表名称 [ alipan 是用表名称进行关联的 ]
     */
    private String tableName;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 注释
     */
    private String comment;
    /**
     * 属性类型
     */
    private String propertyType;
    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 属性包
     */
    private String propertyPackage;

    /**
     * 是否主键
     */
    private Boolean pk;

    /**
     * 是否自增
     */
    private Boolean increment;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 是否为新增
     */
    private Boolean inserted;

    /**
     * 是否编辑
     */
    private Boolean edit;

    /**
     * 是否列表展示
     */
    private Boolean list;

    /**
     * 是否查询
     */
    private Boolean search;

    /**
     * 查询条件
     */
    private String searchCondition;



    /**
     * 针对entity。
     * 是否需要生成;默认设置1：是
     * 【因为本系统模板继承superEntity，所以superEntity的字段无需生成】
     */
    private Boolean generate;


    @Schema(description = "租户ID")
    private Long tenantId;
}
