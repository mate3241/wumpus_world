package hu.flowacademy;

public class Pit extends Hazard {
  @Override
  void interaction(Player player) {
    System.out.println("pit");
  }
}
