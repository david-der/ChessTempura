//import java.io.*;
//import java.util.ArrayList;


public class ComputerMove {

	public Piece[][] board;
	public String player;
	public int piece_score;
	public int position_score;


	ComputerMove(Piece[][] current_board, String color_turn) {
		board = current_board;
		player = color_turn;
	}

	public int piecesScore() {
		int score = 0;
		for(int i=0; i<11; ++i) {
			for(int j=0; j<11; ++j) {
				Piece piece = board[i][j];
				if(piece.color.equals("white")) {
					score += piece.value * 100;
				}
				else if(piece.color.equals("black")) {
					score -= piece.value * 100;
				}
			}
		}

		piece_score = score;
		return score;
	}

}
