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
    private static boolean lookingForMate = false;
    private static Piece[][] board = Board.startingBoard();

    public static String isThisMoveLegal(String start, String end, String piece, boolean checkOnly) {
        //System.out.println("Received move " + piece + " from " + start + " to " + end);

        int oldCol = Board.col(start);
        int oldRow = Board.row(start);
        int newCol = Board.col(end);
        int newRow = Board.row(end);
        String color = piece.substring(0, 1);

        //System.out.println("old square integers:" + oldCol + " " + oldRow);
        //System.out.println("new square integers:" + newCol + " " + newRow);

        String moveType = "legal";   //the end of this function returns moveType
        /////////// possible values:
        //legal
        //white_kingside_castle
        //white_queenside_castle
        //black_kingside_castle
        //black_queenside_castle
        //white_pawn_promotion
        //black_pawn_promotion

        ////////////////////////////////////////////////////////////////////////
        // There are many ways a move can be illegal.
        // Only return "legal" if the move passes all the illegal filters.
        ////////////////////////////////////////////////////////////////////////

        if(! checkOnly) {  // whose turn it is becomes backwards when looking for check
            if(isWhitesTurn) {
                if(color.equals("b")) {
                    //System.out.println("white to move.");
                    return "illegal";
                }
            }
            else if(!isWhitesTurn) {
                if(color.equals("w")) {
                    //System.out.println("black to move.");
                    return "illegal";
                }
            }
        }


        Piece p = board[oldCol][oldRow];

        //can't take your own piece
        if(p.color.equals("white") && board[newCol][newRow].color.equals("white")) {
            return "illegal";
        }
        if(p.color.equals("black") && board[newCol][newRow].color.equals("black")) {
            return "illegal";
        }


        boolean thisMoveIsCastle = false;
        if(p.name1.equals("K")) {
            //castling
            if(color.equals("w")) {
                if(start.equals("e1") && end.equals("g1")) { //kingside castle
                    if(!board[6][1].fullName.equals("empty") || !board[7][1].fullName.equals("empty")) {
                        System.out.println("can't castle kingside through a piece");
                        return "illegal";
                    }
                    if(p.hasMoved == true) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!board[8][1].fullName.equals("wRook") || board[8][1].hasMoved) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal white castle");
                    board[6][1] = board[8][1]; //move rook
                    board[8][1] = new Piece(); //empty
                    thisMoveIsCastle = true;
                    moveType = "white_kingside_castle";
                }
                else if(start.equals("e1") && end.equals("c1")) { //queenside castle
                    if(!board[2][1].fullName.equals("empty") || !board[3][1].fullName.equals("empty") || !board[4][1].fullName.equals("empty") ) {
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
                    board[4][1] = board[1][1]; //move rook
                    board[1][1] = new Piece(); //empty
                    thisMoveIsCastle = true;
                    moveType = "white_queenside_castle";
                }
            }
            else if(color.equals("b")) {
                if(start.equals("e8") && end.equals("g8")) { //kingside castle
                    if(!board[6][8].fullName.equals("empty") || !board[7][8].fullName.equals("empty")) {
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
                    board[6][8] = board[8][8]; //move rook
                    board[8][8] = new Piece(); //empty
                    thisMoveIsCastle = true;
                    moveType = "black_kingside_castle";
                }
                else if(start.equals("e8") && end.equals("c8")) { //queenside castle
                    if(!board[2][8].fullName.equals("empty") || !board[3][8].fullName.equals("empty") || !board[4][8].fullName.equals("empty") ) {
                        System.out.println("can't castle queenside through a piece");
                        return "illegal";
                    }
                    if(p.hasMoved == true) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!board[1][8].fullName.equals("bRook") || board[1][8].hasMoved) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal black castle");
                    board[4][8] = board[1][8]; //move rook
                    board[1][8] = new Piece(); //empty
                    thisMoveIsCastle = true;
                    moveType = "black_queenside_castle";
                }
            }

            //regular king move
            if(!thisMoveIsCastle) {
                if( abs(newCol-oldCol) > 1 || abs(newRow-oldRow) > 1) {
                    //System.out.println("illegal King move");
                    return "illegal";
                }
            }
        }
        else if(p.name1.equals("R")) {
            if( abs(newCol-oldCol) > 0 && abs(newRow-oldRow) > 0 ) {
                //System.out.println("illegal Rook move. can only move along files and ranks");
                return "illegal";
            }
        }
        else if(p.name1.equals("B")) {
            if( abs(newCol-oldCol) != abs(newRow-oldRow) ) {
                //System.out.println("illegal Bishop move. can only move along diagonal");
                return "illegal";
            }
        }
        else if(p.name1.equals("Q")) {
            if( abs(newCol-oldCol) > 0 && abs(newRow-oldRow) > 0 ) { //if either row or column doesnt change, it's a Rook move.  if both change:
                if( abs(newCol-oldCol) != abs(newRow-oldRow) ) {
                    //System.out.println("illegal Queen move. can only move along files and ranks, or along a diagonal");
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
                //System.out.println("illegal Knight move. can only move 2x1 or 1x2");
                return "illegal";
            }
        }
        else if(p.name1.equals("p")) {//pawns are the tricky pieces
            if(p.color.equals("white")) {
                if( abs(newCol-oldCol) > 1 ) {
                    //System.out.println("illegal pawn move. too far horizontally");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 1 && !board[newCol][newRow].color.equals("black") ) {
                    //System.out.println("illegal pawn move. can't change column if not attacking");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 0 && board[newCol][newRow].color.equals("black") ) {
                    //System.out.println("illegal pawn move. can't attack straight");
                    return "illegal";
                }
                else if( newRow - oldRow < 0 ) {
                    //System.out.println("illegal pawn move. backwards");
                    return "illegal";
                }
                else if( newRow - oldRow > 2) {
                    //System.out.println("illegal pawn move. too far");
                    return "illegal";
                }
                else if(oldRow != 2 && abs(newRow - oldRow) == 2) {
                    //System.out.println("illegal pawn move. can only move 2 spaces from starting square");
                    return "illegal";
                }
                else if(board[newCol][newRow].color.equals("black") && ( abs(newCol-oldCol) != 1 || newRow-oldRow != 1  )  ) {
                    //System.out.println("illegal pawn move. incorrect attack");
                    return "illegal";
                }
                if(newRow == 8) {
                    p = new Piece("wQueen"); //pawn promotion
                    moveType = "white_pawn_promotion";
                }
            }
            else if(p.color.equals("black")) {
                if( abs(newCol-oldCol) > 1 ) {
                    //System.out.println("illegal pawn move. too far horizontally");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 1 && !board[newCol][newRow].color.equals("white") ) {
                    //System.out.println("illegal pawn move. can't change column if not attacking");
                    return "illegal";
                }
                else if( abs(newCol-oldCol) == 0 && board[newCol][newRow].color.equals("white") ) {
                    //System.out.println("illegal pawn move. can't attack straight");
                    return "illegal";
                }
                else if( oldRow - newRow < 0 ) {
                    //System.out.println("illegal pawn move. backwards");
                    return "illegal";
                }
                else if( oldRow - newRow > 2) {
                    //System.out.println("illegal pawn move. too far");
                    return "illegal";
                }
                else if(oldRow != 7 && abs(newRow - oldRow) == 2) {
                    //System.out.println("illegal pawn move. can only move 2 spaces from starting square");
                    return "illegal";
                }
                else if(board[newCol][newRow].color.equals("white") && ( abs(newCol-oldCol) != 1 || oldRow-newRow != 1  )  ) {
                    //System.out.println("illegal pawn move. incorrect attack");
                    return "illegal";
                }
                if(newRow == 1) {
                    p = new Piece("bQueen"); //pawn promotion
                    moveType = "black_pawn_promotion";
                }
            }

        } //pawn move

        if(!isClearPath(start, end, p.name1)) {
            return "illegal";
        }


        //cannot move into Check
        //make move, look for Check, unmake move.  if legal, move will be made at the bottom of the function
        if(! checkOnly) {

            Piece previous_new = board[newCol][newRow];
            board[newCol][newRow] = board[oldCol][oldRow];
            board[oldCol][oldRow] = previous_new;

            String resultIsCheck = isCheck();

            previous_new = board[newCol][newRow];
            board[newCol][newRow] = board[oldCol][oldRow];
            board[oldCol][oldRow] = previous_new;

            if(resultIsCheck.equals("check")) {
                return "illegal";
            }
        }


        //if white's turn, and white is in check, a legal move must relieve the check
        if(! checkOnly) {
            if(  (Board.whiteInCheck && isWhitesTurn) || (Board.blackInCheck && !isWhitesTurn) ) {
                //make the move...
                Piece previous_new = board[newCol][newRow];
                board[newCol][newRow] = board[oldCol][oldRow];
                board[oldCol][oldRow] = previous_new;

                String resultIsCheck = isCheck();

                //...unmake the move no matter what (will be re-made below if no return
                previous_new = board[newCol][newRow];
                board[newCol][newRow] = board[oldCol][oldRow];
                board[oldCol][oldRow] = previous_new;

                if(resultIsCheck.equals("check")) {
                    return "illegal";
                }
            }
        }

        //passed all the illegal move filters
        //execute the move, unless the function was only called for Check detection
        if(! checkOnly) {
            Piece previous_new = board[newCol][newRow];
            board[newCol][newRow] = board[oldCol][oldRow];
            board[oldCol][oldRow] = previous_new;
            isWhitesTurn = !isWhitesTurn;
            p.hasMoved = true;
        }

        if(! checkOnly ) {
            String is_check = isCheck();
            System.out.println("is_check: " + is_check);
            if(is_check.equals("check")) {
                String is_check_mate = isCheckMate();
                System.out.println("is_check_mate: " + is_check_mate);
            }
        }


        return moveType;
    }

    public static String isCheck() {

        //if black just moved, it's whites's turn, is white in Check
        if(isWhitesTurn) {
            //find white king
            String end_king_square = "";
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = board[col][row];
                    if(p.fullName.equals("wKing")) {
                        end_king_square = Board.square(col,row);
                        //System.out.println("found wKing on " + end_king_square);
                    }
                }
            }
            //check if any piece move to the king end square is legal
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = board[col][row];
                    String piece_name = p.fullName;
                    if(!piece_name.equals("empty") && p.color.equals("black")) {
                        String start = Board.square(col,row);
                        String moveType = isThisMoveLegal(start, end_king_square, piece_name, true);
                        if(!moveType.equals("illegal")) {
                            System.out.println(start + " " + piece_name + " \t: wKing " + end_king_square + " \t CHECK!");
                            Board.whiteInCheck = true;
                            return "check";
                        }
                    }
                }
            }
        }
        //if white just moved, it's black's turn, is black in Check
        if(!isWhitesTurn) {
            //find black king
            String end_king_square = "";
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = board[col][row];
                    if(p.fullName.equals("bKing")) {
                        end_king_square = Board.square(col,row);
                        //System.out.println("found bKing on " + end_king_square);
                    }
                }
            }
            //check if any piece move to the king end square is legal
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = board[col][row];
                    String piece_name = p.fullName;
                    if(!piece_name.equals("empty") && p.color.equals("white")) {
                        String start = Board.square(col,row);
                        String moveType = isThisMoveLegal(start, end_king_square, piece_name, true);
                        if(!moveType.equals("illegal")) {
                            System.out.println(start + " " + piece_name + " \t: bKing " + end_king_square + " \t CHECK!");
                            Board.blackInCheck = true;
                            return "check";
                        }
                    }
                }
            }


        }
        //if(! lookingForMate) {
        System.out.println("reseting, nobody in check");
        Board.whiteInCheck = false;
        Board.blackInCheck = false;

        return "no_check";
        //}

        //return "check";
    }

    public static String isCheckMate() {
        System.out.println("in isCheckMate()");
        if(Board.whiteInCheck) {
            System.out.println("is white in checkmate?");
            isWhitesTurn = true;
        }
        else if(Board.blackInCheck) {
            System.out.println("is black in checkmate?");
            isWhitesTurn = false;
        }
        else {
            System.out.println("Nobody in check: " + Board.whiteInCheck + " " + Board.blackInCheck);
            return "not_checkmate";
        }

        //for all pieces
        //nest: for all squares on board (16*64 = 1024 possibilities)
        //could be smarter about it, like king is only allowed to move 1 square
        String start = "";
        String end = "";
        for(int col=1; col<=8; ++col) {
            for(int row=1; row<=8; ++row) {
                Piece p = board[col][row];
                if( (Board.whiteInCheck && p.color.equals("white")) || (Board.blackInCheck && p.color.equals("black"))) {
                    start = Board.square(col,row);

                    for(int c=1; c<=8; ++c) {
                        for(int r=1; r<=8; ++r) {
                            end = Board.square(c, r);
                            String moveType = isThisMoveLegal(start, end, p.fullName, true);

                            //if it's a legal move, make the move and see if it relieves check
                            if(!moveType.equals("illegal")) {
                                Piece previous_new = board[c][r];
                                board[c][r] = board[col][row];
                                board[col][row] = previous_new;

                                //lookingForMate = true;
                                String resultIsCheck = isCheck();
                                //lookingForMate = false;

                                previous_new = board[c][r];
                                board[c][r] = board[col][row];
                                board[col][row] = previous_new;

                                if(! resultIsCheck.equals("check")) {
                                    System.out.println("can escape check with " + start + end + " " + p.fullName);
                                    return "not_checkmate";
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("=======================");
        System.out.println("====  Check Mate  =====");
        System.out.println("=======================");

        return "checkmate";
    }

    private static boolean isClearPath(String start, String end, String p) {
        int oldCol = Board.col(start);
        int oldRow = Board.row(start);
        int newCol = Board.col(end);
        int newRow = Board.row(end);

        //not relevant for King, which is only a capture or self-obstruction

        //not relevant for Knight

        //Rook
        if(p.equals("R")) {
            if(newRow > oldRow) { //up
                for(int i=oldRow+1; i<newRow; ++i) {
                    ////System.out.println("rook path up: " + newCol + i + " " + board[newCol][i].fullName);
                    if(!board[newCol][i].fullName.equals("empty")){
                        return false;
                    }
                }
            }
            else if(oldRow > newRow) { //down
                for(int i=oldRow-1; i>newRow; --i) {
                    //System.out.println("rook path down: " + newCol + i + " " + board[newCol][i].fullName);
                    if(!board[newCol][i].fullName.equals("empty")){
                        return false;
                    }
                }
            }
            else if(oldCol > newCol) { //left
                for(int i=oldCol-1; i>newCol; --i) {
                    //System.out.println("rook path left: " + newCol + i + " " + board[i][newRow].fullName);
                    if(!board[i][newRow].fullName.equals("empty")){
                        return false;
                    }
                }
            }
            else if(newCol > oldCol) { //right
                for(int i=oldCol+1; i<newCol; ++i) {
                    //System.out.println("rook path right: " + newCol + i + " " + board[i][newRow].fullName);
                    if(!board[i][newRow].fullName.equals("empty")){
                        return false;
                    }
                }
            }
        }
        else if(p.equals("B")){
            if(newRow > oldRow && newCol > oldCol) { //up-right, row increasing, column increasing
                int pathRow = oldRow+1;
                for(int i=oldCol+1; i<newCol; ++i) {
                    //System.out.println("bishop path up-right: " + i + pathRow + " " + board[i][pathRow].fullName);
                    if(!board[i][pathRow].fullName.equals("empty")){
                        return false;
                    }
                    pathRow++;
                }
            }
            else if(newRow > oldRow && oldCol > newCol) { //up-left: row increasing, column decreasing
                int pathRow = oldRow+1;
                for(int i=oldCol-1; i>newCol; --i) {
                    //System.out.println("bishop path up-left: " + i + pathRow + " " + board[i][pathRow].fullName);
                    if(!board[i][pathRow].fullName.equals("empty")){
                        return false;
                    }
                    pathRow++;
                }
            }
            else if(oldRow > newRow && newCol > oldCol) { //down-right, row decreasing, column increasing
                int pathRow = oldRow-1;
                for(int i=oldCol+1; i<newCol; ++i) {
                    //System.out.println("bishop path down-right: " + i + pathRow + " " + board[i][pathRow].fullName);
                    if(!board[i][pathRow].fullName.equals("empty")){
                        return false;
                    }
                    pathRow--;
                }
            }
            else if(oldRow > newRow && oldCol > newCol) { //down-left
                int pathRow = oldRow-1;
                for(int i=oldCol-1; i>newCol; --i) {
                    //System.out.println("bishop path down-left: " + i + pathRow + " " + board[i][pathRow].fullName);
                    if(!board[i][pathRow].fullName.equals("empty")){
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
                        //System.out.println("queen path up-right: " + i + pathRow + " " + board[i][pathRow].fullName);
                        if(!board[i][pathRow].fullName.equals("empty")){
                            return false;
                        }
                        pathRow++;
                    }
                }
                else if(newRow > oldRow && oldCol > newCol) { //up-left: row increasing, column decreasing
                    int pathRow = oldRow+1;
                    for(int i=oldCol-1; i>newCol; --i) {
                        //System.out.println("queen path up-left: " + i + pathRow + " " + board[i][pathRow].fullName);
                        if(!board[i][pathRow].fullName.equals("empty")){
                            return false;
                        }
                        pathRow++;
                    }
                }
                else if(oldRow > newRow && newCol > oldCol) { //down-right, row decreasing, column increasing
                    int pathRow = oldRow-1;
                    for(int i=oldCol+1; i<newCol; ++i) {
                        //System.out.println("queen path down-right: " + i + pathRow + " " + board[i][pathRow].fullName);
                        if(!board[i][pathRow].fullName.equals("empty")){
                            return false;
                        }
                        pathRow--;
                    }
                }
                else if(oldRow > newRow && oldCol > newCol) { //down-left
                    int pathRow = oldRow-1;
                    for(int i=oldCol-1; i>newCol; --i) {
                        //System.out.println("queen path down-left: " + i + pathRow + " " + board[i][pathRow].fullName);
                        if(!board[i][pathRow].fullName.equals("empty")){
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
                        //System.out.println("queen path up: " + newCol + i + " " + board[newCol][i].fullName);
                        if(!board[newCol][i].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldRow > newRow) { //down
                    for(int i=oldRow-1; i>newRow; --i) {
                        //System.out.println("queen path down: " + newCol + i + " " + board[newCol][i].fullName);
                        if(!board[newCol][i].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldCol > newCol) { //left
                    for(int i=oldCol-1; i>newCol; --i) {
                        //System.out.println("queen path left: " + newCol + i + " " + board[i][newRow].fullName);
                        if(!board[i][newRow].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(newCol > oldCol) { //right
                    for(int i=oldCol+1; i<newCol; ++i) {
                        //System.out.println("queen path right: " + newCol + i + " " + board[i][newRow].fullName);
                        if(!board[i][newRow].fullName.equals("empty")){
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
                        //System.out.println("pawn path 2 up: " + newCol + i + " " + board[newCol][i].fullName);
                        if(!board[newCol][i].fullName.equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldRow > newRow) { //down
                    for(int i=oldRow-1; i>newRow; --i) {
                        //System.out.println("pawn path 2 down: " + newCol + i + " " + board[newCol][i].fullName);
                        if(!board[newCol][i].fullName.equals("empty")){
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
