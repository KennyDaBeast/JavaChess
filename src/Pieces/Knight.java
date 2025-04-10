package Pieces;

import Main.Chessboard;

import java.awt.image.BufferedImage;

public class Knight extends Piece{
    public Knight(Chessboard chessboard, int col, int row, boolean isWhite){
        super(chessboard);
        this.col = col;
        this.row = row;
        this.xPos = col * chessboard.tileSize;
        this.yPos = row * chessboard.tileSize;

        this.isWhite = isWhite;
        this.name = "Knight";

        this.sprite = sheet.getSubimage(3 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(chessboard.tileSize, chessboard.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col , int row){
        return Math.abs(col - this.col) * Math.abs(row - this.row) == 2;
    }

}
