package study.lab.algorithm;

public class Queen {

    private int[] column;
    private int[] rup;
    private int[] lup;
    private int[] queen;
    private int num;

    private static final int QUEEN_NO = 8;

    public Queen() {
        column = new int[QUEEN_NO + 1];
        rup = new int[(2 * QUEEN_NO) + 1];
        lup = new int[(2 * QUEEN_NO) + 1];
        for (int i = 1; i <= QUEEN_NO; i++) {
            column[i] = 0;
        }
        for (int i = 1; i <= (2*QUEEN_NO); i++) {
            rup[i] = lup[i] = 0;
        }
        queen = new int[QUEEN_NO + 1];
    }

    public void backtrace(int i) {
        if (i > QUEEN_NO) {
            showAnswer();
        } else {
            for (int j = 1; j<=QUEEN_NO; j++) {
                if (column[j] == 0 && rup[i + j - 1] == 0 && lup[i - j + QUEEN_NO] == 0) {
                    queen[i] = j;
                    column[j] = rup[i + j - 1] = lup[i - j + QUEEN_NO] = 1;
                    backtrace(i + 1);
                    column[j] = rup[i + j - 1] = lup[i - j + QUEEN_NO] = 0;
                }
            }
        }
    }

    public void showAnswer() {
        num++;
        System.out.println("Answer: " + num);
        for (int y=1;y<=QUEEN_NO;y++) {
            for(int x = 1;x <=QUEEN_NO;x++) {
                if (queen[y] == x) {
                    System.out.print("Q");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Queen q = new Queen();
        q.backtrace(1);
    }
}
