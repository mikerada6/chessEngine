package org.rezatron.chess;

public
class BoardHistory {

  private long whitePawnBitBoard;
  private long whiteRookBitBoard;
  private long whiteKnightBitBoard;
  private long whiteBishopBitBoard;
  private long whiteQueenBitBoard;
  private long whiteKingBitBoard;
  private long blackPawnBitBoard;
  private long blackRookBitBoard;
  private long blackKnightBitBoard;
  private long blackBishopBitBoard;
  private long blackQueenBitBoard;
  private long blackKingBitBoard;
  private boolean whiteKingSideCastle;
  private boolean whiteQueenSideCastle;
  private boolean blackKingSideCastle;
  private boolean blackQueenSideCastle;
  private long fortyMoveCount;

  private String enPassantTarget;

  public
  BoardHistory(long whitePawnBitBoard, long whiteRookBitBoard, long whiteKnightBitBoard, long whiteBishopBitBoard,
               long whiteQueenBitBoard, long whiteKingBitBoard, long blackPawnBitBoard, long blackRookBitBoard,
               long blackKnightBitBoard, long blackBishopBitBoard, long blackQueenBitBoard, long blackKingBitBoard,
               boolean whiteKingSideCastle, boolean whiteQueenSideCastle, boolean blackKingSideCastle,
               boolean blackQueenSideCastle, long fortyMoveCount, String enPassantTarget)
  {
    this.whitePawnBitBoard = whitePawnBitBoard;
    this.whiteRookBitBoard = whiteRookBitBoard;
    this.whiteKnightBitBoard = whiteKnightBitBoard;
    this.whiteBishopBitBoard = whiteBishopBitBoard;
    this.whiteQueenBitBoard = whiteQueenBitBoard;
    this.whiteKingBitBoard = whiteKingBitBoard;
    this.blackPawnBitBoard = blackPawnBitBoard;
    this.blackRookBitBoard = blackRookBitBoard;
    this.blackKnightBitBoard = blackKnightBitBoard;
    this.blackBishopBitBoard = blackBishopBitBoard;
    this.blackQueenBitBoard = blackQueenBitBoard;
    this.blackKingBitBoard = blackKingBitBoard;
    this.whiteKingSideCastle = whiteKingSideCastle;
    this.whiteQueenSideCastle = whiteQueenSideCastle;
    this.blackKingSideCastle = blackKingSideCastle;
    this.blackQueenSideCastle = blackQueenSideCastle;
    this.fortyMoveCount=fortyMoveCount;
    this.enPassantTarget = enPassantTarget;
  }

  public
  long getWhitePawnBitBoard() {
    return whitePawnBitBoard;
  }

  public
  long getWhiteRookBitBoard() {
    return whiteRookBitBoard;
  }

  public
  long getWhiteKnightBitBoard() {
    return whiteKnightBitBoard;
  }

  public
  long getWhiteBishopBitBoard() {
    return whiteBishopBitBoard;
  }

  public
  long getWhiteQueenBitBoard() {
    return whiteQueenBitBoard;
  }

  public
  long getWhiteKingBitBoard() {
    return whiteKingBitBoard;
  }

  public
  long getBlackPawnBitBoard() {
    return blackPawnBitBoard;
  }

  public
  long getBlackRookBitBoard() {
    return blackRookBitBoard;
  }

  public
  long getBlackKnightBitBoard() {
    return blackKnightBitBoard;
  }

  public
  long getBlackBishopBitBoard() {
    return blackBishopBitBoard;
  }

  public
  long getBlackQueenBitBoard() {
    return blackQueenBitBoard;
  }

  public
  long getBlackKingBitBoard() {
    return blackKingBitBoard;
  }

  public
  boolean isWhiteKingSideCastle() {
    return whiteKingSideCastle;
  }

  public
  boolean isWhiteQueenSideCastle() {
    return whiteQueenSideCastle;
  }

  public
  boolean isBlackKingSideCastle() {
    return blackKingSideCastle;
  }

  public
  boolean isBlackQueenSideCastle() {
    return blackQueenSideCastle;
  }

  public
  long getFortyMoveCount() {
    return fortyMoveCount;
  }

  public
  String getEnPassantTarget() {
    return enPassantTarget;
  }
}
