package com.tic.tac.toe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {

	Board gameBoard;
	Deque<Player> playersList;
	
	public TicTacToeGame(){
		initializeGame();
	}

	private void initializeGame() {

		playersList = new LinkedList<>();
		// add players
		playersList.add(new Player("Manika", new PlayingPiece(PlayingPieceType.O)));
		playersList.add(new Player("Anubhav", new PlayingPiece(PlayingPieceType.X)));

		gameBoard = new Board(3);
	}

	public void startGame() {

		boolean noWinner = true;

		while (noWinner) {

			Player playerTurn = playersList.removeFirst();
			printBoard();
			if(gameBoard.getFreeCells()==0) {
				System.out.println("No winner !! Match has tied");
				break;
			}
			System.out.println("Player " + playerTurn.getPlayerId() + " enter pos-> row,col");

			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			int row = Integer.valueOf(input.split(",")[0]);
			int col = Integer.valueOf(input.split(",")[1]);

			if (!gameBoard.addPieceToCell(row, col,playerTurn.getPlayingPiece())) {
				System.out.println("Invalid location ! Please try again");
				playersList.addFirst(playerTurn);
				continue;
			}
			
			playersList.addLast(playerTurn);
			if(isThereWinner(row,col,playerTurn)) {
				System.out.println("Player "+playerTurn.getPlayerId()+ " has Won !!!!!! :-)");
				printBoard();
				break;
			}

		}
	}

	private boolean isThereWinner(int row, int column, Player playerTurn) {
		
		PlayingPieceType pieceType=playerTurn.getPlayingPiece().pieceType;
		
	    boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;

	}

	private void printBoard() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		for(int i=0;i<gameBoard.size;i++) {
			for(int j=0;j<gameBoard.size;j++) {
				if(gameBoard.board[i][j]==null) {
					System.out.print("- ");
				}else {
					System.out.print(gameBoard.board[i][j].pieceType+ " ");
				}
			}
			System.out.println("");
		}
		System.out.println("");

	}

}
