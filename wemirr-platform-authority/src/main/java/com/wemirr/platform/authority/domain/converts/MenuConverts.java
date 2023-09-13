package com.wemirr.platform.authority.domain.converts;


import cn.hutool.core.lang.tree.TreeNode;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.platform.authority.domain.resp.VueRouter;
import org.apache.commons.lang3.StringUtils;

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
            TreeNode<Long> node = new TreeNode<>();
            node.setId(route.getId());
            node.setParentId(route.getParentId());
            node.setName(route.getName());
            Map<String, Object> extra = Maps.newHashMap();
            extra.put("title", route.getLabel());
            extra.put("label", route.getLabel());
            extra.put("path", route.getPath());
            extra.put("name", route.getLabel());
            boolean isUrl = StringUtils.containsAny(route.getComponent(), "http://", "https://");
            if (!isUrl) {
                extra.put("component", route.getComponent());
            }
            extra.put("icon", route.getIcon());
            extra.put("permission", route.getPermission());
            extra.put("sequence", route.getSequence());
            extra.put("type", route.getType());
            extra.put("model", route.getModel());
            extra.put("status", route.getStatus());
            extra.put("global", route.getGlobal());
            Map<String, Object> meta = Maps.newHashMap();
            meta.put("icon", route.getIcon());
            meta.put("title", route.getLabel());
            meta.put("hideMenu", !route.getDisplay());
            if (isUrl) {
                meta.put("frameSrc", route.getComponent());
            }
            extra.put("meta", meta);
            extra.put("description", route.getDescription());
            extra.put("display", route.getDisplay());
            node.setExtra(extra);
            node.setWeight(route.getSequence());
            return node;
        }
    }


}
