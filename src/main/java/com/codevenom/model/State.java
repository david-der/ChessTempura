package com.codevenom.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;

public class State {

    public static boolean isWhitesTurn = true;
    public static boolean whiteInCheck = false; //white must move out of check
    public static boolean blackInCheck = false; //black must move out of check
    public static boolean whiteInCheckMate = false; //white loses
    public static boolean blackInCheckMate = false; //black loses
    public static Piece[][] board = startingBoard();

    public State() {
        isWhitesTurn = true;
        whiteInCheck = false;
        blackInCheck = false;
        whiteInCheckMate = false;
        blackInCheckMate = false;
        board = startingBoard();
	}

	public static Piece[][] startingBoard() {
        isWhitesTurn = true;

        whiteInCheck = false; //white must move out of check
        blackInCheck = false; //black must move out of check

        whiteInCheckMate = false; //white loses
        blackInCheckMate = false; //black loses
		board = new Piece[9][9];

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
