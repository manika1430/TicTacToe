package com.tic.tac.toe;

public class Board {
	
	int size;
	PlayingPiece [][] board;
	
	Board(int size){
		this.size=size;
		board=new PlayingPiece[size][size];
	}

	public boolean addPieceToCell(int row, int col, PlayingPiece playingPiece) {
		if(row>=size || row<0 || col>=size || col<0 || board[row][col]!=null)
		return false;
		
		board[row][col]=playingPiece;
		return true;
	}

	public int getFreeCells() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(board[i][j]==null)
					return 1;
			}
		}
		return 0;
	}

}
