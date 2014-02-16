package com.codevenom.service;

/**
 * Copyright 2013 SWOL.co - Soccer Without Limits
 * User: davidder
 * Date: 12/24/13
 * Time: 12:04 PM
 */
public class LegalMoveService {

    public static String isThisMoveLegal(String start, String end, String piece) {
        System.out.println("Received move " + piece + " from " + start + " to " + end);

        String color = piece.substring(0, 1);
        if(color.equals("w")) {
            return "legal";
        }
        else {
            return "illegal";
        }

    }
}
