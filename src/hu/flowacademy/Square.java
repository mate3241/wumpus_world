package hu.flowacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Square {
  private Meat meat;
  private final List<String> descriptions;
  private Hazard hazard;
  boolean isExplored;
  int x;
  int y;

  public void setMeat(Meat meat) {
    this.meat = meat;
  }

  public List<String> getDescriptions() {
    return List.copyOf(descriptions);
  }

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
    isExplored = false;
    this.descriptions = new ArrayList<>();
  }

  public void setHazard(Hazard hazard) {
    Objects.requireNonNull(hazard, "hazard can't be null");
    this.hazard = hazard;
  }
  public Optional<Hazard> getHazard() {
    return Optional.of(hazard);
  }

  public char determineSymbol() {
    if (isExplored && (hazard instanceof Bat)) {
      return ((Bat) hazard).symbol;
    } else if (isExplored && hazard instanceof Wumpus) {
      return ((Wumpus) hazard).symbol;
    } else if (isExplored) {
      return '.';
    } else {
      return '?';
    }
  }
}
