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

package com.wemirr.platform.iam.system.domain.converts;

import cn.hutool.core.lang.tree.TreeNode;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.platform.iam.system.domain.dto.resp.VueRouter;

import java.util.Map;

/**
 * @author Levin
 * @since 2020-03-02
 */
public class MenuConverts {

    public static final VueRouter2TreeNodeConverts VUE_ROUTER_2_TREE_NODE_CONVERTS = new VueRouter2TreeNodeConverts();

    public static class VueRouter2TreeNodeConverts implements BaseConverts<VueRouter, TreeNode<Long>> {

        @Override
        public TreeNode<Long> convert(VueRouter route) {
            TreeNode<Long> node = new TreeNode<>(route.getId(), route.getParentId(), route.getTitle(), route.getSequence());
            Map<String, Object> extra = Maps.newHashMap();
            extra.put("path", route.getPath());
            extra.put("name", route.getTitle());
            extra.put("title", route.getTitle());
            extra.put("category", route.getCategory());
            if (route.getCategory() == 0) {
                extra.put("component", "BasicLayout");
            } else if (route.getCategory() == 11 || route.getCategory() == 12) {
                extra.put("component", "IFrameView");
                extra.put("url", route.getComponent());
            } else {
                extra.put("component", route.getComponent());
            }
            extra.put("icon", route.getIcon());
            extra.put("permission", route.getPermission());
            Map<String, Object> meta = Maps.newHashMap();
            meta.put("icon", route.getIcon());
            meta.put("title", route.getTitle());
            meta.put("keepAlive", route.getKeepAlive());
            if (route.getCategory() == 11) {
                meta.put("link", route.getComponent());
            }
            if (route.getCategory() == 12) {
                meta.put("iframeSrc", route.getComponent());
            }
            extra.put("meta", meta);
            node.setExtra(extra);
            return node;
        }
    }

}
