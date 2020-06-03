package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Player;

public abstract class Hazard {
  String description;
  private final char symbol = ' ';
  private final String warning = "";
  public abstract void interaction(Player player);
  public char getSymbol(){
    return this.symbol;
  };
  public String getWarning(){
    return this.warning;
  }

}
