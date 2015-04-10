package GCJ2014.qualificationRound;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-9.
 */
public class MinesweeperMaster {
    final static String PROBLEM_NAME = "C-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;
    static int cc = 0;
    static int rr = 0;
    public void solve(Scanner sc, PrintWriter pw){
        int R = sc.nextInt();
        int C = sc.nextInt();
        int M = sc.nextInt();
        if(R == 1 || C == 1){
            if(R * C - M < 1){
                pw.println("Impossible");
            }else{
                printBoard(pw, R, C, M);
            }
        }else if(R*C - M == 1){
            printBoard1(pw, R, C, M);
        }else{
            if(check(R, C, M)){
                printBoard2(pw, R, C, M);
            }else{
                pw.println("Impossible");
            }
        }

    }

    public void printBoard2(PrintWriter pw, int R, int C, int M){
        char[][] board = new char[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                board[i][j] = '*';
            }
        }

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < cc; j++){
                board[i][j] = '.';
            }
        }
        for(int i = 2; i < rr; i++){
            for(int j = 0; j< 2; j++){
                board[i][j] = '.';
            }
        }
        board[0][0] = 'c';
        int count = R*C-M - (2*rr + 2*cc -4);
        int indexx = 2;
        int indexy = 2;
        while(count > 0){
            board[indexx][indexy++] = '.';
            if(indexy >= cc){
                indexy = 2;
                indexx++;
            }
            count--;
        }
        printOut(pw, board);
    }

    public boolean check(int R, int C, int M){
        for(rr = 2; rr <= R ; rr++){
            for(cc = 2; cc <= C; cc++){
                if(2 * rr + 2 * cc -4 <= R * C - M && R * C - M <= rr * cc){
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard1(PrintWriter pw, int R, int C, int M){
        char[][] board = new char[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                board[i][j] = '*';
            }
        }
        board[0][0] = 'c';
        printOut(pw, board);
    }

    public void printOut(PrintWriter pw, char[][] board){
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                pw.print(board[i][j]);
            }
            pw.println();
        }
    }
    public void printBoard(PrintWriter pw, int R, int C, int M){
        char[][] board = new char[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                board[i][j] = '.';
            }
        }
        board[0][0] = 'c';
        if(R == 1){
            int index = C-1;
            while(M > 0){
                board[0][index--] = '*';
                M--;
            }
        }else{
            int index = R-1;
            while(M > 0){
                board[index--][0] = '*';
                M--;
            }
        }
        printOut(pw, board);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.println("Case #" + (caseNum+1) + ": ");
            new MinesweeperMaster().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
