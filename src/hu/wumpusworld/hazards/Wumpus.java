package hu.wumpusworld.hazards;

import hu.wumpusworld.main.Map;
import hu.wumpusworld.main.Movable;
import hu.wumpusworld.main.Player;
import hu.wumpusworld.utils.MovementHelper;

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

  public Wumpus(int x, int y, Map map) {
    this.x = x;
    this.y = y;
    this.map = map;
  }

  @Override
  public String interaction(Player player) {
    if (player.getMeat() > 0){
      player.setMeat(player.getMeat() - 1);
      relocate();
      return "The Wumpus took a piece of meat and ran away";
    } else {
      System.out.println("The Wumpus tears you apart. Nasty!");
      player.die();
      return "The Wumpus tears you apart. Nasty!";
    }
  }

  @Override
  public char getSymbol() {
    return symbol;
  }

  private void relocate() {
    map.squares[getX()][getY()].setWumpus(null);
    for (int i = 0; i < 3; i++) {
      char direction = MovementHelper.generateRandomDirection();
      if(MovementHelper.canGoThatWay(direction, map, this)) {
        move(direction, map);
      } else {
        i--;
      }
    }
    map.squares[getX()][getY()].setWumpus(this);
  }
}
