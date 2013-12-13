package com.codevenom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright 2013 SWOL.co - Soccer Without Limits
 * User: davidder
 * Date: 12/13/13
 * Time: 4:01 PM
 */
@Controller
public class HelloController {

    @RequestMapping("/helloworld")
    public ModelAndView helloWorld() {
        String msg = "Hello there, brothers";
        return new ModelAndView("hello","message1",msg);
    }
}
