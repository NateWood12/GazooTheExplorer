public class Treasure {
    private int value;
    private String description;

    public Treasure(int value, String description) {
       setValue(value);
       setDescription(description);
    }

    public String getConsoleStr() {
        String string;
        string = ConsoleColors.YELLOW + getDescription().charAt(0) + ConsoleColors.RESET;
        return string;
    }
    public Treasure(){
        setValue(5);
        setDescription("Gold");
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    public int getValue(){
        return value;
    }
}
