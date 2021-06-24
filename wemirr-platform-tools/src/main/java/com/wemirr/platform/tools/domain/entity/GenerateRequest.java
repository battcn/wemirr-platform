package com.wemirr.platform.tools.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Levin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("common_generate")
public class GenerateRequest extends SuperEntity<Long> {

    /**
     * 开发人员
     */
    private String author;
    /**
     * 开启 swagger2 模式
     */
    private boolean swagger2 = false;
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
    private String superControllerClass;
    @TableField(exist = false)
    private List<TableFill> fillList;
    private Long platformId;

    public GenerateRequest addFill(String fieldName, FieldFill fieldFill) {
        if (fillList == null) {
            fillList = new ArrayList<>();
        }
        fillList.add(new TableFill(fieldName, fieldFill));
        return this;
    }
}
