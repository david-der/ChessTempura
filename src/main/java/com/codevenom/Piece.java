package com.codevenom;

//import java.util.ArrayList;

public class Piece {

	public String color = "";
    public String name1 = "";
    public String fullName = "";
    public int value = 0;


	Piece() {
		this.name1 = " ";
        this.fullName = "empty";
	}

	Piece(String pieceName) {
		this.fullName = pieceName;
		if( pieceName.length() == 1 ) { //a-h, 1-8 row/column labels
			this.name1 = pieceName;
		}
		else if(pieceName.equals("wKing")) {
			this.color = "white";
			this.name1 = "K";
			this.value = 9999;
		}
		else if(pieceName.equals("wQueen")) {
			this.color = "white";
			this.name1 = "Q";
			this.value = 9;
		}
		else if(pieceName.equals("wRook")) {
			this.color = "white";
			this.name1 = "R";
			this.value = 5;
		}
		else if(pieceName.equals("wBishop")) {
			this.color = "white";
			this.name1 = "B";
			this.value = 3;
		}
		else if(pieceName.equals("wKnight")) {
			this.color = "white";
			this.name1 = "N";
			this.value = 3;
		}
		else if(pieceName.equals("wPawn")) {
			this.color = "white";
			this.name1 = "p";
			this.value = 1;
		}
		else if(pieceName.equals("bKing")) {
			this.color = "black";
			this.name1 = "K";
			this.value = 9999;
		}
		else if(pieceName.equals("bQueen")) {
			this.color = "black";
			this.name1 = "Q";
			this.value = 9;
		}
		else if(pieceName.equals("bRook")) {
			this.color = "black";
			this.name1 = "R";
			this.value = 5;
		}
		else if(pieceName.equals("bBishop")) {
			this.color = "black";
			this.name1 = "B";
			this.value = 3;
		}
		else if(pieceName.equals("bKnight")) {
			this.color = "black";
			this.name1 = "N";
			this.value = 3;
		}
		else if(pieceName.equals("bPawn")) {
			this.color = "black";
			this.name1 = "p";
			this.value = 1;
		}
	}



}
