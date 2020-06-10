package hu.wumpusworld.hazards;

public abstract class Pit extends Hazard {
  private final static char symbol = 'A';
  private final String warning = "You feel a slight breeze";

  @Override
  public String getWarning() {
    return warning;
  }
}
