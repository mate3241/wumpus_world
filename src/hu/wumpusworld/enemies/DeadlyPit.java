package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Player;

public class DeadlyPit extends Pit{
public final char symbol = 'D';
  @Override
  public void interaction(Player player) {
      System.out.println("It's not the fall that kills you, it's the landing");
      player.die();
  }

  @Override
  public char getSymbol() {
    return this.symbol;
  }
}
