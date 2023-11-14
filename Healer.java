public class Healer extends LivingThing{
    private int healValue;

    public Healer(String name,int health, String pieceColor, int healValue){
        super(name, health, pieceColor);
        this.healValue = healValue;
    }

    public void Heal (LivingThing yay){
        yay.setHealth(yay.getHealth() + healValue);
    }

    public int getHealValue() {
        return healValue;
    }


}
