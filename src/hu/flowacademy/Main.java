package hu.flowacademy;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Game Difficulty? (EASY, MEDIUM, HARD)");
    Difficulty difficulty = parseDifficulty(scanner.nextLine());
    MapGenerator mapGenerator = new MapGenerator(difficulty);
    Map map = mapGenerator.generateMap();
    System.out.println("Player name?");
    String playerName = scanner.nextLine();
    mapGenerator.populateMap(map, playerName);
    while (map.player.isAlive()) {
      map.printMap();
      char direction = scanner.next().charAt(0);
      if (MovementHelper.canGoThatWay(direction, map, map.player)) {
        map.player.move(direction);
      }
    }
  }

  public static Difficulty parseDifficulty(String difficulty){
    for (Difficulty d: Difficulty.values()
         ) {
      if (d.toString().equals(difficulty)) {
        System.out.println(d.toString());
        return Difficulty.EASY;
      }
      else if (d.toString().equals(difficulty))
        return Difficulty.MEDIUM;
      else if (d.toString().equals(difficulty))
        return Difficulty.HARD;
    }
    throw new NoSuchElementException("Choose from the list please (EASY, MEDIUM, HARD)");
  }
}

