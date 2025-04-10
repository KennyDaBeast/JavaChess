package Pieces;

import Main.Chessboard;
import Main.Move;

import java.awt.image.BufferedImage;

public class King extends Piece{
    public King(Chessboard chessboard, int col, int row, boolean isWhite){
        super(chessboard);
        this.col = col;
        this.row = row;
        this.xPos = col * chessboard.tileSize;
        this.yPos = row * chessboard.tileSize;

        this.isWhite = isWhite;
        this.name = "King";

        this.sprite = sheet.getSubimage(0, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(chessboard.tileSize, chessboard.tileSize, BufferedImage.SCALE_SMOOTH);


    }

    public boolean isValidMovement(int col, int row){
        return Math.abs((col - this.col) * (row - this.row)) == 1 || Math.abs(col - this.col) + Math.abs(row - this.row) == 1 || canCastle(col,row);
    }

    private boolean canCastle(int col, int row){

        if(this.row == row){

            if(col == 6){
                Piece rook = chessboard.getPiece(7, row);
                if (rook != null && rook.isFirstMove && isFirstMove){
                    return  chessboard.getPiece(5, row) == null &&
                            chessboard.getPiece(6, row) == null &&
                            !chessboard.checkScanner.isKingCheacked(new Move(chessboard, this, 5, row));
                }
            } else if (col == 2) {
                Piece rook = chessboard.getPiece(0, row);
                if (rook != null && rook.isFirstMove && isFirstMove){
                    return  chessboard.getPiece(3, row) == null &&
                            chessboard.getPiece(2, row) == null &&
                            chessboard.getPiece(1, row) == null &&
                            !chessboard.checkScanner.isKingCheacked(new Move(chessboard, this, 3, row));
                }


            }

        }

        return false;
    }

}
