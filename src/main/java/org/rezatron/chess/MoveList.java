package org.rezatron.chess;

import java.util.ArrayList;
import java.util.stream.Stream;

public
class MoveList{
  private Move[] moves;
  private int size;

  public MoveList()
  {
    moves = new Move[256];
    int size = 0;
  }

  public Move get(int i)
  {
    return moves[i];
  }

  public boolean isEmpty()
  {
    return size==0;
  }

  public int size()
  {
    return size;
  }

  public void add(Move move)
  {
    moves[size++]=move;
  }

  public void addAll(MoveList ml)
  {
      for(int i=0;i<ml.size();i++)
      {
        add(ml.get(i));
      }
  }

  public
  Stream<Move> stream()
  {
    ArrayList<Move> temp = new ArrayList<>();
    for(int i=0;i<this.size();i++)
    {
      temp.add(this.get(i));
    }
    return temp.stream();
  }

  public boolean contains(Move m)
  {
    //TODO THIS IS NOT REAL
    return true;
  }

}
