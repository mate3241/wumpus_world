package hu.flowacademy;

public enum Difficulty{
  EASY(6, 2, 5, 3, 2),
  MEDIUM(9, 5, 8, 2, 1),
  HARD(11, 8, 10, 1, 1);
  public final int size;
  public final int bat;
  public final int pit;
  public final int arrow;
  public final int meat;

  Difficulty(int size, int bat, int pit, int arrow, int meat){
    this.size = size;
    this.bat = bat;
    this.pit = pit;
    this.arrow = arrow;
    this.meat = meat;
  }
}