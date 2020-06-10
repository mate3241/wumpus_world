package hu.wumpusworld.main;


import hu.wumpusworld.utils.Difficulty;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;

public class Map implements java.io.Serializable{
  public Map(Square[][] squares, boolean testMode, Difficulty difficulty) {
    this.squares = squares;
    this.testMode = testMode;
    this.difficulty = difficulty;
  }

  public Player player;
  public Square[][] squares;
  boolean testMode;
  Difficulty difficulty;

  public Difficulty getDifficulty() {
    return difficulty;
  }

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

  public void setGraphics(GridPane gridPane){


    gridPane.getChildren().clear();

    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares.length; j++) {
        Text text= new Text();
        Rectangle tile;
        text.setFont(Font.font(40));
        tile = new Rectangle(50, 50);
        tile.setFill(Color.BURLYWOOD);
        tile.setStroke(Color.BLACK);
      if (player.getX() == i && player.getY() == j) {
          text.setText(player.getSymbol() + "");
        tile.setStroke(Color.RED);

      } else {
          text.setText(squares[i][j].determineSymbol(testMode) + "");
        tile.setStroke(Color.BLACK);

      }
        StackPane stackPane = new StackPane(tile, text);
        gridPane.add(stackPane, j, i);
      }
    }
  }

  public String getWarningsFromNeighbors(Square square) {
    StringBuilder sb = new StringBuilder();
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (isValidNeighbor(square, i, j) &&
                squares[square.getX() + i][square.getY() + j].getHazard().isPresent()) {
          System.out.println(squares[square.getX() + i][square.getY() + j].getHazard().get().getWarning());
          sb.append(squares[square.getX() + i][square.getY() + j].getHazard().get().getWarning());
          sb.append("\n");        }
        if (isValidNeighbor(square, i, j) &&
                squares[square.getX() + i][square.getY() + j].getWumpus().isPresent()) {
          System.out.println(squares[square.getX() + i][square.getY() + j].getWumpus().get().getWarning());
          sb.append(squares[square.getX() + i][square.getY() + j].getWumpus().get().getWarning());
          sb.append("\n");
        }
      }

    }
    return sb.toString();
  }

  private boolean isValidNeighbor(Square square, int i, int j) {
    return square.getX() + i >= 0 && square.getX() + i < squares.length &&
            square.getY() + j >= 0 && square.getY() + j < squares.length;
  }

  @Override
  public String toString() {
    return "Map{" +
            "player=" + player +
            ", squares=" + Arrays.toString(squares) +
            ", testMode=" + testMode +
            '}';
  }
}
