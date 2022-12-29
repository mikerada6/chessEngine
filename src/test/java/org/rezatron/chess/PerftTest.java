package org.rezatron.chess;

import junit.framework.TestCase;


import static org.rezatron.chess.Perft.perft;

public class PerftTest extends TestCase {

    public void testPerft1() {
        Board b = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        assertEquals(20, perft(b, 1));
        assertEquals(400, perft(b, 2));
        assertEquals(8902, perft(b, 3));
        assertEquals(197281, perft(b, 4));
        assertEquals(4865609, perft(b, 5));
        assertEquals(119060324, perft(b, 6));
    }

    public void testPerft2() {
        Board b = new Board("3k4/3p4/8/K1P4r/8/8/8/8 b - - 0 1");
        assertEquals(18, perft(b, 1));
        assertEquals(92, perft(b, 2));
        assertEquals(1670, perft(b, 3));
        assertEquals(10138, perft(b, 4));
        assertEquals(185429, perft(b, 5));
        assertEquals(1134888, perft(b, 6));
    }
}
