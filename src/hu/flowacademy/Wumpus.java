package hu.flowacademy;

public class Wumpus extends Hazard implements Movable {
  private int x;
  private int y;
  char symbol;
  @Override
  public int getX() {
    return this.x;
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


  @Override
  void interaction(Player player) {
    if (player.getMeat() > 0){
      player.setMeat(player.getMeat() - 1);
      relocate();
    } else {
      player.die();
    }
  }

  private void relocate() {
  }
}
