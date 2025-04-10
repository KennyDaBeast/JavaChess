package Main;


import Pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {

    Chessboard chessboard;

    public Input(Chessboard chessboard){
        this.chessboard = chessboard;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX() / chessboard.tileSize;
        int row = e.getY() / chessboard.tileSize;

        Piece pieceXY = chessboard.getPiece(col, row);
        if (pieceXY != null){
            chessboard.SelectedPiece = pieceXY;
        }

    }


    @Override
    public void mouseDragged(MouseEvent e) {

        if(chessboard.SelectedPiece != null){
            chessboard.SelectedPiece.xPos = e.getX() - chessboard.tileSize / 2;
            chessboard.SelectedPiece.yPos = e.getY() - chessboard.tileSize / 2;

            chessboard.repaint();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        int col = e.getX() / chessboard.tileSize;
        int row = e.getY() / chessboard.tileSize;

        if(chessboard.SelectedPiece != null){
            Move move = new Move(chessboard, chessboard.SelectedPiece, col ,row);

            if (chessboard.isValidMove(move)){
                chessboard.makeMove(move);
            } else {
                chessboard.SelectedPiece.xPos = chessboard.SelectedPiece.col * chessboard.tileSize;
                chessboard.SelectedPiece.yPos = chessboard.SelectedPiece.row * chessboard.tileSize;
            }
        }

        chessboard.SelectedPiece = null;
        chessboard.repaint();

    }



}
