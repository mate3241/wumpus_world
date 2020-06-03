package hu.wumpusworld.main;

import hu.wumpusworld.enemies.Hazard;
import hu.wumpusworld.enemies.Wumpus;
import hu.wumpusworld.utils.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Square {
  private Meat meat;
  private Hazard hazard;
  boolean isExplored;
  private Wumpus wumpus;
  int x;
  int y;
  Coordinates coords;
  public void setMeat(Meat meat) {
    this.meat = meat;
  }
  public void setWumpus(Wumpus wumpus) {
    this.wumpus = wumpus;
  }

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
    isExplored = false;
  }

  public void setHazard(Hazard hazard) {
    Objects.requireNonNull(hazard, "hazard can't be null");
    this.hazard = hazard;
  }

  public Optional<Hazard> getHazard() {
    return Optional.ofNullable(hazard);
  }

  public Optional<Wumpus> getWumpus() {
    return Optional.ofNullable(wumpus);
  }


  public char determineSymbol(boolean testMode) {
    if (testMode) {
      if (getWumpus().isPresent()) {
        return getWumpus().get().getSymbol();
      } else if (getHazard().isPresent()) {
        return getHazard().get().getSymbol();
      } else {
        return '.';
      }
    } else {
      if (isExplored && getWumpus().isPresent()) {
        return getWumpus().get().getSymbol();
      } else if (isExplored && getHazard().isPresent()) {
        return getHazard().get().getSymbol();
      } else if (isExplored) {
        return '.';
      } else {
        return '?';
      }
    }
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
