package hu.wumpusworld.main;

public interface Movable {
  int x = -1;
  int y = -1;
  default void move(char direction, Map map) {
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
