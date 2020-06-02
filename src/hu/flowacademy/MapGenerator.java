package hu.flowacademy;

public class MapGenerator {

  Difficulty difficulty;
  public MapGenerator(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public Map generateMap(){
    int size;
    switch (difficulty){
      case EASY:
        size = 6;
        break;
      case MEDIUM:
        size = 9;
        break;
      case HARD:
        size = 11;
        break;
      default:
        size = 100;
    }
    Square[][] squares = new Square[size][size];

    for (int i = 0; i < size ; i++) {
      for (int j = 0; j < size; j++) {
        squares[i][j] = new Square(i, j);
      }
    }
    return new Map(squares);
  }
  public void populateMap(Map map, String name){
    map.player = new Player(name);
  }
}
