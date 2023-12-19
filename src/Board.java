import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int[][] board;
    private int difficulty;
    private boolean random;

    private String[][] numBoard; // first index = rows / second = col

    private int[][] guessBoard;

    private int length;
    private boolean solveable;

    public Board(int difficulty, boolean random, boolean isSolver) {
        this.difficulty = difficulty;
        this.random = random;
        if (!random) {
            board = alreadyMadeBoards(difficulty);
            numBoard = hintGetter();
        } else {
            board = randomBoards(difficulty);
            numBoard = hintGetter();
//            while (!solveable) {
//                board = generateAndCheckBoards(difficulty);
//                numBoard = hintGetter();
//                boardSolver(true);
//            }
        }
        if (difficulty == 1) {
            guessBoard = new int[5][5];
        } else if (difficulty == 2) {
            guessBoard = new int[10][10];
        } else {
            guessBoard = new int[15][15];
        }
        System.out.println("Original Board:");
        length = board.length;
        System.out.println(this.toString());
        if (isSolver){
            boardSolver(false);
        }
    }
    private int[][] randomBoards(int difficulty) {
        int randomNum = (int) (Math.random()*10);
        int[][][] allPossibleBoards;

        if (difficulty == 1) {
            allPossibleBoards = new int[10][5][5];
            // Define 10 solvable 5x5 boards
            allPossibleBoards[0] = new int[][]{{1, 1, 0, 0, 0}, {1, 0, 0, 1, 1}, {0, 0, 1, 0, 0}, {1, 1, 0, 1, 0}, {0, 0, 1, 1, 1}};
            allPossibleBoards[1] = new int[][]{{1, 0, 1, 0, 1}, {0, 1, 0, 1, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {1, 0, 1, 0, 1}};
            allPossibleBoards[2] = new int[][]{{1, 1, 0, 1, 1}, {1, 0, 1, 0, 1}, {0, 1, 0, 1, 0}, {1, 0, 1, 0, 1}, {1, 1, 0, 1, 1}};
            allPossibleBoards[3] = new int[][]{{0, 1, 1, 1, 0}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {0, 1, 1, 1, 0}};
            allPossibleBoards[4] = new int[][]{{0, 0, 1, 0, 0}, {0, 1, 1, 1, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}};
            allPossibleBoards[5] = new int[][]{{1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 0, 1}, {0, 1, 0, 1, 0}, {1, 1, 1, 1, 1}};
            allPossibleBoards[6] = new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
            allPossibleBoards[7] = new int[][]{{0, 1, 1, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 1, 0, 0}, {1, 1, 0, 1, 1}, {0, 1, 1, 1, 0}};
            allPossibleBoards[8] = new int[][]{{0, 0, 1, 0, 0}, {0, 1, 1, 1, 0}, {1, 1, 0, 1, 1}, {1, 0, 0, 0, 1}, {0, 1, 1, 1, 0}};
            allPossibleBoards[9] = new int[][]{{1, 0, 0, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 0, 0, 1}};
            return allPossibleBoards[randomNum];
        } else {
            allPossibleBoards = new int[10][15][15];
            // Define 10 solvable 15x15 boards
            allPossibleBoards[0] = new int[][]{{1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0}, {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0}, {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0}, {1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1}, {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0}, {1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1}, {0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1}, {0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0}};
            allPossibleBoards[1] = new int[][]{{0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1}, {0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0}, {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1}, {0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0}, {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1}, {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}};
            allPossibleBoards[2] = new int[][]{{1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1}, {0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0}, {0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1}, {1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1}, {1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0}, {1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0}, {0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0}, {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1}, {0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
            allPossibleBoards[3] = new int[][]{{1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1}, {0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0}, {1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1}, {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0}, {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0}, {1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1}, {1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1}, {1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0}, {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0}, {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1}};
            allPossibleBoards[4] = new int[][]{{0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}};
            allPossibleBoards[5] = new int[][]{{1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0}};
            allPossibleBoards[6] = new int[][]{{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0}, {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0}, {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0}, {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0}, {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}};
            allPossibleBoards[7] = new int[][]{{1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0}, {0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0}, {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1},{1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0}, {0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0}, {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1}, {1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0}, {0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0}, {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1}, {1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0}, {0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0}};
            allPossibleBoards[8] = new int[][]{{0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, {1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0}, {0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1}, {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1}, {0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0}};
            allPossibleBoards[9] = new int[][]{{1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1}, {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0}, {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1}, {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1}, {0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}};
            return allPossibleBoards[randomNum];
        }
    }
    private int[][] generateAndCheckBoards(int difficulty){
        int size = 0;
        double randomNum = 0.0;
        if (difficulty == 1){
            size = 5;
        } else {
            size = 15;
        }
        int[][] randomBoard = new int[size][size];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    randomNum = Math.random();
                    if (randomNum > 0.5){
                        randomBoard[i][j] = 1;
                    } else {
                        randomBoard[i][j] = 0;
                    }
                }
            }
            return randomBoard;
    }

    public void updateGraph(int row, int col, boolean guessBlank) {
        if (guessBlank == true && board[row-1][col-1] == 0) {
            guessBoard[row-1][col-1] = 2;
        } else if (guessBlank == false && board[row-1][col-1] == 1){
            guessBoard[row-1][col-1] = 1;
        }
        if (isEndBoard()) {
            endGame();
        }
    }
    public int getLengthOfClue(String unstringIt){
        int finalAmount = 0;
        int countOfNums = 0;
        String adder = "";
        for(int i = 0; i < unstringIt.length(); i++){
            if (unstringIt.charAt(i) != ' '){
                if (i != unstringIt.length()-1 && unstringIt.charAt(i+1) != ' '){
                    adder = "" + unstringIt.charAt(i) + unstringIt.charAt(i+1);
                    i++;
                    countOfNums++;
                } else {
                    adder = "" + unstringIt.charAt(i);
                    countOfNums++;
                }

                finalAmount += Integer.parseInt(adder);
            }
        } finalAmount += (countOfNums-1);
        return finalAmount;
    }
    public void fillAreaGivenCode(String fillThis, int whichRowOrCol, boolean isRow){
        String fillsAlong = "";
        String lengthHelp = "";
        while (fillsAlong.length()<board.length) {
            lengthHelp = getFirstNum(fillThis);
            fillThis = fillThis.substring(lengthHelp.length());
            lengthHelp = lengthHelp.replaceAll(" ", "");
            for (int i = 0; i < Integer.parseInt(lengthHelp); i++) {
                if (i == Integer.parseInt(lengthHelp) - 1) {
                    fillsAlong += "fe";
                } else {
                    fillsAlong += "f";
                }
            }
        }
        fillsAlong = fillsAlong.substring(0,fillsAlong.length()-1);
        if (isRow) {
            for (int i = 0; i < board.length; i++) {
                if (fillsAlong.charAt(i) == 'f') {
                    updateGraph(whichRowOrCol+1, i+1, false);
                } else {
                    updateGraph(whichRowOrCol+1, i+1, true);
                }
            }
        } else {
            for (int i = 0; i < board.length; i++) {
                if (fillsAlong.charAt(i) == 'f') {
                    updateGraph(i+1, whichRowOrCol+1, false);
                } else {
                    updateGraph(i+1, whichRowOrCol+1, true);
                }
            }
        }
    }

    public String getFirstNum(String string){
        String nextNum = "";
        boolean hasReachedFirstNum = false;
        for(int i = 0; i < string.length();i++){
            if (string.charAt(i) != ' '){
                nextNum += string.charAt(i);
                hasReachedFirstNum = true;
            } else if (hasReachedFirstNum == true){
                return nextNum;
            } else {
                nextNum += string.charAt(i);
            }
        }
        return nextNum;
    }
    public boolean isRowDone(int rowNum){
        int numcount = 0;
        String totalConverter = "";
        int total = 0;
        String helper = numBoard[0][rowNum];
        while(helper.length()>0){
            totalConverter = getFirstNum(helper);
            total = Integer.parseInt(totalConverter.replaceAll(" ", ""));
            helper = helper.substring(totalConverter.length());
        }
        for(int i = 0; i < board.length; i++){
            if (guessBoard[rowNum][i] == 1){
                numcount++;
            }
        }
        if (numcount == total){
            return true;
        }
        return false;
    }
    public boolean isColDone(int colNum){
        int numcount = 0;
        String totalConverter = "";
        int total = 0;
        String helper = numBoard[1][colNum];
        while(helper.length()>0){
            totalConverter = getFirstNum(helper);
            total = Integer.parseInt(totalConverter.replaceAll(" ", ""));
            helper = helper.substring(totalConverter.length());
        }
        for(int i = 0; i < board.length; i++){
            if (guessBoard[i][colNum] == 1){
                numcount++;
            }
        }
        if (numcount == total){
            return true;
        }
        return false;

    }
    public void setEmptyIfNotFull(int num, boolean isRow){
        if (isRow) {
            for (int i = 0; i < board.length; i++) {
                updateGraph(num+1, i+1, true);
            }
        } else {
            for (int i = 0; i < board.length; i++) {
                updateGraph(i+1, num+1, true);
            }
        }
    }
    public void boardSolver(boolean isGenerating) {
        boolean changed;
        int iterations = 0;

        // Initial pass for full lines
        for (int i = 0; i < length; i++) {
            if (getLengthOfClue(numBoard[1][i]) == length) {
                fillAreaGivenCode(numBoard[1][i], i, true);
                if (isGenerating == false) {
                    System.out.println("Next Iteration:");
                    System.out.println(this.toString());
                }
            } else if (getLengthOfClue(numBoard[0][i]) == length) {
                fillAreaGivenCode(numBoard[0][i], i, false);
                if (isGenerating == false) {
                    System.out.println("Next Iteration:");
                    System.out.println(this.toString());
                }
            }
        }

        // Iterative solving
        do {
            changed = false;
            for (int i = 0; i < length; i++) {
                // Solve row
                if (solveLine(numBoard[0][i], i, false)) {
                    changed = true;
                }
                // Solve column
                if (solveLine(numBoard[1][i], i, true)) {
                    changed = true;
                }
                markDefiniteSpaces(numBoard[0][i], i, false);   // Mark definite empty spaces in row
                markDefiniteSpaces(numBoard[1][i], i, true);    // Mark definite empty spaces in column
                if (isGenerating == false) {
                    System.out.println("Next Iteration:");
                    System.out.println(this.toString());
                }
            }
            findAndMarkOverlaps();
            if (isGenerating == false) {
                System.out.println("Iteration: " + iterations);
            }
            iterations++;
            if (iterations > 100) {
                break;
            }
        } while (changed && !isEndBoard());


        if (isEndBoard() && isGenerating == true) {
            solveable = true;
        } else if (isEndBoard()){
            endGame();
        }
    }
    private void findAndMarkOverlaps() {
        for (int i = 0; i < length; i++) {
            markOverlaps(numBoard[0][i], i, false); // Check for overlaps in row
            markOverlaps(numBoard[1][i], i, true);  // Check for overlaps in column
        }
    }

    private void markOverlaps(String clue, int index, boolean isColumn) {
        List<int[]> possibilities = getPossibilities(clue, length);
        int[] overlap = findOverlap(possibilities);

        for (int i = 0; i < overlap.length; i++) {
            if (overlap[i] == 1) {  // If overlap is found, mark the cell as filled
                if (isColumn) {
                    updateGraph(i + 1, index + 1, false);
                } else {
                    updateGraph(index + 1, i + 1, false);
                }
            }
        }
    }

    private int[] findOverlap(List<int[]> possibilities) {
        if (possibilities.isEmpty()) {
            return new int[length]; // Return an empty array if no possibilities
        }

        int[] overlap = new int[length];
        Arrays.fill(overlap, 1);

        for (int i = 0; i < overlap.length; i++) {
            for (int[] possibility : possibilities) {
                if (possibility[i] == 0) {
                    overlap[i] = 0;
                    break;
                }
            }
        }

        return overlap;
    }
    private void markDefiniteSpaces(String clue, int index, boolean isColumn) {
        List<int[]> possibilities = getPossibilities(clue, length);
        int[] definiteEmpty = findDefiniteEmpty(possibilities);

        for (int i = 0; i < definiteEmpty.length; i++) {
            if (definiteEmpty[i] == 0) {  // Mark as empty if confirmed in all possibilities
                if (isColumn) {
                    updateGraph(i + 1, index + 1, true);
                } else {
                    updateGraph(index + 1, i + 1, true);
                }
            }
        }
    }

    private int[] findDefiniteEmpty(List<int[]> possibilities) {
        if (possibilities.isEmpty()) {
            int[] defaultArray = new int[length];
            Arrays.fill(defaultArray, 0);  // If no possibilities, mark all as empty
            return defaultArray;
        }

        int[] definiteEmpty = new int[possibilities.get(0).length];
        Arrays.fill(definiteEmpty, 1);  // Start with assumption that all cells are empty

        for (int i = 0; i < definiteEmpty.length; i++) {
            for (int[] possibility : possibilities) {
                if (possibility[i] == 1) {
                    definiteEmpty[i] = 0;  // If any possibility has a filled cell, mark as not definitively empty
                    break;
                }
            }
        }

        return definiteEmpty;
    }

    private boolean solveLine(String clue, int index, boolean isColumn) {
        List<int[]> possibilities = getPossibilities(clue, isColumn ? length : board[index].length);
        int[] common = findCommon(possibilities);

        boolean changed = false;
        for (int i = 0; i < common.length; i++) {
            int current = isColumn ? board[i][index] : board[index][i];
            if (current != common[i]) {
                if (isColumn) {
                    updateGraph(i + 1, index + 1, common[i] == 0);
                } else {
                    updateGraph(index + 1, i + 1, common[i] == 0);
                }
                changed = true;
            }
        }
        return changed;
    }
    private List<int[]> getPossibilities(String clue, int length) {
        String[] parts = clue.trim().split("\\s+");// turn a string that is like " 1 2 4" into and array of [1, 2, 4]
        int[] clues = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
        return getPossibilitiesRecursive(clues, 0, length, new int[length], 0);
    }

    private List<int[]> getPossibilitiesRecursive(int[] clues, int clueIndex, int length, int[] current, int position) {
        List<int[]> possibilities = new ArrayList<>();
        if (clueIndex == clues.length) {
            if (isLineValid(current, clues)) {
                possibilities.add(current.clone());
            }
            return possibilities;
        }

        int remainingLength = length - position; // Remaining length in the line
        int requiredLength = clues[clueIndex] + (clueIndex < clues.length - 1 ? 1 : 0); // Length needed for current clue plus space

        for (int i = position; i <= remainingLength - requiredLength; i++) {
            Arrays.fill(current, i, i + clues[clueIndex], 1); // Fill with block

            if (clueIndex < clues.length - 1) {
                current[i + clues[clueIndex]] = 0; // Space after block, safe to place now
            }

            possibilities.addAll(getPossibilitiesRecursive(clues, clueIndex + 1, length, current, i + clues[clueIndex] + 1));
            Arrays.fill(current, i, i + clues[clueIndex], 0); // Reset
        }

        return possibilities;
    }
    private boolean isLineValid(int[] line, int[] clues) {
        int clueIndex = 0;
        int count = 0;

        for (int i = 0; i < line.length; i++) {
            if (line[i] == 1) {
                count++;
                if (clueIndex >= clues.length) {
                    // There are more blocks than clues
                    return false;
                }
                if (i == line.length - 1 || line[i + 1] == 0) {
                    // End of a block
                    if (count != clues[clueIndex]) {
                        // The block size doesn't match the clue
                        return false;
                    }
                    clueIndex++;
                    count = 0;
                }
            } else {
                if (count > 0 && count != clues[clueIndex]) {
                    // The block size doesn't match the clue
                    return false;
                }
                count = 0;
            }
        }

        // Check if all clues are accounted for
        return clueIndex == clues.length;
    }
    private int[] findCommon(List<int[]> possibilities) {
        if (possibilities.isEmpty()) {
            // Return a default value when there are no possibilities
            int[] defaultArray = new int[length];
            Arrays.fill(defaultArray, -1);
            return defaultArray;
        }

        int[] common = new int[possibilities.get(0).length];
        Arrays.fill(common, -1);

        for (int i = 0; i < common.length; i++) {
            boolean allOnes = true;
            boolean allZeroes = true;
            for (int[] possibility : possibilities) {
                if (possibility[i] == 0) allOnes = false;
                if (possibility[i] == 1) allZeroes = false;
            }
            if (allOnes) common[i] = 1;
            else if (allZeroes) common[i] = 0;
        }

        return common;
    }

    public void endGame() {
        System.out.println("Final Board:");
        System.out.println(this.toString());
        System.out.println("You completed the game! Congratulations!");
        System.exit(0);
    }

    public boolean isEndBoard() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 1 && guessBoard[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] alreadyMadeBoards(int difficulty) {
        if (difficulty == 1) {
            int[][] easyBoard = {{0, 0, 0, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}};
            length = 5;
            return easyBoard;
        } else if (difficulty == 3) {
            int[][] mediumBoard = {{0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {1, 1, 0, 0, 1, 1, 0, 0, 0, 0}, {1, 1, 0, 0, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 1, 0, 0, 0}, {1, 1, 1, 1, 0, 1, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
            length = 10;
            return mediumBoard;
        } else {
            int[][] hardBoard = {{1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1}, {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}};
            length = 15;
            return hardBoard;
        }
    }

    public String[][] hintGetter() {
        String[][] numberBoard = new String[2][length];
        String colHelper = "";
        String rowHelper = "";
        int rowNumbers = 0;
        int colNumbers = 0;
        for (int i = 0; i < length; i++) { // row getter
            for (int j = 0; j < length; j++) {
                if (board[i][j] == 1) {
                    rowNumbers++;
                } if (board[i][j] == 0 || j == length - 1) {
                    if (rowNumbers != 0) {
                        rowHelper = rowHelper + "  " + rowNumbers;
                        rowNumbers = 0;
                    }
                }
                if (board[j][i] == 1) {
                    colNumbers++;
                } if (board[j][i] == 0 || j == length - 1) {
                    if (colNumbers != 0) {
                        colHelper = colHelper + "  " + colNumbers;
                        colNumbers = 0;
                    }
                }
                if (j == length - 1) {
                    numberBoard[1][i] = rowHelper;
                    numberBoard[0][i] = colHelper; // puts finished hint lines in corresponding spots in the array
                    rowHelper = "";
                    colHelper = "";
                }
            }
        }
        return numberBoard;
    }

    private String nextNumDelete(String[] numberString){
        String helpful = " ";
        int longestValues = 0;
        for(int i = 0; i < numberString.length; i++) {
            numberString[i] = numberString[i].replaceAll(" ", "");
            int longestLength = numberString[i].length();
            if (longestLength >= longestValues) {
                longestValues = longestLength;
            }
        } for(int i = 0; i < numberString.length; i++){
            int longestLength = numberString[i].length();
            while(longestLength < longestValues){
                numberString[i] = "0" + numberString[i];
                longestLength ++;
            }
        }
        for(int i = 0; i < longestValues; i++) {
            for (int j = 0; j < numberString.length; j++) {
                if (numberString[j].charAt(i) != '0'){
                    helpful += numberString[j].charAt(i) + " ";
                } else if (numberString[j].charAt(i) == '0') {
                    helpful += "  ";
                }
                    if (j == numberString.length-1){
                        helpful += "\n ";
                    }
                }
            }
        return helpful;
    }

    @Override
    public String toString() {
        String boardPrint = "";
        String[] colHints = numBoard[0];
        String colString = nextNumDelete(colHints);
        String bottomBar = "";
        for (int i = 0; i < length; i++) {
            bottomBar += "_ ";
        }
        colString += bottomBar;
        boardPrint+= colString + "\n";
        // make col string
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j == length - 1) {
                    boardPrint += " " + guessBoard[i][j] + "  |" + numBoard[1][i] + "\n";
                } else {
                    boardPrint += " " + guessBoard[i][j];
                }
            }
        }
        return boardPrint;
    }
}
