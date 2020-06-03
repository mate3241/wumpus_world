package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Player;

public class Bat extends Hazard {
  private final char symbol = 'B';
  private final String warning = "You hear the flapping of wings";
  @Override
  public void interaction(Player player) {
    if (Math.random() > 0.5) {
      System.out.println("Teleport");
      player.teleport();
    } else {
      System.out.println("panic");
      player.panic();
    }
  }

  @Override
  public String getWarning() {
    return warning;
  }

  @Override
  public char getSymbol() {
    return this.symbol;
  }
}
