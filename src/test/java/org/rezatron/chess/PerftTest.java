package org.rezatron.chess;

import com.google.common.base.Stopwatch;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.constants.MoveFlags;


import static org.rezatron.chess.Perft.divide;
import static org.rezatron.chess.Perft.perft;
import static org.rezatron.chess.constants.MoveFlags.*;

public class PerftTest extends TestCase {

    private static final Logger log = LogManager.getLogger(PerftTest.class);

//    public void testDivide1() {
//        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1");
//        System.out.println(b);
//        b.move(new  Move(7, 5, QUITE_MOVE_FLAG));
//        b.move(new  Move(56, 57, QUITE_MOVE_FLAG));
////        b.move(new Move(60, 62, KING_CASTLE_FLAG));
//        System.out.println(b);
//        System.out.println(divide(b, 1));
//    }

    public void runPerftTest(long expected, Board b, int depth) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        long nodes = perft(b, depth);
        stopwatch.stop(); // optional
        if (nodes == expected)
            log.info("Perft ({}): {} nodes, Time {}, [{}], OK", depth, nodes, stopwatch, expected);
        else
            log.error("Perft ({}): {} nodes, Time {}, [{}], FALSE", depth, nodes, stopwatch, expected);
        assertEquals(b.getFEN() +" for depth " + depth, expected,nodes);

    }


    public void testPerft001() {
        Board b = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(20, b, 1);
        runPerftTest(400, b, 2);
        runPerftTest(8902, b, 3);
        runPerftTest(197281, b, 4);
        runPerftTest(4865609, b, 5);
        runPerftTest(119060324, b, 6);
    }


    public void testPerft002() {
        Board b = new Board("3k4/3p4/8/K1P4r/8/8/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(18, b, 1);
        runPerftTest(92, b, 2);
        runPerftTest(1670, b, 3);
        runPerftTest(10138, b, 4);
        runPerftTest(185429, b, 5);
        runPerftTest(1134888, b, 6);
    }


    public void testPerft003() {
        Board b = new Board("4k3/8/8/8/8/8/8/4K2R w K - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(15, b, 1);
        runPerftTest(66, b, 2);
        runPerftTest(1197, b, 3);
        runPerftTest(7059, b, 4);
        runPerftTest(133987, b, 5);
        runPerftTest(764643, b, 6);
    }


    public void testPerft004() {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(16, b, 1);
        runPerftTest(71, b, 2);
        runPerftTest(1287, b, 3);
        runPerftTest(7626, b, 4);
        runPerftTest(145232, b, 5);
        runPerftTest(846648, b, 6);
    }


    public void testPerft005() {
        Board b = new Board("4k2r/8/8/8/8/8/8/4K3 w k - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(75, b, 2);
        runPerftTest(459, b, 3);
        runPerftTest(8290, b, 4);
        runPerftTest(47635, b, 5);
        runPerftTest(899442, b, 6);
    }


    public void testPerft006() {
        Board b = new Board("r3k3/8/8/8/8/8/8/4K3 w q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(80, b, 2);
        runPerftTest(493, b, 3);
        runPerftTest(8897, b, 4);
        runPerftTest(52710, b, 5);
        runPerftTest(1001523, b, 6);
    }


    public void testPerft007() {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(26, b, 1);
        runPerftTest(112, b, 2);
        runPerftTest(3189, b, 3);
        runPerftTest(17945, b, 4);
        runPerftTest(532933, b, 5);
        runPerftTest(2788982, b, 6);
    }


    public void testPerft008() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(130, b, 2);
        runPerftTest(782, b, 3);
        runPerftTest(22180, b, 4);
        runPerftTest(118882, b, 5);
        runPerftTest(3517770, b, 6);
    }


    public void testPerft009() {
        Board b = new Board("8/8/8/8/8/8/6k1/4K2R w K - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(12, b, 1);
        runPerftTest(38, b, 2);
        runPerftTest(564, b, 3);
        runPerftTest(2219, b, 4);
        runPerftTest(37735, b, 5);
        runPerftTest(185867, b, 6);
    }


    public void testPerft010() {
        Board b = new Board("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(15, b, 1);
        runPerftTest(65, b, 2);
        runPerftTest(1018, b, 3);
        runPerftTest(4573, b, 4);
        runPerftTest(80619, b, 5);
        runPerftTest(413018, b, 6);
    }


    public void testPerft011() {
        Board b = new Board("4k2r/6K1/8/8/8/8/8/8 w k - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(32, b, 2);
        runPerftTest(134, b, 3);
        runPerftTest(2073, b, 4);
        runPerftTest(10485, b, 5);
        runPerftTest(179869, b, 6);
    }


    public void testPerft012() {
        Board b = new Board("r3k3/1K6/8/8/8/8/8/8 w q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(49, b, 2);
        runPerftTest(243, b, 3);
        runPerftTest(3991, b, 4);
        runPerftTest(20780, b, 5);
        runPerftTest(367724, b, 6);
    }


    public void testPerft013() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(26, b, 1);
        runPerftTest(568, b, 2);
        runPerftTest(13744, b, 3);
        runPerftTest(314346, b, 4);
        runPerftTest(7594526, b, 5);
        runPerftTest(179862938, b, 6);
    }


    public void testPerft014() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(567, b, 2);
        runPerftTest(14095, b, 3);
        runPerftTest(328965, b, 4);
        runPerftTest(8153719, b, 5);
        runPerftTest(195629489, b, 6);
    }


    public void testPerft015() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(548, b, 2);
        runPerftTest(13502, b, 3);
        runPerftTest(312835, b, 4);
        runPerftTest(7736373, b, 5);
        runPerftTest(184411439, b, 6);
    }


    public void testPerft016() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(547, b, 2);
        runPerftTest(13579, b, 3);
        runPerftTest(316214, b, 4);
        runPerftTest(7878456, b, 5);
        runPerftTest(189224276, b, 6);
    }


    public void testPerft017() {
        Board b = new Board("1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(26, b, 1);
        runPerftTest(583, b, 2);
        runPerftTest(14252, b, 3);
        runPerftTest(334705, b, 4);
        runPerftTest(8198901, b, 5);
        runPerftTest(198328929, b, 6);
    }


    public void testPerft018() {
        Board b = new Board("2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(560, b, 2);
        runPerftTest(13592, b, 3);
        runPerftTest(317324, b, 4);
        runPerftTest(7710115, b, 5);
        runPerftTest(185959088, b, 6);
    }


    public void testPerft019() {
        Board b = new Board("r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(560, b, 2);
        runPerftTest(13607, b, 3);
        runPerftTest(320792, b, 4);
        runPerftTest(7848606, b, 5);
        runPerftTest(190755813, b, 6);
    }


    public void testPerft020() {
        Board b = new Board("4k3/8/8/8/8/8/8/4K2R b K - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(75, b, 2);
        runPerftTest(459, b, 3);
        runPerftTest(8290, b, 4);
        runPerftTest(47635, b, 5);
        runPerftTest(899442, b, 6);
    }


    public void testPerft021() {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K3 b Q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(80, b, 2);
        runPerftTest(493, b, 3);
        runPerftTest(8897, b, 4);
        runPerftTest(52710, b, 5);
        runPerftTest(1001523, b, 6);
    }


    public void testPerft022() {
        Board b = new Board("4k2r/8/8/8/8/8/8/4K3 b k - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(15, b, 1);
        runPerftTest(66, b, 2);
        runPerftTest(1197, b, 3);
        runPerftTest(7059, b, 4);
        runPerftTest(133987, b, 5);
        runPerftTest(764643, b, 6);
    }


    public void testPerft023() {
        Board b = new Board("r3k3/8/8/8/8/8/8/4K3 b q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(16, b, 1);
        runPerftTest(71, b, 2);
        runPerftTest(1287, b, 3);
        runPerftTest(7626, b, 4);
        runPerftTest(145232, b, 5);
        runPerftTest(846648, b, 6);
    }


    public void testPerft024() {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(130, b, 2);
        runPerftTest(782, b, 3);
        runPerftTest(22180, b, 4);
        runPerftTest(118882, b, 5);
        runPerftTest(3517770, b, 6);
    }


    public void testPerft025() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(26, b, 1);
        runPerftTest(112, b, 2);
        runPerftTest(3189, b, 3);
        runPerftTest(17945, b, 4);
        runPerftTest(532933, b, 5);
        runPerftTest(2788982, b, 6);
    }


    public void testPerft026() {
        Board b = new Board("8/8/8/8/8/8/6k1/4K2R b K - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(32, b, 2);
        runPerftTest(134, b, 3);
        runPerftTest(2073, b, 4);
        runPerftTest(10485, b, 5);
        runPerftTest(179869, b, 6);
    }


    public void testPerft027() {
        Board b = new Board("8/8/8/8/8/8/1k6/R3K3 b Q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(49, b, 2);
        runPerftTest(243, b, 3);
        runPerftTest(3991, b, 4);
        runPerftTest(20780, b, 5);
        runPerftTest(367724, b, 6);
    }


    public void testPerft028() {
        Board b = new Board("4k2r/6K1/8/8/8/8/8/8 b k - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(12, b, 1);
        runPerftTest(38, b, 2);
        runPerftTest(564, b, 3);
        runPerftTest(2219, b, 4);
        runPerftTest(37735, b, 5);
        runPerftTest(185867, b, 6);
    }


    public void testPerft029() {
        Board b = new Board("r3k3/1K6/8/8/8/8/8/8 b q - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(15, b, 1);
        runPerftTest(65, b, 2);
        runPerftTest(1018, b, 3);
        runPerftTest(4573, b, 4);
        runPerftTest(80619, b, 5);
        runPerftTest(413018, b, 6);
    }


    public void testPerft030() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(26, b, 1);
        runPerftTest(568, b, 2);
        runPerftTest(13744, b, 3);
        runPerftTest(314346, b, 4);
        runPerftTest(7594526, b, 5);
        runPerftTest(179862938, b, 6);
    }


    public void testPerft031() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(26, b, 1);
        runPerftTest(583, b, 2);
        runPerftTest(14252, b, 3);
        runPerftTest(334705, b, 4);
        runPerftTest(8198901, b, 5);
        runPerftTest(198328929, b, 6);
    }


    public void testPerft032() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(560, b, 2);
        runPerftTest(13592, b, 3);
        runPerftTest(317324, b, 4);
        runPerftTest(7710115, b, 5);
        runPerftTest(185959088, b, 6);
    }


    public void testPerft033() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(560, b, 2);
        runPerftTest(13607, b, 3);
        runPerftTest(320792, b, 4);
        runPerftTest(7848606, b, 5);
        runPerftTest(190755813, b, 6);
    }


    public void testPerft034() {
        Board b = new Board("1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(567, b, 2);
        runPerftTest(14095, b, 3);
        runPerftTest(328965, b, 4);
        runPerftTest(8153719, b, 5);
        runPerftTest(195629489, b, 6);
    }


    public void testPerft035() {
        Board b = new Board("2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(548, b, 2);
        runPerftTest(13502, b, 3);
        runPerftTest(312835, b, 4);
        runPerftTest(7736373, b, 5);
        runPerftTest(184411439, b, 6);
    }


    public void testPerft036() {
        Board b = new Board("r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(25, b, 1);
        runPerftTest(547, b, 2);
        runPerftTest(13579, b, 3);
        runPerftTest(316214, b, 4);
        runPerftTest(7878456, b, 5);
        runPerftTest(189224276, b, 6);
    }


    public void testPerft037() {
        Board b = new Board("8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(14, b, 1);
        runPerftTest(195, b, 2);
        runPerftTest(2760, b, 3);
        runPerftTest(38675, b, 4);
        runPerftTest(570726, b, 5);
        runPerftTest(8107539, b, 6);
    }


    public void testPerft038() {
        Board b = new Board("8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(11, b, 1);
        runPerftTest(156, b, 2);
        runPerftTest(1636, b, 3);
        runPerftTest(20534, b, 4);
        runPerftTest(223507, b, 5);
        runPerftTest(2594412, b, 6);
    }


    public void testPerft039() {
        Board b = new Board("8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(19, b, 1);
        runPerftTest(289, b, 2);
        runPerftTest(4442, b, 3);
        runPerftTest(73584, b, 4);
        runPerftTest(1198299, b, 5);
        runPerftTest(19870403, b, 6);
    }


    public void testPerft040() {
        Board b = new Board("K7/8/2n5/1n6/8/8/8/k6N w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(51, b, 2);
        runPerftTest(345, b, 3);
        runPerftTest(5301, b, 4);
        runPerftTest(38348, b, 5);
        runPerftTest(588695, b, 6);
    }


    public void testPerft041() {
        Board b = new Board("k7/8/2N5/1N6/8/8/8/K6n w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(17, b, 1);
        runPerftTest(54, b, 2);
        runPerftTest(835, b, 3);
        runPerftTest(5910, b, 4);
        runPerftTest(92250, b, 5);
        runPerftTest(688780, b, 6);
    }


    public void testPerft042() {
        Board b = new Board("8/1n4N1/2k5/8/8/5K2/1N4n1/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(15, b, 1);
        runPerftTest(193, b, 2);
        runPerftTest(2816, b, 3);
        runPerftTest(40039, b, 4);
        runPerftTest(582642, b, 5);
        runPerftTest(8503277, b, 6);
    }


    public void testPerft043() {
        Board b = new Board("8/1k6/8/5N2/8/4n3/8/2K5 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(16, b, 1);
        runPerftTest(180, b, 2);
        runPerftTest(2290, b, 3);
        runPerftTest(24640, b, 4);
        runPerftTest(288141, b, 5);
        runPerftTest(3147566, b, 6);
    }


    public void testPerft044() {
        Board b = new Board("8/8/3K4/3Nn3/3nN3/4k3/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(68, b, 2);
        runPerftTest(1118, b, 3);
        runPerftTest(16199, b, 4);
        runPerftTest(281190, b, 5);
        runPerftTest(4405103, b, 6);
    }


    public void testPerft045() {
        Board b = new Board("K7/8/2n5/1n6/8/8/8/k6N b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(17, b, 1);
        runPerftTest(54, b, 2);
        runPerftTest(835, b, 3);
        runPerftTest(5910, b, 4);
        runPerftTest(92250, b, 5);
        runPerftTest(688780, b, 6);
    }


    public void testPerft046() {
        Board b = new Board("k7/8/2N5/1N6/8/8/8/K6n b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(51, b, 2);
        runPerftTest(345, b, 3);
        runPerftTest(5301, b, 4);
        runPerftTest(38348, b, 5);
        runPerftTest(588695, b, 6);
    }


    public void testPerft047() {
        Board b = new Board("B6b/8/8/8/2K5/4k3/8/b6B w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(17, b, 1);
        runPerftTest(278, b, 2);
        runPerftTest(4607, b, 3);
        runPerftTest(76778, b, 4);
        runPerftTest(1320507, b, 5);
        runPerftTest(22823890, b, 6);
    }


    public void testPerft048() {
        Board b = new Board("8/8/1B6/7b/7k/8/2B1b3/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(21, b, 1);
        runPerftTest(316, b, 2);
        runPerftTest(5744, b, 3);
        runPerftTest(93338, b, 4);
        runPerftTest(1713368, b, 5);
        runPerftTest(28861171, b, 6);
    }


    public void testPerft049() {
        Board b = new Board("k7/B7/1B6/1B6/8/8/8/K6b w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(21, b, 1);
        runPerftTest(144, b, 2);
        runPerftTest(3242, b, 3);
        runPerftTest(32955, b, 4);
        runPerftTest(787524, b, 5);
        runPerftTest(7881673, b, 6);
    }


    public void testPerft050() {
        Board b = new Board("K7/b7/1b6/1b6/8/8/8/k6B w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(7, b, 1);
        runPerftTest(143, b, 2);
        runPerftTest(1416, b, 3);
        runPerftTest(31787, b, 4);
        runPerftTest(310862, b, 5);
        runPerftTest(7382896, b, 6);
    }


    public void testPerft051() {
        Board b = new Board("B6b/8/8/8/2K5/5k2/8/b6B b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(6, b, 1);
        runPerftTest(106, b, 2);
        runPerftTest(1829, b, 3);
        runPerftTest(31151, b, 4);
        runPerftTest(530585, b, 5);
        runPerftTest(9250746, b, 6);
    }


    public void testPerft052() {
        Board b = new Board("8/8/1B6/7b/7k/8/2B1b3/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(17, b, 1);
        runPerftTest(309, b, 2);
        runPerftTest(5133, b, 3);
        runPerftTest(93603, b, 4);
        runPerftTest(1591064, b, 5);
        runPerftTest(29027891, b, 6);
    }


    public void testPerft053() {
        Board b = new Board("k7/B7/1B6/1B6/8/8/8/K6b b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(7, b, 1);
        runPerftTest(143, b, 2);
        runPerftTest(1416, b, 3);
        runPerftTest(31787, b, 4);
        runPerftTest(310862, b, 5);
        runPerftTest(7382896, b, 6);
    }


    public void testPerft054() {
        Board b = new Board("K7/b7/1b6/1b6/8/8/8/k6B b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(21, b, 1);
        runPerftTest(144, b, 2);
        runPerftTest(3242, b, 3);
        runPerftTest(32955, b, 4);
        runPerftTest(787524, b, 5);
        runPerftTest(7881673, b, 6);
    }


    public void testPerft055() {
        Board b = new Board("7k/RR6/8/8/8/8/rr6/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(19, b, 1);
        runPerftTest(275, b, 2);
        runPerftTest(5300, b, 3);
        runPerftTest(104342, b, 4);
        runPerftTest(2161211, b, 5);
        runPerftTest(44956585, b, 6);
    }


    public void testPerft056() {
        Board b = new Board("R6r/8/8/2K5/5k2/8/8/r6R w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(36, b, 1);
        runPerftTest(1027, b, 2);
        runPerftTest(29215, b, 3);
        runPerftTest(771461, b, 4);
        runPerftTest(20506480, b, 5);
//        runPerftTest(525169084, b, 6);
    }


    public void testPerft057() {
        Board b = new Board("7k/RR6/8/8/8/8/rr6/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(19, b, 1);
        runPerftTest(275, b, 2);
        runPerftTest(5300, b, 3);
        runPerftTest(104342, b, 4);
        runPerftTest(2161211, b, 5);
        runPerftTest(44956585, b, 6);
    }


    public void testPerft058() {
        Board b = new Board("R6r/8/8/2K5/5k2/8/8/r6R b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(36, b, 1);
        runPerftTest(1027, b, 2);
        runPerftTest(29227, b, 3);
        runPerftTest(771368, b, 4);
        runPerftTest(20521342, b, 5);
//        runPerftTest(524966748, b, 6);
    }


    public void testPerft059() {
        Board b = new Board("6kq/8/8/8/8/8/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(2, b, 1);
        runPerftTest(36, b, 2);
        runPerftTest(143, b, 3);
        runPerftTest(3637, b, 4);
        runPerftTest(14893, b, 5);
        runPerftTest(391507, b, 6);
    }


    public void testPerft060() {
        Board b = new Board("6KQ/8/8/8/8/8/8/7k b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(2, b, 1);
        runPerftTest(36, b, 2);
        runPerftTest(143, b, 3);
        runPerftTest(3637, b, 4);
        runPerftTest(14893, b, 5);
        runPerftTest(391507, b, 6);
    }


    public void testPerft061() {
        Board b = new Board("K7/8/8/3Q4/4q3/8/8/7k w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(6, b, 1);
        runPerftTest(35, b, 2);
        runPerftTest(495, b, 3);
        runPerftTest(8349, b, 4);
        runPerftTest(166741, b, 5);
        runPerftTest(3370175, b, 6);
    }


    public void testPerft062() {
        Board b = new Board("6qk/8/8/8/8/8/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(22, b, 1);
        runPerftTest(43, b, 2);
        runPerftTest(1015, b, 3);
        runPerftTest(4167, b, 4);
        runPerftTest(105749, b, 5);
        runPerftTest(419369, b, 6);
    }


    public void testPerft063() {
        Board b = new Board("6KQ/8/8/8/8/8/8/7k b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(2, b, 1);
        runPerftTest(36, b, 2);
        runPerftTest(143, b, 3);
        runPerftTest(3637, b, 4);
        runPerftTest(14893, b, 5);
        runPerftTest(391507, b, 6);
    }


    public void testPerft064() {
        Board b = new Board("K7/8/8/3Q4/4q3/8/8/7k b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(6, b, 1);
        runPerftTest(35, b, 2);
        runPerftTest(495, b, 3);
        runPerftTest(8349, b, 4);
        runPerftTest(166741, b, 5);
        runPerftTest(3370175, b, 6);
    }


    public void testPerft065() {
        Board b = new Board("8/8/8/8/8/K7/P7/k7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(7, b, 2);
        runPerftTest(43, b, 3);
        runPerftTest(199, b, 4);
        runPerftTest(1347, b, 5);
        runPerftTest(6249, b, 6);
    }


    public void testPerft066() {
        Board b = new Board("8/8/8/8/8/7K/7P/7k w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(7, b, 2);
        runPerftTest(43, b, 3);
        runPerftTest(199, b, 4);
        runPerftTest(1347, b, 5);
        runPerftTest(6249, b, 6);
    }


    public void testPerft067() {
        Board b = new Board("K7/p7/k7/8/8/8/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(1, b, 1);
        runPerftTest(3, b, 2);
        runPerftTest(12, b, 3);
        runPerftTest(80, b, 4);
        runPerftTest(342, b, 5);
        runPerftTest(2343, b, 6);
    }


    public void testPerft068() {
        Board b = new Board("7K/7p/7k/8/8/8/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(1, b, 1);
        runPerftTest(3, b, 2);
        runPerftTest(12, b, 3);
        runPerftTest(80, b, 4);
        runPerftTest(342, b, 5);
        runPerftTest(2343, b, 6);
    }


    public void testPerft069() {
        Board b = new Board("8/2k1p3/3pP3/3P2K1/8/8/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(7, b, 1);
        runPerftTest(35, b, 2);
        runPerftTest(210, b, 3);
        runPerftTest(1091, b, 4);
        runPerftTest(7028, b, 5);
        runPerftTest(34834, b, 6);
    }


    public void testPerft070() {
        Board b = new Board("8/8/8/8/8/K7/P7/k7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(1, b, 1);
        runPerftTest(3, b, 2);
        runPerftTest(12, b, 3);
        runPerftTest(80, b, 4);
        runPerftTest(342, b, 5);
        runPerftTest(2343, b, 6);
    }


    public void testPerft071() {
        Board b = new Board("8/8/8/8/8/7K/7P/7k b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(1, b, 1);
        runPerftTest(3, b, 2);
        runPerftTest(12, b, 3);
        runPerftTest(80, b, 4);
        runPerftTest(342, b, 5);
        runPerftTest(2343, b, 6);
    }


    public void testPerft072() {
        Board b = new Board("K7/p7/k7/8/8/8/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(7, b, 2);
        runPerftTest(43, b, 3);
        runPerftTest(199, b, 4);
        runPerftTest(1347, b, 5);
        runPerftTest(6249, b, 6);
    }


    public void testPerft073() {
        Board b = new Board("7K/7p/7k/8/8/8/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(7, b, 2);
        runPerftTest(43, b, 3);
        runPerftTest(199, b, 4);
        runPerftTest(1347, b, 5);
        runPerftTest(6249, b, 6);
    }


    public void testPerft074() {
        Board b = new Board("8/2k1p3/3pP3/3P2K1/8/8/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(35, b, 2);
        runPerftTest(182, b, 3);
        runPerftTest(1091, b, 4);
        runPerftTest(5408, b, 5);
        runPerftTest(34822, b, 6);
    }


    public void testPerft075() {
        Board b = new Board("8/8/8/8/8/4k3/4P3/4K3 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(2, b, 1);
        runPerftTest(8, b, 2);
        runPerftTest(44, b, 3);
        runPerftTest(282, b, 4);
        runPerftTest(1814, b, 5);
        runPerftTest(11848, b, 6);
    }


    public void testPerft076() {
        Board b = new Board("4k3/4p3/4K3/8/8/8/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(2, b, 1);
        runPerftTest(8, b, 2);
        runPerftTest(44, b, 3);
        runPerftTest(282, b, 4);
        runPerftTest(1814, b, 5);
        runPerftTest(11848, b, 6);
    }


    public void testPerft077() {
        Board b = new Board("8/8/7k/7p/7P/7K/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(9, b, 2);
        runPerftTest(57, b, 3);
        runPerftTest(360, b, 4);
        runPerftTest(1969, b, 5);
        runPerftTest(10724, b, 6);
    }


    public void testPerft078() {
        Board b = new Board("8/8/k7/p7/P7/K7/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(9, b, 2);
        runPerftTest(57, b, 3);
        runPerftTest(360, b, 4);
        runPerftTest(1969, b, 5);
        runPerftTest(10724, b, 6);
    }


    public void testPerft079() {
        Board b = new Board("8/8/3k4/3p4/3P4/3K4/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(25, b, 2);
        runPerftTest(180, b, 3);
        runPerftTest(1294, b, 4);
        runPerftTest(8296, b, 5);
        runPerftTest(53138, b, 6);
    }


    public void testPerft080() {
        Board b = new Board("8/3k4/3p4/8/3P4/3K4/8/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(8, b, 1);
        runPerftTest(61, b, 2);
        runPerftTest(483, b, 3);
        runPerftTest(3213, b, 4);
        runPerftTest(23599, b, 5);
        runPerftTest(157093, b, 6);
    }


    public void testPerft081() {
        Board b = new Board("8/8/3k4/3p4/8/3P4/3K4/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(8, b, 1);
        runPerftTest(61, b, 2);
        runPerftTest(411, b, 3);
        runPerftTest(3213, b, 4);
        runPerftTest(21637, b, 5);
        runPerftTest(158065, b, 6);
    }


    public void testPerft082() {
        Board b = new Board("k7/8/3p4/8/3P4/8/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(15, b, 2);
        runPerftTest(90, b, 3);
        runPerftTest(534, b, 4);
        runPerftTest(3450, b, 5);
        runPerftTest(20960, b, 6);
    }


    public void testPerft083() {
        Board b = new Board("8/8/7k/7p/7P/7K/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(9, b, 2);
        runPerftTest(57, b, 3);
        runPerftTest(360, b, 4);
        runPerftTest(1969, b, 5);
        runPerftTest(10724, b, 6);
    }


    public void testPerft084() {
        Board b = new Board("8/8/k7/p7/P7/K7/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(9, b, 2);
        runPerftTest(57, b, 3);
        runPerftTest(360, b, 4);
        runPerftTest(1969, b, 5);
        runPerftTest(10724, b, 6);
    }


    public void testPerft085() {
        Board b = new Board("8/8/3k4/3p4/3P4/3K4/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(25, b, 2);
        runPerftTest(180, b, 3);
        runPerftTest(1294, b, 4);
        runPerftTest(8296, b, 5);
        runPerftTest(53138, b, 6);
    }


    public void testPerft086() {
        Board b = new Board("8/3k4/3p4/8/3P4/3K4/8/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(8, b, 1);
        runPerftTest(61, b, 2);
        runPerftTest(411, b, 3);
        runPerftTest(3213, b, 4);
        runPerftTest(21637, b, 5);
        runPerftTest(158065, b, 6);
    }


    public void testPerft087() {
        Board b = new Board("8/8/3k4/3p4/8/3P4/3K4/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(8, b, 1);
        runPerftTest(61, b, 2);
        runPerftTest(483, b, 3);
        runPerftTest(3213, b, 4);
        runPerftTest(23599, b, 5);
        runPerftTest(157093, b, 6);
    }


    public void testPerft088() {
        Board b = new Board("k7/8/3p4/8/3P4/8/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(15, b, 2);
        runPerftTest(89, b, 3);
        runPerftTest(537, b, 4);
        runPerftTest(3309, b, 5);
        runPerftTest(21104, b, 6);
    }


    public void testPerft089() {
        Board b = new Board("7k/3p4/8/8/3P4/8/8/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(19, b, 2);
        runPerftTest(117, b, 3);
        runPerftTest(720, b, 4);
        runPerftTest(4661, b, 5);
        runPerftTest(32191, b, 6);
    }


    public void testPerft090() {
        Board b = new Board("7k/8/8/3p4/8/8/3P4/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(19, b, 2);
        runPerftTest(116, b, 3);
        runPerftTest(716, b, 4);
        runPerftTest(4786, b, 5);
        runPerftTest(30980, b, 6);
    }


    public void testPerft091() {
        Board b = new Board("k7/8/8/7p/6P1/8/8/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft092() {
        Board b = new Board("k7/8/7p/8/8/6P1/8/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft093() {
        Board b = new Board("k7/8/8/6p1/7P/8/8/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft094() {
        Board b = new Board("k7/8/6p1/8/8/7P/8/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft095() {
        Board b = new Board("k7/8/8/3p4/4p3/8/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(3, b, 1);
        runPerftTest(15, b, 2);
        runPerftTest(84, b, 3);
        runPerftTest(573, b, 4);
        runPerftTest(3013, b, 5);
        runPerftTest(22886, b, 6);
    }


    public void testPerft096() {
        Board b = new Board("k7/8/3p4/8/8/4P3/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4271, b, 5);
        runPerftTest(28662, b, 6);
    }


    public void testPerft097() {
        Board b = new Board("7k/3p4/8/8/3P4/8/8/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(19, b, 2);
        runPerftTest(117, b, 3);
        runPerftTest(720, b, 4);
        runPerftTest(5014, b, 5);
        runPerftTest(32167, b, 6);
    }


    public void testPerft098() {
        Board b = new Board("7k/8/8/3p4/8/8/3P4/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(19, b, 2);
        runPerftTest(117, b, 3);
        runPerftTest(712, b, 4);
        runPerftTest(4658, b, 5);
        runPerftTest(30749, b, 6);
    }


    public void testPerft099() {
        Board b = new Board("k7/8/8/7p/6P1/8/8/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft100() {
        Board b = new Board("k7/8/7p/8/8/6P1/8/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft101() {
        Board b = new Board("k7/8/8/6p1/7P/8/8/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft102() {
        Board b = new Board("k7/8/6p1/8/8/7P/8/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft103() {
        Board b = new Board("k7/8/8/3p4/4p3/8/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(15, b, 2);
        runPerftTest(102, b, 3);
        runPerftTest(569, b, 4);
        runPerftTest(4337, b, 5);
        runPerftTest(22579, b, 6);
    }


    public void testPerft104() {
        Board b = new Board("k7/8/3p4/8/8/4P3/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4271, b, 5);
        runPerftTest(28662, b, 6);
    }


    public void testPerft105() {
        Board b = new Board("7k/8/8/p7/1P6/8/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft106() {
        Board b = new Board("7k/8/p7/8/8/1P6/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft107() {
        Board b = new Board("7k/8/8/1p6/P7/8/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft108() {
        Board b = new Board("7k/8/1p6/8/8/P7/8/7K w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft109() {
        Board b = new Board("k7/7p/8/8/8/8/6P1/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(25, b, 2);
        runPerftTest(161, b, 3);
        runPerftTest(1035, b, 4);
        runPerftTest(7574, b, 5);
        runPerftTest(55338, b, 6);
    }


    public void testPerft110() {
        Board b = new Board("k7/6p1/8/8/8/8/7P/K7 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(25, b, 2);
        runPerftTest(161, b, 3);
        runPerftTest(1035, b, 4);
        runPerftTest(7574, b, 5);
        runPerftTest(55338, b, 6);
    }


    public void testPerft111() {
        Board b = new Board("3k4/3pp3/8/8/8/8/3PP3/3K4 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(7, b, 1);
        runPerftTest(49, b, 2);
        runPerftTest(378, b, 3);
        runPerftTest(2902, b, 4);
        runPerftTest(24122, b, 5);
        runPerftTest(199002, b, 6);
    }


    public void testPerft112() {
        Board b = new Board("7k/8/8/p7/1P6/8/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft113() {
        Board b = new Board("7k/8/p7/8/8/1P6/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft114() {
        Board b = new Board("7k/8/8/1p6/P7/8/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(22, b, 2);
        runPerftTest(139, b, 3);
        runPerftTest(877, b, 4);
        runPerftTest(6112, b, 5);
        runPerftTest(41874, b, 6);
    }


    public void testPerft115() {
        Board b = new Board("7k/8/1p6/8/8/P7/8/7K b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(4, b, 1);
        runPerftTest(16, b, 2);
        runPerftTest(101, b, 3);
        runPerftTest(637, b, 4);
        runPerftTest(4354, b, 5);
        runPerftTest(29679, b, 6);
    }


    public void testPerft116() {
        Board b = new Board("k7/7p/8/8/8/8/6P1/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(25, b, 2);
        runPerftTest(161, b, 3);
        runPerftTest(1035, b, 4);
        runPerftTest(7574, b, 5);
        runPerftTest(55338, b, 6);
    }


    public void testPerft117() {
        Board b = new Board("k7/6p1/8/8/8/8/7P/K7 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(5, b, 1);
        runPerftTest(25, b, 2);
        runPerftTest(161, b, 3);
        runPerftTest(1035, b, 4);
        runPerftTest(7574, b, 5);
        runPerftTest(55338, b, 6);
    }


    public void testPerft118() {
        Board b = new Board("3k4/3pp3/8/8/8/8/3PP3/3K4 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(7, b, 1);
        runPerftTest(49, b, 2);
        runPerftTest(378, b, 3);
        runPerftTest(2902, b, 4);
        runPerftTest(24122, b, 5);
        runPerftTest(199002, b, 6);
    }


    public void testPerft119() {
        Board b = new Board("8/Pk6/8/8/8/8/6Kp/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(11, b, 1);
        runPerftTest(97, b, 2);
        runPerftTest(887, b, 3);
        runPerftTest(8048, b, 4);
        runPerftTest(90606, b, 5);
        runPerftTest(1030499, b, 6);
    }


    public void testPerft120() {
        Board b = new Board("n1n5/1Pk5/8/8/8/8/5Kp1/5N1N w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(24, b, 1);
        runPerftTest(421, b, 2);
        runPerftTest(7421, b, 3);
        runPerftTest(124608, b, 4);
        runPerftTest(2193768, b, 5);
        runPerftTest(37665329, b, 6);
    }


    public void testPerft121() {
        Board b = new Board("8/PPPk4/8/8/8/8/4Kppp/8 w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(18, b, 1);
        runPerftTest(270, b, 2);
        runPerftTest(4699, b, 3);
        runPerftTest(79355, b, 4);
        runPerftTest(1533145, b, 5);
        runPerftTest(28859283, b, 6);
    }


    public void testPerft122() {
        Board b = new Board("n1n5/PPPk4/8/8/8/8/4Kppp/5N1N w - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(24, b, 1);
        runPerftTest(496, b, 2);
        runPerftTest(9483, b, 3);
        runPerftTest(182838, b, 4);
        runPerftTest(3605103, b, 5);
        runPerftTest(71179139, b, 6);
    }


    public void testPerft123() {
        Board b = new Board("8/Pk6/8/8/8/8/6Kp/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(11, b, 1);
        runPerftTest(97, b, 2);
        runPerftTest(887, b, 3);
        runPerftTest(8048, b, 4);
        runPerftTest(90606, b, 5);
        runPerftTest(1030499, b, 6);
    }


    public void testPerft124() {
        Board b = new Board("n1n5/1Pk5/8/8/8/8/5Kp1/5N1N b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(24, b, 1);
        runPerftTest(421, b, 2);
        runPerftTest(7421, b, 3);
        runPerftTest(124608, b, 4);
        runPerftTest(2193768, b, 5);
        runPerftTest(37665329, b, 6);
    }


    public void testPerft125() {
        Board b = new Board("8/PPPk4/8/8/8/8/4Kppp/8 b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(18, b, 1);
        runPerftTest(270, b, 2);
        runPerftTest(4699, b, 3);
        runPerftTest(79355, b, 4);
        runPerftTest(1533145, b, 5);
        runPerftTest(28859283, b, 6);
    }


    public void testPerft126() {
        Board b = new Board("n1n5/PPPk4/8/8/8/8/4Kppp/5N1N b - - 0 1");
        log.info("fen: {}", b.getFEN());
        runPerftTest(24, b, 1);
        runPerftTest(496, b, 2);
        runPerftTest(9483, b, 3);
        runPerftTest(182838, b, 4);
        runPerftTest(3605103, b, 5);
        runPerftTest(71179139, b, 6);
    }


    public void testPerft127() {
        Board b = new Board("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1 ");
        log.info("fen: {}", b.getFEN());
        runPerftTest(48, b, 1);
        runPerftTest(2039, b, 2);
        runPerftTest(97862, b, 3);
        runPerftTest(4085603, b, 4);
        runPerftTest(193690690, b, 5) ;
    }


    public void testPerft128() {
        Board b = new Board("rnbQkbnr/3ppppp/p1p5/8/8/2P5/PP1PPPPP/RNB1KBNR b KQkq - 0 ");
        log.info("fen: {}", b.getFEN());
        runPerftTest(1, b, 1);
        runPerftTest(19, b, 2);
        runPerftTest(342, b, 3);
        runPerftTest(7095, b, 4);
        runPerftTest(140931, b, 5);
        runPerftTest(3151343, b, 6);
        runPerftTest(67820026, b, 7);
    }

}
