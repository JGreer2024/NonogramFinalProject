public class Main {
    public static void main(String[] args) {
        Board testCase = new Board(1, false);
        System.out.println(testCase);
        testCase.updateGraph(1,4);
        System.out.println(testCase);
    }
}