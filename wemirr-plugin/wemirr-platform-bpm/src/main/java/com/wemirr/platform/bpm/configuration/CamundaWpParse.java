package com.wemirr.platform.bpm.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author levin
 */
public class CamundaWpParse {

    public List<String> toList(Object... obj) {
        List<String> list = new ArrayList<>();
        for (Object o : obj) {
            if (o == null) {
                continue;
            }
            list.add(o.toString());
        }
        return list;
    }

}
