import javafx.animation.PauseTransition;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
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

        startToGameButton.setOnAction(e -> primaryStage.setScene(sceneMap.get("game")));

        //two scenes returned from two methods; put in hashmap
        sceneMap.put("start", createStartScene());
        sceneMap.put("game", createGameScene());

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

        Menu menu = new Menu("Menu ⬇");

        MenuItem Rules = new MenuItem("Rules");
        MenuItem Odds = new MenuItem("Odds");
        MenuItem Exit = new MenuItem("Exit");

        menu.getItems().addAll(Rules, Odds, Exit);
        menuBar.getMenus().addAll(menu);

        Rules.setOnAction(e -> {
            Stage ruleStage = new Stage();
            TextArea ruleText = new TextArea();
            ruleStage.setTitle("Keno Rules");
            ruleText.setText("1. Decide how much to play per draw. Each play costs $1. Play for $2 to double your prize; play for $3 to triple your prize and so on up to $10 per play.\n" +
                    "Select how many consecutive draws to play. Pick up to 20. Drawings happen every 4 minutes.\n" +
                    "Select how many numbers(spots) to match from 1 to 10. The number of Spots you choose and the amount you play per draw will determine the amount you could win. See the prize chart to determine the amount you could win with a $1 play.\n" +
                    "Pick as many numbers as you did Spots. You can select numbers from 1 to 80 or choose Quick Pick and let the computer terminal randomly pick some or all of these numbers for you.\n" +
                    "Add Multiplier to increase all prizes up to 10X. Multiplier doubles base ticket cost.");
            ruleStage.show();
            }
        );

        //menu.getintems.addAll()

        BorderPane pane = new BorderPane();
        pane.setCenter(paneCenter);
        pane.setTop(menuBar);
        pane.setStyle("-fx-background-color: skyblue;");
        return new Scene(pane, 850, 750);
    }

    public Scene createGameScene() {

        player.playerPicks = new ArrayList<>();

        GridPane daGrid = new GridPane();
        int num = 1;
        for(int _x = 0; _x < 10; _x++ ){
            for(int _y = 0; _y < 8; _y++ ){
                Button button = new Button(""+ num);
                button.setMinWidth(40);
//                button.setOnAction(e -> {player.addPlayerChoice(num); button.setDisable(false); });
                daGrid.add(button, _y, _x);
                num++;
            }
        }
        daGrid.setDisable(true);
        daGrid.setAlignment(Pos.CENTER_LEFT);

        //button e_> get.source
//
//        //pick rolls
        Button pick1Button = new Button("  1 roll");
        pick1Button.setMinWidth(55);
//        pick1Button.setOnAction(event -> {});
        Button pick4Button = new Button(" 4 rolls");
        pick4Button.setMinWidth(55);
        Button pick8Button = new Button(" 8 rolls");
        pick8Button.setMinWidth(55);
        Button pick10Button = new Button("10 rolls");
        pick10Button.setMinWidth(55);

        VBox pickBut = new VBox(pick1Button, pick4Button, pick8Button, pick10Button);
        pickBut.setSpacing(15);
        pickBut.setAlignment(Pos.CENTER);
//        //end on pick roles
//
//

        Button randomPicks = new Button("random");
//        randomPicks.setOnAction(e -> {});

        Button clear = new Button("clear");
//        clear.setOnAction(e -> {})

        Button PlayButton = new Button("Play");
        PlayButton.setDisable(true);
//        PlayButton.setOnAction(e -> {});

        VBox RightButtons = new VBox(pickBut, randomPicks, clear, PlayButton);
        RightButtons.setSpacing(15);
        RightButtons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: skyblue;");
        root.setCenter(daGrid);

        root.setRight(RightButtons);//

//        Scene scene = new Scene(root, 700, 700);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        return new Scene(root, 850, 750);
    }//end of game choice scene

}//end of class
