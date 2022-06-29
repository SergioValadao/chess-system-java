package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bispo extends ChessPiece{

	public Bispo(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMovies() {
		
		Position p = new Position(0,0);
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		int cor = getColor() == Color.WHITE ? 1: -1;
		
		p.setValues(position.getRow()-1*cor, position.getColumn()-1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {			
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow()-1*cor, p.getColumn()-1);
		}
		p.setValues(position.getRow()-1*cor, position.getColumn()+1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {			
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow()-1*cor, p.getColumn()+1);
		}
		p.setValues(position.getRow()+1*cor, position.getColumn()-1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {			
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow()+1*cor, p.getColumn()-1);
		}
		p.setValues(position.getRow()+1*cor, position.getColumn()+1);
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {			
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow()+1*cor, p.getColumn()-1);
		}
		
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;			
		}					
		return mat;
	}
		
}
