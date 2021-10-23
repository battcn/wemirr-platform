package com.wemirr.platform.authority.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.MapHelper;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.mybatis.SuperServiceImpl;
import com.wemirr.framework.db.mybatis.conditions.Wraps;
import com.wemirr.platform.authority.domain.entity.baseinfo.Org;
import com.wemirr.platform.authority.repository.OrgMapper;
import com.wemirr.platform.authority.service.OrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

import static java.util.stream.Collectors.toList;


/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author Levin
 * @since 2019-07-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrgServiceImpl extends SuperServiceImpl<OrgMapper, Org> implements OrgService {

    private final TenantEnvironment tenantEnvironment;

    @Override
    public List<Org> findChildren(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // MySQL 全文索引
        String applySql = String.format(" MATCH(tree_path) AGAINST('%s' IN BOOLEAN MODE) ", StringUtils.join(ids, " "));
        return super.list(Wraps.<Org>lbQ().in(Org::getId, ids).or(query -> query.apply(applySql)));
    }

    @Override
    public void remove(Long id) {
        List<Long> ids = Lists.newArrayList(id);
        final List<Org> children = this.baseMapper.findChildrenById(id);
        if (CollectionUtil.isNotEmpty(children)) {
            ids.addAll(children.stream().map(Entity::getId).collect(toList()));
        }
        this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public void addOrg(@NotNull Org org) {
        if (org == null) {
            throw CheckedException.notFound("信息不能为空");
        }
        final String treePath = this.baseMapper.getTreePathByParentId(org.getParentId());
        org.setTreePath(treePath);
        org.setTenantId(tenantEnvironment.tenantId());
        this.baseMapper.insert(org);
    }


    @Override
    public Map<Serializable, Object> findOrgByIds(Set<Serializable> ids) {
        List<Org> list = getOrgs(ids);

        //key 是 组织id， value 是org 对象
        return MapHelper.uniqueIndex(list, Org::getId, (org) -> org);
    }

    private static final int INT_1000 = 1000;

    private List<Org> getOrgs(Set<Serializable> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> idList = ids.stream().mapToLong(Convert::toLong).boxed().collect(toList());

        List<Org> list;
        if (idList.size() <= INT_1000) {
            list = idList.stream().map(id -> this.baseMapper.selectById(id)).filter(Objects::nonNull).collect(toList());
        } else {
            list = super.list(Wraps.<Org>lbQ().in(Org::getId, idList));
        }
        return list;
    }

}
