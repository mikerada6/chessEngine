package org.rezatron.chess;

import junit.framework.TestCase;

import java.util.List;

import static org.rezatron.chess.constants.ChessPiece.*;
import static org.rezatron.chess.constants.MoveFlags.DOUBLE_PAWN_PUSH_FLAG;
import static org.rezatron.chess.constants.MoveFlags.QUITE_MOVE_FLAG;

public
class BoardTest extends TestCase {


  public
  void testNewBoard() {

    Board b = new Board();
    String boardString = """
         +---+---+---+---+---+---+---+---+
      8  |*r*|*n*|*b*|*q*|*k*|*b*|*n*|*r*|
         +---+---+---+---+---+---+---+---+
      7  |*p*|*p*|*p*|*p*|*p*|*p*|*p*|*p*|
         +---+---+---+---+---+---+---+---+
      6  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      5  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      4  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      3  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      2  |(P)|(P)|(P)|(P)|(P)|(P)|(P)|(P)|
         +---+---+---+---+---+---+---+---+
      1  |(R)|(N)|(B)|(Q)|(K)|(B)|(N)|(R)|
         +---+---+---+---+---+---+---+---+
           a   b   c   d   e   f   g   h""";


    assertEquals( boardString,
                  b.toString() );

    assertEquals( 65280L,
                  b.getWhitePawnBitBoard() );
    assertEquals( 129L,
                  b.getWhiteRookBitBoard() );
    assertEquals( 66L,
                  b.getWhiteKnightBitBoard() );
    assertEquals( 36L,
                  b.getWhiteBishopBitBoard() );
    assertEquals( 8L,
                  b.getWhiteQueenBitBoard() );
    assertEquals( 16L,
                  b.getWhiteKingBitBoard() );

    assertEquals( 65535L,
                  b.getWhiteBitBoard() );

    assertEquals( 71776119061217280L,
                  b.getBlackPawnBitBoard() );
    assertEquals( -9151314442816847872L,
                  b.getBlackRookBitBoard() );
    assertEquals( 4755801206503243776L,
                  b.getBlackKnightBitBoard() );
    assertEquals( 2594073385365405696L,
                  b.getBlackBishopBitBoard() );
    assertEquals( 576460752303423488L,
                  b.getBlackQueenBitBoard() );
    assertEquals( 1152921504606846976L,
                  b.getBlackKingBitBoard() );

    assertEquals( -281474976710656L,
                  b.getBlackBitBoard() );

    assertEquals( -281474976645121L,
                  b.getOccupiedBitBoard() );

    assertEquals( 281474976645120L,
                  b.getEmptyBitBoard() );

    assertEquals( WHITE_ROOK,
                  b.pieceAtSquare( 0 ) );
    assertEquals( WHITE_KNIGHT,
                  b.pieceAtSquare( 1 ) );
    assertEquals( WHITE_BISHOP,
                  b.pieceAtSquare( 2 ) );
    assertEquals( WHITE_QUEEN,
                  b.pieceAtSquare( 3 ) );
    assertEquals( WHITE_KING,
                  b.pieceAtSquare( 4 ) );
    assertEquals( WHITE_BISHOP,
                  b.pieceAtSquare( 5 ) );
    assertEquals( WHITE_KNIGHT,
                  b.pieceAtSquare( 6 ) );
    assertEquals( WHITE_ROOK,
                  b.pieceAtSquare( 7 ) );

    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 8 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 9 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 10 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 11 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 12 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 13 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 14 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 15 ) );

    for (int i = 16; i < 48; i++) {
      assertEquals( EMPTY,
                    b.pieceAtSquare( i ) );
    }


    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 48 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 49 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 50 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 51 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 52 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 53 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 54 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 55 ) );


    assertEquals( BLACK_ROOK,
                  b.pieceAtSquare( 56 ) );
    assertEquals( BLACK_KNIGHT,
                  b.pieceAtSquare( 57 ) );
    assertEquals( BLACK_BISHOP,
                  b.pieceAtSquare( 58 ) );
    assertEquals( BLACK_QUEEN,
                  b.pieceAtSquare( 59 ) );
    assertEquals( BLACK_KING,
                  b.pieceAtSquare( 60 ) );
    assertEquals( BLACK_BISHOP,
                  b.pieceAtSquare( 61 ) );
    assertEquals( BLACK_KNIGHT,
                  b.pieceAtSquare( 62 ) );
    assertEquals( BLACK_ROOK,
                  b.pieceAtSquare( 63 ) );

    assertEquals( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
                  b.getFEN() );

    assertTrue( b.isWhitesTurn() );
  }

  public
  void testNewFen1String() {

    Board b = new Board( "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - " );
    String boardString = """
         +---+---+---+---+---+---+---+---+
      8  |*r*|   |   |   |*k*|   |   |*r*|
         +---+---+---+---+---+---+---+---+
      7  |*p*|   |*p*|*p*|*q*|*p*|*b*|   |
         +---+---+---+---+---+---+---+---+
      6  |*b*|*n*|   |   |*p*|*n*|*p*|   |
         +---+---+---+---+---+---+---+---+
      5  |   |   |   |(P)|(N)|   |   |   |
         +---+---+---+---+---+---+---+---+
      4  |   |*p*|   |   |(P)|   |   |   |
         +---+---+---+---+---+---+---+---+
      3  |   |   |(N)|   |   |(Q)|   |*p*|
         +---+---+---+---+---+---+---+---+
      2  |(P)|(P)|(P)|(B)|(B)|(P)|(P)|(P)|
         +---+---+---+---+---+---+---+---+
      1  |(R)|   |   |   |(K)|   |   |(R)|
         +---+---+---+---+---+---+---+---+
           a   b   c   d   e   f   g   h""";


    assertEquals( boardString,
                  b.toString() );

    assertEquals( 34628232960L,
                  b.getWhitePawnBitBoard() );
    assertEquals( 129L,
                  b.getWhiteRookBitBoard() );
    assertEquals( 68719738880L,
                  b.getWhiteKnightBitBoard() );
    assertEquals( 6144L,
                  b.getWhiteBishopBitBoard() );
    assertEquals( 2097152L,
                  b.getWhiteQueenBitBoard() );
    assertEquals( 16L,
                  b.getWhiteKingBitBoard() );

    assertEquals( 12754334924144640L,
                  b.getBlackPawnBitBoard() );
    assertEquals( -9151314442816847872L,
                  b.getBlackRookBitBoard() );
    assertEquals( 37383395344384L,
                  b.getBlackKnightBitBoard() );
    assertEquals( 18015498021109760L,
                  b.getBlackBishopBitBoard() );
    assertEquals( 4503599627370496L,
                  b.getBlackQueenBitBoard() );
    assertEquals( 1152921504606846976L,
                  b.getBlackKingBitBoard() );

    assertEquals( "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
                  b.getFEN() );
  }

  public
  void testNewFen2String() {

    Board b = new Board( "8/8/8/8/8/8/1k6/R3K3 w Q - 0 1" );
    String boardString = """
         +---+---+---+---+---+---+---+---+
      8  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      7  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      6  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      5  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      4  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      3  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      2  |   |*k*|   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      1  |(R)|   |   |   |(K)|   |   |   |
         +---+---+---+---+---+---+---+---+
           a   b   c   d   e   f   g   h""";


    assertEquals( boardString,
                  b.toString() );

    assertEquals( 0,
                  b.getWhitePawnBitBoard() );
    assertEquals( 1L,
                  b.getWhiteRookBitBoard() );
    assertEquals( 0L,
                  b.getWhiteKnightBitBoard() );
    assertEquals( 0,
                  b.getWhiteBishopBitBoard() );
    assertEquals( 0L,
                  b.getWhiteQueenBitBoard() );
    assertEquals( 16L,
                  b.getWhiteKingBitBoard() );

    assertEquals( 0,
                  b.getBlackPawnBitBoard() );
    assertEquals( 0,
                  b.getBlackRookBitBoard() );
    assertEquals( 0,
                  b.getBlackKnightBitBoard() );
    assertEquals( 0,
                  b.getBlackBishopBitBoard() );
    assertEquals( 0,
                  b.getBlackQueenBitBoard() );
    assertEquals( 512,
                  b.getBlackKingBitBoard() );

    assertEquals( "8/8/8/8/8/8/1k6/R3K3 w Q - 0 1",
                  b.getFEN() );
  }

  public
  void testMove1() {
    Board b = new Board();
    Move m = new Move( 8,
                       24,
                       DOUBLE_PAWN_PUSH_FLAG );

    assertTrue( b.isWhitesTurn() );
    b.move( m );
    String boardString = """
         +---+---+---+---+---+---+---+---+
      8  |*r*|*n*|*b*|*q*|*k*|*b*|*n*|*r*|
         +---+---+---+---+---+---+---+---+
      7  |*p*|*p*|*p*|*p*|*p*|*p*|*p*|*p*|
         +---+---+---+---+---+---+---+---+
      6  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      5  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      4  |(P)|   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      3  |   |   |   |   |   |   |   |   |
         +---+---+---+---+---+---+---+---+
      2  |   |(P)|(P)|(P)|(P)|(P)|(P)|(P)|
         +---+---+---+---+---+---+---+---+
      1  |(R)|(N)|(B)|(Q)|(K)|(B)|(N)|(R)|
         +---+---+---+---+---+---+---+---+
           a   b   c   d   e   f   g   h""";


    assertEquals( boardString,
                  b.toString() );


    assertEquals( 16842240L,
                  b.getWhitePawnBitBoard() );
    assertEquals( 129L,
                  b.getWhiteRookBitBoard() );
    assertEquals( 66L,
                  b.getWhiteKnightBitBoard() );
    assertEquals( 36L,
                  b.getWhiteBishopBitBoard() );
    assertEquals( 8L,
                  b.getWhiteQueenBitBoard() );
    assertEquals( 16L,
                  b.getWhiteKingBitBoard() );

    assertEquals( 71776119061217280L,
                  b.getBlackPawnBitBoard() );
    assertEquals( -9151314442816847872L,
                  b.getBlackRookBitBoard() );
    assertEquals( 4755801206503243776L,
                  b.getBlackKnightBitBoard() );
    assertEquals( 2594073385365405696L,
                  b.getBlackBishopBitBoard() );
    assertEquals( 576460752303423488L,
                  b.getBlackQueenBitBoard() );
    assertEquals( 1152921504606846976L,
                  b.getBlackKingBitBoard() );

    assertEquals( WHITE_ROOK,
                  b.pieceAtSquare( 0 ) );
    assertEquals( WHITE_KNIGHT,
                  b.pieceAtSquare( 1 ) );
    assertEquals( WHITE_BISHOP,
                  b.pieceAtSquare( 2 ) );
    assertEquals( WHITE_QUEEN,
                  b.pieceAtSquare( 3 ) );
    assertEquals( WHITE_KING,
                  b.pieceAtSquare( 4 ) );
    assertEquals( WHITE_BISHOP,
                  b.pieceAtSquare( 5 ) );
    assertEquals( WHITE_KNIGHT,
                  b.pieceAtSquare( 6 ) );
    assertEquals( WHITE_ROOK,
                  b.pieceAtSquare( 7 ) );

    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 24 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 9 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 10 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 11 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 12 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 13 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 14 ) );
    assertEquals( WHITE_PAWN,
                  b.pieceAtSquare( 15 ) );

    for (int i = 16; i < 48; i++) {
      if (i == 24) {
        //look at the old square where the pawn was originally
        assertEquals( EMPTY,
                      b.pieceAtSquare( 8 ) );
      } else {
        assertEquals( EMPTY,
                      b.pieceAtSquare( i ) );
      }
    }


    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 48 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 49 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 50 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 51 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 52 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 53 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 54 ) );
    assertEquals( BLACK_PAWN,
                  b.pieceAtSquare( 55 ) );


    assertEquals( BLACK_ROOK,
                  b.pieceAtSquare( 56 ) );
    assertEquals( BLACK_KNIGHT,
                  b.pieceAtSquare( 57 ) );
    assertEquals( BLACK_BISHOP,
                  b.pieceAtSquare( 58 ) );
    assertEquals( BLACK_QUEEN,
                  b.pieceAtSquare( 59 ) );
    assertEquals( BLACK_KING,
                  b.pieceAtSquare( 60 ) );
    assertEquals( BLACK_BISHOP,
                  b.pieceAtSquare( 61 ) );
    assertEquals( BLACK_KNIGHT,
                  b.pieceAtSquare( 62 ) );
    assertEquals( BLACK_ROOK,
                  b.pieceAtSquare( 63 ) );

    assertFalse( b.isWhitesTurn() );
  }

  public
  void testMove2() {
    Board b = new Board( "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - " );
    String before = b.toString();
    MoveGenerator mg = new MoveGenerator( b );
    List<Move> moves = mg.getWhiteMoves();
    b.move( new Move( 11,
                      20,
                      QUITE_MOVE_FLAG ) );
    String newBoard = """
         +---+---+---+---+---+---+---+---+
      8  |*r*|   |   |   |*k*|   |   |*r*|
         +---+---+---+---+---+---+---+---+
      7  |*p*|   |*p*|*p*|*q*|*p*|*b*|   |
         +---+---+---+---+---+---+---+---+
      6  |*b*|*n*|   |   |*p*|*n*|*p*|   |
         +---+---+---+---+---+---+---+---+
      5  |   |   |   |(P)|(N)|   |   |   |
         +---+---+---+---+---+---+---+---+
      4  |   |*p*|   |   |(P)|   |   |   |
         +---+---+---+---+---+---+---+---+
      3  |   |   |(N)|   |(B)|(Q)|   |*p*|
         +---+---+---+---+---+---+---+---+
      2  |(P)|(P)|(P)|   |(B)|(P)|(P)|(P)|
         +---+---+---+---+---+---+---+---+
      1  |(R)|   |   |   |(K)|   |   |(R)|
         +---+---+---+---+---+---+---+---+
           a   b   c   d   e   f   g   h""";


    assertEquals( newBoard,
                  b.toString() );
    assertEquals( "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N1BQ1p/PPP1BPPP/R3K2R b KQkq - 0 1",
                  b.getFEN() );

    b.undo();

    assertEquals( before,
                  b.toString() );
  }

}
