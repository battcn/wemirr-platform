package com.wemirr.platform.tools.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseColumn;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseGrid;
import com.wemirr.platform.tools.domain.entity.ExportExcelReq;
import com.wemirr.platform.tools.domain.enums.Expression;
import com.wemirr.platform.tools.domain.req.DynamicReleaseGridColumnReq;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQuery;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQueryDrag;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseLogTrackResp;
import com.wemirr.platform.tools.mapper.DynamicReleaseColumnMapper;
import com.wemirr.platform.tools.mapper.DynamicReleaseGridMapper;
import com.wemirr.platform.tools.mapper.DynamicReleaseMapper;
import com.wemirr.platform.tools.service.DynamicReleaseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
@Slf4j
@Service("mySqlDynamicReleaseServiceImpl")
@RequiredArgsConstructor
public class MySqlDynamicReleaseServiceImpl implements DynamicReleaseService<Long> {

    private final DynamicReleaseGridMapper dynamicReleaseGridMapper;
    private final DynamicReleaseMapper dynamicReleaseMapper;
    private final DynamicReleaseColumnMapper dynamicReleaseColumnMapper;


    @Override
    public DynamicReleaseCurdOptionResp curdOptions(String model) {
        DynamicReleaseGrid releaseGrid = getDynamicReleaseGridByCode(model);
        final List<DynamicReleaseColumn> dynamicReleaseColumns = this.dynamicReleaseColumnMapper.selectList(Wraps.<DynamicReleaseColumn>lbQ()
                .eq(DynamicReleaseColumn::getGridId, releaseGrid.getId()));
        if (CollUtil.isEmpty(dynamicReleaseColumns)) {
            throw CheckedException.notFound("动态发布的模型字段不存在");
        }
        final List<DynamicReleaseCurdOptionResp.DynamicReleaseColumnResp> columnRespList = dynamicReleaseColumns.stream().map(dynamic -> {
            log.warn("暂时没想好 dict 怎么做成最优方案....");
            return DynamicReleaseCurdOptionResp.DynamicReleaseColumnResp.builder()
                    .key(dynamic.getKey()).width(dynamic.getWidth()).title(dynamic.getTitle()).type(dynamic.getType())
                    .disabled(dynamic.getDisabled())
                    .showOverflowTooltip(dynamic.getShowOverflowTooltip())
                    .search(JSONUtil.toBean(dynamic.getSearch(), DynamicReleaseCurdOptionResp.DynamicReleaseColumnSearchResp.class))
                    .form(JSONUtil.toBean(dynamic.getForm(), DynamicReleaseCurdOptionResp.DynamicReleaseColumnFormResp.class))
                    .dict(buildDict(dynamic.getDict()))
                    .build();
        }).collect(Collectors.toList());

        return DynamicReleaseCurdOptionResp.builder().columns(columnRespList).build();
    }

    @Override
    public IPage<?> pages(String model, PageRequest request, Map<String, Object> params) {
        // 防止生成的动态SQL出问题
        if (MapUtil.isNotEmpty(params)) {
            params.remove("current");
            params.remove("size");
        }
        DynamicReleaseGrid releaseGrid = getDynamicReleaseGridByCode(model);
        final List<DynamicReleaseColumn> releaseColumns = this.dynamicReleaseColumnMapper.selectList(Wraps.<DynamicReleaseColumn>lbQ()
                .eq(DynamicReleaseColumn::getGridId, releaseGrid.getId()));
        String tableName = releaseGrid.getTableName();
        final SQL buildSql = new SQL().SELECT("*").FROM(tableName);
        if (CollectionUtil.isEmpty(releaseColumns)) {
            return dynamicReleaseMapper.dynamicPageList(request.buildPage(), buildSql.toString());
        }
        releaseColumns.stream()
                .filter(Objects::nonNull)
                .map(column -> {
                    final DynamicReleaseGridColumnReq.DynamicReleaseGridColumnSearch search = JSON.parseObject(column.getSearch(),
                            DynamicReleaseGridColumnReq.DynamicReleaseGridColumnSearch.class);
                    final Object val = params.get(column.getKey());
                    if (search == null || search.isDisabled() || StringUtils.isBlank(search.getExpression()) || Objects.isNull(val)) {
                        return null;
                    }
                    return DynamicReleaseQuery.builder().column(column.getKey()).expression(Expression.of(search.getExpression())).value(val).build().toCondition();
                }).filter(Objects::nonNull).forEach(buildSql::WHERE);
        final String sql = buildSql.toString();
        log.info("[编译的SQL] - {}", sql);
        return dynamicReleaseMapper.dynamicPageList(request.buildPage(), sql);
    }


    @Override
    public IPage<?> pages(String model, DynamicReleaseQueryDrag drag) {
        throw CheckedException.badRequest("未实现");
    }

    @Override
    public void batchDeleteByKeys(String model, List<Long> ids) {

    }

    @Override
    public void exportExcel(String model, ExportExcelReq req, HttpServletResponse response) {

    }

    @Override
    public void deleteById(String model, Long id) {
        DynamicReleaseGrid releaseGrid = getDynamicReleaseGridByCode(model);
        this.dynamicReleaseMapper.deleteById(releaseGrid.getTableName(), id);
    }


    @Override
    public void save(String model, Map<String, Object> map) {
        DynamicReleaseGrid releaseGrid = getDynamicReleaseGridByCode(model);
        this.dynamicReleaseMapper.insertMap(releaseGrid.getTableName(), map);
    }

    @Override
    public List<DynamicReleaseLogTrackResp<Long>> logTrack(String model, Long id) {
        return Lists.newArrayList();
    }

    @Override
    public void pushTrack(String model, Long id) {

    }


    @Override
    public void updateById(String model, Long id, Map<String, Object> map) {
        DynamicReleaseGrid releaseGrid = getDynamicReleaseGridByCode(model);
        this.dynamicReleaseMapper.updateByMap(releaseGrid.getTableName(), id, map);
    }

    private DynamicReleaseGrid getDynamicReleaseGridByCode(String model) {
        final DynamicReleaseGrid releaseGrid = this.dynamicReleaseGridMapper.selectOne(Wraps.<DynamicReleaseGrid>lbQ()
                .eq(DynamicReleaseGrid::getModel, model));
        if (Objects.isNull(releaseGrid)) {
            throw CheckedException.notFound("动态发布的模型表单不存在");
        }
        return releaseGrid;
    }


    public static String build(DynamicReleaseQuery param) {
        return ("`" + param.getColumn() + "` " + param.getExpression() + " " + param.getValue() + "");
    }

    private DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictResp buildDict(String dict) {
        if (StringUtils.isBlank(dict)) {
            return null;
        }
        final DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictResp.DynamicReleaseColumnDictRespBuilder builder =
                DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictResp.builder();
        final JSONObject object = JSON.parseObject(dict);
        final String code = object.getString("code");
        if (StringUtils.isNotBlank(code)) {
            builder.value("code").label("name").url("/authority/dictionaries/" + code + "/list");
        } else {
            builder.url(object.getString("url"));
        }
        return builder.build();
    }


}
