package hu.wumpusworld.main;

import hu.wumpusworld.utils.Difficulty;

import java.io.Serializable;

public class Meat implements Serializable {
  private final char symbol = 'M';
  private boolean isRotten;
  public Meat(Difficulty difficulty) {
    if (difficulty.equals(Difficulty.HARD)) {
      this.isRotten = true;
    }
  }

  public char getSymbol() {
    return symbol;
  }

  public String getWarning() {
    if (isRotten){
      return "You smell something terrible";
    }
    return "";
  }

}
