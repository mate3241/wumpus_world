package hu.wumpusworld.main;

import hu.wumpusworld.enemies.*;
import hu.wumpusworld.utils.Difficulty;

import java.io.Serializable;
import java.util.Random;

public class MapGenerator implements Serializable {
  boolean testMode;
  Difficulty difficulty;

  public MapGenerator(Difficulty difficulty, boolean testMode) {
    this.testMode = testMode;
    this.difficulty = difficulty;
  }

  public Map generateMap() {
    int size = difficulty.size;
    Square[][] squares = new Square[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        squares[i][j] = new Square(i, j);
      }
    }
    return new Map(squares, testMode, difficulty);
  }

  public void populateMap(Map map, String name) {
    map.player = new Player(name, difficulty.arrow, difficulty.meat, map);
    generateWumpus(map);
    generateBats(map);
    generatePits(map);
    generateMeat(map);
  }

  private void generateMeat(Map map) {
    map.squares[0][0].isExplored = true;
    for (int i = 0; i < difficulty.meat; i++) {
      int randomX = generateRandomNumber();
      int randomY = generateRandomNumber();
      if (map.squares[randomX][randomY].getWumpus().isPresent() ||
          map.squares[randomX][randomY].getHazard().isPresent() ||
          (randomX == 0 && randomY == 0)){
        i--;
      }
      else map.squares[randomX][randomY].setMeat(new Meat(difficulty));
    }

  }

  private void generateWumpus(Map map) {
    int randomX = generateRandomNumber();
    int randomY = generateRandomNumber();
    if (randomX < 2 && randomY < 2) {
      generateWumpus(map);
    } else {
      map.squares[randomX][randomY].setWumpus(new Wumpus(randomX, randomY, map));
    }
  }

  private int generateRandomNumber() {
    Random random = new Random();
    return random.nextInt(difficulty.size);
  }

  private void generateBats(Map map) {
    for (int i = 0; i < difficulty.bat; i++) {
      int randomX = generateRandomNumber();
      int randomY = generateRandomNumber();
      if (randomX < 2 && randomY < 2) {
        i--;
      } else if (map.squares[randomX][randomY].getHazard().isPresent()) {
        i--;
      } else if (map.squares[randomX][randomY].getWumpus().isPresent()) {
        i--;
      } else map.squares[randomX][randomY].setHazard(new Bat());
    }
  }


  private void generatePits(Map map) {
    for (int i = 0; i < difficulty.pit; i++) {
      int randomX = generateRandomNumber();
      int randomY = generateRandomNumber();
      if (randomX < 2 && randomY < 2) {
        i--;
      } else if (map.squares[randomX][randomY].getHazard().isPresent()) {
        i--;
      } else if (map.squares[randomX][randomY].getWumpus().isPresent()) {
        i--;
      } else {
        double random = Math.random();
        if (random > 0.4)
        map.squares[randomX][randomY].setHazard(new DeadlyPit());
        else {
          map.squares[randomX][randomY].setHazard(new TeleportPit());

        }
      }
    }
  }
}

