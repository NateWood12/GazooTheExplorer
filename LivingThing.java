import java.io.Console;
public class LivingThing {
    String name;
    int health;
    String pieceColor;

    public LivingThing(String name, int health, String pieceColor){
        setName(name);
        setHealth(health);
        setPieceColor(ConsoleColors.GREEN);
    }

    public String getConsoleStr(){
        String strib;
        strib = getPieceColor() + getName().charAt(0) + ConsoleColors.RESET;
        return strib;
    }

    public void setName(String name){
        this.name = name = name.trim();
        if (name == ""){
            name = "undefined";
            this.name = name.trim();
        }
    }
    public void setHealth(int health){
       if (health < 0){
           System.out.println("Enter a value greater than zero");
           return;
       }else{
           this.health = health;
       }
    }
    public void setPieceColor(String pieceColor){
        this.pieceColor = pieceColor;
    }

    public String getName(){
        return name;
    }
    public int getHealth(){
        return health;
    }
    public String getPieceColor(){
        return pieceColor;
    }
}
