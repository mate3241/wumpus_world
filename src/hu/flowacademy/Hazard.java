package hu.flowacademy;

public abstract class Hazard {
  String description;
  abstract void interaction(Player player);
}
