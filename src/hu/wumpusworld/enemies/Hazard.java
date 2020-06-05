package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Player;

import java.io.Serializable;

public abstract class Hazard implements Serializable {
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
