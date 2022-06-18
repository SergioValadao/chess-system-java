package boardgame;

import boardgame.Piece;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRow() {
		return rows;
	}

	public void setRow(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece(int row, int col) {
		return pieces[row][col];
	}
	
	public Piece piece(Position position) {		
		return pieces[position.getRow()][position.getColumn()];
	}

}
