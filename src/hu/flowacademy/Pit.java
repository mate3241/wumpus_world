package hu.flowacademy;

public class Pit extends Hazard {
  private final static char symbol = 'A';
  @Override
  void interaction(Player player) {
    if (Math.random() > 0.3) {
      System.out.println("you dead");
    } else {
      System.out.println("new level");
    }
  }

  @Override
  public char getSymbol() {
    return symbol;
  }
}
