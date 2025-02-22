package ${package}.${moduleName}.domain.dto.resp;
<#if importList?has_content>
    <#list importList as pkg>
        import ${pkg};
    </#list>
</#if>
<#if swagger>
    import io.swagger.v3.oas.annotations.media.Schema;
</#if>
import com.wemirr.framework.db.mybatisplus.page.PageRequest;
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
<#if swagger>
    @Schema(description = "${ClassName}PageReq")
</#if>
@Data
public class ${ClassName}PageResp {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list columns as field>
    <#if field.list || field.pk>
        <#if field.comment?has_content>
            /**
            * ${field.comment}
            **/
        </#if>
        <#if swagger>
            @Schema(description = "${field.comment!}")
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------->

}
