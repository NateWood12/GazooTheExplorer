import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
public class board {
    Random rand = new Random();
    private ArrayList<ArrayList<Space>> board;
    private ArrayList<Treasure> remainingTreasures;
    private ArrayList<Monster> Monster;
    private ArrayList<Healer> Healer;
    private int currentRow = 0;
    private int currentCol = 0;
    public board() {
        this(4, 4);
    }
    Explorer Gazoo = new Explorer("Gazoo", 20, ConsoleColors.GREEN);

    public board(int rows, int cols) {
        int Cord1 = rand.nextInt(rows);
        int Cord2 = rand.nextInt(cols);
        remainingTreasures = new ArrayList<Treasure>();
        remainingTreasures.add(new Treasure(10, "Gold Pouch"));
        remainingTreasures.add(new Treasure(15, "Golden Goblet"));
        remainingTreasures.add(new Treasure(25, "Ancient Dagger"));
        remainingTreasures.add(new Treasure(100, "Treasure Chest"));
        remainingTreasures.add(new Treasure(50, "Pouch of Gems"));
        board = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            ArrayList<Space> row = new ArrayList<>(cols);
            for (int j = 0; j < cols; j++) {
                row.add(new Space());
            }
            board.add(row);
        }
        currentRow = 0;
        currentCol = 0;
        board.get(0).get(0).setOccupant(Gazoo);
        board.get(Cord1).get(Cord2).setCache(remainingTreasures.get(0));
        board.get(Cord1).get(Cord2).setCache(remainingTreasures.get(1));
        board.get(Cord1).get(Cord2).setCache(remainingTreasures.get(2));
        board.get(Cord1).get(Cord2).setCache(remainingTreasures.get(3));
        board.get(Cord1).get(Cord2).setCache(remainingTreasures.get(4));
        addTreasures();
        addMonsters();
        addHealer();
    }
    private void addMonsters() {
        Monster = new ArrayList<Monster>();
        Monster.add(new Monster("Razorclaw",3,ConsoleColors.RED,9));
        Monster.add(new Monster("Gloomfury",3,ConsoleColors.RED,8));
        Monster.add(new Monster("Fangsnarl",3,ConsoleColors.RED,7));
        Monster.add(new Monster("Vilespike",3,ConsoleColors.RED,6));
        Monster.add(new Monster("Grimscowl",3,ConsoleColors.RED,5));
        int countPlaced = 0;
        while (countPlaced < 5) {
            int row = rand.nextInt(board.size());
            int column = rand.nextInt(board.get(0).size());
            if (board.get(row).get(column).getConsoleStr(false) == "-"){
                board.get(row).get(column).setOccupant(Monster.get(countPlaced));
                countPlaced += 1;
            }
        }
    }


    private void addHealer(){
        Healer = new ArrayList<Healer>();
        Healer.add(new Healer("Healer",1, ConsoleColors.BLUE, 5));
        int countPlaced = 0;
        while (countPlaced < 1                                                                                             ) {
            int row = rand.nextInt(board.size());
            int column = rand.nextInt(board.get(0).size());
            if (board.get(row).get(column).getConsoleStr(false) == "-"){
                board.get(row).get(column).setOccupant(Healer.get(countPlaced));
                countPlaced += 1;
            }
        }
    }

    private void addTreasures(){
        remainingTreasures = new ArrayList<>();
        for(int i = 0; i< 5; i++){
            remainingTreasures.add(new Treasure());
        }
        remainingTreasures.get(0);
        int placed = 0;

        while (placed < remainingTreasures.size()){
            int row =  rand.nextInt(board.size());
            int col =  rand.nextInt(board.get(0).size());
            if (board.get(row).get(col).getConsoleStr(true) == "-"){
                board.get(row).get(col).setCache(remainingTreasures.get(placed));
                placed += 1;

            }
        }
    }
    private boolean AllTheThings(int newRow, int newColumn, Explorer LivingThing) {
        if (board.get(newRow).get(newColumn).getOccupant() instanceof Monster){
            ((Monster) board.get(newRow).get(newColumn).getOccupant()).Hurt(LivingThing);
            System.out.println(LivingThing.getName() + " was attacked by " + board.get(newRow).get(newColumn).getOccupant().getName() +
                    " for a loss of " + ((Monster) board.get(newRow).get(newColumn).getOccupant()).getDamage() + " health.");
            if (LivingThing.getHealth() <= 0) {
                System.out.println("Game over. The Explorer Died.");
                System.out.println("Number of treasures collected: " + LivingThing.getTreasures().size());
                for (Treasure treasure : LivingThing.getTreasures()) {
                    System.out.println("Treasure: " + treasure.getDescription() + " worth " + treasure.getValue());
                }
                System.out.println("Total worth of all treasure : " + LivingThing.getTreasureValue());
                board.get(newRow).get(newColumn).setOccupant(LivingThing);
                return true;
            }
        } else if (board.get(newRow).get(newColumn).getOccupant() instanceof Healer){
            System.out.println(LivingThing.getName() + " was healed by " + board.get(newRow).get(newColumn).getOccupant().getName() +
                    " for an increase of " + ((Healer) board.get(newRow).get(newColumn).getOccupant()).getHealValue() + " health.");
            ((Healer) board.get(newRow).get(newColumn).getOccupant()).Heal(LivingThing);
            board.get(newRow).get(newColumn).setOccupant(LivingThing);
            return false;
        }
        return false;
    }
    public boolean move(char m){
        boolean end;
        if (Gazoo.getHealth() <= 0){
            return true;
        }
        int previousRow, previousCol;
        previousRow = currentRow;
        previousCol = currentCol;
        if (m == 'a') {
            if (validSpace(currentRow, currentCol -1)){
                end = AllTheThings(currentRow, currentCol -1,Gazoo);
                if (end == true){
                    return true;
                }
                board.get(currentRow).get(currentCol).setOccupant(null);
                currentCol -= 1;
                addTreasure();
                board.get(currentRow).get(currentCol).setOccupant(Gazoo);
            }else{
                return false;
            }
        } else if (m == 'w') {
            if (validSpace(currentRow -1, currentCol)){
                end = AllTheThings(currentRow - 1, currentCol ,Gazoo);
                if (end == true){
                    return true;
                }
                board.get(currentRow).get(currentCol).setOccupant(null);
                currentRow -= 1;
                addTreasure();
                board.get(currentRow).get(currentCol).setOccupant(Gazoo);
            }else{
                return false;
            }
        } else if (m == 's') {
            if (validSpace(currentRow + 1, currentCol)){
                end= AllTheThings(currentRow + 1, currentCol ,Gazoo);
                if (end == true){
                    return true;
                }
                board.get(currentRow).get(currentCol).setOccupant(null);
                currentRow += 1;
                addTreasure();
                board.get(currentRow).get(currentCol).setOccupant(Gazoo);
            }else{
                return false;
            }
        } else if (m == 'd') {
            if (validSpace(currentRow, currentCol +1)){
                end = AllTheThings(currentRow, currentCol +1,Gazoo);
                if (end == true){
                    return true;
                }
                board.get(currentRow).get(currentCol).setOccupant(null);
                currentCol += 1;
                addTreasure();
                board.get(currentRow).get(currentCol).setOccupant(Gazoo);
            }else{
                System.out.println("Print a valid input");
                return false;
            }
        } else if (m == 'r') {
            printBoard(true);
        } else if (m == 'j') {
            System.out.println("Number of treasures collected: " + Gazoo.getTreasures().size());
            System.out.println("Total amount of treasure : " + Gazoo.getTreasureValue());
        } else {
            return false;
        }
        if (remainingTreasures.size() == 0){
            System.out.println("Winner!");
            System.out.println("Treasures collected: " + Gazoo.getTreasures().size());
            System.out.println("Total amount of treasure : " + Gazoo.getTreasureValue());
            return true;
        }
        return false;
    }


    public void addTreasure(){
        if (board.get(currentRow).get(currentCol).getCache() instanceof Treasure){
            System.out.println(Gazoo.getName() + " found " + board.get(currentRow).get(currentCol).getCache().getDescription() + " worth " +
                    board.get(currentRow).get(currentCol).getCache().getValue() + ". There are " + (remainingTreasures.size() - 1) + " treasures left.");
            Gazoo.addTreasure(board.get(currentRow).get(currentCol).getCache());
            remainingTreasures.remove(board.get(currentRow).get(currentCol).getCache());
            board.get(currentRow).get(currentCol).setCache(null);
        }
    }
    public boolean validSpace(int row, int col){
        return row >= 0 && row < board.size() && col >= 0 && col < board.get(row).size();
    }

    public void printBoard(boolean ShowContents){
        for (ArrayList<Space> row : board) {
            for (Space value : row) {
                System.out.print(value.getConsoleStr(ShowContents) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    }




