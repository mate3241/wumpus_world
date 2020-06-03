package hu.wumpusworld.main;


import hu.wumpusworld.utils.MovementHelper;

public class Map {
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
          squares[i][j].isExplored = true;
        } else {
          System.out.print(squares[i][j].determineSymbol(testMode) + " ");
        }
      }
      System.out.println();
    }
  }

  public void CHANGETHISNAMELATER(Square square) {
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (square.getX() + i >= 0 && square.getX() + i < squares.length &&
                square.getY() + j >= 0 && square.getY() + j < squares.length &&
                squares[square.getX() + i][square.getY() + j].getHazard().isPresent()) {
          System.out.println(squares[square.getX() + i][square.getY() + j].getHazard().get().getWarning());
        }
        if (square.getX() + i >= 0 && square.getX() + i < squares.length &&
                square.getY() + j >= 0 && square.getY() + j < squares.length &&
                squares[square.getX() + i][square.getY() + j].getWumpus().isPresent()) {
          System.out.println(squares[square.getX() + i][square.getY() + j].getWumpus().get().getWarning());
        }
      }
    }
  }
}
