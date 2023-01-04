package org.rezatron.chess;

import com.google.common.base.Stopwatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.constants.ChessPiece;
import org.rezatron.chess.constants.MoveFlags;

import java.util.LinkedList;
import java.util.List;

import static org.rezatron.chess.constants.ChessConstants.*;
import static org.rezatron.chess.constants.MoveFlags.*;

public
class MoveGenerator {

  private static final Logger log = LogManager.getLogger( MoveGenerator.class );

  private static final double log2 = Math.log( 2 );

  public static
  List<Move> getMoves(Board b) {
    List<Move> moves;
    if (b.isWhitesTurn()) moves = getWhiteMoves( b );
    else moves = getBlackMoves( b );
    return legalizeMoves( b,
                          moves );
  }

  public static
  List<Move> getWhiteMoves(Board b) {
    List<Move> moves = new LinkedList<>();
    moves.addAll( getWhitePawnMoves( b ) );
    moves.addAll( getWhiteNonPawnMoves( b ) );
    return moves;
  }

  public static
  List<Move> getBlackMoves(Board b) {
    List<Move> moves = new LinkedList<>();
    moves.addAll( getBlackPawnMoves( b ) );
    moves.addAll( getBlackNonPawnMoves( b ) );
    return moves;
  }

  public static
  List<Move> getWhitePawnMoves(Board b) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();

    long wp = b.getWhitePawnBitBoard();
    long them = b.getBlackBitBoard();
    long empty = b.getEmptyBitBoard();

    long whitePawnPushOne = (wp << 8) & empty;
    long whitePawnPushTwo = (((((wp << 8) & empty) << 8) & rankMask[3])) & empty;
    long whitePawnAttackLeft = pawnAttackLeft( b,
                                               true ) & them;
    long whitePawnAttackRight = pawnAttackRight( b,
                                                 true ) & them;

    long moveOne = whitePawnPushOne & ~rankMask[7];
    long promoteMove = whitePawnPushOne & rankMask[7];
    long whitePawnAttackLeftNormal = whitePawnAttackLeft & ~rankMask[7];
    long whitePawnAttackRightNormal = whitePawnAttackRight & ~rankMask[7];
    long whitePawnAttackLeftPromote = whitePawnAttackLeft & rankMask[7];
    long whitePawnAttackRightPromote = whitePawnAttackRight & rankMask[7];

    long enPassantLeft = whiteEnPassantLeft( b );
    long enPassantRight = whiteEnPassantRight( b );


    for (long temp = moveOne; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to - 8,
                            to,
                            QUITE_MOVE_FLAG );
      log.trace( "Making a white pawn push one from {} to {} - {}",
                 to - 8,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = whitePawnPushTwo; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to - 16,
                            to,
                            MoveFlags.DOUBLE_PAWN_PUSH_FLAG );
      log.trace( "Making a white pawn push two from {} to {} - {}",
                 to - 16,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = whitePawnAttackLeftNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to - 7,
                            to,
                            MoveFlags.CAPTURE_FLAG );
      log.trace( "Making an attack left white pawn push from {} to {} - {}",
                 to - 7,
                 to,
                 move );
      moves.add( move );
    }
    for (long temp = whitePawnAttackRightNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to - 9,
                            to,
                            MoveFlags.CAPTURE_FLAG );
      log.trace( "Making an attack right  white pawn push from {} to {} - {}",
                 to - 9,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = promoteMove; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = to - 8;
      Move moveRook = new Move( from,
                                to,
                                MoveFlags.ROOK_PROMOTION_FLAG );
      Move moveKnight = new Move( from,
                                  to,
                                  MoveFlags.KNIGHT_PROMOTION_FLAG );
      Move moveBishop = new Move( from,
                                  to,
                                  MoveFlags.BISHOP_PROMOTION_FLAG );
      Move moveQueen = new Move( from,
                                 to,
                                 MoveFlags.QUEEN_PROMOTION_FLAG );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveRook );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveKnight );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveBishop );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveQueen );
      moves.add( moveRook );
      moves.add( moveKnight );
      moves.add( moveBishop );
      moves.add( moveQueen );
    }

    for (long temp = whitePawnAttackLeftPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = to - 7;
      Move moveRook = new Move( from,
                                to,
                                MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG );
      Move moveKnight = new Move( from,
                                  to,
                                  MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG );
      Move moveBishop = new Move( from,
                                  to,
                                  MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG );
      Move moveQueen = new Move( from,
                                 to,
                                 MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveRook );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveKnight );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveBishop );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveQueen );

      moves.add( moveRook );
      moves.add( moveKnight );
      moves.add( moveBishop );
      moves.add( moveQueen );
    }

    for (long temp = whitePawnAttackRightPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = to - 9;
      Move moveRook = new Move( from,
                                to,
                                MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG );
      Move moveKnight = new Move( from,
                                  to,
                                  MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG );
      Move moveBishop = new Move( from,
                                  to,
                                  MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG );
      Move moveQueen = new Move( from,
                                 to,
                                 MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveRook );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveKnight );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveBishop );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveQueen );
      moves.add( moveRook );
      moves.add( moveKnight );
      moves.add( moveBishop );
      moves.add( moveQueen );
    }

    for (long temp = enPassantLeft; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = (to - 7);

      Move move = new Move( from,
                            to,
                            MoveFlags.EP_CAPTURE_FLAG );
      log.trace( "Making an enPassant left from {} to {} - {}",
                 from,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = enPassantRight; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = (to - 9);

      Move move = new Move( from,
                            to,
                            MoveFlags.EP_CAPTURE_FLAG );
      log.trace( "Making an enPassant right from {} to {} - {}",
                 from,
                 to,
                 move );
      moves.add( move );
    }
    stopwatch.stop(); // optional

    log.debug( "white pawn movement took : {}",
               stopwatch );

    return moves;
  }

  public static
  List<Move> getBlackPawnMoves(Board b) {
    Stopwatch stopwatch = Stopwatch.createStarted();

    List<Move> moves = new LinkedList<>();

    long bp = b.getBlackPawnBitBoard();
    long empty = b.getEmptyBitBoard();
    long them = b.getWhiteBitBoard();

    long enPassantLeft = blackEnPassantLeft( b );
    long enPassantRight = blackEnPassantRight( b );

    long blackPawnPushOne = (bp >> 8) & empty;
    long blackPawnPushTwo = (((((bp >> 8) & empty) >> 8) & rankMask[4])) & empty;
    long blackPawnAttackLeft = pawnAttackLeft( b,
                                               false ) & them;
    long blackPawnAttackRight = pawnAttackRight( b,
                                                 false ) & them;

    long moveOne = blackPawnPushOne & ~rankMask[7];
    long promoteMove = blackPawnPushOne & rankMask[7];
    long whitePawnAttackLeftNormal = blackPawnAttackLeft & ~rankMask[7];
    long whitePawnAttackRightNormal = blackPawnAttackRight & ~rankMask[7];
    long whitePawnAttackLeftPromote = blackPawnAttackLeft & rankMask[7];
    long whitePawnAttackRightPromote = blackPawnAttackRight & rankMask[7];


    for (long temp = moveOne; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to + 8,
                            to,
                            QUITE_MOVE_FLAG );
      log.trace( "Making a white pawn push one from {} to {} - {}",
                 to + 8,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = blackPawnPushTwo; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to + 16,
                            to,
                            MoveFlags.DOUBLE_PAWN_PUSH_FLAG );
      log.trace( "Making a white pawn push two from {} to {} - {}",
                 to + 16,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = whitePawnAttackLeftNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to + 7,
                            to,
                            MoveFlags.CAPTURE_FLAG );
      log.trace( "Making an attack left white pawn push from {} to {} - {}",
                 to + 7,
                 to,
                 move );
      moves.add( move );
    }
    for (long temp = whitePawnAttackRightNormal; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      Move move = new Move( to + 9,
                            to,
                            MoveFlags.CAPTURE_FLAG );
      log.trace( "Making an attack right  white pawn push from {} to {} - {}",
                 to + 9,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = promoteMove; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = to + 8;
      Move moveRook = new Move( from,
                                to,
                                MoveFlags.ROOK_PROMOTION_FLAG );
      Move moveKnight = new Move( from,
                                  to,
                                  MoveFlags.KNIGHT_PROMOTION_FLAG );
      Move moveBishop = new Move( from,
                                  to,
                                  MoveFlags.BISHOP_PROMOTION_FLAG );
      Move moveQueen = new Move( from,
                                 to,
                                 MoveFlags.QUEEN_PROMOTION_FLAG );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveRook );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveKnight );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveBishop );
      log.trace( "Making an promotion move  from {} to {} - {}",
                 from,
                 to,
                 moveQueen );
      moves.add( moveRook );
      moves.add( moveKnight );
      moves.add( moveBishop );
      moves.add( moveQueen );
    }

    for (long temp = whitePawnAttackLeftPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = to - 7;
      Move moveRook = new Move( from,
                                to,
                                MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG );
      Move moveKnight = new Move( from,
                                  to,
                                  MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG );
      Move moveBishop = new Move( from,
                                  to,
                                  MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG );
      Move moveQueen = new Move( from,
                                 to,
                                 MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveRook );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveKnight );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveBishop );
      log.trace( "Making an promotion attack left white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveQueen );

      moves.add( moveRook );
      moves.add( moveKnight );
      moves.add( moveBishop );
      moves.add( moveQueen );
    }

    for (long temp = whitePawnAttackRightPromote; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = to + 9;
      Move moveRook = new Move( from,
                                to,
                                MoveFlags.ROOK_PROMOTION_CAPTURE_FLAG );
      Move moveKnight = new Move( from,
                                  to,
                                  MoveFlags.KNIGHT_PROMOTION_CAPTURE_FLAG );
      Move moveBishop = new Move( from,
                                  to,
                                  MoveFlags.BISHOP_PROMOTION_CAPTURE_FLAG );
      Move moveQueen = new Move( from,
                                 to,
                                 MoveFlags.QUEEN_PROMOTION_CAPTURE_FLAG );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveRook );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveKnight );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveBishop );
      log.trace( "Making an promotion attack right white pawn push from {} to {} - {}",
                 from,
                 to,
                 moveQueen );
      moves.add( moveRook );
      moves.add( moveKnight );
      moves.add( moveBishop );
      moves.add( moveQueen );
    }

    for (long temp = enPassantLeft; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = (to + 7);

      Move move = new Move( from,
                            to,
                            MoveFlags.EP_CAPTURE_FLAG );
      log.trace( "Making an enPassant left from {} to {} - {}",
                 from,
                 to,
                 move );
      moves.add( move );
    }

    for (long temp = enPassantRight; temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int to = Long.numberOfTrailingZeros( temp );
      int from = (to + 9);

      Move move = new Move( from,
                            to,
                            MoveFlags.EP_CAPTURE_FLAG );
      log.trace( "Making an enPassant right from {} to {} - {}",
                 from,
                 to,
                 move );
      moves.add( move );
    }
    stopwatch.stop(); // optional

    log.debug( "white pawn movement took : {}",
               stopwatch );

    return moves;
  }

  private static
  long pawnAttackRight(Board b, boolean isWhite) {

    if (isWhite) {
      long wp = b.getWhitePawnBitBoard();
      return ((wp << 9) & ~FILE_A);
    }
    long bp = b.getBlackPawnBitBoard();
    return ((bp >> 9) & ~FILE_H);
  }

  private static
  long pawnAttackLeft(Board b, boolean isWhite) {

    if (isWhite) {
      long wp = b.getWhitePawnBitBoard();
      return ((wp << 7) & ~FILE_H);
    }
    long bp = b.getBlackPawnBitBoard();
    return ((bp >> 7) & ~FILE_A);
  }

  private static
  long blackEnPassantLeft(Board b) {
    String enPassantTarget = b.getEnPassantTarget();
    if (enPassantTarget.equals( "-" ) || b.isWhitesTurn()) return 0L;
    return letterSquaresHashMap.get( enPassantTarget ) & pawnAttackLeft( b,
                                                                         false );
  }

  private static
  long blackEnPassantRight(Board b) {
    String enPassantTarget = b.getEnPassantTarget();
    if (enPassantTarget.equals( "-" ) || b.isWhitesTurn()) return 0L;
    return letterSquaresHashMap.get( enPassantTarget ) & pawnAttackRight( b,
                                                                          false );
  }

  private static
  long whiteEnPassantLeft(Board b) {
    String enPassantTarget = b.getEnPassantTarget();
    if (enPassantTarget.equals( "-" ) || !b.isWhitesTurn()) return 0L;
    return letterSquaresHashMap.get( enPassantTarget ) & pawnAttackLeft( b,
                                                                         true );
  }

  private static
  long whiteEnPassantRight(Board b) {
    String enPassantTarget = b.getEnPassantTarget();
    if (enPassantTarget.equals( "-" ) || !b.isWhitesTurn()) return 0L;
    return letterSquaresHashMap.get( enPassantTarget ) & pawnAttackRight( b,
                                                                          true );
  }

  private static
  List<Move> getWhiteNonPawnMoves(Board b) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();
    for (long temp = (b.getWhiteRookBitBoard() | b.getWhiteBishopBitBoard() | b.getWhiteKnightBitBoard()
                      | b.getWhiteQueenBitBoard()); temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int square = Long.numberOfTrailingZeros( temp );
      switch (b.pieceAtSquare( square )) {
        case WHITE_ROOK -> {
          moves.addAll( getRookMoves( b,
                                      (square) ) );
          log.trace( "After ROOK: {}",
                     moves );
        }
        case WHITE_KNIGHT -> {
          moves.addAll( getKnightMoves( b,
                                        (square) ) );
          log.trace( "After KNIGHT: {}",
                     moves );
        }
        case WHITE_BISHOP -> {
          moves.addAll( getBishopMoves( b,
                                        (square) ) );
          log.trace( "After BISHOP: {}",
                     moves );
        }
        case WHITE_QUEEN -> {
          moves.addAll( getQueenMoves( b,
                                       (square) ) );
          log.trace( "After QUEEN: {}",
                     moves );
        }
      }
    }
    moves.addAll( getKingMoves( b,
                                Long.numberOfTrailingZeros( b.getWhiteKingBitBoard() ) ) );
    long attacks = pawnAttackLeft( b,
                                   true ) | pawnAttackRight( b,
                                                             true ) | getBlackMovement( b );

    if (b.canWhiteKingSideCastle() && b.isSquareEmpty( 5 ) && b.isSquareEmpty( 6 ) && !isAttackedBy( 4,
                                                                                                     attacks )
        && !isAttackedBy( 5,
                          attacks ) && !isAttackedBy( 6,
                                                      attacks ))
    {
      moves.add( new Move( 4,
                           6,
                           KING_CASTLE_FLAG ) );
    }
    if (b.canWhiteQueenSideCastle() && b.isSquareEmpty( 1 ) && b.isSquareEmpty( 2 ) && b.isSquareEmpty( 3 )
        && !isAttackedBy( 2,
                          attacks ) && !isAttackedBy( 3,
                                                      attacks ) && !isAttackedBy( 4,
                                                                                  attacks ))
    {
      moves.add( new Move( 4,
                           2,
                           QUEEN_CASTLE_FLAG ) );
    }

    stopwatch.stop(); // optional

    log.debug( "white non pawn movement took : {}",
               stopwatch );
    return moves;
  }

  private static
  List<Move> getBlackNonPawnMoves(Board b) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();
    for (long temp = (b.getBlackRookBitBoard() | b.getBlackBishopBitBoard() | b.getBlackKnightBitBoard()
                      | b.getBlackQueenBitBoard()); temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int square = Long.numberOfTrailingZeros( temp );
      switch (b.pieceAtSquare( square )) {
        case BLACK_ROOK -> {
          moves.addAll( getRookMoves( b,
                                      (square) ) );
          log.trace( "After ROOK: {}",
                     moves );
        }
        case BLACK_KNIGHT -> {
          moves.addAll( getKnightMoves( b,
                                        (square) ) );
          log.trace( "After KNIGHT: {}",
                     moves );
        }
        case BLACK_BISHOP -> {
          moves.addAll( getBishopMoves( b,
                                        (square) ) );
          log.trace( "After BISHOP: {}",
                     moves );
        }
        case BLACK_QUEEN -> {
          moves.addAll( getQueenMoves( b,
                                       (square) ) );
          log.trace( "After QUEEN: {}",
                     moves );
        }
      }
    }
    moves.addAll( getKingMoves( b,
                                Long.numberOfTrailingZeros( b.getBlackKingBitBoard() ) ) );

    long attacks = pawnAttackLeft( b,
                                   false ) | pawnAttackRight( b,
                                                              false ) | getWhiteMovement( b );
    if (b.canBlackKingSideCastle() && b.isSquareEmpty( 61 ) && b.isSquareEmpty( 62 ) && !isAttackedBy( 60,
                                                                                                       attacks )
        && !isAttackedBy( 61,
                          attacks ) && !isAttackedBy( 62,
                                                      attacks ))
    {
      moves.add( new Move( 60,
                           62,
                           KING_CASTLE_FLAG ) );
    }
    if (b.canBlackQueenSideCastle() && b.isSquareEmpty( 57 ) && b.isSquareEmpty( 58 ) && b.isSquareEmpty( 59 )
        && !isAttackedBy( 58,
                          attacks ) && !isAttackedBy( 59,
                                                      attacks ) && !isAttackedBy( 60,
                                                                                  attacks ))
    {
      moves.add( new Move( 60,
                           58,
                           QUEEN_CASTLE_FLAG ) );
    }

    stopwatch.stop(); // optional

    log.debug( "black non pawn movement took : {}",
               stopwatch );
    return moves;
  }

  private static
  List<Move> getRookMoves(Board b, int square) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();
    long movesBitBoard = getRookMovement( b,
                                          square );

    if (b.isWhitesTurn())
      movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
    else movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());
    for (; movesBitBoard != 0; movesBitBoard -= 1L << Long.numberOfTrailingZeros( movesBitBoard )) {
      int i = Long.numberOfTrailingZeros( movesBitBoard );
      Move move;
      if (b.pieceAtSquare( i ).equals( ChessPiece.EMPTY )) {
        move = new Move( square,
                         i,
                         QUITE_MOVE_FLAG );
      } else {
        move = new Move( square,
                         i,
                         CAPTURE_FLAG );
      }
      log.trace( "adding a rook movement from {} to {} - {}",
                 square,
                 i,
                 move );

      moves.add( move );
    }
    stopwatch.stop(); // optional

    log.debug( "rook movement took : {}",
               stopwatch );
    return moves;
  }

  private static
  long getRookMovement(Board b, int square) {
    long binaryS = 1L << square;
    long r = Long.reverse( binaryS );
    long twoR = 2 * r;
    long occupied = b.getOccupiedBitBoard();
    long fMask = fileMask[square % 8];

    long one = ((occupied & fMask) - (2 * binaryS)) ^ Long.reverse( Long.reverse( occupied & fMask ) - (twoR) );

    long two = (occupied - 2 * binaryS) ^ Long.reverse( Long.reverse( occupied ) - twoR );
    return (one & fileMask[square % 8]) + (two & rankMask[square / 8]);
  }

  private static
  List<Move> getKnightMoves(Board b, int square) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();
    long movesBitBoard = getKnightMovement( b,
                                            square );
    if (b.isWhitesTurn())
      movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
    else movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

    for (; movesBitBoard != 0; movesBitBoard -= 1L << Long.numberOfTrailingZeros( movesBitBoard )) {
      int i = Long.numberOfTrailingZeros( movesBitBoard );
      Move move;
      if (b.pieceAtSquare( i ).equals( ChessPiece.EMPTY )) {
        move = new Move( square,
                         i,
                         QUITE_MOVE_FLAG );
      } else {
        move = new Move( square,
                         i,
                         CAPTURE_FLAG );
      }
      log.trace( "adding a knight movement from {} to {} - {}",
                 square,
                 i,
                 move );

      moves.add( move );
    }
    stopwatch.stop(); // optional

    log.debug( "knight movement took : {}",
               stopwatch );
    return moves;
  }

  private static
  long getKnightMovement(Board b, int square) {
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

  private static
  List<Move> getBishopMoves(Board b, int square) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();
    long movesBitBoard = getBishopMovement( b,
                                            square );
    if (b.isWhitesTurn())
      movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
    else movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

    for (; movesBitBoard != 0; movesBitBoard -= 1L << Long.numberOfTrailingZeros( movesBitBoard )) {
      int i = Long.numberOfTrailingZeros( movesBitBoard );
      Move move;
      if (b.pieceAtSquare( i ).equals( ChessPiece.EMPTY )) {
        move = new Move( square,
                         i,
                         QUITE_MOVE_FLAG );
      } else {
        move = new Move( square,
                         i,
                         CAPTURE_FLAG );
      }
      log.trace( "adding a bishop movement from {} to {} - {}",
                 square,
                 i,
                 move );

      moves.add( move );
    }
    stopwatch.stop(); // optional

    log.debug( "bishop movement took : {}",
               stopwatch );
    return moves;
  }

  private static
  long getBishopMovement(Board b, int square) {
    long binaryS = 1L << square;
    long r = Long.reverse( binaryS );
    long twoR = 2 * r;
    long occupied = b.getOccupiedBitBoard();
    long dMask = diagonalMask[7 + square / 8 - square % 8];
    long adMask = antiDiagonalMask[(square / 8) + (square % 8)];
    long one = ((occupied & dMask) - (2 * binaryS)) ^ Long.reverse( Long.reverse( occupied & dMask ) - (twoR) );
    long two = ((occupied & adMask) - (2 * binaryS)) ^ Long.reverse( Long.reverse( occupied & adMask ) - (twoR) );
    return (one & dMask) | (two & adMask);

  }

  private static
  List<Move> getQueenMoves(Board b, int square) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();

    long movesBitBoard = getQueenMovement( b,
                                           square );

    if (b.isWhitesTurn())
      movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
    else movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

    for (; movesBitBoard != 0; movesBitBoard -= 1L << Long.numberOfTrailingZeros( movesBitBoard )) {
      int i = Long.numberOfTrailingZeros( movesBitBoard );
      Move move;
      if (b.pieceAtSquare( i ).equals( ChessPiece.EMPTY )) {
        move = new Move( square,
                         i,
                         QUITE_MOVE_FLAG );
      } else {
        move = new Move( square,
                         i,
                         CAPTURE_FLAG );
      }
      log.trace( "adding a queen movement from {} to {} - {}",
                 square,
                 i,
                 move );

      moves.add( move );
    }
    stopwatch.stop(); // optional

    log.debug( "queen movement took : {}",
               stopwatch );
    return moves;
  }

  public static
  long getQueenMovement(Board b, int square) {
    return getBishopMovement( b,
                              square ) | getRookMovement( b,
                                                          square );
  }

  private static
  List<Move> getKingMoves(Board b, int square) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> moves = new LinkedList<>();
    long movesBitBoard = getKingMovement( b,
                                          square );
    if ((((b.getWhiteBitBoard() >> square) & 1) == 1)) movesBitBoard &= (b.getBlackBitBoard() | b.getEmptyBitBoard());
    else movesBitBoard &= (b.getWhiteBitBoard() | b.getEmptyBitBoard());

    for (; movesBitBoard != 0; movesBitBoard -= 1L << Long.numberOfTrailingZeros( movesBitBoard )) {
      int i = Long.numberOfTrailingZeros( movesBitBoard );
      Move move;
      if (b.pieceAtSquare( i ).equals( ChessPiece.EMPTY )) {
        move = new Move( square,
                         i,
                         QUITE_MOVE_FLAG );
      } else {
        move = new Move( square,
                         i,
                         CAPTURE_FLAG );
      }
      log.trace( "adding a king movement from {} to {} - {}",
                 square,
                 i,
                 move );

      moves.add( move );
    }
    stopwatch.stop(); // optional

    log.debug( "king movement took : {}",
               stopwatch );
    return moves;
  }

  private static
  long getKingMovement(Board b, int square) {
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

  private static
 List<Move> legalizeMoves(Board b, List<Move> pseudoLegalMoves) {
    Stopwatch stopwatch = Stopwatch.createStarted();
    List<Move> legalMoves = new LinkedList<>();
    for (Move m : pseudoLegalMoves) {
      b.move( m );
      if (b.isWhitesTurn() && isBlackChecked( b )) {
        log.trace( "{} is an illegal move.",
                   m );
      } else if (!b.isWhitesTurn() && isWhiteChecked( b )) {
        log.trace( "{} is an illegal move.",
                   m );
      } else {
        legalMoves.add( m );
      }
      b.undo();
    }
    stopwatch.stop(); // optional

    log.debug( "legalizeMoves took : {}",
               stopwatch );
    return legalMoves;
  }

  private static
  boolean isWhiteChecked(Board b) {
    long attacks = pawnAttackLeft( b,
                                   false ) | pawnAttackRight( b,
                                                              false ) | getBlackMovement( b );
    return isAttackedBy( b.getWhiteKingSquare(),
                         attacks );
  }

  private static
  boolean isBlackChecked(Board b) {
    long attacks = pawnAttackRight( b,
                                    true ) | pawnAttackLeft( b,
                                                             true ) | getWhiteMovement( b );
    return isAttackedBy( b.getBlackKingSquare(),
                         attacks );
  }

  private static
  long getBlackMovement(Board b) {
    Long ans = 0L;
    for (long temp = (b.getBlackRookBitBoard() | b.getBlackBishopBitBoard() | b.getBlackKnightBitBoard()
                      | b.getBlackQueenBitBoard()); temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int square = Long.numberOfTrailingZeros( temp );
      switch (b.pieceAtSquare( square )) {
        case BLACK_ROOK -> ans |= getRookMovement( b,
                                                   square );
        case BLACK_KNIGHT -> ans |= getKnightMovement( b,
                                                       square );
        case BLACK_BISHOP -> ans |= getBishopMovement( b,
                                                       square );
        case BLACK_QUEEN -> ans |= getQueenMovement( b,
                                                     square );
      }
    }
    ans |= getKingMovement( b,
                            Long.numberOfTrailingZeros( b.getBlackKingBitBoard() ) );
    return ans;
  }

  private static
  long getWhiteMovement(Board b) {
    Long ans = 0L;
    for (long temp = (b.getWhiteRookBitBoard() | b.getWhiteBishopBitBoard() | b.getWhiteKnightBitBoard()
                      | b.getWhiteQueenBitBoard()); temp != 0; temp -= 1L << Long.numberOfTrailingZeros( temp )) {
      int square = Long.numberOfTrailingZeros( temp );
      switch (b.pieceAtSquare( square )) {
        case WHITE_ROOK -> ans |= getRookMovement( b,
                                                   square );
        case WHITE_KNIGHT -> ans |= getKnightMovement( b,
                                                       square );
        case WHITE_BISHOP -> ans |= getBishopMovement( b,
                                                       square );
        case WHITE_QUEEN -> ans |= getQueenMovement( b,
                                                     square );
      }
    }
    ans |= getKingMovement( b,
                            Long.numberOfTrailingZeros( b.getWhiteKingBitBoard() ) );
    return ans;
  }


  private static
  boolean isAttackedBy(int square, long attack) {
    return (squares[square] & attack) != 0;
  }


  private static
  String toString(Long test) {
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
        printBoard += " " + String.format( "%02d",
                                           i ) + "|";
      }
      if (i % 8 == 7) printBoard += "\n" + rowString + "\n";
    }
    printBoard += "     a   b   c   d   e   f   g   h \n\n";
    return printBoard;
  }

  /**
   * bitScanForward
   *
   * @param bb bitboard to scan
   * @return index (0..63) of least significant one bit
   * @author Charles E. Leiserson
   * Harald Prokop
   * Keith H. Randall
   * "Using de Bruijn Sequences to Index a 1 in a Computer Word"
   * @precondition bb != 0
   */
  static public
  int bitScanForwardDeBruijn64(long bb) {
    int idx = (int) (((bb & -bb) * deBruijn) >>> 58);
    return magicTable[idx];
  }

  private
  int countBits(long x) {
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

  public
  int log2OfPowerOfTwo(long num)
  {
    return (int) (Math.log( num ) / log2);
  }


}
