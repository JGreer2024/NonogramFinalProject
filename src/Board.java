public class Board {
    private int[][] board;
    private int difficulty;
    private boolean random;

    private String[][] numBoard; // first index = rows / second = col

    private int[][] guessBoard;

    private int length;

    public Board(int difficulty, boolean random) {
        this.difficulty = difficulty;
        this.random = random;
        if (!random) {
            board = alreadyMadeBoards(difficulty);
            //} else if (random == true){
        }
        numBoard = hintGetter();
        if (difficulty == 1) {
            guessBoard = new int[5][5];
        } else if (difficulty == 2) {
            guessBoard = new int[10][10];
        } else {
            guessBoard = new int[15][15];
        }
    }

    public void updateGraph(int row, int col) {
        if (board[row-1][col-1] == 1) {
            guessBoard[row-1][col-1] = 1;
        } else {
            board[row-1][col-1] = 2;
            guessBoard[row-1][col-1] = 2;
        }
        if (isEndBoard()) {
            endGame();
        }
    }

    public void endGame() {
        System.out.println("You completed the game! Congratulations!");
        System.exit(0);
    }

    public boolean isEndBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != guessBoard[i][j]) {
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
        } else if (difficulty == 2) {
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
        String[][] numberBoard = new String[2][board.length];
        String colHelper = "";
        String rowHelper = "";
        int rowNumbers = 0;
        int colNumbers = 0;
        for (int i = 0; i < board.length; i++) { // row getter
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    rowNumbers++;
                } if (board[i][j] == 0 || j == board.length - 1) {
                    if (rowNumbers != 0) {
                        rowHelper = rowHelper + "  " + rowNumbers;
                        rowNumbers = 0;
                    }
                }
                if (board[j][i] == 1) {
                    colNumbers++;
                } if (board[j][i] == 0 || j == board.length - 1) {
                    if (colNumbers != 0) {
                        colHelper = colHelper + "  " + colNumbers;
                        colNumbers = 0;
                    }
                }
                if (j == board.length - 1) {
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
        for (int i = 0; i < board.length; i++) {
            bottomBar += "_ ";
        }
        colString += bottomBar;
        boardPrint+= colString + "\n";
        // make col string
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (j == board.length - 1) {
                    boardPrint += " " + board[i][j] + "  |" + numBoard[1][i] + "\n";
                } else {
                    boardPrint += " " + board[i][j];
                }
            }
        }
        return boardPrint;
    }
}
