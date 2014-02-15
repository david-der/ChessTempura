package com.codevenom.controller;

import com.codevenom.service.LegalMoveService;
import com.firebase.client.Firebase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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

        Firebase firebase = new Firebase("https://chesstempura.firebaseIO-demo.com/");

        String msg = "Hello there, brothers";
        return new ModelAndView("hello","message1",msg);
    }

    @RequestMapping(value="/makeMove.htm",method= RequestMethod.POST)
    public @ResponseBody
    boolean makeMove(@RequestParam Map<String, String> params) {

        String startSquare = params.get("startSquare");
        String endSquare = params.get("endSquare");
        String piece = params.get("piece");

        System.out.println("Start square: " + startSquare);
        System.out.println("End square: " + endSquare);
        System.out.println("test");
        System.out.println("piece: " + piece);

        boolean isLegal = LegalMoveService.isThisMoveLegal(startSquare, endSquare, piece);

        System.out.println("isLegal: " + isLegal);


        return isLegal;
    }

}
