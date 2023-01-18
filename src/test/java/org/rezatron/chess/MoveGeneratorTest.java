package org.rezatron.chess;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.rezatron.chess.constants.MoveFlags.*;

public
class MoveGeneratorTest extends TestCase {

    private static final Logger log = LogManager.getLogger(MoveGeneratorTest.class);


    public void testGetWhiteMoves() {
        Board b = new Board("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - ");
        System.out.println(b);
        MoveGenerator mg = new MoveGenerator(b);
        MoveList moves = mg.getWhiteMoves();
        System.out.println(moves);
        assertEquals(48,
                moves.size());
    }

    public void testGetWhiteStartingMoves() {

        Board b = new Board();
        System.out.println(b);
        MoveGenerator mg = new MoveGenerator(b);
        MoveList moves = mg.getWhiteMoves();
        assertEquals(20,
                moves.size());

        assertTrue(moves.contains(new Move(8,
                16,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(9,
                17,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(10,
                18,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(11,
                19,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(12,
                20,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(13,
                21,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(14,
                22,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(15,
                23,
                QUITE_MOVE_FLAG)));

        assertTrue(moves.contains(new Move(8,
                24,
                DOUBLE_PAWN_PUSH_FLAG)));
        assertTrue(moves.contains(new Move(9,
                25,
                DOUBLE_PAWN_PUSH_FLAG)));
        assertTrue(moves.contains(new Move(10,
                26,
                DOUBLE_PAWN_PUSH_FLAG)));
        assertTrue(moves.contains(new Move(11,
                27,
                DOUBLE_PAWN_PUSH_FLAG)));
        assertTrue(moves.contains(new Move(12,
                28,
                DOUBLE_PAWN_PUSH_FLAG)));
        assertTrue(moves.contains(new Move(13,
                29,
                DOUBLE_PAWN_PUSH_FLAG)));
        assertTrue(moves.contains(new Move(14,
                30,
                DOUBLE_PAWN_PUSH_FLAG)));
        assertTrue(moves.contains(new Move(15,
                31,
                DOUBLE_PAWN_PUSH_FLAG)));

        assertTrue(moves.contains(new Move(1,
                16,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(1,
                18,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(6,
                21,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(6,
                23,
                QUITE_MOVE_FLAG)));
    }

    public void testGetWhiteRookMoves() {

        Board b = new Board("r6r/1k6/8/8/8/8/1K6/R6R w - - 0 1");
        System.out.println(b);
        MoveGenerator mg = new MoveGenerator(b);
      MoveList moves = mg.getMoves();
        assertEquals(31,
                moves.size());

        assertTrue(moves.contains(new Move(0,
                1,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                2,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                3,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                4,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                5,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                6,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                8,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                16,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                24,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                32,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                40,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                48,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(0,
                56,
                CAPTURE_FLAG)));

        assertTrue(moves.contains(new Move(7,
                1,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                2,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                3,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                4,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                5,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                6,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                15,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                23,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                31,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                39,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                47,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                55,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(7,
                63,
                CAPTURE_FLAG)));

        assertTrue(moves.contains(new Move(9,
                17,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(9,
                18,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(9,
                10,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(9,
                2,
                QUITE_MOVE_FLAG)));
        assertTrue(moves.contains(new Move(9,
                1,
                QUITE_MOVE_FLAG)));

        assertFalse(moves.contains(new Move(9,
                16,
                QUITE_MOVE_FLAG)));
        assertFalse(moves.contains(new Move(9,
                8,
                QUITE_MOVE_FLAG)));

    }

}
