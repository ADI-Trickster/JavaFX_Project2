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

import java.util.ArrayList;
import java.util.HashMap;

public class JavaFXTemplate extends Application {
    TextField text;
    Button startToGameButton;
    HashMap<String, Scene> sceneMap;
    MenuBar menuBar;
    GridPane daGrid = new GridPane();
    GridPane gridToMatch  = new GridPane();
    Button PlayButton;
    Button playAgain;
    Button toResults;
    Button randomPicks;
    Button randomFill;
    Button clear;

    PauseTransition pause = new PauseTransition(Duration.seconds(3));
    Player player = new Player();
    playGame playTheGame = new playGame();

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
        toResults =  new Button("To Results");

        startToGameButton.setOnAction(e -> primaryStage.setScene(sceneMap.get("game")));
        PlayButton.setOnAction(e -> primaryStage.setScene(sceneMap.get("drawing")));
        playAgain.setOnAction(e -> {primaryStage.setScene(sceneMap.get("start")); resetBoardForPlayAgain();});
        toResults.setOnAction(e -> primaryStage.setScene(sceneMap.get("result")));
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
        pick1Button.setOnAction(event -> {handleHowManyPicks(1);});

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

        randomPicks = new Button("Quick Fill All");
        randomPicks.setOnAction(e -> {player.quickPickAll(); handleQuickFillLook();});
        randomPicks.setDisable(true);

        randomFill = new Button("Quick Fill");
        randomFill.setOnAction(e -> {player.quickFill(); handleQuickFillLook();});
        randomFill.setDisable(true);

        clear = new Button("clear");
        clear.setOnAction(e -> {player.clearPicks(); resetButtons();});
        clear.setDisable(true);

        PlayButton.setDisable(true);

        VBox RightButtons = new VBox(pickBut, randomFill, randomPicks, clear, PlayButton);
        RightButtons.setSpacing(15);
        RightButtons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        MenuBar menuBarGame =  new MenuBarStart.MenuBarGame(root);
        root.setStyle("-fx-background-color: skyblue;");
        root.setTop(menuBarGame);
        root.setCenter(daGrid);
        root.setRight(RightButtons);

        return new Scene(root, 850, 750);
    }//end of game choice scene

    public  Scene resultScene() {
        BorderPane pane = new BorderPane();
        MenuBar menuBarGame = new MenuBarStart.MenuBarGame(pane);
        Label resultLabel = new Label("Results\n$" + player.getTotalWinning());
        resultLabel.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold; -fx-text-alignment: center;");
        playAgain.setPrefSize(200,100);
        VBox centered = new VBox(40, resultLabel, playAgain);
        centered.setAlignment(Pos.CENTER);

        pane.setTop(menuBarGame);
        pane.setCenter(centered);
        BorderPane.setAlignment(playAgain, Pos.CENTER);
        pane.setStyle("-fx-background-color: skyblue;");
        return new Scene(pane, 850, 750);
    }

    public Scene createDrawingScene(){
        System.out.println("Creating Drawing Scene");
        playTheGame.draw20Numbers();
        System.out.println("Got 20 numbers");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: red;");

        int num = 1;
        for(int _x = 0; _x < 10; _x++ ){
            for(int _y = 0; _y < 8; _y++ ){
                Button button = new Button(""+ num);
                button.setMinWidth(40);
                gridToMatch.add(button, _y, _x);
                num++;
            }
        }

        Button toMatch = new Button("spin");
        toMatch.setMinWidth(40);

        ArrayList<Integer> matched = playTheGame.matchNumbers(player.getPlayerPicks());
        toMatch.setOnAction(e -> {playTheGame.matchNumbers(player.getPlayerPicks());});
        //matched = playTheGame.matchNumbers(player.getPlayerPicks());
        System.out.println(player.getPlayerPicks());
        System.out.println("Got matching numbers: "+ matched);
        for (int i = 0; i < 80; i++) {
            Node node = gridToMatch.getChildren().get(i);
            if (node instanceof Button){
                if(matched.contains(i+1)){
//                    System.out.println(player.getPlayerPicks());
                    node.setStyle("-fx-background-color: red;");
                }
//                else{
//                    node.setStyle("-fx-opacity: 1;  -fx-background-color: green;");
////                    node.setStyle("-fx-opacity: 0.7;");
//                }
            }
        }
//        handleDrawing(matched);

        gridToMatch.setAlignment(Pos.CENTER);
        toResults.setPrefSize(200, 100);
        VBox centered = new VBox(40, gridToMatch,toMatch, toResults);
        centered.setAlignment(Pos.CENTER);
        root.setCenter(centered);

        return new Scene(root, 850, 750);
    }

    public void handleChoice(int num, Button currButton){
        if(!player.getPlayerPicks().contains(num)){
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
        randomPicks.setDisable(false);
        randomFill.setDisable(false);
        clear.setDisable(false);
        System.out.println("Player picks: " + player.getPlayerPicks());
        player.setMaxPicks(num);
        if(player.getPlayerPicks().size() > player.getMaxPicks()) {
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

    public void handleQuickFillLook(){
        resetButtons();
        System.out.println("Player picks: " + player.getPlayerPicks());
        for (int i = 0; i < 80; i++) {
            Node node = daGrid.getChildren().get(i);
            if (node instanceof Button){
                if(player.getPlayerPicks().contains(i+1)){
                    node.setStyle("-fx-opacity: 0.7;  -fx-background-color: green;");
                }
//                else{
//                    node.setStyle("-fx-opacity: 1; -fx-background-color: lightgray;");
//                }
            }
        }
        PlayButton.setDisable(false);
    }

    public void handleDrawing(ArrayList<Integer> matches){
        //players numbers get drawn
        System.out.println(player.getPlayerPicks());

        for (int i = 0; i < 80; i++) {
            Node node = gridToMatch.getChildren().get(i);
            if (node instanceof Button){
                if(player.getPlayerPicks().contains(i+1)){
                    node.setStyle("-fx-opacity: 1;  -fx-background-color: green;");
                }
                else{
                    node.setStyle("-fx-opacity: 0.7;");
                }
            }
        }
//        for (int i = 0; i < 80; i++) {
//            Node node = gridToMatch.getChildren().get(i);
//            if (node instanceof Button) {
//                for (int x = 0; x < matches.size(); x++) {
//                    // for every match the button changes to gold
////                    with a pause between each change
//                }
//            }
//        }
//        for (int i = 0; i < matches.size(); i++) {
//            int change = matches.get(i);
//            Node node = gridToMatch.getChildren().get(change - 1);
//            PauseTransition drawingPause = new PauseTransition(Duration.seconds(2);
//
//            if (node instanceof Button) {
//                Button button = (Button) node;
//
//                // Create a pause for each number with increasing delay
//                PauseTransition pause = new PauseTransition(Duration.seconds(2 * i)); // 2 seconds between each draw
//
//                pause.setOnFinished(e -> {
//                    // Highlight the drawn number in gold
//                    button.setStyle("-fx-opacity: 1; -fx-background-color: gold;");
//                });
//
//                pause.play();
//            }
//
//        }

    }//end of drawing func

    public void resetBoardForPlayAgain(){
        player.clearPicks();
        resetButtons();
        daGrid.setDisable(true);
        PlayButton.setDisable(true);
    }
}//end of class
