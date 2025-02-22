package ${package}.${moduleName}.repository;

import com.wemirr.framework.db.mybatisplus.ext.SuperMapper;
import ${package}.${moduleName}.domain.entity.${ClassName};
import org.springframework.stereotype.Repository;

/**
* ${table.comment!}接口层
*
* @author ${author}
* @since ${date}
*/

@Repository
public interface ${ClassName}Mapper extends SuperMapper<${ClassName}> {

}
