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
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.platform.iam.system.domain.dto.resp.VueRouter;
import com.wemirr.platform.iam.system.domain.enums.ResourceType;

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
            // TODO VBen5.x Name 如果为中文部分情况会 404
            extra.put("name", route.getPath());
            extra.put("title", route.getTitle());
            extra.put("type", route.getType().getValue());
            if (route.getType() == ResourceType.DIRECTORY) {
                extra.put("component", "BasicLayout");
            } else if (route.getType() == ResourceType.IFRAME || route.getType() == ResourceType.LINK) {
                extra.put("component", "IFrameView");
                extra.put("url", route.getComponent());
            } else {
                if (StrUtil.isNotBlank(route.getComponent())) {
                    extra.put("component", route.getComponent());
                }
            }
            if (route.getVisible() != null) {
                extra.put("hideInMenu", route.getVisible());
            }
            extra.put("icon", route.getIcon());
            extra.put("permission", route.getPermission());
            extra.put("meta", buildRouteMeta(route));
            node.setExtra(extra);
            return node;
        }

        private static Map<String, Object> buildRouteMeta(VueRouter route) {
            Map<String, Object> meta = Maps.newHashMap();
            meta.put("icon", route.getIcon());
            meta.put("title", route.getTitle());
            if (route.getKeepAlive() != null) {
                meta.put("keepAlive", route.getKeepAlive());
            }
            if (route.getType() == ResourceType.LINK) {
                meta.put("link", route.getComponent());
            }
            if (route.getType() == ResourceType.IFRAME) {
                meta.put("iframeSrc", route.getComponent());
            }
            return meta;
        }
    }

}
