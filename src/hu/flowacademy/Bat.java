package hu.flowacademy;

public class Bat extends Hazard {

  @Override
  void interaction(Player player) {
    if (Math.random() > 0.5) {
      System.out.println("Teleport");
      player.teleport();
    } else {
      System.out.println("panic");
      player.panic();
    }
  }
}
