package hu.wumpusworld.utils;

import hu.wumpusworld.enemies.Wumpus;
import hu.wumpusworld.main.Main;
import hu.wumpusworld.main.Map;
import hu.wumpusworld.main.MapGenerator;
import hu.wumpusworld.main.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer implements java.io.Serializable {
  Player player;
  Wumpus wumpus;
  Map map;
  Main main;
  MapGenerator mapGenerator;

  public Serializer() {
  }


  public void saveGame() throws IOException {
    FileOutputStream fileOut = new FileOutputStream(this.player.getName() + ".sav");
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    out.writeObject(main);
    out.writeObject(mapGenerator);
    out.writeObject(map);
    out.writeObject(player);
    out.writeObject(wumpus);

    out.close();
  }

  public void savePlayer() throws IOException {
    FileOutputStream fileOut = new FileOutputStream(this.player.getName() + ".sav");
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    out.writeObject(player);
    out.close();

  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setWumpus(Wumpus wumpus) {
    this.wumpus = wumpus;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  public Player getPlayer() {
    return player;
  }

  public Wumpus getWumpus() {
    return wumpus;
  }

  public Map getMap() {
    return map;
  }

  public Main getMain() {
    return main;
  }

  public MapGenerator getMapGenerator() {
    return mapGenerator;
  }

  public void setMapGenerator(MapGenerator mapGenerator) {
    this.mapGenerator = mapGenerator;
  }
}
