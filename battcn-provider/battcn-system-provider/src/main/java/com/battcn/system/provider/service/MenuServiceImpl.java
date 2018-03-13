package com.battcn.system.provider.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.mybatis.service.impl.BaseServiceImpl;
import com.battcn.system.facade.MenuService;
import com.battcn.system.pojo.dto.PermissionDto;
import com.battcn.system.pojo.dto.TreeNode;
import com.battcn.system.pojo.po.Menu;
import com.battcn.system.provider.mapper.AuthMapper;
import com.battcn.system.provider.mapper.MenuMapper;
import com.battcn.system.provider.mapper.OperateMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    private final AuthMapper authMapper;
    private final OperateMapper operateMapper;
    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(AuthMapper authMapper, OperateMapper operateMapper, MenuMapper menuMapper) {
        this.authMapper = authMapper;
        this.operateMapper = operateMapper;
        this.menuMapper = menuMapper;

    }

    private List<TreeNode> createTree(List<Menu> menus, Integer pid) {
        return menus.stream().filter(m -> m.getParentId() != null && Objects.equals(pid, m.getParentId()))
                .map(m -> new TreeNode(m.getId(), m.getName(), m.getIcon())).collect(toList());
    }

    @Override
    public List<TreeNode> listTree(Integer roleId) {
        List<Menu> menus = this.authMapper.listMenuByRoleId(roleId);
        return menus.stream().filter(m -> m.getParentId() == null).map(m -> {
            List<TreeNode> tree = createTree(menus, m.getId());
            if (CollectionUtils.isNotEmpty(tree)) {
                return new TreeNode(m.getId(), m.getName(), m.getIcon(), tree);
            }
            return null;
        }).filter(Objects::nonNull).collect(toList());
    }

    @Override
    public List<PermissionDto> listPermissions() {
        List<PermissionDto> list = operateMapper.listPermissions();
        return list.stream().filter(m1 -> m1.getParentId() == null).map(m2 -> {
            m2.setChildren(list.stream()
                    .filter(p1 -> StringUtils.equals(p1.getOption(), "list") && Objects.equals(p1.getParentId(), m2.getMenuId()))
                    .map(m3 -> {
                        m3.setChildren(list.stream()
                                .filter(b1 -> !StringUtils.equals(b1.getOption(), "list") && Objects.equals(b1.getMenuId(), m3.getMenuId()))
                                .collect(toList()));
                        return m3;
                    }).collect(toList()));
            return m2;
        }).collect(toList());
    }

    @Override
    public PageInfo<Menu> listMenuForDataGrid(DataGrid grid) {
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).setOrderBy("sort ASC").doSelectPageInfo(this.menuMapper::selectAll);
    }

    @Override
    public List<Menu> listMenu() {
        PageHelper.orderBy("sort ASC");
        List<Menu> menus = this.menuMapper.selectAll();
        menus.forEach(m -> m.setName(m.getParentId() == null ? "－－" + m.getName() : "－－－－" + m.getName()));
        return menus;
    }

    @Override
    public void saveOrUpdate(Menu menu) {
        if (menu.getId() != null) {
            super.updateSelectiveById(menu);
        } else {
            super.insertSelective(menu);
        }
        this.menuMapper.refreshTreeNodes();
    }

}
