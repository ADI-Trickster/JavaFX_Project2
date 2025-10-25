import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int maxPicks;
    private int totalWinning;
    private ArrayList<Integer> playerPicks;
    private Random random;

    public Player(){
        playerPicks =  new ArrayList<>();
        totalWinning = 0;
        maxPicks = 0;
    }

    public ArrayList<Integer> getPlayerPicks(){
        return playerPicks;
    }

    public ArrayList<Integer> getplayerPicks(){
        return this.playerPicks;
    }

    public void setMaxPicks(int picks){
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
            removePlayerChoice(pick);
        }
    }

    public void removePlayerChoice(int pick){
        this.playerPicks.remove((Integer)pick);
    }

    public void addWinnings(int wonFromDraw){
        this.totalWinning+=wonFromDraw;
    }

    public int getTotalWinning(){
        return this.totalWinning;
    }

    public void quickPickAll(){
        this.playerPicks.clear();
        while(this.playerPicks.size()< maxPicks){
            int pick = random.nextInt(80) + 1;
            if(!this.playerPicks.contains(pick)){
                this.playerPicks.add(pick);
            }
        }
    }

    public void quickFill(){
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
        this.totalWinning = 0;
        this.maxPicks = 0;
    }

}
