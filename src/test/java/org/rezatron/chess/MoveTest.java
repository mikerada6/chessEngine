package org.rezatron.chess;

import junit.framework.TestCase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.rezatron.chess.constants.MoveFlags;

import static org.rezatron.chess.constants.MoveFlags.DOUBLE_PAWN_PUSH_FLAG;

public class MoveTest extends TestCase {

    public void testMove() {
        Move m = new Move(8, 24, DOUBLE_PAWN_PUSH_FLAG);

        assertEquals(4632, m.getMove());
        assertEquals(8, m.getFrom());
        assertEquals(24, m.getTo());
        assertFalse(m.isCapture());
    }

    @ParameterizedTest
    @EnumSource(value = MoveFlags.class, names = {"QUITE_MOVE_FLAG", "DOUBLE_PAWN_PUSH_FLAG", "KING_CASTLE_FLAG", "QUEEN_CASTLE_FLAG", "CAPTURE_FLAG", "EP_CAPTURE_FLAG"})
        // six numbers
    void testNoPromotionFlags(MoveFlags flag) {
        Move m = new Move(8, 24, flag);
        assertFalse(m.isPromotion());
    }

    @ParameterizedTest
    @EnumSource(value = MoveFlags.class, names = {"KNIGHT_PROMOTION_FLAG", "BISHOP_PROMOTION_FLAG", "ROOK_PROMOTION_FLAG", "QUEEN_PROMOTION_FLAG", "KNIGHT_PROMOTION_CAPTURE_FLAG", "BISHOP_PROMOTION_CAPTURE_FLAG", "ROOK_PROMOTION_CAPTURE_FLAG", "QUEEN_PROMOTION_CAPTURE_FLAG"})
        // six numbers
    void testPromotionFlags(MoveFlags flag) {
        Move m = new Move(8, 24, flag);
        assertTrue(m.isPromotion());
    }

}