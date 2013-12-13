//import java.util.ArrayList;

public class Piece {

    String color = "";
    String unicode = "";
    String name1 = "";
    String fullName = "";
    int value = 0;


    Piece() {
	this.unicode = " .";
	this.name1 = " .";
    }

    Piece(String pieceName) {
	this.fullName = pieceName;
	if( pieceName.length() == 1 ) { //a-h, 1-8 row/column labels
	    this.unicode = pieceName;
	    this.name1 = pieceName;
	}
	else if( pieceName.equals("open")) {
	    this.unicode = ".";
	    this.name1 = ".";
	}
	else if( pieceName.equals("blank")) {
	    this.unicode = " ";
	    this.name1 = " ";
	}
	else if( pieceName.equals("topBorder") || pieceName.equals("bottomBorder")) {
	    this.unicode = "_";
	    this.name1 = "_";
	}
	else if( pieceName.equals("leftBorder") || pieceName.equals("rightBorder")) {
	    this.unicode = "|";
	    this.name1 = "|";
	}
	else if(pieceName.equals("wKing")) {
	    this.color = "white";
	    this.unicode = "\u2654";
	    this.name1 = "K";
	    this.value = 9999;
	}
	else if(pieceName.equals("wQueen")) {
	    this.color = "white";
	    this.unicode = "\u2655";
	    this.name1 = "Q";
	    this.value = 9;
	}
	else if(pieceName.equals("wRook")) {
	    this.color = "white";
	    this.unicode = "\u2656";
	    this.name1 = "R";
	    this.value = 5;
	}
	else if(pieceName.equals("wBishop")) {
	    this.color = "white";
	    this.unicode = "\u2657";
	    this.name1 = "B";
	    this.value = 3;
	}
	else if(pieceName.equals("wKnight")) {
	    this.color = "white";
	    this.unicode = "\u2658";
	    this.name1 = "N";
	    this.value = 3;
	}
	else if(pieceName.equals("wPawn")) {
	    this.color = "white";
	    this.unicode = "\u2659";
	    this.name1 = "p";
	    this.value = 1;
	}
	else if(pieceName.equals("bKing")) {
	    this.color = "black";
	    this.unicode = "\u265A";
	    this.name1 = "K";
	    this.value = 9999;
	}
	else if(pieceName.equals("bQueen")) {
	    this.color = "black";
	    this.unicode = "\u265B";
	    this.name1 = "Q";
	    this.value = 9;
	}
	else if(pieceName.equals("bRook")) {
	    this.color = "black";
	    this.unicode = "\u265C";
	    this.name1 = "R";
	    this.value = 5;
	}
	else if(pieceName.equals("bBishop")) {
	    this.color = "black";
	    this.unicode = "\u265D";
	    this.name1 = "B";
	    this.value = 3;
	}
	else if(pieceName.equals("bKnight")) {
	    this.color = "black";
	    this.unicode = "\u265E";
	    this.name1 = "N";
	    this.value = 3;
	}
	else if(pieceName.equals("bPawn")) {
	    this.color = "black";
	    this.unicode = "\u265F";
	    this.name1 = "p";
	    this.value = 1;
	}
    }



}
