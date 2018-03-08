package com.battcn.managent.consumer.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Levin
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static final String PATH_REDIRECT_LOGOUT = "redirect:/login";
    static final String PATH_MAIN = "main";
    static final String PATH_LOGIN = "login";
    static final String DENIED = "denied";

    @Autowired
    protected HttpServletRequest request;
}
