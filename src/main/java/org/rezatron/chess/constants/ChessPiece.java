package org.rezatron.chess.constants;

import static org.rezatron.chess.constants.ChessConstants.*;

public enum ChessPiece {
    WHITE_PAWN(whitePawnBitBoard), WHITE_ROOK(whiteRookBitBoard), WHITE_KNIGHT(whiteKingBitBoard), WHITE_BISHOP(whiteBitBoard), WHITE_QUEEN(whiteQueenBitBoard), WHITE_KING(whiteKingBitBoard),
    BLACK_PAWN(blackPawnBitBoard), BLACK_ROOK(blackRookBitBoard), BLACK_KNIGHT(blackKingBitBoard), BLACK_BISHOP(blackBishopBitBoard), BLACK_QUEEN(blackQueenBitBoard), BLACK_KING(blackKingBitBoard),
    EMPTY(emptyBitBoard);

    private int bitBoardIndex;

    ChessPiece(int bitBoardIndex) {
        this.bitBoardIndex = bitBoardIndex;
    }

    public int getBitBoardIndex() {
        return bitBoardIndex;
    }
}