/*
 * Copyright (c) 2023 WEMIRR-PLATFORM Authors. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    private Boolean springdoc = false;
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
