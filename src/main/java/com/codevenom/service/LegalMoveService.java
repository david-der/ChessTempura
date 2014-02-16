package com.codevenom.service;

import com.codevenom.Board;
import com.codevenom.Piece;

/**
 * Copyright 2013 SWOL.co - Soccer Without Limits
 * User: davidder
 * Date: 12/24/13
 * Time: 12:04 PM
 */
public class LegalMoveService {

    private static boolean isWhitesTurn = true;

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

        //Board board_object = new Board();
        Piece[][] board = Board.startingBoard();

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


        //passed all the illegal move filters
        isWhitesTurn = !isWhitesTurn;
        return "legal";
    }
}
