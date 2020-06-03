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
    return Optional.ofNullable(hazard);
  }

  public char determineSymbol(boolean testMode) {
    if (testMode){
      if (getHazard().isPresent()){
        return getHazard().get().getSymbol();

      } else {
        return '.';
      }
    }
    else {
      if (isExplored && getHazard().isPresent() ){
        return getHazard().get().getSymbol();
      } else if (isExplored) {
        return '.';
      } else {
        return '?';
      }
    }
  }
}
