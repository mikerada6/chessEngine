package org.rezatron.chess;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.constants.MoveFlags;


import static org.rezatron.chess.Perft.divide;
import static org.rezatron.chess.Perft.perft;

public class PerftTest extends TestCase {

    private static final Logger log = LogManager.getLogger(PerftTest.class);

    public void testDivide1() {
        Board b = new Board("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1");
        System.out.println(b);
       System.out.println(divide(b,1));
    }

    
    public void testPerft001()
    {
        Board b = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(20, perft(b,1));
        assertEquals(400, perft(b,2));
        assertEquals(8902, perft(b,3));
        assertEquals(197281, perft(b,4));
        assertEquals(4865609, perft(b,5));
        assertEquals(119060324, perft(b,6));
    }

    
    public void testPerft002()
    {
        Board b = new Board("3k4/3p4/8/K1P4r/8/8/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(18, perft(b,1));
        assertEquals(92, perft(b,2));
        assertEquals(1670, perft(b,3));
        assertEquals(10138, perft(b,4));
        assertEquals(185429, perft(b,5));
        assertEquals(1134888, perft(b,6));
    }

    
    public void testPerft003()
    {
        Board b = new Board("4k3/8/8/8/8/8/8/4K2R w K - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(15, perft(b,1));
        assertEquals(66, perft(b,2));
        assertEquals(1197, perft(b,3));
        assertEquals(7059, perft(b,4));
        assertEquals(133987, perft(b,5));
        assertEquals(764643, perft(b,6));
    }


    
    public void testPerft004()
    {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K3 w Q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(16, perft(b,1));
        assertEquals(71, perft(b,2));
        assertEquals(1287, perft(b,3));
        assertEquals(7626, perft(b,4));
        assertEquals(145232, perft(b,5));
        assertEquals(846648, perft(b,6));
    }

    
    public void testPerft005()
    {
        Board b = new Board("4k2r/8/8/8/8/8/8/4K3 w k - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(75, perft(b,2));
        assertEquals(459, perft(b,3));
        assertEquals(8290, perft(b,4));
        assertEquals(47635, perft(b,5));
        assertEquals(899442, perft(b,6));
    }

    
    public void testPerft006()
    {
        Board b = new Board("r3k3/8/8/8/8/8/8/4K3 w q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(80, perft(b,2));
        assertEquals(493, perft(b,3));
        assertEquals(8897, perft(b,4));
        assertEquals(52710, perft(b,5));
        assertEquals(1001523, perft(b,6));
    }

    
    public void testPerft007()
    {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K2R w KQ - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(26, perft(b,1));
        assertEquals(112, perft(b,2));
        assertEquals(3189, perft(b,3));
        assertEquals(17945, perft(b,4));
        assertEquals(532933, perft(b,5));
        assertEquals(2788982, perft(b,6));
    }

    
    public void testPerft008()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/4K3 w kq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(130, perft(b,2));
        assertEquals(782, perft(b,3));
        assertEquals(22180, perft(b,4));
        assertEquals(118882, perft(b,5));
        assertEquals(3517770, perft(b,6));
    }

    
    public void testPerft009()
    {
        Board b = new Board("8/8/8/8/8/8/6k1/4K2R w K - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(12, perft(b,1));
        assertEquals(38, perft(b,2));
        assertEquals(564, perft(b,3));
        assertEquals(2219, perft(b,4));
        assertEquals(37735, perft(b,5));
        assertEquals(185867, perft(b,6));
    }

    
    public void testPerft010()
    {
        Board b = new Board("8/8/8/8/8/8/1k6/R3K3 w Q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(15, perft(b,1));
        assertEquals(65, perft(b,2));
        assertEquals(1018, perft(b,3));
        assertEquals(4573, perft(b,4));
        assertEquals(80619, perft(b,5));
        assertEquals(413018, perft(b,6));
    }

    
    public void testPerft011()
    {
        Board b = new Board("4k2r/6K1/8/8/8/8/8/8 w k - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(32, perft(b,2));
        assertEquals(134, perft(b,3));
        assertEquals(2073, perft(b,4));
        assertEquals(10485, perft(b,5));
        assertEquals(179869, perft(b,6));
    }

    
    public void testPerft012()
    {
        Board b = new Board("r3k3/1K6/8/8/8/8/8/8 w q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(49, perft(b,2));
        assertEquals(243, perft(b,3));
        assertEquals(3991, perft(b,4));
        assertEquals(20780, perft(b,5));
        assertEquals(367724, perft(b,6));
    }

    
    public void testPerft013()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K2R w KQkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(26, perft(b,1));
        assertEquals(568, perft(b,2));
        assertEquals(13744, perft(b,3));
        assertEquals(314346, perft(b,4));
        assertEquals(7594526, perft(b,5));
        assertEquals(179862938, perft(b,6));
    }

    
    public void testPerft014()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/1R2K2R w Kkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(567, perft(b,2));
        assertEquals(14095, perft(b,3));
        assertEquals(328965, perft(b,4));
        assertEquals(8153719, perft(b,5));
        assertEquals(195629489, perft(b,6));
    }

    
    public void testPerft015()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/2R1K2R w Kkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(548, perft(b,2));
        assertEquals(13502, perft(b,3));
        assertEquals(312835, perft(b,4));
        assertEquals(7736373, perft(b,5));
        assertEquals(184411439, perft(b,6));
    }

    
    public void testPerft016()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K1R1 w Qkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(547, perft(b,2));
        assertEquals(13579, perft(b,3));
        assertEquals(316214, perft(b,4));
        assertEquals(7878456, perft(b,5));
        assertEquals(189224276, perft(b,6));
    }

    
    public void testPerft017()
    {
        Board b = new Board("1r2k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(26, perft(b,1));
        assertEquals(583, perft(b,2));
        assertEquals(14252, perft(b,3));
        assertEquals(334705, perft(b,4));
        assertEquals(8198901, perft(b,5));
        assertEquals(198328929, perft(b,6));
    }

    
    public void testPerft018()
    {
        Board b = new Board("2r1k2r/8/8/8/8/8/8/R3K2R w KQk - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(560, perft(b,2));
        assertEquals(13592, perft(b,3));
        assertEquals(317324, perft(b,4));
        assertEquals(7710115, perft(b,5));
        assertEquals(185959088, perft(b,6));
    }

    
    public void testPerft019()
    {
        Board b = new Board("r3k1r1/8/8/8/8/8/8/R3K2R w KQq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(560, perft(b,2));
        assertEquals(13607, perft(b,3));
        assertEquals(320792, perft(b,4));
        assertEquals(7848606, perft(b,5));
        assertEquals(190755813, perft(b,6));
    }

    
    public void testPerft020()
    {
        Board b = new Board("4k3/8/8/8/8/8/8/4K2R b K - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(75, perft(b,2));
        assertEquals(459, perft(b,3));
        assertEquals(8290, perft(b,4));
        assertEquals(47635, perft(b,5));
        assertEquals(899442, perft(b,6));
    }

    
    public void testPerft021()
    {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K3 b Q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(80, perft(b,2));
        assertEquals(493, perft(b,3));
        assertEquals(8897, perft(b,4));
        assertEquals(52710, perft(b,5));
        assertEquals(1001523, perft(b,6));
    }

    
    public void testPerft022()
    {
        Board b = new Board("4k2r/8/8/8/8/8/8/4K3 b k - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(15, perft(b,1));
        assertEquals(66, perft(b,2));
        assertEquals(1197, perft(b,3));
        assertEquals(7059, perft(b,4));
        assertEquals(133987, perft(b,5));
        assertEquals(764643, perft(b,6));
    }

    
    public void testPerft023()
    {
        Board b = new Board("r3k3/8/8/8/8/8/8/4K3 b q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(16, perft(b,1));
        assertEquals(71, perft(b,2));
        assertEquals(1287, perft(b,3));
        assertEquals(7626, perft(b,4));
        assertEquals(145232, perft(b,5));
        assertEquals(846648, perft(b,6));
    }

    
    public void testPerft024()
    {
        Board b = new Board("4k3/8/8/8/8/8/8/R3K2R b KQ - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(130, perft(b,2));
        assertEquals(782, perft(b,3));
        assertEquals(22180, perft(b,4));
        assertEquals(118882, perft(b,5));
        assertEquals(3517770, perft(b,6));
    }

    
    public void testPerft025()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/4K3 b kq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(26, perft(b,1));
        assertEquals(112, perft(b,2));
        assertEquals(3189, perft(b,3));
        assertEquals(17945, perft(b,4));
        assertEquals(532933, perft(b,5));
        assertEquals(2788982, perft(b,6));
    }

    
    public void testPerft026()
    {
        Board b = new Board("8/8/8/8/8/8/6k1/4K2R b K - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(32, perft(b,2));
        assertEquals(134, perft(b,3));
        assertEquals(2073, perft(b,4));
        assertEquals(10485, perft(b,5));
        assertEquals(179869, perft(b,6));
    }

    
    public void testPerft027()
    {
        Board b = new Board("8/8/8/8/8/8/1k6/R3K3 b Q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(49, perft(b,2));
        assertEquals(243, perft(b,3));
        assertEquals(3991, perft(b,4));
        assertEquals(20780, perft(b,5));
        assertEquals(367724, perft(b,6));
    }

    
    public void testPerft028()
    {
        Board b = new Board("4k2r/6K1/8/8/8/8/8/8 b k - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(12, perft(b,1));
        assertEquals(38, perft(b,2));
        assertEquals(564, perft(b,3));
        assertEquals(2219, perft(b,4));
        assertEquals(37735, perft(b,5));
        assertEquals(185867, perft(b,6));
    }

    
    public void testPerft029()
    {
        Board b = new Board("r3k3/1K6/8/8/8/8/8/8 b q - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(15, perft(b,1));
        assertEquals(65, perft(b,2));
        assertEquals(1018, perft(b,3));
        assertEquals(4573, perft(b,4));
        assertEquals(80619, perft(b,5));
        assertEquals(413018, perft(b,6));
    }

    
    public void testPerft030()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K2R b KQkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(26, perft(b,1));
        assertEquals(568, perft(b,2));
        assertEquals(13744, perft(b,3));
        assertEquals(314346, perft(b,4));
        assertEquals(7594526, perft(b,5));
        assertEquals(179862938, perft(b,6));
    }

    
    public void testPerft031()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/1R2K2R b Kkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(26, perft(b,1));
        assertEquals(583, perft(b,2));
        assertEquals(14252, perft(b,3));
        assertEquals(334705, perft(b,4));
        assertEquals(8198901, perft(b,5));
        assertEquals(198328929, perft(b,6));
    }

    
    public void testPerft032()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/2R1K2R b Kkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(560, perft(b,2));
        assertEquals(13592, perft(b,3));
        assertEquals(317324, perft(b,4));
        assertEquals(7710115, perft(b,5));
        assertEquals(185959088, perft(b,6));
    }

    
    public void testPerft033()
    {
        Board b = new Board("r3k2r/8/8/8/8/8/8/R3K1R1 b Qkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(560, perft(b,2));
        assertEquals(13607, perft(b,3));
        assertEquals(320792, perft(b,4));
        assertEquals(7848606, perft(b,5));
        assertEquals(190755813, perft(b,6));
    }

    
    public void testPerft034()
    {
        Board b = new Board("1r2k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(567, perft(b,2));
        assertEquals(14095, perft(b,3));
        assertEquals(328965, perft(b,4));
        assertEquals(8153719, perft(b,5));
        assertEquals(195629489, perft(b,6));
    }

    
    public void testPerft035()
    {
        Board b = new Board("2r1k2r/8/8/8/8/8/8/R3K2R b KQk - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(548, perft(b,2));
        assertEquals(13502, perft(b,3));
        assertEquals(312835, perft(b,4));
        assertEquals(7736373, perft(b,5));
        assertEquals(184411439, perft(b,6));
    }

    
    public void testPerft036()
    {
        Board b = new Board("r3k1r1/8/8/8/8/8/8/R3K2R b KQq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(25, perft(b,1));
        assertEquals(547, perft(b,2));
        assertEquals(13579, perft(b,3));
        assertEquals(316214, perft(b,4));
        assertEquals(7878456, perft(b,5));
        assertEquals(189224276, perft(b,6));
    }

    
    public void testPerft037()
    {
        Board b = new Board("8/1n4N1/2k5/8/8/5K2/1N4n1/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(14, perft(b,1));
        assertEquals(195, perft(b,2));
        assertEquals(2760, perft(b,3));
        assertEquals(38675, perft(b,4));
        assertEquals(570726, perft(b,5));
        assertEquals(8107539, perft(b,6));
    }

    
    public void testPerft038()
    {
        Board b = new Board("8/1k6/8/5N2/8/4n3/8/2K5 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(11, perft(b,1));
        assertEquals(156, perft(b,2));
        assertEquals(1636, perft(b,3));
        assertEquals(20534, perft(b,4));
        assertEquals(223507, perft(b,5));
        assertEquals(2594412, perft(b,6));
    }

    
    public void testPerft039()
    {
        Board b = new Board("8/8/4k3/3Nn3/3nN3/4K3/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(19, perft(b,1));
        assertEquals(289, perft(b,2));
        assertEquals(4442, perft(b,3));
        assertEquals(73584, perft(b,4));
        assertEquals(1198299, perft(b,5));
        assertEquals(19870403, perft(b,6));
    }

    
    public void testPerft040()
    {
        Board b = new Board("K7/8/2n5/1n6/8/8/8/k6N w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(51, perft(b,2));
        assertEquals(345, perft(b,3));
        assertEquals(5301, perft(b,4));
        assertEquals(38348, perft(b,5));
        assertEquals(588695, perft(b,6));
    }

    
    public void testPerft041()
    {
        Board b = new Board("k7/8/2N5/1N6/8/8/8/K6n w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(17, perft(b,1));
        assertEquals(54, perft(b,2));
        assertEquals(835, perft(b,3));
        assertEquals(5910, perft(b,4));
        assertEquals(92250, perft(b,5));
        assertEquals(688780, perft(b,6));
    }

    
    public void testPerft042()
    {
        Board b = new Board("8/1n4N1/2k5/8/8/5K2/1N4n1/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(15, perft(b,1));
        assertEquals(193, perft(b,2));
        assertEquals(2816, perft(b,3));
        assertEquals(40039, perft(b,4));
        assertEquals(582642, perft(b,5));
        assertEquals(8503277, perft(b,6));
    }

    
    public void testPerft043()
    {
        Board b = new Board("8/1k6/8/5N2/8/4n3/8/2K5 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(16, perft(b,1));
        assertEquals(180, perft(b,2));
        assertEquals(2290, perft(b,3));
        assertEquals(24640, perft(b,4));
        assertEquals(288141, perft(b,5));
        assertEquals(3147566, perft(b,6));
    }

    
    public void testPerft044()
    {
        Board b = new Board("8/8/3K4/3Nn3/3nN3/4k3/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(68, perft(b,2));
        assertEquals(1118, perft(b,3));
        assertEquals(16199, perft(b,4));
        assertEquals(281190, perft(b,5));
        assertEquals(4405103, perft(b,6));
    }

    
    public void testPerft045()
    {
        Board b = new Board("K7/8/2n5/1n6/8/8/8/k6N b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(17, perft(b,1));
        assertEquals(54, perft(b,2));
        assertEquals(835, perft(b,3));
        assertEquals(5910, perft(b,4));
        assertEquals(92250, perft(b,5));
        assertEquals(688780, perft(b,6));
    }

    
    public void testPerft046()
    {
        Board b = new Board("k7/8/2N5/1N6/8/8/8/K6n b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(51, perft(b,2));
        assertEquals(345, perft(b,3));
        assertEquals(5301, perft(b,4));
        assertEquals(38348, perft(b,5));
        assertEquals(588695, perft(b,6));
    }

    
    public void testPerft047()
    {
        Board b = new Board("B6b/8/8/8/2K5/4k3/8/b6B w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(17, perft(b,1));
        assertEquals(278, perft(b,2));
        assertEquals(4607, perft(b,3));
        assertEquals(76778, perft(b,4));
        assertEquals(1320507, perft(b,5));
        assertEquals(22823890, perft(b,6));
    }

    
    public void testPerft048()
    {
        Board b = new Board("8/8/1B6/7b/7k/8/2B1b3/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(21, perft(b,1));
        assertEquals(316, perft(b,2));
        assertEquals(5744, perft(b,3));
        assertEquals(93338, perft(b,4));
        assertEquals(1713368, perft(b,5));
        assertEquals(28861171, perft(b,6));
    }

    
    public void testPerft049()
    {
        Board b = new Board("k7/B7/1B6/1B6/8/8/8/K6b w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(21, perft(b,1));
        assertEquals(144, perft(b,2));
        assertEquals(3242, perft(b,3));
        assertEquals(32955, perft(b,4));
        assertEquals(787524, perft(b,5));
        assertEquals(7881673, perft(b,6));
    }

    
    public void testPerft050()
    {
        Board b = new Board("K7/b7/1b6/1b6/8/8/8/k6B w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(7, perft(b,1));
        assertEquals(143, perft(b,2));
        assertEquals(1416, perft(b,3));
        assertEquals(31787, perft(b,4));
        assertEquals(310862, perft(b,5));
        assertEquals(7382896, perft(b,6));
    }

    
    public void testPerft051()
    {
        Board b = new Board("B6b/8/8/8/2K5/5k2/8/b6B b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(6, perft(b,1));
        assertEquals(106, perft(b,2));
        assertEquals(1829, perft(b,3));
        assertEquals(31151, perft(b,4));
        assertEquals(530585, perft(b,5));
        assertEquals(9250746, perft(b,6));
    }

    
    public void testPerft052()
    {
        Board b = new Board("8/8/1B6/7b/7k/8/2B1b3/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(17, perft(b,1));
        assertEquals(309, perft(b,2));
        assertEquals(5133, perft(b,3));
        assertEquals(93603, perft(b,4));
        assertEquals(1591064, perft(b,5));
        assertEquals(29027891, perft(b,6));
    }

    
    public void testPerft053()
    {
        Board b = new Board("k7/B7/1B6/1B6/8/8/8/K6b b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(7, perft(b,1));
        assertEquals(143, perft(b,2));
        assertEquals(1416, perft(b,3));
        assertEquals(31787, perft(b,4));
        assertEquals(310862, perft(b,5));
        assertEquals(7382896, perft(b,6));
    }

    
    public void testPerft054()
    {
        Board b = new Board("K7/b7/1b6/1b6/8/8/8/k6B b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(21, perft(b,1));
        assertEquals(144, perft(b,2));
        assertEquals(3242, perft(b,3));
        assertEquals(32955, perft(b,4));
        assertEquals(787524, perft(b,5));
        assertEquals(7881673, perft(b,6));
    }

    
    public void testPerft055()
    {
        Board b = new Board("7k/RR6/8/8/8/8/rr6/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(19, perft(b,1));
        assertEquals(275, perft(b,2));
        assertEquals(5300, perft(b,3));
        assertEquals(104342, perft(b,4));
        assertEquals(2161211, perft(b,5));
        assertEquals(44956585, perft(b,6));
    }

    
    public void testPerft056()
    {
        Board b = new Board("R6r/8/8/2K5/5k2/8/8/r6R w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(36, perft(b,1));
        assertEquals(1027, perft(b,2));
        assertEquals(29215, perft(b,3));
        assertEquals(771461, perft(b,4));
        assertEquals(20506480, perft(b,5));
        assertEquals(525169084, perft(b,6));
    }

    
    public void testPerft057()
    {
        Board b = new Board("7k/RR6/8/8/8/8/rr6/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(19, perft(b,1));
        assertEquals(275, perft(b,2));
        assertEquals(5300, perft(b,3));
        assertEquals(104342, perft(b,4));
        assertEquals(2161211, perft(b,5));
        assertEquals(44956585, perft(b,6));
    }

    
    public void testPerft058()
    {
        Board b = new Board("R6r/8/8/2K5/5k2/8/8/r6R b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(36, perft(b,1));
        assertEquals(1027, perft(b,2));
        assertEquals(29227, perft(b,3));
        assertEquals(771368, perft(b,4));
        assertEquals(20521342, perft(b,5));
        assertEquals(524966748, perft(b,6));
    }

    
    public void testPerft059()
    {
        Board b = new Board("6kq/8/8/8/8/8/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(2, perft(b,1));
        assertEquals(36, perft(b,2));
        assertEquals(143, perft(b,3));
        assertEquals(3637, perft(b,4));
        assertEquals(14893, perft(b,5));
        assertEquals(391507, perft(b,6));
    }

    
    public void testPerft060()
    {
        Board b = new Board("6KQ/8/8/8/8/8/8/7k b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(2, perft(b,1));
        assertEquals(36, perft(b,2));
        assertEquals(143, perft(b,3));
        assertEquals(3637, perft(b,4));
        assertEquals(14893, perft(b,5));
        assertEquals(391507, perft(b,6));
    }

    
    public void testPerft061()
    {
        Board b = new Board("K7/8/8/3Q4/4q3/8/8/7k w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(6, perft(b,1));
        assertEquals(35, perft(b,2));
        assertEquals(495, perft(b,3));
        assertEquals(8349, perft(b,4));
        assertEquals(166741, perft(b,5));
        assertEquals(3370175, perft(b,6));
    }

    
    public void testPerft062()
    {
        Board b = new Board("6qk/8/8/8/8/8/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(22, perft(b,1));
        assertEquals(43, perft(b,2));
        assertEquals(1015, perft(b,3));
        assertEquals(4167, perft(b,4));
        assertEquals(105749, perft(b,5));
        assertEquals(419369, perft(b,6));
    }

    
    public void testPerft063()
    {
        Board b = new Board("6KQ/8/8/8/8/8/8/7k b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(2, perft(b,1));
        assertEquals(36, perft(b,2));
        assertEquals(143, perft(b,3));
        assertEquals(3637, perft(b,4));
        assertEquals(14893, perft(b,5));
        assertEquals(391507, perft(b,6));
    }

    
    public void testPerft064()
    {
        Board b = new Board("K7/8/8/3Q4/4q3/8/8/7k b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(6, perft(b,1));
        assertEquals(35, perft(b,2));
        assertEquals(495, perft(b,3));
        assertEquals(8349, perft(b,4));
        assertEquals(166741, perft(b,5));
        assertEquals(3370175, perft(b,6));
    }

    
    public void testPerft065()
    {
        Board b = new Board("8/8/8/8/8/K7/P7/k7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(7, perft(b,2));
        assertEquals(43, perft(b,3));
        assertEquals(199, perft(b,4));
        assertEquals(1347, perft(b,5));
        assertEquals(6249, perft(b,6));
    }

    
    public void testPerft066()
    {
        Board b = new Board("8/8/8/8/8/7K/7P/7k w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(7, perft(b,2));
        assertEquals(43, perft(b,3));
        assertEquals(199, perft(b,4));
        assertEquals(1347, perft(b,5));
        assertEquals(6249, perft(b,6));
    }

    
    public void testPerft067()
    {
        Board b = new Board("K7/p7/k7/8/8/8/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(1, perft(b,1));
        assertEquals(3, perft(b,2));
        assertEquals(12, perft(b,3));
        assertEquals(80, perft(b,4));
        assertEquals(342, perft(b,5));
        assertEquals(2343, perft(b,6));
    }

    
    public void testPerft068()
    {
        Board b = new Board("7K/7p/7k/8/8/8/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(1, perft(b,1));
        assertEquals(3, perft(b,2));
        assertEquals(12, perft(b,3));
        assertEquals(80, perft(b,4));
        assertEquals(342, perft(b,5));
        assertEquals(2343, perft(b,6));
    }

    
    public void testPerft069()
    {
        Board b = new Board("8/2k1p3/3pP3/3P2K1/8/8/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(7, perft(b,1));
        assertEquals(35, perft(b,2));
        assertEquals(210, perft(b,3));
        assertEquals(1091, perft(b,4));
        assertEquals(7028, perft(b,5));
        assertEquals(34834, perft(b,6));
    }

    
    public void testPerft070()
    {
        Board b = new Board("8/8/8/8/8/K7/P7/k7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(1, perft(b,1));
        assertEquals(3, perft(b,2));
        assertEquals(12, perft(b,3));
        assertEquals(80, perft(b,4));
        assertEquals(342, perft(b,5));
        assertEquals(2343, perft(b,6));
    }

    
    public void testPerft071()
    {
        Board b = new Board("8/8/8/8/8/7K/7P/7k b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(1, perft(b,1));
        assertEquals(3, perft(b,2));
        assertEquals(12, perft(b,3));
        assertEquals(80, perft(b,4));
        assertEquals(342, perft(b,5));
        assertEquals(2343, perft(b,6));
    }

    
    public void testPerft072()
    {
        Board b = new Board("K7/p7/k7/8/8/8/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(7, perft(b,2));
        assertEquals(43, perft(b,3));
        assertEquals(199, perft(b,4));
        assertEquals(1347, perft(b,5));
        assertEquals(6249, perft(b,6));
    }

    
    public void testPerft073()
    {
        Board b = new Board("7K/7p/7k/8/8/8/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(7, perft(b,2));
        assertEquals(43, perft(b,3));
        assertEquals(199, perft(b,4));
        assertEquals(1347, perft(b,5));
        assertEquals(6249, perft(b,6));
    }

    
    public void testPerft074()
    {
        Board b = new Board("8/2k1p3/3pP3/3P2K1/8/8/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(35, perft(b,2));
        assertEquals(182, perft(b,3));
        assertEquals(1091, perft(b,4));
        assertEquals(5408, perft(b,5));
        assertEquals(34822, perft(b,6));
    }

    
    public void testPerft075()
    {
        Board b = new Board("8/8/8/8/8/4k3/4P3/4K3 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(2, perft(b,1));
        assertEquals(8, perft(b,2));
        assertEquals(44, perft(b,3));
        assertEquals(282, perft(b,4));
        assertEquals(1814, perft(b,5));
        assertEquals(11848, perft(b,6));
    }

    
    public void testPerft076()
    {
        Board b = new Board("4k3/4p3/4K3/8/8/8/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(2, perft(b,1));
        assertEquals(8, perft(b,2));
        assertEquals(44, perft(b,3));
        assertEquals(282, perft(b,4));
        assertEquals(1814, perft(b,5));
        assertEquals(11848, perft(b,6));
    }

    
    public void testPerft077()
    {
        Board b = new Board("8/8/7k/7p/7P/7K/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(9, perft(b,2));
        assertEquals(57, perft(b,3));
        assertEquals(360, perft(b,4));
        assertEquals(1969, perft(b,5));
        assertEquals(10724, perft(b,6));
    }

    
    public void testPerft078()
    {
        Board b = new Board("8/8/k7/p7/P7/K7/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(9, perft(b,2));
        assertEquals(57, perft(b,3));
        assertEquals(360, perft(b,4));
        assertEquals(1969, perft(b,5));
        assertEquals(10724, perft(b,6));
    }

    
    public void testPerft079()
    {
        Board b = new Board("8/8/3k4/3p4/3P4/3K4/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(25, perft(b,2));
        assertEquals(180, perft(b,3));
        assertEquals(1294, perft(b,4));
        assertEquals(8296, perft(b,5));
        assertEquals(53138, perft(b,6));
    }

    
    public void testPerft080()
    {
        Board b = new Board("8/3k4/3p4/8/3P4/3K4/8/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(8, perft(b,1));
        assertEquals(61, perft(b,2));
        assertEquals(483, perft(b,3));
        assertEquals(3213, perft(b,4));
        assertEquals(23599, perft(b,5));
        assertEquals(157093, perft(b,6));
    }

    
    public void testPerft081()
    {
        Board b = new Board("8/8/3k4/3p4/8/3P4/3K4/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(8, perft(b,1));
        assertEquals(61, perft(b,2));
        assertEquals(411, perft(b,3));
        assertEquals(3213, perft(b,4));
        assertEquals(21637, perft(b,5));
        assertEquals(158065, perft(b,6));
    }

    
    public void testPerft082()
    {
        Board b = new Board("k7/8/3p4/8/3P4/8/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(15, perft(b,2));
        assertEquals(90, perft(b,3));
        assertEquals(534, perft(b,4));
        assertEquals(3450, perft(b,5));
        assertEquals(20960, perft(b,6));
    }

    
    public void testPerft083()
    {
        Board b = new Board("8/8/7k/7p/7P/7K/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(9, perft(b,2));
        assertEquals(57, perft(b,3));
        assertEquals(360, perft(b,4));
        assertEquals(1969, perft(b,5));
        assertEquals(10724, perft(b,6));
    }

    
    public void testPerft084()
    {
        Board b = new Board("8/8/k7/p7/P7/K7/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(9, perft(b,2));
        assertEquals(57, perft(b,3));
        assertEquals(360, perft(b,4));
        assertEquals(1969, perft(b,5));
        assertEquals(10724, perft(b,6));
    }

    
    public void testPerft085()
    {
        Board b = new Board("8/8/3k4/3p4/3P4/3K4/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(25, perft(b,2));
        assertEquals(180, perft(b,3));
        assertEquals(1294, perft(b,4));
        assertEquals(8296, perft(b,5));
        assertEquals(53138, perft(b,6));
    }

    
    public void testPerft086()
    {
        Board b = new Board("8/3k4/3p4/8/3P4/3K4/8/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(8, perft(b,1));
        assertEquals(61, perft(b,2));
        assertEquals(411, perft(b,3));
        assertEquals(3213, perft(b,4));
        assertEquals(21637, perft(b,5));
        assertEquals(158065, perft(b,6));
    }

    
    public void testPerft087()
    {
        Board b = new Board("8/8/3k4/3p4/8/3P4/3K4/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(8, perft(b,1));
        assertEquals(61, perft(b,2));
        assertEquals(483, perft(b,3));
        assertEquals(3213, perft(b,4));
        assertEquals(23599, perft(b,5));
        assertEquals(157093, perft(b,6));
    }

    
    public void testPerft088()
    {
        Board b = new Board("k7/8/3p4/8/3P4/8/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(15, perft(b,2));
        assertEquals(89, perft(b,3));
        assertEquals(537, perft(b,4));
        assertEquals(3309, perft(b,5));
        assertEquals(21104, perft(b,6));
    }

    
    public void testPerft089()
    {
        Board b = new Board("7k/3p4/8/8/3P4/8/8/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(19, perft(b,2));
        assertEquals(117, perft(b,3));
        assertEquals(720, perft(b,4));
        assertEquals(4661, perft(b,5));
        assertEquals(32191, perft(b,6));
    }

    
    public void testPerft090()
    {
        Board b = new Board("7k/8/8/3p4/8/8/3P4/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(19, perft(b,2));
        assertEquals(116, perft(b,3));
        assertEquals(716, perft(b,4));
        assertEquals(4786, perft(b,5));
        assertEquals(30980, perft(b,6));
    }

    
    public void testPerft091()
    {
        Board b = new Board("k7/8/8/7p/6P1/8/8/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft092()
    {
        Board b = new Board("k7/8/7p/8/8/6P1/8/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft093()
    {
        Board b = new Board("k7/8/8/6p1/7P/8/8/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft094()
    {
        Board b = new Board("k7/8/6p1/8/8/7P/8/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft095()
    {
        Board b = new Board("k7/8/8/3p4/4p3/8/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(3, perft(b,1));
        assertEquals(15, perft(b,2));
        assertEquals(84, perft(b,3));
        assertEquals(573, perft(b,4));
        assertEquals(3013, perft(b,5));
        assertEquals(22886, perft(b,6));
    }

    
    public void testPerft096()
    {
        Board b = new Board("k7/8/3p4/8/8/4P3/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4271, perft(b,5));
        assertEquals(28662, perft(b,6));
    }

    
    public void testPerft097()
    {
        Board b = new Board("7k/3p4/8/8/3P4/8/8/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(19, perft(b,2));
        assertEquals(117, perft(b,3));
        assertEquals(720, perft(b,4));
        assertEquals(5014, perft(b,5));
        assertEquals(32167, perft(b,6));
    }

    
    public void testPerft098()
    {
        Board b = new Board("7k/8/8/3p4/8/8/3P4/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(19, perft(b,2));
        assertEquals(117, perft(b,3));
        assertEquals(712, perft(b,4));
        assertEquals(4658, perft(b,5));
        assertEquals(30749, perft(b,6));
    }

    
    public void testPerft099()
    {
        Board b = new Board("k7/8/8/7p/6P1/8/8/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft0100()
    {
        Board b = new Board("k7/8/7p/8/8/6P1/8/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft0101()
    {
        Board b = new Board("k7/8/8/6p1/7P/8/8/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft102()
    {
        Board b = new Board("k7/8/6p1/8/8/7P/8/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft103()
    {
        Board b = new Board("k7/8/8/3p4/4p3/8/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(15, perft(b,2));
        assertEquals(102, perft(b,3));
        assertEquals(569, perft(b,4));
        assertEquals(4337, perft(b,5));
        assertEquals(22579, perft(b,6));
    }

    
    public void testPerft104()
    {
        Board b = new Board("k7/8/3p4/8/8/4P3/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4271, perft(b,5));
        assertEquals(28662, perft(b,6));
    }

    
    public void testPerft105()
    {
        Board b = new Board("7k/8/8/p7/1P6/8/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft106()
    {
        Board b = new Board("7k/8/p7/8/8/1P6/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft107()
    {
        Board b = new Board("7k/8/8/1p6/P7/8/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft108()
    {
        Board b = new Board("7k/8/1p6/8/8/P7/8/7K w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft109()
    {
        Board b = new Board("k7/7p/8/8/8/8/6P1/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(25, perft(b,2));
        assertEquals(161, perft(b,3));
        assertEquals(1035, perft(b,4));
        assertEquals(7574, perft(b,5));
        assertEquals(55338, perft(b,6));
    }

    
    public void testPerft110()
    {
        Board b = new Board("k7/6p1/8/8/8/8/7P/K7 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(25, perft(b,2));
        assertEquals(161, perft(b,3));
        assertEquals(1035, perft(b,4));
        assertEquals(7574, perft(b,5));
        assertEquals(55338, perft(b,6));
    }

    
    public void testPerft111()
    {
        Board b = new Board("3k4/3pp3/8/8/8/8/3PP3/3K4 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(7, perft(b,1));
        assertEquals(49, perft(b,2));
        assertEquals(378, perft(b,3));
        assertEquals(2902, perft(b,4));
        assertEquals(24122, perft(b,5));
        assertEquals(199002, perft(b,6));
    }

    
    public void testPerft112()
    {
        Board b = new Board("7k/8/8/p7/1P6/8/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft113()
    {
        Board b = new Board("7k/8/p7/8/8/1P6/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft114()
    {
        Board b = new Board("7k/8/8/1p6/P7/8/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(22, perft(b,2));
        assertEquals(139, perft(b,3));
        assertEquals(877, perft(b,4));
        assertEquals(6112, perft(b,5));
        assertEquals(41874, perft(b,6));
    }

    
    public void testPerft115()
    {
        Board b = new Board("7k/8/1p6/8/8/P7/8/7K b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(4, perft(b,1));
        assertEquals(16, perft(b,2));
        assertEquals(101, perft(b,3));
        assertEquals(637, perft(b,4));
        assertEquals(4354, perft(b,5));
        assertEquals(29679, perft(b,6));
    }

    
    public void testPerft116()
    {
        Board b = new Board("k7/7p/8/8/8/8/6P1/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(25, perft(b,2));
        assertEquals(161, perft(b,3));
        assertEquals(1035, perft(b,4));
        assertEquals(7574, perft(b,5));
        assertEquals(55338, perft(b,6));
    }

    
    public void testPerft117()
    {
        Board b = new Board("k7/6p1/8/8/8/8/7P/K7 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(5, perft(b,1));
        assertEquals(25, perft(b,2));
        assertEquals(161, perft(b,3));
        assertEquals(1035, perft(b,4));
        assertEquals(7574, perft(b,5));
        assertEquals(55338, perft(b,6));
    }

    
    public void testPerft118()
    {
        Board b = new Board("3k4/3pp3/8/8/8/8/3PP3/3K4 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(7, perft(b,1));
        assertEquals(49, perft(b,2));
        assertEquals(378, perft(b,3));
        assertEquals(2902, perft(b,4));
        assertEquals(24122, perft(b,5));
        assertEquals(199002, perft(b,6));
    }

    
    public void testPerft119()
    {
        Board b = new Board("8/Pk6/8/8/8/8/6Kp/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(11, perft(b,1));
        assertEquals(97, perft(b,2));
        assertEquals(887, perft(b,3));
        assertEquals(8048, perft(b,4));
        assertEquals(90606, perft(b,5));
        assertEquals(1030499, perft(b,6));
    }

    
    public void testPerft120()
    {
        Board b = new Board("n1n5/1Pk5/8/8/8/8/5Kp1/5N1N w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(24, perft(b,1));
        assertEquals(421, perft(b,2));
        assertEquals(7421, perft(b,3));
        assertEquals(124608, perft(b,4));
        assertEquals(2193768, perft(b,5));
        assertEquals(37665329, perft(b,6));
    }

    
    public void testPerft121()
    {
        Board b = new Board("8/PPPk4/8/8/8/8/4Kppp/8 w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(18, perft(b,1));
        assertEquals(270, perft(b,2));
        assertEquals(4699, perft(b,3));
        assertEquals(79355, perft(b,4));
        assertEquals(1533145, perft(b,5));
        assertEquals(28859283, perft(b,6));
    }

    
    public void testPerft122()
    {
        Board b = new Board("n1n5/PPPk4/8/8/8/8/4Kppp/5N1N w - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(24, perft(b,1));
        assertEquals(496, perft(b,2));
        assertEquals(9483, perft(b,3));
        assertEquals(182838, perft(b,4));
        assertEquals(3605103, perft(b,5));
        assertEquals(71179139, perft(b,6));
    }

    
    public void testPerft123()
    {
        Board b = new Board("8/Pk6/8/8/8/8/6Kp/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(11, perft(b,1));
        assertEquals(97, perft(b,2));
        assertEquals(887, perft(b,3));
        assertEquals(8048, perft(b,4));
        assertEquals(90606, perft(b,5));
        assertEquals(1030499, perft(b,6));
    }

    
    public void testPerft124()
    {
        Board b = new Board("n1n5/1Pk5/8/8/8/8/5Kp1/5N1N b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(24, perft(b,1));
        assertEquals(421, perft(b,2));
        assertEquals(7421, perft(b,3));
        assertEquals(124608, perft(b,4));
        assertEquals(2193768, perft(b,5));
        assertEquals(37665329, perft(b,6));
    }

    
    public void testPerft125()
    {
        Board b = new Board("8/PPPk4/8/8/8/8/4Kppp/8 b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(18, perft(b,1));
        assertEquals(270, perft(b,2));
        assertEquals(4699, perft(b,3));
        assertEquals(79355, perft(b,4));
        assertEquals(1533145, perft(b,5));
        assertEquals(28859283, perft(b,6));
    }

    
    public void testPerft126()
    {
        Board b = new Board("n1n5/PPPk4/8/8/8/8/4Kppp/5N1N b - - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(24, perft(b,1));
        assertEquals(496, perft(b,2));
        assertEquals(9483, perft(b,3));
        assertEquals(182838, perft(b,4));
        assertEquals(3605103, perft(b,5));
        assertEquals(71179139, perft(b,6));
    }

    
    public void testPerft127()
    {
        Board b = new Board("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1 ");
        log.info("fen: {}",b.getFEN());
        assertEquals(48, perft(b,1));
        assertEquals(2039, perft(b,2));
        assertEquals(97862, perft(b,3));
        assertEquals(4085603, perft(b,4));
        assertEquals(193690690,perft(b,5) );
    }

    
    public void testPerft128()
    {
        Board b = new Board("rnbQkbnr/3ppppp/p1p5/8/8/2P5/PP1PPPPP/RNB1KBNR b KQkq - 0 ");
        log.info("fen: {}",b.getFEN());
        assertEquals(67820026 , perft(b,7));
    }

    
    public void testPerft129()
    {
        Board b = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        log.info("fen: {}",b.getFEN());
        assertEquals(119060324 , perft(b,6));
    }
}
