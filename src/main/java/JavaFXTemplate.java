import javafx.animation.PauseTransition;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Node;
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
    GridPane daGrid = new GridPane();

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
            ruleText.setWrapText(true);
            ruleText.setEditable(false);
            ruleText.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold;");
            ruleStage.setTitle("Keno Rules");
            ruleText.setText("1. Decide how much to play per draw. Each play costs $1. Play for $2 to double your prize, $3 to triple, $5 to x5, $10 to x10.\n" +
                    "2. Select how many consecutive draws to play. Pick up to 4.\n" +
                    "3. Select how many numbers(spots) to match (1, 4, 8, 10). The number of Spots you choose and the amount you play per draw will determine the amount you could win.\n" +
                    "4. Pick the same amount of numbers as Spots. You can select numbers from 1 to 80 or choose Quick Pick for a random pick some or all of these numbers for you.\n" +
                    "5. Add Multiplier to increase all prizes up to 10X. Multiplier doubles base ticket cost.");
            VBox ruleLayout = new VBox(ruleText);
            Scene ruleScene = new Scene(ruleLayout, 424, 400);
            ruleStage.setMaxHeight(424);
            ruleStage.setScene(ruleScene);
            ruleStage.show();
            }
        );

//        Odds.setOnAction(e -> {
//            Stage oddStage = new Stage();
//            TextArea fullOddsText = new TextArea();
//
//            fullOddsText.setWrapText(false); // Disable wrapping for alignment
//            fullOddsText.setEditable(false);
//
//            // 1. Use a monospaced font for alignment
//            fullOddsText.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 18; -fx-font-weight: bold;");
//
//            // Define the header and data. Use spaces for alignment.
//            String header = "Spots chosen  Odds          with Multiplier\n";
//            String oddsData =
//                    "1             1 in 4              x1 - 1 in 2.5\n" +
//                            "4             1 in 3.86
//                            "8             1 in 9.77
//                            "10            1 in 9.05
//
//            fullOddsText.setText(header + oddsData);
//
//            oddStage.setTitle("Odds to Win");
//
//            // Use a VBox to hold the single TextArea
//            VBox oddLayout = new VBox(fullOddsText);
//            oddLayout.setAlignment(Pos.CENTER);
//
//            // Adjust size to accommodate the width and height of the combined text
//            Scene oddScene = new Scene(oddLayout, 450, 400);
//            oddStage.setScene(oddScene);
//            oddStage.show();
//        });
        Odds.setOnAction(e -> {
                    Stage oddStage = new Stage();
                    TextArea oddText = new TextArea();
                    TextArea multText = new TextArea();

                    oddText.setWrapText(true);
                    oddText.setEditable(false);

                    multText.setWrapText(true);
                    multText.setEditable(false);

                    oddText.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold;");
                    multText.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold;");

                    oddStage.setTitle("Odds to Win");
                    oddText.setText("1 - 1 in 4\n" + "4 - 1 in 3.86\n" + "8 - 1 in 9.77\n" + "10 - 1 in 9.05");
                    multText.setText("x1 - 1 in 2.5\n" +
                            "x2 - 1 in 2.35\n" +
                            "x3 - 1 in 16\n" +
                            "x4 - 1 in 16\n" +
                            "x5 - 1 in 26.67\n" +
                            "x10 - 1 in 80");

                    VBox oddLayout = new VBox(oddText, multText);
                    oddLayout.setAlignment(Pos.CENTER);

                    Scene oddScene = new Scene(oddLayout, 424, 400);
//                    oddStage.setMaxHeight(424);
                    oddStage.setScene(oddScene);
                    oddStage.show();
            }
        );

//        Exit.setOnAction(e -> {
//        );

        //menu.getintems.addAll()

        BorderPane pane = new BorderPane();
        pane.setCenter(paneCenter);
        pane.setTop(menuBar);
        pane.setStyle("-fx-background-color: skyblue;");
        return new Scene(pane, 850, 750);
    }

    public Scene createGameScene() {

        player.playerPicks = new ArrayList<>();

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
        randomPicks.setOnAction(e -> {player.quickPickAll();});

        Button randomFill = new Button("Quick Fill");
        randomPicks.setOnAction(e -> {player.quickFill();});

        Button clear = new Button("clear");
        clear.setOnAction(e -> {player.clearPicks(); resetButtons();});

        Button PlayButton = new Button("Play");
        PlayButton.setDisable(true);
//        PlayButton.setOnAction(e -> {});

        VBox RightButtons = new VBox(pickBut, randomFill, randomPicks, clear, PlayButton);
        RightButtons.setSpacing(15);
        RightButtons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: skyblue;");
        root.setCenter(daGrid);
        root.setRight(RightButtons);

        return new Scene(root, 850, 750);
    }//end of game choice scene

    public void handleChoice(int num, Button currButton){
        if(!player.playerPicks.contains(num)){
            if(player.getPlayerPickSize() < player.getMaxPicks()) {
                player.addPlayerChoice(num);
                currButton.setStyle("-fx-opacity: 0.7;");
            }
        }else{
            player.removePlayerChoice(num);
            currButton.setStyle("-fx-opacity: 1;");
        }
    }

    public void handleHowManyPicks(int num){
        daGrid.setDisable(false);
        resetButtons();
        player.setMaxPicks(num);
    }

    public void resetButtons(){
//        ArrayList<Integer> nums = player.getPlayerPicks();
//        for(int _x = 0; _x < player.getPlayerPickSize(); _x++){
//            int num = nums.get(_x);
//            daGrid.getChildren().get(num-1).setStyle("-fx-opacity: 1;");
//        }
        for (int i = 0; i < 80; i++) {
            Node node = daGrid.getChildren().get(i);
            if (node instanceof Button) {
                node.setStyle("-fx-opacity: 1;");
            }
        }
    }

}//end of class
