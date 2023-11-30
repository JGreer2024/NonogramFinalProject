public class Board {
    private int[][] board;
    private int difficulty;
    private boolean random;

    private String[][] numBoard; // first index = rows / second = col

    public Board(int difficulty, boolean random){
        this.difficulty = difficulty;
        this.random = random;
        if (!random){
            board = alreadyMadeBoards(difficulty);
        //} else if (random == true){
        }
        numBoard = hintGetter();

    }

    public int[][] alreadyMadeBoards(int difficulty){
        if (difficulty == 1){
            int[][] easyBoard = {{0,0,0,1,1},{1,1,1,0,0},{0,0,1,1,1},{1,0,1,0,1},{1,0,0,0,1}};
            return easyBoard;
        } else if (difficulty == 2) {
            int[][] mediumBoard = {{0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 1, 1, 1, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {1, 1, 0, 0, 1, 1, 0, 0, 0, 0}, {1, 1, 0, 0, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 1, 0, 0, 0}, {1, 1, 1, 1, 0, 1, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
            return mediumBoard;
        } else {
            int[][] hardBoard = {{1,1,0,0,0,0,1,1,1,0,0,0,0,1,1},{1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},{1,0,0,1,1,1,1,1,1,1,1,1,0,1,1},{1,1,1,0,0,0,1,1,0,0,0,1,1,1,1},{1,1,1,0,0,0,1,1,0,0,0,1,1,1,1},{0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,1,0,0,0},{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},{0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},{0,0,0,1,1,0,0,0,0,0,1,1,0,0,0}};
            return hardBoard;
        }
    }

    public String[][] hintGetter(){
        String[][] numberBoard = new String[board.length-1][board.length-1];
        String colHelper = "";
        String rowHelper = "";
        int rowNumbers = 0;
        int colNumbers = 0;
        for(int i = 0; i < board.length; i++){ // row getter
            for(int j = 0; j < board.length; j++){
                if (board[i][j] == 1){
                    rowNumbers ++;
                } else if (board[j][i] == 0 || j == board.length-1){
                    rowHelper = rowHelper + " " + rowNumbers;
                    rowNumbers = 0;
                } if (board[j][i] == 1){
                    colNumbers ++;
                } else if (board[j][i] == 0 || j == board.length-1) {
                    colHelper = colHelper + " " + colNumbers;
                    colNumbers = 0;
                } if (j == board.length -1){
                    numberBoard[i][]
                }
            }
        }
    }
}
