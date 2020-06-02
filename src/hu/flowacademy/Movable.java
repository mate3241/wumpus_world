package hu.flowacademy;

public interface Movable {
  default void move(char direction) {
    switch(direction){
      case 'w':
        setX(getX() - 1);
        break;
      case 's':
        setX(getX() + 1);
        break;
      case 'a':
        setY(getY() - 1);
        break;
      case 'd':
        setY(getY() + 1);
        break;
    }
  };
  int getX();
  int getY();
  void setX(int newX);
  void setY(int newY);
}
