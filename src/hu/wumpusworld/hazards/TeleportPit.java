package hu.wumpusworld.hazards;

import hu.wumpusworld.main.Player;

public class TeleportPit extends Pit {
  private final char symbol = 'T';
  @Override
  public String interaction(Player player) {
    System.out.println("You fall for a while, then land on unfamiliar terrain");
    player.teleport();
    return "You fall for a while, then land on unfamiliar terrain";
  }

  @Override
  public char getSymbol() {
    return this.symbol;
  }
}
