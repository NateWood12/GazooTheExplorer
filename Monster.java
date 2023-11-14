public class Monster extends LivingThing{
    private int damage;

    public int getDamage() {
        return damage;
    }

    public Monster(String name,int health, String pieceColor, int damage){
        super(name, health, pieceColor);
        this.damage = damage;
    }

    public void Hurt(LivingThing ouchie){
        ouchie.setHealth(ouchie.getHealth() - damage);
    }
}
