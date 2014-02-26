package com.codevenom.model;

//import java.util.ArrayList;

public class Notation {

    //helper function, get col/row from square name
    public static int row(String square) { //for example, square="e2"
        return Integer.valueOf(square.substring(1,2));
    }
    public static int col(String square) {
        if(square.substring(0,1).equals("a")) { return 1; }
        if(square.substring(0,1).equals("b")) { return 2; }
        if(square.substring(0,1).equals("c")) { return 3; }
        if(square.substring(0,1).equals("d")) { return 4; }
        if(square.substring(0,1).equals("e")) { return 5; }
        if(square.substring(0,1).equals("f")) { return 6; }
        if(square.substring(0,1).equals("g")) { return 7; }
        if(square.substring(0,1).equals("h")) { return 8; }
        return 0;
    }
    //helper function, get square name from col/row
    public static String square(int col, int row) {
        String r = Integer.toString(row);
        String c = "";
        if(col == 1) { c = "a"; }
        if(col == 2) { c = "b"; }
        if(col == 3) { c = "c"; }
        if(col == 4) { c = "d"; }
        if(col == 5) { c = "e"; }
        if(col == 6) { c = "f"; }
        if(col == 7) { c = "g"; }
        if(col == 8) { c = "h"; }
        String square = c + r;
        return square;
    }

    public static String algebraicNotation(String start, String end, String piece) {


        
        return "temp";
    }


}
