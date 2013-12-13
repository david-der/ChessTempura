import java.io.*;
//import java.util.ArrayList;


public class ChessViaText {

    Piece[][] board = new Piece[11][11];
    String player = "white";
    int move_counter = 0;


    public static void main (String[] args) {

	ChessViaText ct = new ChessViaText();

	Board b = new Board();
	b.showPieceNames();
	ct.board = b.startingBoard();
	b.printBoard(ct.board);
	
	String move = "";
	while(true) {

	    if(ct.move_counter % 2 == 0) {
		System.out.print("\nWhite to move: ");
		ct.player = "white";

		//Get move
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
		    move = br.readLine();
		    if(move.equals("quit")) {
			System.exit(-1);
		    }
		} catch (IOException ioe) {
		    System.out.println("IO error trying to read your move.");
		    System.exit(1);
		}
		//Execute move
		HumanMove current_move = new HumanMove(ct.board, ct.player);
		if( current_move.movePiece(move) ) {
		    ++ct.move_counter;
		}
	    }
	    else {
		System.out.print("\nBlack to move: ");
		ct.player = "black";

		System.out.println("Computer unable to move");
		ComputerMove cpu_move = new ComputerMove(ct.board, ct.player);
		System.out.println("Current pieces score: " + cpu_move.piecesScore());
		++ct.move_counter;
	    }




	}
    }


}  // end of ChessViaText class
