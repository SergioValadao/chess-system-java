package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program{

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chess = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while (true) {
			try {
				UI.clearScreen();
				//UI.printBoard(chess.getPieces()); subistituida por printMatch
				UI.printMatch(chess, captured);
				System.out.println();
				System.out.print("Source (Letra e Numero): ");
				ChessPosition source = UI.readChessPosition(sc);
				boolean[][] possibleMoves = chess.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chess.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target (Letra e Numero): ");
				ChessPosition target = UI.readChessPosition(sc);				
				ChessPiece capturedPiece = chess.performChessMove(source, target);
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
			}catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}