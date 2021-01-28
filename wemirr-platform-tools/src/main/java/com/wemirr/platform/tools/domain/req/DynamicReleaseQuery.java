package com.wemirr.platform.tools.domain.req;

import com.wemirr.platform.tools.domain.enums.Expression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Levin
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DynamicReleaseQuery {

    private String column;
    private Object value;
    private Expression expression;

    public String toCondition() {
        if (expression == Expression.LIKE) {
            return "`" + column + "` " + expression.value + "'%" + value + "%'";
        }
        return "`" + column + "` " + expression.value + "'" + value + "'";
    }
}
