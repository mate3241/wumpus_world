package hu.wumpusworld.main;

import hu.wumpusworld.utils.Difficulty;
import hu.wumpusworld.utils.MovementHelper;
import hu.wumpusworld.utils.Serializer;

import java.io.*;
import java.util.Scanner;


public class Main implements java.io.Serializable {
  Scanner scanner = new Scanner(System.in);
  boolean testMode;
  Difficulty difficulty;
  String playerName;
  MapGenerator mapGenerator;
  Serializer serializer;
  Map map;

  public static void main(String[] args) throws IOException {
    Main main = new Main();
    if (args.length > 0 && "t".equals(args[0])) {
      main.testMode = true;
    }
    main.start();
      }

  private void start() {
    map = this.setupMap();
    this.clearTerminalLinux();
    map.printMap();
    try {
      this.mainGameLoop(map);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private Map setupMap() {
    System.out.println("Game Difficulty? (EASY, MEDIUM, HARD)");
    difficulty = parseDifficulty(scanner.nextLine());
    mapGenerator = new MapGenerator(difficulty, testMode);
    Map map = mapGenerator.generateMap();
    System.out.println("Player name?");
    playerName = scanner.nextLine();
    mapGenerator.populateMap(map, playerName);
    return map;
  }

  private void mainGameLoop(Map map) throws IOException {
    while (map.player.isAlive() && !map.player.won) {
      if (map.player.teleported) {
        map.printMap();
        map.player.setTeleported(false);
        newLevel();
      }
      String input = scanner.next();
      if ("save".equals(input)) {
        this.saveGame();
        continue;
      }
      if ("load".equals(input)) {
        try {
          this.loadGame();
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
      char direction = input.charAt(0);
      clearTerminalLinux();
      if (MovementHelper.canGoThatWay(direction, map, map.player)) {
        map.player.move(direction, map);
        if (map.player.isAlive()) {
          map.printMap();
          map.getWarningsFromNeighbors(map.squares[map.player.getX()][map.player.getY()]);
        }
      } else {
        map.player.shoot(direction, map);
      }
    }

  }

  private void newLevel() throws IOException {
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

  private void clearTerminalLinux() {
    System.out.print("\033[H\033[2J");
  }

  public void saveGame() throws IOException {
    Serializer serializer = new Serializer();
    serializer.setMapGenerator(mapGenerator);
    serializer.setMap(map);
    FileOutputStream fileOut = new FileOutputStream("save.sav");
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    out.writeObject(serializer);
    out.close();
  }

  public void loadGame() throws IOException, ClassNotFoundException {
    FileInputStream fis = new FileInputStream("save.sav");
    ObjectInputStream ois = new ObjectInputStream(fis);
    this.serializer = (Serializer) ois.readObject();
    this.mapGenerator = serializer.getMapGenerator();
    this.map = serializer.getMap();
    ois.close();
    mainGameLoop(serializer.getMap());
    map.printMap();
  }
}
