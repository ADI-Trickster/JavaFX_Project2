import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
 import kenoMenu.MenuBarStart;
 import kenoMenu.MenuBarStart.MenuBarGame;

class MyTest {

    Player player;
    playGame gameInstance;
    private ArrayList<Integer> drawnNumbers;
    private Random random;

    @BeforeAll
    static void initJfxToolkit() {
        try {
            // Attempt to initialize the platform
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // Toolkit already initialized (e.g., in a previous test run)
        }
    }

    @BeforeEach
    void setUp() {
        gameInstance = new playGame();
        player  = new Player();
    }

	@Test
	void newPlayerTest() {
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        assertTrue(player.getPlayerPicks().isEmpty(),"wrong number of picks");
        assertEquals(0,player.getTotalWinning(),"wrong total winning");
        assertEquals(0, player.getMaxPicks(),"wrong max picks");
	}

    @Test
    void playerChangingPicks() {
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        assertTrue(player.getPlayerPicks().isEmpty(),"wrong number of picks");

        player.setMaxPicks(4);
        assertEquals(4, player.getMaxPicks(),"wrong max picks");
        player.setMaxPicks(1);
        assertEquals(1, player.getMaxPicks(),"wrong max picks");
        player.setMaxPicks(8);
        assertEquals(8, player.getMaxPicks(),"wrong max picks");
        player.setMaxPicks(10);
        assertEquals(10, player.getMaxPicks(),"wrong max picks");
    }

    @Test
    void playerChoicesNumber() {
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        player.setMaxPicks(8);
        player.addPlayerChoice(5);
        assertEquals(1,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(15);
        player.addPlayerChoice(25);
        player.addPlayerChoice(35);
        player.addPlayerChoice(45);
        assertEquals(5,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(55);
        player.addPlayerChoice(65);
        player.addPlayerChoice(75);
        assertEquals(8,player.getPlayerPickSize(),"wrong number of picks");
    }

    @Test
    void playerChoicesTooMany(){//picks don't go over max picks
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        player.setMaxPicks(8);
        player.addPlayerChoice(5);
        assertEquals(1,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(15);
        player.addPlayerChoice(25);
        player.addPlayerChoice(35);
        player.addPlayerChoice(45);
        assertEquals(5,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(55);
        player.addPlayerChoice(65);
        player.addPlayerChoice(75);
        assertEquals(8,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(76);
        assertEquals(8,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(77);
        player.addPlayerChoice(78);
        player.addPlayerChoice(79);
        player.addPlayerChoice(80);
        assertEquals(8,player.getPlayerPickSize(),"wrong number of picks");
    }

    @Test
    void playerUnpicked(){//chooses the same
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        player.setMaxPicks(4);
        player.addPlayerChoice(5);
        assertEquals(1,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(15);
        player.addPlayerChoice(25);
        player.addPlayerChoice(35);
        assertEquals(4,player.getPlayerPickSize(),"wrong number of picks");
        player.addPlayerChoice(35);
        player.addPlayerChoice(25);
        assertEquals(2,player.getPlayerPickSize(),"wrong number of picks");
    }

    @Test
    void playerQuickFillAll(){
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        player.setMaxPicks(4);
        player.addPlayerChoice(5);
        assertEquals(1,player.getPlayerPickSize(),"wrong number of picks");
        player.quickPickAll();
        assertEquals(4,player.getPlayerPickSize(),"wrong number of picks");
    }

    @Test
    void playerQuickFill(){
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        player.setMaxPicks(4);
        player.addPlayerChoice(5);
        assertEquals(1,player.getPlayerPickSize(),"wrong number of picks");
        player.quickFill();
        assertEquals(4,player.getPlayerPickSize(),"wrong number of picks");
        assertTrue(player.getPlayerPicks().contains(5));
    }

    @Test
    void playerClear(){
        assertEquals(0,player.getPlayerPickSize(),"wrong Start Size");
        player.setMaxPicks(4);
        player.quickPickAll();
        assertEquals(4,player.getPlayerPickSize(),"wrong number of picks");
        player.clearPicks();
        assertEquals(0,player.getPlayerPickSize(),"wrong number of picks");
    }

    @Test
    void ResetPlayer(){
        player.setMaxPicks(4);
        player.quickPickAll();
        player.addWinnings(100);

        assertEquals(4,player.getPlayerPickSize(),"wrong number of picks");
        assertEquals(100,player.getTotalWinning(),"wrong total winnings");
        assertEquals(4,player.getMaxPicks(),"wrong max picks");

        player.resetPlayer();
        assertEquals(0,player.getPlayerPickSize(),"wrong number of picks");
        assertEquals(0,player.getTotalWinning(),"wrong total winnings");
        assertEquals(0,player.getMaxPicks(),"wrong max picks");
    }

    @Test
    void draw20NumbersTest(){
        gameInstance.draw20Numbers();
        assertEquals(20, gameInstance.getDrawnNumbers().size(),"wrong amount of drawn numbers");
    }

    @Test
    void addRandomNumberTest() {
        //in range
        gameInstance.getDrawnNumbers().clear();
        gameInstance.addRandomNumber();

        assertEquals(1, gameInstance.getDrawnNumbers().size(), "Should have exactly one number drawn.");
    }
    @Test
    void addRandomNumberInRangeTest() {
        gameInstance.getDrawnNumbers().clear();
        gameInstance.addRandomNumber();
        int drawnNumber = gameInstance.getDrawnNumbers().get(0);
        assertTrue(drawnNumber >= 1, "Drawn number is outside the required minimum range (1).");
        assertTrue(drawnNumber <= 80, "Drawn number is outside the required maximum range (80).");
    }

    @Test
    void clearPicksTest() {
        player.setMaxPicks(4);
        player.addPlayerChoice(10);
        player.addPlayerChoice(20);

        assertEquals(2, player.getPlayerPickSize(), "Setup failed: PlayerPicks should have 2 numbers.");

        player.clearPicks();

        assertEquals(0, player.getPlayerPickSize(),
                "clearPicks should reset the pick size to 0.");
        assertTrue(player.getPlayerPicks().isEmpty(),
                "clearPicks should make the playerPicks list empty.");
    }

    @Test
    void setAndGetMaxPicksTest() {
        final int expectedMax = 8;

        player.setMaxPicks(expectedMax);
        assertEquals(expectedMax, player.getMaxPicks(),
                "getMaxPicks should return the value set by setMaxPicks.");
    }

    @Test
    void getPlayerPickSizeTest() {
        assertEquals(0, player.getPlayerPickSize(),
                "Initial size should be 0.");

        player.setMaxPicks(4);
        player.addPlayerChoice(1);
        player.addPlayerChoice(2);

        assertEquals(2, player.getPlayerPickSize(),
                "Size should be 2 after adding two picks.");

        player.removePlayerChoice(1);

        // Verify size decrements
        assertEquals(1, player.getPlayerPickSize(),
                "Size should be 1 after removing one pick.");
    }

    @Test
    void matchNumbersTest() {
        ArrayList<Integer> playerPicks = new ArrayList<>();
        playerPicks.add(5);
        playerPicks.add(10);
        playerPicks.add(15); //This willmatch
        playerPicks.add(25); // This will  match

        gameInstance.getDrawnNumbers().clear();
        gameInstance.getDrawnNumbers().add(1);
        gameInstance.getDrawnNumbers().add(15); // Match
        gameInstance.getDrawnNumbers().add(2);
        gameInstance.getDrawnNumbers().add(25); // Match
        gameInstance.getDrawnNumbers().add(50);

        for(int i = 0; gameInstance.getDrawnNumbers().size() < 20; i++) {
            gameInstance.getDrawnNumbers().add(60 + i);
        }

        ArrayList<Integer> matchedNumbers = gameInstance.matchNumbers(playerPicks);

        assertEquals(2, matchedNumbers.size(), "wrong # of matched numbers.");
        assertTrue(matchedNumbers.contains(15), "15 does not match when should.");
        assertTrue(matchedNumbers.contains(25), "25 does not match when should.");
        assertFalse(matchedNumbers.contains(5), "5 is matched (picked not drawn) - wrong.");
    }

    @Test
    void playerWinningsTest() {
        assertEquals(0, player.getTotalWinning(), "Winnings = 0.");

        player.addWinnings(50);

        assertEquals(50, player.getTotalWinning(), "Total winnings = 50.");

        player.addWinnings(10);

        assertEquals(60, player.getTotalWinning(), "Total = 60.");
    }

    private Menu getMainMenu(MenuBar menuBar) {
        assertFalse(menuBar.getMenus().isEmpty(), "MenuBar has no menus");
        return menuBar.getMenus().get(0);
    }
    @Test
    void menuBarStartNotEmptyTest() {
        MenuBarStart menuBar = new MenuBarStart();
        Menu mainMenu = getMainMenu(menuBar);

        assertEquals(1, menuBar.getMenus().size(), "Dropdown Menu exists");
        assertEquals(3, mainMenu.getItems().size(), "Rules, Odds, Exit options exist");
    }

    @Test
    void menuBarStartRightOrderTest() {
        MenuBarStart menuBar = new MenuBarStart();
        Menu mainMenu = getMainMenu(menuBar);
        // 2. Verify item names and order
        assertEquals("Rules", mainMenu.getItems().get(0).getText());
        assertEquals("Odds", mainMenu.getItems().get(1).getText());
        assertEquals("Exit", mainMenu.getItems().get(2).getText());
    }

    @Test
    void quickFillDoesNotExceedMaxTest() {
        player.setMaxPicks(4);
        player.addPlayerChoice(10);

        player.quickFill();
        assertEquals(4, player.getPlayerPickSize(), "Should fill exactly 4 picks");
    }

    @Test
    void quickFillDoesNotExceedMax2Test() {
        player.setMaxPicks(4);
        player.addPlayerChoice(10);

        player.quickFill();
        assertEquals(4, player.getPlayerPickSize(), "Should fill exactly 4 picks");

        player.setMaxPicks(10);
        player.quickFill();
        assertEquals(10, player.getPlayerPickSize(), "Should fill exactly 10 picks");
    }

    @Test
    void quickFillDoesNotRemoveExistingTest() {
        player.setMaxPicks(4);
        player.addPlayerChoice(7);
        player.quickFill();
        assertTrue(player.getPlayerPicks().contains(7), "Existing pick should remain");
    }

    @Test
    void quickFillDoesNotRemoveExisting2Test() {
        player.setMaxPicks(4);
        player.addPlayerChoice(7);
        player.addPlayerChoice(15);
        player.quickFill();
        player.setMaxPicks(8);
        player.quickFill();
        assertTrue(player.getPlayerPicks().contains(7), "Existing pick should remain");
        assertTrue(player.getPlayerPicks().contains(15), "Existing pick should remain");
    }

    @Test
    void resetAfterCompleteRoundTest() {
        player.setMaxPicks(4);
        player.quickPickAll();
        gameInstance.draw20Numbers();
        player.addWinnings(200);

        player.resetPlayer();
        assertEquals(0, player.getPlayerPickSize());
        assertEquals(0, player.getTotalWinning());
    }

    @Test
    void getMaxPicksTest() {
        player.setMaxPicks(8);
        assertEquals(8, player.getMaxPicks(), "getMaxPicks should return the value set by setMaxPicks.");
    }

    }//end of test


