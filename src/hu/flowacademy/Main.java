package hu.flowacademy;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    MapGenerator mapGenerator = new MapGenerator(Difficulty.EASY);
    Map map = mapGenerator.generateMap();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Player name?");
    String playerName = scanner.nextLine();
    mapGenerator.populateMap(map, playerName);
    char direction;
    while (true) {
      map.printMap();
      direction = scanner.next().charAt(0);
      if (EdgeCaseFunctions.canGoThatWay(direction, map)) {
        map.player.move(direction);
      }
    }
  }
}

