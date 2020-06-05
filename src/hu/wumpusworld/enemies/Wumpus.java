package hu.wumpusworld.enemies;

import hu.wumpusworld.main.Movable;
import hu.wumpusworld.main.Player;

import java.io.Serializable;

public class Wumpus extends Hazard implements Movable, Serializable {
  private int x;
  private int y;
  private final char symbol = 'W';
  private final String warning = "You smell something terrible";
  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public String getWarning() {
    return this.warning;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void setX(int newX) {
    this.x = newX;
  }

  @Override
  public void setY(int newY) {
    this.y = newY;
  }

  public Wumpus(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void interaction(Player player) {
    if (player.getMeat() > 0){
      player.setMeat(player.getMeat() - 1);
      relocate();
    } else {
      System.out.println("The Wumpus tears you apart. Nasty!");
      player.die();
    }
  }

  @Override
  public char getSymbol() {
    return symbol;
  }

  private void relocate() {
    System.out.println("The Wumpus ate one piece of meat and ran away");
  }
}
