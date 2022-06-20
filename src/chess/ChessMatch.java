package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placepiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('b', 6, new King(board, Color.WHITE));
		placeNewPiece('f', 5, new Rook(board, Color.WHITE));
		
		
	}

}
