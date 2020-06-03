package hu.flowacademy;

public class Map {
  public Map(Square[][] squares, boolean testMode) {
    this.squares = squares;
    this.testMode = testMode;
  }
  Player player;
  Wumpus wumpus;
  public Square[][] squares;
  boolean testMode;


  public void printMap(){
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares.length; j++) {
        if(player.getX() == i && player.getY() == j) {
          System.out.print(player.getSymbol() + " ");
          squares[i][j].isExplored = true;
        } else {

          System.out.print(squares[i][j].determineSymbol(testMode) + " ");
        }
      }
      System.out.println();
    }
  }
}

