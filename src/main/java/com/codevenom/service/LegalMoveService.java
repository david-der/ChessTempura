package com.codevenom.service;

import com.codevenom.model.Notation;
import com.codevenom.model.State;
import com.codevenom.model.Piece;
import com.codevenom.model.Move;

/**
 * Copyright 2013 SWOL.co - Soccer Without Limits
 * User: davidder
 * Date: 12/24/13
 * Time: 12:04 PM
 */
public class LegalMoveService {

    ////////////////////////////////////////////////////////////////////////
    // There are many ways a move can be illegal.
    // Only return "legal" if the move passes all the illegal filters.
    ////////////////////////////////////////////////////////////////////////
    public static String isThisMoveLegal(String start, String end, String piece, boolean checkOnly) {

        int oldCol = Notation.col(start);
        int oldRow = Notation.row(start);
        int newCol = Notation.col(end);
        int newRow = Notation.row(end);
        String color = piece.substring(0, 1);
        String moveType;   //the end of this function returns moveType
        /////////// possible values:
        //legal
        //white_kingside_castle
        //white_queenside_castle
        //black_kingside_castle
        //black_queenside_castle
        //white_pawn_promotion
        //black_pawn_promotion
        //en_passant

        Piece p = State.getBoard()[oldCol][oldRow];


        //1. whites turn, blacks turn
        if(! checkOnly) {  // whose turn it is becomes backwards when looking for check
            if(State.isWhitesTurn()) {
                if(color.equals("b")) {
                    //System.out.println("white to move.");
                    return "illegal";
                }
            }
            else if(!State.isWhitesTurn()) {
                if(color.equals("w")) {
                    //System.out.println("black to move.");
                    return "illegal";
                }
            }
        }

        //2. can't take your own piece
        if(p.getColor().equals("white") && State.getBoard()[newCol][newRow].getColor().equals("white")) {
            return "illegal";
        }
        if(p.getColor().equals("black") && State.getBoard()[newCol][newRow].getColor().equals("black")) {
            return "illegal";
        }

        //3. geometry
        moveType = correctGeometry(start, end, piece);
        if(moveType.equals("illegal")) {
            return "illegal";
        }

        //4. clear path
        if(!isClearPath(start, end, p.getName1())) {
            return "illegal";
        }



        //5. obey check rules (can't move into check, must relieve check)

        String is_check;
        //for a normal move, checkOnly == false and the move must obey check rules
        //sometimes pseudo moves are made to look for check or checkmate, this avoids infinite loop
        if(! checkOnly) {
            //checkOnly == true when looking for any legal move to king's square (isCheck)
            //can any piece move to the king end square?

            //checkOnly == true when looking for check mate (isCheckMate)
            //can any move occur to get rid of check?


            String obey_check = considerCheck(start, end);

            if(obey_check.equals("illegal")) {
                return "illegal";
            }

            //passed all the illegal move filters
            //create the move, unless the function was only called for Check detection
            Move move = new Move();
            move.setWhiteMove(State.isWhitesTurn()); //backwards bc move was made
            move.setStartSquare(start);
            move.setEndSquare(end);
            move.setPieceName(piece);
            //move.setCapture(false);      //change this!
            //move.setAmbiguousCol(false); //change this
            //move.setAmbiguousRow(false); //change this
            if(moveType.equals("white_queenside_castle")) {
                move.setQueensideCastle(true);
                State.getBoard()[4][1] = State.getBoard()[1][1]; //move rook
                State.getBoard()[1][1] = new Piece(); //empty
            }
            if(moveType.equals("black_queenside_castle")) {
                move.setQueensideCastle(true);
                State.getBoard()[4][8] = State.getBoard()[1][8]; //move rook
                State.getBoard()[1][8] = new Piece(); //empty
            }
            if(moveType.equals("white_kingside_castle")) {
                move.setKingsideCastle(true);
                State.getBoard()[6][1] = State.getBoard()[8][1]; //move rook
                State.getBoard()[8][1] = new Piece(); //empty
            }
            if(moveType.equals("black_kingside_castle")) {
                move.setKingsideCastle(true);
                State.getBoard()[6][8] = State.getBoard()[8][8]; //move rook
                State.getBoard()[8][8] = new Piece(); //empty
            }
            if(moveType.equals("white_pawn_promotion") || moveType.equals("black_pawn_promotion")) {
                move.setPawnPromotion(true);
            }
            if(moveType.equals("en_passant")) {
                move.setCapture(true);
            }
            if(State.getBoard()[oldCol][oldRow].getColor().equals("white") && State.getBoard()[newCol][newRow].getColor().equals("black")) {
                move.setCapture(true);
            }
            if(State.getBoard()[oldCol][oldRow].getColor().equals("black") && State.getBoard()[newCol][newRow].getColor().equals("white")) {
                move.setCapture(true);
            }
            if(State.isWhitesTurn()) {
                State.getWhiteMoveHistory().add(move);
                move.setMoveNumber(State.getWhiteMoveHistory().size());
            }
            else{
                State.getBlackMoveHistory().add(move);
                move.setMoveNumber(State.getBlackMoveHistory().size());
            }



            //passed all the illegal move filters
            //execute the move, unless the function was only called for Check detection
            Piece previous_new = State.getBoard()[newCol][newRow];
            State.getBoard()[newCol][newRow] = State.getBoard()[oldCol][oldRow];
            State.getBoard()[oldCol][oldRow] = previous_new;
            State.setWhitesTurn(!State.isWhitesTurn());
            p.setHasMoved(true);

            //after executing the move, is it check or check mate?
            is_check = isCheck(false);
            //System.out.println("is_check: " + is_check);
            if(is_check.equals("check")) {
                String is_check_mate = isCheckMate();
                System.out.println("is_check_mate: " + is_check_mate);
            }


            String notation = Notation.algebraicNotation(move);
            System.out.println(move.getMoveNumber() + ". " + notation);
            return notation;

        }




        return ""; //should never reach here
    }

    public static String considerCheck(String start, String end) {
        int oldCol = Notation.col(start);
        int oldRow = Notation.row(start);
        int newCol = Notation.col(end);
        int newRow = Notation.row(end);

        //cannot move into Check
        //make move, look for Check, unmake move.
        Piece previous_new = State.getBoard()[newCol][newRow];
        State.getBoard()[newCol][newRow] = State.getBoard()[oldCol][oldRow];
        State.getBoard()[oldCol][oldRow] = previous_new;

        String resultIsCheck = isCheck(false);

        previous_new = State.getBoard()[newCol][newRow];
        State.getBoard()[newCol][newRow] = State.getBoard()[oldCol][oldRow];
        State.getBoard()[oldCol][oldRow] = previous_new;

        if(resultIsCheck.equals("check")) {
            return "illegal";
        }



        //if white's turn, and white is in check, a legal move must relieve the check
        if(  (State.isWhiteInCheck() && State.isWhitesTurn()) || (State.isBlackInCheck() && !State.isWhitesTurn()) ) {
            //make the move...
            previous_new = State.getBoard()[newCol][newRow];
            State.getBoard()[newCol][newRow] = State.getBoard()[oldCol][oldRow];
            State.getBoard()[oldCol][oldRow] = previous_new;

            resultIsCheck = isCheck(false);

            //...unmake the move no matter what (will be re-made below if no return
            previous_new = State.getBoard()[newCol][newRow];
            State.getBoard()[newCol][newRow] = State.getBoard()[oldCol][oldRow];
            State.getBoard()[oldCol][oldRow] = previous_new;

            if(resultIsCheck.equals("check")) {
                return "illegal";
            }
        }

        return "legal";

    }

    public static String correctGeometry(String start, String end, String piece) {
        int oldCol = Notation.col(start);
        int oldRow = Notation.row(start);
        int newCol = Notation.col(end);
        int newRow = Notation.row(end);
        String color = piece.substring(0, 1);
        Piece p = State.getBoard()[oldCol][oldRow];
        String moveType = "legal";

        boolean thisMoveIsCastle = false;
        if(p.getName1().equals("K")) {
            //castling
            if(color.equals("w")) {
                if(start.equals("e1") && end.equals("g1")) { //kingside castle
                    if(!State.getBoard()[6][1].getFullName().equals("empty") || !State.getBoard()[7][1].getFullName().equals("empty")) {
                        System.out.println("can't castle kingside through a piece");
                        return "illegal";
                    }
                    if(p.isHasMoved()) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!State.getBoard()[8][1].getFullName().equals("wRook") || State.getBoard()[8][1].isHasMoved()) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal white kingside castle");
                    thisMoveIsCastle = true;
                    moveType = "white_kingside_castle";
                }
                else if(start.equals("e1") && end.equals("c1")) { //queenside castle
                    if(!State.getBoard()[2][1].getFullName().equals("empty") || !State.getBoard()[3][1].getFullName().equals("empty") || !State.getBoard()[4][1].getFullName().equals("empty") ) {
                        System.out.println("can't castle queenside through a piece");
                        return "illegal";
                    }
                    if(p.isHasMoved()) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!State.getBoard()[1][1].getFullName().equals("wRook") || State.getBoard()[1][1].isHasMoved()) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal white queenside castle");
                    thisMoveIsCastle = true;
                    moveType = "white_queenside_castle";
                }
            }
            else if(color.equals("b")) {
                if(start.equals("e8") && end.equals("g8")) { //kingside castle
                    if(!State.getBoard()[6][8].getFullName().equals("empty") || !State.getBoard()[7][8].getFullName().equals("empty")) {
                        System.out.println("can't castle kingside through a piece");
                        return "illegal";
                    }
                    if(p.isHasMoved()) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!State.getBoard()[8][8].getFullName().equals("bRook") || State.getBoard()[8][8].isHasMoved()) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal black kingside castle");
                    thisMoveIsCastle = true;
                    moveType = "black_kingside_castle";
                }
                else if(start.equals("e8") && end.equals("c8")) { //queenside castle
                    if(!State.getBoard()[2][8].getFullName().equals("empty") || !State.getBoard()[3][8].getFullName().equals("empty") || !State.getBoard()[4][8].getFullName().equals("empty") ) {
                        System.out.println("can't castle queenside through a piece");
                        return "illegal";
                    }
                    if(p.isHasMoved()) {
                        System.out.println("can't castle, king already moved");
                        return "illegal";
                    }
                    if(!State.getBoard()[1][8].getFullName().equals("bRook") || State.getBoard()[1][8].isHasMoved()) {
                        System.out.println("can't castle, rook already moved");
                        return "illegal";
                    }
                    System.out.println("legal black queenside castle");
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
        else if(p.getName1().equals("R")) {
            if( abs(newCol-oldCol) > 0 && abs(newRow-oldRow) > 0 ) {
                //System.out.println("illegal Rook move. can only move along files and ranks");
                return "illegal";
            }
        }
        else if(p.getName1().equals("B")) {
            if( abs(newCol-oldCol) != abs(newRow-oldRow) ) {
                //System.out.println("illegal Bishop move. can only move along diagonal");
                return "illegal";
            }
        }
        else if(p.getName1().equals("Q")) {
            if( abs(newCol-oldCol) > 0 && abs(newRow-oldRow) > 0 ) { //if either row or column doesnt change, it's a Rook move.  if both change:
                if( abs(newCol-oldCol) != abs(newRow-oldRow) ) {
                    //System.out.println("illegal Queen move. can only move along files and ranks, or along a diagonal");
                    return "illegal";
                }
            }
        }
        else if(p.getName1().equals("N")) {
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
        else if(p.getName1().equals("p")) {//pawns are the tricky pieces
            if(p.getColor().equals("white")) {
                if( abs(newCol-oldCol) > 1 ) {
                    //System.out.println("illegal pawn move. too far horizontally");
                    return "illegal";
                }
                else if( newRow - oldRow <= 0 ) {
                    //System.out.println("illegal pawn move. must move forward");
                    return "illegal";
                }
                else if( newRow - oldRow > 2) {
                    //System.out.println("illegal pawn move. too far");
                    return "illegal";
                }
                else if(oldRow != 2 && abs(newRow - oldRow) == 2) {
                    //System.out.println("illegal pawn move. can only move 2 spaces on pawn's first move.");
                    return "illegal";
                }
                else if(State.getBoard()[newCol][newRow].getColor().equals("black") && ( abs(newCol-oldCol) != 1 || newRow-oldRow != 1  )  ) {
                    //System.out.println("illegal pawn move. incorrect attack");
                    return "illegal";
                }
                else if(abs(newCol-oldCol) == 1 && newRow-oldRow == 1) { //if attack move
                    if(!State.getBoard()[newCol][newRow].getColor().equals("black")) {
                        //only legal to move diagonol on non-capture if capturing "en passant"
                        String prior_move_end = State.getBlackMoveHistory().get(State.getBlackMoveHistory().size() - 1).getEndSquare();
                        String prior_move_start = State.getBlackMoveHistory().get(State.getBlackMoveHistory().size() - 1).getStartSquare();
                        String required_end = Notation.square(newCol, newRow-1);
                        String required_start = Notation.square(newCol, newRow+1);
                        if(!State.getBoard()[newCol][oldRow].getFullName().equals("bPawn")
                                || !prior_move_end.equals(required_end)
                                || !prior_move_start.equals(required_start) ){
                            //System.out.println("illegal pawn move. can only move diagonal if attacking.");
                            return "illegal";
                        }
                        else {
                            System.out.println("en passant");
                            moveType = "en_passant";
                        }
                    }
                }
                if(newRow == 8) { //pawn promotion
                    p.setFullName("wQueen");
                    p.setName1("Q");
                    p.setValue(9);
                    moveType = "white_pawn_promotion";
                }
            }
            else if(p.getColor().equals("black")) {
                if( abs(newCol-oldCol) > 1 ) {
                    //System.out.println("illegal pawn move. too far horizontally");
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
                    //System.out.println("illegal pawn move. can only move 2 spaces on pawn's first move");
                    return "illegal";
                }
                else if(State.getBoard()[newCol][newRow].getColor().equals("white") && ( abs(newCol-oldCol) != 1 || oldRow-newRow != 1  )  ) {
                    //System.out.println("illegal pawn move. incorrect attack");
                    return "illegal";
                }
                else if(abs(newCol-oldCol) == 1 && oldRow-newRow == 1) { //if attack move
                    if(!State.getBoard()[newCol][newRow].getColor().equals("white")) {
                        //only legal to move diagonol on non-capture if capturing "en passant"
                        String prior_move_end = State.getWhiteMoveHistory().get(State.getWhiteMoveHistory().size() - 1).getEndSquare();
                        String prior_move_start = State.getWhiteMoveHistory().get(State.getWhiteMoveHistory().size() - 1).getStartSquare();
                        String required_end = Notation.square(newCol, newRow+1);
                        String required_start = Notation.square(newCol, newRow-1);
                        if(!State.getBoard()[newCol][oldRow].getFullName().equals("wPawn")
                                || !prior_move_end.equals(required_end)
                                || !prior_move_start.equals(required_start) ){
                            //System.out.println("illegal pawn move. can only move diagonal if attacking.");
                            return "illegal";
                        }
                        else {
                            System.out.println("en passant");
                            moveType = "en_passant";
                        }
                    }
                }
                if(newRow == 1) { //pawn promotion
                    p.setFullName("bQueen");
                    p.setName1("Q");
                    p.setValue(9);
                    moveType = "black_pawn_promotion";
                }
            }
            else {
                //should never reach here
                return "illegal";
            }

        } //pawn move
        return moveType;
    }



    public static String isCheck(boolean mateQueryOnly) {

        //if black just moved, it's whites's turn, is white in Check
        if(State.isWhitesTurn()) {
            //find white king
            String end_king_square = "";
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = State.getBoard()[col][row];
                    if(p.getFullName().equals("wKing")) {
                        end_king_square = Notation.square(col,row);
                        //System.out.println("found wKing on " + end_king_square);
                    }
                }
            }
            //check if any piece move to the king end square is legal
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = State.getBoard()[col][row];
                    String piece_name = p.getFullName();
                    if(!piece_name.equals("empty") && p.getColor().equals("black")) {
                        String start = Notation.square(col,row);
                        String moveType = isThisMoveLegal(start, end_king_square, piece_name, true);
                        if(!moveType.equals("illegal")) {
                            if(!mateQueryOnly) {
                                System.out.println(start + " " + piece_name + " \t: wKing " + end_king_square + " \t CHECK!");
                                State.setWhiteInCheck(true);
                            }
                            return "check";
                        }
                    }
                }
            }
        }
        //if white just moved, it's black's turn, is black in Check
        if(!State.isWhitesTurn()) {
            //find black king
            String end_king_square = "";
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = State.getBoard()[col][row];
                    if(p.getFullName().equals("bKing")) {
                        end_king_square = Notation.square(col,row);
                        //System.out.println("found bKing on " + end_king_square);
                    }
                }
            }
            //check if any piece move to the king end square is legal
            for(int col=1; col<=8; ++col) {
                for(int row=1; row<=8; ++row) {
                    Piece p = State.getBoard()[col][row];
                    String piece_name = p.getFullName();
                    if(!piece_name.equals("empty") && p.getColor().equals("white")) {
                        String start = Notation.square(col,row);
                        String moveType = isThisMoveLegal(start, end_king_square, piece_name, true);
                        if(!moveType.equals("illegal")) {
                            if(!mateQueryOnly) {
                                System.out.println(start + " " + piece_name + " \t: bKing " + end_king_square + " \t CHECK!");
                                State.setBlackInCheck(true);
                            }
                            return "check";
                        }
                    }
                }
            }


        }
        if(!mateQueryOnly) {
            //System.out.println("reseting, nobody in check");
            State.setWhiteInCheck(false);
            State.setBlackInCheck(false);
        }

        return "no_check";

    }

    public static String isCheckMate() {
        //System.out.println("in isCheckMate()");
        if(State.isWhiteInCheck()) {
            System.out.println("is white in checkmate?");
            State.setWhitesTurn(true);
        }
        else if(State.isBlackInCheck()) {
            System.out.println("is black in checkmate?");
            State.setWhitesTurn(false);
        }
        else {
            //System.out.println("Nobody in check: " + State.isWhiteInCheck() + " " + State.isBlackInCheck());
            return "not_checkmate";
        }

        //for all pieces
        //nest: for all squares on board (16*64 = 1024 possibilities)
        //could be smarter about it, like king is only allowed to move 1 square
        String start;
        String end;
        for(int col=1; col<=8; ++col) {
            for(int row=1; row<=8; ++row) {
                Piece p = State.getBoard()[col][row];
                if( (State.isWhiteInCheck() && p.getColor().equals("white")) || (State.isBlackInCheck() && p.getColor().equals("black"))) {
                    start = Notation.square(col,row);

                    for(int c=1; c<=8; ++c) {
                        for(int r=1; r<=8; ++r) {
                            end = Notation.square(c, r);
                            String moveType = isThisMoveLegal(start, end, p.getFullName(), true);

                            //if it's a legal move, make the move and see if it relieves check
                            if(!moveType.equals("illegal")) {
                                Piece previous_new = State.getBoard()[c][r];
                                State.getBoard()[c][r] = State.getBoard()[col][row];
                                State.getBoard()[col][row] = previous_new;

                                String resultIsCheck = isCheck(true);

                                previous_new = State.getBoard()[c][r];
                                State.getBoard()[c][r] = State.getBoard()[col][row];
                                State.getBoard()[col][row] = previous_new;

                                if(! resultIsCheck.equals("check")) {
                                    System.out.println("can escape check with " + start + end + " " + p.getFullName());
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
        int oldCol = Notation.col(start);
        int oldRow = Notation.row(start);
        int newCol = Notation.col(end);
        int newRow = Notation.row(end);

        //not relevant for King, which is only a capture or self-obstruction

        //not relevant for Knight

        //Rook
        if(p.equals("R")) {
            if(newRow > oldRow) { //up
                for(int i=oldRow+1; i<newRow; ++i) {
                    ////System.out.println("rook path up: " + newCol + i + " " + State.getBoard()[newCol][i].getFullName());
                    if(!State.getBoard()[newCol][i].getFullName().equals("empty")){
                        return false;
                    }
                }
            }
            else if(oldRow > newRow) { //down
                for(int i=oldRow-1; i>newRow; --i) {
                    //System.out.println("rook path down: " + newCol + i + " " + State.getBoard()[newCol][i].getFullName());
                    if(!State.getBoard()[newCol][i].getFullName().equals("empty")){
                        return false;
                    }
                }
            }
            else if(oldCol > newCol) { //left
                for(int i=oldCol-1; i>newCol; --i) {
                    //System.out.println("rook path left: " + newCol + i + " " + State.getBoard()[i][newRow].getFullName());
                    if(!State.getBoard()[i][newRow].getFullName().equals("empty")){
                        return false;
                    }
                }
            }
            else if(newCol > oldCol) { //right
                for(int i=oldCol+1; i<newCol; ++i) {
                    //System.out.println("rook path right: " + newCol + i + " " + State.getBoard()[i][newRow].getFullName());
                    if(!State.getBoard()[i][newRow].getFullName().equals("empty")){
                        return false;
                    }
                }
            }
        }
        else if(p.equals("B")){
            if(newRow > oldRow && newCol > oldCol) { //up-right, row increasing, column increasing
                int pathRow = oldRow+1;
                for(int i=oldCol+1; i<newCol; ++i) {
                    //System.out.println("bishop path up-right: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                    if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
                        return false;
                    }
                    pathRow++;
                }
            }
            else if(newRow > oldRow && oldCol > newCol) { //up-left: row increasing, column decreasing
                int pathRow = oldRow+1;
                for(int i=oldCol-1; i>newCol; --i) {
                    //System.out.println("bishop path up-left: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                    if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
                        return false;
                    }
                    pathRow++;
                }
            }
            else if(oldRow > newRow && newCol > oldCol) { //down-right, row decreasing, column increasing
                int pathRow = oldRow-1;
                for(int i=oldCol+1; i<newCol; ++i) {
                    //System.out.println("bishop path down-right: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                    if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
                        return false;
                    }
                    pathRow--;
                }
            }
            else if(oldRow > newRow && oldCol > newCol) { //down-left
                int pathRow = oldRow-1;
                for(int i=oldCol-1; i>newCol; --i) {
                    //System.out.println("bishop path down-left: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                    if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
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
                        //System.out.println("queen path up-right: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                        if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
                            return false;
                        }
                        pathRow++;
                    }
                }
                else if(newRow > oldRow && oldCol > newCol) { //up-left: row increasing, column decreasing
                    int pathRow = oldRow+1;
                    for(int i=oldCol-1; i>newCol; --i) {
                        //System.out.println("queen path up-left: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                        if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
                            return false;
                        }
                        pathRow++;
                    }
                }
                else if(oldRow > newRow && newCol > oldCol) { //down-right, row decreasing, column increasing
                    int pathRow = oldRow-1;
                    for(int i=oldCol+1; i<newCol; ++i) {
                        //System.out.println("queen path down-right: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                        if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
                            return false;
                        }
                        pathRow--;
                    }
                }
                else if(oldRow > newRow && oldCol > newCol) { //down-left
                    int pathRow = oldRow-1;
                    for(int i=oldCol-1; i>newCol; --i) {
                        //System.out.println("queen path down-left: " + i + pathRow + " " + State.getBoard()[i][pathRow].getFullName());
                        if(!State.getBoard()[i][pathRow].getFullName().equals("empty")){
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
                        //System.out.println("queen path up: " + newCol + i + " " + State.getBoard()[newCol][i].getFullName());
                        if(!State.getBoard()[newCol][i].getFullName().equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldRow > newRow) { //down
                    for(int i=oldRow-1; i>newRow; --i) {
                        //System.out.println("queen path down: " + newCol + i + " " + State.getBoard()[newCol][i].getFullName());
                        if(!State.getBoard()[newCol][i].getFullName().equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldCol > newCol) { //left
                    for(int i=oldCol-1; i>newCol; --i) {
                        //System.out.println("queen path left: " + newCol + i + " " + State.getBoard()[i][newRow].getFullName());
                        if(!State.getBoard()[i][newRow].getFullName().equals("empty")){
                            return false;
                        }
                    }
                }
                else if(newCol > oldCol) { //right
                    for(int i=oldCol+1; i<newCol; ++i) {
                        //System.out.println("queen path right: " + newCol + i + " " + State.getBoard()[i][newRow].getFullName());
                        if(!State.getBoard()[i][newRow].getFullName().equals("empty")){
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
                        //System.out.println("pawn path 2 up: " + newCol + i + " " + State.getBoard()[newCol][i].getFullName());
                        if(!State.getBoard()[newCol][i].getFullName().equals("empty")){
                            return false;
                        }
                    }
                }
                else if(oldRow > newRow) { //down
                    for(int i=oldRow-1; i>newRow; --i) {
                        //System.out.println("pawn path 2 down: " + newCol + i + " " + State.getBoard()[newCol][i].getFullName());
                        if(!State.getBoard()[newCol][i].getFullName().equals("empty")){
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
