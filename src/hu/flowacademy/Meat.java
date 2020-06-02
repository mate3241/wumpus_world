package hu.flowacademy;

public class Meat {

  private boolean isRotten;
  public Meat(boolean isRotten) {
    this.isRotten = isRotten;
  }

  public String smell() {
    if (isRotten){
      return "You smell something terrible";
    }
    return "";
  }

}
