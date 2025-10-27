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
        random = new Random();
    }

    public ArrayList<Integer> getPlayerPicks(){
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
        resetWinning();
        this.maxPicks = 0;
    }

    public void resetWinning(){
        this.totalWinning = 0;
    }

    public void decideWinning(int maxPicks, ArrayList<Integer> toCheck){
        int matches = getNumOfMatches(toCheck, playerPicks);
        int winnings = 0;
        switch(maxPicks){
            case 1:
                winnings = 2;
                break;
            case 4:
                if(matches == 2){
                    winnings = 1;
                } else if (matches == 3) {
                    winnings = 5;
                } else if (matches == 4) {
                    winnings = 75;
                }
                break;
            case 8:
                if(matches == 4){
                    winnings = 2;
                } else if (matches == 5) {
                    winnings = 12;
                } else if (matches == 6) {
                    winnings = 50;
                }else if (matches == 7) {
                    winnings = 750;
                }else if (matches == 8) {
                    winnings = 10000;
                }
                break;
            case 10:
                if(matches == 0){
                    winnings = 5;
                } else if (matches == 5) {
                    winnings = 2;
                } else if (matches == 6) {
                    winnings = 15;
                }else if (matches == 7) {
                    winnings = 40;
                }else if (matches == 8) {
                    winnings = 450;
                }else if (matches == 9) {
                    winnings = 4250;
                }else if (matches == 10) {
                    winnings = 100000;
                }
                break;
        }
        addWinnings(winnings);
    }

    public int getNumOfMatches(ArrayList<Integer> toCheck, ArrayList<Integer> playerPicks){
        int ret = 0;
        for(int i = 0; i < toCheck.size(); i++){
            int val  = toCheck.get(i);
            if(playerPicks.contains(val)){
                ret++;
            }
        }
        return ret;
    }

}
