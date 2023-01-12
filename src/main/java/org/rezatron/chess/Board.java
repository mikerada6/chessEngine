package org.rezatron.chess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.constants.ChessPiece;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.rezatron.chess.constants.ChessConstants.*;
import static org.rezatron.chess.constants.MoveFlags.*;

public
class Board {

  private static final Logger log = LogManager.getLogger( Board.class );
  private final List<BoardHistory> history;
//  private final List<String> enPassantTargetHistory;
  private final int[] moves;
  private String enPassantTarget;
  private long whitePawnBitBoard, whiteRookBitBoard, whiteKnightBitBoard, whiteBishopBitBoard, whiteKingBitBoard,
    whiteQueenBitBoard, blackRookBitBoard, blackKnightBitBoard, blackBishopBitBoard, blackKingBitBoard,
    blackQueenBitBoard, blackPawnBitBoard, whiteBitBoard, blackBitBoard, occupiedBitBoard, emptyBitBoard;
  private boolean blackKingSideCastle, blackQueenSideCastle, whiteKingSideCastle, whiteQueenSideCastle;
  private long fortyMoveCount;
  private boolean isWhitesTurn;
  private int moveCount;


  public
  Board() {
    log.info( "new  board" );
    enPassantTarget = "-";
    history = new ArrayList<>();
//    enPassantTargetHistory = new LinkedList<>();
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

    blackKingSideCastle = blackQueenSideCastle = whiteKingSideCastle = whiteQueenSideCastle = true;
    fortyMoveCount = 0;
    isWhitesTurn = true;
    // moves = "";
    moveCount = 0;
    updateHistory();
    moveCount = 1;

    whiteBitBoard = whiteRookBitBoard | whiteKnightBitBoard | whiteBishopBitBoard | whiteQueenBitBoard | whiteKingBitBoard
                    | whitePawnBitBoard;
    blackBitBoard = blackRookBitBoard | blackKnightBitBoard | blackBishopBitBoard | blackQueenBitBoard | blackKingBitBoard
                    | blackPawnBitBoard;
    occupiedBitBoard = whiteBitBoard | blackBitBoard;
    emptyBitBoard = ~occupiedBitBoard;
  }

  public
  Board(String fenString) {
    enPassantTarget = "-";
    // not chess960 compatible

    history = new LinkedList<>();
//    enPassantTargetHistory = new ArrayList<>();
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

    whiteKingSideCastle = false;
    whiteQueenSideCastle = false;
    blackKingSideCastle = false;
    blackQueenSideCastle = false;
    int charIndex = 0;
    int boardIndex = 0;
    while (fenString.charAt( charIndex ) != ' ') {
      switch (fenString.charAt( charIndex++ )) {
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
    isWhitesTurn = fenString.charAt( ++charIndex ) == 'w';
    charIndex += 2;
    while (fenString.charAt( charIndex ) != ' ') {
      switch (fenString.charAt( charIndex++ )) {
        case '-':
          break;
        case 'K':
          whiteKingSideCastle = true;
          break;
        case 'Q':
          whiteQueenSideCastle = true;
          break;
        case 'k':
          blackKingSideCastle = true;
          break;
        case 'q':
          blackQueenSideCastle = true;
          break;
        default:
          break;
      }
    }
    if (pieceAtSquare( 0 ) != ChessPiece.WHITE_ROOK) whiteQueenSideCastle = false;
    if (pieceAtSquare( 7 ) != ChessPiece.WHITE_ROOK) whiteKingSideCastle = false;
    if (pieceAtSquare( 56 ) != ChessPiece.BLACK_ROOK) blackQueenSideCastle = false;
    if (pieceAtSquare( 63 ) != ChessPiece.BLACK_ROOK) blackKingSideCastle = false;
    enPassantTarget = "-";
    if (fenString.charAt( ++charIndex ) != '-') {
      enPassantTarget = fenString.substring( charIndex,
                                             charIndex + 2 );
    }

    // the rest of the fenString is not yet utilized
    // moves = "";
    moveCount = 0;
    updateHistory();
    moveCount = 1;

    whiteBitBoard = whiteRookBitBoard | whiteKnightBitBoard | whiteBishopBitBoard | whiteQueenBitBoard | whiteKingBitBoard
                    | whitePawnBitBoard;
    blackBitBoard = blackRookBitBoard | blackKnightBitBoard | blackBishopBitBoard | blackQueenBitBoard | blackKingBitBoard
                    | blackPawnBitBoard;
    occupiedBitBoard = whiteBitBoard | blackBitBoard;
    emptyBitBoard = ~occupiedBitBoard;
  }

  public
  void undo() {
    isWhitesTurn = !isWhitesTurn;

//    enPassantTargetHistory.remove( enPassantTargetHistory.size() - 1 );
//    enPassantTarget = enPassantTargetHistory.get( enPassantTargetHistory.size() - 1 );

//    for (int i = 0; i < 17; i++) {
//      history.remove( history.size() - 1 );
//    }

    history.remove( history.size() - 1 );
    BoardHistory historyTarget = history.get( history.size() - 1 );

    fortyMoveCount = (historyTarget.getFortyMoveCount());
    // history.remove(history.size()-1);

    blackQueenSideCastle = (historyTarget.isBlackQueenSideCastle());
    // history.remove(history.size()-1);
    blackKingSideCastle = (historyTarget.isBlackKingSideCastle());
    // history.remove(history.size()-1);
    whiteQueenSideCastle = (historyTarget.isWhiteQueenSideCastle());
    // history.remove(history.size()-1);
    whiteKingSideCastle = (historyTarget.isWhiteKingSideCastle());
    // history.remove(history.size()-1);

    blackKingBitBoard = historyTarget.getBlackKingBitBoard();
    // history.remove(history.size()-1);

    blackQueenBitBoard = historyTarget.getBlackQueenBitBoard();
    // history.remove(history.size()-1);

    blackBishopBitBoard = historyTarget.getBlackBishopBitBoard();
    // history.remove(history.size()-1);

    blackKnightBitBoard = historyTarget.getBlackKnightBitBoard();
    // history.remove(history.size()-1);

    blackRookBitBoard = historyTarget.getBlackRookBitBoard();
    // history.remove(history.size()-1);

    blackPawnBitBoard = historyTarget.getBlackPawnBitBoard();
    // history.remove(history.size()-1);

    whiteKingBitBoard = historyTarget.getWhiteKingBitBoard();
    // history.remove(history.size()-1);

    whiteQueenBitBoard = historyTarget.getWhiteQueenBitBoard();
    // history.remove(history.size()-1);

    whiteBishopBitBoard = historyTarget.getWhiteBishopBitBoard();
    // history.remove(history.size()-1);

    whiteKnightBitBoard = historyTarget.getWhiteKnightBitBoard();
    // history.remove(history.size()-1);

    whiteRookBitBoard = historyTarget.getWhiteRookBitBoard();
    // history.remove(history.size()-1);

    whitePawnBitBoard = historyTarget.getWhitePawnBitBoard();
    // history.remove(history.size()-1);

    enPassantTarget = historyTarget.getEnPassantTarget();
    moveCount -= 1;

    // wp + "," + wr + "," + wn + "," + wb + "," + wq
    // + "," + wk + "," + bp + "," + br + "," + bn + "," + bb + ","
    // + bq + "," + bk + "," + wkc + "," + wqc + "," + bkc + "," + bqc
    // + "," + enPassantTarget + "," + fortyMoveCount

    whiteBitBoard = whiteRookBitBoard | whiteKnightBitBoard | whiteBishopBitBoard | whiteQueenBitBoard | whiteKingBitBoard
                    | whitePawnBitBoard;
    blackBitBoard = blackRookBitBoard | blackKnightBitBoard | blackBishopBitBoard | blackQueenBitBoard | blackKingBitBoard
                    | blackPawnBitBoard;
    occupiedBitBoard = whiteBitBoard | blackBitBoard;
    emptyBitBoard = ~occupiedBitBoard;
  }

  public
  void move(Move move) {
    int from = move.getFrom();
    int to = move.getTo();
    enPassantTarget = "-";
    ChessPiece attackedPiece = pieceAtSquare( to );
    int flag = move.getFlags();
    log.trace( "Move {} is a move from {} to {} with flags{}.",
               move,
               from,
               to,
               flag );
    ChessPiece fromPiece = pieceAtSquare( from );
    if (isWhitesTurn && flag == KING_CASTLE_FLAG.getFlag()) {
      fortyMoveCount = 0;
      whiteKingBitBoard += (squares[6] - squares[4]);
      whiteRookBitBoard += (squares[5] - squares[7]);
    } else if (isWhitesTurn && flag == QUEEN_CASTLE_FLAG.getFlag()) {
      fortyMoveCount = 0;
      whiteKingBitBoard += (squares[2] - squares[4]);
      whiteRookBitBoard += (squares[3] - squares[0]);
    } else if (!isWhitesTurn && flag == KING_CASTLE_FLAG.getFlag()) {
      fortyMoveCount = 0;
      blackKingBitBoard += (squares[62] - squares[60]);
      blackRookBitBoard += (squares[61] - squares[63]);
    } else if (!isWhitesTurn && flag == QUEEN_CASTLE_FLAG.getFlag()) {
      fortyMoveCount = 0;
      blackKingBitBoard += (squares[58] - squares[60]);
      blackRookBitBoard += (squares[59] - squares[56]);
    } else if (isWhitesTurn && flag == EP_CAPTURE_FLAG.getFlag()) {
      whitePawnBitBoard += (squares[to] - squares[from]);
      blackPawnBitBoard -= squares[to - 8];
    } else if (!isWhitesTurn && flag == EP_CAPTURE_FLAG.getFlag()) {
      blackPawnBitBoard += (squares[to] - squares[from]);
      whitePawnBitBoard -= squares[to + 8];
    } else {
      switch (fromPiece) {
        case WHITE_PAWN -> {
          whitePawnBitBoard += (squares[to] - squares[from]);
          if(flag == ROOK_PROMOTION_CAPTURE_FLAG.getFlag() || flag == ROOK_PROMOTION_FLAG.getFlag())
          {
            whitePawnBitBoard -= squares[to];
            whiteRookBitBoard += squares[to];
          } else if(flag == KNIGHT_PROMOTION_CAPTURE_FLAG.getFlag() || flag == KNIGHT_PROMOTION_FLAG.getFlag())
          {
            whitePawnBitBoard -= squares[to];
            whiteKnightBitBoard += squares[to];
          }
          else if(flag == BISHOP_PROMOTION_CAPTURE_FLAG.getFlag() || flag == BISHOP_PROMOTION_FLAG.getFlag())
          {
            whitePawnBitBoard -= squares[to];
            whiteBishopBitBoard += squares[to];
          }
          else if(flag == QUEEN_PROMOTION_CAPTURE_FLAG.getFlag() || flag == QUEEN_PROMOTION_FLAG.getFlag())
          {
            whitePawnBitBoard -= squares[to];
            whiteQueenBitBoard += squares[to];
          }
          if (to - from == 16) {
            enPassantTarget = letterSquares[from + 8];
          }
          fortyMoveCount = 0;
        }
        case WHITE_ROOK -> whiteRookBitBoard += (squares[to] - squares[from]);
        case WHITE_KNIGHT -> whiteKnightBitBoard += (squares[to] - squares[from]);
        case WHITE_BISHOP -> whiteBishopBitBoard += (squares[to] - squares[from]);
        case WHITE_QUEEN -> whiteQueenBitBoard += (squares[to] - squares[from]);
        case WHITE_KING -> whiteKingBitBoard += (squares[to] - squares[from]);
        case BLACK_PAWN -> {
          blackPawnBitBoard += (squares[to] - squares[from]);
          if(flag == ROOK_PROMOTION_CAPTURE_FLAG.getFlag() || flag == ROOK_PROMOTION_FLAG.getFlag())
          {
            blackPawnBitBoard -= squares[to];
            blackRookBitBoard += squares[to];
          } else if(flag == KNIGHT_PROMOTION_CAPTURE_FLAG.getFlag() || flag == KNIGHT_PROMOTION_FLAG.getFlag())
          {
            blackPawnBitBoard -= squares[to];
            blackKnightBitBoard += squares[to];
          }
          else if(flag == BISHOP_PROMOTION_CAPTURE_FLAG.getFlag() || flag == BISHOP_PROMOTION_FLAG.getFlag())
          {
            blackPawnBitBoard -= squares[to];
            blackBishopBitBoard += squares[to];
          }
          else if(flag == QUEEN_PROMOTION_CAPTURE_FLAG.getFlag() || flag == QUEEN_PROMOTION_FLAG.getFlag())
          {
            blackPawnBitBoard -= squares[to];
            blackQueenBitBoard += squares[to];
          }
          fortyMoveCount = 0;
          if (from - to == 16) {
            enPassantTarget = letterSquares[from - 8];
          }
        }
        case BLACK_ROOK -> blackRookBitBoard += (squares[to] - squares[from]);
        case BLACK_KNIGHT -> blackKnightBitBoard += (squares[to] - squares[from]);
        case BLACK_BISHOP -> blackBishopBitBoard += (squares[to] - squares[from]);
        case BLACK_QUEEN -> blackQueenBitBoard += (squares[to] - squares[from]);
        case BLACK_KING -> blackKingBitBoard += (squares[to] - squares[from]);
        case EMPTY -> {
        }
      }
      if (move.isCapture()) {
        switch (attackedPiece) {
          case EMPTY:
            break;
          case WHITE_PAWN:
            fortyMoveCount = 0;
            whitePawnBitBoard -= squares[to];
            break;
          case WHITE_ROOK:
            fortyMoveCount = 0;
            whiteRookBitBoard -= squares[to];
            break;
          case WHITE_KNIGHT:
            fortyMoveCount = 0;
            whiteKnightBitBoard -= squares[to];
            break;
          case WHITE_BISHOP:
            fortyMoveCount = 0;
            whiteBishopBitBoard -= squares[to];
            break;
          case WHITE_QUEEN:
            fortyMoveCount = 0;
            whiteQueenBitBoard -= squares[to];
            break;
          case BLACK_PAWN:
            fortyMoveCount = 0;
            blackPawnBitBoard -= squares[to];
            break;
          case BLACK_ROOK:
            fortyMoveCount = 0;
            blackRookBitBoard -= squares[to];
            break;
          case BLACK_KNIGHT:
            fortyMoveCount = 0;
            blackKnightBitBoard -= squares[to];
            break;
          case BLACK_BISHOP:
            fortyMoveCount = 0;
            blackBishopBitBoard -= squares[to];
            break;
          case BLACK_QUEEN:
            fortyMoveCount = 0;
            blackQueenBitBoard -= squares[to];
            break;
        }
      }
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
    updateHistory();
    moveCount++;

    whiteBitBoard = whiteRookBitBoard | whiteKnightBitBoard | whiteBishopBitBoard | whiteQueenBitBoard | whiteKingBitBoard
                    | whitePawnBitBoard;
    blackBitBoard = blackRookBitBoard | blackKnightBitBoard | blackBishopBitBoard | blackQueenBitBoard | blackKingBitBoard
                    | blackPawnBitBoard;
    occupiedBitBoard = whiteBitBoard | blackBitBoard;
    emptyBitBoard = ~occupiedBitBoard;
  }

  private
  void updateHistory() {
    BoardHistory bh = new BoardHistory( whitePawnBitBoard, whiteRookBitBoard, whiteKnightBitBoard, whiteBishopBitBoard,
                                        whiteQueenBitBoard, whiteKingBitBoard, blackPawnBitBoard, blackRookBitBoard,
                                        blackKnightBitBoard, blackBishopBitBoard, blackQueenBitBoard, blackKingBitBoard,
                                        whiteKingSideCastle, whiteQueenSideCastle, blackKingSideCastle,
                                        blackQueenSideCastle, fortyMoveCount, enPassantTarget );
    history.add(bh);
  }

  public
  String toString() {
    String rowString = "   +---+---+---+---+---+---+---+---+";
    String printBoard;
    int row = 8;
    StringBuilder printBoardBuilder = new StringBuilder( rowString + "\n" );
    for (int i : order) {
      if (i % 8 == 0) {
        printBoardBuilder.append( row ).append( "  |" );
        row--;
      }

      if (((whitePawnBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "(P)" + "|" );
      } else if (((whiteRookBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "(R)" + "|" );
      } else if (((whiteKnightBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "(N)" + "|" );
      } else if (((whiteBishopBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "(B)" + "|" );
      } else if (((whiteQueenBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "(Q)" + "|" );
      } else if (((whiteKingBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "(K)" + "|" );
      } else if (((blackPawnBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "*p*" + "|" );
      } else if (((blackRookBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "*r*" + "|" );
      } else if (((blackKnightBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "*n*" + "|" );
      } else if (((blackBishopBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "*b*" + "|" );
      } else if (((blackQueenBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "*q*" + "|" );
      } else if (((blackKingBitBoard >> i) & 1) == 1) {
        printBoardBuilder.append( "*k*" + "|" );
      } else {
        printBoardBuilder.append( "   " + "|" );
      }
      if (i % 8 == 7) printBoardBuilder.append( "\n" ).append( rowString ).append( "\n" );
    }
    printBoard = printBoardBuilder.toString();
    printBoard += "     a   b   c   d   e   f   g   h";
    return printBoard;
  }

  public
  String getFEN() {
    StringBuilder ans = new StringBuilder();
    int count = 0;
    for (int i = 0; i < order.length; i++) {

      switch (pieceAtSquare( order[i] )) {
        case WHITE_PAWN -> {
          if (count > 0) ans.append( count );
          ans.append( "P" );
          count = 0;
        }
        case WHITE_ROOK -> {
          if (count > 0) ans.append( count );
          ans.append( "R" );
          count = 0;
        }
        case WHITE_KNIGHT -> {
          if (count > 0) ans.append( count );
          ans.append( "N" );
          count = 0;
        }
        case WHITE_BISHOP -> {
          if (count > 0) ans.append( count );
          ans.append( "B" );
          count = 0;
        }
        case WHITE_QUEEN -> {
          if (count > 0) ans.append( count );
          ans.append( "Q" );
          count = 0;
        }
        case WHITE_KING -> {
          if (count > 0) ans.append( count );
          ans.append( "K" );
          count = 0;
        }
        case BLACK_PAWN -> {
          if (count > 0) ans.append( count );
          ans.append( "p" );
          count = 0;
        }
        case BLACK_ROOK -> {
          if (count > 0) ans.append( count );
          ans.append( "r" );
          count = 0;
        }
        case BLACK_KNIGHT -> {
          if (count > 0) ans.append( count );
          ans.append( "n" );
          count = 0;
        }
        case BLACK_BISHOP -> {
          if (count > 0) ans.append( count );
          ans.append( "b" );
          count = 0;
        }
        case BLACK_QUEEN -> {
          if (count > 0) ans.append( count );
          ans.append( "q" );
          count = 0;
        }
        case BLACK_KING -> {
          if (count > 0) ans.append( count );
          ans.append( "k" );
          count = 0;
        }
        case EMPTY -> count++;

      }
      if (i % 8 == 7 && i < 60) {
        if (count > 0) ans.append( count );
        ans.append( "/" );
        count = 0;
      }
    }
    if (count != 0) ans.append( count );
    if (isWhitesTurn) ans.append( " w " );
    else ans.append( " b " );
    if (whiteKingSideCastle) ans.append( "K" );
    if (whiteQueenSideCastle) ans.append( "Q" );
    if (blackKingSideCastle) ans.append( "k" );
    if (blackQueenSideCastle) ans.append( "q" );
    if (!(whiteKingSideCastle || whiteQueenSideCastle || blackKingSideCastle || blackQueenSideCastle)) {
      ans.append( "-" );
    }

    ans.append( " " ).append( enPassantTarget );
    ans.append( " " ).append( fortyMoveCount ).append( " " ).append( (1 + moveCount) / 2 );
    return ans.toString();
  }

  public
  ChessPiece pieceAtSquare(int square) {
    if ((emptyBitBoard & squares[square]) != 0) return ChessPiece.EMPTY;
    if ((whitePawnBitBoard & squares[square]) != 0) return ChessPiece.WHITE_PAWN;
    if ((blackPawnBitBoard & squares[square]) != 0) return ChessPiece.BLACK_PAWN;
    if ((whiteQueenBitBoard & squares[square]) != 0) return ChessPiece.WHITE_QUEEN;
    if ((blackQueenBitBoard & squares[square]) != 0) return ChessPiece.BLACK_QUEEN;
    if ((whiteRookBitBoard & squares[square]) != 0) return ChessPiece.WHITE_ROOK;
    if ((blackRookBitBoard & squares[square]) != 0) return ChessPiece.BLACK_ROOK;
    if ((whiteBishopBitBoard & squares[square]) != 0) return ChessPiece.WHITE_BISHOP;
    if ((blackBishopBitBoard & squares[square]) != 0) return ChessPiece.BLACK_BISHOP;
    if ((whiteKnightBitBoard & squares[square]) != 0) return ChessPiece.WHITE_KNIGHT;
    if ((blackKnightBitBoard & squares[square]) != 0) return ChessPiece.BLACK_KNIGHT;
    if ((whiteKingBitBoard & squares[square]) != 0) return ChessPiece.WHITE_KING;
    if ((blackKingBitBoard & squares[square]) != 0) return ChessPiece.BLACK_KING;
    return ChessPiece.EMPTY;
  }

  public
  boolean isSquareEmpty(int square) {
    long all = getOccupiedBitBoard();
    return ((all >> square) & 1) != 1;
  }


  public
  long getWhiteBitBoard() {
    return whiteBitBoard;
  }

  public
  long getBlackBitBoard() {
    return blackBitBoard;
  }

  public
  long getOccupiedBitBoard() {
    return occupiedBitBoard;
  }

  public
  long getEmptyBitBoard() {
    return emptyBitBoard;
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
  long getWhiteKingBitBoard() {
    return whiteKingBitBoard;
  }

  public
  long getWhiteQueenBitBoard() {
    return whiteQueenBitBoard;
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
  long getBlackKingBitBoard() {
    return blackKingBitBoard;
  }

  public
  long getBlackQueenBitBoard() {
    return blackQueenBitBoard;
  }

  public
  long getBlackPawnBitBoard() {
    return blackPawnBitBoard;
  }

  public
  boolean canBlackKingSideCastle() {
    return blackKingSideCastle;
  }

  public
  boolean canBlackQueenSideCastle() {
    return blackQueenSideCastle;
  }

  public
  boolean canWhiteKingSideCastle() {
    return whiteKingSideCastle;
  }

  public
  boolean canWhiteQueenSideCastle() {
    return whiteQueenSideCastle;
  }

  public
  boolean isWhitesTurn() {
    return isWhitesTurn;
  }

  public
  String getEnPassantTarget() {
    return enPassantTarget;
  }

  public
  int getWhiteKingSquare() {
    return Long.numberOfTrailingZeros( whiteKingBitBoard );
  }

  public
  int getBlackKingSquare() {
    return Long.numberOfTrailingZeros( blackKingBitBoard );
  }


}
