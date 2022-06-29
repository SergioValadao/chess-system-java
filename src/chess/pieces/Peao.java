package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Peao extends ChessPiece{

	public Peao(Board board, Color color) {
		super(board, color);	
	}

	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] possibleMovies() {
		
		Position p = new Position(0,0);
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		int cor = getColor() == Color.WHITE ? 1: -1;
					
		p.setValues(position.getRow()-1*cor, position.getColumn());		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;			
		}
		p.setValues(position.getRow()-2*cor, position.getColumn());
		Position p2 = new Position(position.getRow()-1*cor, position.getColumn());
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) ) {
			mat[p.getRow()][p.getColumn()] = true;			
		}
		p.setValues(position.getRow()-1*cor, position.getColumn()-1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow()-1*cor, position.getColumn()+1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;			
		}
		return mat;
	}
}