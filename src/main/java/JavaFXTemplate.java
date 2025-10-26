import javafx.scene.control.*;
import kenoMenu.MenuBarStart;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;

public class JavaFXTemplate extends Application {
    TextField text;
    Button startToGameButton;
    HashMap<String, Scene> sceneMap;
    MenuBar menuBar;
    GridPane daGrid = new GridPane();
    Button PlayButton;
    Button playAgain;

    PauseTransition pause = new PauseTransition(Duration.seconds(3));
    Player player = new Player();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

    //feel free to remove the starter code from this method
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        primaryStage.setTitle("Welcome to Keno Game(Project 2)");

        startToGameButton = new Button("Start Game");
        sceneMap = new HashMap<>();
        text = new TextField();
        menuBar =  new MenuBar();
        PlayButton = new Button("Play");
        playAgain = new Button("Play Again");

        startToGameButton.setOnAction(e -> primaryStage.setScene(sceneMap.get("game")));
        PlayButton.setOnAction(e -> {primaryStage.setScene(sceneMap.get("result"));});//switch to drawing then results
        playAgain.setOnAction(e -> {primaryStage.setScene(sceneMap.get("start")); resetBoardForPlayAgain();});
        //# of scenes returned from # of methods; put in hashmap
        sceneMap.put("start", createStartScene());
        sceneMap.put("game", createGameScene());
        sceneMap.put("result", resultScene());
        sceneMap.put("drawing", createDrawingScene());

        primaryStage.setScene(sceneMap.get("start"));
        primaryStage.show();
    }

    public Scene createStartScene() {
        text.setText("Welcome to Keno Game(Project 2)");
        text.setPrefWidth(250);
        text.setEditable(false);
        //CSS
        text.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold;");
        text.setAlignment(Pos.CENTER);
        VBox paneCenter = new VBox(10, text , startToGameButton);
        paneCenter.setAlignment(Pos.CENTER);

        MenuBar menuBarStart = new MenuBarStart();

        BorderPane pane = new BorderPane();
        pane.setCenter(paneCenter);
        pane.setTop(menuBarStart);
        pane.setStyle("-fx-background-color: skyblue;");
        return new Scene(pane, 850, 750);
    }

    public Scene createGameScene() {

        MenuBar menuBarGame =  new MenuBarStart.MenuBarGame();

        int num = 1;
        for(int _x = 0; _x < 10; _x++ ){
            for(int _y = 0; _y < 8; _y++ ){
                final int currentNum = num;
                Button button = new Button(""+ num);
                button.setMinWidth(40);
                button.setOnAction(e -> {Button curr = (Button) e.getSource(); handleChoice(currentNum, curr);});
                daGrid.add(button, _y, _x);
                num++;
            }
        }
        daGrid.setDisable(true);
        daGrid.setAlignment(Pos.CENTER_LEFT);

//        //pick rolls
        Button pick1Button = new Button("  1 picks");
        pick1Button.setMinWidth(55);
        pick1Button.setOnAction(event -> handleHowManyPicks(1));

        Button pick4Button = new Button(" 4 picks");
        pick4Button.setMinWidth(55);
        pick4Button.setOnAction(event -> handleHowManyPicks(4));

        Button pick8Button = new Button(" 8 picks");
        pick8Button.setMinWidth(55);
        pick8Button.setOnAction(event -> handleHowManyPicks(8));

        Button pick10Button = new Button("10 picks");
        pick10Button.setMinWidth(55);
        pick10Button.setOnAction(event -> handleHowManyPicks(10));

        VBox pickBut = new VBox(pick1Button, pick4Button, pick8Button, pick10Button);
        pickBut.setSpacing(15);
        pickBut.setAlignment(Pos.CENTER);
//        //end on pick roles

        Button randomPicks = new Button("Quick Fill All");
        randomPicks.setOnAction(e -> {player.quickPickAll(); handleRQuickFillLook();});

        Button randomFill = new Button("Quick Fill");
        randomFill.setOnAction(e -> {player.quickFill(); handleRQuickFillLook();});

        Button clear = new Button("clear");
        clear.setOnAction(e -> {player.clearPicks(); resetButtons();});
        PlayButton.setDisable(true);
//        PlayButton.setOnAction(e -> {primaryStage.setScene(sceneMap.get("game"))});

        VBox RightButtons = new VBox(pickBut, randomFill, randomPicks, clear, PlayButton);
        RightButtons.setSpacing(15);
        RightButtons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: skyblue;");
        root.setTop(menuBarGame);
        root.setCenter(daGrid);
        root.setRight(RightButtons);

        return new Scene(root, 850, 750);
    }//end of game choice scene

    public  Scene resultScene() {
        BorderPane pane = new BorderPane();
        MenuBar menuBarGame = new MenuBarStart.MenuBarGame();
        // textbox for results of game button bottom center, play again bringing them back to start
        Label resultLabel = new Label("Results\n$" + player.getTotalWinning());
        resultLabel.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold; -fx-text-alignment: center;");

//        Button
        playAgain.setPrefSize(200,100);
//        playAgain.setOnAction(e -> {
                    // switch to start screen
//                });
        VBox centered = new VBox(40, resultLabel, playAgain);
        centered.setAlignment(Pos.CENTER);

        pane.setTop(menuBarGame);
        pane.setCenter(centered);
        BorderPane.setAlignment(playAgain, Pos.CENTER);
        pane.setStyle("-fx-background-color: skyblue;");
        return new Scene(pane, 850, 750);
    }

    public Scene createDrawingScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: red;");
        return new Scene(root, 850, 750);
    }

    public void handleChoice(int num, Button currButton){
        if(!player.getplayerPicks().contains(num)){
            if(player.getPlayerPickSize() < player.getMaxPicks()) {
                player.addPlayerChoice(num);
                currButton.setStyle("-fx-opacity: 0.7; -fx-background-color: green;");
            }
            if(player.getPlayerPickSize() >= player.getMaxPicks()) {
                PlayButton.setDisable(false);
            }

        }else{
            player.removePlayerChoice(num);
            currButton.setStyle("");
            currButton.setStyle("-fx-opacity: 1;");
            PlayButton.setDisable(true);
        }
    }

    public void handleHowManyPicks(int num){
        daGrid.setDisable(false);
        resetButtons();
        System.out.println("Player picks: " + player.getplayerPicks());
        player.setMaxPicks(num);
        if(player.getplayerPicks().size() > player.getMaxPicks()) {
            player.clearPicks();
        }
    }

    public void resetButtons(){
        for (int i = 0; i < 80; i++) {
            Node node = daGrid.getChildren().get(i);
            if (node instanceof Button) {
                node.setStyle("");
                node.setStyle("-fx-opacity: 1;");
            }
        }
        PlayButton.setDisable(true);
        System.out.println("buttons reset");
    }

    public void handleRQuickFillLook(){
//        player.quickFill();
        System.out.println("Player picks: " + player.getplayerPicks());
//        System.out.println(player.getplayerPicks());
        resetButtons();
        for (int i = 0; i < 80; i++) {
            Node node = daGrid.getChildren().get(i);
            if (node instanceof Button){
                if(player.getplayerPicks().contains(i+1)){
                    node.setStyle("-fx-opacity: 0.7;  -fx-background-color: green;");
                }
//                else{
//                    node.setStyle("-fx-opacity: 1; -fx-background-color: lightgray;");
//                }
            }
        }
        PlayButton.setDisable(false);
    }

    public void resetBoardForPlayAgain(){
        player.clearPicks();
        resetButtons();
        daGrid.setDisable(true);
        PlayButton.setDisable(true);
    }
}//end of class
