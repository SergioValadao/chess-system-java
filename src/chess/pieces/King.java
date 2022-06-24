package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	// acenelio criou(eu não) uma funcao para saber se podia mover a peça.
	
	@Override
	public boolean[][] possibleMovies() {
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		//Above
		p.setValues(position.getRow() -1, position.getColumn());		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//below
		p.setValues(position.getRow() +1, position.getColumn());		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//left
		p.setValues(position.getRow(), position.getColumn()-1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//right
		p.setValues(position.getRow(), position.getColumn()+1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//diagonal - UP/left
		p.setValues(position.getRow()-1, position.getColumn()-1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//diagonal - UP/right
		p.setValues(position.getRow()-1, position.getColumn()+1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//diagonal - down/left
		p.setValues(position.getRow()+1, position.getColumn()-1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//diagonal - down/right
		p.setValues(position.getRow()+1, position.getColumn()+1);		
		if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;			
		}
		return mat;
	}
}
