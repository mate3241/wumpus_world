package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Map;
import hu.wumpusworld.main.Player;

import java.io.Serializable;

public abstract class Hazard implements Serializable {
  Map map;
  String description;
  private final char symbol = ' ';
  private final String warning = "";
  public abstract String interaction(Player player);
  public char getSymbol(){
    return this.symbol;
  };
  public String getWarning(){
    return this.warning;
  }

}
