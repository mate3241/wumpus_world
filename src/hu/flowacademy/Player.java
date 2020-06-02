package hu.flowacademy;

public class Player implements Movable {

  public String name;
  private int arrow;
  private int meat;
  private int x;
  private int y;
  char symbol = 'P';
  boolean alive;

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    alive = alive;
  }

  public Player(String name) {
    this.name = name;
    setX(0);
    setY(0);

  }

  public void move(char direction){
    switch(direction){
      case 'w':
        setX(getX() - 1);
        break;
      case 's':
        setX(getX() + 1);
        break;
      case 'a':
        setX(getY() - 1);
        break;
      case 'd':
        setX(getY() + 1);
        break;
    }
  }
  public void shoot(char direction){};
  public void detect(){};
  public void die(){
    setAlive(false);
    System.out.println("REKT");

  }
  public void win(){};

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

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void teleport() {
    System.out.println("player teleported");
  }
  public void panic() {
    System.out.println("player panicked");
  }
}
