import java.util.ArrayList;

public class Explorer extends LivingThing{

    private ArrayList<Treasure> treasures;

    public Explorer(String name, int health, String pieceColor){
        super(name, health, pieceColor);
        treasures = new ArrayList<Treasure>();
    }
    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public void addTreasure(Treasure cool){
        treasures.add(cool);
    }

    public int getTreasureValue(){
        int total = 0;
        for(int i = 0; i <= treasures.size() - 1; i++){
            total += treasures.get(i).getValue();
        }
       return total;
    }
}
