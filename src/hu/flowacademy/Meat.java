package hu.flowacademy;

public class Meat {
  private final static char symbol = 'M';
  private boolean isRotten;
  public Meat(Difficulty difficulty) {
    if (difficulty.equals(Difficulty.HARD)) {
      this.isRotten = true;
    }
  }
  public String smell() {
    if (isRotten){
      return "You smell something terrible";
    }
    return "";
  }

}
