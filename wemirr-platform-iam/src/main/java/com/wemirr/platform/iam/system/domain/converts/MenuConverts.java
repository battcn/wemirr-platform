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
import com.wemirr.platform.iam.system.domain.dto.resp.VisibleResourceResp;
import com.wemirr.platform.iam.system.domain.enums.ResourceType;

import java.util.Map;

/**
 * @author Levin
 * @since 2020-03-02
 */
public class MenuConverts {

    public static final VueRouter2TreeNodeConverts VUE_ROUTER_2_TREE_NODE_CONVERTS = new VueRouter2TreeNodeConverts();

    public static class VueRouter2TreeNodeConverts implements BaseConverts<VisibleResourceResp, TreeNode<Long>> {

        /**
         * 构建路由 meta 信息
         * 后端自动构建的字段：title, icon, keepAlive, hideInMenu, link, iframeSrc
         * 从数据库 meta JSON 合并的字段：activeIcon, hideChildrenInMenu, hideInBreadcrumb, hideInTab, affixTab, badge, badgeType, badgeVariants
         */
        private static Map<String, Object> buildRouteMeta(VisibleResourceResp route) {
            Map<String, Object> meta = Maps.newHashMap();
            // 基础字段
            meta.put("title", route.getTitle());
            meta.put("icon", route.getIcon());
            // 可见性
            if (route.getVisible() != null && !route.getVisible()) {
                meta.put("hideInMenu", true);
                meta.put("activePath", StrUtil.subBefore(route.getPath(), "/", true));
            } else {
                meta.put("hideInMenu", false);
            }
            // 页面缓存
            if (route.getKeepAlive() != null) {
                meta.put("keepAlive", route.getKeepAlive());
            }
            // 外链
            if (route.getType() == ResourceType.LINK) {
                meta.put("link", route.getComponent());
            }
            // 内嵌
            if (route.getType() == ResourceType.IFRAME) {
                meta.put("iframeSrc", route.getComponent());
            }
            if (route.getMeta() != null) {
                meta.putAll(route.getMeta());
            }
            return meta;
        }

        @Override
        public TreeNode<Long> convert(VisibleResourceResp route) {
            TreeNode<Long> node = new TreeNode<>(route.getId(), route.getParentId(), route.getTitle(), route.getSequence());
            Map<String, Object> extra = Maps.newHashMap();

            // 基础信息
            extra.put("id", route.getId());
            extra.put("clientId", route.getClientId());
            extra.put("parentId", route.getParentId());
            extra.put("title", route.getTitle());
            extra.put("path", route.getPath());
            extra.put("name", route.getPath()); // VBen5.x name 用 path
            extra.put("type", route.getType().getValue());
            extra.put("icon", route.getIcon());
            extra.put("permission", route.getPermission());
            extra.put("sequence", route.getSequence());
            extra.put("status", route.getStatus());
            extra.put("keepAlive", route.getKeepAlive());
            extra.put("visible", route.getVisible());
            extra.put("shared", route.getShared());
            extra.put("description", route.getDescription());
            // 组件处理
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
            // 路由 meta
            extra.put("meta", buildRouteMeta(route));
            node.setExtra(extra);
            return node;
        }
    }

}