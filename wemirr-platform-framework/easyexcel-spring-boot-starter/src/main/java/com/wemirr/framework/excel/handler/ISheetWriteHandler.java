package com.wemirr.framework.excel.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.wemirr.framework.excel.configuration.EasyExcelProperties;
import com.wemirr.framework.excel.convert.DictConverter;
import com.wemirr.framework.excel.convert.InstantConverter;
import com.wemirr.framework.excel.domain.ExcelWriteFile;
import com.wemirr.framework.excel.domain.SheetInfo;
import com.wemirr.framework.excel.handler.head.HeadGenerator;
import com.wemirr.framework.excel.handler.head.HeadMeta;
import com.wemirr.framework.excel.handler.head.I18nHeaderCellWriteHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author Levin
 */
public interface ISheetWriteHandler {

    /**
     * 验证是导出的结构和对象否支持
     *
     * @param obj obj
     * @return true | false
     */
    default boolean support(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof List<?> list) {
            return !list.isEmpty();
        }
        if (obj instanceof ExcelWriteFile file) {
            return support(file.getData());
        }
        return false;
    }

    /**
     * 通用的获取ExcelWriter方法
     *
     * @param response HttpServletResponse
     * @param file     file
     * @return ExcelWriter
     */
    @SneakyThrows(IOException.class)
    default ExcelWriter getExcelWriter(ApplicationContext context, HttpServletResponse response, ExcelWriteFile file) {
        ExcelWriterBuilder writerBuilder = EasyExcel.write(response.getOutputStream())
                .registerConverter(new DictConverter())
                .registerConverter(new InstantConverter())
                .autoCloseStream(true)
                .excelType(file.getExcelType())
                .inMemory(file.getInMemory());
        // 设置文件密码
        if (StringUtils.hasText(file.getPassword())) {
            writerBuilder.password(file.getPassword());
        }
        if (file.getInclude() != null) {
            writerBuilder.includeColumnFieldNames(file.getInclude());
        }
        if (file.getExclude() != null) {
            writerBuilder.excludeColumnFieldNames(file.getExclude());
        }
        // 拦截器，自定义样式等处理器
        Class<? extends WriteHandler>[] writeHandlers = file.getWriteHandlers();
        if (writeHandlers != null) {
            for (Class<? extends WriteHandler> clazz : writeHandlers) {
                writerBuilder.registerWriteHandler(BeanUtils.instantiateClass(clazz));
            }
        }
        // 开启国际化头信息处理
        var headerCellWriteHandler = context.getBeanProvider(I18nHeaderCellWriteHandler.class).getIfAvailable();
        if (file.getI18nHeader() && headerCellWriteHandler != null) {
            writerBuilder.registerWriteHandler(headerCellWriteHandler);
        }
        // 注入全局类型转换
        registerGlobalConverters(context, writerBuilder);
        // 注入自定义类型转换
        registerConverters(writerBuilder);
        Class<? extends Converter<?>>[] converters = file.getConverters();
        if (converters != null) {
            for (Class<? extends Converter<?>> clazz : converters) {
                writerBuilder.registerConverter(BeanUtils.instantiateClass(clazz));
            }
        }
        EasyExcelProperties properties = context.getBean(EasyExcelProperties.class);
        // 自动列宽（不一定好使，还是建议自己设置一个默认列宽）
        if (properties.getAutoColumnWidth()) {
            writerBuilder.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy());
        }
        // 填充模式往模板写入内容
        String templatePath = properties.getTemplatePath();
        if (StringUtils.hasText(file.getTemplate())) {
            ClassPathResource classPathResource = new ClassPathResource(
                    templatePath + File.separator + file.getTemplate());
            InputStream inputStream = classPathResource.getInputStream();
            writerBuilder.withTemplate(inputStream);
        }
        return writerBuilder.build();
    }


    /**
     * 注入自定义类型转换
     *
     * @param writerBuilder writerBuilder
     */
    default void registerConverters(ExcelWriterBuilder writerBuilder) {
    }


    default WriteSheet sheet(ApplicationContext context, SheetInfo sheet, Class<?> dataClass, String template, Class<? extends HeadGenerator> headGenerator) {
        // Sheet 编号和名称
        Integer sheetNo = sheet.getSheetNo() >= 0 ? sheet.getSheetNo() : null;
        String sheetName = sheet.getName();
        // 是否模板写入
        ExcelWriterSheetBuilder writerSheetBuilder = StringUtils.hasText(template) ? EasyExcel.writerSheet(sheetNo)
                : EasyExcel.writerSheet(sheetNo, sheetName);
        Class<? extends HeadGenerator> headGenerateClass = null;
        // 优先使用 Sheet 指定的头信息
        if (isNotInterface(sheet.getHeadGenerateClass())) {
            headGenerateClass = sheet.getHeadGenerateClass();
        } else {
            if (isNotInterface(headGenerator)) {
                // 其次使用 @ResponseExcel 中定义的全局头信息增强
                headGenerateClass = headGenerator;
            }
        }
        // 定义头信息增强则使用其生成头信息，否则使用 dataClass 来自动获取
        if (headGenerateClass != null) {
            fillCustomHeadInfo(context, dataClass, headGenerator, writerSheetBuilder);
        } else if (dataClass != null) {
            writerSheetBuilder.head(dataClass);
            if (sheet.getExcludes() != null) {
                writerSheetBuilder.excludeColumnFieldNames(sheet.getExcludes());
            }
            if (sheet.getIncludes() != null) {
                writerSheetBuilder.includeColumnFieldNames(sheet.getIncludes());
            }
        }
        return writerSheetBuilder.build();
    }

    /**
     * 是否为Null Head Generator
     *
     * @param headGeneratorClass 头生成器类型
     * @return true 已指定 false 未指定(默认值)
     */
    default boolean isNotInterface(Class<? extends HeadGenerator> headGeneratorClass) {
        return !Modifier.isInterface(headGeneratorClass.getModifiers());
    }

    private void fillCustomHeadInfo(ApplicationContext context, Class<?> dataClass, Class<? extends HeadGenerator> headEnhancerClass,
                                    ExcelWriterSheetBuilder writerSheetBuilder) {
        HeadGenerator headGenerator = context.getBean(headEnhancerClass);
        Assert.notNull(headGenerator, "The header generated bean does not exist.");
        HeadMeta head = headGenerator.head(dataClass);
        writerSheetBuilder.head(head.getHead());
        writerSheetBuilder.excludeColumnFieldNames(head.getIgnoreFields());
    }

    default void registerGlobalConverters(ApplicationContext context, ExcelWriterBuilder builder) {
        ObjectProvider<List<Converter<?>>> converterProvider = context.getBeanProvider(ResolvableType.forClass(Converter.class));
        converterProvider.ifAvailable(converters -> converters.forEach(builder::registerConverter));
    }


    /**
     * 校验
     *
     * @param writeFile writeFile
     */
    void validate(ExcelWriteFile writeFile);

    /**
     * 返回的对象
     *
     * @param response 输出对象
     * @param file     file
     */
    void export(HttpServletResponse response, ExcelWriteFile file);

    /**
     * 写成对象
     *
     * @param response 输出对象
     * @param file     file
     */
    void write(HttpServletResponse response, ExcelWriteFile file);


}
