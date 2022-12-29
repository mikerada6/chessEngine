package org.rezatron.chess;

import java.util.ArrayList;

public class Perft {

    private static long diveIn(Board board, int depth) {
        ArrayList<Move> moveList = MoveGenerator.getMoves(board);
        if (moveList.size() == 0 && depth != 0) {
            return 0;
        } else if (moveList.size() == 0 || depth == 1) {
            return moveList.size();
        }
        long nodes = 0;
        if (depth == 0) {
            return 1;
        }

        if (!moveList.isEmpty()) {
            for (Move move : moveList) {
                board.move(move);
                nodes += diveIn(board, depth - 1);
                board.undo();
            }
        }
        return nodes;
    }

    public static String divide(Board board, int depth) {
        System.out.println("Divide for board :" + board.getFEN()
                + "\n\t Depth: " + depth);
        ArrayList<Move> moveList = MoveGenerator.getMoves(board);
        String[] ary1 = new String[moveList.size()];
        long[] count = new long[ary1.length];
        StringBuilder answer = new StringBuilder();
        int temp = 0;
        if (!(moveList.size() == 0)) {
            for (Move move : moveList) {
                ary1[temp] = move.toString();
                board.move(move);
                count[temp] = diveIn(board, depth - 1);
                board.undo();
                temp++;
            }
            int sum = 0;
            for (int i = 0; i < ary1.length; i++) {
                answer.append(ary1[i]).append(": ").append(count[i]).append("\n");
                sum += count[i];
            }
            answer.append("\n Moves: ").append(ary1.length).append("\n Nodes: ").append(sum);
        }
        return answer.toString();
    }


    public static long perft(Board board, int depth) {
        ArrayList<Move> moveList = MoveGenerator.getMoves(board);
        if (depth == 1)
            return moveList.size();
        long nodes = 0;
        if (!moveList.isEmpty()) {
            for (Move move : moveList) {
                board.move(move);
                nodes += diveIn(board, depth - 1);
                board.undo();
            }
        }
        return nodes;
    }
}
