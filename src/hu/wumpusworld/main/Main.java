package hu.wumpusworld.main;

import hu.wumpusworld.utils.Difficulty;
import hu.wumpusworld.utils.MovementHelper;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
  Scanner scanner = new Scanner(System.in);
  boolean testMode;
  Difficulty difficulty;
  String playerName;

  public static void main(String[] args) {
    Main main = new Main();
    if (args.length > 0 && "t".equals(args[0])) {
      main.testMode = true;
    }
    Map map = main.setupMap();
    main.mainGameLoop(map);
  }

  private Map setupMap() {
    System.out.println("Game Difficulty? (EASY, MEDIUM, HARD)");
    difficulty = parseDifficulty(scanner.nextLine());
    MapGenerator mapGenerator = new MapGenerator(difficulty, testMode);
    Map map = mapGenerator.generateMap();
    System.out.println("Player name?");
    playerName = scanner.nextLine();
    mapGenerator.populateMap(map, playerName);
    return map;
  }

  private void mainGameLoop(Map map) {
    while (map.player.isAlive() && !map.player.won) {
      if (map.player.teleported){
        map.player.setTeleported(false);
        newLevel();
      }
      map.printMap();
      char direction = scanner.next().charAt(0);
      if (MovementHelper.canGoThatWay(direction, map, map.player) && (direction == 'w' || direction == 'a' || direction == 's' || direction == 'd')) {
        map.player.move(direction, map);
        map.CHANGETHISNAMELATER(map.squares[map.player.getX()][map.player.getY()]);
      }
      else {map.player.shoot(direction, map);}
    }
  }
  private void newLevel() {
   MapGenerator mapGenerator = new MapGenerator(difficulty, testMode);
    Map newMap = mapGenerator.generateMap();
    mapGenerator.populateMap(newMap, playerName);
    mainGameLoop(newMap);
  }

  public static Difficulty parseDifficulty(String difficulty) {
    for (Difficulty d : Difficulty.values()
    ) {
      if (d.toString().equals(difficulty.toUpperCase())) {
        return d;
      }
    }
    return Difficulty.MEDIUM;
  }
}
