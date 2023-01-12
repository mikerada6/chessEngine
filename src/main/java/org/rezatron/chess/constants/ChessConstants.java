package org.rezatron.chess.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessConstants {

    public static final short[] knightPlacement = {-50, -40, -30, -30, -30,
            -30, -40, -50, -40, -20, 0, 0, 0, 0, -20, -40, -30, 0, 10, 15, 15,
            10, 0, -30, -30, 5, 15, 20, 20, 15, 5, -30, -30, 0, 15, 20, 20, 15,
            0, -30, -30, 5, 10, 15, 15, 10, 5, -30, -40, -20, 0, 5, 5, 0, -20,
            -40, -50, -40, -30, -30, -30, -30, -40, -50,};
    public static final short[] blackKingPlacement = {-30, -40, -40, -50,
            -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30, -30,
            -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40,
            -40, -30, -20, -30, -30, -40, -40, -30, -30, -20, -10, -20, -20,
            -20, -20, -20, -20, -10, 20, 20, 0, 0, 0, 0, 20, 20, 20, 30, 10, 0,
            0, 10, 30, 20};
    public static final short[] whitePawnPlacement = {0, 0, 0, 0, 0, 0, 0, 0,
            5, 10, 10, -20, -20, 10, 10, 5, 5, -5, -10, 0, 0, -10, -5, 5, 0, 0,
            0, 20, 20, 0, 0, 0, 5, 5, 10, 25, 25, 10, 5, 5, 10, 10, 20, 30, 30,
            20, 10, 10, 50, 50, 50, 50, 50, 50, 50, 50, 0, 0, 0, 0, 0, 0, 0, 0,};
    public static final short[] blackPawnPlacement = {0, 0, 0, 0, 0, 0, 0, 0,
            50, 50, 50, 50, 50, 50, 50, 50, 10, 10, 20, 30, 30, 20, 10, 10, 5,
            5, 10, 25, 25, 10, 5, 5, 0, 0, 0, 20, 20, 0, 0, 0, 5, -5, -10, 0,
            0, -10, -5, 5, 5, 10, 10, -20, -20, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0,
            0};
    public static final short[] bishopPlacement = {-20, -10, -10, -10, -10,
            -10, -10, -20, -10, 0, 0, 0, 0, 0, 0, -10, -10, 0, 5, 10, 10, 5, 0,
            -10, -10, 5, 5, 10, 10, 5, 5, -10, -10, 0, 10, 10, 10, 10, 0, -10,
            -10, 10, 10, 10, 10, 10, 10, -10, -10, 5, 0, 0, 0, 0, 5, -10, -20,
            -10, -10, -10, -10, -10, -10, -20,};
    public static final short[] queenPlacement = {-20, -10, -10, -5, -5, -10,
            -10, -20, -10, 0, 0, 0, 0, 0, 0, -10, -10, 0, 5, 5, 5, 5, 0, -10,
            -5, 0, 5, 5, 5, 5, 0, -5, 0, 0, 5, 5, 5, 5, 0, -5, -10, 5, 5, 5, 5,
            5, 0, -10, -10, 0, 5, 0, 0, 0, 0, -10, -20, -10, -10, -5, -5, -10,
            -10, -20};
    public static final short[] rookPlacement = {0, 0, 0, 0, 0, 0, 0, 0, 5,
            10, 10, 10, 10, 10, 10, 5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0,
            0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5,
            -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, 0, 5, 5, 0, 0, 0};
    public static final long[] diagonalMask = {128L, 32832L, 8405024L,
            2151686160L, 550831656968L, 141012904183812L, 36099303471055874L,
            -9205322385119247871L, 4620710844295151872L, 2310355422147575808L,
            1155177711073755136L, 577588855528488960L, 288794425616760832L,
            144396663052566528L, 72057594037927936L};
    // rank + file
    public static final long[] antiDiagonalMask = {1L, 258L, 66052L,
            16909320L, 4328785936L, 1108169199648L, 283691315109952L,
            72624976668147840L, 145249953336295424L, 290499906672525312L,
            580999813328273408L, 1161999622361579520L, 2323998145211531264L,
            4647714815446351872L, -9223372036854775808L};
    public static final long[] rankMask = {255L, 65280L, 16711680L,
            4278190080L, 1095216660480L, 280375465082880L, 71776119061217280L,
            -72057594037927936L};
    public static final long[] fileMask = {72340172838076673L,
            144680345676153346L, 289360691352306692L, 578721382704613384L,
            1157442765409226768L, 2314885530818453536L, 4629771061636907072L,
            -9187201950435737472L};
    public static final long[] squares = {1L, 2L, 4L, 8L, 16L, 32L, 64L,
            128L, 256L, 512L, 1024L, 2048L, 4096L, 8192L, 16384L, 32768L,
            65536L, 131072L, 262144L, 524288L, 1048576L, 2097152L, 4194304L,
            8388608L, 16777216L, 33554432L, 67108864L, 134217728L, 268435456L,
            536870912L, 1073741824L, 2147483648L, 4294967296L, 8589934592L,
            17179869184L, 34359738368L, 68719476736L, 137438953472L,
            274877906944L, 549755813888L, 1099511627776L, 2199023255552L,
            4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L,
            70368744177664L, 140737488355328L, 281474976710656L,
            562949953421312L, 1125899906842624L, 2251799813685248L,
            4503599627370496L, 9007199254740992L, 18014398509481984L,
            36028797018963968L, 72057594037927936L, 144115188075855872L,
            288230376151711744L, 576460752303423488L, 1152921504606846976L,
            2305843009213693952L, 4611686018427387904L, -9223372036854775808L};
    static public final long deBruijn = 0x03f79d71b4cb0a89L;
    static public final int[] magicTable = {
            0, 1, 48, 2, 57, 49, 28, 3,
            61, 58, 50, 42, 38, 29, 17, 4,
            62, 55, 59, 36, 53, 51, 43, 22,
            45, 39, 33, 30, 24, 18, 12, 5,
            63, 47, 56, 27, 60, 41, 37, 16,
            54, 35, 52, 21, 44, 32, 23, 11,
            46, 26, 40, 15, 34, 20, 31, 10,
            25, 14, 19, 9, 13, 8, 7, 6,
    };

    public static final int[] index64 = {
            63, 0, 58, 1, 59, 47, 53, 2,
            60, 39, 48, 27, 54, 33, 42, 3,
            61, 51, 37, 40, 49, 18, 28, 20,
            55, 30, 34, 11, 43, 14, 22, 4,
            62, 57, 46, 52, 38, 26, 32, 41,
            50, 36, 17, 19, 29, 10, 13, 21,
            56, 45, 25, 31, 35, 16, 9, 12,
            44, 24, 15, 8, 23, 7, 6, 5
    };
    public static final int[] order = {56, 57, 58, 59, 60, 61, 62, 63, 48,
            49, 50, 51, 52, 53, 54, 55, 40, 41, 42, 43, 44, 45, 46, 47, 32, 33,
            34, 35, 36, 37, 38, 39, 24, 25, 26, 27, 28, 29, 30, 31, 16, 17, 18,
            19, 20, 21, 22, 23, 8, 9, 10, 11, 12, 13, 14, 15, 0, 1, 2, 3, 4, 5,
            6, 7};
    public static final String[] letterSquares = {"a1", "b1", "c1", "d1",
            "e1", "f1", "g1", "h1", "a2", "b2", "c2", "d2", "e2", "f2", "g2",
            "h2", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3", "a4", "b4",
            "c4", "d4", "e4", "f4", "g4", "h4", "a5", "b5", "c5", "d5", "e5",
            "f5", "g5", "h5", "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
            "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7", "a8", "b8", "c8",
            "d8", "e8", "f8", "g8", "h8"};
    public static final long KING_SPAN = 460039L;
    public static final long FILE_A = 72340172838076673L;
    public static final long FILE_H = -9187201950435737472L;
    public static final long FILE_AB = 217020518514230019L;
    public static final long FILE_GH = -4557430888798830400L;
    public static final long RANK_1 = -72057594037927936L;
    public static final long RANK_4 = 1095216660480L;
    public static final long RANK_5 = 4278190080L;
    public static final long RANK_8 = 255L;
    public static final long CENTRE = 103481868288L;
    public static final long EXTENDED_CENTRE = 66229406269440L;
    public static final long KNIGHT_SPAN = 43234889994L;
    public static final int whitePawnBitBoard = 0;
    public static final int whiteRookBitBoard = 1;
    public static final int whiteKnightBitBoard = 2;
    public static final int whiteBishopBitBoard = 3;
    public static final int whiteQueenBitBoard = 4;
    public static final int whiteKingBitBoard = 5;
    public static final int blackPawnBitBoard = 6;
    public static final int blackRookBitBoard = 7;
    public static final int blackKnightBitBoard = 8;
    public static final int blackBishopBitBoard = 9;
    public static final int blackQueenBitBoard = 10;
    public static final int blackKingBitBoard = 11;
    public static final int whiteBitBoard = 12;
    public static final int blackBitBoard = 13;
    public static final int occupiedBitBoard = 14;
    public static final int emptyBitBoard = 15;
    public static Map<String, Long> letterSquaresHashMap = createMap();

    private static Map<String, Long> createMap() {
        Map<String, Long> result = new HashMap<>();

        result.put("a1", 1L);
        result.put("b1", 2L);
        result.put("c1", 4L);
        result.put("d1", 8L);
        result.put("e1", 16L);
        result.put("f1", 32L);
        result.put("g1", 64L);
        result.put("h1", 128L);
        result.put("a2", 256L);
        result.put("b2", 512L);
        result.put("c2", 1024L);
        result.put("d2", 2048L);
        result.put("e2", 4096L);
        result.put("f2", 8192L);
        result.put("g2", 16384L);
        result.put("h2", 32768L);
        result.put("a3", 65536L);
        result.put("b3", 131072L);
        result.put("c3", 262144L);
        result.put("d3", 524288L);
        result.put("e3", 1048576L);
        result.put("f3", 2097152L);
        result.put("g3", 4194304L);
        result.put("h3", 8388608L);
        result.put("a4", 16777216L);
        result.put("b4", 33554432L);
        result.put("c4", 67108864L);
        result.put("d4", 134217728L);
        result.put("e4", 268435456L);
        result.put("f4", 536870912L);
        result.put("g4", 1073741824L);
        result.put("h4", 2147483648L);
        result.put("a5", 4294967296L);
        result.put("b5", 8589934592L);
        result.put("c5", 17179869184L);
        result.put("d5", 34359738368L);
        result.put("e5", 68719476736L);
        result.put("f5", 137438953472L);
        result.put("g5", 274877906944L);
        result.put("h5", 549755813888L);
        result.put("a6", 1099511627776L);
        result.put("b6", 2199023255552L);
        result.put("c6", 4398046511104L);
        result.put("d6", 8796093022208L);
        result.put("e6", 17592186044416L);
        result.put("f6", 35184372088832L);
        result.put("g6", 70368744177664L);
        result.put("h6", 140737488355328L);
        result.put("a7", 281474976710656L);
        result.put("b7", 562949953421312L);
        result.put("c7", 1125899906842624L);
        result.put("d7", 2251799813685248L);
        result.put("e7", 4503599627370496L);
        result.put("f7", 9007199254740992L);
        result.put("g7", 18014398509481984L);
        result.put("h7", 36028797018963968L);
        result.put("a8", 72057594037927936L);
        result.put("b8", 144115188075855872L);
        result.put("c8", 288230376151711744L);
        result.put("d8", 576460752303423488L);
        result.put("e8", 1152921504606846976L);
        result.put("f8", 2305843009213693952L);
        result.put("g8", 4611686018427387904L);
        result.put("h8", -9223372036854775808L);
        return Collections.unmodifiableMap(result);
    }
}
