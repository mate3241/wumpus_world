package hu.wumpusworld.main;

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
  boolean won;

  @Override
  public void move(char direction, Map map) {
    switch(direction){
      case 'w':
        setX(getX() - 1);
        break;
      case 's':
        setX(getX() + 1);
        break;
      case 'a':
        setY(getY() - 1);
        break;
      case 'd':
        setY(getY() + 1);
        break;
    }
    interact(map.squares[getX()][getY()]);

    map.squares[getX()][getY()].isExplored = true;
  }

  private void interact(Square square) {
    if (square.getHazard().isPresent()){
      square.getHazard().get().interaction(this);
    } else if (square.getWumpus().isPresent()){
      square.getWumpus().get().interaction(this);
    } else if (square.getMeat().isPresent()){
      setMeat(getMeat() + 1);
      System.out.println("You picked up a piece of meat");
      square.setMeat(null);
    }

  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public Player(String name, int arrow, int meat) {
    this.name = name;
    setX(0);
    setY(0);
    setAlive(true);
    setArrow(arrow);
    setTeleported(false);
    won = false;
    setMeat(meat);
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
        if(getX() > 0 && map.squares[getX() - 1][getY()].getWumpus().isPresent()){
          win();
        } else {
          System.out.println("You didn't hit anything");
          setArrow(getArrow() - 1);
        }
        break;
      case '2':
        if(getX() < map.squares.length - 1 && map.squares[getX() + 1][getY()].getWumpus().isPresent()){
          win();
        }
        else {
          System.out.println("You didn't hit anything");
          setArrow(getArrow() - 1);
        }
        break;
      case '4':
        if(getY() > 0 && map.squares[getX()][getY() - 1].getWumpus().isPresent()){
          win();
        } else {
          System.out.println("You didn't hit anything");
          setArrow(getArrow() - 1);
        }
        break;
      case '6':
        if(getY() < map.squares.length - 1 && map.squares[getX()][getY() + 1].getWumpus().isPresent()){
          win();
        } else {
          System.out.println("You didn't hit anything");
          setArrow(getArrow() - 1);
        }
        break;
    }
  };
  public void detect(){};
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

    System.out.println("player panicked");
  }

  /*public void placeAtRandom() {
    MapGenerator mg = new MapGenerator()
    int x =
  }*/
}
