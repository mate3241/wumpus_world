package hu.flowacademy;

public class Wumpus extends Hazard implements Movable {
  @Override
  public void move(char direction) {

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
