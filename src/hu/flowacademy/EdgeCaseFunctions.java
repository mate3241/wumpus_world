package hu.flowacademy;

public class EdgeCaseFunctions {

  protected static boolean canGoThatWay(char key, Map map) {
    switch (key) {
      case 'w':
        if (map.player.getX() > 0) {
          return true;
        }
        break;
      case 's':
        if (map.player.getX() < map.squares.length - 1) {
          return true;
        }
        break;
      case 'a':
        if (map.player.getY() > 0) {
          return true;
        }
        break;
      case 'd':
        if (map.player.getY() < map.squares.length - 1) {
          return true;
        }
        break;
      default:
        return false;
    }
    return false;
  }
  void emitStuff(){

  }
}
