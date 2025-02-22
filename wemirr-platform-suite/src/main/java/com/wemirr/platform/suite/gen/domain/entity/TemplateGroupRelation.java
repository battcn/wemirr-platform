package com.wemirr.platform.suite.gen.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author xiao1
 * @date 2024-12
 */

@Data
@TableName("c_generate_template_group_relation")
public class TemplateGroupRelation {

    private Long templateId;

    private Long groupId;

}
