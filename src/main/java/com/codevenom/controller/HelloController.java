package com.codevenom.controller;

import com.codevenom.service.Board;
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
    String makeMove(@RequestParam Map<String, String> params) {

        String startSquare = params.get("startSquare");
        String endSquare = params.get("endSquare");
        String piece = params.get("piece");

        String isLegal = LegalMoveService.isThisMoveLegal(startSquare, endSquare, piece, false);

        return isLegal;
    }

    @RequestMapping(value="/isCheck.htm",method= RequestMethod.POST)
    public @ResponseBody
    String isCheck(@RequestParam Map<String, String> params) {
        String is_check = LegalMoveService.isCheck();
        return is_check;
    }

    @RequestMapping(value="/initializeBoard.htm",method= RequestMethod.POST)
    public @ResponseBody
    String initializeBoard(@RequestParam Map<String, String> params) {
        Board board = new Board();
        return "initialized";
    }

}
