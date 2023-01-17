package org.rezatron.chess;

import com.google.common.base.Stopwatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.constants.MoveFlags;

import java.util.LinkedList;
import java.util.List;

import static org.rezatron.chess.constants.ChessConstants.*;
import static org.rezatron.chess.constants.MoveFlags.*;

public
class MoveGenerator {

    private static final Logger log = LogManager.getLogger(MoveGenerator.class);

    private final Board b;

    public MoveGenerator(Board b) {
        this.b = b;
    }

    public List<Move> getMoves() {
        List<Move> moves;
        if (b.isWhitesTurn()) moves = getWhiteMoves();
        else moves = getBlackMoves();
        return legalizeMoves(moves);
    }

    public List<Move> getWhiteMoves() {
        List<Move> moves = new LinkedList<>();
        moves.addAll(getWhitePawnMoves());
        moves.addAll(getWhiteNonPawnMoves());
        return moves;
    }

    public List<Move> getBlackMoves() {
        List<Move> moves = new LinkedList<>();
        moves.addAll(getBlackPawnMoves());
        moves.addAll(getBlackNonPawnMoves());
        return moves;
    }

    private List<Move> getWhitePawnMoves() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();

        long wp = b.getWhitePawnBitBoard();
        long them = b.getBlackBitBoard();
        long empty = b.getEmptyBitBoard();

        long whitePawnPushOne = (wp << 8) & empty;
        long whitePawnPushTwo = (((((wp << 8) & empty) << 8) & rankMask[3])) & empty;
        long whitePawnAttackLeft = pawnAttackLeft(true) & them;
        long whitePawnAttackRight = pawnAttackRight(true) & them;

        long moveOne = whitePawnPushOne & ~rankMask[7];
        long promoteMove = whitePawnPushOne & rankMask[7];
        long whitePawnAttackLeftNormal = whitePawnAttackLeft & ~rankMask[7];
        long whitePawnAttackRightNormal = whitePawnAttackRight & ~rankMask[7];
        long whitePawnAttackLeftPromote = whitePawnAttackLeft & rankMask[7];
        long whitePawnAttackRightPromote = whitePawnAttackRight & rankMask[7];

        long enPassantLeft = whiteEnPassantLeft();
        long enPassantRight = whiteEnPassantRight();


        for (long temp = moveOne; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 8,
                    to,
                    QUITE_MOVE_FLAG);
//            log.trace("Making a white pawn push one from {} to {} - {}",
//                    to - 8,
//                    to,
//                    move);
            moves.add(move);
        }

        for (long temp = whitePawnPushTwo; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 16,
                    to,
                    MoveFlags.DOUBLE_PAWN_PUSH_FLAG);
//            log.trace("Making a white pawn push two from {} to {} - {}",
//                    to - 16,
//                    to,
//                    move);
            moves.add(move);
        }

        for (long temp = whitePawnAttackLeftNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 7,
                    to,
                    MoveFlags.CAPTURE_FLAG);
//            log.trace("Making an attack left white pawn push from {} to {} - {}",
//                    to - 7,
//                    to,
//                    move);
            moves.add(move);
        }
        for (long temp = whitePawnAttackRightNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to - 9,
                    to,
                    MoveFlags.CAPTURE_FLAG);
//            log.trace("Making an attack right  white pawn push from {} to {} - {}",
//                    to - 9,
//                    to,
//                    move);
            moves.add(move);
        }

        for (long temp = promoteMove; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to - 8;
            Move moveRook = new Move(from,
                    to,
                    MoveFlags.ROOK_PROMOTION_FLAG);
            Move moveKnight = new Move(from,
                    to,
                    MoveFlags.KNIGHT_PROMOTION_FLAG);
            Move moveBishop = new Move(from,
                    to,
                    MoveFlags.BISHOP_PROMOTION_FLAG);
            Move moveQueen = new Move(from,
                    to,
                    MoveFlags.QUEEN_PROMOTION_FLAG);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveRook);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveKnight);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveBishop);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveQueen);
            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = whitePawnAttackLeftPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to - 7;
            Move moveRook = new Move(from,
                    to,
                    MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG);
            Move moveKnight = new Move(from,
                    to,
                    MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG);
            Move moveBishop = new Move(from,
                    to,
                    MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG);
            Move moveQueen = new Move(from,
                    to,
                    MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveRook);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveKnight);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveBishop);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveQueen);

            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = whitePawnAttackRightPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to - 9;
            Move moveRook = new Move(from,
                    to,
                    MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG);
            Move moveKnight = new Move(from,
                    to,
                    MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG);
            Move moveBishop = new Move(from,
                    to,
                    MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG);
            Move moveQueen = new Move(from,
                    to,
                    MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveRook);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveKnight);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveBishop);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveQueen);
            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = enPassantLeft; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = (to - 7);

            Move move = new Move(from,
                    to,
                    MoveFlags.EP_CAPTURE_FLAG);
//            log.trace("Making an enPassant left from {} to {} - {}",
//                    from,
//                    to,
//                    move);
            moves.add(move);
        }

        for (long temp = enPassantRight; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = (to - 9);

            Move move = new Move(from,
                    to,
                    MoveFlags.EP_CAPTURE_FLAG);
//            log.trace("Making an enPassant right from {} to {} - {}",
//                    from,
//                    to,
//                    move);
            moves.add(move);
        }
        stopwatch.stop(); // optional

//        log.debug("white pawn movement took : {}",
//                stopwatch);

        return moves;
    }

    public List<Move> getBlackPawnMoves() {
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Move> moves = new LinkedList<>();

        long bp = b.getBlackPawnBitBoard();
        long empty = b.getEmptyBitBoard();
        long them = b.getWhiteBitBoard();

        long enPassantLeft = blackEnPassantLeft();
        long enPassantRight = blackEnPassantRight();

        long blackPawnPushOne = (bp >> 8) & empty;
        long blackPawnPushTwo = (((((bp >> 8) & empty) >> 8) & rankMask[4])) & empty;
        long blackPawnAttackLeft = pawnAttackLeft(false) & them;
        long blackPawnAttackRight = pawnAttackRight(false) & them;

        long moveOne = blackPawnPushOne & ~rankMask[0];
        long promoteMove = blackPawnPushOne & rankMask[0];
        long blackPawnAttackLeftNormal = blackPawnAttackLeft & ~rankMask[0];
        long blackPawnAttackRightNormal = blackPawnAttackRight & ~rankMask[0];
        long blackPawnAttackLeftPromote = blackPawnAttackLeft & rankMask[0];
        long blackPawnAttackRightPromote = blackPawnAttackRight & rankMask[0];


        for (long temp = moveOne; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to + 8,
                    to,
                    QUITE_MOVE_FLAG);
//            log.trace("Making a white pawn push one from {} to {} - {}",
//                    to + 8,
//                    to,
//                    move);
            moves.add(move);
        }

        for (long temp = blackPawnPushTwo; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to + 16,
                    to,
                    MoveFlags.DOUBLE_PAWN_PUSH_FLAG);
//            log.trace("Making a white pawn push two from {} to {} - {}",
//                    to + 16,
//                    to,
//                    move);
            moves.add(move);
        }

        for (long temp = blackPawnAttackLeftNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to + 7,
                    to,
                    MoveFlags.CAPTURE_FLAG);
//            log.trace("Making an attack left white pawn push from {} to {} - {}",
//                    to + 7,
//                    to,
//                    move);
            moves.add(move);
        }
        for (long temp = blackPawnAttackRightNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            Move move = new Move(to + 9,
                    to,
                    MoveFlags.CAPTURE_FLAG);
//            log.trace("Making an attack right  white pawn push from {} to {} - {}",
//                    to + 9,
//                    to,
//                    move);
            moves.add(move);
        }
        for (long temp = promoteMove; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to + 8;
            Move moveRook = new Move(from,
                    to,
                    MoveFlags.ROOK_PROMOTION_FLAG);
            Move moveKnight = new Move(from,
                    to,
                    MoveFlags.KNIGHT_PROMOTION_FLAG);
            Move moveBishop = new Move(from,
                    to,
                    MoveFlags.BISHOP_PROMOTION_FLAG);
            Move moveQueen = new Move(from,
                    to,
                    MoveFlags.QUEEN_PROMOTION_FLAG);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveRook);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveKnight);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveBishop);
//            log.trace("Making an promotion move  from {} to {} - {}",
//                    from,
//                    to,
//                    moveQueen);
            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = blackPawnAttackLeftPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to + 7;
            Move moveRook = new Move(from,
                    to,
                    MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG);
            Move moveKnight = new Move(from,
                    to,
                    MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG);
            Move moveBishop = new Move(from,
                    to,
                    MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG);
            Move moveQueen = new Move(from,
                    to,
                    MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveRook);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveKnight);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveBishop);
//            log.trace("Making an promotion attack left white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveQueen);

            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = blackPawnAttackRightPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = to + 9;
            Move moveRook = new Move(from,
                    to,
                    MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG);
            Move moveKnight = new Move(from,
                    to,
                    MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG);
            Move moveBishop = new Move(from,
                    to,
                    MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG);
            Move moveQueen = new Move(from,
                    to,
                    MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveRook);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveKnight);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveBishop);
//            log.trace("Making an promotion attack right white pawn push from {} to {} - {}",
//                    from,
//                    to,
//                    moveQueen);
            moves.add(moveRook);
            moves.add(moveKnight);
            moves.add(moveBishop);
            moves.add(moveQueen);
        }

        for (long temp = enPassantLeft; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = (to + 7);

            Move move = new Move(from,
                    to,
                    MoveFlags.EP_CAPTURE_FLAG);
//            log.trace("Making an enPassant left from {} to {} - {}",
//                    from,
//                    to,
//                    move);
            moves.add(move);
        }

        for (long temp = enPassantRight; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int to = Long.numberOfTrailingZeros(temp);
            int from = (to + 9);

            Move move = new Move(from,
                    to,
                    MoveFlags.EP_CAPTURE_FLAG);
//            log.trace("Making an enPassant right from {} to {} - {}",
//                    from,
//                    to,
//                    move);
            moves.add(move);
        }
        stopwatch.stop(); // optional

        log.debug("white pawn movement took : {}",
                stopwatch);

        return moves;
    }

    private long pawnAttackRight(boolean isWhite) {

        if (isWhite) {
            long wp = b.getWhitePawnBitBoard();
            return ((wp << 9) & ~FILE_A);
        }
        long bp = b.getBlackPawnBitBoard();
        return ((bp >> 9) & ~FILE_H);
    }

    private long pawnAttackLeft(boolean isWhite) {

        if (isWhite) {
            long wp = b.getWhitePawnBitBoard();
            return ((wp << 7) & ~FILE_H);
        }
        long bp = b.getBlackPawnBitBoard();
        return ((bp >> 7) & ~FILE_A);
    }

    private long blackEnPassantLeft() {
        String enPassantTarget = b.getEnPassantTarget();
        if (enPassantTarget.equals("-") || b.isWhitesTurn()) return 0L;
        return letterSquaresHashMap.get(enPassantTarget) & pawnAttackLeft(false);
    }

    private long blackEnPassantRight() {
        String enPassantTarget = b.getEnPassantTarget();
        if (enPassantTarget.equals("-") || b.isWhitesTurn()) return 0L;
        return letterSquaresHashMap.get(enPassantTarget) & pawnAttackRight(false);
    }

    private long whiteEnPassantLeft() {
        String enPassantTarget = b.getEnPassantTarget();
        if (enPassantTarget.equals("-") || !b.isWhitesTurn()) return 0L;
        return letterSquaresHashMap.get(enPassantTarget) & pawnAttackLeft(true);
    }

    private long whiteEnPassantRight() {
        String enPassantTarget = b.getEnPassantTarget();
        if (enPassantTarget.equals("-") || !b.isWhitesTurn()) return 0L;
        return letterSquaresHashMap.get(enPassantTarget) & pawnAttackRight(true);
    }

    private List<Move> getWhiteNonPawnMoves() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();

        for (long temp = b.getWhiteRookBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getRookMoves((square)));
//            log.trace("After ROOK: {}",
//                    moves);
        }
        for (long temp = b.getWhiteBishopBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getBishopMoves((square)));
//            log.trace("After BISHOP: {}",
//                    moves);
        }
        for (long temp = b.getWhiteKnightBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getKnightMoves((square)));
//            log.trace("After KNIGHT: {}",
//                    moves);
        }
        for (long temp = b.getWhiteQueenBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getQueenMoves((square)));
//            log.trace("After QUEEN: {}",
//                    moves);
        }

        moves.addAll(getKingMoves(Long.numberOfTrailingZeros(b.getWhiteKingBitBoard())));
        long attacks = pawnAttackLeft(false) | pawnAttackRight(false) | getBlackMovement();


        if (b.canWhiteKingSideCastle() && b.isSquareEmpty(5) && b.isSquareEmpty(6) && !isAttackedBy(112L,
                attacks)) {
            moves.add(new Move(4,
                    6,
                    KING_CASTLE_FLAG));
        }

        if (b.canWhiteQueenSideCastle() && b.isSquareEmpty(1) && b.isSquareEmpty(2) && b.isSquareEmpty(3)
                && !isAttackedBy(28L,
                attacks)) {
            moves.add(new Move(4,
                    2,
                    QUEEN_CASTLE_FLAG));
        }

        stopwatch.stop(); // optional

//        log.debug("white non pawn movement took : {}",
//                stopwatch);
        return moves;
    }

    private List<Move> getBlackNonPawnMoves() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();
        for (long temp = b.getBlackRookBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getRookMoves((square)));
//            log.trace("After ROOK: {}",
//                    moves);
        }
        for (long temp = b.getBlackBishopBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getBishopMoves((square)));
//            log.trace("After BISHOP: {}",
//                    moves);
        }
        for (long temp = b.getBlackKnightBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getKnightMoves((square)));
//            log.trace("After KNIGHT: {}",
//                    moves);
        }
        for (long temp = b.getBlackQueenBitBoard() ; temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            moves.addAll(getQueenMoves((square)));
//            log.trace("After QUEEN: {}",
//                    moves);
        }
        moves.addAll(getKingMoves(Long.numberOfTrailingZeros(b.getBlackKingBitBoard())));

        long attacks = pawnAttackLeft(true) | pawnAttackRight(true) | getWhiteMovement();

        if (b.canBlackKingSideCastle() && b.isSquareEmpty(61) && b.isSquareEmpty(62)
                && !isAttackedBy(8070450532247928832L,
                attacks)) {
            moves.add(new Move(60,
                    62,
                    KING_CASTLE_FLAG));
        }

        if (b.canBlackQueenSideCastle() && b.isSquareEmpty(57) && b.isSquareEmpty(58) && b.isSquareEmpty(59)
                && !isAttackedBy(2017612633061982208L,
                attacks)) {
            moves.add(new Move(60,
                    58,
                    QUEEN_CASTLE_FLAG));
        }

        stopwatch.stop(); // optional

        log.debug("black non pawn movement took : {}",
                stopwatch);
        return moves;
    }

    private List<Move> getRookMoves(int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();
        long movesBitBoard = getRookMovement(square);

        movesBitBoard =removeLikeSquares(movesBitBoard);
        long quiteMoves = movesBitBoard & b.getEmptyBitBoard();
        long captureMoves = movesBitBoard & b.getOccupiedBitBoard();

        for (; quiteMoves != 0; quiteMoves -= 1L << Long.numberOfTrailingZeros(quiteMoves)) {
            int i = Long.numberOfTrailingZeros(quiteMoves);
            Move move;
            move = new Move(square,
                    i,
                    QUITE_MOVE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        for (; captureMoves != 0; captureMoves -= 1L << Long.numberOfTrailingZeros(captureMoves)) {
            int i = Long.numberOfTrailingZeros(captureMoves);
            Move move;
            move = new Move(square,
                    i,
                    CAPTURE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        stopwatch.stop(); // optional
//
//        log.debug("rook movement took : {}",
//                stopwatch);
        return moves;
    }

    private long getRookMovement(int square) {

        long occupied = b.getOccupiedBitBoard();
        return getRookMovement(occupied,
                square);
    }

    private long getRookMovement(long occupied, int square) {
        long binaryS = 1L << square;
        long r = Long.reverse(binaryS);
        long twoR = 2 * r;
        long fMask = fileMask[square % 8];

        long one = ((occupied & fMask) - (2 * binaryS)) ^ Long.reverse(Long.reverse(occupied & fMask) - (twoR));

        long two = (occupied - 2 * binaryS) ^ Long.reverse(Long.reverse(occupied) - twoR);
        return (one & fileMask[square % 8]) + (two & rankMask[square / 8]);
    }

    private List<Move> getKnightMoves(int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();
        long movesBitBoard = getKnightMovement(square);
        movesBitBoard =removeLikeSquares(movesBitBoard);

        long quiteMoves = movesBitBoard & b.getEmptyBitBoard();
        long captureMoves = movesBitBoard & b.getOccupiedBitBoard();

        for (; quiteMoves != 0; quiteMoves -= 1L << Long.numberOfTrailingZeros(quiteMoves)) {
            int i = Long.numberOfTrailingZeros(quiteMoves);
            Move move;
            move = new Move(square,
                    i,
                    QUITE_MOVE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        for (; captureMoves != 0; captureMoves -= 1L << Long.numberOfTrailingZeros(captureMoves)) {
            int i = Long.numberOfTrailingZeros(captureMoves);
            Move move;
            move = new Move(square,
                    i,
                    CAPTURE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        stopwatch.stop(); // optional

//        log.debug("knight movement took : {}",
//                stopwatch);
        return moves;
    }

    private long getKnightMovement(int square) {
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
        return movesBitBoard;
    }

    private List<Move> getBishopMoves(int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();
        long movesBitBoard = getBishopMovement(square);
        movesBitBoard =removeLikeSquares(movesBitBoard);

        long quiteMoves = movesBitBoard & b.getEmptyBitBoard();
        long captureMoves = movesBitBoard & b.getOccupiedBitBoard();

        for (; quiteMoves != 0; quiteMoves -= 1L << Long.numberOfTrailingZeros(quiteMoves)) {
            int i = Long.numberOfTrailingZeros(quiteMoves);
            Move move;
            move = new Move(square,
                    i,
                    QUITE_MOVE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        for (; captureMoves != 0; captureMoves -= 1L << Long.numberOfTrailingZeros(captureMoves)) {
            int i = Long.numberOfTrailingZeros(captureMoves);
            Move move;
            move = new Move(square,
                    i,
                    CAPTURE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        stopwatch.stop(); // optional

        log.debug("bishop movement took : {}",
                stopwatch);
        return moves;
    }

    private long getBishopMovement(int square) {

        long occupied = b.getOccupiedBitBoard();
        return getBishopMovement(occupied,
                square);

    }

    private long getBishopMovement(long occupied, int square) {
        long binaryS = 1L << square;
        long r = Long.reverse(binaryS);
        long twoR = 2 * r;
        long dMask = diagonalMask[7 + square / 8 - square % 8];
        long adMask = antiDiagonalMask[(square / 8) + (square % 8)];
        long one = ((occupied & dMask) - (2 * binaryS)) ^ Long.reverse(Long.reverse(occupied & dMask) - (twoR));
        long two = ((occupied & adMask) - (2 * binaryS)) ^ Long.reverse(Long.reverse(occupied & adMask) - (twoR));
        return (one & dMask) | (two & adMask);
    }

    private long removeLikeSquares(long movesBitBoard)
    {
        if(b.isWhitesTurn())
            return movesBitBoard & (b.getBlackBitBoard() | b.getEmptyBitBoard());
        return movesBitBoard & (b.getWhiteBitBoard() | b.getEmptyBitBoard());
    }

    private List<Move> getQueenMoves(int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();

        long movesBitBoard = getQueenMovement(square);

        movesBitBoard =removeLikeSquares(movesBitBoard);

        long quiteMoves = movesBitBoard & b.getEmptyBitBoard();
        long captureMoves = movesBitBoard & b.getOccupiedBitBoard();

        for (; quiteMoves != 0; quiteMoves -= 1L << Long.numberOfTrailingZeros(quiteMoves)) {
            int i = Long.numberOfTrailingZeros(quiteMoves);
            Move move;
            move = new Move(square,
                    i,
                    QUITE_MOVE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        for (; captureMoves != 0; captureMoves -= 1L << Long.numberOfTrailingZeros(captureMoves)) {
            int i = Long.numberOfTrailingZeros(captureMoves);
            Move move;
            move = new Move(square,
                    i,
                    CAPTURE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        stopwatch.stop(); // optional

        log.debug("queen movement took : {}",
                stopwatch);
        return moves;
    }

    public long getQueenMovement(int square) {
        return getBishopMovement(square) | getRookMovement(square);
    }

    private List<Move> getKingMoves(int square) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Move> moves = new LinkedList<>();
        long movesBitBoard = getKingMovement(square);
        if ((((b.getWhiteBitBoard() >> square) & 1) == 1))
            movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
        else movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

        long quiteMoves = movesBitBoard & b.getEmptyBitBoard();
        long captureMoves = movesBitBoard & b.getOccupiedBitBoard();

        for (; quiteMoves != 0; quiteMoves -= 1L << Long.numberOfTrailingZeros(quiteMoves)) {
            int i = Long.numberOfTrailingZeros(quiteMoves);
            Move move;
            move = new Move(square,
                    i,
                    QUITE_MOVE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        for (; captureMoves != 0; captureMoves -= 1L << Long.numberOfTrailingZeros(captureMoves)) {
            int i = Long.numberOfTrailingZeros(captureMoves);
            Move move;
            move = new Move(square,
                    i,
                    CAPTURE_FLAG);

//            log.trace("adding a bishop movement from {} to {} - {}",
//                    square,
//                    i,
//                    move);

            moves.add(move);
        }
        stopwatch.stop(); // optional

        log.debug("king movement took : {}",
                stopwatch);
        return moves;
    }

    private long getKingMovement(int square) {
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
        return movesBitBoard;
    }

    private List<Move> legalizeMoves(List<Move> pseudoLegalMoves) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int king;
        boolean isChecked;
        if (b.isWhitesTurn()) {
            king = b.getWhiteKingSquare();
            isChecked = isWhiteChecked();
        } else {
            king = b.getBlackKingSquare();
            isChecked = isBlackChecked();
        }
//    long rookXRayMoves = xrayRookAttacks(all, me, king);
//    long bishopXRayMoves = xrayBishopAttacks(all, me, king);
//    long xRayMoves = rookXRayMoves|bishopXRayMoves;

        long rookXRayMoves = getRookMovement(king);
        long bishopXRayMoves = getBishopMovement(king);
        long xRayMoves = rookXRayMoves | bishopXRayMoves;


        List<Move> legalMoves = new LinkedList<>();
        for (Move m : pseudoLegalMoves) {
            long moveBitBoard = 1L << m.getFrom();

            if ((moveBitBoard & xRayMoves) != 0) {
                Board newBoard = new Board(b);
                newBoard.move(m);
                MoveGenerator mg = new MoveGenerator(newBoard);
                if (newBoard.isWhitesTurn() ? mg.isBlackChecked() : mg.isWhiteChecked()) {
//                    log.trace("{} is an illegal move.",
//                            m);
                } else {
                    legalMoves.add(m);
                }
            } else if (m.getFlags() == EP_CAPTURE_FLAG.getFlag()) {
                Board newBoard = new Board(b);
                newBoard.move(m);
                MoveGenerator mg = new MoveGenerator(newBoard);
                if (newBoard.isWhitesTurn() ? mg.isBlackChecked() : mg.isWhiteChecked()) {
//                    log.trace("{} is an illegal move.",
//                            m);
                } else {
                    legalMoves.add(m);
                }
            } else if (m.getFrom() == king) {
                Board newBoard = new Board(b);
                newBoard.move(m);
                MoveGenerator mg = new MoveGenerator(newBoard);
                if (newBoard.isWhitesTurn() ? mg.isBlackChecked() : mg.isWhiteChecked()) {
//                    log.trace("{} is an illegal move.",
//                            m);
                } else {
                    legalMoves.add(m);
                }
            } else if (isChecked) {
                Board newBoard = new Board(b);
                newBoard.move(m);
                MoveGenerator mg = new MoveGenerator(newBoard);
                if (newBoard.isWhitesTurn() ? mg.isBlackChecked() : mg.isWhiteChecked()) {
//                    log.trace("{} is an illegal move.",
//                            m);
                } else {
                    legalMoves.add(m);
                }
            } else {
//                log.trace("Move {} is not checked on board {}",
//                        m,
//                        b);
                legalMoves.add(m);
            }
        }
        stopwatch.stop(); // optional

//        log.debug("legalizeMoves took : {}",
//                stopwatch);
        return legalMoves;
    }

    public boolean isWhiteChecked() {
        int dangerSquare = b.getWhiteKingSquare();
        long rookXRayMoves = getRookMovement(dangerSquare);
        long bishopXRayMoves = getBishopMovement(dangerSquare);
        long knightMoves = getKnightMovement(dangerSquare);
        long kingSquare = 1L << b.getWhiteKingSquare();

        long temp1 = rookXRayMoves & (b.getBlackRookBitBoard() | b.getBlackQueenBitBoard());
        long temp2 = bishopXRayMoves & (b.getBlackBishopBitBoard() | b.getBlackQueenBitBoard());
        long temp3 = knightMoves & (b.getBlackKnightBitBoard());
        long temp4 = (kingSquare & getKingMovement(b.getBlackKingSquare()));
        long attacks =
                (kingSquare & pawnAttackRight(false)) | (kingSquare & pawnAttackLeft(false)) | temp1 | temp2 | temp3 | temp4;
        return attacks != 0;
    }

    public boolean isBlackChecked() {
        int dangerSquare = b.getBlackKingSquare();
        long rookXRayMoves = getRookMovement(dangerSquare);
        long bishopXRayMoves = getBishopMovement(dangerSquare);
        long knightMoves = getKnightMovement(dangerSquare);
        long kingSquare = 1L << b.getBlackKingSquare();

        long temp1 = rookXRayMoves & (b.getWhiteRookBitBoard() | b.getWhiteQueenBitBoard());
        long temp2 = bishopXRayMoves & (b.getWhiteBishopBitBoard() | b.getWhiteQueenBitBoard());
        long temp3 = knightMoves & (b.getWhiteKnightBitBoard());
        long temp4 = (kingSquare & getKingMovement(b.getWhiteKingSquare()));
        long attacks =
                (kingSquare & pawnAttackRight(true)) | (kingSquare & pawnAttackLeft(true)) | temp1 | temp2 | temp3 | temp4;
        return attacks != 0;
    }

    private long getBlackMovement() {
        Long ans = 0L;
        for (long temp = b.getBlackRookBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getRookMovement(square);
            }
        for (long temp = b.getBlackBishopBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getBishopMovement(square);
        }
        for (long temp = b.getBlackKnightBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getKnightMovement(square);
        }
        for (long temp = b.getBlackQueenBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getQueenMovement(square);
        }
//
//        for (long temp = (b.getBlackRookBitBoard() | b.getBlackBishopBitBoard() | b.getBlackKnightBitBoard()
//                | b.getBlackQueenBitBoard()); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
//            int square = Long.numberOfTrailingZeros(temp);
//            switch (b.pieceAtSquare(square)) {
//                case BLACK_ROOK -> ans |= getRookMovement(square);
//                case BLACK_KNIGHT -> ans |= getKnightMovement(square);
//                case BLACK_BISHOP -> ans |= getBishopMovement(square);
//                case BLACK_QUEEN -> ans |= getQueenMovement(square);
//            }
//        }
        ans |= getKingMovement(Long.numberOfTrailingZeros(b.getBlackKingBitBoard()));
        return ans;
    }


    private long getWhiteMovement() {
        Long ans = 0L;

        for (long temp = b.getWhiteRookBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getRookMovement(square);
        }
        for (long temp = b.getWhiteBishopBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getBishopMovement(square);
        }
        for (long temp = b.getWhiteKnightBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getKnightMovement(square);
        }
        for (long temp = b.getWhiteQueenBitBoard(); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
            int square = Long.numberOfTrailingZeros(temp);
            ans |= getQueenMovement(square);
        }
//        for (long temp = (b.getWhiteRookBitBoard() | b.getWhiteBishopBitBoard() | b.getWhiteKnightBitBoard()
//                | b.getWhiteQueenBitBoard()); temp != 0; temp -= 1L << Long.numberOfTrailingZeros(temp)) {
//            int square = Long.numberOfTrailingZeros(temp);
//            switch (b.pieceAtSquare(square)) {
//                case WHITE_ROOK -> ans |= getRookMovement(square);
//                case WHITE_KNIGHT -> ans |= getKnightMovement(square);
//                case WHITE_BISHOP -> ans |= getBishopMovement(square);
//                case WHITE_QUEEN -> ans |= getQueenMovement(square);
//            }
//        }
        ans |= getKingMovement(Long.numberOfTrailingZeros(b.getWhiteKingBitBoard()));
        return ans;
    }

    private boolean isSquareAttackedBy(int square, long attack) {
        return (squares[square] & attack) != 0;
    }

    //TODO make this not require attacks
    private boolean isAttackedBy(long squares, long attack) {
        return (squares & attack) != 0;
    }

    private long xrayRookAttacks(long occ, long blockers, int rookSq) {
        long attacks = getRookMovement(occ,
                rookSq);
        blockers &= attacks;
        return attacks ^ getRookMovement(occ ^ blockers,
                rookSq);
    }

    private long xrayBishopAttacks(long occ, long blockers, int bishopSq) {
        long attacks = getBishopMovement(occ,
                bishopSq);
        blockers &= attacks;
        return attacks ^ getBishopMovement(occ ^ blockers,
                bishopSq);
    }

    private String toString(Long test) {
        String rowString = "   +---+---+---+---+---+---+---+---+";
        String printBoard = "\n\n" + rowString + "\n";
        int row = 8;
        for (int x = 0; x < order.length; x++) {
            int i = order[x];
            if (i % 8 == 0) {
                printBoard += row + "  |";
                row--;
            }

            if (((test >> i) & 1) == 1) {
                printBoard += "(X)" + "|";
            } else {
                printBoard += " " + String.format("%02d",
                        i) + "|";
            }
            if (i % 8 == 7) printBoard += "\n" + rowString + "\n";
        }
        printBoard += "     a   b   c   d   e   f   g   h \n\n";
        return printBoard;
    }


}
