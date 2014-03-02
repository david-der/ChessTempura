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

    public static String algebraicNotation(Move move) {

        String AN = "";

        //0. castle, otherwise:
        ////Assemble move name:
        //1. name1
        //2. x if capture
        //3. qualify col or row if ambiguous
        //4. end square
        //5. =Q if pawn promotion
        //6. + if check, # if checkmate

        if(move.getKingsideCastle() == true) {
            return "O-O";
        }
        if(move.getQueensideCastle() == true) {
            return "O-O-O";
        }

        int oldCol = Notation.col(move.getStartSquare());
        int oldRow = Notation.row(move.getStartSquare());
        int newCol = Notation.col(move.getEndSquare());
        int newRow = Notation.row(move.getEndSquare());

        Piece start_piece = State.board[newCol][newRow];//backwards bc move was made
        Piece end_piece   = State.board[oldCol][oldRow];
        //System.out.println("start, end pieces: " + start_piece.fullName + " " + end_piece.fullName);

        //1. name1
        if(! start_piece.name1.equals("p")) {
            AN += start_piece.name1;
        }

        //2. capture
        if(move.getWhiteMove() == true) {
            if(end_piece.color.equals("black")) {
                move.setCapture(true);
                AN += "x";
            }
        }
        else if(move.getWhiteMove() == false) {
            if(end_piece.color.equals("white")) {
                move.setCapture(true);
                AN += "x";
            }
        }

        //ambiguous move
        //if any piece of the same name can move to the same end square

        //does row need to be qualified, or does column need to be qualified


        //end square
        AN += move.getEndSquare();

        //pawn promotion
        if(move.getPawnPromotion() == true) {
            AN += "=Q";
        }

        // +check, #checkmate
        if(State.whiteInCheckMate || State.blackInCheckMate) {
            AN += "#";
        }
        else if(State.whiteInCheck || State.blackInCheck) {
            AN += "+";
        }

        move.setMoveNotation(AN);


        /*
        1   1
        2   1
        3   2
        4   2
        5   3
        6   3
        7
        8
        9





         */


        return AN;
    }


}
