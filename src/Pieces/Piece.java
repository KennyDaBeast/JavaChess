package Pieces;

import Main.Chessboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    public int col, row;
    public int xPos, yPos;

    public boolean isWhite;
    public String name;
    public int value;

    public boolean isFirstMove = true;

    BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("Pieces.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    protected int sheetScale = sheet.getWidth()/6;

    Image sprite;

    Chessboard chessboard;

    public Piece(Chessboard chessboard){
        this.chessboard = chessboard;

    }

    public void paint(Graphics2D g2d) {
        g2d.drawImage(sprite, xPos, yPos, null );
    }


    public boolean isValidMovement(int col, int row) {
        return true;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    }


}
