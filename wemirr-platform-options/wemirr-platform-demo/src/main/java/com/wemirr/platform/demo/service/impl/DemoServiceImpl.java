package com.wemirr.platform.demo.service.impl;

import com.wemirr.platform.demo.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author Levin
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello() {
        return "hello world";
    }


}
