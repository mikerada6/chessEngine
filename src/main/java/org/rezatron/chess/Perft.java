package org.rezatron.chess;

import java.util.ArrayList;

public class Perft {


    public static long perft(Board board, int depth) {
        ArrayList<Move> moveList = MoveGenerator.getWhiteMoves(board);
        if (depth == 1)
            return moveList.size();
        long nodes = 0;
//        if (!moveList.equals("")) {
//            for (int i = 0; i < moveList.size(); i++) {
//                board.move(moveList.get(i));
//                nodes += diveIn(board,depth - 1);
//                board.undo();
//            }
//        }
        return nodes;
    }
}
