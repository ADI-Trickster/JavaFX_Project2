import java.util.ArrayList;

public class Player {
    ArrayList<Integer> playerPicks;
    int MaxPicks;
    int totalWinnning;

    public Player(){
        playerPicks =  new ArrayList<>();
        totalWinnning = 0;
        MaxPicks = 0;
    }

    public ArrayList<Integer> getPlayerPicks(){
        return playerPicks;
    }

    public void MaxPicks(int picks){
        MaxPicks = picks;
    }

    public void addPlayerChoice(ArrayList<Integer> playerChoice){
        //button handle
    }

    public void addWinnings(int wonFromDraw){
        totalWinnning+=wonFromDraw;
    }

    public int getTotalWinnning(){
        return totalWinnning;
    }

    public void random(){
        while(this.playerPicks.size()<MaxPicks){
            int pick = (int) (Math.random()*80);
            if(!this.playerPicks.contains(pick)){
                this.playerPicks.add(pick);
            }
        }
    }

    public void clear(){
        playerPicks.clear();
    }

}
