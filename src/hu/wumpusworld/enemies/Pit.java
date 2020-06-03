package hu.wumpusworld.enemies;

import hu.wumpusworld.enemies.Hazard;
import hu.wumpusworld.main.Player;

public abstract class Pit extends Hazard {
  private final static char symbol = 'A';
  private final String warning = "You feel a slight breeze";

  @Override
  public String getWarning() {
    return warning;
  }
}
