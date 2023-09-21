package com.wemirr.platform;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levin
 */
public class AddressTest {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String code;
        private String name;
        private String parentId;
        private Integer level;
        private List<Address> children;

        @Override
        public String toString() {
            code = StringUtils.rightPad(code, 6, "0");
            parentId = StringUtils.rightPad(parentId, 6, "0");
            return "INSERT INTO `common_area` (`id`, `name`, `parent_id`, `level`) VALUES (" + code + ", '" + name + "', " + parentId + ", " + level + ");";
        }
    }


    @Test
    public void test() throws IOException {
        final byte[] addressBytes = Files.readAllBytes(Paths.get("/Users/battcn/Development/opensource/wemirr-platform/wemirr-platform-authority/src/main/resources/pcas-code.json"));
        final String addressText = new String(addressBytes, StandardCharsets.UTF_8);
        final List<Address> addresses = JSON.parseArray(addressText, Address.class);
        List<Address> excelData = Lists.newArrayList();
        for (Address province : addresses) {
            province.setLevel(1);
            province.setParentId("0");
            excelData.add(province);
            if (CollectionUtil.isEmpty(province.getChildren())) {
                continue;
            }
            for (Address city : province.getChildren()) {
                city.setLevel(2);
                city.setParentId(province.getCode());
                excelData.add(city);
                if (CollectionUtil.isEmpty(city.getChildren())) {
                    continue;
                }
                for (Address district : city.getChildren()) {
                    district.setLevel(3);
                    district.setParentId(city.getCode());
                    excelData.add(district);
                    if (CollectionUtil.isEmpty(district.getChildren())) {
                        continue;
                    }
                    for (Address town : district.getChildren()) {
                        town.setLevel(4);
                        town.setParentId(district.getCode());
                        excelData.add(town);
                    }
                }
            }
        }
        String filePath = "/Users/battcn/Development/opensource/wemirr-platform/wemirr-platform-authority/src/main/resources/common_area.sql";
        final List<Address> collect = excelData.stream().sorted(this::compare).collect(Collectors.toList());
        FileUtil.writeLines(collect, filePath, "UTF-8");

    }

    private int compare(Address o1, Address o2) {
        if (StringUtils.equals(o1.getCode(), o2.getCode())) {
            return 0;
        }
        return Integer.parseInt(o1.getCode()) < Integer.parseInt(o2.getCode()) ? -1 : 1;
    }
}
