package org.rezatron.chess;

import com.google.common.base.Stopwatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.rezatron.chess.constants.ChessPiece;
import org.rezatron.chess.constants.MoveFlags;

import java.util.ArrayList;


import static org.rezatron.chess.constants.ChessConstants.*;
import static org.rezatron.chess.constants.MoveFlags.*;

public class MoveGenerator {

    private static final Logger log = LogManager.getLogger(MoveGenerator.class);

    public static ArrayList<Move> getWhiteMoves(Board b) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getWhitePawnMoves(b));
        moves.addAll(getWhiteNonPawnMovement(b));
        return moves;
    }

    public static ArrayList<Move> getWhitePawnMoves(Board b) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //TODO deal with en Passant
        ArrayList<Move> moves = new ArrayList<>();

        long wp = b.getWhitePawnBitBoard();
        long them = b.getBlackBitBoard();
        long empty = b.getEmptyBitBoard();

        long whitePawnPushOne = (wp << 8) & empty;
        long whitePawnPushTwo = (((((wp << 8) & empty) << 8) & rankMask[3])) & empty;
        long whitePawnAttackLeft = ((wp << 7) & ~FILE_H) & them;
        long whitePawnAttackRight = ((wp << 9) & ~FILE_A) & them;

        long moveOne = whitePawnPushOne & ~rankMask[7];
        long promoteMove = whitePawnPushOne & rankMask[7];
        long whitePawnAttackLeftNormal = whitePawnAttackLeft & ~rankMask[7];
        long whitePawnAttackRightNormal = whitePawnAttackRight & ~rankMask[7];
        long whitePawnAttackLeftPromote = whitePawnAttackLeft & rankMask[7];
        long whitePawnAttackRightPromote = whitePawnAttackRight & rankMask[7];


        for (long temp = moveOne; temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 8, to, QUITE_MOVE_FLAG);
            log.trace("Making a white pawn push one from {} to {} - {}", to - 8, to, move);
            moves.add(move);
        }

        for (long temp = whitePawnPushTwo; temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 16, to, MoveFlags.DOUBLE_PAWN_PUSH_FLAG);
            log.trace("Making a white pawn push two from {} to {} - {}", to - 16, to, move);
            moves.add(move);
        }

        for (long temp = whitePawnAttackLeftNormal; temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 7, to, MoveFlags.CAPTURE_FLAG);
            log.trace("Making an attack left white pawn push from {} to {} - {}", to - 7, to, move);
            moves.add(move);
        }
        for (long temp = whitePawnAttackRightNormal; temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 9, to, MoveFlags.CAPTURE_FLAG);
            log.trace("Making an attack right  white pawn push from {} to {} - {}", to - 9, to, move);
            moves.add(move);
        }

        for (long temp = promoteMove; temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to - 8;
            Move moveRook = new Move(from, to, MoveFlags.ROOK_PROMOTION_FLAG);
            Move moveKnight = new Move(from, to, MoveFlags.KNIGHT_PROMOTION_FLAG);
            Move moveBishop = new Move(from, to, MoveFlags.BISHOP_PROMOTION_FLAG);
            Move moveQueen = new Move(from, to, MoveFlags.QUEEN_PROMOTION_FLAG);
            log.trace("Making an promotion move  from {} to {} - {}", to - 8, to, moveRook);
            log.trace("Making an promotion move  from {} to {} - {}", to - 8, to, moveKnight);
            log.trace("Making an promotion move  from {} to {} - {}", to - 8, to, moveBishop);
            log.trace("Making an promotion move  from {} to {} - {}", to - 8, to, moveQueen);
            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = whitePawnAttackLeftPromote; temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to - 7;
            Move moveRook = new Move(from, to, MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG);
            Move moveKnight = new Move(from, to, MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG);
            Move moveBishop = new Move(from, to, MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG);
            Move moveQueen = new Move(from, to, MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG);
            log.trace("Making an promotion attack left white pawn push from {} to {} - {}", to - 9, to, moveRook);
            log.trace("Making an promotion attack left white pawn push from {} to {} - {}", to - 9, to, moveKnight);
            log.trace("Making an promotion attack left white pawn push from {} to {} - {}", to - 9, to, moveBishop);
            log.trace("Making an promotion attack left white pawn push from {} to {} - {}", to - 9, to, moveQueen);

            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = whitePawnAttackRightPromote; temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to - 9;
            Move moveRook = new Move(from, to, MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG);
            Move moveKnight = new Move(from, to, MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG);
            Move moveBishop = new Move(from, to, MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG);
            Move moveQueen = new Move(from, to, MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG);
            log.trace("Making an promotion attack right white pawn push from {} to {} - {}", to - 9, to, moveRook);
            log.trace("Making an promotion attack right white pawn push from {} to {} - {}", to - 9, to, moveKnight);
            log.trace("Making an promotion attack right white pawn push from {} to {} - {}", to - 9, to, moveBishop);
            log.trace("Making an promotion attack right white pawn push from {} to {} - {}", to - 9, to, moveQueen);
            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }
        stopwatch.stop(); // optional

        log.debug("white pawn movement took : {}", stopwatch);

        return moves;
    }

    private static ArrayList<Move> getWhiteNonPawnMovement(Board b) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ArrayList<Move> moves = new ArrayList<>();
        for (long temp = (b.getWhiteRookBitBoard() | b.getWhiteBishopBitBoard() | b.getWhiteKnightBitBoard() | b.getWhiteQueenBitBoard()); temp != 0; temp -= 1L << Long
                .numberOfTrailingZeros(temp)) {
            switch (b.pieceAtSquare(Long.numberOfTrailingZeros(temp))) {
                case WHITE_ROOK -> {
                    moves.addAll(getRookMovement(b, (Long
                            .numberOfTrailingZeros(temp))));
                    log.trace("After ROOK: {}", moves);
                }
                case WHITE_KNIGHT -> {
                    moves.addAll(getKnightMovement(b, (Long
                            .numberOfTrailingZeros(temp))));
                    log.trace("After KNIGHT: {}", moves);
                }
                case WHITE_BISHOP -> {
                    moves.addAll(getBishopMovement(b, (Long
                            .numberOfTrailingZeros(temp))));
                    log.trace("After BISHOP: {}", moves);
                }
                case WHITE_QUEEN -> {
                    moves.addAll(getQueenMovement(b, (Long
                            .numberOfTrailingZeros(temp))));
                    log.trace("After QUEEN: {}", moves);
                }
            }
        }
        moves.addAll(getKingMovement(b, Long.numberOfTrailingZeros(b.getWhiteKingBitBoard())));

        //TODO handle that you can not castle through check
        if(b.canWhiteKingSideCastle() && b.isSquareEmpty(5) && b.isSquareEmpty(6))
        {
            moves.add(new Move(4,2,KING_CASTLE_FLAG));
        }
        if(b.canWhiteQueenSideCastle() && b.isSquareEmpty(1) && b.isSquareEmpty(2) && b.isSquareEmpty(3))
        {
            moves.add(new Move(4,7,QUEEN_CASTLE_FLAG));
        }

        stopwatch.stop(); // optional

        log.debug("non pawn movement took : {}", stopwatch);
        return moves;
    }

    private static ArrayList<Move> getRookMovement(Board b, int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ArrayList<Move> moves = new ArrayList<>();
        long binaryS = 1L << square;
        long r = Long.reverse(binaryS);
        long twoR = 2 * r;
        long occupied = b.getOccupiedBitBoard();
        long fMask = fileMask[square % 8];

        long one = ((occupied & fMask) - (2 * binaryS))
                ^ Long.reverse(Long.reverse(occupied & fMask) - (twoR));

        long two = (occupied - 2 * binaryS)
                ^ Long.reverse(Long.reverse(occupied) - twoR);
        long movesBitBoard  = (one & fileMask[square % 8]) + (two & rankMask[square / 8]);

        if ((((b.getWhiteRookBitBoard() >> square) & 1) == 1))
            movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
        else
            movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());
        for (int i = 0; i < 64; i++) {

            long temp = movesBitBoard & (1L << i);
            if (temp != 0) {
                Move move;
                if (b.pieceAtSquare(i).equals(ChessPiece.EMPTY)) {
                    move = new Move(square, i, QUITE_MOVE_FLAG);
                } else {
                    move = new Move(square, i, CAPTURE_FLAG);
                }
                log.trace("adding a rook movement from {} to {} - {}", square, i, move);

                moves.add(move);
            }
        }
        stopwatch.stop(); // optional

        log.debug("rook movement took : {}", stopwatch);
        return moves;
    }

    private static ArrayList<Move> getKnightMovement(Board b, int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ArrayList<Move> moves = new ArrayList<>();
        long movesBitBoard;

        if (square > 18) {
            movesBitBoard = KNIGHT_SPAN << (square - 18);
        } else {
            movesBitBoard = KNIGHT_SPAN >> (18 - square);
        }
        if (square % 8 < 4) {
            movesBitBoard &= ~FILE_GH;
        } else {
            movesBitBoard &= ~FILE_AB;
        }
        if ((((b.getWhiteKnightBitBoard() >> square) & 1) == 1))
            movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
        else
            movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

        for (int i = 0; i < 64; i++) {

            long temp = movesBitBoard & (1L << i);
            if (temp != 0) {
                Move move;
                if (b.pieceAtSquare(i).equals(ChessPiece.EMPTY)) {
                    move = new Move(square, i, QUITE_MOVE_FLAG);
                } else {
                    move = new Move(square, i, CAPTURE_FLAG);
                }
                log.trace("adding a knight movement from {} to {} - {}", square, i, move);

                moves.add(move);
            }
        }
        stopwatch.stop(); // optional

        log.debug("knight movement took : {}", stopwatch);
        return moves;
    }

    private static ArrayList<Move> getBishopMovement(Board b, int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ArrayList<Move> moves = new ArrayList<>();
        long binaryS = 1L << square;
        long r = Long.reverse(binaryS);
        long twoR = 2 * r;
        long occupied = b.getOccupiedBitBoard();
        long dMask = diagonalMask[7 + square / 8 - square % 8];
        long adMask = antiDiagonalMask[(square / 8) + (square % 8)];
        long one = ((occupied & dMask) - (2 * binaryS))
                ^ Long.reverse(Long.reverse(occupied & dMask) - (twoR));
        long two = ((occupied & adMask) - (2 * binaryS))
                ^ Long.reverse(Long.reverse(occupied & adMask) - (twoR));
        long  movesBitBoard = (one & dMask) | (two & adMask);
        if ((((b.getWhiteBishopBitBoard() >> square) & 1) == 1))
            movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
        else
            movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

        for (int i = 0; i < 64; i++) {

            long temp = movesBitBoard & (1L << i);
            if (temp != 0) {
                Move move;
                if (b.pieceAtSquare(i).equals(ChessPiece.EMPTY)) {
                    move = new Move(square, i, QUITE_MOVE_FLAG);
                } else {
                    move = new Move(square, i, CAPTURE_FLAG);
                }
                log.trace("adding a bishop movement from {} to {} - {}", square, i, move);

                moves.add(move);
            }
        }
        stopwatch.stop(); // optional

        log.debug("bishop movement took : {}", stopwatch);
        return moves;
    }

    private static ArrayList<Move> getQueenMovement(Board b, int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ArrayList<Move> moves = new ArrayList<>();
        long binaryS = 1L << square;
        long r = Long.reverse(binaryS);
        long twoR = 2 * r;
        long occupied = b.getOccupiedBitBoard();
        long dMask = diagonalMask[7 + square / 8 - square % 8];
        long adMask = antiDiagonalMask[(square / 8) + (square % 8)];
        long fMask = fileMask[square % 8];
        long one = ((occupied & dMask) - (2 * binaryS))
                ^ Long.reverse(Long.reverse(occupied & dMask) - (twoR));
        long two = ((occupied & adMask) - (2 * binaryS))
                ^ Long.reverse(Long.reverse(occupied & adMask) - (twoR));
        long three = ((occupied & fMask) - (2 * binaryS))
                ^ Long.reverse(Long.reverse(occupied & fMask) - (twoR));
        long four = (occupied - 2 * binaryS)
                ^ Long.reverse(Long.reverse(occupied) - twoR);
        long movesBitBoard = ((four & rankMask[square / 8]) | (three & fMask))
                | (one & dMask) | (two & adMask);
        if ((((b.getWhiteQueenBitBoard() >> square) & 1) == 1))
            movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
        else
            movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

        for (int i = 0; i < 64; i++) {

            long temp = movesBitBoard & (1L << i);
            if (temp != 0) {
                Move move;
                if (b.pieceAtSquare(i).equals(ChessPiece.EMPTY)) {
                    move = new Move(square, i, QUITE_MOVE_FLAG);
                } else {
                    move = new Move(square, i, CAPTURE_FLAG);
                }
                log.trace("adding a queen movement from {} to {} - {}", square, i, move);

                moves.add(move);
            }
        }
        stopwatch.stop(); // optional

        log.debug("queen movement took : {}", stopwatch);
        return moves;
    }

    private static ArrayList<Move> getKingMovement(Board b, int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ArrayList<Move> moves = new ArrayList<>();
        long movesBitBoard;

        if (square > 9) {
            movesBitBoard = KING_SPAN << (square - 9);
        } else {
            movesBitBoard = KING_SPAN >> (9 - square);
        }
        if (square % 8 < 4) {
            movesBitBoard &= ~FILE_GH;
        } else {
            movesBitBoard &= ~FILE_AB;
        }
        if ((((b.getWhiteBitBoard() >> square) & 1) == 1))
            movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
        else
            movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

        for (int i = 0; i < 64; i++) {

            long temp = movesBitBoard & (1L << i);
            if (temp != 0) {
                Move move;
                if (b.pieceAtSquare(i).equals(ChessPiece.EMPTY)) {
                    move = new Move(square, i, QUITE_MOVE_FLAG);
                } else {
                    move = new Move(square, i, CAPTURE_FLAG);
                }
                log.trace("adding a king movement from {} to {} - {}", square, i, move);

                moves.add(move);
            }
        }
        stopwatch.stop(); // optional

        log.debug("king movement took : {}", stopwatch);
        return moves;
    }


    private int countBits(long x) {
        long m1 = 0x5555555555555555L; // binary: 0101...
        long m2 = 0x3333333333333333L; // binary: 00110011..
        long m4 = 0x0f0f0f0f0f0f0f0fL; // binary: 4 zeros, 4 ones ...

        x -= (x >> 1) & m1; // put count of each 2 bits into those 2 bits
        x = (x & m2) + ((x >> 2) & m2); // put count of each 4 bits into those 4
        // bits
        x = (x + (x >> 4)) & m4; // put count of each 8 bits into those 8 bits
        x += x >> 8; // put count of each 16 bits into their lowest 8 bits
        x += x >> 16; // put count of each 32 bits into their lowest 8 bits
        x += x >> 32; // put count of each 64 bits into their lowest 8 bits
        return (int) (x & 0x7fL);
    }



}
