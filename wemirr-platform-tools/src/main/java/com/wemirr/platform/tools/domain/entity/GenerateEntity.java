package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("common_generate")
public class GenerateEntity extends SuperEntity<Long> {

    /**
     * 开发人员
     */
    private String author;
    /**
     * 开启 swagger2 模式
     */
    @Builder.Default
    private boolean springdoc = false;
    private String rootDir;
    private String tableName;
    private String parentPackage;
    private String moduleName;
    private String tablePrefix;
    private String apiUrlPrefix;
    /**
     * 逻辑删除属性名称
     */
    private String logicDeleteField;
    @TableField(exist = false)
    private List<IFill> fillList;
    private Long platformId;

    public GenerateEntity addFill(String fieldName, FieldFill fieldFill) {
        if (fillList == null) {
            fillList = new ArrayList<>();
        }
        fillList.add(new Column(fieldName, fieldFill));
        return this;
    }
}
