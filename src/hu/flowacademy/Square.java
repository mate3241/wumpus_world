package hu.flowacademy;

public class Square {

  private Meat meat;
private String[] descriptions;
private Hazard hazard;
boolean isExplored;
int x;
int y;

  public void setMeat(Meat meat) {
    this.meat = meat;
  }

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
    isExplored = false;
  }

  public void setHazards(Hazard hazard) {
    this.hazard = hazard;
  }
  public char determineSymbol() {
    if (isExplored){
      return '.';
    }
    return '?';
  }
}
