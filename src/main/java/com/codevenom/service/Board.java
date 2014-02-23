package com.codevenom.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;

public class Board {

    public static boolean Check = false;
    public static boolean CheckMate = false;

	public Board() {
	}

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


	public static Piece[][] startingBoard() {

		Piece[][] board = new Piece[9][9];

        //[col][row] for consistency with chess square names

		//black pieces
		board[1][1]= new Piece("wRook");
		board[2][1]= new Piece("wKnight");
		board[3][1]= new Piece("wBishop");
		board[4][1]= new Piece("wQueen");
		board[5][1]= new Piece("wKing");
		board[6][1]= new Piece("wBishop");
		board[7][1]= new Piece("wKnight");
		board[8][1]= new Piece("wRook");
		//black pawns
		board[1][2]= new Piece("wPawn");
		board[2][2]= new Piece("wPawn");
		board[3][2]= new Piece("wPawn");
		board[4][2]= new Piece("wPawn");
		board[5][2]= new Piece("wPawn");
		board[6][2]= new Piece("wPawn");
		board[7][2]= new Piece("wPawn");
		board[8][2]= new Piece("wPawn");
		//white pawns
		board[1][7]= new Piece("bPawn");
		board[2][7]= new Piece("bPawn");
		board[3][7]= new Piece("bPawn");
		board[4][7]= new Piece("bPawn");
		board[5][7]= new Piece("bPawn");
		board[6][7]= new Piece("bPawn");
		board[7][7]= new Piece("bPawn");
		board[8][7]= new Piece("bPawn");
		//white pieces
		board[1][8]= new Piece("bRook");
		board[2][8]= new Piece("bKnight");
		board[3][8]= new Piece("bBishop");
		board[4][8]= new Piece("bQueen");
		board[5][8]= new Piece("bKing");
		board[6][8]= new Piece("bBishop");
		board[7][8]= new Piece("bKnight");
		board[8][8]= new Piece("bRook");

        board[0][0]= new Piece();
        board[0][1]= new Piece();
        board[0][2]= new Piece();
        board[0][3]= new Piece();
        board[0][4]= new Piece();
        board[0][5]= new Piece();
        board[0][6]= new Piece();
        board[0][7]= new Piece();
        board[0][8]= new Piece();
        board[1][0]= new Piece();
        board[2][0]= new Piece();
        board[3][0]= new Piece();
        board[4][0]= new Piece();
        board[5][0]= new Piece();
        board[6][0]= new Piece();
        board[7][0]= new Piece();
        board[8][0]= new Piece();

        for(int row=3; row<=6; ++row) {
            for(int col=1; col<=8; ++col) {
                board[col][row] = new Piece();
            }
        }


		return board;
	}

}
