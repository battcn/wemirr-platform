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

package com.wemirr.framework.redis.plus.sequence;

import java.time.format.DateTimeFormatter;

/**
 * @author Levin
 */
public interface Sequence {

    /**
     * Redis Key
     *
     * @return String
     */
    String key();

    /**
     * Redis Prefix
     *
     * @return UN => UN20210909000001
     */
    String prefix();


    /**
     * 流水号的位数，如10 表示 0001
     */
    default int size() {
        return 4;
    }


    /**
     * 分隔符
     *
     * @return 分隔符
     */
    default String delimiter() {
        return "";
    }

    /**
     * 流水号中日期的格式， null 表示流水号中没有 日期
     */
    default DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("yyyyMMdd");
    }

}
