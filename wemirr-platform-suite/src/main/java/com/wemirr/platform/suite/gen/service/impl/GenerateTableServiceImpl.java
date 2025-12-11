package com.wemirr.platform.suite.gen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wemirr.framework.commons.BeanUtilPlus;
import com.wemirr.framework.commons.exception.CheckedException;
import com.wemirr.framework.commons.security.AuthenticationContext;
import com.wemirr.framework.db.mybatisplus.ext.SuperServiceImpl;
import com.wemirr.framework.db.mybatisplus.wrap.Wraps;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTablePageReq;
import com.wemirr.platform.suite.gen.domain.dto.req.GenerateTableSaveReq;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTableDetailResp;
import com.wemirr.platform.suite.gen.domain.dto.resp.GenerateTablePageResp;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTable;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTableColumn;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplate;
import com.wemirr.platform.suite.gen.domain.entity.GenerateTemplateGroup;
import com.wemirr.platform.suite.gen.repository.GenerateTableMapper;
import com.wemirr.platform.suite.gen.service.GenerateTableColumnService;
import com.wemirr.platform.suite.gen.service.GenerateTableService;
import com.wemirr.platform.suite.gen.service.GenerateTemplateGroupService;
import com.wemirr.platform.suite.gen.service.GenerateTemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.anyline.metadata.Column;
import org.anyline.metadata.Table;
import org.anyline.proxy.ServiceProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author xiao1
 * @since 2024-12
 */
@Service
@RequiredArgsConstructor
public class GenerateTableServiceImpl extends SuperServiceImpl<GenerateTableMapper, GenerateTable> implements GenerateTableService {


    private final DataSource dataSource;

    private final GenerateTableColumnService generateTableColumnService;

    private final GenerateTemplateService generateTemplateService;

    private final GenerateTemplateGroupService generateTemplateGroupService;

    private final AuthenticationContext authenticationContext;

    @Resource
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);


    @Override
    public List<GenerateTable> getGenTableListFromDs(String tableName) throws Exception {
        LinkedHashMap<String, Table<?>> tablesMap = ServiceProxy.metadata().tables();
        List<GenerateTable> genTables = baseMapper.selectList();
        for (GenerateTable genTable : genTables) {
            tablesMap.remove(genTable.getName().toUpperCase());
        }
        if (StrUtil.isNotEmpty(tableName)) {
            tablesMap = tablesMap.entrySet().stream()
                    .filter(entry -> entry.getKey().toLowerCase().contains(tableName.toLowerCase()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (existing, replacement) -> existing,
                            LinkedHashMap::new
                    ));
        }

        return tablesMap.values().stream()
                .map(x -> {
                    GenerateTable gen = new GenerateTable();
                    gen.setName(x.getName());
                    gen.setComment(x.getComment());
                    if (x.getCreateTime() != null) {
                        gen.setCreateTime(x.getCreateTime().toInstant());
                    }
                    if (x.getUpdateTime() != null) {
                        gen.setLastModifyTime(x.getUpdateTime().toInstant());
                    }
                    return gen;
                }).toList();
    }

    @Transactional
    @Override
    public void importToGenTable(List<String> tableNames) {
        /**
         *  删除掉已经存在的表列信息
         */
        for (String tableName : tableNames) {
            generateTableColumnService.remove(Wraps.<GenerateTableColumn>lbQ().eq(GenerateTableColumn::getTableName, tableName));

        }
        LinkedHashMap<String, Table<?>> tablesMap = ServiceProxy.metadata().tables();
        // 过滤tablesMap，只保留表名存在于tableNames中的项
        tablesMap = tablesMap.entrySet()
                .stream()
                .filter(entry -> tableNames.contains(entry.getKey().toLowerCase()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
        List<GenerateTable> generateTables = tablesMap.values().stream()
                .map(x -> {
                    GenerateTable gen = new GenerateTable();
                    gen.setName(x.getName());
                    gen.setComment(x.getComment());
                    if (x.getCreateTime() != null) {
                        gen.setCreateTime(x.getCreateTime().toInstant());
                    }
                    if (x.getUpdateTime() != null) {
                        gen.setLastModifyTime(x.getUpdateTime().toInstant());
                    }
                    buildInitTable(gen);
                    return gen;
                }).toList();
        List<GenerateTableColumn> generateTableColumns = new ArrayList<>();
        for (String tableName : tableNames) {
            generateTableColumns.addAll(selectDbTableColumnsByName(tableName));
        }
        generateTableColumnService.saveBatch(generateTableColumns);
        this.baseMapper.insertBatch(generateTables);

    }

    @Override
    public IPage<GenerateTablePageResp> pageList(GenerateTablePageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<GenerateTable>lbQ()
                        .like(GenerateTable::getName, req.getName()))
                .convert(x -> BeanUtil.toBean(x, GenerateTablePageResp.class));
    }

    @Override
    public GenerateTableDetailResp detail(Long id) {
        GenerateTable generateTable = this.getById(id);
        return BeanUtil.toBean(generateTable, GenerateTableDetailResp.class);

    }

    @Override
    public void modify(Long id, GenerateTableSaveReq req) {
        long count = this.baseMapper.selectCount(GenerateTable::getId, id);
        if (count == 0) {
            throw CheckedException.notFound("表配置信息不存在");
        }
        var bean = BeanUtilPlus.toBean(id, req, GenerateTable.class);
        //修改配置后，应该针对 remove_prefix 进行处理逻辑
        if (req.getRemovePrefix()) {
            bean.setBusinessName(convertTableNameToBusinessNameRemovePre(bean.getName()));
            bean.setClassName(convertTableNameToClassName(bean));
        } else {
            bean.setBusinessName(convertTableNameToBusinessName(bean.getName()));
            bean.setClassName(convertTableNameToClassName(bean));
        }
        this.baseMapper.updateById(bean);
    }

    @Transactional
    @Override
    public void removeGen(Long id) {
        GenerateTable generateTable = this.baseMapper.selectById(id);
        Optional.ofNullable(generateTable)
                .orElseThrow(() -> CheckedException.notFound("表配置信息不存在"));
        //删除对应的列配置信息
        generateTableColumnService.remove(Wraps.<GenerateTableColumn>lbQ().eq(GenerateTableColumn::getTableName, generateTable.getName()));
        this.baseMapper.deleteById(generateTable);
    }

    @Override
    public Map<String, String> previewCode(Long id) {
        List<Long> ids = ListUtil.toList(id);
        return getPathCodeMap(ids);
    }

    @Override
    public void generate(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Long> ids = ListUtil.toList(id);
        Map<String, String> previewMap = getPathCodeMap(ids);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String baseFileName = "gen-code_" + timestamp;
        Path tempOutputDir = Files.createTempDirectory("gen-code-");
        File outputZipFile = tempOutputDir.resolve(baseFileName + ".zip").toFile();

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputZipFile))) {
            for (Map.Entry<String, String> entry : previewMap.entrySet()) {
                String filePath = entry.getKey();
                String fileContent = entry.getValue();
                ZipEntry zipEntry = new ZipEntry(filePath);
                zos.putNextEntry(zipEntry);
                if (fileContent != null) {
                    byte[] bytes = fileContent.getBytes(StandardCharsets.UTF_8);
                    zos.write(bytes, 0, bytes.length);
                }
                zos.closeEntry();
            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + baseFileName + ".zip");
        try (InputStream fis = new FileInputStream(outputZipFile);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } finally {
            Files.deleteIfExists(outputZipFile.toPath());
            Files.deleteIfExists(tempOutputDir);
        }
        response.flushBuffer();
    }

    /**
     * 生成文件路径：代码映射
     */
    public Map<String, String> getPathCodeMap(List<Long> tableIds) {
        Map<String, String> previewMap = new HashMap<>();
        for (Long tableId : tableIds) {
            GenerateTable generateTable = this.baseMapper.selectById(tableId);
            //获取只需要生成的列信息
            List<GenerateTableColumn> columns = this.generateTableColumnService.listByTableName(generateTable.getName());
            generateTable.setColumns(columns);
            Map<String, Object> modalData = getModalData(generateTable);
            //获取模板
            Long templateGroupId = generateTable.getTemplateGroupId();
            //根据模板获取模板详情
            List<GenerateTemplate> templates = generateTemplateService.getTemplateDetailByGroupId(templateGroupId);
            for (GenerateTemplate template : templates) {
                //模板代码
                String templateCode = template.getCode();
                //模板生成路径
                String generatePath = template.getGeneratePath();
                String renderedString = renderTemplate(templateCode, modalData);
                String filePath = renderTemplate(generatePath, modalData);
                previewMap.put(filePath, renderedString);
            }
        }
        return previewMap;
    }

    /**
     * 渲染给定的模板字符串。
     *
     * @param templateStr 模板字符串
     * @param model       模型数据
     * @return 渲染后的字符串
     */
    public String renderTemplate(String templateStr, Map<String, Object> model) {
        try {
            Template template = new Template("inline", templateStr, configuration);
            StringWriter stringWriter = new StringWriter();
            template.process(model, stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Error rendering template", e);
        }
    }

    public Map<String, Object> getModalData(GenerateTable genTable) {
        Map<String, Object> modalData = new HashMap<>();
        String packageName = genTable.getPackageName();
        modalData.put("tableName", genTable.getName());
        modalData.put("ClassName", genTable.getClassName());
        modalData.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        modalData.put("moduleName", genTable.getModuleName());
        modalData.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
        modalData.put("businessName", genTable.getBusinessName());
        modalData.put("package", packageName);
        modalData.put("packagePath", genTable.getPackageName().replace(".", "/"));
        modalData.put("author", genTable.getAuthor());
        modalData.put("date", "2024-12-19");
        modalData.put("importList", getTableImportPath(genTable.getColumns()));
        modalData.put("columns", genTable.getColumns());
        modalData.put("table", genTable);
        modalData.put("swagger", genTable.getSwagger());
        return modalData;
    }

    public List<GenerateTableColumn> selectDbTableColumnsByName(String tableName) {
        // 设置数据源
        LinkedHashMap<String, Column> columns = ServiceProxy.metadata().columns(tableName);
        List<GenerateTableColumn> tableColumns = new ArrayList<>();
        columns.forEach((columnName, column) -> {
            GenerateTableColumn tableColumn = new GenerateTableColumn();
            tableColumn.setPk(column.isPrimaryKey());
            tableColumn.setName(column.getName());
            tableColumn.setComment(column.getComment());
            tableColumn.setType(column.getTypeName().toLowerCase());
            tableColumn.setSort(column.getPosition());
            tableColumn.setTableName(tableName);
            tableColumn.setRequired(column.isNullable());
            tableColumn.setIncrement(column.isAutoIncrement());
            //默认不是主键且需要生成列
            tableColumn.setInserted(!column.isPrimaryKey() && isNeedGenerate(column.getName()));
            tableColumn.setEdit(!column.isPrimaryKey() && isNeedGenerate(column.getName()));
            tableColumn.setList(!column.isPrimaryKey() || isNeedGenerate(column.getName()));
            tableColumn.setSearch(false);
            tableColumn.setSearchCondition("LIKE");// EQ LIKE
            tableColumn.setDeleted(false);
            //属性名。sql字段 转换为小驼峰命名
            tableColumn.setPropertyName(StrUtil.toCamelCase(column.getName()));
            //属性类型
            tableColumn.setPropertyType(getJavaType(column.getTypeName().toLowerCase()));
            tableColumn.setGenerate(isNeedGenerate(column.getName()));
            tableColumn.setPropertyPackage(getColumnImportPath(tableColumn.getPropertyType()));
            tableColumns.add(tableColumn);
        });
        return tableColumns;
    }


    public void buildInitTable(GenerateTable genTable) {
        //前缀默认不设置
        //是否忽略前缀，默认false
        genTable.setRemovePrefix(false);
        genTable.setPrefix("");
        genTable.setClassName(convertTableNameToClassName(genTable));
        //包名默认设置
        genTable.setPackageName("com.wemirr.platform");
        //模块名默认设置
        genTable.setModuleName("GenCode");
        //业务名默认设置=> sys_user => SysUser
        genTable.setBusinessName(convertTableNameToBusinessName(genTable.getName()));
        genTable.setAuthor(authenticationContext.nickName());
        // 设置默认模板组id
        GenerateTemplateGroup one = generateTemplateGroupService.getOne(Wraps.<GenerateTemplateGroup>lbQ().eq(GenerateTemplateGroup::getIsDefault, true));
        genTable.setDeleted(false);
        genTable.setTemplateGroupId(one.getId());

    }

    /**
     * 将表名转换为业务名称 【忽略前缀】例如：sys_user => User
     *
     * @param tableName tableName
     * @return 结果
     */
    public static String convertTableNameToBusinessNameRemovePre(String tableName) {
        int firstIndex = tableName.indexOf('_');
        if (firstIndex == -1 || firstIndex == tableName.length() - 1) {
            // 如果没有下划线或下划线在最后，则直接返回原表名
            return tableName;
        }
        String businessName = tableName.substring(firstIndex + 1);
        return StrUtil.toCamelCase(businessName);
    }

    /**
     * 将表名转换为业务名称 【不忽略前缀】例如：sys_user => SysUser
     *
     * @param tableName
     * @return
     */
    public static String convertTableNameToBusinessName(String tableName) {
        return StrUtil.toCamelCase(tableName);
    }

    /**
     * 将表名转换为类名  [根据是否忽略前缀判断]
     *
     * @param genTable GenerateTable对象
     * @return 转换后的类名
     */
    public static String convertTableNameToClassName(GenerateTable genTable) {
        // 获取配置项
        boolean autoRemovePre = genTable.getRemovePrefix();
        String tablePrefix = genTable.getPrefix();
        String tableName = genTable.getName();
        // 如果启用了自动移除前缀并且表前缀不为空，则进行前缀移除
        if (autoRemovePre && StringUtils.isNotEmpty(tablePrefix)) {
            String[] prefixes = StringUtils.split(tablePrefix, ",");
            for (String prefix : prefixes) {
                // 检查表名是否以任意一个前缀开始，如果匹配则移除
                if (tableName.startsWith(prefix)) {
                    tableName = tableName.replaceFirst(prefix, "");
                    break;
                }
            }
        }
        // 将处理后的表名转换为驼峰命名法并首字母大写
        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
    }

    //判断是否需要生成代码
    public boolean isNeedGenerate(String columnName) {
        // 忽略的列名列表，可以根据实际需要进行扩展或修改
        List<String> ignoredColumns = Arrays.asList("id", "create_by",
                "create_name", "create_time", "last_modify_by",
                "last_modify_name", "last_modify_time", "deleted", "tenant_id");
        return !ignoredColumns.contains(columnName);
    }

    private static final Map<String, String> MYSQL_TO_JAVA_TYPE_MAP = new HashMap<>();

    static {
        // 初始化映射关系
        MYSQL_TO_JAVA_TYPE_MAP.put("bit", "Boolean");
        MYSQL_TO_JAVA_TYPE_MAP.put("tinyint", "Integer");
        MYSQL_TO_JAVA_TYPE_MAP.put("smallint", "Short");
        MYSQL_TO_JAVA_TYPE_MAP.put("mediumint", "Integer");
        MYSQL_TO_JAVA_TYPE_MAP.put("int", "Integer");
        MYSQL_TO_JAVA_TYPE_MAP.put("integer", "Integer");
        MYSQL_TO_JAVA_TYPE_MAP.put("bigint", "Long");
        MYSQL_TO_JAVA_TYPE_MAP.put("float", "Float");
        MYSQL_TO_JAVA_TYPE_MAP.put("double", "Double");
        MYSQL_TO_JAVA_TYPE_MAP.put("decimal", "BigDecimal");
        MYSQL_TO_JAVA_TYPE_MAP.put("numeric", "BigDecimal");
        MYSQL_TO_JAVA_TYPE_MAP.put("date", "Date");
        MYSQL_TO_JAVA_TYPE_MAP.put("datetime", "Date");
        MYSQL_TO_JAVA_TYPE_MAP.put("timestamp", "Date");
        MYSQL_TO_JAVA_TYPE_MAP.put("time", "Time");
        MYSQL_TO_JAVA_TYPE_MAP.put("year", "Short");
        MYSQL_TO_JAVA_TYPE_MAP.put("char", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("varchar", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("binary", "byte[]");
        MYSQL_TO_JAVA_TYPE_MAP.put("varbinary", "byte[]");
        MYSQL_TO_JAVA_TYPE_MAP.put("tinyblob", "byte[]");
        MYSQL_TO_JAVA_TYPE_MAP.put("blob", "byte[]");
        MYSQL_TO_JAVA_TYPE_MAP.put("mediumblob", "byte[]");
        MYSQL_TO_JAVA_TYPE_MAP.put("longblob", "byte[]");
        MYSQL_TO_JAVA_TYPE_MAP.put("tinytext", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("text", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("mediumtext", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("longtext", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("enum", "String");
        MYSQL_TO_JAVA_TYPE_MAP.put("set", "List");
    }

    public String getJavaType(String mysqlType) {
        if (mysqlType == null || mysqlType.trim().isEmpty()) {
            throw new IllegalArgumentException("MySQL type cannot be null or empty.");
        }
        // 去除可能存在的括号和参数，如 "varchar(255)" -> "varchar"
        String normalizedType = mysqlType.toLowerCase().replaceAll("[().,0-9]", "").trim();
        String javaType = MYSQL_TO_JAVA_TYPE_MAP.get(normalizedType);
        // 如果找不到匹配项，默认返回String类型
        if (javaType == null) {
            return "String";
        }
        return javaType;
    }

    // 定义需要导入包的Java类型到其完整类路径的映射
    private static final Map<String, String> JAVA_TYPE_TO_PACKAGE_MAP = new HashMap<>();

    static {
        // 仅包含非 java.lang 包中的类型
        JAVA_TYPE_TO_PACKAGE_MAP.put("BigDecimal", "java.math.BigDecimal");
        JAVA_TYPE_TO_PACKAGE_MAP.put("Date", "java.util.Date");
        JAVA_TYPE_TO_PACKAGE_MAP.put("Time", "java.sql.Time");
        JAVA_TYPE_TO_PACKAGE_MAP.put("List", "java.util.List");
        // 其他需要导入的类型...
    }


    public static String getColumnImportPath(String javaType) {
        if (javaType == null || javaType.trim().isEmpty()) {
            return null;
        }
        return JAVA_TYPE_TO_PACKAGE_MAP.get(javaType);
    }

    public static Set<String> getTableImportPath(List<GenerateTableColumn> columns) {
        Set<String> importPaths = new HashSet<>();
        for (GenerateTableColumn column : columns) {
            String propertyType = column.getPropertyType();
            if (propertyType == null || propertyType.trim().isEmpty()) {
                continue;
            }
            if (JAVA_TYPE_TO_PACKAGE_MAP.get(propertyType) != null) {
                importPaths.add(JAVA_TYPE_TO_PACKAGE_MAP.get(propertyType));
            }
        }
        return importPaths;
    }
}
