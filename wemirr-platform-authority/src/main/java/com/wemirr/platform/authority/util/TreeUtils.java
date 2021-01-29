package com.wemirr.platform.authority.util;


import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.TreeEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * list列表 转换成tree列表
 *
 * @author Levin
 */
public class TreeUtils {


    /**
     * 构建Tree结构
     *
     * @param treeList treeList
     * @param <E>      实体
     * @param <?>      主键
     * @return tree
     */
    public static <E extends TreeEntity<E, ? extends Serializable>> List<E> buildTree(List<E> treeList) {
        final ConcurrentMap<? extends Serializable, Integer> map =
                treeList.stream().collect(Collectors.toConcurrentMap(TreeEntity::getId, TreeEntity::getSize));
        final ConcurrentMap<Serializable, Integer> incrMap = Maps.newConcurrentMap();
        if (CollectionUtil.isEmpty(treeList)) {
            return treeList;
        }
        //记录自己是自己的父节点的id集合
        List<Serializable> selfIdEqSelfParent = new ArrayList<>();
        // 为每一个节点找到子节点集合
        for (E parent : treeList) {
            if (!incrMap.containsKey(parent.getId())) {
                incrMap.put(parent.getId(), parent.getSize());
            }
            final Serializable id = parent.getId();
            for (E children : treeList) {
                if (children != null && CollectionUtil.isNotEmpty(children.getChildren())) {
                    int sum = 0;
                    for (E child : children.getChildren()) {
                        sum += child.getSize();
                        map.remove(child.getId());
                    }
                    if (sum != 0) {
                        children.setSize(sum);
                    }
                }
                if (parent != children && children != null) {
                    //parent != children 这个来判断自己的孩子不允许是自己，因为有时候，根节点的parent会被设置成为自己
                    if (id.equals(children.getParentId())) {
                        parent.initChildren();
                        parent.getChildren().add(children);
                    }
                } else if (id.equals(parent.getParentId())) {
                    selfIdEqSelfParent.add(id);
                }
            }
        }
        final Integer reduce = incrMap.values().stream().reduce(0, Integer::sum);
        final List<E> list = treeList.stream()
                .filter(parent -> parent.getParentId() != null)
                .filter(parent -> "1".equals(parent.getParentId().toString()))
                .collect(Collectors.toList());
        incrMap.forEach((key, value) -> {
            for (E parent : list) {
                if (parent.getId() == key && CollectionUtil.isNotEmpty(parent.getChildren())) {
                    parent.setSize(parent.getSize() + value);
                }
            }
        });
        // 找出根节点集合
        List<E> trees = new ArrayList<>();
        List<? extends Serializable> allIds = treeList.stream().map(node -> node.getId()).collect(Collectors.toList());
        for (E baseNode : treeList) {
            if (!allIds.contains(baseNode.getParentId()) || selfIdEqSelfParent.contains(baseNode.getParentId())) {
                baseNode.setSize(reduce);
                trees.add(baseNode);
            }
        }
        // 处理子节点下面挂人和多层子节点
        trees.forEach(tree -> processTree(tree, map));
        // 处理父节点
        trees.stream().filter(xx -> xx != null && CollectionUtil.isNotEmpty(xx.getChildren()))
                .flatMap(xx -> xx.getChildren().stream())
                .filter(tree -> CollectionUtil.isNotEmpty(tree.getChildren()))
                .forEach(tree -> {
                    final int sum = tree.getChildren().stream().filter(xx -> CollectionUtil.isNotEmpty(xx.getChildren()))
                            .flatMap(xx -> xx.getChildren().stream()).mapToInt(xx -> xx.getSize()).sum();
                    if (tree.getSize() != sum && sum > 0) {
                        tree.setSize(sum);
                    }
                });
        return trees;
    }

    static <E extends TreeEntity<E, ? extends Serializable>> void processTree(E tree, final Map<? extends Serializable, Integer> map) {
        if (tree == null) {
            return;
        }
        if (CollectionUtil.isEmpty(tree.getChildren())) {
            if (!tree.getSize().equals(map.get(tree.getId()))) {
                tree.setSize(tree.getSize() + map.getOrDefault(tree.getId(), 0));
            }
        } else {
            tree.getChildren().forEach(children -> processTree(children, map));
        }
    }
}
