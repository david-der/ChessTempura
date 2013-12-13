import java.io.*;
//import java.util.ArrayList;


public class Board {

    Board() {
    }


    public Piece[][] startingBoard() {

	Piece[][] board = new Piece[11][11];

	for(int i=0; i<11; ++i) { //row
	    for(int j=0; j<11; ++j) { //col
		board[i][j] = new Piece("open");;
	    }
	}

	//board [row][col]

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

	
	//I chose this layout so the pieces are intuitively positioned in row/column 1-8


	//black pieces
	board[1][1]= new Piece("bRook");
	board[1][2]= new Piece("bKnight");
	board[1][3]= new Piece("bBishop");
	board[1][4]= new Piece("bKing");
	board[1][5]= new Piece("bQueen");
	board[1][6]= new Piece("bBishop");
	board[1][7]= new Piece("bKnight");
	board[1][8]= new Piece("bRook");
	//black pawns
	board[2][1]= new Piece("bPawn");
	board[2][2]= new Piece("bPawn");
	board[2][3]= new Piece("bPawn");
	board[2][4]= new Piece("bPawn");
	board[2][5]= new Piece("bPawn");
	board[2][6]= new Piece("bPawn");
	board[2][7]= new Piece("bPawn");
	board[2][8]= new Piece("bPawn");
	//white pawns
	board[7][1]= new Piece("wPawn");
	board[7][2]= new Piece("wPawn");
	board[7][3]= new Piece("wPawn");
	board[7][4]= new Piece("wPawn");
	board[7][5]= new Piece("wPawn");
	board[7][6]= new Piece("wPawn");
	board[7][7]= new Piece("wPawn");
	board[7][8]= new Piece("wPawn");
	//white pieces
	board[8][1]= new Piece("wRook");
	board[8][2]= new Piece("wKnight");
	board[8][3]= new Piece("wBishop");
	board[8][4]= new Piece("wQueen");
	board[8][5]= new Piece("wKing");
	board[8][6]= new Piece("wBishop");
	board[8][7]= new Piece("wKnight");
	board[8][8]= new Piece("wRook");


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

}
