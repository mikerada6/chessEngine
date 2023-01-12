package org.rezatron.chess;

public
class BoardHistory {

    private final long whitePawnBitBoard;
    private final long whiteRookBitBoard;
    private final long whiteKnightBitBoard;
    private final long whiteBishopBitBoard;
    private final long whiteQueenBitBoard;
    private final long whiteKingBitBoard;
    private final long blackPawnBitBoard;
    private final long blackRookBitBoard;
    private final long blackKnightBitBoard;
    private final long blackBishopBitBoard;
    private final long blackQueenBitBoard;
    private final long blackKingBitBoard;
    private final boolean whiteKingSideCastle;
    private final boolean whiteQueenSideCastle;
    private final boolean blackKingSideCastle;
    private final boolean blackQueenSideCastle;
    private final long fortyMoveCount;

    private final String enPassantTarget;

    public BoardHistory(long whitePawnBitBoard, long whiteRookBitBoard, long whiteKnightBitBoard, long whiteBishopBitBoard,
                        long whiteQueenBitBoard, long whiteKingBitBoard, long blackPawnBitBoard, long blackRookBitBoard,
                        long blackKnightBitBoard, long blackBishopBitBoard, long blackQueenBitBoard, long blackKingBitBoard,
                        boolean whiteKingSideCastle, boolean whiteQueenSideCastle, boolean blackKingSideCastle,
                        boolean blackQueenSideCastle, long fortyMoveCount, String enPassantTarget) {
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
        this.fortyMoveCount = fortyMoveCount;
        this.enPassantTarget = enPassantTarget;
    }

    public long getWhitePawnBitBoard() {
        return whitePawnBitBoard;
    }

    public long getWhiteRookBitBoard() {
        return whiteRookBitBoard;
    }

    public long getWhiteKnightBitBoard() {
        return whiteKnightBitBoard;
    }

    public long getWhiteBishopBitBoard() {
        return whiteBishopBitBoard;
    }

    public long getWhiteQueenBitBoard() {
        return whiteQueenBitBoard;
    }

    public long getWhiteKingBitBoard() {
        return whiteKingBitBoard;
    }

    public long getBlackPawnBitBoard() {
        return blackPawnBitBoard;
    }

    public long getBlackRookBitBoard() {
        return blackRookBitBoard;
    }

    public long getBlackKnightBitBoard() {
        return blackKnightBitBoard;
    }

    public long getBlackBishopBitBoard() {
        return blackBishopBitBoard;
    }

    public long getBlackQueenBitBoard() {
        return blackQueenBitBoard;
    }

    public long getBlackKingBitBoard() {
        return blackKingBitBoard;
    }

    public boolean isWhiteKingSideCastle() {
        return whiteKingSideCastle;
    }

    public boolean isWhiteQueenSideCastle() {
        return whiteQueenSideCastle;
    }

    public boolean isBlackKingSideCastle() {
        return blackKingSideCastle;
    }

    public boolean isBlackQueenSideCastle() {
        return blackQueenSideCastle;
    }

    public long getFortyMoveCount() {
        return fortyMoveCount;
    }

    public String getEnPassantTarget() {
        return enPassantTarget;
    }
}
