package hu.wumpusworld.main;

import hu.wumpusworld.utils.Difficulty;
import hu.wumpusworld.utils.MovementHelper;
import java.io.Serializable;

public class Player implements Movable, Serializable {

  public String name;
  public boolean teleported;
  private int arrow;
  private int meat;
  private int x;
  private int y;
  char symbol = 'P';
  boolean alive;
  public boolean won;
  public Map map;

  @Override
  public void move(char direction, Map map) {
    switch(direction){
      case 'w':
      case 'W':
        setX(getX() - 1);
        break;
      case 's':
      case 'S':
        setX(getX() + 1);
        break;
      case 'a':
      case 'A':
        setY(getY() - 1);
        break;
      case 'D':
      case 'd':
        setY(getY() + 1);
        break;
    }
    map.squares[getX()][getY()].isExplored = true;
    interact(map.squares[getX()][getY()]);
  }

  private String interact(Square square) {
    if (square.getHazard().isPresent()){
      return square.getHazard().get().interaction(this);
    } else if (square.getWumpus().isPresent()){
      return square.getWumpus().get().interaction(this);
    } else if (square.getMeat().isPresent()){
      setMeat(getMeat() + 1);
      System.out.println("You picked up a piece of meat");
      square.setMeat(null);
      return "You picked up a piece of meat";
    }
    return "";
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public Player(String name, int arrow, int meat, Map map) {
    this.name = name;
    setX(0);
    setY(0);
    setAlive(true);
    setArrow(arrow);
    setTeleported(false);
    won = false;
    setMeat(meat);
    this.map = map;
  }

  public void setTeleported(boolean teleported) {
    this.teleported = teleported;
  }

  public void shoot(char direction, Map map){
    if (getArrow() == 0){
      System.out.println("You have no arrows");
      return;
    }
    switch(direction){
      case '8':
      case '&':
        setArrow(getArrow() - 1);
        if(getX() > 0 && map.squares[getX() - 1][getY()].getWumpus().isPresent()){
          win();
        } else {
          System.out.println("You didn't hit anything");
        }
        break;
      case '2':
      case '(':
        setArrow(getArrow() - 1);
        if(getX() < map.squares.length - 1 && map.squares[getX() + 1][getY()].getWumpus().isPresent()){
          win();
        }
        else {
          System.out.println("You didn't hit anything");
        }
        break;
      case '4':
      case '%':
        setArrow(getArrow() - 1);
        if(getY() > 0 && map.squares[getX()][getY() - 1].getWumpus().isPresent()){
          win();
        } else {
          System.out.println("You didn't hit anything");
        }
        break;
      case '6':
      case '\'':
        setArrow(getArrow() - 1);
        if(getY() < map.squares.length - 1 && map.squares[getX()][getY() + 1].getWumpus().isPresent()){
          win();
        } else {
          System.out.println("You didn't hit anything");
        }
        break;
    }
  }

  public void die(){
    setAlive(false);
    System.out.println("REKT");
  }

  public void win(){
    System.out.println("You won, " + name + "!");
    won = true;
  };

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public String getName() {
    return name;
  }

  public int getArrow() {
    return arrow;
  }

  public int getMeat() {
    return meat;
  }

  public char getSymbol() {
    return symbol;
  }

  public void setArrow(int arrow) {
    this.arrow = arrow;
  }

  public void setMeat(int meat) {
    this.meat = meat;
  }

  @Override
  public void setX(int x) {
    this.x = x;
  }

  @Override
  public void setY(int y) {
    this.y = y;
  }

  public void teleport() {
    setTeleported(true);
    System.out.println("player teleported");
  }

  public void panic() {
    for (int i = 0; i < 3; i++) {
      char direction = MovementHelper.generateRandomDirection();
      if(MovementHelper.canGoThatWay(direction, map, this)) {
        move(direction, map);
      } else {
        i--;
      }
    }
    System.out.println("player panicked");
  }

  public void placeAtRandom(Map map, Difficulty difficulty) {
      for (int i = 0; i < 1; i++) {
        int randomX = MovementHelper.generateRandomNumber(difficulty);
        int randomY = MovementHelper.generateRandomNumber(difficulty);
        if (map.squares[randomX][randomY].getWumpus().isPresent() ||
                map.squares[randomX][randomY].getHazard().isPresent()){
          i--;
        }
        else {
          setX(randomX);
          setY(randomY);
          map.squares[randomX][randomY].isExplored = true;

        }
      }
  }
}
