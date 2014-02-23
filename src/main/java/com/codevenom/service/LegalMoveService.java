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

        System.out.println("white's turn? " + isWhitesTurn);

        String color = piece.substring(0, 1);
        if(isWhitesTurn) {
            if(color.equals("b")) {
                System.out.println("white to move.");
                return "illegal";
            }
        }
        else if(!isWhitesTurn) {
            if(color.equals("w")) {
                System.out.println("black to move.");
                return "illegal";
            }
        }

        int oldCol = Board.col(start);
        int oldRow = Board.row(start);
        int newCol = Board.col(end);
        int newRow = Board.row(end);

        System.out.println("old square integers:" + oldCol + " " + oldRow);
        System.out.println("new square integers:" + newCol + " " + newRow);

        //Piece newpiece = new Piece();
        //newpiece = board[newRow][newCol];
        //System.out.println("new " + newpiece.fullName);

        //can't take your own piece
        if(isWhitesTurn && board[newRow][newCol].color.equals("white")) {
            return "illegal";
        }
        if(!isWhitesTurn && board[newRow][newCol].color.equals("black")) {
            return "illegal";
        }

        Piece p = board[oldRow][oldCol];

        if(p.name1.equals("K")) {
            //castling
            if(color.equals("w")) {
                if(start.equals("e1") && end.equals("g1")) { //kingside castle
                    if(!board[1][6].fullName.equals("empty") || !board[1][7].fullName.equals("empty")) {
                        System.out.println("can't castle kingside through a piece");
                        return "illegal";
                    }
                    if(p.hasMoved == true) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!board[1][8].fullName.equals("wRook") || board[1][8].hasMoved) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal white castle");
                    return "legal";
                }
                else if(start.equals("e1") && end.equals("c1")) { //queenside castle
                    if(!board[1][2].fullName.equals("empty") || !board[1][3].fullName.equals("empty") || !board[1][4].fullName.equals("empty") ) {
                        System.out.println("can't castle queenside through a piece");
                        return "illegal";
                    }
                    if(p.hasMoved == true) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!board[1][1].fullName.equals("wRook") || board[1][1].hasMoved) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal white castle");
                    return "legal";
                }
            }
            else if(color.equals("b")) {
                if(start.equals("e8") && end.equals("g8")) { //kingside castle
                    if(!board[8][6].fullName.equals("empty") || !board[8][7].fullName.equals("empty")) {
                        System.out.println("can't castle kingside through a piece");
                        return "illegal";
                    }
                    if(p.hasMoved == true) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!board[8][8].fullName.equals("bRook") || board[8][8].hasMoved) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal black castle");
                    return "legal";
                }
                else if(start.equals("e8") && end.equals("c8")) { //queenside castle
                    if(!board[8][2].fullName.equals("empty") || !board[8][3].fullName.equals("empty") || !board[8][4].fullName.equals("empty") ) {
                        System.out.println("can't castle queenside through a piece");
                        return "illegal";
                    }
                    if(p.hasMoved == true) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!board[8][1].fullName.equals("bRook") || board[8][1].hasMoved) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal black castle");
                    return "legal";
                }
            }

            //regular king move
            //won't be evaluated if successful castle, because a castle breaks this >1 square rule
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
            if(p.color.equals("white")) {
                if( abs(newCol-oldCol) > 1 ) {
                    System.out.println("illegal pawn move. too far horizontally");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 1 && !board[newRow][newCol].color.equals("black") ) {
                    System.out.println("illegal pawn move. can't change column if not attacking");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 0 && board[newRow][newCol].color.equals("black") ) {
                    System.out.println("illegal pawn move. can't attack straight");
                    return "illegal";
                }
                else if( newRow - oldRow < 0 ) {
                    System.out.println("illegal pawn move. backwards");
                    return "illegal";
                }
                else if( newRow - oldRow > 2) {
                    System.out.println("illegal pawn move. too far");
                    return "illegal";
                }
                else if(oldRow != 2 && abs(newRow - oldRow) == 2) {
                    System.out.println("illegal pawn move. can only move 2 spaces from starting square");
                    return "illegal";
                }
                else if(board[newRow][newCol].color.equals("black") && ( abs(newCol-oldCol) != 1 || newRow-oldRow != 1  )  ) {
                    System.out.println("illegal pawn move. incorrect attack");
                    return "illegal";
                }
            }
            else if(p.color.equals("black")) {
                if( abs(newCol-oldCol) > 1 ) {
                    System.out.println("illegal pawn move. too far horizontally");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 1 && !board[newRow][newCol].color.equals("white") ) {
                    System.out.println("illegal pawn move. can't change column if not attacking");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 0 && board[newRow][newCol].color.equals("white") ) {
                    System.out.println("illegal pawn move. can't attack straight");
                    return "illegal";
                }
                else if( oldRow - newRow < 0 ) {
                    System.out.println("illegal pawn move. backwards");
                    return "illegal";
                }
                else if( oldRow - newRow > 2) {
                    System.out.println("illegal pawn move. too far");
                    return "illegal";
                }
                else if(oldRow != 7 && abs(newRow - oldRow) == 2) {
                    System.out.println("illegal pawn move. can only move 2 spaces from starting square");
                    return "illegal";
                }
                else if(board[newRow][newCol].color.equals("white") && ( abs(newCol-oldCol) != 1 || oldRow-newRow != 1  )  ) {
                    System.out.println("illegal pawn move. incorrect attack");
                    return "illegal";
                }
            }

        } //pawn check

        if(!isClearPath(start, end, p.name1)) { //incomplete
            return "illegal";
        }



        //passed all the illegal move filters

        Piece previous_new = board[newRow][newCol];
        board[newRow][newCol] = board[oldRow][oldCol];
        board[oldRow][oldCol] = previous_new;

        isWhitesTurn = !isWhitesTurn;
        p.hasMoved = true;
        return "legal";
    }

    private static boolean isClearPath(String start, String end, String p) {
        int oldCol = Board.col(start);
        int oldRow = Board.row(start);
        int newCol = Board.col(end);
        int newRow = Board.row(end);

        //not relevant for King, which is only a capture or self-obstruction

        //not relevant for Knight

    //bishop down right
        //black rook, why does color matter?


        //Rook
        if(p.equals("R")) {
            if(newRow > oldRow) { //up
                for(int i=oldRow+1; i<newRow; ++i) {
                    System.out.println("rook path up: " + newCol + i + " " + board[i][newCol].fullName);
                    if(!board[i][newCol].fullName.equals("empty")){
                        return false;
                    }
                }
            }
            else if(oldRow > newRow) { //down
                for(int i=oldRow-1; i>newRow; --i) {
                    System.out.println("rook path down: " + newCol + i + " " + board[i][newCol].fullName);
                    if(!board[i][newCol].fullName.equals("empty")){
                        return false;
                    }
                }
            }
            else if(oldCol > newCol) { //left
                for(int i=oldCol-1; i>newCol; --i) {
                    System.out.println("rook path left: " + newCol + i + " " + board[newRow][i].fullName);
                    if(!board[newRow][i].fullName.equals("empty")){
                        return false;
                    }
                }
            }
            else if(newCol > oldCol) { //right
                for(int i=oldCol+1; i<newCol; ++i) {
                    System.out.println("rook path right: " + newCol + i + " " + board[newRow][i].fullName);
                    if(!board[newRow][i].fullName.equals("empty")){
                        return false;
                    }
                }
            }
        }
        else if(p.equals("B")){
            if(newRow > oldRow && newCol > oldCol) { //up-right, row increasing, column increasing
                int pathRow = oldRow+1;
                for(int i=oldCol+1; i<newCol; ++i) {
                    System.out.println("bishop path up-right: " + i + pathRow + " " + board[pathRow][i].fullName);
                    if(!board[pathRow][i].fullName.equals("empty")){
                        return false;
                    }
                    pathRow++;
                }
            }
            else if(newRow > oldRow && oldCol > newCol) { //up-left: row increasing, column decreasing
                int pathRow = oldRow+1;
                for(int i=oldCol-1; i>newCol; --i) {
                    System.out.println("bishop path up-left: " + i + pathRow + " " + board[pathRow][i].fullName);
                    if(!board[pathRow][i].fullName.equals("empty")){
                        return false;
                    }
                    pathRow++;
                }
            }
            else if(oldRow > newRow && newCol > oldCol) { //down-right, row decreasing, column increasing
                int pathRow = oldRow-1;
                for(int i=oldCol+1; i<newCol; ++i) {
                    System.out.println("bishop path down-right: " + i + pathRow + " " + board[pathRow][i].fullName);
                    if(!board[pathRow][i].fullName.equals("empty")){
                        return false;
                    }
                    pathRow--;
                }
            }
            else if(oldRow > newRow && oldCol > newCol) { //down-left
                int pathRow = oldRow-1;
                for(int i=oldCol-1; i>newCol; --i) {
                    System.out.println("bishop path down-left: " + i + pathRow + " " + board[pathRow][i].fullName);
                    if(!board[pathRow][i].fullName.equals("empty")){
                        return false;
                    }
                    pathRow--;
                }
            }
        }
        else if(p.equals("Q")) {
            int changeCol = abs(newCol - oldCol);
            int changeRow = abs(newRow - oldRow);
            if( changeCol > 0 && changeRow > 0) {
                //Bishop rules for queen
                if(newRow > oldRow && newCol > oldCol) { //up-right, row increasing, column increasing
                    int pathRow = oldRow+1;
                    for(int i=oldCol+1; i<newCol; ++i) {
                        System.out.println("queen path up-right: " + i + pathRow + " " + board[pathRow][i].fullName);
                        if(!board[pathRow][i].fullName.equals("empty")){
                            return false;
                        }
                        pathRow++;
                    }
                }
                else if(newRow > oldRow && oldCol > newCol) { //up-left: row increasing, column decreasing
                    int pathRow = oldRow+1;
                    for(int i=oldCol-1; i>newCol; --i) {
                        System.out.println("queen path up-left: " + i + pathRow + " " + board[pathRow][i].fullName);
                        if(!board[pathRow][i].fullName.equals("empty")){
                            return false;
                        }
                        pathRow++;
                    }
                }
                else if(oldRow > newRow && newCol > oldCol) { //down-right, row decreasing, column increasing
                    int pathRow = oldRow-1;
                    for(int i=oldCol+1; i<newCol; ++i) {
                        System.out.println("queen path down-right: " + i + pathRow + " " + board[pathRow][i].fullName);
                        if(!board[pathRow][i].fullName.equals("empty")){
                            return false;
                        }
                        pathRow--;
                    }
                }
                else if(oldRow > newRow && oldCol > newCol) { //down-left
                    int pathRow = oldRow-1;
                    for(int i=oldCol-1; i>newCol; --i) {
                        System.out.println("queen path down-left: " + i + pathRow + " " + board[pathRow][i].fullName);
                        if(!board[pathRow][i].fullName.equals("empty")){
                            return false;
                        }
                        pathRow--;
                    }
                }
            }
            else {
                //Rook rules for queen
                if(newRow > oldRow) { //up
                    for(int i=oldRow+1; i<newRow; ++i) {
                        System.out.println("queen path up: " + newCol + i + " " + board[i][newCol].fullName);
                        if(!board[i][newCol].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldRow > newRow) { //down
                    for(int i=oldRow-1; i>newRow; --i) {
                        System.out.println("queen path down: " + newCol + i + " " + board[i][newCol].fullName);
                        if(!board[i][newCol].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldCol > newCol) { //left
                    for(int i=oldCol-1; i>newCol; --i) {
                        System.out.println("queen path left: " + newCol + i + " " + board[newRow][i].fullName);
                        if(!board[newRow][i].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(newCol > oldCol) { //right
                    for(int i=oldCol+1; i<newCol; ++i) {
                        System.out.println("queen path right: " + newCol + i + " " + board[newRow][i].fullName);
                        if(!board[newRow][i].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
            }
        }
        else if(p.equals("p")){
            //pawn
            if(abs(newRow-oldRow) == 2) {
                if(newRow > oldRow) { //up
                    for(int i=oldRow+1; i<newRow; ++i) {
                        System.out.println("pawn path 2 up: " + newCol + i + " " + board[i][newCol].fullName);
                        if(!board[i][newCol].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldRow > newRow) { //down
                    for(int i=oldRow-1; i>newRow; --i) {
                        System.out.println("pawn path 2 down: " + newCol + i + " " + board[i][newCol].fullName);
                        if(!board[i][newCol].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
            }
        }


        return true;
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
