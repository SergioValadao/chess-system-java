package boardgame;

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

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int col) {
		if(!positionExists(row, col)) {
			throw new BoardException("Posi��o n�o existe no tabuleiro");
		}
		return pieces[row][col];
	}
	
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posi��o n�o existe no tabuleiro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placepiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("J� existe uma pe�a nesta posi��o.");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posi��o invalida!");
		}
		if(piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {		
		return row >=0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posi��o n�o existe no tabuleiro");
		}
		return piece(position) != null;
	}
}
