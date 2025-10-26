package kenoMenu;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.Region;

public class MenuBarStart extends MenuBar {
    public MenuBarStart() {
        Menu menu = new Menu("Menu ⬇");
        MenuItem Rules = new MenuItem("Rules");
        MenuItem Odds = new MenuItem("Odds");
        MenuItem Exit = new MenuItem("Exit");

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

        Odds.setOnAction(e -> {
                    Stage oddStage = new Stage();
                    TextArea oddText = new TextArea();
//            TextArea multText = new TextArea();

                    oddText.setWrapText(true);
                    oddText.setEditable(false);

//            multText.setWrapText(true);
//            multText.setEditable(false);

                    oddText.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold;");
//            multText.setStyle("-fx-background-color: clear; -fx-font-size: 32; -fx-font-weight: bold;");

                    oddStage.setTitle("Odds to Win");
                    String oddHeader = "Spots - Odds\n\n";
                    String oddBody = "     1   -   1 in 4\n" + "     4   -   1 in 3.86\n" + "     8   -   1 in 9.77\n" + "   10   -   1 in 9.05";
                    oddText.setText(oddHeader + oddBody);
//

                    VBox oddLayout = new VBox(oddText);
                    oddLayout.setAlignment(Pos.CENTER);
                    oddText.setPrefWidth(200);
                    oddLayout.setPrefHeight(400);
                    Scene oddScene = new Scene(oddLayout, 424, 400);
                    oddStage.setScene(oddScene);
                    oddStage.show();
                }
        );

        Exit.setOnAction(e -> {
//            add pause animation, animation, pause animation then exit
                    Platform.exit();
                }
        );

        menu.getItems().addAll(Rules, Odds, Exit);
        this.getMenus().addAll(menu);
    }

    public static class MenuBarGame extends MenuBarStart {
        private final Random random = new Random();
        private final String[] colors = {"blue", "yellow", "orange",
                "purple", "pink", "cyan", "magenta", "lime",
                "gold", "silver", "teal", "navy", "maroon",
                "olive", "aqua", "indigo", "salmon", "chocolate"};
        private final String[] fonts = {"Arial", "Verdana", "Tahoma", "Times New Roman", "Courier New",
                "Georgia", "Garamond", "Impact", "Trebuchet MS", "Lucida Console",
                "Comic Sans MS", "Arial Black", "Century Gothic", "Palatino Linotype", "Calibri",
                "Roboto", "Open Sans", "Helvetica", "SansSerif", "Serif"};
        private final String[] imgs = {}; // future use

        public MenuBarGame(Region root) {
            super();
            Menu menu = this.getMenus().get(0);
            MenuItem changeLook = new MenuItem("Change Look");

            changeLook.setOnAction(e -> {
                String color = colors[random.nextInt(colors.length)];
                String font = fonts[random.nextInt(fonts.length)];
                root.setStyle("-fx-background-color: " + color + "; -fx-font-family: '" + font + "';");
//                System.out.println("Changed look to color: " + color + ", font: " + font);
            });

            int exitIndex = menu.getItems().size() - 1;
            menu.getItems().add(exitIndex, changeLook);
        }
    }
}