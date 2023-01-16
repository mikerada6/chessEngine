package org.rezatron.chess;

import org.rezatron.chess.constants.ChessPiece;
import org.rezatron.chess.constants.MoveFlags;

import static org.rezatron.chess.constants.ChessConstants.letterSquares;
import static org.rezatron.chess.constants.ChessConstants.letterSquaresHashMap;
import static org.rezatron.chess.constants.MoveFlags.*;

public class Move {


    private final int move;

    public Move(int from, int to, MoveFlags flag) {
        move = ((flag.getFlag() & 0xf) << 12) | ((from & 0x3f) << 6) | (to & 0x3f);
    }

    public Move(String move, Board b) {
        move = move.toLowerCase();
        String fromString = move.substring(0, 2);
        String toString = move.substring(2, 4);
        int from = Long.numberOfTrailingZeros(letterSquaresHashMap.get(fromString));
        int to = Long.numberOfTrailingZeros(letterSquaresHashMap.get(toString));
        ChessPiece fromPiece = b.pieceAtSquare(from);
        ChessPiece toPiece = b.pieceAtSquare(to);
        MoveFlags flag = QUITE_MOVE_FLAG;
        if (toPiece == ChessPiece.EMPTY)
            flag = QUITE_MOVE_FLAG;
        else {
            flag = CAPTURE_FLAG;
        }
        if ((fromPiece == ChessPiece.WHITE_PAWN || fromPiece == ChessPiece.BLACK_PAWN) && Math.abs(from - to) > 10) {
            flag = DOUBLE_PAWN_PUSH_FLAG;
        }
        this.move = ((flag.getFlag() & 0xf) << 12) | ((from & 0x3f) << 6) | (to & 0x3f);
    }

    public int getTo() {
        return move & 0x3f;
    }

    public int getFrom() {
        return (move >> 6) & 0x3f;
    }

    public int getFlags() {
        return (move >> 12) & 0x0f;
    }

    public boolean isCapture() {
        return (this.getFlags() & CAPTURE_FLAG.getFlag()) != 0;
    }

    public boolean isPromotion() {
        return (getFlags() & 8L) != 0;
    }

    public int getButterflyIndex() {
        return move & 0x0fff;
    }

    public int getMove() {
        return move;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move1 = (Move) o;

        return (move & 0xffff) == (move1.getMove() & 0xffff);
    }

    @Override
    public int hashCode() {
        return move;
    }

    public String toString() {
//        if(getFlags()== KING_CASTLE_FLAG.getFlag())
//            return "0-0";
//        if(getFlags()== QUEEN_CASTLE_FLAG.getFlag())
//            return "0-0-0";
        String ans = "";
        ans += letterSquares[getFrom()];
//        if(isCapture())
//            ans+="x";
        ans += letterSquares[getTo()];
        if (getFlags() == QUEEN_PROMOTION_CAPTURE_FLAG.getFlag() || getFlags() == QUEEN_PROMOTION_FLAG.getFlag()) {
            ans += "=Q";
        } else if (getFlags() == ROOK_PROMOTION_CAPTURE_FLAG.getFlag() || getFlags() == ROOK_PROMOTION_FLAG.getFlag()) {
            ans += "=R";
        } else if (getFlags() == KNIGHT_PROMOTION_CAPTURE_FLAG.getFlag() || getFlags() == KNIGHT_PROMOTION_FLAG.getFlag()) {
            ans += "=N";
        } else if (getFlags() == BISHOP_PROMOTION_CAPTURE_FLAG.getFlag() || getFlags() == BISHOP_PROMOTION_FLAG.getFlag()) {
            ans += "=B";
        }
        return ans;
    }
}
