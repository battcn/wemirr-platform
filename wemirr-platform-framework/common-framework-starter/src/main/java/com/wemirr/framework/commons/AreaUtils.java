package com.wemirr.framework.commons;

import cn.hutool.core.collection.CollectionUtil;
import com.wemirr.framework.commons.entity.Area;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * 地址处理
 *
 * @author Levin
 */
public class AreaUtils {

    /**
     * 省
     */
    public static final String PROVINCE = "省";
    public static final String ZZQ = "自治区";
    public static final String ZZZ = "自治州";
    /**
     * 市
     */
    public static final String CITY = "市";

    /**
     * 区
     */
    public static final String COUNTY = "区";
    /**
     * 县
     */
    public static final String REGION = "县";

    /**
     * 模糊搜索
     *
     * @param provinces 省份MAP数据
     * @param nodes     国标码:详细信息
     * @param parentId  父节点ID
     * @param name      模糊搜索的名字
     * @return 搜索结果
     */
    public static Area likeByName(@NotEmpty Map<String, Area> provinces, @NotEmpty List<Area> nodes, @NotNull Integer parentId, @NotBlank String name) {
        return likeByName(provinces, nodes.stream().collect(toMap(Area::getId, Function.identity())), parentId, name);
    }

    /**
     * 模糊搜索
     *
     * @param provinces 省份MAP数据
     * @param areas     国标码:详细信息
     * @param parentId  父节点ID
     * @param name      模糊搜索的名字
     * @return 搜索结果
     */
    public static Area likeByName(@NotEmpty Map<String, Area> provinces, @NotEmpty Map<Integer, Area> areas, @NotNull Integer parentId, @NotBlank String name) {
        if (name == null) {
            return null;
        }
        if (parentId == null || parentId == 0) {
            final Optional<Map.Entry<String, Area>> optional = provinces.entrySet().stream().filter(province -> {
                final String provinceName = province.getKey();
                return StringUtils.equals(provinceName, name) ||
                        StringUtils.containsAny(provinceName, name,
                                StringUtils.removeEnd(name, PROVINCE),
                                StringUtils.removeEnd(name, ZZQ),
                                StringUtils.removeEnd(name, ZZZ))
                        ;
            }).findFirst();
            return optional.map(Map.Entry::getValue).orElse(null);
        }
        final Area area = areas.get(parentId);
        if (area == null) {
            return null;
        }
        final List<Area> childrenList = area.getChildren();
        if (CollectionUtil.isEmpty(childrenList)) {
            return null;
        }
        final Optional<Area> optional = childrenList.stream().filter(children ->
                StringUtils.equals(children.getName(), name) ||
                        StringUtils.containsAny(children.getName(), name,
                                StringUtils.removeEnd(name, CITY),
                                StringUtils.removeEnd(name, COUNTY),
                                StringUtils.removeEnd(name, ZZQ),
                                StringUtils.removeEnd(name, ZZZ),
                                StringUtils.removeEnd(name, REGION)))
                .findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        // 如果省里面没找到,那么就去市里面找
        final Optional<Area> optionalArea = area.getChildren().stream().filter(Objects::nonNull)
                .filter(xx -> CollectionUtil.isNotEmpty(xx.getChildren()))
                .flatMap(xx -> xx.getChildren().stream()).filter(children ->
                        StringUtils.equals(children.getName(), name) ||
                                StringUtils.containsAny(children.getName(), name,
                                        StringUtils.removeEnd(name, CITY),
                                        StringUtils.removeEnd(name, COUNTY),
                                        StringUtils.removeEnd(name, ZZQ),
                                        StringUtils.removeEnd(name, ZZZ),
                                        StringUtils.removeEnd(name, REGION)))
                .findFirst();
        return optionalArea.orElse(null);
    }
}
