package hu.wumpusworld.utils;

import hu.wumpusworld.main.Map;
import hu.wumpusworld.main.Movable;

public class MovementHelper {
  static char generateRandomDirection(){
    double random = Math.random();
    if (random > 0.75) {
      return 'w';
    }
    if (random > 0.5) {
      return 's';
    }
    if (random > 0.25) {
      return 'a';
    }
    return 'd';
  }
   public static boolean canGoThatWay(char key, Map map, Movable movable) {
    switch (key) {
      case 'w':
        if (movable.getX() > 0) {
          return true;
        }
        break;
      case 's':
        if (movable.getX() < map.squares.length - 1) {
          return true;
        }
        break;
      case 'a':
        if (movable.getY() > 0) {
          return true;
        }
        break;
      case 'd':
        if (movable.getY() < map.squares.length - 1) {
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
