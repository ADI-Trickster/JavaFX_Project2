import java.util.ArrayList;
import java.util.Random;

public class playGame{
    private ArrayList<Integer> drawnNumbers;
    private Random random;

    public playGame() {
        drawnNumbers = new ArrayList<>();
    }

    public ArrayList<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    public void addRandomNumber(){
        int ranNum = random.nextInt(80) + 1;
        if(!this.drawnNumbers.contains(ranNum)){
            this.drawnNumbers.add(ranNum);
        }
    }

    public void draw20Numbers(){
        for(int i = 0; i < 20; i++){
            addRandomNumber();
        }
    }

    public ArrayList<Integer> matchNumbers(ArrayList<Integer> toCheck){
        ArrayList<Integer> matchNumbers = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            int checkNum = getDrawnNumbers().get(i);
            if(toCheck.contains(checkNum)){
                matchNumbers.add(checkNum);
            }
        }
        return matchNumbers;
    }

}
