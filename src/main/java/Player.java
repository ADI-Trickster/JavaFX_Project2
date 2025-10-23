import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    ArrayList<Integer> playerNumbers;

    public Player(){
        playerNumbers =  new ArrayList<>();
    };

    public ArrayList<Integer> getPlayerNumbers(){
        return playerNumbers;
    }

    public void addPlayerChoice(ArrayList<Integer> playerNumbers){

    }

    public void addPlayerChoice(int playerNumber){
        playerNumbers.add(playerNumber);
    }


}
