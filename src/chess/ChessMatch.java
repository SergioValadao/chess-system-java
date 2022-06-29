package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Peao;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private Piece capturedPiece;
	private boolean check;
	private boolean checkMate;
	private List<Piece> pieceOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}	
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public  boolean getCheckMate() {
		return checkMate;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMovies();		
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source,target);
		Piece capturedPiece = makeMove(source, target);
		if(testeCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Você não pode fazer este movimento, pois te coloca em check.");
		}
		check = (testeCheck(opponent(currentPlayer)) ? true : false);
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		nextTurn();
		return (ChessPiece) capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem informada");
		}
		if(currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("Você não pode mover peça do adversário.");
		}
		if(!board.piece(position).IsThereAnyPossibleMovie()) {
			throw new ChessException("Não existe mais movimentos para a peça escolhida.");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMovies(target)){
			throw new ChessException("A peça escolhida não pode fazer este movimento"); 
		}			
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		capturedPiece = board.removePiece(target);		
		board.placepiece(p, target);
		if(capturedPiece != null) {
			pieceOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capteredPiece) {
		
		Piece p = board.removePiece(target);
		board.placepiece(p, source);
		
		if(capturedPiece != null) {
			board.placepiece(capturedPiece,target);
			capturedPieces.remove(capturedPiece);
			pieceOnTheBoard.add(capturedPiece);
		}
	}
	
	private Color opponent(Color color) {
		return color == Color.WHITE ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece Rei(Color color){
		List<Piece> list = pieceOnTheBoard.stream().filter(x -> ( (ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p:list) {
			if(p instanceof Rei) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " Rei on de board.");
	}
	
	private boolean testeCheck(Color color) {
		Position kingPosition = Rei(color).getChessPosition().toPosition();
		List<Piece> opponentPiece = pieceOnTheBoard.stream().filter(x -> ( (ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for(Piece p : opponentPiece) {
			boolean[][] mat = p.possibleMovies();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if(!testeCheck(color)) {
			return false;
		}
		List<Piece> list = pieceOnTheBoard.stream().filter(x -> ( (ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p: list) {
			boolean[][] mat= p.possibleMovies();
			for(int i=0; i<board.getRow(); i++) {
				for(int j=0; j<board.getColumns(); j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testeCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
						
					}
				}
			}
		}
		return false;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placepiece(piece, new ChessPosition(column, row).toPosition());
		pieceOnTheBoard.add(piece);
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer==Color.WHITE ? Color.BLACK : Color.WHITE);
	}
	
	private void initialSetup() {
		
		placeNewPiece('a', 1, new Torre(board, Color.WHITE));
        placeNewPiece('e', 1, new Rei(board, Color.WHITE));
        placeNewPiece('h', 1, new Torre(board, Color.WHITE));
        placeNewPiece('a', 2, new Peao(board, Color.WHITE));
        placeNewPiece('b', 2, new Peao(board, Color.WHITE));
        placeNewPiece('c', 2, new Peao(board, Color.WHITE));
        placeNewPiece('d', 2, new Peao(board, Color.WHITE));
        placeNewPiece('e', 2, new Peao(board, Color.WHITE));
        placeNewPiece('f', 2, new Peao(board, Color.WHITE));
        placeNewPiece('g', 2, new Peao(board, Color.WHITE));
        placeNewPiece('h', 2, new Peao(board, Color.WHITE));
        
        placeNewPiece('a', 8, new Torre(board, Color.BLACK));
        placeNewPiece('e', 8, new Rei(board, Color.BLACK));
        placeNewPiece('h', 8, new Torre(board, Color.BLACK));
        placeNewPiece('a', 7, new Peao(board, Color.BLACK));
        placeNewPiece('b', 7, new Peao(board, Color.BLACK));
        placeNewPiece('c', 7, new Peao(board, Color.BLACK));
        placeNewPiece('d', 7, new Peao(board, Color.BLACK));
		placeNewPiece('e', 7, new Peao(board, Color.BLACK));
		placeNewPiece('f', 7, new Peao(board, Color.BLACK));
		placeNewPiece('g', 7, new Peao(board, Color.BLACK));
		placeNewPiece('h', 7, new Peao(board, Color.BLACK));		
	}
	
}
