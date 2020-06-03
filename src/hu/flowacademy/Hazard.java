package hu.flowacademy;

public abstract class Hazard {
  String description;
  private char symbol = ' ';
  abstract void interaction(Player player);
  abstract public char getSymbol();

}
