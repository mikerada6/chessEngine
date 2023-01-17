package org.rezatron.chess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.constants.ChessPiece;

import java.util.Arrays;

import static org.rezatron.chess.constants.ChessConstants.*;
import static org.rezatron.chess.constants.MoveFlags.*;

public
class Board {

    private static final Logger log = LogManager.getLogger(Board.class);
    private final long[] bitboards = new long[16];
    private String enPassantTarget;
    private boolean blackKingSideCastle, blackQueenSideCastle, whiteKingSideCastle, whiteQueenSideCastle;
    private long fortyMoveCount;
    private boolean isWhitesTurn;
    private int moveCount;

    private final Board parentBoard;


    public Board() {
        log.info("new  board");
        enPassantTarget = "-";
        bitboards[whitePawnBitBoard] = 65280L;
        bitboards[whiteRookBitBoard] = 129L;
        bitboards[whiteKnightBitBoard] = 66L;
        bitboards[whiteBishopBitBoard] = 36L;
        bitboards[whiteQueenBitBoard] = 8L;
        bitboards[whiteKingBitBoard] = 16L;

        bitboards[blackPawnBitBoard] = 71776119061217280L;
        bitboards[blackRookBitBoard] = -9151314442816847872L;
        bitboards[blackKnightBitBoard] = 4755801206503243776L;
        bitboards[blackBishopBitBoard] = 2594073385365405696L;
        bitboards[blackQueenBitBoard] = 576460752303423488L;
        bitboards[blackKingBitBoard] = 1152921504606846976L;

        blackKingSideCastle = blackQueenSideCastle = whiteKingSideCastle = whiteQueenSideCastle = true;
        fortyMoveCount = 0;
        isWhitesTurn = true;

        updateGlobalBitBoards();
        moveCount++;
        parentBoard=null;
    }



    public Board(Board b) {
//        log.info("new  board from  another board");
        enPassantTarget = "-";
        bitboards[whitePawnBitBoard] = b.getWhitePawnBitBoard();
        bitboards[whiteRookBitBoard] = b.getWhiteRookBitBoard();
        bitboards[whiteKnightBitBoard] = b.getWhiteKnightBitBoard();
        bitboards[whiteBishopBitBoard] = b.getWhiteBishopBitBoard();
        bitboards[whiteQueenBitBoard] = b.getWhiteQueenBitBoard();
        bitboards[whiteKingBitBoard] = b.getWhiteKingBitBoard();

        bitboards[blackPawnBitBoard] = b.getBlackPawnBitBoard();
        bitboards[blackRookBitBoard] = b.getBlackRookBitBoard();
        bitboards[blackKnightBitBoard] = b.getBlackKnightBitBoard();
        bitboards[blackBishopBitBoard] = b.getBlackBishopBitBoard();
        bitboards[blackQueenBitBoard] = b.getBlackQueenBitBoard();
        bitboards[blackKingBitBoard] = b.getBlackKingBitBoard();

        whiteKingSideCastle = b.canWhiteKingSideCastle();
        whiteQueenSideCastle = b.canWhiteQueenSideCastle();
        blackKingSideCastle = b.canBlackKingSideCastle();
        blackQueenSideCastle = b.canBlackQueenSideCastle();

        fortyMoveCount = 0;
        isWhitesTurn = b.isWhitesTurn;
        moveCount=b.getMoveCount();

        updateGlobalBitBoards();
        moveCount++;
        parentBoard=b;
    }

    public Board(String fenString) {
        enPassantTarget = "-";
        // not chess960 compatible
        bitboards[whitePawnBitBoard] = 0L;
        bitboards[whiteRookBitBoard] = 0L;
        bitboards[whiteKnightBitBoard] = 0L;
        bitboards[whiteBishopBitBoard] = 0L;
        bitboards[whiteQueenBitBoard] = 0L;
        bitboards[whiteKingBitBoard] = 0L;

        bitboards[blackPawnBitBoard] = 0L;
        bitboards[blackRookBitBoard] = 0L;
        bitboards[blackKnightBitBoard] = 0L;
        bitboards[blackBishopBitBoard] = 0L;
        bitboards[blackQueenBitBoard] = 0L;
        bitboards[blackKingBitBoard] = 0L;

        whiteKingSideCastle = false;
        whiteQueenSideCastle = false;
        blackKingSideCastle = false;
        blackQueenSideCastle = false;
        int charIndex = 0;
        int boardIndex = 0;
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case 'P' -> bitboards[whitePawnBitBoard] |= squares[order[boardIndex++]];
                case 'p' -> bitboards[blackPawnBitBoard] |= squares[order[boardIndex++]];
                case 'N' -> bitboards[whiteKnightBitBoard] |= squares[order[boardIndex++]];
                case 'n' -> bitboards[blackKnightBitBoard] |= squares[order[boardIndex++]];
                case 'B' -> bitboards[whiteBishopBitBoard] |= squares[order[boardIndex++]];
                case 'b' -> bitboards[blackBishopBitBoard] |= squares[order[boardIndex++]];
                case 'R' -> bitboards[whiteRookBitBoard] |= squares[order[boardIndex++]];
                case 'r' -> bitboards[blackRookBitBoard] |= squares[order[boardIndex++]];
                case 'Q' -> bitboards[whiteQueenBitBoard] |= squares[order[boardIndex++]];
                case 'q' -> bitboards[blackQueenBitBoard] |= squares[order[boardIndex++]];
                case 'K' -> bitboards[whiteKingBitBoard] |= squares[order[boardIndex++]];
                case 'k' -> bitboards[blackKingBitBoard] |= squares[order[boardIndex++]];
                case '1' -> boardIndex++;
                case '2' -> boardIndex += 2;
                case '3' -> boardIndex += 3;
                case '4' -> boardIndex += 4;
                case '5' -> boardIndex += 5;
                case '6' -> boardIndex += 6;
                case '7' -> boardIndex += 7;
                case '8' -> boardIndex += 8;
                default -> {
                }
            }
        }
        isWhitesTurn = fenString.charAt(++charIndex) == 'w';
        charIndex += 2;
        while (fenString.charAt(charIndex) != ' ') {
            switch (fenString.charAt(charIndex++)) {
                case 'K' -> whiteKingSideCastle = true;
                case 'Q' -> whiteQueenSideCastle = true;
                case 'k' -> blackKingSideCastle = true;
                case 'q' -> blackQueenSideCastle = true;
                default -> {
                }
            }
        }

        updateGlobalBitBoards();

        if (pieceAtSquare(0) != ChessPiece.WHITE_ROOK) whiteQueenSideCastle = false;
        if (pieceAtSquare(7) != ChessPiece.WHITE_ROOK) whiteKingSideCastle = false;
        if (pieceAtSquare(56) != ChessPiece.BLACK_ROOK) blackQueenSideCastle = false;
        if (pieceAtSquare(63) != ChessPiece.BLACK_ROOK) blackKingSideCastle = false;
        enPassantTarget = "-";
        if (fenString.charAt(++charIndex) != '-') {
            enPassantTarget = fenString.substring(charIndex,
                    charIndex + 2);
        }
        moveCount++;
        parentBoard=null;
    }

    private void updateGlobalBitBoards() {
        bitboards[whiteBitBoard] = bitboards[whitePawnBitBoard] |
                bitboards[whiteRookBitBoard] |
                bitboards[whiteKnightBitBoard] |
                bitboards[whiteBishopBitBoard] |
                bitboards[whiteQueenBitBoard] |
                bitboards[whiteKingBitBoard];
        bitboards[blackBitBoard] = bitboards[blackPawnBitBoard] |
                bitboards[blackRookBitBoard] |
                bitboards[blackKnightBitBoard] |
                bitboards[blackBishopBitBoard] |
                bitboards[blackQueenBitBoard] |
                bitboards[blackKingBitBoard];
        bitboards[occupiedBitBoard] = bitboards[whiteBitBoard] | bitboards[blackBitBoard];
        bitboards[emptyBitBoard] = ~bitboards[occupiedBitBoard];
    }


    public void move(Move move) {
        moveCount++;
        int from = move.getFrom();
        int to = move.getTo();
        enPassantTarget = "-";
        int flag = move.getFlags();
        ChessPiece fromPiece = pieceAtSquare(from);
        ChessPiece toPiece = pieceAtSquare(to);


        if (flag == QUITE_MOVE_FLAG.getFlag()) {
            bitboards[fromPiece.getBitBoardIndex()] += (squares[to] - squares[from]);
        } else if (flag == DOUBLE_PAWN_PUSH_FLAG.getFlag()) {
            bitboards[fromPiece.getBitBoardIndex()] += (squares[to] - squares[from]);
            if (fromPiece == ChessPiece.WHITE_PAWN) {
                enPassantTarget = letterSquares[from + 8];
            } else {
                enPassantTarget = letterSquares[from - 8];
            }
            fortyMoveCount = 0;
        } else if (move.isCapture()) {
            bitboards[fromPiece.getBitBoardIndex()] += (squares[to] - squares[from]);
            bitboards[toPiece.getBitBoardIndex()] -= (squares[to]);
            fortyMoveCount = 0;
            if (flag == ROOK_PROMOTION_CAPTURE_FLAG.getFlag()) {
                bitboards[fromPiece.getBitBoardIndex()] -= squares[to];
                bitboards[fromPiece.getBitBoardIndex() + 1] += squares[to];
            } else if (flag == KNIGHT_PROMOTION_CAPTURE_FLAG.getFlag()) {
                bitboards[fromPiece.getBitBoardIndex()] -= squares[to];
                bitboards[fromPiece.getBitBoardIndex() + 2] += squares[to];
            } else if (flag == BISHOP_PROMOTION_CAPTURE_FLAG.getFlag()) {
                bitboards[fromPiece.getBitBoardIndex()] -= squares[to];
                bitboards[fromPiece.getBitBoardIndex() + 3] += squares[to];
            } else if (flag == QUEEN_PROMOTION_CAPTURE_FLAG.getFlag()) {
                bitboards[fromPiece.getBitBoardIndex()] -= squares[to];
                bitboards[fromPiece.getBitBoardIndex() + 4] += squares[to];
            } else if (isWhitesTurn && flag == EP_CAPTURE_FLAG.getFlag()) {
                bitboards[blackPawnBitBoard] -= squares[to - 8];
            } else if (!isWhitesTurn && flag == EP_CAPTURE_FLAG.getFlag()) {
                bitboards[whitePawnBitBoard] -= squares[to + 8];
            }
        } else if (isWhitesTurn && flag == KING_CASTLE_FLAG.getFlag()) {
            fortyMoveCount = 0;
            bitboards[whiteKingBitBoard] += (squares[6] - squares[4]);
            bitboards[whiteRookBitBoard] += (squares[5] - squares[7]);
        } else if (isWhitesTurn && flag == QUEEN_CASTLE_FLAG.getFlag()) {
            fortyMoveCount = 0;
            bitboards[whiteKingBitBoard] += (squares[2] - squares[4]);
            bitboards[whiteRookBitBoard] += (squares[3] - squares[0]);
        } else if (!isWhitesTurn && flag == KING_CASTLE_FLAG.getFlag()) {
            fortyMoveCount = 0;
            bitboards[blackKingBitBoard] += (squares[62] - squares[60]);
            bitboards[blackRookBitBoard] += (squares[61] - squares[63]);
        } else if (!isWhitesTurn && flag == QUEEN_CASTLE_FLAG.getFlag()) {
            fortyMoveCount = 0;
            bitboards[blackKingBitBoard] += (squares[58] - squares[60]);
            bitboards[blackRookBitBoard] += (squares[59] - squares[56]);
        } else if (flag == ROOK_PROMOTION_FLAG.getFlag()) {
            bitboards[fromPiece.getBitBoardIndex()] -= squares[from];
            bitboards[fromPiece.getBitBoardIndex() + 1] += squares[to];
        } else if (flag == KNIGHT_PROMOTION_FLAG.getFlag()) {
            bitboards[fromPiece.getBitBoardIndex()] -= squares[from];
            bitboards[fromPiece.getBitBoardIndex() + 2] += squares[to];
        } else if (flag == BISHOP_PROMOTION_FLAG.getFlag()) {
            bitboards[fromPiece.getBitBoardIndex()] -= squares[from];
            bitboards[fromPiece.getBitBoardIndex() + 3] += squares[to];
        } else if (flag == QUEEN_PROMOTION_FLAG.getFlag()) {
            bitboards[fromPiece.getBitBoardIndex()] -= squares[from];
            bitboards[fromPiece.getBitBoardIndex() + 4] += squares[to];
        }

        //set castle flags
        if (from == 0 || to == 0) {
            whiteQueenSideCastle = false;
        }
        if (from == 7 || to == 7) {
            whiteKingSideCastle = false;
        }
        if (from == 56 || to == 56) {
            blackQueenSideCastle = false;
        }
        if (from == 63 || to == 63) {
            blackKingSideCastle = false;
        }
        if (from == 4) {
            whiteKingSideCastle = false;
            whiteQueenSideCastle = false;
        }
        if (from == 60) {
            blackKingSideCastle = false;
            blackQueenSideCastle = false;
        }


        isWhitesTurn = !isWhitesTurn;
//        updateHistory();

        fortyMoveCount++;
        updateGlobalBitBoards();
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

            if (((bitboards[whitePawnBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("(P)" + "|");
            } else if (((bitboards[whiteRookBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("(R)" + "|");
            } else if (((bitboards[whiteKnightBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("(N)" + "|");
            } else if (((bitboards[whiteBishopBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("(B)" + "|");
            } else if (((bitboards[whiteQueenBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("(Q)" + "|");
            } else if (((bitboards[whiteKingBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("(K)" + "|");
            } else if (((bitboards[blackPawnBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("*p*" + "|");
            } else if (((bitboards[blackRookBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("*r*" + "|");
            } else if (((bitboards[blackKnightBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("*n*" + "|");
            } else if (((bitboards[blackBishopBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("*b*" + "|");
            } else if (((bitboards[blackQueenBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("*q*" + "|");
            } else if (((bitboards[blackKingBitBoard] >> i) & 1) == 1) {
                printBoardBuilder.append("*k*" + "|");
            } else {
                printBoardBuilder.append("   " + "|");
            }
            if (i % 8 == 7) printBoardBuilder.append("\n").append(rowString).append("\n");
        }
        printBoard = printBoardBuilder.toString();
        printBoard += "     a   b   c   d   e   f   g   h";
        return printBoard;
    }

    public String getFEN() {
        StringBuilder ans = new StringBuilder();
        int count = 0;
        for (int i = 0; i < order.length; i++) {

            switch (pieceAtSquare(order[i])) {
                case WHITE_PAWN -> {
                    if (count > 0) ans.append(count);
                    ans.append("P");
                    count = 0;
                }
                case WHITE_ROOK -> {
                    if (count > 0) ans.append(count);
                    ans.append("R");
                    count = 0;
                }
                case WHITE_KNIGHT -> {
                    if (count > 0) ans.append(count);
                    ans.append("N");
                    count = 0;
                }
                case WHITE_BISHOP -> {
                    if (count > 0) ans.append(count);
                    ans.append("B");
                    count = 0;
                }
                case WHITE_QUEEN -> {
                    if (count > 0) ans.append(count);
                    ans.append("Q");
                    count = 0;
                }
                case WHITE_KING -> {
                    if (count > 0) ans.append(count);
                    ans.append("K");
                    count = 0;
                }
                case BLACK_PAWN -> {
                    if (count > 0) ans.append(count);
                    ans.append("p");
                    count = 0;
                }
                case BLACK_ROOK -> {
                    if (count > 0) ans.append(count);
                    ans.append("r");
                    count = 0;
                }
                case BLACK_KNIGHT -> {
                    if (count > 0) ans.append(count);
                    ans.append("n");
                    count = 0;
                }
                case BLACK_BISHOP -> {
                    if (count > 0) ans.append(count);
                    ans.append("b");
                    count = 0;
                }
                case BLACK_QUEEN -> {
                    if (count > 0) ans.append(count);
                    ans.append("q");
                    count = 0;
                }
                case BLACK_KING -> {
                    if (count > 0) ans.append(count);
                    ans.append("k");
                    count = 0;
                }
                case EMPTY -> count++;

            }
            if (i % 8 == 7 && i < 60) {
                if (count > 0) ans.append(count);
                ans.append("/");
                count = 0;
            }
        }
        if (count != 0) ans.append(count);
        if (isWhitesTurn) ans.append(" w ");
        else ans.append(" b ");
        if (whiteKingSideCastle) ans.append("K");
        if (whiteQueenSideCastle) ans.append("Q");
        if (blackKingSideCastle) ans.append("k");
        if (blackQueenSideCastle) ans.append("q");
        if (!(whiteKingSideCastle || whiteQueenSideCastle || blackKingSideCastle || blackQueenSideCastle)) {
            ans.append("-");
        }

        ans.append(" ").append(enPassantTarget);
        ans.append(" ").append(fortyMoveCount).append(" ").append((1 + moveCount) / 2);
        return ans.toString();
    }

    public ChessPiece pieceAtSquare(int square) {
        long i = squares[square];
        if ((bitboards[occupiedBitBoard] & i) == 0) return ChessPiece.EMPTY;
        if ((bitboards[whitePawnBitBoard] & i) != 0) return ChessPiece.WHITE_PAWN;
        if ((bitboards[blackPawnBitBoard] & i) != 0) return ChessPiece.BLACK_PAWN;
        if ((bitboards[whiteQueenBitBoard] & i) != 0) return ChessPiece.WHITE_QUEEN;
        if ((bitboards[blackQueenBitBoard] & i) != 0) return ChessPiece.BLACK_QUEEN;
        if ((bitboards[whiteRookBitBoard] & i) != 0) return ChessPiece.WHITE_ROOK;
        if ((bitboards[blackRookBitBoard] & i) != 0) return ChessPiece.BLACK_ROOK;
        if ((bitboards[whiteBishopBitBoard] & i) != 0) return ChessPiece.WHITE_BISHOP;
        if ((bitboards[blackBishopBitBoard] & i) != 0) return ChessPiece.BLACK_BISHOP;
        if ((bitboards[whiteKnightBitBoard] & i) != 0) return ChessPiece.WHITE_KNIGHT;
        if ((bitboards[blackKnightBitBoard] & i) != 0) return ChessPiece.BLACK_KNIGHT;
        if ((bitboards[whiteKingBitBoard] & i) != 0) return ChessPiece.WHITE_KING;
        if ((bitboards[blackKingBitBoard] & i) != 0) return ChessPiece.BLACK_KING;
        return ChessPiece.EMPTY;
    }

    public boolean isSquareEmpty(int square) {
        long all = getOccupiedBitBoard();
        return ((all >> square) & 1) != 1;
    }


    public long getWhiteBitBoard() {
        return bitboards[whiteBitBoard];
    }

    public long getBlackBitBoard() {
        return bitboards[blackBitBoard];
    }

    public long getOccupiedBitBoard() {
        return bitboards[occupiedBitBoard];
    }

    public long getEmptyBitBoard() {
        return bitboards[emptyBitBoard];
    }

    public long getWhitePawnBitBoard() {
        return bitboards[whitePawnBitBoard];
    }

    public long getWhiteRookBitBoard() {
        return bitboards[whiteRookBitBoard];
    }

    public long getWhiteKnightBitBoard() {
        return bitboards[whiteKnightBitBoard];
    }

    public long getWhiteBishopBitBoard() {
        return bitboards[whiteBishopBitBoard];
    }

    public long getWhiteKingBitBoard() {
        return bitboards[whiteKingBitBoard];
    }

    public long getWhiteQueenBitBoard() {
        return bitboards[whiteQueenBitBoard];
    }

    public long getBlackRookBitBoard() {
        return bitboards[blackRookBitBoard];
    }

    public long getBlackKnightBitBoard() {
        return bitboards[blackKnightBitBoard];
    }

    public long getBlackBishopBitBoard() {
        return bitboards[blackBishopBitBoard];
    }

    public long getBlackKingBitBoard() {
        return bitboards[blackKingBitBoard];
    }

    public long getBlackQueenBitBoard() {
        return bitboards[blackQueenBitBoard];
    }

    public long getBlackPawnBitBoard() {
        return bitboards[blackPawnBitBoard];
    }

    public boolean canBlackKingSideCastle() {
        return blackKingSideCastle;
    }

    public boolean canBlackQueenSideCastle() {
        return blackQueenSideCastle;
    }

    public boolean canWhiteKingSideCastle() {
        return whiteKingSideCastle;
    }

    public boolean canWhiteQueenSideCastle() {
        return whiteQueenSideCastle;
    }

    public boolean isWhitesTurn() {
        return isWhitesTurn;
    }

    public String getEnPassantTarget() {
        return enPassantTarget;
    }

    public int getWhiteKingSquare() {
        return Long.numberOfTrailingZeros(bitboards[whiteKingBitBoard]);
    }

    public int getBlackKingSquare() {
        return Long.numberOfTrailingZeros(bitboards[blackKingBitBoard]);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public Board getParentBoard() {
        return parentBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (blackKingSideCastle != board.blackKingSideCastle) return false;
        if (blackQueenSideCastle != board.blackQueenSideCastle) return false;
        if (whiteKingSideCastle != board.whiteKingSideCastle) return false;
        if (whiteQueenSideCastle != board.whiteQueenSideCastle) return false;
        if (isWhitesTurn != board.isWhitesTurn) return false;
        if (!Arrays.equals(bitboards, board.bitboards)) return false;
        return enPassantTarget.equals(board.enPassantTarget);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(bitboards);
        result = 31 * result + enPassantTarget.hashCode();
        result = 31 * result + (blackKingSideCastle ? 1 : 0);
        result = 31 * result + (blackQueenSideCastle ? 1 : 0);
        result = 31 * result + (whiteKingSideCastle ? 1 : 0);
        result = 31 * result + (whiteQueenSideCastle ? 1 : 0);
        result = 31 * result + (isWhitesTurn ? 1 : 0);
        return result;
    }
}
