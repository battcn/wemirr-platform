package com.wemirr.platform.tools.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wemirr.framework.commons.entity.Entity;
import com.wemirr.framework.commons.entity.SuperEntity;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.times.LocalDateTimeUtils;
import com.wemirr.framework.db.TenantEnvironment;
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.tools.domain.DynamicReleaseDragSetting;
import com.wemirr.platform.tools.domain.entity.DynamicReleaseDrag;
import com.wemirr.platform.tools.domain.entity.ExportExcelReq;
import com.wemirr.platform.tools.domain.enums.DictFiledType;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQuery;
import com.wemirr.platform.tools.domain.req.DynamicReleaseQueryDrag;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseCurdOptionResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseDragPushServerMapperResp;
import com.wemirr.platform.tools.domain.resp.DynamicReleaseLogTrackResp;
import com.wemirr.platform.tools.repository.DynamicReleaseDragMapper;
import com.wemirr.platform.tools.repository.DynamicReleaseDragPushServerMapper;
import com.wemirr.platform.tools.repository.DynamicReleaseDragPushServerModelMapper;
import com.wemirr.platform.tools.service.DynamicReleaseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author Levin
 */
@Slf4j
@Service("mongoDynamicReleaseServiceImpl")
@RequiredArgsConstructor
public class MongoDynamicReleaseServiceImpl implements DynamicReleaseService<String> {

    private final DynamicReleaseDragMapper dynamicReleaseDragMapper;
    private final TenantEnvironment tenantEnvironment;

    /**
     * fix: mongoTemplate  暂时无效
     */
    private MongoTemplate mongoTemplate;

    @Override
    public DynamicReleaseCurdOptionResp curdOptions(String model) {
        log.debug("[表名] - {}", tableName(model));
        final DynamicReleaseDrag drag = this.dynamicReleaseDragMapper.selectOne(Wraps.<DynamicReleaseDrag>lbQ().eq(DynamicReleaseDrag::getModel, model)
                .eq(DynamicReleaseDrag::getTenantId, tenantEnvironment.tenantId()));
        if (drag == null || StringUtils.isBlank(drag.getSetting())) {
            throw CheckedException.notFound("记录不存在或者缺乏表单配置");
        }
        final String setting = drag.getSetting();
        log.debug("[信息] - {}", setting);
        final DynamicReleaseDragSetting object = JSON.parseObject(setting, DynamicReleaseDragSetting.class);
        if (Objects.isNull(object) || CollectionUtil.isEmpty(object.getFields())) {
            return null;
        }
        final List<DynamicReleaseCurdOptionResp.DynamicReleaseColumnResp> columns = object.getFields().stream()
                .map(field -> {
                    final DynamicReleaseDragSetting.DynamicReleaseGenerateFieldConfig fieldConfig = field.getConfig();
                    return DynamicReleaseCurdOptionResp.DynamicReleaseColumnResp.builder()
                            .key(field.getColumn()).title(fieldConfig.getLabel()).disabled(field.getDisabled())
                            .showOverflowTooltip(true)
                            .type(formGenerateToType(field)).dict(formGenerateToDict(field))
                            .form(DynamicReleaseCurdOptionResp.DynamicReleaseColumnFormResp.builder()
                                    .component(DynamicReleaseCurdOptionResp.DynamicReleaseColumnComponentResp.builder()
                                            .span(getSpan(field)).build())
                                    .rules(DynamicReleaseCurdOptionResp.DynamicReleaseColumnFormRuleResp.builder()
                                            .required(fieldConfig.getRequired()).build()).build()).build();
                })
                .collect(toList());
        columns.add(DynamicReleaseCurdOptionResp.DynamicReleaseColumnResp.builder()
                .key(Entity.CREATE_TIME).title("创建时间")
                .form(DynamicReleaseCurdOptionResp.DynamicReleaseColumnFormResp.builder()
                        .disabled(true)
                        .build())
                .build());
        final ArrayList<DynamicReleaseCurdOptionResp.RowHandleCustom> custom =
                Lists.newArrayList(
                        DynamicReleaseCurdOptionResp.RowHandleCustom.builder()
                                .emit("push-track")
                                .icon("el-icon-s-promotion")
                                .build(),
                        DynamicReleaseCurdOptionResp.RowHandleCustom.builder()
                                .emit("log-track")
                                .icon("el-icon-truck")
                                .type("success")
                                .build()
                );
        return DynamicReleaseCurdOptionResp.builder()
                .columns(columns)
                .rowHandle(DynamicReleaseCurdOptionResp.RowHandle.builder().width(240).custom(custom).build())
                .pageOptions(new DynamicReleaseCurdOptionResp.PageOptions())
                .options(new DynamicReleaseCurdOptionResp.Options())
                .selectionRow(new DynamicReleaseCurdOptionResp.SelectionRow())
                .build();
    }

    @Override
    public IPage<?> pages(String model, PageRequest request, Map<String, Object> params) {
        throw CheckedException.badRequest("未实现");
    }

    @Override
    public IPage<?> pages(String model, DynamicReleaseQueryDrag drag) {
        final IPage<JSONObject> pages = new Page<>(drag.getCurrent(), drag.getSize());
        Query query = buildQuery(drag.getQueries());
        final String tableName = tableName(model);
        final long count = mongoTemplate.count(query, tableName);
        query.skip((drag.getCurrent() - 1) * drag.getSize());
        query.limit((int) drag.getSize());
        log.debug("[构造条件] - {}", query.toString());
        List<JSONObject> result = mongoTemplate.find(query, JSONObject.class, tableName);
        pages.setRecords(result);
        pages.setTotal(count);
        return pages;
    }

    private Query buildQuery(List<DynamicReleaseQuery> queries) {
        Query query = new Query();
        if (CollectionUtil.isEmpty(queries)) {
            return query;
        }
        queries.stream()
                .filter(releaseQuery -> StringUtils.isNotBlank(releaseQuery.getColumn()))
                .forEach(releaseQuery -> buildCriteria(query, releaseQuery));
        return query;
    }

    private void buildCriteria(Query query, DynamicReleaseQuery releaseQuery) {
        switch (releaseQuery.getExpression()) {
            case LIKE ->
                    query.addCriteria(Criteria.where(releaseQuery.getColumn()).regex(releaseQuery.getValue().toString()));
            case GT -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).gt(releaseQuery.getValue()));
            case GE -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).gte(releaseQuery.getValue()));
            case LT -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).lt(releaseQuery.getValue()));
            case LE -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).lte(releaseQuery.getValue()));
            case IS_NULL -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).is(null));
            case IS_NOT_NULL -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).ne(null));
            case NE -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).ne(releaseQuery.getValue()));
            default -> query.addCriteria(Criteria.where(releaseQuery.getColumn()).is(releaseQuery.getValue()));
        }
    }

    @Override
    public void deleteById(String model, String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        this.mongoTemplate.remove(query, tableName(model));
    }

    private final DynamicReleaseDragPushServerMapper dynamicReleaseDragPushServerMapper;
    private final DynamicReleaseDragPushServerModelMapper dynamicReleaseDragPushServerModelMapper;


    @Override
    public void save(String model, Map<String, Object> body) {
        body.put("_id", IdUtil.getSnowflake().nextId());
        body.put(Entity.CREATE_USER, tenantEnvironment.userId());
        body.put(Entity.CREATE_USER_NAME, tenantEnvironment.realName());
        body.put(Entity.CREATE_TIME, LocalDateTimeUtils.now());
        this.mongoTemplate.insert(body, tableName(model));
        bodyCallback(model, body);
    }

    @Override
    public List<DynamicReleaseLogTrackResp<String>> logTrack(String model, String id) {
        // 查询日志
        Query query = new Query(Criteria.where("content._id").is(id)).with(Sort.by(Sort.Direction.DESC, Entity.CREATE_TIME));
        final List<JSONObject> objects = this.mongoTemplate.find(query, JSONObject.class, logTableName(model));
        if (CollectionUtil.isEmpty(objects)) {
            return null;
        }
        return objects.stream().map(obj -> {
            final String createdName = obj.getString(Entity.CREATE_USER_NAME);
            final String createdTime = obj.getString(Entity.CREATE_TIME);
            final Integer status = obj.getInteger("status");
            final String result = obj.getString("result");
            return new DynamicReleaseLogTrackResp<>(obj.getString("_id"), createdName, createdTime, status, result);
        }).collect(toList());
    }

    @Override
    public void pushTrack(String model, String id) {
        final JSONObject object = this.mongoTemplate.findById(id, JSONObject.class, tableName(model));
        bodyCallback(model, object);
    }

    private void bodyCallback(String model, Map<String, Object> body) {
        final List<DynamicReleaseDragPushServerMapperResp> mapperResp = this.dynamicReleaseDragPushServerModelMapper.queryDragPushServerByModel(model);
        if (CollectionUtil.isEmpty(mapperResp)) {
            return;
        }
        for (DynamicReleaseDragPushServerMapperResp resp : mapperResp) {
            final Method method = Method.valueOf(resp.getMethod());
            log.info("[请求方式] - {} , [请求地址] - {}", resp.getMethod(), resp.getUrl());
            final String result;
            if (method == Method.GET) {
                result = HttpUtil.get(resp.getUrl(), body);
            } else {
                result = HttpUtil.post(resp.getUrl(), JSON.toJSONString(body));
            }
            final String logTableName = logTableName(model);
            Map<String, Object> map = Maps.newHashMap();
            map.put("_id", IdUtil.getSnowflake().nextId());
            map.put(Entity.CREATE_USER, tenantEnvironment.userId());
            map.put(Entity.CREATE_USER_NAME, tenantEnvironment.realName());
            map.put(Entity.CREATE_TIME, LocalDateTimeUtils.now());
            map.put("status", 200);
            map.put("resultType", parseResult(result));
            map.put("content", body);
            map.put("result", result);
            log.info("[回调结果] - {}", result);
            this.mongoTemplate.insert(map, logTableName);
        }
    }

    private static DocumentBuilder builder;

    static {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private String parseResult(String result) {
        try {
            final Object parse = JSON.parse(result);
            if (parse != null) {
                return "json";
            }
            final Document document = builder.parse(result);
            if (document != null) {
                return "xml";
            }
        } catch (Exception e) {
            log.error("解析错误", e);
        }
        return "none";
    }

    @Override
    public void updateById(String model, String id, Map<String, Object> body) {
        if (MapUtil.isEmpty(body)) {
            return;
        }
        body.put(SuperEntity.UPDATE_USER, tenantEnvironment.userId());
        body.put(SuperEntity.UPDATE_USER_NAME, tenantEnvironment.realName());
        body.put(SuperEntity.UPDATE_TIME, LocalDateTimeUtils.now());
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        for (Map.Entry<String, Object> entry : body.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        log.info("[构造条件] - {}", update.toString());
        this.mongoTemplate.updateFirst(query, update, tableName(model));
    }


    private static final String TEXTAREA = "textarea";
    private static final String RICH_TEXT = "rich-text";
    private static final String EDITOR_QUILL = "editor-quill";
    private static final String TEXT_AREA = "text-area";

    private static final String DATE_RANGE = "date-range";
    private static final String DATERANGE = "daterange";
    private static final String TIME_RANGE = "time-range";
    private static final String TIMERANGE = "timerange";

    private Integer getSpan(DynamicReleaseDragSetting.DynamicReleaseGenerateField field) {
        String type = field.getConfig().getTagIcon();
        if (StringUtils.equals(type, DATE_RANGE)) {
            return 24;
        } else if (StringUtils.equals(type, TIME_RANGE)) {
            return 24;
        }
        return null;
    }

    private String formGenerateToType(DynamicReleaseDragSetting.DynamicReleaseGenerateField field) {
        String type = field.getConfig().getTagIcon();
        if (StringUtils.equals(type, TEXTAREA)) {
            return TEXT_AREA;
        } else if (StringUtils.equals(type, RICH_TEXT)) {
            return EDITOR_QUILL;
        } else if (StringUtils.equals(type, DATE_RANGE)) {
            return DATERANGE;
        } else if (StringUtils.equals(type, TIME_RANGE)) {
            return TIMERANGE;
        }
        return type;
    }

    private DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictResp formGenerateToDict(DynamicReleaseDragSetting.DynamicReleaseGenerateField field) {
        if (field == null) {
            return null;
        }
        final String tagIcon = field.getConfig().getTagIcon();
        final DictFiledType dictFiledType = DictFiledType.of(tagIcon);
        if (dictFiledType == null) {
            return null;
        }
        final DynamicReleaseDragSetting.DynamicReleaseGenerateFieldSlot fieldSlot = field.getSlot();
        final List<DynamicReleaseDragSetting.DynamicReleaseGenerateFieldSlotOption> options = fieldSlot.getOptions();
        if (CollectionUtil.isEmpty(options)) {
            return null;
        }
        List<DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictData> data = options.stream().map(option ->
                        DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictData.builder()
                                .label(option.getLabel()).value(option.getValue()).build())
                .collect(toList());
        return DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictResp.builder()
                .data(data)
                .build();
    }


    @Override
    public String tableName(String model) {
        final Long tenantId = tenantEnvironment.tenantId();
        return "tenant_" + tenantId + "_" + model;
    }

    private String logTableName(String model) {
        final Long tenantId = tenantEnvironment.tenantId();
        return "tenant_" + tenantId + "_" + model + "_log";
    }

    @Override
    public void batchDeleteByKeys(String model, List<String> ids) {
        Query query = new Query(Criteria.where("_id").in(ids));
        this.mongoTemplate.remove(query, tableName(model));
    }


    @SneakyThrows
    @Override
    public void exportExcel(String model, ExportExcelReq req, HttpServletResponse response) {
        final List<ExportExcelReq.DynamicReleaseColumnReq> columns = req.getColumns().stream().filter(ExportExcelReq.DynamicReleaseColumnReq::getShow).collect(toList());
        final List<List<String>> titles = columns.stream().map(column -> Lists.newArrayList(column.getTitle())).collect(toList());
        Query query = new Query();
        final String tableName = tableName(model);
        final List<JSONObject> list = this.mongoTemplate.find(query, JSONObject.class, tableName);
        final List<List<String>> lists = list.stream().map(object -> getItem(columns, object)).collect(toList());
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 背景色
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteCellStyle.setWriteFont(headWriteFont);
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 字体策略
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        //设置 垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        final WriteSheet writeSheet = EasyExcel.writerSheet("表格数据")
                .head(titles)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .autoTrim(true).build();
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        excelWriter.write(lists, writeSheet);
        excelWriter.finish();
    }

    private List<String> getItem(List<ExportExcelReq.DynamicReleaseColumnReq> columns, JSONObject object) {
        List<String> item = Lists.newArrayList();
        for (ExportExcelReq.DynamicReleaseColumnReq column : columns) {
            final ExportExcelReq.DynamicReleaseColumnDictReq dict = column.getDict();
            if (dict == null || CollectionUtil.isEmpty(dict.getData())) {
                item.add(object.getString(column.getKey()));
            } else {
                final List<DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictData> data = dict.getData();
                final Optional<DynamicReleaseCurdOptionResp.DynamicReleaseColumnDictData> optional = data.stream()
                        .filter(val -> StringUtils.equalsIgnoreCase(String.valueOf(val.getValue()), object.getString(column.getKey())))
                        .findFirst();
                item.add(optional.isPresent() ? optional.get().getLabel() : "");
            }
        }
        return item;
    }


}
