package hu.wumpusworld.graphics;

import hu.wumpusworld.main.Map;
import hu.wumpusworld.main.MapGenerator;
import hu.wumpusworld.utils.Difficulty;
import hu.wumpusworld.utils.MovementHelper;
import hu.wumpusworld.utils.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {
  static GridPane gridPane;
  static BorderPane borderPane;
  boolean testMode = true;
  Difficulty difficulty;
  String playerName = "mate";
  MapGenerator mapGenerator;
  Serializer serializer;
  Map map;
  TextArea textArea = new TextArea();
  Alert loseAlert;
  Scene scene;
  private Alert winAlert;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Main main = new Main();
    Menu newGameMenu = new Menu("_New Game");
    borderPane = new BorderPane();
    gridPane = new GridPane();
    gridPane.setPadding(new Insets(10, 10, 10, 10));
    gridPane.setVgap(8);
    gridPane.setHgap(10);
    List<Character> movementKeys = new ArrayList<>(Arrays.asList('W', 'A', 'S', 'D'));
    List<Character> shootKeys = new ArrayList<>(Arrays.asList(
            KeyCode.UP.getChar().charAt(0), KeyCode.DOWN.getChar().charAt(0), KeyCode.LEFT.getChar().charAt(0), KeyCode.RIGHT.getChar().charAt(0)));

    scene = new Scene(borderPane, 850, 900);

    scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
      if (movementKeys.contains(event.getCode().getChar().charAt(0))) {
        try {
          mainGameLoop(event.getCode().getChar().charAt(0));
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (shootKeys.contains(event.getCode().getChar().charAt(0))) {
        map.player.shoot(event.getCode().getChar().charAt(0), map);
      }
      event.consume();
      if (map.player.won) {
        popUpWin(winAlert);
        map.player.won = false;
    }});
    primaryStage.setTitle("Wumpus World");
    primaryStage.setScene(scene);
    primaryStage.show();
    for (Difficulty d : Difficulty.values()
    ) {
      MenuItem currentDiff = new MenuItem(d.toString());
      newGameMenu.getItems().add(currentDiff);
      currentDiff.setOnAction(e -> {
        difficulty = d;
        begin();

      });
    }

    Menu saveLoadMenu = new Menu("Save / Load Game");
    Menu testModeMenu = new Menu("Test Mode");
    ToggleGroup toggleTest = new ToggleGroup();
    RadioMenuItem on = new RadioMenuItem("On");
    RadioMenuItem off = new RadioMenuItem("Off");
    on.setToggleGroup(toggleTest);
    off.setToggleGroup(toggleTest);
    testModeMenu.getItems().addAll(on, off);
    testModeMenu.setOnAction(e -> {
      testMode = on.isSelected();
    });
    MenuBar menuBar = new MenuBar();
    menuBar.getMenus().addAll(newGameMenu, saveLoadMenu, testModeMenu);
    MenuItem saveButton = new MenuItem("Save");
    saveButton.setOnAction(e -> {
      try {
        saveGame();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });
    MenuItem loadButton = new MenuItem("Load");
    loadButton.setOnAction(e -> {
      try {
        loadGame();
      } catch (IOException | ClassNotFoundException ioException) {
        ioException.printStackTrace();
      }
    });
    saveLoadMenu.getItems().addAll(saveButton, loadButton);
    HBox topMenu = new HBox();
    Button topButton = new Button("_New game");
    Button topButton2 = new Button("Difficulty");


    topMenu.getChildren().addAll(topButton, topButton2);

    VBox leftMenu = new VBox();
    leftMenu.setSpacing(20);
    Button northButton = new Button("North");
    moveFromButton(northButton, 'w');
    Button southButton = new Button("South");
    moveFromButton(southButton, 's');
    Button eastButton = new Button("East");
    moveFromButton(eastButton, 'd');
    Button westButton = new Button("West");
    moveFromButton(westButton, 'a');
    leftMenu.getChildren().addAll(northButton, southButton, eastButton, westButton);


    VBox rightMenu = new VBox();
    rightMenu.setSpacing(20);
    Button shootNorthButton = new Button("Shoot North");
    shootFromButton(shootNorthButton, '8');
    Button shootEastButton = new Button("Shoot East");
    shootFromButton(shootEastButton, '6');
    Button shootSouthButton = new Button("Shoot South");
    shootFromButton(shootSouthButton, '2');
    Button shootWestButton = new Button("Shoot West");
    shootFromButton(shootWestButton, '4');
    rightMenu.getChildren().addAll(shootNorthButton, shootSouthButton, shootEastButton, shootWestButton);

    borderPane.setTop(menuBar);
    borderPane.setLeft(leftMenu);
    borderPane.setRight(rightMenu);
    borderPane.setCenter(gridPane);
    borderPane.setBottom(textArea);

    winAlert = new Alert(Alert.AlertType.CONFIRMATION);
    winAlert.setTitle("You won!");
    winAlert.setHeaderText(null);
    winAlert.setContentText("Want to play another round?");
    loseAlert = new Alert(Alert.AlertType.CONFIRMATION);
    loseAlert.setHeaderText(null);
    loseAlert.setTitle("You died a horrible death!");
    loseAlert.setContentText("Want to play another round?");
  }

  private void shootFromButton(Button shootNorthButton, char c) {
    shootNorthButton.setOnAction(e -> {
      map.player.shoot(c, map);
      if (map.player.won) {
        popUpWin(winAlert);
      } else textArea.setText("You didn't hit anything");

    });
  }

  private void moveFromButton(Button moveButton, char c) {
    moveButton.setOnAction(e -> {
      try {
        mainGameLoop(c);
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });
  }

  private void popUpWin(Alert winAlert) {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Optional<ButtonType> result = winAlert.showAndWait();
    if (result.get() == ButtonType.OK) {
      gridPane.getChildren().clear();
    } else {
      System.exit(0);
    }
  }

  void begin() {
    map = setupMap(difficulty, playerName);
    this.clearTerminalLinux();
    map.setGraphics(Main.gridPane);
  }

  private Map setupMap(Difficulty difficulty, String playerName) {
    mapGenerator = new MapGenerator(difficulty, testMode);
    Map map = mapGenerator.generateMap();
    mapGenerator.populateMap(map, playerName);
    return map;
  }


  private void mainGameLoop(char direction) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append("Arrows: ").append(map.player.getArrow()).append("\t").append("Meat: ").append(map.player.getMeat()).append("\n");
    if (map.player.isAlive() && !map.player.won) {

    }
    //clearTerminalLinux();
    if (MovementHelper.canGoThatWay(direction, map, map.player)) {
      map.player.move(direction, map);
      if (map.player.teleported) {
        map.player.setTeleported(false);
        begin();
        gridPane.getChildren().clear();
        map.setGraphics(Main.gridPane);
      }
      if (map.player.isAlive()) {
        map.setGraphics(gridPane);
        sb.append(map.getWarningsFromNeighbors(map.squares[map.player.getX()][map.player.getY()]));
        textArea.setText(sb.toString());
      } else {
        gridPane.getChildren().clear();
        map.setGraphics(gridPane);
        Optional<ButtonType> result = loseAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
          gridPane.getChildren().clear();
        } else {
          System.exit(0);
        }
      }
    }
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
    map.setGraphics(gridPane);
    map.printMap();
  }

    public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void handle(ActionEvent actionEvent) {

  }
}