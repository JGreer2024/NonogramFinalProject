import java.util.*;
public class Main {
    public static void main(String[] args) {
        boolean isAI = false;
        boolean isRandom = false;
        int difficulty = 0;
        String scannerHelp = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which mode do you want to play(Solve, AI): ");
        scannerHelp = scanner.nextLine();
        if(scannerHelp.equals("AI")){
            isAI = true;
        }
        System.out.println("Which type of board do you want(Random, Made): ");
        scannerHelp = scanner.nextLine();
        if (scannerHelp.equals("Random")){
            isRandom = true;
        }
        System.out.println("What difficulty do you want to play at(1,2,3): ");
        scannerHelp = scanner.nextLine();
        if (scannerHelp.equals("1")){
            difficulty = 1;
        } else if(scannerHelp.equals("2")){
            difficulty = 2;
        } else {
            difficulty = 3;
        }

        Board testCase = new Board(difficulty, isRandom, isAI);
        System.out.println(testCase);
        testCase.updateGraph(1,4, false);
        System.out.println(testCase);

        testCase.getLengthOfClue("1 1 2 3");

    }
}