package org.rezatron.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rezatron.chess.Board;
import org.rezatron.chess.Move;
import org.rezatron.chess.MoveGenerator;

import java.util.List;

public class Perft {

    private static final Logger log = LogManager.getLogger(Perft.class);

    private static long diveIn(Board board, int depth) {
        MoveGenerator mg = new MoveGenerator(board);
        List<Move> moveList = mg.getMoves();
        if (moveList.size() == 0 && depth != 0) {
            return 0;
        } else if (moveList.size() == 0 || depth == 1) {
            return moveList.size();
        }
        long nodes = 0L;

        for (Move move : moveList) {
            Board newBoard = new Board(board);
            newBoard.move(move);
            nodes += diveIn(newBoard, depth - 1);
        }
        return nodes;
    }

    public static String divide(Board board, int depth) {
        System.out.println("Divide for board :" + board.getFEN()
                + "\n\t Depth: " + depth);

        MoveGenerator mg = new MoveGenerator(board);
        List<Move> moveList = mg.getMoves();

        String[] ary1 = new String[moveList.size()];
        long[] count = new long[ary1.length];
        StringBuilder answer = new StringBuilder();
        int temp = 0;
        if(depth <=1)
        {
            for(Move m: moveList)
            {
                answer.append(m.toString()).append(": ").append(1).append("\n");
            }
            return answer.toString();
        }
        if (moveList.size() == 0) {
            return answer.toString();
        }
        for (Move move : moveList) {
            ary1[temp] = move.toString();
            Board newBoard = new Board(board);
            newBoard.move(move);
            count[temp] = diveIn(board, depth - 1);
//            board.undo();
            temp++;
        }
        int sum = 0;
        for (int i = 0; i < ary1.length; i++) {
            answer.append(ary1[i]).append(": ").append(count[i]).append("\n");
            sum += count[i];
        }
        answer.append("\n Moves: ").append(ary1.length).append("\n Nodes: ").append(sum);
        return answer.toString();
    }


    public static long perft(Board board, int depth) {
        MoveGenerator mg = new MoveGenerator(board);
        List<Move> moveList = mg.getMoves();
        if (depth == 1) {
            return moveList.size();
        }
        long nodes = 0;
        if (!moveList.isEmpty()) {
            for (Move move : moveList) {
                Board newBoard = new Board(board);
                newBoard.move(move);
                nodes += diveIn(newBoard, depth - 1);
            }
        }

        return nodes;
    }
}
