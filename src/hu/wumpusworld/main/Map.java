package hu.wumpusworld.main;


import hu.wumpusworld.utils.Serializer;

import java.io.IOException;
import java.util.Arrays;

public class Map implements java.io.Serializable{
  public Map(Square[][] squares, boolean testMode) {
    this.squares = squares;
    this.testMode = testMode;
  }

  Player player;
  public Square[][] squares;
  boolean testMode;


  public void printMap() {
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares.length; j++) {
        if (player.getX() == i && player.getY() == j) {
          System.out.print(player.getSymbol() + " ");

        } else {
          System.out.print(squares[i][j].determineSymbol(testMode) + " ");
        }
      }
      if (i == 0) { System.out.print("   Arrow(s): " + player.getArrow());}
      if (i == 1) { System.out.print("   Meat: " + player.getMeat());}
      System.out.println();
    }
  }

  public void getWarningsFromNeighbors(Square square) {
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (isValidNeighbor(square, i, j) &&
                squares[square.getX() + i][square.getY() + j].getHazard().isPresent()) {
          System.out.println(squares[square.getX() + i][square.getY() + j].getHazard().get().getWarning());
        }
        if (isValidNeighbor(square, i, j) &&
                squares[square.getX() + i][square.getY() + j].getWumpus().isPresent()) {
          System.out.println(squares[square.getX() + i][square.getY() + j].getWumpus().get().getWarning());
        }
      }
    }
  }

  private boolean isValidNeighbor(Square square, int i, int j) {
    return square.getX() + i >= 0 && square.getX() + i < squares.length &&
            square.getY() + j >= 0 && square.getY() + j < squares.length;
  }
}
