//import java.io.*;
//import java.util.ArrayList;

public class HumanMove {

	public Piece[][] board;
	public String player;

	HumanMove(Piece[][] current_board, String color_turn) {
		board = current_board;
		player = color_turn;
	}

	public boolean movePiece(String move) {
		String c1 = move.substring(0,1);
		String c2 = move.substring(1,2);
		String c3 = move.substring(2,3);
		String c4 = move.substring(3,4);
		String c5 = move.substring(4,5);
		int oldCol = getCol(c1);
		int oldRow = getRow(c2);
		String name1 = c3;
		int newCol = getCol(c4);
		int newRow = getRow(c5);

		//System.out.println("Your move " + c1 + c2 + c3 + c4 + c5);

		if(newCol < 1 || newCol > 8 || newRow < 1 || newRow > 8) {
			System.out.println("illegal move,");
			return false;
		}
		if(oldCol < 1 || oldCol > 8 || oldRow < 1 || oldRow > 8) {
			System.out.println("illegal move,");
			return false;
		}
		if(board[oldRow][oldCol].name1.equals(".")) {
			System.out.println("open square,");
			return false;
		}
		if( ! board[oldRow][oldCol].name1.equals(name1) ) {
			System.out.println("incorrect piece, " + board[oldRow][oldCol].name1 + " " + name1 );
			return false;
		}
		if( board[oldRow][oldCol].value <= 0 ) {
			System.out.println("not a piece, " + board[oldRow][oldCol].name1 + " " + name1 );
			return false;
		}
		if( player.equals("white") ) {
			if(board[oldRow][oldCol].color.equals("black")) {
				System.out.println("white's turn,");
				return false;
			}
		}
		if( player.equals("black") ) {
			if(board[oldRow][oldCol].color.equals("white")) {
				System.out.println("black's turn,");
				return false;
			}
		}
		if(board[newRow][newCol].color.equals(player)) {
			System.out.println("one of your pieces is on that square");
			return false;
		}


		boolean is_legal_move = isLegalMoveForPiece(board[oldRow][oldCol], move);
		if( ! is_legal_move ) {
			return false;
		}


		// execute move
		board[newRow][newCol] = board[oldRow][oldCol];
		board[oldRow][oldCol] = new Piece("open");

		// refresh board
		Board b = new Board();
		b.printBoard(board);

		return true;
	}

	public int getCol(String col) {
		if(col.equals("a")) { return 1; }
		if(col.equals("b")) { return 2; }
		if(col.equals("c")) { return 3; }
		if(col.equals("d")) { return 4; }
		if(col.equals("e")) { return 5; }
		if(col.equals("f")) { return 6; }
		if(col.equals("g")) { return 7; }
		if(col.equals("h")) { return 8; }
		return 0;
	}
	public int getRow(String row) {
		if(row.equals("1")) { return 8; }
		if(row.equals("2")) { return 7; }
		if(row.equals("3")) { return 6; }
		if(row.equals("4")) { return 5; }
		if(row.equals("5")) { return 4; }
		if(row.equals("6")) { return 3; }
		if(row.equals("7")) { return 2; }
		if(row.equals("8")) { return 1; }
		return 0;
	}

	public boolean isLegalMoveForPiece(Piece piece, String move) {
		String c1 = move.substring(0,1);
		String c2 = move.substring(1,2);
		String c3 = move.substring(2,3);
		String c4 = move.substring(3,4);
		String c5 = move.substring(4,5);
		int oldCol = getCol(c1);
		int oldRow = getRow(c2);
		String name1 = c3;
		int newCol = getCol(c4);
		int newRow = getRow(c5);
		int oldBoardCol = oldCol;
		int oldBoardRow = -(oldRow-9);
		int newBoardCol = newCol;
		int newBoardRow = -(newRow-9);

		/*System.out.println("oldCol " + oldCol);
	System.out.println("oldRow " + oldRow);
	System.out.println("newCol " + newCol);
	System.out.println("newRow " + newRow);

	System.out.println("oldBoardCol " + oldBoardCol);
	System.out.println("oldBoardRow " + oldBoardRow);
	System.out.println("newBoardCol " + newBoardCol);
	System.out.println("newBoardRow " + newBoardRow);*/

		if(name1.equals("K")) {
			if( abs(newBoardCol-oldBoardCol) > 1 || abs(newBoardRow-oldBoardRow) > 1) {
				System.out.println("illegal King move");
				return false;
			}
		}
		//pawns are the toughest because they are most restricted, and direction matters
		//white pawn
		if(name1.equals("p") && piece.color.equals("white")) {
			if( abs(newBoardCol-oldBoardCol) > 1 ) {
				System.out.println("illegal pawn move. too far horizontally");
				return false;
			}
			else if( abs(newBoardCol-oldBoardCol) == 1 && !board[newRow][newCol].color.equals("black") ) {
				System.out.println("illegal pawn move. can't change column if not attacking");
				return false;
			}
			else if( abs(newBoardCol-oldBoardCol) == 0 && board[newRow][newCol].color.equals("black") ) {
				System.out.println("illegal pawn move. can't attack straight");
				return false;
			}
			else if( newBoardRow - oldBoardRow < 0 ) {
				System.out.println("illegal pawn move. backwards");
				return false;
			}
			else if( newBoardRow - oldBoardRow > 2) {
				System.out.println("illegal pawn move. too far");
				return false;
			}
			else if(oldBoardRow != 2 && abs(newBoardRow - oldBoardRow) == 2) {
				System.out.println("illegal pawn move. can only move 2 spaces from starting square");
				return false;
			}
			else if(board[newRow][newCol].color.equals("black") && ( abs(newBoardCol-oldBoardCol) != 1 || newBoardRow-oldBoardRow != 1  )  ) {
				System.out.println("illegal pawn move. incorrect attack");
				return false;
			}
		}
		//black pawn
		if(name1.equals("p") && piece.color.equals("black")) {
			if( abs(newBoardCol-oldBoardCol) > 1 ) {
				System.out.println("illegal pawn move. too far horizontally");
				return false;
			}
			else if( abs(newBoardCol-oldBoardCol) == 1 && !board[newRow][newCol].color.equals("white") ) {
				System.out.println("illegal pawn move. can't change column if not attacking");
				return false;
			}
			else if( abs(newBoardCol-oldBoardCol) == 0 && board[newRow][newCol].color.equals("white") ) {
				System.out.println("illegal pawn move. can't attack straight");
				return false;
			}
			else if( oldBoardRow - newBoardRow < 0 ) {
				System.out.println("illegal pawn move. backwards");
				return false;
			}
			else if( oldBoardRow - newBoardRow > 2) {
				System.out.println("illegal pawn move. too far");
				return false;
			}
			else if(oldBoardRow != 7 && abs(newBoardRow - oldBoardRow) == 2) {
				System.out.println("illegal pawn move. can only move 2 spaces from starting square");
				return false;
			}
			else if(board[newRow][newCol].color.equals("white") && ( abs(newBoardCol-oldBoardCol) != 1 || oldBoardRow-newBoardRow != 1  )  ) {
				System.out.println("illegal pawn move. incorrect attack");
				return false;
			}
		}
		
		if(name1.equals("R")) {
			if( abs(newBoardCol-oldBoardCol) > 0 && abs(newBoardRow-oldBoardRow) > 0 ) {
				System.out.println("illegal Rook move. can only move along files and ranks");
				return false;
			}
		}
		if(name1.equals("B")) {
			if( abs(newBoardCol-oldBoardCol) != abs(newBoardRow-oldBoardRow) ) {
				System.out.println("illegal Bishop move. can only move along diagonal");
				return false;
			}
		}
		if(name1.equals("Q")) {
			if( abs(newBoardCol-oldBoardCol) > 0 && abs(newBoardRow-oldBoardRow) > 0 ) { //if either row or column doesnt change, it's a Rook move.  if both change:
				if( abs(newBoardCol-oldBoardCol) != abs(newBoardRow-oldBoardRow) ) {
					System.out.println("illegal Queen move. can only move along files and ranks, or along a diagonal");
					return false;
				}
			}
		}
		if(name1.equals("N")) {
			if( abs(newBoardCol-oldBoardCol) == 1 && abs(newBoardRow-oldBoardRow) == 2 ) {
				return true;
			}
			else if( abs(newBoardCol-oldBoardCol) == 2 && abs(newBoardRow-oldBoardRow) == 1 ) {
				return true;
			}
			else {
				System.out.println("illegal Knight move. can only move 2x1 or 1x2");
			}

		}

		return true;
	}

	public boolean isClearPath(Piece piece, String move) {

		return true;
	}

	public boolean isDiscoveredCheck() {

		return false;
	}

	public int abs(int x) {
		if (x < 0) {
			x *= -1;
			return x;
		}
		else{
			return x;
		}
	}

}
