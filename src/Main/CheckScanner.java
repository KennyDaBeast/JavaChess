package Main;

import Pieces.Piece;

public class CheckScanner {

    Chessboard chessboard;

    public CheckScanner(Chessboard chessboard){
        this.chessboard = chessboard;
    }


    public boolean isKingCheacked(Move move){

        Piece king = chessboard.findKing(move.piece.isWhite);
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;

        if(chessboard.SelectedPiece != null && chessboard.SelectedPiece.name.equals("king")){
            kingCol = move.newCol;
            kingRow = move.newRow;
        }


        return  hitByRook  (move.newCol, move.newRow, king, kingCol, kingRow,0,1) || // up
                hitByRook  (move.newCol, move.newRow, king, kingCol, kingRow,1,0) || // right
                hitByRook  (move.newCol, move.newRow, king, kingCol, kingRow,0,-1) || // down
                hitByRook  (move.newCol, move.newRow, king, kingCol, kingRow,-1,0) || // left

                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow,-1, -1) || // up left
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow,1, -1) || // up right
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow,1, 1) || // down right
                hitByBishop(move.newCol, move.newRow, king, kingCol, kingRow,-1, 1) || // down left

                hitByKnight(move.newCol, move.newRow, king, kingCol, kingRow) ||
                hitByPawn(move.newCol, move.newRow, king, kingCol, kingRow) ||
                hitByKing(king, kingCol, kingRow);


    }

    private boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal){
        for (int i = 1; i < 8; i++){
            if(kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row ){
                break;
            }

            Piece piece = chessboard.getPiece((kingCol + (i * colVal)), kingRow + (i * rowVal));
            if (piece != null && piece != chessboard.SelectedPiece){
                if(!chessboard.sameTeam(piece, king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }


    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal){
        for (int i = 1; i < 8; i++){
            if(kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row ){
                break;
            }

            Piece piece = chessboard.getPiece((kingCol - (i * colVal)), kingRow - (i * rowVal));
            if (piece != null && piece != chessboard.SelectedPiece){
                if(!chessboard.sameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }


    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow){
        return  checkKnight(chessboard.getPiece(kingCol - 1, kingRow - 2), king, col, row) ||
                checkKnight(chessboard.getPiece(kingCol + 1, kingRow - 2), king, col, row) ||
                checkKnight(chessboard.getPiece(kingCol + 2, kingRow - 1), king, col, row) ||
                checkKnight(chessboard.getPiece(kingCol + 2, kingRow + 1), king, col, row) ||
                checkKnight(chessboard.getPiece(kingCol + 1, kingRow + 2), king, col, row) ||
                checkKnight(chessboard.getPiece(kingCol - 1, kingRow + 2), king, col, row) ||
                checkKnight(chessboard.getPiece(kingCol - 2, kingRow + 1), king, col, row) ||
                checkKnight(chessboard.getPiece(kingCol - 2, kingRow - 1), king, col, row);
    }


    private boolean checkKnight(Piece p, Piece k, int col, int row){
        return p != null && !chessboard.sameTeam(p, k) && p.name.equals("Knight") && !(p.col == col && p.row == row);
    }

    private boolean hitByKing(Piece king, int kingCol, int kingRow){
        return  checkKing(chessboard.getPiece(kingCol - 1, kingRow - 1), king) ||
                checkKing(chessboard.getPiece(kingCol + 1, kingRow - 1), king) ||
                checkKing(chessboard.getPiece(kingCol, kingRow - 1), king) ||
                checkKing(chessboard.getPiece(kingCol - 1, kingRow), king) ||
                checkKing(chessboard.getPiece(kingCol + 1, kingRow), king) ||
                checkKing(chessboard.getPiece(kingCol - 1, kingRow + 1), king) ||
                checkKing(chessboard.getPiece(kingCol + 1, kingRow + 1), king) ||
                checkKing(chessboard.getPiece(kingCol, kingRow + 1), king);
    }

    private boolean checkKing(Piece p, Piece k){
        return  p != null && !chessboard.sameTeam(p, k) && p.name.equals("king");
    }


    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow){
        int colorval = king.isWhite ? -1: +1;
        return  checkPawn(chessboard.getPiece(kingCol + 1, kingRow + colorval), king, col, row) ||
                checkPawn(chessboard.getPiece(kingCol - 1, kingRow + colorval), king, col, row);
    }

    private boolean checkPawn(Piece p, Piece k, int col, int row){
        return p != null && !chessboard.sameTeam(p,k) && p.name.equals("Pawn") && !(p.col == col && p.row == row);
    }

}
