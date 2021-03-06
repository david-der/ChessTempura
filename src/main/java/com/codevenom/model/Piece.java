package com.codevenom.model;

//import java.util.ArrayList;

public class Piece {

	private String color = "";
    private String name1 = "";
    private String fullName = "empty";
    private int value = 0;
    private boolean hasMoved = false;


	public Piece() {
		this.name1 = " ";
        this.fullName = "empty";
        this.color = "none";
        this.value = 0;
        this.hasMoved = false;
	}

    public Piece(Piece current) {
        this.name1 = current.name1;
        this.fullName = current.fullName;
        this.color = current.color;
        this.value = current.value;
        this.hasMoved = current.hasMoved;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Piece(String pieceName) {
		this.fullName = pieceName;
        this.hasMoved = false;
        if(pieceName.equals("wKing")) {
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
