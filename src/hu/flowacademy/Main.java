package hu.flowacademy;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
  static Scanner scanner = new Scanner(System.in);
  static boolean testMode;

  public static void main(String[] args) {
    if (args.length > 0 && "t".equals(args[0])) {
      testMode = true;
    }
    Map map = setupMap();

    mainGameLoop(map);
  }

  private static Map setupMap() {
    System.out.println("Game Difficulty? (EASY, MEDIUM, HARD)");
    Difficulty difficulty = parseDifficulty(scanner.nextLine());
    MapGenerator mapGenerator = new MapGenerator(difficulty, testMode);
    Map map = mapGenerator.generateMap();
    System.out.println("Player name?");
    String playerName = scanner.nextLine();
    mapGenerator.populateMap(map, playerName);
    return map;
  }

  private static void mainGameLoop(Map map) {
    while (map.player.isAlive()) {
      map.printMap();
      char direction = scanner.next().charAt(0);
      if (MovementHelper.canGoThatWay(direction, map, map.player)) {
        map.player.move(direction);

      }
    }
  }

  public static Difficulty parseDifficulty(String difficulty) {
    for (Difficulty d : Difficulty.values()
    ) {
      if (d.toString().equals(difficulty.toUpperCase())) {
        return d;
      }
    }
    throw new NoSuchElementException("Choose from the list please (EASY, MEDIUM, HARD)");
  }
}
