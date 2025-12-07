package com.wemirr.platform.ai.service;

import com.wemirr.platform.ai.core.annotation.AiTool;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 智能体工具服务
 * 负责扫描和管理所有标注了 @Tool 的 Bean
 *
 * @author xJh
 * @since 2025/12/06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ToolService implements ApplicationListener<ContextRefreshedEvent> {

    private final ApplicationContext applicationContext;
    
    // 缓存工具信息：BeanName -> List<ToolMethodInfo>
    private final Map<String, List<ToolMethodInfo>> toolCache = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            scanTools();
        }
    }

    /**
     * 扫描所有 Bean，查找带有 @Tool 的方法
     */
    public void scanTools() {
        toolCache.clear();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        
        for (String beanName : beanNames) {
            try {
                Object bean = applicationContext.getBean(beanName);
                Class<?> beanClass = AopUtils.getTargetClass(bean);
                
                List<ToolMethodInfo> toolMethods = new ArrayList<>();
                for (Method method : beanClass.getDeclaredMethods()) {
                    Tool toolAnnotation = AnnotationUtils.findAnnotation(method, Tool.class);
                    if (toolAnnotation != null) {
                        toolMethods.add(buildToolInfo(beanName, method, toolAnnotation));
                    }
                }
                
                if (!toolMethods.isEmpty()) {
                    toolCache.put(beanName, toolMethods);
                    log.info("Found {} tools in bean '{}'", toolMethods.size(), beanName);
                }
            } catch (Exception e) {
                // 忽略无法处理的 Bean
                log.debug("Failed to scan bean '{}' for tools", beanName, e);
            }
        }
    }

    private ToolMethodInfo buildToolInfo(String beanName, Method method, Tool toolAnnotation) {
        List<ToolParameterInfo> parameters = Arrays.stream(method.getParameters())
                .map(this::buildParameterInfo)
                .collect(Collectors.toList());

        // 获取 value 数组，如果有值则拼接，否则使用 null 或空字符串
        String[] values = toolAnnotation.value();
        String description = (values != null && values.length > 0) ? String.join("\n", values) : "";

        // 如果 Tool 注解中没有描述，尝试从 P 获取方法名（Langchain4j 默认也支持 name()）
        // 这里主要取 name()，如果为空则用 description
        String name = method.getName();
        if (toolAnnotation.name() != null && !toolAnnotation.name().isEmpty()) {
            name = toolAnnotation.name();
        }

        return ToolMethodInfo.builder()
                .beanName(beanName)
                .methodName(method.getName())
                .name(name)
                .description(description)
                .parameters(parameters)
                .build();
    }

    private ToolParameterInfo buildParameterInfo(Parameter parameter) {
        String desc = null;
        P p = parameter.getAnnotation(P.class);
        if (p != null) {
            desc = p.value();
        }

        return ToolParameterInfo.builder()
                .name(parameter.getName())
                .type(parameter.getType().getSimpleName())
                .description(desc)
                .build();
    }

    /**
     * 获取所有可用工具列表
     */
    public List<ToolDTO> getTools() {
        return toolCache.entrySet().stream()
                .map(entry -> {
                    String beanName = entry.getKey();
                    String name = beanName;
                    String description = "";
                    String icon = "";

                    try {
                        Object bean = applicationContext.getBean(beanName);
                        Class<?> beanClass = AopUtils.getTargetClass(bean);

                        // 优先读取自定义 @AiTool 注解
                        AiTool aiTool = AnnotationUtils.findAnnotation(beanClass, AiTool.class);
                        if (aiTool != null) {
                            if (!aiTool.name().isEmpty()) {
                                name = aiTool.name();
                            }
                            description = aiTool.description();
                            icon = aiTool.icon();
                        }
                    } catch (Exception e) {
                        log.warn("Failed to read tool metadata for bean: {}", beanName);
                    }

                    return ToolDTO.builder()
                            .beanName(beanName)
                            .name(name)         // 新增：友好名称
                            .description(description) // 新增：详细描述
                            .icon(icon)         // 新增：图标
                            .methods(entry.getValue())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Data
    @Builder
    public static class ToolDTO {
        private String beanName;
        private String name;        // 友好名称
        private String description; // 描述
        private String icon;        // 图标
        private List<ToolMethodInfo> methods;
    }

    @Data
    @Builder
    public static class ToolMethodInfo {
        private String beanName;
        private String methodName;
        private String name;
        private String description;
        private List<ToolParameterInfo> parameters;
    }

    @Data
    @Builder
    public static class ToolParameterInfo {
        private String name;
        private String type;
        private String description;
    }
}


