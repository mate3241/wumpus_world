package hu.flowacademy;

public class Pit extends Hazard {
  @Override
  void interaction(Player player) {
    if (Math.random() > 0.3) {
      System.out.println("you dead");
    } else {
      System.out.println("new level");
    }
  }
}
