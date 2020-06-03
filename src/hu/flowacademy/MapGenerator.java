package hu.flowacademy;

import java.util.Random;

public class MapGenerator {
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
    return new Map(squares, testMode);
  }

  public void populateMap(Map map, String name) {
    map.player = new Player(name);
    generateWumpus(map);
    generateBats(map);
    //generatePits(map);
    //generateMeat(map);
  }

  private void generateWumpus(Map map) {
    int randomX = generateRandomNumber();
    int randomY = generateRandomNumber();
    if (randomX == 0 && randomY == 0) {
      generateWumpus(map);
    } else {
      map.wumpus = new Wumpus(randomX, randomY);
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
      if (randomX == 0 && randomY == 0) {
        i--;
      } else if (map.squares[randomX][randomY].getHazard().isPresent()) {
        i--;
      } else if (randomX == map.wumpus.getX() && randomY == map.wumpus.getY()) {
        i--;
      }
       else map.squares[randomX][randomY].setHazard(new Bat());
      }
    }


  private void generatePits(Map map) {
    for (int i = 0; i < difficulty.pit; i++) {
      int randomX = generateRandomNumber();
      int randomY = generateRandomNumber();
      if (randomX == 0 && randomY == 0) {
        i--;
      } else if (map.squares[randomX][randomY].getHazard().isPresent()) {
        i--;
      } else {
        map.squares[randomX][randomY].setHazard(new Bat());
      }
    }
  }
}
