package ${package}.${moduleName}.domain.dto.resp;
<#if importList?has_content>
    <#list importList as pkg>
        import ${pkg};
    </#list>
</#if>
<#if swagger>
    import io.swagger.v3.oas.annotations.media.Schema;
</#if>
import com.baomidou.mybatisplus.annotation.*;
import com.wemirr.framework.commons.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
* <p>
    * ${table.comment!}
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Data
<#if swagger>
    @Schema(description = "${ClassName}DetailResp")
</#if>
public class ${ClassName}DetailResp {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list columns as field>
    <#if  field.generate>
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