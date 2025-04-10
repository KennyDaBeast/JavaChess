package Pieces;

import Main.Chessboard;

import java.awt.image.BufferedImage;

public class Pawn extends Piece{
    public Pawn(Chessboard chessboard, int col, int row, boolean isWhite){
        super(chessboard);
        this.col = col;
        this.row = row;
        this.xPos = col * chessboard.tileSize;
        this.yPos = row * chessboard.tileSize;

        this.isWhite = isWhite;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(chessboard.tileSize, chessboard.tileSize, BufferedImage.SCALE_SMOOTH);


    }

    public boolean isValidMovement(int col, int row){

        int colorIndex = isWhite ? 1 : -1;

        // push pawn 1
        if (this.col == col && row == this.row - colorIndex && chessboard.getPiece(col,row) == null)
            return true;

        // push pawn 2
        if (isFirstMove && this.col == col && row == this.row - colorIndex * 2 && chessboard.getPiece(col,row) == null && chessboard.getPiece(col,row + colorIndex) == null)
            return true;

        // capture left
        if (col == this.col - 1 && row == this.row - colorIndex && chessboard.getPiece(col,row) != null)
            return  true;

        // capture right
        if (col == this.col + 1 && row == this.row - colorIndex && chessboard.getPiece(col,row) != null)
            return  true;

        // en passant  left
        if(chessboard.getTileNum(col,row) == chessboard.enPassantTile && col == this.col - 1 && row == this.row - colorIndex && chessboard.getPiece(col, row + colorIndex) != null){
            return true;
        }

        // en passant  right
        if(chessboard.getTileNum(col,row) == chessboard.enPassantTile && col == this.col + 1 && row == this.row - colorIndex && chessboard.getPiece(col, row + colorIndex) != null){
            return true;
        }

        return false;
    }

}
