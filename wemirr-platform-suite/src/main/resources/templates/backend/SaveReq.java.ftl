package ${package}.${moduleName}.domain.dto.req;
<#if importList?has_content>
    <#list importList as pkg>
        import ${pkg};
    </#list>
</#if>
<#if swagger>
    import io.swagger.v3.oas.annotations.media.Schema;
</#if>
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* <p>
    * ${table.comment!}
    *</p>
*
* @author ${author}
* @since ${date}
*/
@Data
<#if swagger>
    @Schema(description = "${ClassName}PageReq")
</#if>
public class ${ClassName}SaveReq  {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list columns as field>
    <#if field.pk >
        /**
        * ${field.comment}
        **/
        <#if swagger>
            @Schema(description = "${field.comment!}")
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
    <#if field.inserted  >
        <#if field.comment?has_content>
            /**
            * ${field.comment}
            **/
        </#if>
        <#if swagger>
            @Schema(description = "${field.comment!}")
        </#if>
        <#if field.propertyType == "String">
            @NotBlank(message = "${field.comment}不能为空")
        </#if>
        <#if field.propertyType != "String">
            @NotNull(message = "${field.comment}不能为空")
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------->

}
