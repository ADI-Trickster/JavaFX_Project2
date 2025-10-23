import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int maxPicks;
    private int totalWinnning;

    ArrayList<Integer> playerPicks;
    Random random;

    public Player(){
        playerPicks =  new ArrayList<>();
        totalWinnning = 0;
        maxPicks = 0;
    }

    public ArrayList<Integer> getPlayerPicks(){
        return playerPicks;
    }

    public void MaxPicks(int picks){
        this.maxPicks = picks;
    }

    public int getMaxPicks(){
        return this.maxPicks;
    }

    public void addPlayerChoice(int pick){
        //button handle
        if(!this.playerPicks.contains(pick)){
            if(playerPicks.size() < maxPicks){
                this.playerPicks.add(pick);
            }
        }else{
            this.playerPicks.remove((Integer) pick);
        }
    }

    public void addWinnings(int wonFromDraw){
        this.totalWinnning+=wonFromDraw;
    }

    public int getTotalWinnning(){
        return this.totalWinnning;
    }

    public void makeRandom(){
        this.playerPicks.clear();
        while(this.playerPicks.size()< maxPicks){
            int pick = random.nextInt(80) + 1;
            if(!this.playerPicks.contains(pick)){
                this.playerPicks.add(pick);
            }
        }
    }

    public void clearPicks(){
        playerPicks.clear();
    }

    public int getPlayerPickSize(){
        return playerPicks.size();
    }

    public void resetPlayer(){
        playerPicks.clear();
        this.totalWinnning = 0;
        this.maxPicks = 0;
    }

}
