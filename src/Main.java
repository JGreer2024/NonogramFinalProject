import java.util.*;
public class Main {
    public static void main(String[] args) {
        boolean isAI = false;
        boolean isRandom = false;
        int difficulty = 0;
        String scannerHelp = "";
        String isDone = "";
        while(isDone != "no"){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which mode do you want to play (Myself, Computer): ");
            scannerHelp = scanner.nextLine().toUpperCase();
            if (scannerHelp.equals("COMPUTER")) {
                isAI = true;
            }
//        System.out.println("Which type of board do you want (Random, Made): ");
//        scannerHelp = scanner.nextLine().toLowerCase();
//        if (scannerHelp.equals("random")){
//            isRandom = true;
//        }
            System.out.println("What difficulty do you want to play at (Easy, Hard): ");
            scannerHelp = scanner.nextLine().toLowerCase();
            if (scannerHelp.equals("easy")) {
                difficulty = 1;
            } else {
                difficulty = 4;
            }
            Board testCase = new Board(difficulty, isRandom, isAI);
            while(testCase.isEndBoard() == false){
                String xcord = "";
                String ycord = "";
                System.out.println("Which x coordinate is your next guess (top left is 1,1): ");
                ycord = scanner.nextLine();
                System.out.println("Which y coordinate is your next guess (top left is 1,1): ");
                xcord = scanner.nextLine();
                testCase.updateGraph(Integer.parseInt(xcord), Integer.parseInt(ycord), false);
                System.out.println(testCase);
            }
        }



    }
}