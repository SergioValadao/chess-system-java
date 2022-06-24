package boardgame;

import boardgame.Board;
import boardgame.Position;

public abstract class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {		
		this.board = board;
		position = null;
	}
	
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMovies();
	
	public boolean possibleMovies(Position position){
		return possibleMovies()[position.getRow()][position.getColumn()];
	}
	
	public boolean IsThereAnyPossibleMovie(){
		boolean[][] mat = possibleMovies();
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat.length;j++) {
				if(mat[i][j]) {
					return true;				
				}
			}
		}
		return false;
	} 
}
