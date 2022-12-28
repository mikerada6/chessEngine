package org.rezatron.chess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.constants.ChessPiece;


import java.util.ArrayList;

public class Board {

    private static final Logger log = LogManager.getLogger(Board.class);

    private String enPassantTarget;
    private ArrayList<Long> history;
    private ArrayList<String> enPassantTargetHistory;
    private int[] moves;


    private long whitePawnBitBoard, whiteRookBitBoard, whiteKnightBitBoard, whiteBishopBitBoard, whiteKingBitBoard, whiteQueenBitBoard, blackRookBitBoard, blackKnightBitBoard, blackBishopBitBoard, blackKingBitBoard, blackQueenBitBoard, blackPawnBitBoard;
    private boolean bkc, bqc, wkc, wqc;
    private long fortyMoveCount;
    private boolean isWhitesTurn;
    private int moveCount;

    private static final int[] order = {56, 57, 58, 59, 60, 61, 62, 63, 48,
            49, 50, 51, 52, 53, 54, 55, 40, 41, 42, 43, 44, 45, 46, 47, 32, 33,
            34, 35, 36, 37, 38, 39, 24, 25, 26, 27, 28, 29, 30, 31, 16, 17, 18,
            19, 20, 21, 22, 23, 8, 9, 10, 11, 12, 13, 14, 15, 0, 1, 2, 3, 4, 5,
            6, 7};

    String[] letterSquares = {"a1", "b1", "c1", "d1", "e1", "f1", "g1",
            "h1", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", "a3",
            "b3", "c3", "d3", "e3", "f3", "g3", "h3", "a4", "b4", "c4",
            "d4", "e4", "f4", "g4", "h4", "a5", "b5", "c5", "d5", "e5",
            "f5", "g5", "h5", "a6", "b6", "c6", "d6", "e6", "f6", "g6",
            "h6", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7", "a8",
            "b8", "c8", "d8", "e8", "f8", "g8", "h8"};

    private static final long[] squares = {1L, 2L, 4L, 8L, 16L, 32L, 64L,
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

    public Board() {
        log.info("new  board");
        enPassantTarget = "-";
        history = new ArrayList<>();
        enPassantTargetHistory = new ArrayList<>();
        moves = new int[6300];
        whitePawnBitBoard = 65280L;
        whiteRookBitBoard = 129L;
        whiteKnightBitBoard = 66L;
        whiteBishopBitBoard = 36L;
        whiteQueenBitBoard = 8L;
        whiteKingBitBoard = 16L;

        blackPawnBitBoard = 71776119061217280L;
        blackRookBitBoard = -9151314442816847872L;
        blackKnightBitBoard = 4755801206503243776L;
        blackBishopBitBoard = 2594073385365405696L;
        blackQueenBitBoard = 576460752303423488L;
        blackKingBitBoard = 1152921504606846976L;

        bkc = bqc = wkc = wqc = true;
        fortyMoveCount = 0;
        isWhitesTurn = true;
        // moves = "";
        moveCount = 0;
        updateHistory();
        moveCount = 1;
    }

    public Board(String fenString) {
        enPassantTarget = "-";
        // not chess960 compatible

        history = new ArrayList<>();
        enPassantTargetHistory = new ArrayList<>();
        moves = new int[6300];
        whitePawnBitBoard = 0;
        whiteRookBitBoard = 0;
        whiteKnightBitBoard = 0;
        whiteBishopBitBoard = 0;
        whiteQueenBitBoard = 0;
        whiteKingBitBoard = 0;

        blackPawnBitBoard = 0;
        blackRookBitBoard = 0;
        blackKnightBitBoard = 0;
        blackBishopBitBoard = 0;
        blackQueenBitBoard = 0;
        blackKingBitBoard = 0;

        wkc = false;
        wqc = false;
        bkc = false;
        bqc = false;
        int charIndex = 0;
        int boardIndex = 0;
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case 'P':
                    whitePawnBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'p':
                    blackPawnBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'N':
                    whiteKnightBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'n':
                    blackKnightBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'B':
                    whiteBishopBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'b':
                    blackBishopBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'R':
                    whiteRookBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'r':
                    blackRookBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'Q':
                    whiteQueenBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'q':
                    blackQueenBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'K':
                    whiteKingBitBoard |= squares[order[boardIndex++]];
                    break;
                case 'k':
                    blackKingBitBoard |= squares[order[boardIndex++]];
                    break;
                case '/':
                    break;
                case '1':
                    boardIndex++;
                    break;
                case '2':
                    boardIndex += 2;
                    break;
                case '3':
                    boardIndex += 3;
                    break;
                case '4':
                    boardIndex += 4;
                    break;
                case '5':
                    boardIndex += 5;
                    break;
                case '6':
                    boardIndex += 6;
                    break;
                case '7':
                    boardIndex += 7;
                    break;
                case '8':
                    boardIndex += 8;
                    break;
                default:
                    break;
            }
        }
        isWhitesTurn = fenString.charAt(++charIndex) == 'w';
        charIndex += 2;
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case '-':
                    break;
                case 'K':
                    wkc = true;
                    break;
                case 'Q':
                    wqc = true;
                    break;
                case 'k':
                    bkc = true;
                    break;
                case 'q':
                    bqc = true;
                    break;
                default:
                    break;
            }
        }
        if (pieceAtSquare(0) != ChessPiece.WHITE_ROOK)
            wqc = false;
        if (pieceAtSquare(7) != ChessPiece.WHITE_ROOK)
            wkc = false;
        if (pieceAtSquare(56) != ChessPiece.BLACK_ROOK)
            bqc = false;
        if (pieceAtSquare(63) != ChessPiece.BLACK_ROOK)
            bkc = false;
        enPassantTarget = "-";
        if (fenString.charAt(++charIndex) != '-') {
            enPassantTarget = fenString.substring(charIndex, charIndex + 2);
        }

        // the rest of the fenString is not yet utilized
        // moves = "";
        moveCount = 0;
        updateHistory();
        moveCount = 1;
    }

    private void updateHistory() {
        /*
         * history[moveCount] = wp + "," + wr + "," + wn + "," + wb + "," + wq +
         * "," + wk + "," + bp + "," + br + "," + bn + "," + bb + "," + bq + ","
         * + bk + "," + wkc + "," + wqc + "," + bkc + "," + bqc + "," +
         * enPassantTarget + "," + fortyMoveCount;
         */
        history.add(whitePawnBitBoard);
        history.add(whiteRookBitBoard);
        history.add(whiteKnightBitBoard);
        history.add(whiteBishopBitBoard);
        history.add(whiteQueenBitBoard);
        history.add(whiteKingBitBoard);
        history.add(blackPawnBitBoard);
        history.add(blackRookBitBoard);
        history.add(blackKnightBitBoard);
        history.add(blackBishopBitBoard);
        history.add(blackQueenBitBoard);
        history.add(blackKingBitBoard);
        history.add((wkc ? 1L : 0L));
        history.add((wqc ? 1L : 0L));
        history.add((bkc ? 1L : 0L));
        history.add((bqc ? 1L : 0L));
        enPassantTargetHistory.add(enPassantTarget);
        history.add(fortyMoveCount);

    }

    public String toString() {
        String rowString = "   +---+---+---+---+---+---+---+---+";
        String printBoard;
        int row = 8;
        StringBuilder printBoardBuilder = new StringBuilder(rowString + "\n");
        for (int i : order) {
            if (i % 8 == 0) {
                printBoardBuilder.append(row).append("  |");
                row--;
            }

            if (((whitePawnBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("(P)" + "|");
            } else if (((whiteRookBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("(R)" + "|");
            } else if (((whiteKnightBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("(N)" + "|");
            } else if (((whiteBishopBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("(B)" + "|");
            } else if (((whiteQueenBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("(Q)" + "|");
            } else if (((whiteKingBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("(K)" + "|");
            } else if (((blackPawnBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("*p*" + "|");
            } else if (((blackRookBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("*r*" + "|");
            } else if (((blackKnightBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("*n*" + "|");
            } else if (((blackBishopBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("*b*" + "|");
            } else if (((blackQueenBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("*q*" + "|");
            } else if (((blackKingBitBoard >> i) & 1) == 1) {
                printBoardBuilder.append("*k*" + "|");
            } else {
                printBoardBuilder.append("   " + "|");
            }
            if (i % 8 == 7)
                printBoardBuilder.append("\n").append(rowString).append("\n");
        }
        printBoard = printBoardBuilder.toString();
        printBoard += "     a   b   c   d   e   f   g   h";
        return printBoard;
    }

    public ChessPiece pieceAtSquare(int square) {
        if (((whitePawnBitBoard >> square) & 1) == 1)
            return ChessPiece.WHITE_PAWN;
        if (((whiteRookBitBoard >> square) & 1) == 1)
            return ChessPiece.WHITE_ROOK;
        if (((whiteKnightBitBoard >> square) & 1) == 1)
            return ChessPiece.WHITE_KNIGHT;
        if (((whiteBishopBitBoard >> square) & 1) == 1)
            return ChessPiece.WHITE_BISHOP;
        if (((whiteQueenBitBoard >> square) & 1) == 1)
            return ChessPiece.WHITE_QUEEN;
        if (((whiteKingBitBoard >> square) & 1) == 1)
            return ChessPiece.WHITE_KING;

        if (((blackPawnBitBoard >> square) & 1) == 1)
            return ChessPiece.BLACK_PAWN;
        if (((blackRookBitBoard >> square) & 1) == 1)
            return ChessPiece.BLACK_ROOK;
        if (((blackKnightBitBoard >> square) & 1) == 1)
            return ChessPiece.BLACK_KNIGHT;
        if (((blackBishopBitBoard >> square) & 1) == 1)
            return ChessPiece.BLACK_BISHOP;
        if (((blackQueenBitBoard >> square) & 1) == 1)
            return ChessPiece.BLACK_QUEEN;
        if ((blackKingBitBoard >> square & 1) == 1)
            return ChessPiece.BLACK_KING;
        return ChessPiece.EMPTY;
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

    public long getWhiteKingBitBoard() {
        return whiteKingBitBoard;
    }

    public long getWhiteQueenBitBoard() {
        return whiteQueenBitBoard;
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

    public long getBlackKingBitBoard() {
        return blackKingBitBoard;
    }

    public long getBlackQueenBitBoard() {
        return blackQueenBitBoard;
    }

    public long getBlackPawnBitBoard() {
        return blackPawnBitBoard;
    }
}
