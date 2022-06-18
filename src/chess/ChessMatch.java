package chess;

import boardgame.Board;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumns()];
		for(int x = 0; x < board.getRow(); x++) {
			for(int y = 0; y < board.getColumns(); y++) {
				mat[x][y] = (ChessPiece) board.piece(x,y);
			}
		}
		return mat;
	}

}
