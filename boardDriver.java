import java.util.Scanner;

public class boardDriver {
    public static void main(String[] args) {
        boolean wow = true;
        board b = new board(6,6);
        do{
            b.printBoard(false);
            Scanner input = new Scanner(System.in);
            System.out.print("Enter “w” to go up, “a” to go left, “s” to go down, and “d” to go right -> ");
            char move = input.next().trim().toLowerCase().charAt(0);
            b.move(move);
        }while(wow);
    }
}
