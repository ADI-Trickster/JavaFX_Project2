import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class MyTest {

    Player player;

    @BeforeEach
    void setUp() {
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




    }


    //matches per each picks
    //total winning don't set back to 0

    //clear removes all numbers



//}
