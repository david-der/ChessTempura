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

		//black pieces
		board[1][1]= new Piece("wRook");
		board[1][2]= new Piece("wKnight");
		board[1][3]= new Piece("wBishop");
		board[1][4]= new Piece("wQueen");
		board[1][5]= new Piece("wKing");
		board[1][6]= new Piece("wBishop");
		board[1][7]= new Piece("wKnight");
		board[1][8]= new Piece("wRook");
		//black pawns
		board[2][1]= new Piece("wPawn");
		board[2][2]= new Piece("wPawn");
		board[2][3]= new Piece("wPawn");
		board[2][4]= new Piece("wPawn");
		board[2][5]= new Piece("wPawn");
		board[2][6]= new Piece("wPawn");
		board[2][7]= new Piece("wPawn");
		board[2][8]= new Piece("wPawn");
		//white pawns
		board[7][1]= new Piece("bPawn");
		board[7][2]= new Piece("bPawn");
		board[7][3]= new Piece("bPawn");
		board[7][4]= new Piece("bPawn");
		board[7][5]= new Piece("bPawn");
		board[7][6]= new Piece("bPawn");
		board[7][7]= new Piece("bPawn");
		board[7][8]= new Piece("bPawn");
		//white pieces
		board[8][1]= new Piece("bRook");
		board[8][2]= new Piece("bKnight");
		board[8][3]= new Piece("bBishop");
		board[8][4]= new Piece("bQueen");
		board[8][5]= new Piece("bKing");
		board[8][6]= new Piece("bBishop");
		board[8][7]= new Piece("bKnight");
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
                board[row][col] = new Piece();
            }
        }


		return board;
	}

    /*
	public void printBoard(Piece[][] board) {

		for(int r=0; r<11; ++r) {
			String row = "";
			for(int c=0; c<11; ++c) {
				row += " " + board[r][c].unicode;
			}
			//System.out.println(row);
			try{
				PrintStream out = new PrintStream (System.out, true , "UTF8" );
				out.println(row);
			}catch(UnsupportedEncodingException exception) {}

		}
	}


	public void showPieceNames() {
		try {
			String unicodeMessage =
					" \u2654" + "K" + // white king
							" \u2655" + "Q" + // white queen
							" \u2656" + "R" + // white rook
							" \u2657" + "B" + // white bishop
							" \u2658" + "N" + // white knight
							" \u2659" + "p" + // white pawn
							" \n" +
							" \u265A" + "K" + // black king
							" \u265B" + "Q" + // black queen
							" \u265C" + "R" + // black rook
							" \u265D" + "B" + // black bishop
							" \u265E" + "N" + // black knight
							" \u265F" + "p" + // black pawn
							"\n" ;
			PrintStream out = new PrintStream (System.out, true , "UTF8" );
			out.println(unicodeMessage);
		}
		catch (UnsupportedEncodingException exception) {
		}
	}

	
	public Piece[][]  getEmptyBoard() {
		
		Piece[][] board = new Piece[11][11];

		for(int i=0; i<11; ++i) { //row
			for(int j=0; j<11; ++j) { //col
				board[i][j] = new Piece("open");;
			}
		}

		//miscellaneous corner characters
		board[10][10]= new Piece("blank");
		board[0][10] = new Piece("blank");
		board[10][0] = new Piece("blank");
		board[9][10] = new Piece("blank");
		board[10][9] = new Piece("blank");

		//top and bottom border
		board[0][1]= new Piece("topBorder");
		board[0][2]= new Piece("topBorder");
		board[0][3]= new Piece("topBorder");
		board[0][4]= new Piece("topBorder");
		board[0][5]= new Piece("topBorder");
		board[0][6]= new Piece("topBorder");
		board[0][7]= new Piece("topBorder");
		board[0][8]= new Piece("topBorder");
		board[9][1]= new Piece("bottomBorder");
		board[9][2]= new Piece("bottomBorder");
		board[9][3]= new Piece("bottomBorder");
		board[9][4]= new Piece("bottomBorder");
		board[9][5]= new Piece("bottomBorder");
		board[9][6]= new Piece("bottomBorder");
		board[9][7]= new Piece("bottomBorder");
		board[9][8]= new Piece("bottomBorder");
		//left and right border
		board[1][0]= new Piece("leftBorder");
		board[2][0]= new Piece("leftBorder");
		board[3][0]= new Piece("leftBorder");
		board[4][0]= new Piece("leftBorder");
		board[5][0]= new Piece("leftBorder");
		board[6][0]= new Piece("leftBorder");
		board[7][0]= new Piece("leftBorder");
		board[8][0]= new Piece("leftBorder");
		board[1][9]= new Piece("rightBorder");
		board[2][9]= new Piece("rightBorder");
		board[3][9]= new Piece("rightBorder");
		board[4][9]= new Piece("rightBorder");
		board[5][9]= new Piece("rightBorder");
		board[6][9]= new Piece("rightBorder");
		board[7][9]= new Piece("rightBorder");
		board[8][9]= new Piece("rightBorder");

		//row label, number
		board[1][10]= new Piece("8");
		board[2][10]= new Piece("7");
		board[3][10]= new Piece("6");
		board[4][10]= new Piece("5");
		board[5][10]= new Piece("4");
		board[6][10]= new Piece("3");
		board[7][10]= new Piece("2");
		board[8][10]= new Piece("1");
		//column label, letter
		board[10][1]= new Piece("a");
		board[10][2]= new Piece("b");
		board[10][3]= new Piece("c");
		board[10][4]= new Piece("d");
		board[10][5]= new Piece("e");
		board[10][6]= new Piece("f");
		board[10][7]= new Piece("g");
		board[10][8]= new Piece("h");

		return board;
		
	}
	
	public Piece[][]  getRandomBoardWithSpecificPieces(String list_pieces) { //wPawn, wRook, bRook.  both Kings by default.

		Piece[][] board = getEmptyBoard();
		
		//can't have more than the max available pieces on the Board
		ArrayList<String> availablePieces = new ArrayList<String>();
		availablePieces.add("wKing");	    availablePieces.add("bKing");	
		availablePieces.add("wQueen");	availablePieces.add("bQueen");	
		availablePieces.add("wRook");	availablePieces.add("bRook");	
		availablePieces.add("wRook");	availablePieces.add("bRook");	
		availablePieces.add("wBishop");	availablePieces.add("bBishop");	
		availablePieces.add("wBishop");	availablePieces.add("bBishop");	
		availablePieces.add("wKnight");	availablePieces.add("bKnight");	
		availablePieces.add("wKnight");	availablePieces.add("bKnight");	
		for(int i=0; i<8; ++i) {
			availablePieces.add("wPawn");
			availablePieces.add("bPawn");
		}

		//Adding desired pieces to array
		ArrayList<String> tokens = new ArrayList<String>();
		String[] split_nonalpha = list_pieces.split("\\P{Alpha}+");
		for(int i=0; i< split_nonalpha.length; ++i) {
			String token = split_nonalpha[i];
			if( ! token.equals("") ) {
				if(availablePieces.remove(token)) {
					tokens.add(token);
				}
			}
		}
		//two kings are always needed
		if(availablePieces.remove("wKing")) {
			tokens.add("wKing");
		}
		if(availablePieces.remove("bKing")) {
			tokens.add("bKing");
		}
		
		//getting enough random numbers to assign the pieces to unique row/col on the board
		HashSet<int[]> randomRowCol = new HashSet<int[]>();
		
		for(String pieceName: tokens) {
			Boolean free_square = false;
			while( ! free_square ) {
				
				int row = randomPosition();
				int col = randomPosition();
				
				//dont allow Pawns on back rank or front rank!
				if(pieceName.equals("wPawn")) {
					row = Math.max(row, 3); //don't want it too close to promotion
					row = Math.min(row, 7); //can't go backward
					System.out.println("white row " + row );
				}
				if(pieceName.equals("bPawn")) {
					row = Math.max(row, 2); //can't go backward
					row = Math.min(row, 6); //don't want it too close to promotion
					System.out.println("black row " + row );
				}
				int[]row_col = {row, col};

				if( ! randomRowCol.contains(row_col)) {
					randomRowCol.add(row_col);
					board[row][col] = new Piece(pieceName);
					free_square = true;
				}
			}
		}
		
		
		//dont allow two Bishops on same color square!
		//dark square if (row+col) % 2 == 0
		//light square if (row+col) % 2 == 1
		//i don't feel like implementing the logic for this scenario
		
		
		return board;
		
	}
	
	public int randomPosition() {
	
		Random generator = new Random(); 
		int r = generator.nextInt(10000);
		
		return (r % 8) + 1;
	}

	*/
}
