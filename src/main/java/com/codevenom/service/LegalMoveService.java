package com.codevenom.service;

//import com.codevenom.Board;
//import com.codevenom.Piece;

/**
 * Copyright 2013 SWOL.co - Soccer Without Limits
 * User: davidder
 * Date: 12/24/13
 * Time: 12:04 PM
 */
public class LegalMoveService {

    private static boolean isWhitesTurn = true;
    private static Piece[][] board = Board.startingBoard();

    public static String isThisMoveLegal(String start, String end, String piece) {
        System.out.println("Received move " + piece + " from " + start + " to " + end);

        //There are many ways a move can be illegal.
        //Only return "legal" if the move passes all the illegal filters.


        String color = piece.substring(0, 1);
        if(isWhitesTurn) {
            if(color.equals("b")) {
                return "illegal";
            }
        }
        else if(!isWhitesTurn) {
            if(color.equals("w")) {
                return "illegal";
            }
        }

        int oldCol = Board.col(start);
        int oldRow = Board.row(start);
        int newCol = Board.col(end);
        int newRow = Board.row(end);

        System.out.println("old square integers:" + oldCol + " " + oldRow);
        System.out.println("new square integers:" + newCol + " " + newRow);

        //can't take your own piece
        if(isWhitesTurn && board[oldRow][oldCol].color.equals("white")) {
            return "illegal";
        }
        if(!isWhitesTurn && board[oldRow][oldCol].color.equals("black")) {
            return "illegal";
        }

        Piece p = board[oldRow][oldCol];

        if(p.name1.equals("K")) {
            if( abs(newCol-oldCol) > 1 || abs(newRow-oldRow) > 1) {
                System.out.println("illegal King move");
                return "illegal";
            }
        }
        else if(p.name1.equals("R")) {
            if( abs(newCol-oldCol) > 0 && abs(newRow-oldRow) > 0 ) {
                System.out.println("illegal Rook move. can only move along files and ranks");
                return "illegal";
            }
        }
        else if(p.name1.equals("B")) {
            if( abs(newCol-oldCol) != abs(newRow-oldRow) ) {
                System.out.println("illegal Bishop move. can only move along diagonal");
                return "illegal";
            }
        }
        else if(p.name1.equals("Q")) {
            if( abs(newCol-oldCol) > 0 && abs(newRow-oldRow) > 0 ) { //if either row or column doesnt change, it's a Rook move.  if both change:
                if( abs(newCol-oldCol) != abs(newRow-oldRow) ) {
                    System.out.println("illegal Queen move. can only move along files and ranks, or along a diagonal");
                    return "illegal";
                }
            }
        }
        else if(p.name1.equals("N")) {
            if( abs(newCol-oldCol) == 1 && abs(newRow-oldRow) == 2 ) {
                //legal
            }
            else if( abs(newCol-oldCol) == 2 && abs(newRow-oldRow) == 1 ) {
                //legal
            }
            else {
                System.out.println("illegal Knight move. can only move 2x1 or 1x2");
                return "illegal";
            }
        }
        else if(p.name1.equals("p")) {//pawns are the tricky pieces


        }




        //passed all the illegal move filters

        Piece previous_new = board[newRow][newCol];
        board[newRow][newCol] = board[oldRow][oldCol];
        board[oldRow][oldCol] = previous_new;

        isWhitesTurn = !isWhitesTurn;
        return "legal";
    }

    private static int abs(int x) {
        if (x < 0) {
            x *= -1;
            return x;
        }
        else{
            return x;
        }
    }
}
