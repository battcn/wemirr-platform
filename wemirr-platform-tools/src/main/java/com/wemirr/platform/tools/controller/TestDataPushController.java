package com.wemirr.platform.tools.controller;

import com.wemirr.framework.commons.entity.Result;
import com.wemirr.framework.security.client.annotation.IgnoreAuthorize;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @author Levin
 */
@Slf4j
@RestController
@RequestMapping("/test_data_push")
@RequiredArgsConstructor
@Validated
public class TestDataPushController {


    @SneakyThrows
    @IgnoreAuthorize
    @PostMapping
    public Result<ResponseEntity<Void>> listen(String title, HttpServletRequest request) {
        final InputStream bodyStream = request.getInputStream();
        final String body = IOUtils.toString(bodyStream);
        log.info("[url params] - {}", request.getParameterMap());
        log.info("[body] - {}", body);
        return Result.success();
    }


}
