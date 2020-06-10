package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Player;

public class Bat extends Hazard {
  private final char symbol = 'B';
  private final String warning = "You hear the flapping of wings";
  @Override
  public String interaction(Player player) {
    if (Math.random() > 0.5) {
      System.out.println(player.map);
      player.placeAtRandom(player.map, player.map.getDifficulty());
      return "The bat teleported you (somehow)";
    } else {
      player.panic();
      return "You flee in panic";
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
