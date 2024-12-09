package com.wemirr.framework.diff.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.PropertyName;
import org.javers.core.metamodel.annotation.TypeName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@TypeName("Employee")
@AllArgsConstructor
public class Employee {

    @Id
    @PropertyName("名字")
    private String name;

    @PropertyName("薪资")
    private int salary;

    @DiffIgnore
    private int age;

    private String desc;

    private Employee boss;

    @Builder.Default
    @PropertyName("员工")
    private List<Employee> subordinates = new ArrayList<>();

    private Address primaryAddress;

    private Set<String> skills;

    public Employee(String name) {
        this.name = name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String city;

        private String street;

    }
}