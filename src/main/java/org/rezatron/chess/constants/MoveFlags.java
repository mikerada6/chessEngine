package org.rezatron.chess.constants;

public enum MoveFlags {

    QUITE_MOVE_FLAG(0),
    DOUBLE_PAWN_PUSH_FLAG(1),
    KING_CASTLE_FLAG(2),
    QUEEN_CASTLE_FLAG(3),
    CAPTURE_FLAG(4),
    EP_CAPTURE_FLAG(5),
    KNIGHT_PROMOTION_FLAG(8),
    BISHOP_PROMOTION_FLAG(9),
    ROOK_PROMOTION_FLAG(10),
    QUEEN_PROMOTION_FLAG(11),
    KNIGHT_PROMOTION_CAPTURE_FLAG(12),
    BISHOP_PROMOTION_CAPTURE_FLAG(13),
    ROOK_PROMOTION_CAPTURE_FLAG(14),
    QUEEN_PROMOTION_CAPTURE_FLAG(15);

    private final int flagValue;

    MoveFlags(int flagValue) {
        this.flagValue = flagValue;
    }

    public int getFlag() {
        return flagValue;
    }

}
