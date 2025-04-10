package Main;

import Pieces.Piece;

public class Move {

    int oldCol;
    int oldRow;
    int newRow;
    int newCol;

    Piece piece;
    Piece capture;

    public Move(Chessboard chessboard, Piece piece, int newCol, int newRow){

        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = chessboard.getPiece(newCol, newRow);
    }
}
