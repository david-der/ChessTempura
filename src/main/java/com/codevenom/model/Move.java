package com.codevenom.model;

/**
 * Created by bryander on 3/1/14.
 */
public class Move {

    private boolean white_move;
    private boolean check;
    private boolean check_mate;
    private boolean capture;
    private boolean ambiguous_col;
    private boolean ambiguous_row;
    private boolean queenside_castle;
    private boolean kingside_castle;
    private boolean pawn_promotion;
    private String start_square;
    private String end_square;
    private String piece_name;
    private String move_notation;
    private int move_number;

    public Move() {
        check = false;
        capture = false;
        ambiguous_col = false;
        ambiguous_row = false;
        queenside_castle = false;
        kingside_castle = false;
        pawn_promotion = false;
        move_number = 0;

    }

    public boolean getWhiteMove() {
        return white_move;
    }
    public boolean getCheck() {
        return check;
    }
    public boolean getCheckMate() {
        return check_mate;
    }
    public boolean getCapture() {
        return capture;
    }
    public boolean getAmbiguousCol() {
        return ambiguous_col;
    }
    public boolean getAmbiguousRow() {
        return ambiguous_row;
    }
    public boolean getQueensideCastle() {
        return queenside_castle;
    }
    public boolean getKingsideCastle() {
        return kingside_castle;
    }
    public boolean getPawnPromotion() {
        return pawn_promotion;
    }
    public String getStartSquare() {
        return start_square;
    }
    public String getEndSquare() {
        return end_square;
    }
    public String getPieceName() {
        return piece_name;
    }
    public String getMoveNotation() {
        return move_notation;
    }
    public int getMoveNumber() {
        return move_number;
    }

    public void setWhiteMove(boolean w) {
        white_move = w;
    }
    public void setCheck(boolean c) {
        check = c;
    }
    public void setCheckMate(boolean c) {
        check_mate = c;
    }
    public void setCapture(boolean c) {
        capture = c;
    }
    public void setAmbiguousCol(boolean ac) {
        ambiguous_col = ac;
    }
    public void setAmbiguousRow(boolean ar) {
        ambiguous_row = ar;
    }
    public void setQueensideCastle(boolean qc) {
        queenside_castle = qc;
    }
    public void setKingsideCastle(boolean kc) {
        kingside_castle = kc;
    }
    public void setPawnPromotion(boolean P) {
        pawn_promotion = P;
    }
    public void setStartSquare(String start) {
        start_square = start;
    }
    public void setEndSquare(String end) {
        end_square = end;
    }
    public void setPieceName(String piece) {
        piece_name = piece;
    }
    public void setMoveNotation(String n) {
        move_notation = n;
    }
    public void setMoveNumber(int n) {
        move_number = n;
    }




}
