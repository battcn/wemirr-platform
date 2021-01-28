package com.wemirr.platform.authority.domain.converts;


import com.wemirr.framework.commons.entity.BaseConverts;
import com.wemirr.platform.authority.domain.entity.Menu;
import com.wemirr.platform.authority.domain.vo.VueRouter;

/**
 * @author Levin
 * @since 2020-03-02
 */
public class MenuConverts {

    public static final Menu2VueRouterConverts MENU_2_VUE_ROUTER_CONVERTS = new Menu2VueRouterConverts();


    public static class Menu2VueRouterConverts implements BaseConverts<Menu, VueRouter> {
        @Override
        public VueRouter convert(Menu source) {
            if (source == null) {
                return null;
            }
            VueRouter router = new VueRouter();
            router.setId(source.getId());
            router.setName(source.getLabel());
            router.setIcon(source.getIcon());
//            router.setTitle(source.getLabel());
//            router.setPath(source.getPath());
//            router.setLabel(source.getLabel());
//            router.setParentId(source.getParentId());
//            router.setComponent(source.getComponent());
//            router.setCreatedTime(source.getCreatedTime());
//            router.setPermission(source.getPermission());
//            router.setSequence(source.getSequence());
//            router.setDescription(source.getDescription());
//            router.setMeta(RouterMeta.builder().icon(source.getIcon()).title(source.getLabel()).build());
//            router.setLocked(source.getLocked());
//            router.setGlobal(source.getGlobal());
            return router;
        }
    }


}
