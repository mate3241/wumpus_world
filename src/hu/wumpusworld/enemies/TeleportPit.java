package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Player;

public class TeleportPit extends Pit {
  private final char symbol = 'T';
  @Override
  public void interaction(Player player) {
    System.out.println("You fall for a while, then land on unfamiliar terrain");
    player.teleport();
  }

  @Override
  public char getSymbol() {
    return this.symbol;
  }
}
