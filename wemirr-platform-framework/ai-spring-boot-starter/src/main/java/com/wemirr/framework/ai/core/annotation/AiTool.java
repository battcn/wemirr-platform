package com.wemirr.framework.ai.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * AI 工具标注注解
 * <p>
 * 用于标注可被 AI 智能体调用的工具类。被此注解标注的类将自动注册为 Spring Bean，
 * 并可通过工具发现机制被智能体识别和调用。
 * </p>
 *
 * <h3>使用示例</h3>
 * <pre>{@code
 * @AiTool(
 *     name = "天气查询工具",
 *     description = "提供实时天气查询和天气预报功能",
 *     icon = "ant-design:cloud-outlined"
 * )
 * public class WeatherToolService {
 *     
 *     @Tool(name = "查询当前天气")
 *     public String getCurrentWeather(@P("城市名称") String city) {
 *         return "北京当前天气：晴，温度 25°C";
 *     }
 * }
 * }</pre>
 *
 * @author Levin
 * @since 2025/12/06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AiTool {

    /**
     * 工具名称
     */
    String name() default "";

    /**
     * 工具描述
     */
    String description() default "";

    /**
     * 工具图标（支持 Ant Design 图标库格式）
     */
    String icon() default "";
}
