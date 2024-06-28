package com.wemirr.framework.diff;

import cn.hutool.core.util.ReflectUtil;
import com.wemirr.framework.diff.domain.Employee;
import com.wemirr.framework.log.diff.core.DiffFieldStrategy;
import com.wemirr.framework.log.diff.core.LocalPropertyChange;
import com.wemirr.framework.log.diff.core.NoopJaversRepository;
import com.wemirr.framework.log.diff.core.annotation.DiffField;
import com.wemirr.framework.log.diff.utils.DiffUtils;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.DiffBuilder;
import org.javers.core.diff.changetype.PropertyChange;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

@Slf4j
public class DiffTests {


    Javers javers = JaversBuilder.javers()
            .registerJaversRepository(NoopJaversRepository.INSTANCE)
            .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
            .build();


    public String toLogContent(final Object source, final Object target) {
        if (source == null && target == null) {
            return "";
        }
        Diff diff = DiffUtils.compare(javers, source, target);
        if (!diff.hasChanges()) {
            return "";
        }
        List<Change> changes = diff.getChangesByType(PropertyChange.class)
                .stream().map(x -> LocalPropertyChange.wrap(javers, x))
                .toList();
        diff = new DiffBuilder(javers.getCoreConfiguration().getPrettyValuePrinter())
                .addChanges(changes)
                .build();
        log.info("diff - {}", diff);
        StringBuilder builder = new StringBuilder();
        for (Change change : changes) {
            processChangeNode(builder, change);
        }
        return "success";
    }

    private void processChangeNode(StringBuilder builder, Change change) {
        if (!(change instanceof LocalPropertyChange valueChange)) {
            return;
        }
        Field field = ReflectUtil.getField(valueChange.getClassName(), valueChange.getOriginalName());
        DiffField annotation = field.getAnnotation(DiffField.class);
        String filedLogName = Optional.ofNullable(annotation).map(DiffField::name).orElse(valueChange.getPropertyName());
        String functionName = Optional.ofNullable(annotation).map(DiffField::function).orElse(null);
        DiffFieldStrategy strategy = Optional.ofNullable(annotation).map(DiffField::strategy).orElse(DiffFieldStrategy.ALWAYS);
        log.info("filedLogName - {},functionName - {},strategy - {}", filedLogName, functionName, strategy);
    }

    @Test
    public void diff() {
        Employee loloOld = Employee.builder().name("lolo")
                .age(30)
                .salary(10_000)
                .desc("描述")
                .primaryAddress(new Employee.Address("常德", "0号街道"))
                .skills(Collections.singleton("management"))
                .subordinates(List.of(new Employee("小华"), new Employee("小华1")))
                .build();

        Employee loloNew = Employee.builder().name("lolo")
                .age(40)
                .salary(20_000)
                .primaryAddress(new Employee.Address("长沙", "1号街道"))
                .skills(Collections.singleton("java"))
                .subordinates(List.of(new Employee("小华1"), new Employee("小华2")))
                .build();
        toLogContent(loloOld, loloNew);
//        toLogContent(List.of(loloOld), List.of(loloNew));
//        toLogContent(List.of(loloOld), null);
//        toLogContent(null, loloNew);


        // 创建实体对象和值对象ID
//        ValueObject valueObject = new ValueObject("Example Field");
//        GlobalId globalId = example.javers.getTypeMapping(ValueObject.class).createId("field", valueObject);
//        JaversType javersType = javers.getTypeMapping(Employee.class);
        // 获取原始类名
//        String originalClassName = example.getOriginalClassName(globalId);
//        System.out.println("Original class name: " + originalClassName);
    }
}
